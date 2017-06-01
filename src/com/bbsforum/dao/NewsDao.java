package com.bbsforum.dao;

import java.util.List;

import com.bbsforum.entity.News;
import com.bbsforum.entity.User;

public interface NewsDao {

	/**
	 * 发送消息
	 * @param news  消息实例对象
	 * @return 返回ture表示发送成功，返回false表示发送失败
	 */
	public boolean sendNews(News news);
	/**
	 * 处理好友请求消息
	 * @param senderMail 发送者邮箱地址
	 * @param receiverMail 接受者邮箱地址
	 * @param operate  true表示接受邀请，false表示拒绝里邀请
	 * @return
	 */
	public boolean handleFriendRequest(String newsId,boolean operate);
	/**
	 * 判断该发送者是否已经发送过好友请求给该接受者
	 * @param senderMail 发送者邮箱
	 * @param receiverMail 接受者邮箱
	 * @return true代表该用户已经给该用户发送过好友请求了，false表示没有发送过
	 */
	public boolean checkFriRequestExist(String senderMail,String receiverMail);
	
	/**
	 * 通过接受者邮箱的地址获取该用户而所接受到的所有好友请求
	 * @param reciverMail  接收者邮箱
	 * @return   消息集合，里面只包含好友请求
	 */
	public List<News> getFriRequestListByReceiverMail(String reciverMail);
	/**
	 * 用户获取私信页面中左边的最近联系人列表
	 * @param userMail 登陆者的邮箱地址
	 * @return 返回用户列表
	 */
	public List<User> getLastestSender(String userMail);
	/**
	 * 获取针对某个发送者的未读消息的条数
	 * @param senderMail  发送者邮箱地址
	 * @param receiverMail  接受者邮箱地址
	 * @return 未读条数
	 */
	public int getUnreadSumBySender(String senderMail,String receiverMail);
	/**
	 * 通过接受者邮箱与发送者邮箱获取最近两人之间的聊天记录
	 * @param receiverMail 接收者邮箱地址
	 * @param pageIndex  页码
	 * @param pageSize 页面大小
	 * @return 消息列表
	 */
	public List<News> getLastestNewsForReceiver(String senderMail,String receiverMail, int offset,int pageSize);
	/**
	 * 获取发送者与接受者之间的聊天记录总数
	 * @param senderMail
	 * @param receiverMail
	 * @return
	 */
	public int getSumNewsForReceiver(String senderMail ,String  receiverMail);
}
