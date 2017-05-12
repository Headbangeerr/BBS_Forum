package com.bbsforum.dao;

import java.util.List;

import com.bbsforum.entity.User;

public interface FriendsDao {

	public boolean addFriends(String userMail,User friend);
	
	public List getFriendList(String userMail,int offset,int PageSize);
	public boolean deleteFriend(String userMail,User friend);
}
