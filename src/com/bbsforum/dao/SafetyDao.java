package com.bbsforum.dao;

import java.util.List;

import com.bbsforum.entity.Safety;

public interface SafetyDao {
	//��ѯ��ȫ���¼
	public List findSafetyByMail(Safety safety);
	//��Ӱ�ȫ���¼
	public boolean addSafety(Safety safety);
}
