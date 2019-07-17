package com.xm.xmstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xm.xmstore.entity.Product;
import com.xm.xmstore.mapper.ProductMapper;
import com.xm.xmstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	/**1. 根据priority来获取前5个热销商品*/
	public List<Product> getByPriority() {
		List<Product> list = findByPriority();
		// 将一些属性置空
		for (Product product : list) {
			product.setStatus(null);
			product.setPriority(null);
			product.setCreatedUser(null);
			product.setCreatedTime(null);
			product.setModifiedUser(null);
			product.setModifiedUser(null);
		}
		
		return list;
	}

	/**根据priority来查询前5个热销商品--1*/
	private List<Product> findByPriority(){
		return productMapper.findByPriority();
	}
	
}




















