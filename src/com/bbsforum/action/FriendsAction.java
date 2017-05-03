package com.bbsforum.action;

import com.bbsforum.biz.FriendsBiz;

public class FriendsAction extends BaseAction {

	FriendsBiz friendsBiz;
	public FriendsBiz getFriendsBiz() {
		return friendsBiz;
	}
	private String getFriendsList(){
		return SUCCESS;
	}
	private String addFriends(){
		return SUCCESS;
	}
	private String deleteFriends(){
		return SUCCESS;
	}
	
	
}
