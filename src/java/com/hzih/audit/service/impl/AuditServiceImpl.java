package com.hzih.audit.service.impl;

import cn.collin.commons.domain.PageResult;
import com.hzih.audit.dao.SysLogDao;
import com.hzih.audit.dao.UserOperLogDao;
import com.hzih.audit.domain.SysLog;
import com.hzih.audit.domain.UserOperLog;
import com.hzih.audit.service.AuditService;
import com.hzih.audit.utils.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-6-19
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
public class AuditServiceImpl implements AuditService {
    private SysLogDao sysLogDao;
    private UserOperLogDao userOperLogDao;

    /**
     * 分页读取用户日志--并以json形式返回
     */
    public String selectUserAudit(int pageIndex, int limit, Date startDate, Date endDate,
                                  String logLevel, String userName) throws Exception {
        PageResult pageResult = userOperLogDao.listLogsByParams(pageIndex,limit,startDate, endDate, logLevel, userName);
        List<UserOperLog> userOperLogs = pageResult.getResults();
        int total = pageResult.getAllResultsAmount();
        String json = "{success:true,total:"+ total + ",rows:[";
        for (UserOperLog u : userOperLogs) {
            json +="{id:'"+u.getId()+"',userName:'"+u.getUserName()+"',level:'"+u.getLevel()+
                    "',auditModule:'"+u.getAuditModule()+"',auditInfo:'"+u.getAuditInfo()+
                    "',logTime:'"+ DateUtils.formatDate(u.getLogTime(), "yy-MM-dd HH:mm:ss")+"'},";
        }
        json += "]}";
        return json;
    }

    @Override
    public String selectOSAudit(int pageIndex, int limit, Date startDate, Date endDate, String logLevel) {
        PageResult pageResult = sysLogDao.listLogsByParams(pageIndex,limit,startDate, endDate, logLevel);
        List<SysLog> list = pageResult.getResults();
        int total = pageResult.getAllResultsAmount();
        String json = "{success:true,total:"+ total + ",rows:[";
        for (SysLog o : list) {
            json +="{id:'"+o.getId()+"',auditAction:'"+o.getAuditAction()+"',level:'"+o.getLevel()+
                    "',auditModule:'"+o.getAuditModule()+"',auditInfo:'"+o.getAuditInfo()+
                    "',logTime:'"+DateUtils.formatDate(o.getLogTime(),"yy-MM-dd HH:mm:ss")+"'},";
        }
        json += "]}";
        return json;
    }

    public SysLogDao getSysLogDao() {
        return sysLogDao;
    }

    public void setSysLogDao(SysLogDao sysLogDao) {
        this.sysLogDao = sysLogDao;
    }

    public UserOperLogDao getUserOperLogDao() {
        return userOperLogDao;
    }

    public void setUserOperLogDao(UserOperLogDao userOperLogDao) {
        this.userOperLogDao = userOperLogDao;
    }
}
