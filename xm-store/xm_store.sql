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
		`nickname` VARCHAR(50) COMMENT '昵称',
		`email` VARCHAR(50) COMMENT '电子邮箱',
	  	`is_delete` INT NOT NULL COMMENT '是否删除，0-未删除，1-已删除',
	  	`created_user` VARCHAR(50)  COMMENT '创建人',
	  	`created_time` DATETIME  COMMENT '创建时间',  	
	  	`modified_user` VARCHAR(50)  COMMENT '最后修改人',
	  	`modified_time` DATETIME  COMMENT '最后修改时间',
  		PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#-------2. 创建收货地址表：xm_address------#
CREATE TABLE xm_address(
	`aid` INT AUTO_INCREMENT COMMENT '收货地址id',
	`uid` INT COMMENT '用户id',
	`name` VARCHAR(50) COMMENT '收货人姓名',
	`province_code` CHAR(6) COMMENT '省代号',
	`province_name` VARCHAR(50) COMMENT '省名称',
	`city_code` CHAR(6) COMMENT '市代号',
	`city_name` VARCHAR(50) COMMENT '市名称',
	`area_code` CHAR(6) COMMENT '区代号',
	`area_name` VARCHAR(50) COMMENT '区名称',
	`zip` CHAR(6) COMMENT '邮编',
	`address` VARCHAR(150) COMMENT '详细地址',
	`phone` VARCHAR(20) COMMENT '手机',
	`tel` VARCHAR(20) COMMENT '固话',
	`tag` VARCHAR(30) COMMENT '地址类型',
	`is_default` INT COMMENT '是否默认,0-非默认,1-默认',
	`created_user` VARCHAR(50) COMMENT '创建人',
	`created_time` DATETIME COMMENT '创建时间',
	`modified_user` VARCHAR(50) COMMENT '最后修改人',
	`modified_time` DATETIME COMMENT '最后修改时间',
	PRIMARY KEY(`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#-------3. 创建购物车表：xm_cart------#
CREATE TABLE xm_cart(
		cid INT AUTO_INCREMENT COMMENT '购物车数据id',
		uid INT NOT NULL COMMENT '用户id',
		pid INT NOT NULL COMMENT '商品id',
		num INT NOT NULL COMMENT '数量',
		price BIGINT NOT NULL COMMENT '商品单价',
		created_user VARCHAR(50) COMMENT '创建人',
		created_time DATETIME COMMENT '创建时间',
		modified_user VARCHAR(50) COMMENT '最后修改人',
		modified_time DATETIME COMMENT '最后修改时间',
		PRIMARY KEY(cid)
	)DEFAULT CHARSET=UTF8;
	
#-------5. 创建订单表：xm_order------#
CREATE TABLE xm_order(
	oid INT AUTO_INCREMENT COMMENT 'id',
	uid INT COMMENT '用户id',
	recv_name VARCHAR(50) COMMENT '收货人姓名',
	recv_phone VARCHAR(20) COMMENT '收货人电话',
	recv_province VARCHAR(50) COMMENT '收货地址所在省',
	recv_city VARCHAR(50) COMMENT '收货地址所在市',
	recv_area VARCHAR(50) COMMENT '收货地址所在区',
	recv_address VARCHAR(100) COMMENT '详细收货地址',
	total_price BIGINT COMMENT '总价',
	status INT COMMENT '状态：0-未支付，1-已支付，2-已取消',
	order_time DATETIME COMMENT '下单时间',
	pay_time DATETIME COMMENT '支付时间',
	created_user VARCHAR(50) COMMENT '创建人',
	created_time DATETIME COMMENT '创建时间',
	modified_user VARCHAR(50) COMMENT '最后修改人',
	modified_time DATETIME COMMENT '最后修改时间',
	PRIMARY KEY(oid)
)DEFAULT CHARSET=UTF8;

#-------6. 创建订单详情表：xm_order_item------#
CREATE TABLE xm_order_item(
	id INT AUTO_INCREMENT COMMENT 'id',
	oid INT COMMENT '归属的订单id',
	pid INT COMMENT '商品id',
	title VARCHAR(100) COMMENT '商品标题',
	image VARCHAR(500) COMMENT '商品图片',
	price BIGINT COMMENT '商品单价',
	num INT COMMENT '购买数量',
	created_user VARCHAR(50) COMMENT '创建人',
	created_time DATETIME COMMENT '创建时间',
	modified_user VARCHAR(50) COMMENT '最后修改人',
	modified_time DATETIME COMMENT '最后修改时间',
	PRIMARY KEY(id)
)DEFAULT CHARSET=UTF8;














