package com.xm.xmstore.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xm.xmstore.entity.Address;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTests {
	
	@Autowired
	private AddressMapper mapper;
	
	@Test
	public void findByUid() {
		List<Address> list = mapper.findByUid(1);
		try {
			for (Address address : list) {
				System.err.println(address);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findByAid() {
		Address addr = mapper.findByAid(1);
		System.err.println(addr);
	}
	
	@Test
	public void updateNotDefault() {
		Integer rows = mapper.updateNotDefaultByUid(1);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void updateDefault() {
		Integer rows = mapper.updateDefaultByAid(1,"root",new Date());
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void countByUid() {
		Integer rows = mapper.countByUid(1);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void delete() {
		Integer rows = mapper.deleteByAid(6);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void last() {
		Address addr = mapper.findLastModifiedTime(1);
		System.err.println(addr);
	}
}
