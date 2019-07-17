package com.xm.xmstore.service;

import java.util.List;

import com.xm.xmstore.entity.Product;

/**
 * 商品数据的业务层接口
 * @author Administrator
 *
 */
public interface ProductService {
	
	/**1. 根据priority来获取前5个热销商品*/
	List<Product> getByPriority();
	
}















