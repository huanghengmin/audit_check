package com.hzih.audit.syslog.hibernate;


import com.hzih.audit.domain.ClientLog;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class ClientLogDao extends BaseHibernateDAO{

    public ClientLog findOldLog() {
        String hql=" from ClientLog a order by a.datetime asc";
        Session session = getSession();
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        ArrayList<ClientLog> accountLogs  = (ArrayList<ClientLog>)query.list();
        session.close();
        if(accountLogs!=null&&accountLogs.size()>0){
            return accountLogs.get(0);
        }
        return null;
    }

    public void saveLog(ClientLog clientLog) {
        Session session = getSession();
        Transaction tran = session.beginTransaction();
        session.saveOrUpdate(clientLog);
        tran.commit();
        session.close();
    }
}
