package com.xm.xmstore.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xm.xmstore.entity.Product;

/**
 * 商品实体类的持久层接口
 * @author Administrator
 *
 */
public interface ProductMapper {
	
	/**根据priority来查询前5个热销商品*/
	List<Product> findByPriority();
	
/*	*//**根据商品id更新商品库存*//*
	Integer updateNum(@Param("pid")Integer pid,@Param("num")Integer num);*/
	
	
	
}
















