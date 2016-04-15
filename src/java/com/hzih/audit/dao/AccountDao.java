package com.hzih.audit.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzih.audit.domain.Account;

public interface AccountDao extends BaseDao {

	PageResult listByPage(String userName, String status, int pageIndex, int limit);

	Account findByNameAndPwd(String name, String pwd);

    Account findByName(String userName);
}
