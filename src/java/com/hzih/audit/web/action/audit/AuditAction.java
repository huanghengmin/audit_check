package com.hzih.audit.web.action.audit;


import com.hzih.audit.domain.Account;
import com.hzih.audit.service.AuditService;
import com.hzih.audit.service.LogService;
import com.hzih.audit.service.LoginService;
import com.hzih.audit.utils.DateUtils;
import com.hzih.audit.utils.FileUtil;
import com.hzih.audit.utils.StringUtils;
import com.hzih.audit.web.SessionUtils;
import com.hzih.audit.web.action.ActionBase;
import com.inetec.common.security.DesEncrypterAsPassword;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-6-19
 * Time: 上午9:55
 * To change this template use File | Settings | File Templates.
 * 日志审计
 */
public class AuditAction extends ActionSupport{
    private static final Logger logger = LoggerFactory.getLogger(AuditAction.class);
    private LogService logService;
    private AuditService auditService;
    private LoginService loginService;
    private int start;
    private int limit;

    public String selectUserAudit() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        String json =  "{'success':true,'total':0,'rows':[]}";
        try {
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String logLevel = request.getParameter("logLevel");
            String userName = request.getParameter("userName");
            Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
                    .parse(startDateStr, "yyyy-MM-dd");
            Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
                    .parse(endDateStr, "yyyy-MM-dd");

            json = auditService.selectUserAudit(start/limit+1, limit,startDate,endDate,logLevel,userName );
//            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "管理员日志审计","用户读取管理员日志审计信息成功 ");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "管理员日志审计","用户读取管理员日志审计信息失败 ");
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    /**
     * 检查startDate和endDate,endDate必须大于startDate
     * @return
     * @throws Exception
     */
    public String checkDate() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        String msg = null;
        boolean isClear = false;
        try {
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            isClear = DateUtils.checkDate(startDate, endDate, "yyyy-MM-dd");
            msg = "校验成功";
        } catch (Exception e) {
            msg = "校验失败"+e.getMessage();
            logger.error(msg,e);
        }
        String json =  "{success:true,msg:'"+msg+"',clear:"+isClear+"}";
        actionBase.actionEnd(response, json, result);
        return null;
    }



    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public AuditService getAuditService() {
        return auditService;
    }

    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

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

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
