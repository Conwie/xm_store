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
	
	/**
	 * 2. 根据商品id查询商品详情
	 * @param id 商品pid
	 * @return 匹配的商品详情，如果没有匹配的数据，则返回null
	 */
	Product findById(Integer id);
}
















