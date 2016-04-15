package com.hzih.audit.web.action.audit;

import com.hzih.audit.dao.AccountLogDao;
import com.hzih.audit.dao.ClientLogDao;
import com.hzih.audit.domain.AccountLog;
import com.hzih.audit.domain.ClientLog;
import com.hzih.audit.service.LogService;
import com.hzih.audit.web.SessionUtils;
import com.hzih.audit.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 15-4-23.
 */
public class AuditReportConfigAction extends ActionSupport {
    private Logger logger = Logger.getLogger(AuditReportConfigAction.class);
    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
    /**
     * 保存配置
     * @return
     * @throws java.io.IOException
     */
    public String save() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        String json = null;
        String msg = null;
        String audit_warn_number = request.getParameter("audit_warn_number");
        String verity_warn_number = request.getParameter("verity_warn_number");
        String client_warn_number = request.getParameter("client_warn_number");
        String admin_warn_number = request.getParameter("admin_warn_number");
        String build_warn_number = request.getParameter("build_warn_number");
        String full_warn_number = request.getParameter("full_warn_number");
        String decode_warn_number = request.getParameter("decode_warn_number");
        String discard_warn_number = request.getParameter("discard_warn_number");
        String storage_warn_number = request.getParameter("storage_warn_number");
        String replay_warn_number = request.getParameter("replay_warn_number");

        String audit_police_number = request.getParameter("audit_police_number");
        String verity_police_number = request.getParameter("verity_police_number");
        String client_police_number = request.getParameter("client_police_number");
        String admin_police_number = request.getParameter("admin_police_number");
        String build_police_number = request.getParameter("build_police_number");
        String full_police_number = request.getParameter("full_police_number");
        String decode_police_number = request.getParameter("decode_police_number");
        String discard_police_number = request.getParameter("discard_police_number");
        String storage_police_number = request.getParameter("storage_police_number");
        String replay_police_number = request.getParameter("replay_police_number");



        boolean flag = ReportXMLUtils.save(
                audit_warn_number,
                verity_warn_number,
                client_warn_number,
                admin_warn_number,
                build_warn_number,
                full_warn_number,
                decode_warn_number,
                discard_warn_number,
                storage_warn_number,
                replay_warn_number,

                audit_police_number,
                verity_police_number,
                client_police_number,
                admin_police_number,
                build_police_number,
                full_police_number,
                decode_police_number,
                discard_police_number,
                storage_police_number,
                replay_police_number );
        if(flag){
            msg = "审计分析配置保存成功";
            json = "{success:true,msg:'" + msg + "'}";
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "审计分析配置", msg);
        }else {
            msg = "审计分析配置保存失败";
            json = "{success:false,msg:'" + msg + "'}";
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "审计分析配置", msg);
        }
        actionBase.actionEnd(response,json,result);
        return null;
    }

    /**
     * 查找
     * @return
     */
    public String find(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        int totalCount =0;
        String sb = ReportXMLUtils.find();
        totalCount = totalCount+1;
        StringBuilder json=new StringBuilder("{totalCount:"+totalCount+",root:[");
        json.append(sb);
        json.append("]}");
        try {
            actionBase.actionEnd(response,json.toString(),result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
