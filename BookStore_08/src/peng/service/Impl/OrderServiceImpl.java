 package peng.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import peng.bean.Book;
import peng.bean.Cart;
import peng.bean.CartItem;
import peng.bean.Order;
import peng.bean.OrderItem;
import peng.bean.User;
import peng.dao.OrderDao;
import peng.dao.OrderItemDao;
import peng.dao.Impl.OrderDaoImpl;
import peng.dao.Impl.OrderItemDaoImpl;
import peng.service.BookService;
import peng.service.OrderItemService;
import peng.service.OrderService;

public class OrderServiceImpl implements OrderService {

	OrderDao od = new OrderDaoImpl();
	OrderItemService ois = new OrderItemServiceImpl();
	BookService bs = new BookServiceImpl();

	@Override
	public List<Order> getAllOrder() {
		return od.getOrderList();
	}

	@Override
	public List<Order> getMyOrders(Integer UserId) {
		return od.getOrderByUserId(UserId);
	}
	
	@Override
	public Order getOneOrder(String orderId) {
		return od.getOne(orderId);
	}

	@Override
	public void updateStatus(String orderid, String status) {
		Order order = new Order();
		order.setOrderId(orderid);
		int parseInt = 0;
		try {
			parseInt = Integer.parseInt(status);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order.setStatus(parseInt);
		od.updateStatus(order);
	}

	@Override
	public String checkout(Cart cart, User user) {
		// 1. generate orderid with algorithm
		long millis = System.currentTimeMillis();
		String orderId = millis + "" + user.getId();

		// 2. generate an order
		Order order = new Order();
		order.setCreateDate(new Date());
		order.setOrderId(orderId);
		order.setTotalMoney(cart.getTotalMoney());
		order.setStatus(0);
		order.setUserId(user.getId());

		// 3. generate orderitems for this order (according to cart) and save it to
		// database one by one
		List<CartItem> allItems = cart.getAllItems();
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem cartItem : allItems) {
			OrderItem item = new OrderItem(cartItem.getBook().getTitle(), cartItem.getBook().getPrice(),
					cartItem.getCount(), cartItem.getTotalPrice(), orderId);
			orderItems.add(item);
		}
		// 4. save order
		od.saveOrder(order);

		// 5. save orderitems
		ois.saveItem(orderItems);
		
		// 6. update stock and sales
		for(CartItem cartItem : allItems) {
			Book book = cartItem.getBook();
			// update stock and sales according to the databank in real time in case of invalid operation
			Book one = bs.getOne(book);
			one.setSales(one.getSales() + cartItem.getCount());
			one.setStock(one.getStock() - cartItem.getCount());
			bs.update(one);
		}
		
		// 7. clear cart
		cart.clear();
		return orderId;
	}

}
