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
	
	/**1. 根据priority来查询前5个热销商品*/
	List<Product> findByPriority();
	
	/**
	 * 根据商品id查询商品详情
	 * @param id 商品id
	 * @return 匹配的商品详情，如果没有匹配的数据，则返回null
	 */
	
	Product findById(Integer pid);
	
	/**
	 * 更新商品的库存
	 * @param pid 商品的id
	 * @param num 新的库存量
	 * @return 受影响的行数
	 */
	Integer updateNum(
			@Param("pid") Integer pid, 
			@Param("num") Integer num);
	
	
}
















