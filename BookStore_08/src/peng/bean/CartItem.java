package peng.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 单个购物车项目中的书
	private Book book;
	
	// 该书有多少本在购物车中
	private int count;
	
	// 所有该书在购物车中的价钱
	// private double totalPrice;
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getTotalPrice() {
		// 将获取到的价格采用BigDecimal包装，以防止计算不准，先以String类来包装
		BigDecimal price = new BigDecimal(getBook().getPrice()+"");
		BigDecimal count = new BigDecimal(getCount()+"");
		BigDecimal multiply = price.multiply(count);
		// 采用BigDecimal将数据转换为double类
		return multiply.doubleValue();
	}

	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}

	public CartItem() {
		super();
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
	
	
}
