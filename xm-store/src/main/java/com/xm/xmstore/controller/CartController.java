package com.xm.xmstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xm.xmstore.entity.CartVO;
import com.xm.xmstore.service.CartService;
import com.xm.xmstore.util.JsonResult;

/**
 * 购物车控制层
 * @author Administrator
 *
 */
@RestController
@RequestMapping("carts")
public class CartController extends BaseController{
	@Autowired
	CartService cartService;
	
	/**插入订单数据到购物车*/
	@RequestMapping("add_to_cart")
	public JsonResult<Void> addToCart(Integer pid, Integer num, HttpSession session) {
		// 从session中获取uid和username
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		// 调用业务层对象的方法执行任务
		cartService.addToCart(uid, pid, num, username);
		// 响应成功
		return new JsonResult<> (SUCCESS);
	}
	
	@GetMapping("/")
	public JsonResult<List<CartVO>> getByUid(HttpSession session){
		//session中获取uid
		Integer uid = getUidFromSession(session);
		//调用业务层对象的方法执行任务
		List<CartVO> data = cartService.getByUid(uid);
		//响应成功
		return new JsonResult<List<CartVO>>(SUCCESS,data);
		
	}
	
	/**增加订单数量*/
	@RequestMapping("{cid}/add_num")
	public JsonResult<Void> updateNum(
			@PathVariable("cid") Integer cid,
			HttpSession session
			){
		// 从session中获取uid和username
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		// 调用业务层对象的方法执行任务
		cartService.addNum(cid, uid, username);
		// 响应成功
		return new JsonResult<>(SUCCESS);
	}
	
	/**减少订单数量*/
	@RequestMapping("{cid}/reduce_num")
	public JsonResult<Void> reduceNum(
			@PathVariable("cid") Integer cid,
			HttpSession session
			){
		// 从session中获取uid和username
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		// 调用业务层对象的方法执行任务
		cartService.reduceNum(cid, uid, username);
		// 响应成功
		return new JsonResult<>(SUCCESS);
		
		
		
	}
	
	@RequestMapping("{cid}/deleteCart")
	public JsonResult<Void> deleteCart(
			@PathVariable("cid") Integer cid,HttpSession session){
		//session中获取uid
		Integer uid = getUidFromSession(session);
		//调用业务层对象的方法执行任务
		cartService.deleteCart(uid, cid);
		//响应成功
		return new JsonResult<>(SUCCESS);
		
	}
	
}