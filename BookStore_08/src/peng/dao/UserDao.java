package peng.dao;

import peng.bean.User;

public interface UserDao {

	/**
	 * 按照用户密码查询详细信息
	 * @param user
	 * @return
	 */
	public User getUserByUserNameAndPassword(User user);
	
	/**
	 * 登记用户信息到数据库
	 * @param user
	 * @return
	 */
	public boolean registUser(User user);
	
	/**
	 * getUserByUserName for username-reuse-or-not validation
	 * @param user
	 * @return
	 */
	public User getUserByUserName(User user);
	
}
