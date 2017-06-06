
package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.User;

public interface UserBiz {
	
	public User getUserByMailAddress(String mailAddress);
	
	public List getAllUserList(int offset, int pageSize);
	
	public boolean addUser(User user);
	
	public boolean updateUser(User user);
}

