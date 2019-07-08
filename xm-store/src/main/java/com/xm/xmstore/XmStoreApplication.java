package com.xm.xmstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xm.xmstore.mapper")
public class XmStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmStoreApplication.class, args);
	}

}
