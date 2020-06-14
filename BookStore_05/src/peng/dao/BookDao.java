package peng.dao;

import java.util.List;

import peng.bean.Book;

public interface BookDao {

	/**
	 * 获取所有图书
	 * @return
	 */
	public List<Book> getAllBook();
	
	/**
	 * @param 添加一本图书 传入要添加的图书
	 * @return true代表添加成功
	 */
	public boolean addBook(Book book);
	/**
	 * 删除一本图书
	 * @param book要删除的图书，id
	 * @return
	 */
	public boolean delBook(Book book);
	/**
	 * 修改一本图书
	 * @param book要修改的图书，在传入的book对象上修改
	 * @return
	 */
	public boolean updateBook(Book book);
	/**
	 * 取得图书对象
	 * @param book
	 * @return
	 */
	public Book getBook(Book book);
	/**
	 * 分页查找图书的方法
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Book> getPageList(int index, int size);
	/**
	 * 查找到的数据库中book一共有多少本
	 * @return
	 */
	public int getTotalCount();
	/**
	 * 按照价格区间查找到的数据库中book一共有多少本
	 * @return
	 */
	public int getTotalCountByPrice(double minPrice, double maxPrice);
	/**
	 * 按照价格区间分页查找图书
	 * @return
	 */
	public List<Book> getPageByPrice(int index, int size, double minPrice, double maxPrice);
		
}
