package com.bbsforum.biz;

import java.util.List;
import java.util.Set;

import com.bbsforum.entity.Board;
import com.bbsforum.entity.Childboard;

public interface BoardBiz {

	public List getBoardList();
	public Set getChildboardByParentBoardId(int ParentBoardId);
	
	public void addBoard(Board board);
	public void addChildBoard(Childboard childBoard);
	public Board getBoardById(int id);
	public void updateBoard(Board board);
	public void deleteBoard(int id);
	public void updateChildBoard(Childboard childBoard);
	public Childboard getChildBoardById(int id);
	
}
