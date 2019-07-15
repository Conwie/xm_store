package com.xm.xmstore.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.xm.xmstore.entity.Cart;
/**
 * 订单持久层接口
 * @author Administrator
 *
 */
public interface CartMapper {
	/** 1.用参数Cid查询提单数据*/
	Cart findByCid(Integer cid);
	
	/** 2.修改商品在购物车的数量*/
	Integer updateNum(
			@Param("cid") Integer cid, 
			@Param("num") Integer num,
			@Param("username") String username, 
			@Param("modifiedTime") Date modifiedTime);
	
}
