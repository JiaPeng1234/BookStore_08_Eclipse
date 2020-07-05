package peng.service.Impl;

import java.util.List;

import peng.bean.Book;
import peng.bean.Page;
import peng.dao.BookDao;
import peng.dao.Impl.BookDaoImpl;
import peng.service.BookService;

public class BookServiceImpl implements BookService {

	BookDao bd = new BookDaoImpl();

	/**
	 * 增添一本图书
	 */
	@Override
	public boolean add(Book book) {
		return bd.addBook(book);
	}

	/**
	 * 更新一本图书
	 */
	@Override
	public boolean update(Book book) {
		return bd.updateBook(book);
	}

	/**
	 * 删除图书
	 */
	@Override
	public boolean delete(Book book) {
		return bd.delBook(book);
	}

	/**
	 * 查找一本图书
	 */
	@Override
	public Book getOne(Book book) {
		return bd.getBook(book);
	}

	/**
	 * 查找所有图书
	 */
	@Override
	public List<Book> getAll() {
		return bd.getAllBook();
	}

	@Override
	public Page<Book> getPage(String pageNo, String pageSize) {
		
		Page<Book> page = new Page<Book>();
		
		// 1. 将用户传入的数据转型并封装，并设置默认值
		int pn = 1;
		int ps = page.getPageSize();
		try {
			pn = Integer.parseInt(pageNo);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps = Integer.parseInt(pageSize);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2. 因为要使用totalcount也即是当前总记录数，还需要查询数据库
		// 由于增加的ps，需要知道它是否越上界，下面的几个语句的逻辑必须按照如下顺序
		// 先要设置页面大小
		page.setPageSize(ps);
		// 获取总记录数
		int totalcount = bd.getTotalCount();
		// 设置总记录数
		page.setTotalCount(totalcount);
		// 这样就可以算出总页数 getTotalPage()
		// setPageNo(pn)把pageNo赋值给page对象，同时还要判断pn是否>0&<page.getTotalPage()，所以需要总页数page.getTotalPage()
		// getTotalPage()是在setPageNo之中自动调用，而getTotalPage又需要知道getTotalCount() / getPageSize()
		page.setPageNo(pn);
		
		// 3. 将得到的pageData内容赋值给page对象中的pageData属性
		List<Book> list = bd.getPageList(page.getIndex(), page.getPageSize());
		page.setPageData(list);

		return page;
	}
	
	@Override
	public Page<Book> getPageByPrice(String pageNo, String pageSize, String minPrice, String maxPrice) {
		
		Page<Book> page = new Page<Book>();
		double max = Double.MAX_VALUE;
		double min = 0.0;
		try {
			max = Double.parseDouble(maxPrice);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			min = Double.parseDouble(minPrice);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 1. 将用户传入的数据转型并封装，并设置默认值
		int pn = 1;
		int ps = page.getPageSize();
		try {
			pn = Integer.parseInt(pageNo);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps = Integer.parseInt(pageSize);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2. 因为要使用totalcount也即是当前总记录数，还需要查询数据库
		// 由于增加的ps，需要知道它是否越上界，下面的几个语句的逻辑必须按照如下顺序
		// 先要设置页面大小
		page.setPageSize(ps);
		// 获取总记录数
		int totalcount = bd.getTotalCountByPrice(min, max);
		// 设置总记录数
		page.setTotalCount(totalcount);
		// 这样就可以算出总页数 getTotalPage()
		// setPageNo(pn)把pageNo赋值给page对象，同时还要判断pn是否>0&<page.getTotalPage()，所以需要总页数page.getTotalPage()
		// getTotalPage()是在setPageNo之中自动调用，而getTotalPage又需要知道getTotalCount() / getPageSize()
		page.setPageNo(pn);
		
		// 3. 将得到的pageData内容赋值给page对象中的pageData属性
		List<Book> list = bd.getPageByPrice(page.getIndex(), page.getPageSize(), min, max);
		page.setPageData(list);

		return page;
	}

}
