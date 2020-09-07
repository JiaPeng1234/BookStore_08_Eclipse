package peng.bean;

import java.util.Date;

public class Order {

	private String orderId;    // 订单号
	private Date createDate;   // 订单创建日期
	private int status;		   // 订单状态
	private Integer userId;    // 对应用户信息
	private double totalMoney; // 订单总金额
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Order(String orderId, Date createDate, int status, Integer userId, double totalMoney) {
		super();
		this.orderId = orderId;
		this.createDate = createDate;
		this.status = status;
		this.userId = userId;
		this.totalMoney = totalMoney;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", createDate=" + createDate + ", status=" + status + ", userId=" + userId
				+ ", totalMoney=" + totalMoney + "]";
	}
	
	
}
