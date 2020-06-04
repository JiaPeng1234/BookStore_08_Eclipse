package peng.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	private static DataSource ds = new ComboPooledDataSource("book_devoloper");
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * �ͷ����ݿ�����
	 * @param connection
	 */
	public static void releaseConnection(Connection connection) {
		try {
			if (connection != null)
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
