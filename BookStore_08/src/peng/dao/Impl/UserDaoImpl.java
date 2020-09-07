package peng.dao.Impl;

import peng.bean.User;
import peng.dao.BaseDao;
import peng.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao{

	@Override
	public User getUserByUserNameAndPassword(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from bs_user where username = ? and password = ?";
		// String sql = "select * from bs_user where id = 1";
		User bean = this.getBean(sql, user.getUsername(), user.getPassword());
		return bean;
	}

	@Override
	public boolean registUser(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into bs_user(username, password, email) values(?,?,?)";
		// update > 0 is success
		int update = this.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
		return update > 0;
	}

	@Override
	public User getUserByUserName(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from bs_user where username=?";
		User bean = getBean(sql, user.getUsername());
		return bean;
	}
}
