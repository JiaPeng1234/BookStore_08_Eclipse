package peng.dao;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.MissingResourceException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import peng.utils.JDBCUtils;

public class BaseDao<T> {
	
	private QueryRunner runner = new QueryRunner();
	private Class<T> type; //理解：Class<T>是一个运行时类，也就是Class的一个实例，也就是指T类型这个类本身
	
	public BaseDao() {
		// TODO Auto-generated constructor stub
		// 获取父类的类型，父类时带泛型参数的 ParameterizedType 翻译过来 带变量的类，也就是泛型类
		
		//UserDao这个类的带泛型的父类
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
//		System.out.println(superclass.getClass());
		
		//UserDao这个类的带泛型的父类，后面所带的泛型是什么类？（也就是问Class<T>而不是T）
		type = (Class<T>) superclass.getActualTypeArguments()[0];
	}

	/**
	 * 获取一个对象
	 * 
	 * @return
	 */
	public T getBean(String sql, Object...params){
		Connection connection = JDBCUtils.getConnection();
		T query = null;
		try {
			query = runner.query(connection, sql, new BeanHandler<>(type), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("BaseDao exception occurs");
		} 
//		finally {
//			JDBCUtils.releaseConnection(connection);
//		}
		return query;
	}

	/**
	 * 获取对象的集合
	 * 
	 * @return
	 */
	
	public List<T> getBeanList(String sql, Object...params){
		Connection connection = JDBCUtils.getConnection();
		List<T> query = null;
		try {
			query = runner.query(connection, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			throw new RuntimeException("BaseDao exception occurs");
		} 
//		finally {
//			JDBCUtils.releaseConnection(connection);
//		}
		return query;
	}

	/**
	 * 执行增删改
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object...params){
		//
		int count = 0;
		Connection connection = JDBCUtils.getConnection();
		try {
			count = runner.update(connection, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			throw new RuntimeException("BaseDao exception occurs");
		} 
//		finally {
//			JDBCUtils.releaseConnection(connection);
//		}
		return count;
	}
	
	/**
	 * BaseDao返回单个值的方法, BookDao中会用到
	 * @return
	 */
	public Object getSingleValue(String sql, Object...params){
		Object query = null;
		Connection connection = JDBCUtils.getConnection();
		try {
			query = runner.query(connection, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			throw new RuntimeException("BaseDao exception occurs");
		} 
//		finally {
//			JDBCUtils.releaseConnection(connection);
//		}
		return query;
	}
	
	/**
	 * 批量更新batch，把每一条sql执行之后再关闭链接，而不是一条一关
	 * @param sql
	 * @param params
	 * @return
	 */
	public int batch(String sql, Object[][] params){
		Connection connection = JDBCUtils.getConnection();
		try {
			runner.batch(connection, sql, params);
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("BaseDao exception occurs");
		} 
//		finally {
//			JDBCUtils.releaseConnection(connection);
//		}
		return 1;
	}
}
