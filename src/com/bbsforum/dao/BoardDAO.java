package com.bbsforum.dao;

import java.util.List;
import java.util.Set;

import com.bbsforum.entity.Childboard;

/**
 * 
 * @author ����
 *@date 2017��4��6��
 */
public interface BoardDao {

	//����ҳ���ȡʱ��ȡ����б�
	public List getBoardList(  );
	//��ȡ�Ӱ���б���Ҫ���븸���Id
	public Set getChildBoardListByParentBoardId(int ParentBoardId);
	//ɾ��ָ��id���Ӱ��
	public boolean deleteChildBoard(int ChildBoardId);
	
	public Childboard getChildboard(int id);
}
