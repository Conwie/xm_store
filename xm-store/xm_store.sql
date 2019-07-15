#----小米商城数据库详情----#
#-------创建数据库：xm_store-------#
CREATE DATABASE xm_store CHARACTER SET UTF8;

USE xm_store;

#-------1. 创建用户表：xm_user------#
CREATE TABLE xm_user (
		`uid` INT AUTO_INCREMENT COMMENT '用户id',
 		`username` VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名',
	  	`password` CHAR(36) NOT NULL COMMENT '密码',
	  	`salt` CHAR(36)  COMMENT '盐值',
	  	`gender` INT  COMMENT '性别，0-女，1-男',
	  	`phone` VARCHAR(20) UNIQUE NOT NULL COMMENT '手机号码',
	  	`avatar` VARCHAR(100) COMMENT '头像',
	  	`is_delete` INT NOT NULL COMMENT '是否删除，0-未删除，1-已删除',
	  	`created_user` VARCHAR(50)  COMMENT '创建人',
	  	`created_time` DATETIME  COMMENT '创建时间',  	
	  	`modified_user` VARCHAR(50)  COMMENT '最后修改人',
	  	`modified_time` DATETIME  COMMENT '最后修改时间',
  		PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;














