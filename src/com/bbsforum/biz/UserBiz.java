
package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.User;

public interface UserBiz {
	
	public User getUserByMailAddress(String mailAddress);
	
	public List getAllUserList(int offset, int pageSize);
	
	public boolean addUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUserByMailAddress(String mailAddress);
	public boolean silenceUserByMailAddress(String mailAddress);
	public boolean NonsilenceUserByMailAddress(String mailAddress);
	public void updateUserLastLoginDate(User user);
	public boolean checkShieldForUser(String userMail,String shieldMail);
	public boolean addShieldUser(String userMail,String shieldMail);
	public boolean deleShieldUser(String userMail,String shieldMail);
}

