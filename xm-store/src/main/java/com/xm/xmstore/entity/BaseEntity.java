package com.xm.xmstore.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 	实体类的基类
 * 	@author jamie
 * 
 */
public abstract class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -5882064199939706583L;

	private String createdUser; //创建人
	private Date createdTime; //创建时间
	private String modifiedUser; //最后修改人
	private Date modifiedTime; //最后修改时间

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "BaseEntity [createdUser=" + createdUser + ", createdTime=" + createdTime + ", modifiedUser="
				+ modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}
	
}
