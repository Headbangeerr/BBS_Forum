package com.bbsforum.bizimpl;

import com.bbsforum.biz.UserBiz;
import com.bbsforum.dao.UserDAO;
import com.bbsforum.entity.User;

public class UserBizImpl implements UserBiz {

	UserDAO userDao;
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}


	@Override
	public User getUserByMailAddress(String mailAddress) {
		User user=userDao.findUserByMailAddress(mailAddress);
		return user;
	}

}
