package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.User;

public interface FriendsBiz {
public boolean addFriends(User sender,User receiver);
	
	public List getFriendList(User user);
	public boolean deleteFriend(User user,String friendMail);
}
