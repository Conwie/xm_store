package com.xm.xmstore.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xm.xmstore.entity.Address;
import com.xm.xmstore.entity.District;
import com.xm.xmstore.mapper.AddressMapper;
import com.xm.xmstore.service.AddressService;
import com.xm.xmstore.service.DistrictService;
import com.xm.xmstore.service.ex.AddressCountLimitException;
import com.xm.xmstore.service.ex.InsertException;
/**
 *  处理收货地址数据的业务逻辑接口实现类
 *
 */
@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	DistrictService disService;
	
	public void create(Integer uid, String username, Address address)
			throws AddressCountLimitException, InsertException {
		// 基于参数uid查询该用户的收货地址数量
		Integer rows = countByUid(uid);
		// 判断数量是否达到上限值
		if(rows>=10) {
			throw new AddressCountLimitException("您的收货地址超过上限，上限值为"+rows);
		}
		// 是：AddressCountLimitException
		address.setUid(uid);
		// 补全参数address中的数据：uid
		// 补全参数address中的数据：省市区的名称
		String provinceCode = address.getProvinceCode();
		String cityCode = address.getCityCode();
		String areaCode = address.getAreaCode();
		String provinceName = getDistrictNameByCode(provinceCode);
		String cityName = getDistrictNameByCode(cityCode);
		String areaName = getDistrictNameByCode(areaCode);
		
		address.setProvinceName(provinceName);
		address.setCityName(cityName);
		address.setAreaName(areaName);
		// 补全参数address中的数据：isDefault，根据收货地址数量确定该属性的值
		address.setIsDefault(0);
		// 创建当前时间对象
		address.setCreatedTime(new Date());
		address.setCreatedUser(username);
		// 补全参数address中的数据：4项日志
		address.setModifiedUser(username);
		address.setModifiedTime(new Date());
		// 执行增加
		addressMapper.addnew(address);
	}
	
	/**
	 * 根据省/市/区的代号获取名称
	 * @param code 省/市/区的代号
	 * @return 省/市/区的代号匹配的名称，如果没有匹配的数据，则返回空字符串
	 */
	private String getDistrictNameByCode(String code) {
		District result = disService.getByCode(code);
		return result==null?"":result.getName();
	}
	
	/**
	 * 根据用户id返回收货地址数据
	 */
	public List<Address> getByUid(Integer uid) {
		return findByUid(uid);
	}
	
	
	/**
	 * 根据用户id返回收货地址数据
	 */
	private List<Address> findByUid(Integer uid){
		return addressMapper.findByUid(uid);
	}
	
	//查询收货地址数量
	private Integer countByUid(Integer uid ) {
		if(uid==null||uid<1) {
			throw new IllegalArgumentException();
		}
		return addressMapper.countByUid(uid);
	}
	
}
