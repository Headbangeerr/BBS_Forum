package com.bbsforum.bizimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.SearchBiz;
import com.bbsforum.dao.SearchDao;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.Post;
import com.bbsforum.entity.User;

public class SearchBizImpl implements SearchBiz {
	private Logger logger=Logger.getLogger(SearchBizImpl.class);
	@Autowired
	SearchDao searchDao;
	public SearchDao getSearchDao() {
		return searchDao;
	}
	@Override
	public PageBean SearchPostByKeyword(String keyword, int pageIndex, int pageSize) {
		int itemSum=searchDao.SearchSumPostByKeyword(keyword);
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标		
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<Post> posts=searchDao.SearchPostByKeywordForPage(keyword, offset, pageSize);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(posts);
		pageBean.init();
		return pageBean;
	}

	@Override
	public PageBean SearchUserByUsername(String username, int pageIndex, int pageSize) {
		int itemSum=searchDao.SearchSumUserByUsername(username);	
		logger.info("正在通过用户名搜索用户    用户名"+username+"  搜索结果总数："+itemSum);		
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标		
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<User> users=searchDao.SearchUserByUsernameForPage(username, offset, pageSize);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(users);
		pageBean.init();
		return pageBean;
	}
	@Override
	public PageBean SearchPostByChildboardId(String keyword, int childboardId,
		int pageIndex, int pageSize) {
		int itemSum=searchDao.SearchSumPostByChildboard(keyword, childboardId);
		logger.info("正在通过版块搜索帖子    版块id:"+childboardId+"  搜索关键字："+keyword+"  搜索结果总数："+itemSum);
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标		
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<Post> posts;
		if(itemSum!=0){
			 posts=searchDao.SearchPostByChildboard(keyword, childboardId, offset, pageSize);
		}
		else{
			posts=null;
		}
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(posts);
		pageBean.init();
		return pageBean;
	}
	@Override
	public PageBean SearchPostByUsername(String username, String  keyword,
			int pageIndex, int pageSize) {
		int itemSum=searchDao.SearchSumPostByUsername(username, keyword);
		logger.info("正在通过用户名搜索帖子    版块id:"+username+"  搜索关键字："+keyword+"  搜索结果总数："+itemSum);
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标		
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<Post> posts;
		if(itemSum!=0){
			 posts=searchDao.SearchPostByUsername(username, keyword, offset, pageSize);
		}
		else{
			posts=null;
		}
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(posts);
		pageBean.init();
		return pageBean;
	}

}
