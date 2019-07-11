package com.xm.xmstore.service;


import com.xm.xmstore.entity.User;
import com.xm.xmstore.service.ex.InsertException;
import com.xm.xmstore.service.ex.PasswordNotMatchException;
import com.xm.xmstore.service.ex.ServiceException;
import com.xm.xmstore.service.ex.UpdateException;
import com.xm.xmstore.service.ex.UserNotFoundException;
import com.xm.xmstore.service.ex.UsernameDuplicateException;

/**
 * 	处理用户相关数据的业务逻辑接口
 * @author Administrator
 *
 */
public interface UserService {
	
	/** 处理用户注册的操作*/
	void reg(User user) throws UsernameDuplicateException,InsertException;
	/** 处理用户登录的操作*/
  User login(String username,String password,String code) throws PasswordNotMatchException,UserNotFoundException;
	/** 处理用户修改密码的操作*/
	void changePassword(Integer uid,String oldPassword,String newPassword,String modifiedUser) throws UserNotFoundException,PasswordNotMatchException;
	/**修改用户个人信息*/
	void changeInfo(Integer uid, String username, User user) throws UserNotFoundException, UpdateException;
	/**更新头像*/
	public void changeAvatar(Integer uid,String avatar,String modifiedUser) 
		throws UserNotFoundException, UpdateException;
	/**根据用户id获取用户个人信息*/
	User getByUid(Integer uid) throws UserNotFoundException;
	/**判断用户名是否被注册*/
	Boolean checkUser(String username);
	/**判断手机号是否被注册*/
	void checkPhone(String phone);
}
