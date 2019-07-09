package com.xm.xmstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xm.xmstore.controller.ex.FileEmptyException;
import com.xm.xmstore.controller.ex.FileSizeException;
import com.xm.xmstore.controller.ex.FileStateException;
import com.xm.xmstore.controller.ex.FileTypeException;
import com.xm.xmstore.controller.ex.FileUploadIOException;
import com.xm.xmstore.service.ex.AccessDeniedException;
import com.xm.xmstore.service.ex.AddressNotFoundException;
import com.xm.xmstore.service.ex.CodeErrorException;
import com.xm.xmstore.service.ex.InsertException;
import com.xm.xmstore.service.ex.PasswordNotMatchException;
import com.xm.xmstore.service.ex.PhoneDuplicateException;
import com.xm.xmstore.service.ex.ServiceException;
import com.xm.xmstore.service.ex.UpdateException;
import com.xm.xmstore.service.ex.UserNotFoundException;
import com.xm.xmstore.service.ex.UsernameDuplicateException;
import com.xm.xmstore.util.JsonResult;

/**
 * 	用户控制器的基类
 * @author jamie
 *
 */
public abstract class BaseController {
	
	public static final Integer SUCCESS = 2000;
	
	/**1. 异常处理操作*/
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult<Void> handleException(Exception e){
		JsonResult<Void> jr = new JsonResult<Void>(e);
		jr.setMessage(e.getMessage());
		if (e instanceof UsernameDuplicateException) {
			//用户名已注册异常
			jr.setState(4000);
		}else if(e instanceof UserNotFoundException) {
			//用户名不存在异常
			jr.setState(4001);
		}else if(e instanceof AddressNotFoundException) {
			//地址不存在异常
			jr.setState(4002);
		}else if(e instanceof AccessDeniedException) {
			//拒绝访问异常
			jr.setState(4003);
		} else if(e instanceof PhoneDuplicateException) {
			//手机号已注册异常
			jr.setState(5000);
		} else if(e instanceof InsertException) {
			//插入异常
			jr.setState(5001);
		}else if(e instanceof UpdateException) {
			//更新异常
			jr.setState(5002);
		}else if(e instanceof PasswordNotMatchException ) {
			//密码错误异常
			jr.setState(5003);
		}else if(e instanceof CodeErrorException){
			//验证码错误异常
			jr.setState(5004);
		}else if (e instanceof FileEmptyException) {
			//文件为空异常
			jr.setState(6000);
		} else if (e instanceof FileSizeException) {
			//文件大小超出限制异常
			jr.setState(6001);
		} else if (e instanceof FileTypeException) {
			//文件类型不支持异常
			jr.setState(6002);
		} else if (e instanceof FileStateException) {
			//文件错误状态异常
			jr.setState(6003);
		} else if (e instanceof FileUploadIOException) {
			//文件上传异常
			jr.setState(6004);
		}
		return jr;
	}
	
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf((session.getAttribute("uid").toString()));
	}
	
	protected final String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}
	
}












