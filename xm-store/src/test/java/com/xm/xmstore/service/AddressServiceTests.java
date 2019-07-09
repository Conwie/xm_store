package com.xm.xmstore.service;

import java.nio.file.AccessDeniedException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xm.xmstore.service.ex.AddressCountLimitException;
import com.xm.xmstore.service.ex.AddressNotFoundException;
import com.xm.xmstore.service.ex.DeleteException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTests {
	
	@Autowired
	private AddressService service;
	
	@Test
	public void delete() {
		Integer aid = 3;
		Integer uid = 1;
		String username = "xixix";
		try {
			service.delete(aid, uid, username);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
