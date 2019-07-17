package com.xm.xmstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xm.xmstore.entity.Product;
import com.xm.xmstore.mapper.ProductMapper;
import com.xm.xmstore.service.ProductService;
import com.xm.xmstore.service.ex.ProductNotFoundException;

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
	
	/**
	 * 2. 获取商品id查询商品详情
	 * @param id 商品pid
	 * @return 匹配的商品详情，如果没有匹配的数据，则返回null
	 */
	@Override
	public Product getById(Integer id) {
				// 调用私有方法执行查询
				Product result = findById(id);
				// 判断查询结果是否为null：ProductNotFoundException
				if (result == null) {
					throw new ProductNotFoundException(
						"查询商品详情失败！尝试访问的数据不存在！");
				}

				// 将查询结果中的部分属性设置为null，例如隐藏属性和日志
				result.setPriority(null);
				result.setCreatedUser(null);
				result.setCreatedTime(null);
				result.setModifiedUser(null);
				result.setModifiedTime(null);
				// 返回结果
				return result;
	}

	/**根据priority来查询前5个热销商品--1*/
	private List<Product> findByPriority(){
		return productMapper.findByPriority();
	}
<<<<<<< HEAD
	
	/**
	 * 根据商品id查询商品详情
	 * @param id 商品id
	 * @return 匹配的商品详情，如果没有匹配的数据，则返回null
	 */
	private Product findById(Integer pid) {
		return productMapper.findById(pid);
	}
=======
>>>>>>> conwie

	@Override
	public Product getById(Integer id) {
		return findById(id);
	}
	
	private Product findById(Integer id) throws ProductNotFoundException{
		Product p = productMapper.findById(id);
		if(p == null) {
			throw new ProductNotFoundException("该商品不存在！");
		}
		return p;
	}
	
	
}




















