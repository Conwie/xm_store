package com.xm.xmstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xm.xmstore.entity.Address;
import com.xm.xmstore.entity.Order;
import com.xm.xmstore.entity.OrderItem;
import com.xm.xmstore.mapper.OrderMapper;
import com.xm.xmstore.service.AddressService;
import com.xm.xmstore.service.CartService;
import com.xm.xmstore.service.OrderService;
import com.xm.xmstore.service.ex.InsertException;
import com.xm.xmstore.service.ex.OrderNotFoundException;
import com.xm.xmstore.service.ex.UpdateException;


/**
 * 处理订单数据的业务层逻辑接口实现类 
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CartService cartService;
//	@Autowired
//	private ProductService productService;
	
	@Transactional
	public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
		//创建当前时间对象
		Date now = new Date();
		// 根据参数cids，通过cartService的getByCids()
		// 查询购物车数据，得到List<CartVO>类型的对象
/*		List<CartVO> list = cartService.getByCids(cids, uid);
		// 遍历购物车集合对象以计算总价
		Long totalPrice = 0L;
		for (CartVO cartVO : list) {
			System.err.println(cartVO.getNum());
			System.err.println(cartVO.getRealPrice());
			totalPrice += cartVO.getNum() * cartVO.getRealPrice();
			
		}*/
		
		//准备一个orderItem集合，关闭订单操作需要的数据
		List<OrderItem> orderItems = new ArrayList<>();
		
		// 创建Order对象
		Order order = new Order();
		// 补充Order对象属性：uid => 参数
		order.setUid(uid);
		// 根据参数aid，通过addressService查询收货地址详情
		Address address = addressService.getByAid(aid);
		// 补充Order对象属性：recv_*
		order.setRecvProvince(address.getProvinceName());
		order.setRecvCity(address.getCityName());
		order.setRecvArea(address.getAreaName());
		order.setRecvAddress(address.getAddress());
		order.setRecvName(address.getName());
		order.setRecvPhone(address.getPhone());
		// 补充Order对象属性：tota_price => ?
/*		order.setTotalPrice(totalPrice);*/
		// 补充Order对象属性：status => 0
		order.setStatus(0);
		// 补充Order对象属性：order_time => 现在
		order.setOrderTime(now);
		// 补充Order对象属性：pay_time => null
		order.setPayTime(null);
		// 补充Order对象属性：日志 => 参数username，当前时间
		order.setCreatedUser(username);
		order.setCreatedTime(now);
		order.setModifiedUser(username);
		order.setModifiedTime(now);
		// 插入订单数据：insertOrder(order)
		insertOrder(order);
		System.err.println("order" + order.getOid());
		// 遍历购物车集合对象
/*		for (CartVO cartVO : list) {
			// -- 创建OrderItem对象
			OrderItem orderItem = new OrderItem();
			// -- 补充OrderItem对象属性：oid => order.getOid();
			orderItem.setOid(order.getOid());
			// -- 补充OrderItem对象属性：pid, title, image, price, num 
															//=> 遍历对象pid, title, iamge, realPrice, num
			orderItem.setPid(cartVO.getPid());
			orderItem.setTitle(cartVO.getTitle());
			orderItem.setImage(cartVO.getImage());
			orderItem.setPrice(cartVO.getPrice());
			orderItem.setNum(cartVO.getNum());
			orderItem.setCreatedTime(now);
			orderItem.setCreatedUser(username);
			orderItem.setModifiedTime(now);
			orderItem.setModifiedUser(username);
			// -- 插入订单商品数据：insertOrderItem(orderItem)
			insertOrderItem(orderItem);
			//把订单详情数据添加到集合里
			orderItems.add(orderItem);
			//销库存
			productService.reduceNum(cartVO.getPid(), cartVO.getNum());
		}
		*/
		// 删除购物车中对应的数据
		/*cartService.delete(cids, uid);*/
		
		// TODO 开启倒计时任务(Timer/Thread)，如果用户在规定时间内未支付，则归还库存
		
		new Thread() {
			public void run() {
				System.err.println("OrderService:准备开始倒计时30秒，30秒后未支付则关闭订单");
				try {
					Thread.sleep(3 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				close(order.getOid(), orderItems, username);
				System.err.println("OrderService:订单已关闭！");
			}
		}.start();
		
		return order;
	}
	

	public void changeStatus(Integer oid, Integer status, String username) throws OrderNotFoundException{
		// 先查找订单是否存在
		Order result = findByOid(oid);
		// 判断查询结果是否不存在：OrderNotFoundException
		if(result == null) {
			throw new OrderNotFoundException("更新订单状态失败！订单数据不存在！");
		}
		// 修改订单状态
		updateStatus(oid, status, username, new Date());
	}
	
	@Override
	public void close(Integer oid, List<OrderItem> orderItems, String username) {
		System.err.println("close:oid=" + oid);
		// 检查订单
		Order result = findByOid(oid);
		if(result == null) {
			throw new OrderNotFoundException("订单关闭失败！订单不存在！");
		}
		
		//检查订单当前状态是否不是“未支付”
		if(result.getStatus() != Status.UNPAID) {
			return;
		}
		
		// 将订单状态修改为关闭
		changeStatus(oid, Status.CLOSED, username);
		
		// 归还订单商品的库存
		for (OrderItem orderItem : orderItems) {
			/*productService.addNum(orderItem.getPid(), orderItem.getNum());*/
		}
		
	}
	
	
	/**
	 * 添加订单数据
	 * @param order 订单数据
	 * @return 受影响的行数
	 */
	private void insertOrder(Order order) {
		Integer rows = orderMapper.insertOrder(order);
		if(rows != 1) {
			throw new InsertException("添加订单数据失败！插入数据时出现未知错误！");
		}
	}
	
	/**
	 * 添加订单商品数据
	 * @param orderItem 订单商品数据
	 * @return  受影响的行数
	 */
	private void insertOrderItem(OrderItem orderItem) {
		Integer rows = orderMapper.insertOrderItem(orderItem);
		if(rows != 1) {
			throw new InsertException("添加订单数据失败！插入数据时出现未知错误！");
		}
	}
	
	/**
	 * 更新订单状态
	 * @param oid 订单id
	 * @param status 订单状态
	 * @param username 执行人
	 * @param midifiedTime 修改时间
	 * @throw UpdateException 更新异常
	 */
	private void updateStatus(Integer oid,Integer status,String username,Date modifiedTime ) throws UpdateException{
		Integer rows = orderMapper.updateStatus(oid, status, username, modifiedTime);
		if(rows != 1) {
			throw new UpdateException("修改订单状态失败！更新数据时出现异常！");
		}
	}
	
	/**
	 * 根据订单id查找订单信息
	 * @param oid 订单id
	 * @return 订单信息
	 */
	private Order findByOid(Integer oid) {
		return orderMapper.findByOid(oid);
	}


	
}
