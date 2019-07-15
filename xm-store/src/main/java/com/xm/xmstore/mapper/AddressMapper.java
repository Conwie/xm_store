package com.xm.xmstore.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xm.xmstore.entity.Address;

/**
 * 处理收货地址数据的持久层接口
 *
 */
public interface AddressMapper {
	
	/**添加收货地址*/
	Integer addnew(Address address);
	
	/**删除收货地址*/
	Integer deleteByAid(Integer aid);
	
	/**根据aid将收货地址设置为默认*/
	Integer updateDefaultByAid(
					@Param("aid")Integer aid,
					@Param("username")String username,
					@Param("modifiedTime")Date modifiedTime);
	
	/**根据uid将用户所有的收货地址设置为非默认*/
	Integer updateNotDefaultByUid(Integer uid);
	
	/**根据aid查找收货地址对应的用户uid*/
	Address findByAid(Integer aid);
	
	/**查询收货地址数量*/
	Integer countByUid(Integer uid);
	
	/**根据用户uid查找对应所有的收货地址*/
	List<Address> findByUid(Integer uid);
	
	/**查找最近一条修改过的收货地址*/
	Address findLastModifiedTime(Integer uid);
}
