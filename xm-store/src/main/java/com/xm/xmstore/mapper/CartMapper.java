package com.xm.xmstore.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xm.xmstore.entity.Cart;
import com.xm.xmstore.entity.CartVO;

/**
 * 订单持久层接口
 * @author Administrator
 *
 */
public interface CartMapper {
	/**插入购物车数据*/
	Integer insert(Cart cart);
	/** 用参数Cid查询提单数据*/
	Cart findByCid(Integer cid);
	
	/** 修改商品在购物车的数量*/
	Integer updateNum(
			@Param("cid") Integer cid, 
			@Param("num") Integer num,
			@Param("username") String username, 
			@Param("modifiedTime") Date modifiedTime);
	/** 根据用户id和商品id查询购物车数据 */
	Cart findByUidAndPid(
			@Param("uid") Integer uid,
			@Param("pid") Integer pid);
	/**根据uid查询购物车数据*/
	List<CartVO> findByUid(Integer uid);
	
	/**根据cid删除购物车数据*/
	Integer deleteByCid(Integer cid);
	
}
