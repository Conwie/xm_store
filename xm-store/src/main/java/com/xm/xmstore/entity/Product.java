package com.xm.xmstore.entity;

/**
 * 商品实体类
 * @author Administrator
 *
 */
public class Product extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Integer pid; // 商品id
	private Integer categoryId; // 分类id
	private String itemType; // 商品系列
	private String title; // 商品标题
	private String sellPoint; //商品卖点
	private Long price; //商品单价
	private Integer num; //库存数量
	private String image; //图片路径
	private Integer status; //商品状态  1：上架   2：下架   3：删除
	private Integer priority; //显示优先级
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
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
		Product other = (Product) obj;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", categoryId=" + categoryId + ", itemType=" + itemType + ", title=" + title
				+ ", sellPoint=" + sellPoint + ", price=" + price + ", num=" + num + ", image=" + image + ", status="
				+ status + ", priority=" + priority + "]";
	}
	
	
	
	
	
	
	
}
