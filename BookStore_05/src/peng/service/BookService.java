package peng.service;

import java.util.List;

import peng.bean.Book;
import peng.bean.Page;

/**
 * 图书业务逻辑
 * @author JaP44
 *
 */
public interface BookService {

	/**
	 * 添加图书
	 * @param book
	 * @return
	 */
	public boolean add(Book book);
	
	/**
	 * 修改图书
	 * @param book
	 * @return
	 */
	public boolean update(Book book);
	
	/**
	 * 删除图书
	 * @param book
	 * @return
	 */
	public boolean delete(Book book);
	
	/**
	 * 查找一本图书
	 * @param book
	 * @return
	 */
	public Book getOne(Book book);
	
	/**
	 * 查询出所有图书
	 * @return
	 */
	public List<Book> getAll();
	
	/**
	 * 返回分页数据
	 * @return
	 */
	public Page<Book> getPage(String pageNo, String pageSize);
	
	/**
	 * 按照价格区间返回分页数据
	 * @return
	 */
	public Page<Book> getPageByPrice(String pageNo, String pageSize, String minPrice, String maxPrice);
}
