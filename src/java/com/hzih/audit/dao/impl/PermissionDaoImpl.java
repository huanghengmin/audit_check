package com.hzih.audit.dao.impl;

import cn.collin.commons.dao.MyDaoSupport;
import com.hzih.audit.dao.PermissionDao;
import com.hzih.audit.domain.Permission;

public class PermissionDaoImpl extends MyDaoSupport implements PermissionDao {

	@Override
	public void setEntityClass() {
		this.entityClass = Permission.class;
	}

}
