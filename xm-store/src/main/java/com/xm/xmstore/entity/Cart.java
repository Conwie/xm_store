package com.xm.xmstore.entity;

import java.util.Date;

public class Cart extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4713161152639673044L;
	
	private Integer cid;
	private Integer uid; 
	private Integer pid; 
	private Integer num;
	private Long price;
	private String created_user;
	private Date created_time;
	private String modified_use;
	private Date modified_time;
	public Integer getCid() {
		return cid;
	}
	public Integer getUid() {
		return uid;
	}
	public Integer getPid() {
		return pid;
	}
	public Integer getNum() {
		return num;
	}
	public Long getPrice() {
		return price;
	}
	public String getCreated_user() {
		return created_user;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public String getModified_use() {
		return modified_use;
	}
	public Date getModified_time() {
		return modified_time;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public void setModified_use(String modified_use) {
		this.modified_use = modified_use;
	}
	public void setModified_time(Date modified_time) {
		this.modified_time = modified_time;
	}
	@Override
	public String toString() {
		return "Cart [cid=" + cid + ", uid=" + uid + ", pid=" + pid + ", num=" + num + ", price=" + price
				+ ", created_user=" + created_user + ", created_time=" + created_time + ", modified_use=" + modified_use
				+ ", modified_time=" + modified_time + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		return true;
	}
	
	
}
