package com.xm.xmstore.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xm.xmstore.entity.Product;
import com.xm.xmstore.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {
	
	@Autowired
	private ProductService productService;
	
	/**1. 根据priority来获取前5个热销商品*/
	@Test
	public void  getByPriority() {
		try {
			List<Product> list = productService.getByPriority();
			for(Product item : list) {
				System.err.println(item);
			}
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	
	
		
	
}













