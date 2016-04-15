package com.hzih.audit.web.action.audit;

import com.hzih.audit.service.LogService;
import com.hzih.audit.web.SessionUtils;
import com.hzih.audit.web.action.ActionBase;
import com.hzih.audit.web.action.config.AlertMessage;
import com.hzih.audit.web.action.config.AlertThread;
import com.hzih.audit.web.action.config.AlertUtils;
import com.hzih.audit.web.action.config.CheckDataBaseUse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Node;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 15-4-23.
 */
public class AuditStorageAction extends ActionSupport {
    private Logger logger = Logger.getLogger(AuditStorageAction.class);
    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public String find() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        int totalCount = 0;
        String s = StorageXMLUtils.find();
        totalCount = totalCount + 1;
        StringBuilder json = new StringBuilder("[");
        json.append(s);
        json.append("]");
        try {
            actionBase.actionEnd(response, json.toString(), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String update() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String msg = "更新失败";
        String slider_backup = request.getParameter("slider_backup");
        if (slider_backup == null)
            slider_backup = "";
        String slider_process = request.getParameter("slider_process");
        if (slider_process == null)
            slider_process = "";
        String local_backup = request.getParameter("local_backup");
        if (local_backup == null)
            local_backup = "";
        String local_backup_path = request.getParameter("local_backup_path");
        if (local_backup_path == null)
            local_backup_path = "";
        String ftp_backup = request.getParameter("ftp_backup");
        if (ftp_backup == null)
            ftp_backup = "";
        String ftp_host = request.getParameter("ftp_host");
        if (ftp_host == null)
            ftp_host = "";
        String ftp_port = request.getParameter("ftp_port");
        if (ftp_port == null)
            ftp_port = "";
        String ftp_user = request.getParameter("ftp_user");
        if (ftp_user == null)
            ftp_user = "";
        String ftp_pass = request.getParameter("ftp_pass");
        if (ftp_pass == null)
            ftp_pass = "";
        String ftp_path = request.getParameter("ftp_path");
        if (ftp_path == null)
            ftp_path = "";
        String backup_flag = request.getParameter("backup_flag");
        if (backup_flag == null)
            backup_flag = "";
        //按日，周，月
        String conf_type = request.getParameter("conf_type");
        if (conf_type == null)
            conf_type = "";
        String conf_time = request.getParameter("conf_time");
        if (conf_time == null)
            conf_time = "";
        String conf_day = request.getParameter("conf_day");
        if (conf_day == null)
            conf_day = "";
        String conf_time2 = request.getParameter("conf_time2");
        if (conf_time2 == null)
            conf_time2 = "";
        String conf_month_day = request.getParameter("conf_month_day");
        if (conf_month_day == null)
            conf_month_day = "";
        String conf_time3 = request.getParameter("conf_time3");
        if (conf_time3 == null)
            conf_time3 = "";
        boolean flag = StorageXMLUtils.save(slider_backup, slider_process, local_backup, local_backup_path, ftp_backup, ftp_host, ftp_port, ftp_user, ftp_pass, ftp_path, backup_flag, conf_type, conf_time, conf_day, conf_time2, conf_month_day, conf_time3);
        if (flag) {
            msg = "保存成功";
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "审计备份策略", "用户更新审计备份策略信息成功");
        } else {
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "审计备份策略", "用户更新审计备份策略信息失败");
            msg = "保存失败";
        }
        CheckDataBaseUse checkDataBaseUse = new CheckDataBaseUse();
        int i = checkDataBaseUse.alertDatabaseUse();
        if (i != -1) {//超出阀值
            if(i==0) {
                logger.info("超出审计阀值,当前处理模式为:忽略审计数据");
                logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "审计备份策略", "超出审计阀值,当前处理模式为:忽略审计数据");
            }else if(i==1) {
                logger.info("超出审计阀值,当前处理模式为:覆盖最老的审计数据");
                logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "审计备份策略", "超出审计阀值,当前处理模式为:覆盖最老的审计数据");
            }
            List<Node> nodeList = AlertUtils.queryEmailAll();
            AlertMessage message = new AlertMessage("SSLVPN审计系统 审计存储阀值已经超出"+slider_process, "请更改审计存储阀值或加大审计系统存储容量！");
            AlertThread thread = new AlertThread(nodeList, message);
            thread.start();
        }
        String json = "{success:true,msg:'" + msg + "'}";
        actionBase.actionEnd(response, json, result);
        return null;
    }
}
