package com.hzih.audit.service;

import com.hzih.audit.domain.Account;

public interface LoginService {

	Account getAccountByNameAndPwd(String name, String pwd) ;

    Account getAccountByName(String name) ;
}
