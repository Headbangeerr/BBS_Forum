package com.bbsforum.biz;

import com.bbsforum.entity.User;

public interface UserBiz {
	
	public User getUserByMailAddress(String mailAddress);
}
