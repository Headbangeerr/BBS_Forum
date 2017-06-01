package com.bbsforum.bizimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.FriendsBiz;
import com.bbsforum.dao.FriendsDao;
import com.bbsforum.entity.User;

public class FriendsBizImpl implements FriendsBiz {

	@Autowired
	FriendsDao  friendsDao;
	public FriendsDao getFriendsDao() {
		return friendsDao;
	}
	
	@Override
	public boolean addFriends(String userMail, String friendMail) {
		return friendsDao.addFriends(userMail, friendMail);
	}
	@Override
	public boolean deleteFriend(String userMail, String friendMail) {
		
		return friendsDao.deleteFriend(userMail, friendMail);
	}

	@Override
	public boolean checkFriend(String userMail, String friendMail) {
		return friendsDao.checkFriend(userMail, friendMail);
	}

}
