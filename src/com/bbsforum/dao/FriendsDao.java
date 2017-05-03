package com.bbsforum.dao;

import java.util.List;

import com.bbsforum.entity.User;

public interface FriendsDao {

	public boolean addFriends(User sender,User receiver);
	
	public List getFriendList(User user);
	public boolean deleteFriend(User user,String friendMail);
}
