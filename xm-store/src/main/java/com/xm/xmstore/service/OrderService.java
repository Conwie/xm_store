package com.xm.xmstore.service;

import java.util.List;

import com.xm.xmstore.entity.Order;
import com.xm.xmstore.entity.OrderItem;
import com.xm.xmstore.service.ex.OrderNotFoundException;
import com.xm.xmstore.service.ex.UpdateException;



/**
 * 处理订单数据的业务层逻辑接口
 */
public interface OrderService {
	
	public interface Status{
		int UNPAID = 0;
		int PAID = 1;
		int CANCELED = 2;
		int CLOSED = 3;
	}
	
	/**
	 * 创建订单
	 * @param aid 收货地址id
	 * @param cids 购物车数据列表的id
	 * @param uid 用户id
	 * @param username 用户名
	 * @return 订单数据
	 */
	Order create(Integer aid,Integer[] cids,Integer uid,String username);
	
	/**
	 * 修改订单状态
	 * @param oid 读巴格达吧ud
	 * @param status 新的状态值，应该使用{@link Status}中的值
	 * @param username
	 * @throws OrderNotFoundException
	 * @throws UpdateException
	 * @see Status
	 */
	void changeStatus(Integer oid,Integer status,String username) throws OrderNotFoundException,UpdateException;

	/**
	 * 关闭订单
	 * @param oid 订单id
	 * @param orderItems 订单详情
	 * @param username 执行人
	 */
	void close(Integer oid,List<OrderItem> orderItems,String username);
}
