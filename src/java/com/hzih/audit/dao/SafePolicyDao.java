package com.hzih.audit.dao;

import cn.collin.commons.dao.BaseDao;
import com.hzih.audit.domain.SafePolicy;

public interface SafePolicyDao extends BaseDao{

	SafePolicy getData();

}
