
package com.bbsforum.dao;

import java.util.List;

import org.hibernate.Transaction;

import com.bbsforum.entity.Post;
import com.bbsforum.entity.User;

public interface UserDao {

	//通过邮箱地址即登录时所用的账号搜索用户，返回一个User实例
	public 	User findUserByMailAddress(String mailAddress);

	public List getAllUserList(int offset,int pageSize);
	
	public List getAllUserList1(int i);
	
	public boolean updaUser(User user);
	
	public List getUser(int i);
}