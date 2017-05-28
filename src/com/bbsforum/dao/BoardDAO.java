package com.bbsforum.dao;

import java.util.List;
import java.util.Set;

import com.bbsforum.entity.Childboard;

/**
 * 
 * @author 颜磊
 *@date 2017年4月6日
 */
public interface BoardDao {

	//用于页面读取时获取板块列表
	//用于页面读取时获取板块列表
	public List getBoardList(  );
	//获取子版块列表，需要传入父版块Id
	public Set getChildBoardListByParentBoardId(int ParentBoardId);
	//删除指定id的子版块
	public boolean deleteChildBoard(int ChildBoardId);
	
	public Childboard getChildboard(int id);

}
