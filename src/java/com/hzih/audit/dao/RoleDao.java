package com.hzih.audit.dao;

import cn.collin.commons.dao.BaseDao;
import com.hzih.audit.domain.Role;

public interface RoleDao extends BaseDao {

    public Role findByName(String name) throws Exception;
}
