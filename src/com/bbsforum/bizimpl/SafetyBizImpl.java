package com.bbsforum.bizimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bbsforum.biz.SafetyBiz;
import com.bbsforum.dao.SafetyDao;
import com.bbsforum.entity.Safety;
//使用@Service注解在Spring容器中注册名为userBiz的UserBizImpl实例
@Service("userBiz")
public class SafetyBizImpl implements SafetyBiz {
	//使用@Resource注解注入UserDAOImpl实例
	@Resource
	SafetyDao safetyDao;
	public void setUserDAO(SafetyDao safetyDao) {
		this.safetyDao = safetyDao;
	}

	@Override
	public List findSafetyByMail(Safety safety) {
		return safetyDao.findSafetyByMail(safety);
	}

	@Override
	public boolean addSafety(Safety safety) {
		return safetyDao.addSafety(safety);
	}

}
