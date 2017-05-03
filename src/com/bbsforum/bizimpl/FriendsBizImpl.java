package com.bbsforum.bizimpl;

import java.util.List;

import com.bbsforum.biz.FriendsBiz;
import com.bbsforum.dao.FriendsDao;
import com.bbsforum.entity.User;

public class FriendsBizImpl implements FriendsBiz {

	FriendsDao  friendsDao;
	public FriendsDao getFriendsDao() {
		return friendsDao;
	}
	@Override
	public boolean addFriends(User sender, User receiver) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List getFriendList(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFriend(User user, String friendMail) {
		// TODO Auto-generated method stub
		return false;
	}

}
