package com.bbsforum.biz;

import java.util.List;
import java.util.Set;

public interface BoardBiz {

	public List getBoardList();
	public Set getChildboardByParentBoardId(int ParentBoardId);
}
