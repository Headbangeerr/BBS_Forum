package com.bbsforum.dao;

import java.util.List;

public interface SearchDao {

	/**
	 * 用于搜索结果的分页显示
	 * @param keyword 搜索关键字
	 * @param offset
	 * @param PageSize
	 * @return 搜索列表
	 */
	public List SearchPostByKeywordForPage(String keyword,int offset, int PageSize);
	/**
	 * 用户获取搜索结果条数
	 * @param keyword	关键字
	 * @return
	 */
	public int SearchSumPostByKeyword(String keyword);
	/**
	 * 
	 * @param username  用户名
	 * @return
	 */
	public int SearchSumUserByUsername(String username);
	/**
	 * 用于分页显示通过用户名搜索用户的搜索结果
	 * @param username
	 * @param offset
	 * @param PageSize
	 * @return
	 */
	public List SearchUserByUsernameForPage(String username,int offset, int PageSize);
	
	public int SearchSumPostByChildboard(String keyword,int childboardId);
	
	public List SearchPostByChildboard(String keyword,int childboardId,int offset,int pageSize);
	
	public int SearchSumPostByUsername(String username,String keyword);
	
	public List SearchPostByUsername(String uername,String keyword,int offset,int pageSize);
}
