package peng.test;

import org.junit.Test;

import peng.bean.Book;
import peng.bean.Cart;
import peng.bean.User;
import peng.service.BookService;
import peng.service.OrderService;
import peng.service.Impl.BookServiceImpl;
import peng.service.Impl.OrderServiceImpl;

public class OrderServiceTest {

	BookService bs = new BookServiceImpl();
	OrderService os = new OrderServiceImpl();
//	@Test
//	public void test() {
//		// 还需要测试一下 stock和sales对不对
//		BookService bs = new BookServiceImpl();
//		OrderService os = new OrderServiceImpl();
//		// Book book = new Book(null, "测试大法", "鹏神", 123, 2, 33, null);
//		Book book = new Book();
//		book.setId(1);
//		Book one = bs.getOne(book);
//		Cart cart = new Cart();
//		cart.addBook2Cart(one);
//		cart.addBook2Cart(one);
//		cart.addBook2Cart(one);
//		boolean checkout = os.checkout(2, cart);
//		System.out.println(checkout);
//	}
	
	@Test
	public void test2() {
		Book book = new Book();
		book.setId(72);
		Book one = bs.getOne(book);
		Cart cart = new Cart();
		User user = new User();
		user.setId(8);
		cart.addBook2Cart(one);
		cart.addBook2Cart(one);
		cart.addBook2Cart(one);
		String checkout = os.checkout(cart, user);
		System.out.println(checkout);
		
	}
}
