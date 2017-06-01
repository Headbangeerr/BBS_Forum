package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.User;

public interface FriendsBiz {
	public boolean addFriends(String userMail,String friendMail);

	public boolean deleteFriend(String userMail,String friendMail);
	public boolean checkFriend(String userMail,String friendMail);
}
