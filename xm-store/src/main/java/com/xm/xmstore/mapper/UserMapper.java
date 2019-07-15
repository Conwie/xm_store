package com.xm.xmstore.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.xm.xmstore.entity.User;

/**
 * 	处理用户数据操作的数据访问逻辑
 * 	@author jamie
 *
 */
public interface UserMapper {
	
	/** 添加新用户数据*/
	Integer addnew(User user);
	
	/** 修改密码，最后修改人，最后修改时间*/
	Integer updatePassword(@Param("uid")Integer uid, 
					@Param("password")String password, 
					@Param("modifiedUser")String modifiedUser, 
					@Param("modifiedTime")Date modifiedTime);
	
	/** 修改个人资料 */
	Integer updateInfo(User user);
	
	/**
	 * 	更新头像
	 * @param uid 用户id
	 * @param avatar 新头像路径
	 * @param modifiedUser	最后修改人
	 * @param modifiedTime	最后修改时间
	 * @return 返回成功行数
	 */
	Integer updateAvatar(
			@Param("uid")Integer uid, 
			@Param("avatar")String avatar, 
			@Param("modifiedUser")String modifiedUser, 
			@Param("modifiedTime")Date modifiedTime
			);
	
	/** 根据用户名查询用户数据*/
	User findByUsername(String username);
	
	/** 根据手机号码查询用户数据*/
	Integer findByPhone(String phone);
	
	/** 根据用户id查询用户数据 */
	User findByUid(Integer uid);
	
	
	
}






