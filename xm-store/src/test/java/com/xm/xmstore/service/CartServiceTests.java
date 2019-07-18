package com.xm.xmstore.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
	@Autowired
	CartService cartService;
	@Test
	public void updateNum() {
		Integer cid = 2;
		Integer uid = 2;
		String username = "管理员";
		cartService.addNum(cid, uid, username );
		System.err.println("ok");
	}
	
}
