package com.xm.xmstore.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xm.xmstore.entity.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTests {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	/**1. 根据priority来查询前5个热销商品*/
	public void findByPriority(){
		List<Product> list = productMapper.findByPriority();
		for(Product item : list) {
			System.err.println(item);
		}
	}
<<<<<<< HEAD
	
	/** 根据商品id查询商品详情 */
	@Test
	 public void findById() {
	  Integer id = 100001;
	  Product data = productMapper.findById(id);
	  System.err.println(data);
	 }
=======
>>>>>>> conwie
}






















