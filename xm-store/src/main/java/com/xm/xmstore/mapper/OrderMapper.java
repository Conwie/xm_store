package com.xm.xmstore.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.xm.xmstore.entity.Order;
import com.xm.xmstore.entity.OrderItem;


/**
 * 处理订单和订单商品数据的持久层接口
 */
public interface OrderMapper {
	
	/**
	 * 添加订单数据
	 * @param order 订单数据
	 * @return 受影响的行数
	 */
	Integer insertOrder(Order order);
	
	/**
	 * 添加订单商品数据
	 * @param orderItem 订单商品数据
	 * @return  受影响的行数
	 */
	Integer insertOrderItem(OrderItem orderItem);
	
	/**
	 * 更新订单状态
	 * @param oid 订单id
	 * @param status 订单状态
	 * @param username 执行人
	 * @param midifiedTime 修改时间
	 * @return 受影响行数
	 */
	Integer updateStatus(@Param("oid")Integer oid,
						@Param("status")Integer status,
						@Param("username")String username,
						@Param("modifiedTime")Date modifiedTime );
	
	/**
	 * 根据订单id查找订单信息
	 * @param oid 订单id
	 * @return 订单信息
	 */
	Order findByOid(Integer oid);
	
	
	
}
