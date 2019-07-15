package com.xm.xmstore.service;

import java.util.List;

import com.xm.xmstore.entity.District;

public interface DistrictService {
	
	List<District> getByParent(String parent);
	
	District getByCode(String code);
}
