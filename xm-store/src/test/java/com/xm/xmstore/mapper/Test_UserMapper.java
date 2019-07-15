package com.xm.xmstore.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xm.xmstore.entity.User;
import com.xm.xmstore.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_UserMapper {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserServiceImpl service;
	
	@Test
	/**1. 添加新用户数据*/
	public void addnew() {
		User user = new User();
		user.setUsername("root");
		user.setPassword("123456");
		user.setSalt("121354561216565");
		user.setPhone("13570296744");
		user.setIsDelete(0);
		Date now = new Date();
		user.setCreatedTime(now);
		user.setCreatedUser("root");
		user.setModifiedUser("root");
		user.setModifiedTime(now);
		userMapper.addnew(user);
	}
	
	@Test
	public void findByUsername() {
		String username = "root";
		User user = userMapper.findByUsername(username);
		System.err.println(user);
		System.err.println(user.getModifiedUser());
	}
	
	@Test
	public void findByPhone() {
		String phone = "13570296744";
		Integer rows = userMapper.findByPhone(phone);
		System.err.println(rows);
	}
	
	@Test
	public void findByUid() {
		User user =userMapper.findByUid(1);
		System.err.println(user);
	}
	
	@Test
	public void updatePassword() {
		User user =userMapper.findByUid(4);
		String newPassword=service.getMessageDigest("123456",user.getSalt()); 
		Integer rows = userMapper.updatePassword(4, newPassword,"锋哥", new Date());
		System.err.println(rows);
	}
	
	
}













