package com.xm.xmstore.service.impl;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xm.xmstore.entity.Cart;
import com.xm.xmstore.mapper.CartMapper;
import com.xm.xmstore.service.CartService;
import com.xm.xmstore.service.ex.AccessDeniedException;
import com.xm.xmstore.service.ex.CartNotFoundException;
import com.xm.xmstore.service.ex.UpdateException;
/**
 * 购物车业务层
 * @author Administrator
 *
 */
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public void addNum(Integer cid, Integer uid, String username)
			throws CartNotFoundException, AccessDeniedException, UpdateException {
		// 根据参数cid查询数据
		Cart result = getByCid(cid);
		// 从查询结果中获取尝试购买的原数量
		Integer oldNum = result.getNum();
		// 将数量更新为原数量+1的结果
		updateNum(cid, oldNum+1, username, new Date());
		
		
	}
	
	@Override
	public void reduceNum(Integer cid, Integer uid, String username)
			throws CartNotFoundException, AccessDeniedException, UpdateException {
		// 根据参数cid查询数据
		Cart result = getByCid(cid);
		// 从查询结果中获取尝试购买的原数量
		Integer oldNum = result.getNum();
		// 将数量更新为原数量+1的结果
		updateNum(cid, oldNum-1, username, new Date());
		
		
	}
	
	
	 /**1.修改商品在购物车中的数量*/
	
	private void updateNum(Integer cid, Integer num,
		String username, Date modifiedTime) {
		Integer rows = cartMapper.updateNum(cid, num, username, modifiedTime);
		if (rows != 1) {
			throw new UpdateException(
				"调整商品数量失败！修改数据时发生未知错误！");
		}
	}
	
	/** 2.用参数Cid查询提单数据
	 * @return */
	private Cart getByCid(Integer cid) {
		Cart cart = cartMapper.findByCid(cid);
		// 判断查询结果是否为null：CartNotFoundException
		if(cart==null) {
			throw new CartNotFoundException("增加失败！尝试访问的购物车数据不存在");
		}
		// 判断查询结果中的uid与参数uid是否不同：AccessDeniedException
		if (cart.getCid() != cid) {
			throw new AccessDeniedException(
				"增加失败！不允许操作他人的数据！");
		}
		return cart;
		
	}
	

}
