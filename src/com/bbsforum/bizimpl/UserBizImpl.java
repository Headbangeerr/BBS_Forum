
package com.bbsforum.bizimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.UserBiz;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.User;

public class UserBizImpl implements UserBiz {

	private static Logger logger=Logger.getLogger(UserBiz.class);
	@Autowired
	UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public User getUserByMailAddress(String mailAddress) {
		User user=userDao.findUserByMailAddress(mailAddress);
		return user;
	}


	@Override
	public List getAllUserList(int offset, int pageSize) {
		// TODO Auto-generated method stub
		List user=userDao.getAllUserList(offset, pageSize);
		return user;
	}
	
	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}


	@Override
	public boolean updateUser(User user) {
		return userDao.UpdateUser(user);
	}
	
	@Override
	public boolean deleteUserByMailAddress(String mailAddress) {
	    return userDao.deleteUserByMailAddress(mailAddress);
		
	}


	@Override
	public boolean silenceUserByMailAddress(String mailAddress) {
		
		return userDao.silenceUserByMailAddress(mailAddress);
	
		
	}


	@Override
	public boolean NonsilenceUserByMailAddress(String mailAddress) {
		return 	userDao.NonsilenceUserByMailAddress(mailAddress);
		
	}


	@Override
	public void updateUserLastLoginDate(User user) {
		userDao.updateUserLastLoginDate(user);
		
	}


	@Override
	public boolean checkShieldForUser(String userMail, String shieldMail) {
		
		return userDao.checkShieldForUser(userMail, shieldMail);
	}


	@Override
	public boolean addShieldUser(String userMail, String shieldMail) {
		
		return userDao.addShieldUser(userMail, shieldMail);
	}


	@Override
	public boolean deleShieldUser(String userMail, String shieldMail) {
		
		return userDao.deleShieldUser(userMail, shieldMail);
	}

	
}
