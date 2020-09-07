package peng.dao.Impl;

import java.util.List;
import peng.bean.Book;
import peng.dao.BaseDao;
import peng.dao.BookDao;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	/**
	 * 获取所有图书
	 */
	@Override
	public List<Book> getAllBook() {
		// img_path
		String sql = "select id,title,author,price,sales,stock,img_path as imgPath from bs_book";
		return getBeanList(sql);
	}

	/**
	 * 添加图书
	 */
	@Override
	public boolean addBook(Book book) {
		String sql = "insert into bs_book(title,author,price,sales,stock,img_path) values (?,?,?,?,?,?)";
		int update = update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath());
		return update > 0;
	}

	/**
	 * 删除图书
	 */
	@Override
	public boolean delBook(Book book) {
		String sql = "delete from bs_book where id=?";
		int update = update(sql, book.getId());
		return update > 0;
	}

	/**
	 * 修改图书
	 */
	@Override
	public boolean updateBook(Book book) {
		// 此处整了一个异常
		String sql = "update bs_book set title=? , author=? , price=? , sales=? , stock=? , img_path=? where id=?";
		int update = update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath(), book.getId());
		return update > 0;
	}

	/**
	 * 获取一本图书
	 */
	@Override
	public Book getBook(Book book) {
		String sql = "select id,title,author,price,sales,stock,img_path as imgPath from bs_book where id=?";
		return getBean(sql, book.getId());
	}

	@Override
	public List<Book> getPageList(int index, int size) {
		String sql = "select id,title,author,price,sales,stock,img_path as imgPath from bs_book limit ?,?";
		return getBeanList(sql, index, size);
	}

	@Override
	public int getTotalCount() {
		String sql = "select count(*) from bs_book";
		Object object = getSingleValue(sql);
		int parseInt = 0;
		try {
			parseInt = Integer.parseInt(object.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parseInt;
	}
	
	@Override
	public int getTotalCountByPrice(double minPrice, double maxPrice) {
		String sql = "select count(*) from bs_book where price between ? and ?";
		Object object = getSingleValue(sql,minPrice,maxPrice);
		int parseInt = 0;
		try {
			parseInt = Integer.parseInt(object.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parseInt;
	}
	
	@Override
	public List<Book> getPageByPrice(int index, int size, double minPrice, double maxPrice) {
		String sql = "select id,title,author,price,sales,stock,img_path as imgPath from bs_book where price between ? and ? limit ?,?";
		return getBeanList(sql, minPrice, maxPrice, index, size);	
	}
	
}
