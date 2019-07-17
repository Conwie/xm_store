package com.xm.xmstore.mapper;

import java.util.List;

import com.xm.xmstore.entity.Product;

/**
 * 商品实体类的持久层接口
 * @author Administrator
 *
 */
public interface ProductMapper {
	
	/**1. 根据priority来查询前5个热销商品*/
	List<Product> findByPriority();
	
}
















