package com.xm.xmstore.service;

import java.util.List;

import com.xm.xmstore.entity.Address;
import com.xm.xmstore.service.ex.AddressCountLimitException;
import com.xm.xmstore.service.ex.InsertException;

/**
 * 处理收货地址数据的业务逻辑接口
 * @author soft01
 *
 */
public interface AddressService {
	
	/**通过用户id查找收货地址列表*/
	List<Address> getByUid(Integer uid);
	
	/**添加收货地址*/
	void create(Integer uid,String username,Address address) throws AddressCountLimitException,InsertException;
		
}
