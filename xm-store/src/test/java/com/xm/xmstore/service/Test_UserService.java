package com.xm.xmstore.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xm.xmstore.entity.User;
import com.xm.xmstore.service.ex.ServiceException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_UserService {
	@Autowired	
	UserService service;
	
	@Test
	public void reg(){
		try {
			User user = new User();
			user.setUsername("test01");
			user.setPassword("123321");
			service.reg(user);
			System.out.println("注册成功！");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void login() {
		try {
			User user = service.login("root", "123321", "");
			System.err.println(user);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changePassword() {
		try {
			Integer uid = 2;
			String oldPassword = "123321";
			String newPassword = "123456";
			String modifiedUser = "系统管理员";
			service.changePassword(uid, oldPassword, newPassword, modifiedUser);
			System.err.println("OK");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
}
