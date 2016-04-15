package com.hzih.audit.syslog.hibernate;

import com.hzih.audit.domain.AccountLog;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;


public class AccountLogDao extends BaseHibernateDAO{
    private Logger logger = Logger.getLogger(AccountLog.class);

    public AccountLog findOldLog() {
        String hql=" from AccountLog a order by a.datetime asc";
        Session session = getSession();
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        ArrayList<AccountLog> accountLogs  = (ArrayList<AccountLog>)query.list();
        session.close();
        if(accountLogs!=null&&accountLogs.size()>0){
            return accountLogs.get(0);
        }
        return null;
    }

    public void saveLog(AccountLog accountLog) {
        Session session = getSession();
        Transaction tran = session.beginTransaction();
        session.saveOrUpdate(accountLog);
        tran.commit();
        session.close();
    }

}
