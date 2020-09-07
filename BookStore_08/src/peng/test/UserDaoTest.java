package peng.test;

import org.junit.Test;

import peng.bean.User;
import peng.dao.UserDao;
import peng.dao.Impl.UserDaoImpl;

public class UserDaoTest {

	@Test
	public void test() {
		UserDao ud = new UserDaoImpl();
		User queryUser = ud.getUserByUserNameAndPassword(new User(null, "tomcat", "12345611", null));
		System.out.println(queryUser);
	}
	
	@Test
	public void test2() {
		UserDao ud = new UserDaoImpl();
		boolean b = ud.registUser(new User(null, "hehe", "12345611", null));
		System.out.println(b);
	}
}
