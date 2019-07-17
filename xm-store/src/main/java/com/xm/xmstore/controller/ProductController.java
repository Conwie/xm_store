package com.xm.xmstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xm.xmstore.entity.Product;
import com.xm.xmstore.service.ProductService;
import com.xm.xmstore.util.JsonResult;

/**
 * 
 * @author Administrator
 *
 */

@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
	
	@Autowired
	private ProductService productService;
	
	/** 1. 处理获取前5个商品的请求 */
	@GetMapping("get_product")
	public JsonResult<List<Product>> handleGetProduct(){
		List<Product> data = productService.getByPriority();
		return new JsonResult<List<Product>>(SUCCESS, data);
	}
	
	@GetMapping("{id}/details")
	public JsonResult<Product> getById(
		@PathVariable("id") Integer id) {
		// 调用业务层对象执行查询
		Product data = productService.getById(id);
		// 响应成功与数据
		return new JsonResult<>(SUCCESS, data);
	}
	
<<<<<<< HEAD
=======
	
	
>>>>>>> conwie
}















