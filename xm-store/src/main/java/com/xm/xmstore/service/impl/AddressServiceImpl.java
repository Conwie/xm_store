package com.xm.xmstore.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xm.xmstore.entity.Address;
import com.xm.xmstore.entity.District;
import com.xm.xmstore.mapper.AddressMapper;
import com.xm.xmstore.service.AddressService;
import com.xm.xmstore.service.DistrictService;
import com.xm.xmstore.service.ex.AccessDeniedException;
import com.xm.xmstore.service.ex.AddressCountLimitException;
import com.xm.xmstore.service.ex.AddressNotFoundException;
import com.xm.xmstore.service.ex.DeleteException;
import com.xm.xmstore.service.ex.InsertException;
import com.xm.xmstore.service.ex.UpdateException;
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
		if(rows>=MAX_COUNT) {
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
	
	/**删除收货地址*/
	@Transactional
	public void delete(Integer aid,Integer uid,String username) throws AddressNotFoundException,
																AccessDeniedException,DeleteException,AddressCountLimitException{
		//根据aid返回查询结果判断是否为null：AddressNotFoundException
		Address result = findByAid(aid);
		if(result == null) {
			throw new AddressNotFoundException("删除收货地址失败！收货地址不存在！");
		}
		//根据查询结果的uid和参数uid进行比对：AccessDeniedException
		if(result.getUid() != uid) {
			throw new AccessDeniedException("删除收货地址失败！拒绝访问他人的数据！");
		}
		//执行删除业务
		deleteByAid(aid);
		//判断查询结果的isDefault是否为0：
		if(result.getIsDefault() == null || result.getIsDefault() == 0) {
			//是：return
			return;
		}
		
		//统计剩余的收货地址是否为0：
		Integer count = countByUid(uid);
		if(count == 0) {
			//是：return
			return;
		}
		
		//查找最近一条修改过的收货地址，设置为默认
		Address address = findLastModifiedTime(uid);
		setDefault(address.getAid(), uid, username);
		
	}
	
	/**
	 * 根据用户id返回收货地址数据
	 */
	public List<Address> getByUid(Integer uid) {
		return findByUid(uid);
	}
	
	
	@Override
	@Transactional
	public void setDefault(Integer aid, Integer uid, String username)
			throws AddressNotFoundException, AccessDeniedException, UpdateException {
		//根据收货地址aid查找地址是否存在：AddressNotFoundException
		Address result = findByAid(aid);
		if(result == null) {
			throw new AddressNotFoundException("修改默认地址失败！收货地址数据不存在！");
		}
		//根据返回的result的uid与参数uid进行匹配：AccessDeniedException
		if(result.getUid() != uid) {
			throw new AccessDeniedException("修改默认地址失败！不允许访问他人的数据！");
		}
		//将所有的收货地址设置为非默认
		updateNotDefaultByUid(uid);
		//把指定收货地址设置为默认
		Date modifiedTime = new Date();
		updateDefaultByAid(aid, username, modifiedTime);
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
	
	/**根据aid查找收货地址对应的用户uid*/
	private Address findByAid(Integer aid) {
		return addressMapper.findByAid(aid);
	}
	
	/**根据aid将收货地址设置为默认*/
	private void updateDefaultByAid(Integer aid,String username,
																				Date modifiedTime) throws UpdateException{
		Integer rows = addressMapper.updateDefaultByAid(aid, username, modifiedTime);
		if(rows != 1) {
			throw new UpdateException("修改默认地址失败！更新数据时出现未知异常！");
		}
	}
	
	
	/**根据uid将用户所有的收货地址设置为非默认*/
	private void updateNotDefaultByUid(Integer uid)throws UpdateException{
		Integer rows = addressMapper.updateNotDefaultByUid(uid);
		if(rows < 1) {
			throw new UpdateException("修改默认地址失败！更新数据时出现未知错误！");
		}
	}

	
	/**删除收货地址*/
	private void deleteByAid(Integer aid) {
		Integer rows = addressMapper.deleteByAid(aid);
		if(rows != 1) {
			throw new DeleteException("删除收货地址失败！删除数据时出现未知错误！");
		}
	}

	/**查找最近一条修改过的收货地址*/
	private Address findLastModifiedTime(Integer uid) {
		return addressMapper.findLastModifiedTime(uid);
	}
}
