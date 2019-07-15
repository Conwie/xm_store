package com.xm.xmstore.mapper;

import java.util.List;

import com.xm.xmstore.entity.District;

public interface DistrictMapper {
	
	List<District> findByParent(String parent);
	
	District findByCode(String code);
	
}
