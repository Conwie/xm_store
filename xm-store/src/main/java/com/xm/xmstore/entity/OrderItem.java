package com.xm.xmstore.entity;

/**
 *订单商品详情实体类 
 */
public class OrderItem extends BaseEntity{
	private static final long serialVersionUID = 4220002725658268401L;
	
	private Integer id;
	private Integer oid;
	private Integer pid;
	private String title;
	private String image;
	private Long price;
	private Integer num;
	
	public Integer getId() {
		return id;
	}
	public Integer getOid() {
		return oid;
	}
	public Integer getPid() {
		return pid;
	}
	public String getTitle() {
		return title;
	}
	public String getImage() {
		return image;
	}
	public Long getPrice() {
		return price;
	}
	public Integer getNum() {
		return num;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", oid=" + oid + ", pid=" + pid + ", title=" + title + ", image=" + image
				+ ", price=" + price + ", num=" + num + "]";
	}
	
}
