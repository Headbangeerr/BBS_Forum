package com.bbsforum.dao;

import java.util.List;

import com.bbsforum.entity.Safety;

public interface SafetyDao {
	//查询安全码记录
	public List findSafetyByMail(Safety safety);
	//添加安全码记录
	public boolean addSafety(Safety safety);
}
