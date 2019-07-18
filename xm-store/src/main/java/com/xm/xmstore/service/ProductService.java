package com.xm.xmstore.service;

import java.util.List;

import com.xm.xmstore.entity.Product;
import com.xm.xmstore.service.ex.ProductNotFoundException;

/**
 * 商品数据的业务层接口
 * @author Administrator
 *
 */
public interface ProductService {
	
	/**1. 根据priority来获取前5个热销商品*/
	List<Product> getByPriority();

	Product getById(Integer id) throws ProductNotFoundException;
	
	/**
	 * 减少指定商品的库存量
	 * @param pid 商品的id
	 * @param amount 减少的库存量
	 */
	void reduceNum(Integer pid, Integer amount);
	
	/**
	 * 增加指定商品的库存量
	 * @param pid 商品的id
	 * @param amount 增加的库存量
	 */
	void addNum(Integer pid, Integer amount);
	
	
}















