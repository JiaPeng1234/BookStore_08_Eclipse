package peng.service;

import peng.bean.User;

public interface UserService {

	public User login(User user);
	
	public boolean regist(User user);
	
	public boolean checkuser(User user);

}
