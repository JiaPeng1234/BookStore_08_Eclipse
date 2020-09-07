package peng.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, CartItem> items = new LinkedHashMap<>();

//	private int totalCount;
//	
//	private double totalMoney;
	
	public List<CartItem> getAllItems() {
		Collection<CartItem> values = items.values();
		return new ArrayList<CartItem>(values);
	}

	// 购物车中总商品数
	public int getTotalCount() {
		List<CartItem> list = getAllItems();
		int count = 0;
		for(CartItem cartitem : list) {
			count += cartitem.getCount();
		}
		return count;
	}

	//购物车总金额
	public double getTotalMoney() {
		List<CartItem> list = getAllItems();
		BigDecimal money = new BigDecimal(0.0+"");
		for(CartItem cartitem : list) {
			BigDecimal totoalPrice = new BigDecimal(cartitem.getTotalPrice()+"");
			money = money.add(totoalPrice);
		}
		return money.doubleValue();
	}
	
	public void addBook2Cart(Book book) {
		CartItem cartitem = items.get(book.getId());
		if(cartitem == null) {
			cartitem = new CartItem(book, 1);
			items.put(book.getId(), cartitem);
		}else {
			cartitem.setCount(cartitem.getCount() + 1);
		}
	}

	public void deleteItem(String bookId) {
		items.remove(Integer.parseInt(bookId));
	}
	
	public CartItem getItem(String bookId) {
		int id = 0;
		try {
			id = Integer.parseInt(bookId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return items.get(id);
	}
	
	public int updateCount(String bookId, String count) {
		int c = 1;
		int id = 0;
		try {
			c = Integer.parseInt(count);
			c = c>0?c:1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			id = Integer.parseInt(bookId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CartItem cartItem = items.get(id);
		if(cartItem!=null)
			cartItem.setCount(c);
		return c;
	}
	
	public void clear() {
		// 清空Map
		items.clear();
	}
	
	public Cart(Map<Integer, CartItem> items) {
		super();
		this.items = items;
	}

	public Cart() {
		super();
	}

	@Override
	public String toString() {
		return "Cart [items=" + items + "]";
	}
	
	
}
