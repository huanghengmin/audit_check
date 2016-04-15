package com.hzih.audit.web.action.audit;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;
import com.hzih.audit.dao.AccountLogDao;
import com.hzih.audit.domain.AccountLog;
import com.hzih.audit.service.LogService;
import com.hzih.audit.utils.NullCheckUtils;
import com.hzih.audit.utils.StringUtils;
import com.hzih.audit.web.SessionUtils;
import com.hzih.audit.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 15-5-6.
 */
public class AuditAccountLogAction extends ActionSupport {
    private Logger logger = Logger.getLogger(AuditAccountLogAction.class);
    private AccountLogDao accountLogDao;
    private LogService logService;
    private int start;
    private int limit;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public AccountLogDao getAccountLogDao() {
        return accountLogDao;
    }

    public void setAccountLogDao(AccountLogDao accountLogDao) {
        this.accountLogDao = accountLogDao;
    }

    public String find() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        StringBuilder json = new StringBuilder();
        try {
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String audittype = request.getParameter("audittype");
            String name = request.getParameter("name");
            Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils.parse(startDateStr, "yyyy-MM-dd");
            Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils.parse(endDateStr, "yyyy-MM-dd");

            PageResult pageResult = accountLogDao.listByPage(name,audittype,startDate,endDate,start/limit+1, limit);
            if (pageResult != null) {
                List<AccountLog> list = pageResult.getResults();
                int count = pageResult.getAllResultsAmount();
                if (list != null) {
                    json.append("{success:true,total:" + count + ",rows:[");
                    Iterator<AccountLog> raUserIterator = list.iterator();
                    while (raUserIterator.hasNext()) {
                        AccountLog log = raUserIterator.next();
                        if (raUserIterator.hasNext()) {
                            json.append("{");
                            json.append("id:'").append(NullCheckUtils.nullCheck(log.getId())).append("'").append(",");
                            json.append("account:'").append(NullCheckUtils.nullCheck(log.getAccount())).append("'").append(",");
                            json.append("action:'").append(NullCheckUtils.nullCheck(log.getAction())).append("'").append(",");
                            json.append("auditmodel:'").append(NullCheckUtils.nullCheck(log.getAuditmodel())).append("'").append(",");
                            json.append("auditlevel:'").append(NullCheckUtils.nullCheck(log.getAuditlevel())).append("'").append(",");
                            json.append("audittype:'").append(NullCheckUtils.nullCheck(log.getAudittype())).append("'").append(",");
                            json.append("result:'").append(NullCheckUtils.nullCheck(log.getResult())).append("'").append(",");
                            json.append("datetime:'").append(DateUtils.format(log.getDatetime(),"yyyy年MM月dd日HH时mm分ss秒")).append("'");
                            json.append("}").append(",");
                        } else {
                            json.append("{");
                            json.append("id:'").append(NullCheckUtils.nullCheck(log.getId())).append("'").append(",");
                            json.append("account:'").append(NullCheckUtils.nullCheck(log.getAccount())).append("'").append(",");
                            json.append("action:'").append(NullCheckUtils.nullCheck(log.getAction())).append("'").append(",");
                            json.append("auditmodel:'").append(NullCheckUtils.nullCheck(log.getAuditmodel())).append("'").append(",");
                            json.append("auditlevel:'").append(NullCheckUtils.nullCheck(log.getAuditlevel())).append("'").append(",");
                            json.append("audittype:'").append(NullCheckUtils.nullCheck(log.getAudittype())).append("'").append(",");
                            json.append("result:'").append(NullCheckUtils.nullCheck(log.getResult())).append("'").append(",");
                            json.append("datetime:'").append(DateUtils.format(log.getDatetime(),"yyyy年MM月dd日HH时mm分ss秒")).append("'");
                            json.append("}");
                        }
                    }
                    json.append("]}");
                    actionBase.actionEnd(response, json.toString(), result);
                }
            }

        } catch (Exception e) {
            logger.error("管理端日志审计", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "管理端日志审计","用户读取管理端审计日志信息失败");
        }
        return null;
    }
}
