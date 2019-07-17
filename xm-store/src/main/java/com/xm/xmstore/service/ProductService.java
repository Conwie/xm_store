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
	
	/**
	 * 2. 根据商品id查询商品详情
	 * @param id 商品id
	 * @return 匹配的商品详情，如果没有匹配的数据，则返回null
	 */
	Product getById(Integer id);
	
}















