package com.xm.xmstore.service;


import com.xm.xmstore.entity.User;
import com.xm.xmstore.service.ex.ServiceException;
import com.xm.xmstore.service.ex.UpdateException;
import com.xm.xmstore.service.ex.UserNotFoundException;

/**
 * 	处理用户相关数据的业务逻辑接口
 * @author Administrator
 *
 */
public interface UserService {
	
	/** 处理用户注册的操作*/
	void reg(User user) throws ServiceException;
	/** 处理用户登录的操作*/
  User login(String username,String password,String code) throws ServiceException;
	/** 处理用户修改密码的操作*/
	void changePassword(Integer uid,String oldPassword,String newPassword,String modifiedUser) throws ServiceException;
	/**修改用户个人信息*/
	void changeInfo(Integer uid, String username, User user) throws UserNotFoundException, UpdateException;
	/**更新头像*/
	public void changeAvatar(Integer uid,String avatar,String modifiedUser) 
		throws UserNotFoundException, UpdateException;
	/**根据用户id获取用户个人信息*/
	User getByUid(Integer uid) throws UserNotFoundException;
	
	
	
	Boolean checkUser(String username);
}
