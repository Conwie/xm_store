package com.xm.xmstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xm.xmstore.entity.District;
import com.xm.xmstore.service.DistrictService;
import com.xm.xmstore.util.JsonResult;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {
	
	@Autowired
	DistrictService service; 
	
	@RequestMapping("/")
	public JsonResult<List<District>>  getByparent(String parent){
		List<District> data =  service.getByParent(parent);
		return new JsonResult<>(SUCCESS,data);
	}
	
	
}
