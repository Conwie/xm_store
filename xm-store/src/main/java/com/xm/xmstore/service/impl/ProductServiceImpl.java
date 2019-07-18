package com.xm.xmstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xm.xmstore.entity.Product;
import com.xm.xmstore.mapper.ProductMapper;
import com.xm.xmstore.service.ProductService;
import com.xm.xmstore.service.ex.ProductNotFoundException;
import com.xm.xmstore.service.ex.ProductOutOfStockException;
import com.xm.xmstore.service.ex.UpdateException;



@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	/**1. 根据priority来获取前5个热销商品*/
	public List<Product> getByPriority() {
		List<Product> list = findByPriority();
		// 将一些属性置空
		for (Product product : list) {
			product.setStatus(null);
			product.setPriority(null);
			product.setCreatedUser(null);
			product.setCreatedTime(null);
			product.setModifiedUser(null);
			product.setModifiedUser(null);
		}
		
		return list;
	}

	/**根据priority来查询前5个热销商品--1*/
	private List<Product> findByPriority(){
		return productMapper.findByPriority();
	}

	@Override
	public Product getById(Integer id) {
		return findById(id);
	}
	
	@Override
	public void reduceNum(Integer pid, Integer amount) {
		// 通过参数pid查询商品数据
		Product result = findById(pid);
		// 判断查询结果是否为null：ProductNotFoundException
		if (result == null) {
			throw new ProductNotFoundException(
				"更新商品库存失败！尝试访问的商品数量不存在！");
		}
		
		// 暂不考虑商品下架的问题

		// 判断查询结果中的num(当前库存)是否小于参数amount(将要购买或减少的库存量)：ProductOutOfStockException
		if (result.getNum() < amount) {
			throw new ProductOutOfStockException(
				"更新商品库存失败！当前商品库存已经不足！");
		}

		// 执行减少库存
		updateNum(pid, result.getNum() - amount);
	}
	
	@Override
	public void addNum(Integer pid, Integer amount) {
		// 通过参数pid查询商品数据
		Product result = findById(pid);
		// 判断查询结果是否为null：ProductNotFoundException
		if (result == null) {
			throw new ProductNotFoundException(
				"更新商品库存失败！尝试访问的商品数量不存在！");
		}
		
		// 暂不考虑商品下架的问题
		
		// 执行增加库存
		updateNum(pid, result.getNum() + amount);
	}
	
	private Product findById(Integer id) throws ProductNotFoundException{
		Product p = productMapper.findById(id);
		if(p == null) {
			throw new ProductNotFoundException("该商品不存在！");
		}
		return p;
	}
	
	/**
	 * 更新商品的库存
	 * @param pid 商品的id
	 * @param num 新的库存量
	 * @throws UpdateException 更新商品数量失败
	 */
	private void updateNum(Integer pid, Integer num) {
		Integer rows = productMapper.updateNum(pid, num);
		if (rows != 1) {
			throw new UpdateException(
				"更新商品数量失败！更新数据时出现未知错误！");
		}
	}
	
}




















