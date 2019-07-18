package com.xm.xmstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xm.xmstore.entity.Cart;
import com.xm.xmstore.entity.CartVO;
import com.xm.xmstore.mapper.CartMapper;
import com.xm.xmstore.service.CartService;
import com.xm.xmstore.service.ProductService;
import com.xm.xmstore.service.ex.AccessDeniedException;
import com.xm.xmstore.service.ex.CartNotFoundException;
import com.xm.xmstore.service.ex.DeleteException;
import com.xm.xmstore.service.ex.InsertException;
import com.xm.xmstore.service.ex.UpdateException;
/**
 * 购物车业务层
 * @author Administrator
 *
 */
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private ProductService productService;

	@Override
	public void addToCart(Integer uid, Integer pid, Integer num, String username) {
		// 基于参数uid和pid查询数据
		Cart result = findByUidAndPid(uid,pid);
		// 判断查询结果是否为null
		Date date = new Date();
		if(result==null) {
		// 是：需要新增购物车数据
		//	自行创建Cart对象
			Cart cart = new Cart();
		//	调用productService的getById()方法获取单价并封装到Cart对象
			Long price = productService.getById(pid).getPrice();
			cart.setPrice(price);
		//	将uid、pid、num参数封装到Cart对象
			cart.setUid(uid);
			cart.setPid(pid);
			cart.setNum(num);
		//	创建当前时间对象，将时间和username封装到Cart对象的日志属性
			
			cart.setCreatedUser(username);
			cart.setCreatedTime(date);
		//	执行插入
			insert(cart);
		}else {
		// 否：需要修改欲购物的商品的数量
		//	从查询结果中获取当前数量num和数据的cid
			Integer oldNum = result.getNum();
			Integer cid = result.getCid();
		//	将以上查询结果中的当前数量num和参数增量num相加，得到新的数量
			Integer newNum = oldNum+num;
		//	执行更新数量
			updateNum(cid, newNum, username, date);
		}
	}

	@Override
	public void addNum(Integer cid, Integer uid, String username)
			throws CartNotFoundException, AccessDeniedException, UpdateException {
		// 根据参数cid查询数据
		Cart result = getByCid(cid);
		// 从查询结果中获取尝试购买的原数量
		Integer oldNum = result.getNum();
		// 将数量更新为原数量+1的结果
		updateNum(cid, oldNum+1, username, new Date());


	}

	@Override
	public void reduceNum(Integer cid, Integer uid, String username)
			throws CartNotFoundException, AccessDeniedException, UpdateException {
		// 根据参数cid查询数据
		Cart result = getByCid(cid);
		// 从查询结果中获取尝试购买的原数量
		Integer oldNum = result.getNum();
		// 将数量更新为原数量+1的结果
		updateNum(cid, oldNum-1, username, new Date());


	}
	
	@Override
	public void deleteCart(Integer uid, Integer cid)
			throws CartNotFoundException, AccessDeniedException, DeleteException {
		// 根据参数aid查询数据
		Cart result = findByCid(cid);
		// 判断查询结果是否为null：CartNotFoundException
		if(result==null) {
			throw new CartNotFoundException("删除数据失败！尝试访问的数据不存在");
		}
		if(!result.getUid().equals(uid)) {
			// 判断查询结果中的uid与参数uid是否不同：AccessDeniedException
			throw new AccessDeniedException("删除数据失败！不允许访问他人的数据");
		}
		
		// 执行删除
		getdeleteByCid(cid);
	}
	
	@Override
	public List<CartVO> getByUid(Integer uid) {
		return findByUid(uid);
	}
	
	@Override
	public List<CartVO> getByCids(Integer[] cids, Integer uid) {
		// 检查参数cids
		if (cids == null) {
			return new ArrayList<>();
		}
		
		// 执行查询
		List<CartVO> result = findByCids(cids);
		
		// 遍历查询结果，去除归属错误的数据
		Iterator<CartVO> it = result.iterator();
		while (it.hasNext()) {
			CartVO item = it.next();
			if (item.getUid() != uid) {
				it.remove();
			}
		}
		
		// 返回
		return result;
	}
	
	@Override
	public void delete(Integer[] cids, Integer uid) {
		// 判断即将删除的数据是否存在，及数据归属是否正确
		// 可以调用自身的：List<CartVO> getByCids(Integer[] cids, Integer uid)，得到cid有效，且归属正确的购物车数据
		List<CartVO> carts = getByCids(cids, uid);
		
		// 判断以上查询结果的长度是否有效
		if (carts.size() == 0) {
			throw new CartNotFoundException(
				"删除购物车数据失败！尝试访问的数据不存在！");
		}

		// 基于以上得到的List<CartVO>得到允许执行删除的cid的数组
		Integer[] ids = new Integer[carts.size()];
		for (int i = 0; i < carts.size(); i++) {
			ids[i] = carts.get(i).getCid();
		}

		// 执行删除
		deleteByCids(ids);
	}

	/**
	 * 插入购物车数据
	 * @param cart 购物车数据
	 * @throws InsertException 插入数据异常
	 */
	private void insert(Cart cart) throws InsertException {
		Integer rows = cartMapper.insert(cart);
		if (rows != 1) {
			throw new InsertException(
					"将商品添加到购物车失败！插入数据时发生未知错误！");
		}
	}
	/**修改商品在购物车中的数量*/

	private void updateNum(Integer cid, Integer num,
			String username, Date modifiedTime) {
		Integer rows = cartMapper.updateNum(cid, num, username, modifiedTime);
		if (rows != 1) {
			throw new UpdateException(
					"调整商品数量失败！修改数据时发生未知错误！");
		}
	}
	
	private void getdeleteByCid(Integer cid) {
		Integer rows = cartMapper.deleteByCid(cid);
		if(rows!=1) {
			throw new DeleteException("删除购物数据失败！发生未知错误！");
		}
		
	}

	/** 用参数Cid查询提单数据
	 * @return */
	private Cart getByCid(Integer cid) {
		Cart cart = cartMapper.findByCid(cid);
		// 判断查询结果是否为null：CartNotFoundException
		if(cart==null) {
			throw new CartNotFoundException("增加失败！尝试访问的购物车数据不存在");
		}
		// 判断查询结果中的uid与参数uid是否不同：AccessDeniedException
		if (cart.getCid() != cid) {
			throw new AccessDeniedException(
					"增加失败！不允许操作他人的数据！");
		}
		return cart;

	}

	/**
	 * 根据用户id和商品id查询购物车数据
	 * @param uid 用户id
	 * @param pid 商品id
	 * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
	 */
	private Cart findByUidAndPid(Integer uid, Integer pid) {
		return cartMapper.findByUidAndPid(uid, pid);
	}
	
	private List<CartVO> findByUid(Integer uid){
		return cartMapper.findByUid(uid);
	}

	private Cart findByCid(Integer cid) {
		return cartMapper.findByCid(cid);
		
	}
	
	/**
	 * 根据购物车数据的若干个id值获取匹配的购物车数据的详情
	 * @param cids 若干个购物车数据的id
	 * @return 匹配的购物车数据的详情
	 */
	private List<CartVO> findByCids(Integer[] cids) {
		return cartMapper.findByCids(cids);
	}

	
	
	/**
	 * 根据若干个购物车数据id删除数据
	 * @param cids 若干个购物车数据id
	 * @throws DeleteException 删除数据异常
	 */
	private void deleteByCids(Integer[] cids) {
		Integer rows = cartMapper.deleteByCids(cids);
		if (rows < 1) {
			throw new DeleteException(
				"清除购物车数据失败！删除数据时发生未知错误！");
		}
	}

}
