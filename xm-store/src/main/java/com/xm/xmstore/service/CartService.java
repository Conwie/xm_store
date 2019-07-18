package com.xm.xmstore.service;

import java.util.List;

import com.xm.xmstore.entity.CartVO;
import com.xm.xmstore.service.ex.AccessDeniedException;
import com.xm.xmstore.service.ex.CartNotFoundException;
import com.xm.xmstore.service.ex.DeleteException;
import com.xm.xmstore.service.ex.UpdateException;

public interface CartService {
	
	/**商品数量增加*/
	void addNum(Integer cid, Integer uid, String username) throws CartNotFoundException, AccessDeniedException, UpdateException;
	
	/**商品数量减少*/
	void reduceNum(Integer cid, Integer uid, String username) throws CartNotFoundException, AccessDeniedException, UpdateException;
	
	/**加入购物车数据*/
	void addToCart(Integer uid, Integer pid, Integer num, String username);
	
	/**获取uid，查询购物车*/
	List<CartVO> getByUid(Integer uid);
	
	/**删除购物车数据*/
	void deleteCart(Integer uid, Integer cid) throws CartNotFoundException, AccessDeniedException,DeleteException;
	
	/**
	 * 根据购物车数据的若干个id值获取匹配的购物车数据的详情
	 * @param cids 若干个购物车数据的id
	 * @return 匹配的购物车数据的详情
	 */
	List<CartVO> getByCids(Integer[] cids, Integer uid);
	
	void delete(Integer[] cids, Integer uid);
	
}
