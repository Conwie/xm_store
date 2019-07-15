package com.xm.xmstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xm.xmstore.entity.District;
import com.xm.xmstore.mapper.DistrictMapper;
import com.xm.xmstore.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {
	
	@Autowired
	DistrictMapper dismapper;
	
	public List<District> getByParent(String parent){
		return findByParent(parent);
	}
	
	private List<District> findByParent(String parent){
		return dismapper.findByParent(parent);
	}
	
	public District getByCode(String code) {
		return findByCode(code);
	}
	
	public District findByCode(String code) {
		return dismapper.findByCode(code);
	}

}
