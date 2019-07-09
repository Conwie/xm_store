package com.xm.xmstore.service.impl;

import java.util.Date;
import java.util.UUID;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.xm.xmstore.entity.User;
import com.xm.xmstore.mapper.UserMapper;
import com.xm.xmstore.service.UserService;
import com.xm.xmstore.service.ex.InsertException;
import com.xm.xmstore.service.ex.PasswordNotMatchException;
import com.xm.xmstore.service.ex.PhoneDuplicateException;
import com.xm.xmstore.service.ex.ServiceException;
import com.xm.xmstore.service.ex.UpdateException;
import com.xm.xmstore.service.ex.UserNotFoundException;
import com.xm.xmstore.service.ex.UsernameDuplicateException;

/**
 * 处理用户数据的业务逻辑接口实现类
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	/** 处理用户注册的操作*/
	@Override
	public void reg(User user) {
		// 获取用户名、手机号码
		String username = user.getUsername();
		String phone = user.getPhone();
		// 根据用户名查询用户数据
		User result = findByUsername(username);
		// 判断用户是否存在
		if(result != null) {
			// 是，抛出异常
			throw new UsernameDuplicateException("用户名已注册");
		}
		// 根据手机号码查询用户是否被使用
		findByPhone(phone);
		
		//否，插入数据
		String password = user.getPassword();
		String salt = UUID.randomUUID().toString();
		String mdPassword = getMessageDigest(password, salt);
		user.setPassword(mdPassword);
		user.setSalt(salt);
		user.setIsDelete(0);
		Date now = new Date();
		user.setCreatedUser(username);
		user.setCreatedTime(now);
		user.setModifiedUser(username);
		user.setModifiedTime(now);
		addnew(user);	
	}
	
	/** 处理用户登录的操作*/
	public User login(String username, String password,String code) 
    
			throws PasswordNotMatchException, UserNotFoundException {
			// 根据参数username执行查询
			User result = findByUsername(username);
			
			// 判断查询结果是否为null
			if(result==null) {
				// 是：抛出UserNotFoundException
				throw new UserNotFoundException("用户名不存在！");
			}
			// 判断查询结果中的isDelete是否为1
			if(result.getIsDelete()==1) {
				// 是：抛出UserNotFoundException
				throw new UserNotFoundException("用户名不存在！");
			}
			
			// 从查询结果中获取盐值
			String salt = result.getSalt();
			// 基于参数password和盐值执行加密
//			System.err.println("password0000000"+password);
			String md5 = getMessageDigest(password,salt); 
			// 判断以上加密结果与查询结果中的password是否不匹配
			if(!result.getPassword().equals(md5)) {
			/*	System.err.println("md5"+md5);
				System.err.println("password"+result.getPassword());*/
				// 是：抛出PasswordNotMatchException
				throw new PasswordNotMatchException("密码错误");	
			}
			// 将查询结果中的password设置为null
			result.setPassword(null);
			// 将查询结果中的idDelete设置为null
			result.setIsDelete(null);
			// 将查询结果中的salt设置为null
			result.setSalt(null);
			// 返回查询结果
			return result;
		
	}
	
	/** 更改密码操作 */
	public void changePassword(Integer uid,String oldPassword,String newPassword,String modifiedUser) throws ServiceException {
		// 根据参数uid查询用户数据
		User result = findByUid(uid);
		// 判断查询结果是否为null：UserNotFoundException
		if(result==null||result.getIsDelete()==1) {
			// 判断查询结果中的isDelete是否为1：UserNotFoundException
			throw new UserNotFoundException("修改个人资料失败！用户数据不存在！");
		}
		// 从查询结果中获取盐值
		String salt = result.getSalt();
		// 对参数oldPassword执行加密，得到oldMd5Password
		String oldMd5Password = getMessageDigest(oldPassword,salt);
		// 判断查询结果中的密码与oldMd5Password是否不匹配：PasswordNotMatchException
		if(!oldMd5Password.equals(result.getPassword())) {
//			System.err.println(oldMd5Password+"="+result.getPassword());
			throw new PasswordNotMatchException("密码错误");
		}
		// 对参数newPassword执行加密，得到newMd5Passowrd
		String newMd5Password = getMessageDigest(newPassword,salt);
		
		// 执行更新，获取返回值(受影响的行数)
		updatePassword(uid, newMd5Password, modifiedUser, new Date());

	}
	
	/** 修改资料操作  */
	public void changeInfo(Integer uid, String username, User user) throws UserNotFoundException, UpdateException {
		// 根据参数uid查询用户数据
		User result = userMapper.findByUid(uid);
		// 判断查询结果是否为null：UserNotFoundException
		if(result==null||result.getIsDelete()==1) {
		// 判断查询结果中的isDelete是否为1：UserNotFoundException
		throw new UserNotFoundException("修改个人资料失败！用户数据不存在！");
		}
		// 将参数uid和参数username封装到参数user的uid和modifiedUser属性中
		user.setUid(uid);
		user.setModifiedUser(username);
		// 执行更新，获取返回值(受影响的行数)
		updateInfo(user);
		// 判断受影响的行数是否不为1：UpdateException
	}

	/** 获取用户信息操作*/
	public User getByUid(Integer uid) {
		
		// 根据参数uid查询用户数据
		User result = findByUid(uid);
		// 判断查询结果是否为null：UserNotFoundException
		// 判断查询结果中的isDelete是否为1：UserNotFoundException
		if(result==null||result.getIsDelete()==1) {
			throw new UserNotFoundException("获取用户资料失败！用户数据不存在！");
		}
		// 将查询结果中不相关的数据设置为null
		User user = new User();
		user.setUsername(result.getUsername());
		user.setNickname(result.getNickname());
		user.setPhone(result.getPhone());
		user.setEmail(result.getEmail());
		user.setGender(result.getGender());
		// 返回查询结果
		return user;
	}
	public Boolean checkUser(String username) {
		User result = findByUsername(username);
		return result == null ? false : true;
	}
	
	//更新头像
	
			@Override
			public void changeAvatar(Integer uid, String avatar, String modifiedUser)
					throws UserNotFoundException, UpdateException {
					//根据参数uid查询用户数据
					User result = userMapper.findByUid(uid);
//					System.err.println(result);
					//判断查询结果是否为null
					//判断查询结果中的isDelete是否为1
					if(result==null || result.getIsDelete()==1) {
						//是,抛出UserNotFoundException
						throw new UserNotFoundException("更新失败,用户不存在"); 				
					}
					
					//执行更新,返回成功行数
					Integer rows = userMapper.updateAvatar(uid, avatar, modifiedUser, new Date());
					//判断行数是否为1
					if(rows!=1) {
						throw new UpdateException("更新失败,发生未知错误");
					}
			
		}
	

	/**加密操作*/
	public String getMessageDigest(String password, String salt) {
		String str = salt + password + salt;	
		for(int i=0; i<3; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes());
		}
		return str;
	}

	
	
	/**1. 添加新用户数据*/
	private void addnew(User user) {
		Integer rows = userMapper.addnew(user);
		if(rows != 1) {
			throw new InsertException("注册失败！由于未知因素，请联系管理员或重新注册！");
		}		
	}
	
	/**2. 根据用户名查询用户数据*/
	private User findByUsername(String username) {
		if(username == null || "".equals(username)) {
			throw new IllegalArgumentException("用户名不能为空！");
		}
		User result = userMapper.findByUsername(username);
		
		return result;
	}
	
	/**3. 根据手机号码查询用户数据*/
	private void findByPhone(String phone) {
		if(phone == null || "".equals(phone)) {
			throw new IllegalArgumentException("手机号不能为空！");
		}
		Integer rows = userMapper.findByPhone(phone);
		// 判断用户是否存在
		if(rows > 0) {
			// 是
			throw new PhoneDuplicateException("手机号码已注册");
		}
	}
	
	/**4.根据用户id查询用户数据 */
	private User findByUid(Integer uid) {
		if(uid == null || uid < 1) {
			throw new IllegalArgumentException("uid不能为null且需大于0");
		}
		User result = userMapper.findByUid(uid);
		return result;
	}
	 
	/**5.修改密码，最后修改人，最后修改时间*/
	private void updatePassword(@Param("uid")Integer uid, 
					@Param("password")String password, 
					@Param("modifiedUser")String modifiedUser, 
					@Param("modifiedTime")Date modifiedTime) {
		if(password == null || "".equals(password)) {
			throw new IllegalArgumentException("password不能为空！");
		}
		Integer rows = userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		// 判断受影响的行数是否不为1：UpdateException	
		if(rows!=1) {
			throw new UpdateException("更新异常，请与管理员联系");
		}
	}

	

	
	/** 修改个人资料 */
	private void updateInfo(User user) {
		Integer rows = userMapper.updateInfo(user);
		if(rows!=1) {
			throw new UpdateException("修改个人资料失败！更新数据时出现未知错误！");
		}
	}
}















