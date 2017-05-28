package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.Post;
import com.bbsforum.entity.Reply;

public interface PostBiz {

	/**
	 * ��ҳ��ȡ���������б�
	 * @param pageIndex  ҳ�룬��1��ʼ
	 * @param pageSize	ҳ���С����ÿһҳ����������
	 * @return �����б�
	 */
	public boolean addPost(Post post);
	
	public List<Post> getLastestPost(int pageIndex, int pageSize);
	
	public List<Post> getChoosePostListForPage(int offset, int PageSize,int bid);
	
	public Post getPost(String id);
	

}
