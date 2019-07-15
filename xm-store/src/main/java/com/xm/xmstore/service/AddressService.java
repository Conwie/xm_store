package com.xm.xmstore.service;

import java.util.List;

import com.xm.xmstore.entity.Address;
import com.xm.xmstore.service.ex.AccessDeniedException;
import com.xm.xmstore.service.ex.AddressCountLimitException;
import com.xm.xmstore.service.ex.AddressNotFoundException;
import com.xm.xmstore.service.ex.DeleteException;
import com.xm.xmstore.service.ex.InsertException;
import com.xm.xmstore.service.ex.UpdateException;

/**
 * 处理收货地址数据的业务逻辑接口
 * @author soft01
 *
 */
public interface AddressService {
	/**
	 * 每个用户可以添加的最大收货地址数量
	 */
	int MAX_COUNT = 6;
	
	/**通过用户id查找收货地址列表*/
	List<Address> getByUid(Integer uid);
	
	/**添加收货地址*/
	void create(Integer uid,String username,Address address) throws AddressCountLimitException,InsertException;
	
	/**将收货地址设置为默认*/
	void setDefault(Integer aid,Integer uid,String username) throws AddressNotFoundException,AccessDeniedException,UpdateException;

	/**删除收货地址*/
	void delete(Integer aid,Integer uid,String username) throws AddressNotFoundException,AccessDeniedException,DeleteException,AddressCountLimitException;
}
