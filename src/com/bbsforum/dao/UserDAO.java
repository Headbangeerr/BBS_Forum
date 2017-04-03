package com.bbsforum.dao;

import com.bbsforum.entity.User;

public interface UserDao {

	//通过邮箱地址即登录时所用的账号搜索用户，返回一个User实例
	public 	User findUserByMailAddress(String mailAddress);


}
