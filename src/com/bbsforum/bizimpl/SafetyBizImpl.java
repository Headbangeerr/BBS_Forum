package com.bbsforum.bizimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bbsforum.biz.SafetyBiz;
import com.bbsforum.dao.SafetyDao;
import com.bbsforum.entity.Safety;
//ʹ��@Serviceע����Spring������ע����ΪuserBiz��UserBizImplʵ��
@Service("userBiz")
public class SafetyBizImpl implements SafetyBiz {
	//ʹ��@Resourceע��ע��UserDAOImplʵ��
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
