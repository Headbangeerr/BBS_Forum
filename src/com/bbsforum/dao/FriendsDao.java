package com.bbsforum.dao;

import java.util.List;

import com.bbsforum.entity.User;

public interface FriendsDao {

	public boolean addFriends(String userMail,String friendMail);
	
	public List getFriendList(String userMail,int offset,int PageSize);
	public boolean deleteFriend(String userMail,String friendMail);
	/**
	 * 判断查看的这名用户是否是登陆者的好友
	 * @param userMail 登录用户
	 * @param friendMail 被查看的用户
	 * @return 
	 */
	public boolean checkFriend(String userMail,String friendMail);
}
