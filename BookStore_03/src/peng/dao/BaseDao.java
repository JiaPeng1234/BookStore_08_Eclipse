package peng.dao;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import peng.utils.JDBCUtils;

public class BaseDao<T> {
	
	private QueryRunner runner = new QueryRunner();
	private Class<T> type; //��⣺Class<T>��һ������ʱ�࣬Ҳ����Class��һ��ʵ����Ҳ����ָT��������౾��
	
	public BaseDao() {
		// TODO Auto-generated constructor stub
		// ��ȡ��������ͣ�����ʱ�����Ͳ����� ParameterizedType ������� ���������࣬Ҳ���Ƿ�����
		
		//UserDao�����Ĵ����͵ĸ���
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
//		System.out.println(superclass.getClass());
		
		//UserDao�����Ĵ����͵ĸ��࣬���������ķ�����ʲô�ࣿ��Ҳ������Class<T>������T��
		type = (Class<T>) superclass.getActualTypeArguments()[0];
	}

	/**
	 * ��ȡһ������
	 * 
	 * @return
	 */
	public T getBean(String sql, Object...params) {
		Connection connection = JDBCUtils.getConnection();
		T query = null;
		try {
			query = runner.query(connection, sql, new BeanHandler<>(type), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.releaseConnection(connection);
		}
		return query;
	}

	/**
	 * ��ȡ����ļ���
	 * 
	 * @return
	 */
	
	public List<T> getBeanList(String sql, Object...params) {
		Connection connection = JDBCUtils.getConnection();
		List<T> query = null;
		try {
			query = runner.query(connection, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.releaseConnection(connection);
		}
		return query;
	}

	/**
	 * ִ����ɾ��
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object...params) {
		//
		int count = 0;
		Connection connection = JDBCUtils.getConnection();
		try {
			count = runner.update(connection, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.releaseConnection(connection);
		}
		return count;
	}
}
