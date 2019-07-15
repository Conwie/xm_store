package com.xm.xmstore.service;

import com.xm.xmstore.service.ex.AccessDeniedException;
import com.xm.xmstore.service.ex.CartNotFoundException;
import com.xm.xmstore.service.ex.UpdateException;

public interface CartService {
	/**商品数量增加*/
	void addNum(Integer cid, Integer uid, String username) throws CartNotFoundException, AccessDeniedException, UpdateException;
	
	/**商品数量增加*/
	void reduceNum(Integer cid, Integer uid, String username) throws CartNotFoundException, AccessDeniedException, UpdateException;
	
	
}
