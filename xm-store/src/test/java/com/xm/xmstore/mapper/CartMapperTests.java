package com.xm.xmstore.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xm.xmstore.entity.Cart;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTests {
	@Autowired
	private CartMapper cartMapper;
	
	@Test
	public void updateNum() {
		Integer cid = 1;
		Integer num = 100;
		String username = "小李";
		Integer rows = cartMapper.updateNum(cid, num, username, new Date());
		System.err.println(rows);
	}
	
	@Test
	public void findByCid() {
		Integer cid = 1;
		Cart cart = cartMapper.findByCid(cid);
		System.err.println(cart);
	}
}






