package com.xm.xmstore.util;

/**
 * 	json格式数据的类型
 * @author Administrator
 *
 * @param <T>
 */
public class JsonResult<T> {	
	
	private Integer state = 2000;
	private String message;
	private T data;
	
	public JsonResult(Exception e) {
		super();
		this.message = e.getMessage();
	}

	public JsonResult(Integer state) {
		super();
		this.state = state;
	}
	
	
	public JsonResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getdata() {
		return data;
	}
	public void setdata(T data) {
		this.data = data;
	}
}
