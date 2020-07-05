package peng.test;

import java.util.List;

import org.junit.Test;

import peng.bean.Book;
import peng.bean.Page;
import peng.dao.BookDao;
import peng.dao.Impl.BookDaoImpl;
import peng.service.BookService;
import peng.service.Impl.BookServiceImpl;

public class BookDaoTest {

	BookDao bd = new BookDaoImpl();
	
	@Test
	public void test() {
		List<Book> list = bd.getAllBook();
		System.out.println(list);
	}
	
	/**
	 * 測試添加圖書
	 */
	@Test
	public void test2() {
		Book book = new Book(null, "测试一下BookDao", "pinklipinkli", 12.12, 1, 22, null);
		boolean b = bd.addBook(book);
		System.out.println(b);
	}
	
	/**
	 * 測試删除圖書
	 */
	@Test
	public void test3() {
		Book book = new Book();
		book.setId(3);
		boolean b = bd.delBook(book);
		System.out.println(b);
	}
	
	/**
	 * 測試修改圖書
	 */
	@Test
	public void test4() {
		Book book = new Book();
		book.setId(24);
		book.setTitle("鹏神列传");
		book.setAuthor("鵬神");
		boolean b = bd.updateBook(book);
		System.out.println(b);
	}
	
	/**
	 * 測試获取page
	 */
	@Test
	public void test5() {
		BookService bs = new BookServiceImpl();
		
		Page<Book> page = bs.getPage("2", "4");
		System.out.println(page);
		System.out.println(page.getPageData());
	}
	
	/**
	 * 測試获取pageByPrice
	 */
	@Test
	public void test6() {
		BookService bs = new BookServiceImpl();
		
		Page<Book> page = bs.getPageByPrice("1", "4", "10", "40");
		System.out.println(page);
		System.out.println(page.getPageData());
		System.out.println(page.getTotalCount());
	}
}
