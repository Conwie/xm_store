package com.xm.xmstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xm.xmstore.entity.Address;
import com.xm.xmstore.service.AddressService;
import com.xm.xmstore.service.ex.AccessDeniedException;
import com.xm.xmstore.service.ex.AddressCountLimitException;
import com.xm.xmstore.service.ex.AddressNotFoundException;
import com.xm.xmstore.service.ex.DeleteException;
import com.xm.xmstore.service.ex.UpdateException;
import com.xm.xmstore.util.JsonResult;
/**
 * 处理收货地址相关请求的控制器 
 *
 */
@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
	
	@Autowired
	private AddressService service;
	
	@RequestMapping("create")
	public JsonResult<Void> create(Address address,HttpSession session){
			Integer uid = getUidFromSession(session);
			String username =getUsernameFromSession(session);
			service.create(uid, username, address);
			return new JsonResult<>(SUCCESS);
	}
	
	@RequestMapping("/{aid}/delete")
	public JsonResult<Void> delete(@PathVariable("aid")Integer aid,
																		HttpSession session) {
		Integer uid = getUidFromSession(session);
		String username =getUsernameFromSession(session);
		
		service.delete(aid, uid, username);
		return new JsonResult<>(SUCCESS);
	}
	
	@GetMapping("get_by_uid")
	public JsonResult<List<Address>> getByUid(Integer uid){
		List<Address> data =service.getByUid(uid);
		return new JsonResult<List<Address>>(SUCCESS,data);
	}
	
	@GetMapping("/")
	public JsonResult<List<Address>> getByUid(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<Address> list = service.getByUid(uid);
		return new JsonResult<List<Address>>(SUCCESS, list);
	}
	
	@RequestMapping("/{aid}/set_default")
	public JsonResult<Void> setDefault(@PathVariable("aid")Integer aid,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		service.setDefault(aid, uid, username);
		return new JsonResult<Void>(SUCCESS);
	}
	
}
