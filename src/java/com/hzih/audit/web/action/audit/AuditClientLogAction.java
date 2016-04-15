package com.hzih.audit.web.action.audit;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;
import com.hzih.audit.dao.ClientLogDao;
import com.hzih.audit.domain.ClientLog;
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
public class AuditClientLogAction extends ActionSupport {
    private Logger logger = Logger.getLogger(AuditClientLogAction.class);
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

    private ClientLogDao clientLogDao;

    public ClientLogDao getClientLogDao() {
        return clientLogDao;
    }

    public void setClientLogDao(ClientLogDao clientLogDao) {
        this.clientLogDao = clientLogDao;
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

            PageResult pageResult = clientLogDao.listByPage(name,audittype,startDate,endDate,start/limit+1, limit);
            if (pageResult != null) {
                List<ClientLog> list = pageResult.getResults();
                int count = pageResult.getAllResultsAmount();
                if (list != null) {
                    json.append("{success:true,total:" + count + ",rows:[");
                    Iterator<ClientLog> raUserIterator = list.iterator();
                    while (raUserIterator.hasNext()) {
                        ClientLog log = raUserIterator.next();
                        if (raUserIterator.hasNext()) {
                            json.append("{");
                            json.append("id:'").append(NullCheckUtils.nullCheck(log.getId())).append("'").append(",");
                            json.append("CN:'").append(NullCheckUtils.nullCheck(log.getCN())).append("'").append(",");
                            json.append("SN:'").append(NullCheckUtils.nullCheck(log.getSN())).append("'").append(",");
                            json.append("O:'").append(NullCheckUtils.nullCheck(log.getO())).append("'").append(",");
                            json.append("OU:'").append(NullCheckUtils.nullCheck(log.getOU())).append("'").append(",");
                            json.append("L:'").append(NullCheckUtils.nullCheck(log.getL())).append("'").append(",");
                            json.append("ST:'").append(NullCheckUtils.nullCheck(log.getST())).append("'").append(",");
                            json.append("phone:'").append(NullCheckUtils.nullCheck(log.getPhone())).append("'").append(",");
                            json.append("idcard:'").append(NullCheckUtils.nullCheck(log.getIdcard())).append("'").append(",");
                            json.append("email:'").append(NullCheckUtils.nullCheck(log.getEmail())).append("'").append(",");
                            json.append("sourceip:'").append(NullCheckUtils.nullCheck(log.getSourceip())).append("'").append(",");
                            json.append("sourceport:'").append(NullCheckUtils.nullCheck(log.getSourceport())).append("'").append(",");
                            json.append("accessurl:'").append(NullCheckUtils.nullCheck(log.getAccessurl())).append("'").append(",");
                            json.append("result:'").append(NullCheckUtils.nullCheck(log.getResult())).append("'").append(",");
                            json.append("upbytes:'").append(NullCheckUtils.nullCheck(log.getUpbytes())).append("'").append(",");
                            json.append("downbytes:'").append(NullCheckUtils.nullCheck(log.getDownbytes())).append("'").append(",");
                            json.append("audittype:'").append(NullCheckUtils.nullCheck(log.getAudittype())).append("'").append(",");
                            json.append("datetime:'").append(DateUtils.format(log.getDatetime(),"yyyy年MM月dd日HH时mm分ss秒")).append("'");
                            json.append("}").append(",");
                        } else {
                            json.append("{");
                            json.append("id:'").append(NullCheckUtils.nullCheck(log.getId())).append("'").append(",");
                            json.append("CN:'").append(NullCheckUtils.nullCheck(log.getCN())).append("'").append(",");
                            json.append("SN:'").append(NullCheckUtils.nullCheck(log.getSN())).append("'").append(",");
                            json.append("O:'").append(NullCheckUtils.nullCheck(log.getO())).append("'").append(",");
                            json.append("OU:'").append(NullCheckUtils.nullCheck(log.getOU())).append("'").append(",");
                            json.append("L:'").append(NullCheckUtils.nullCheck(log.getL())).append("'").append(",");
                            json.append("ST:'").append(NullCheckUtils.nullCheck(log.getST())).append("'").append(",");
                            json.append("phone:'").append(NullCheckUtils.nullCheck(log.getPhone())).append("'").append(",");
                            json.append("idcard:'").append(NullCheckUtils.nullCheck(log.getIdcard())).append("'").append(",");
                            json.append("email:'").append(NullCheckUtils.nullCheck(log.getEmail())).append("'").append(",");
                            json.append("sourceip:'").append(NullCheckUtils.nullCheck(log.getSourceip())).append("'").append(",");
                            json.append("sourceport:'").append(NullCheckUtils.nullCheck(log.getSourceport())).append("'").append(",");
                            json.append("accessurl:'").append(NullCheckUtils.nullCheck(log.getAccessurl())).append("'").append(",");
                            json.append("result:'").append(NullCheckUtils.nullCheck(log.getResult())).append("'").append(",");
                            json.append("upbytes:'").append(NullCheckUtils.nullCheck(log.getUpbytes())).append("'").append(",");
                            json.append("downbytes:'").append(NullCheckUtils.nullCheck(log.getDownbytes())).append("'").append(",");
                            json.append("audittype:'").append(NullCheckUtils.nullCheck(log.getAudittype())).append("'").append(",");
                            json.append("datetime:'").append(DateUtils.format(log.getDatetime(), "yyyy年MM月dd日HH时mm分ss秒")).append("'");
                            json.append("}");
                        }
                    }
                    json.append("]}");
                    actionBase.actionEnd(response, json.toString(), result);
                }
            }

        } catch (Exception e) {
            logger.error("客户端日志审计", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "客户端日志审计","用户读取客户端审计日志信息失败");
        }
        return null;
    }
}
