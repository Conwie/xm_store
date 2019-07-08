package com.xm.xmstore.entity;


public class Address extends BaseEntity{

	private static final long serialVersionUID = 5228698703153053001L;

	private Integer aid;
	private Integer uid;
	private String name;
	private String provinceCode;
	private String provinceName;
	private String cityCode;
	private String cityName;
	private String areaCode;
	private String areaName;
	private String zip; 
	private String address;
	private String phone;
	private String tel; 
	private String tag; 
	private Integer isDefault;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getAid() {
		return aid;
	}
	public Integer getUid() {
		return uid;
	}
	public String getName() {
		return name;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public String getZip() {
		return zip;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public String getTel() {
		return tel;
	}
	public String getTag() {
		return tag;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aid == null) ? 0 : aid.hashCode());
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
		Address other = (Address) obj;
		if (aid == null) {
			if (other.aid != null)
				return false;
		} else if (!aid.equals(other.aid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Address [aid=" + aid + ", uid=" + uid + ", name=" + name + ", provinceCode=" + provinceCode
				+ ", provinceName=" + provinceName + ", cityCode=" + cityCode + ", cityName=" + cityName + ", areaCode="
				+ areaCode + ", areaName=" + areaName + ", zip=" + zip + ", address=" + address + ", phone=" + phone
				+ ", tel=" + tel + ", tag=" + tag + ", isDefault=" + isDefault + "]";
	} 
	
	
	
}
