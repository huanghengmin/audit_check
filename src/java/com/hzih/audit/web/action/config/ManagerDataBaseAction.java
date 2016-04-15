package com.hzih.audit.web.action.config;

import com.hzih.audit.constant.AppConstant;
import com.hzih.audit.service.LogService;
import com.hzih.audit.utils.StringContext;
import com.hzih.audit.web.SessionUtils;
import com.hzih.audit.web.SiteContext;
import com.hzih.audit.web.action.ActionBase;
import com.inetec.common.util.OSInfo;
import com.inetec.common.util.Proc;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;

/**
 * 审计库管理、审计备份策略
 */
public class ManagerDataBaseAction extends ActionSupport {

    private static final Logger logger = LoggerFactory.getLogger(ManagerDataBaseAction.class);
    private LogService logService;

    /**
     * 读取/pages/data/db-config.xml
     *
     * @return
     * @throws Exception
     */
    public String select() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = null;
        try {
            SAXReader reader = new SAXReader();
            String fileName = SiteContext.getInstance().contextRealPath + AppConstant.XML_DB_CONFIG_PATH;
            Document doc = reader.read(new File(fileName));
            String dbIp = doc.selectSingleNode("//config/maindb/dbip").getText();
            String dbPort = doc.selectSingleNode("//config/maindb/dbport").getText();
            String dbName = doc.selectSingleNode("//config/maindb/dbname").getText();
            String userName = doc.selectSingleNode("//config/maindb/username").getText();
            String password = doc.selectSingleNode("//config/maindb/password").getText();
            int dbUsed = Integer.parseInt(doc.selectSingleNode("//config/maindb/dbused").getText());
            String backupDbIp = doc.selectSingleNode("//config/backupdb/dbip").getText();
            String backupDbPort = doc.selectSingleNode("//config/backupdb/dbport").getText();
            String backupDbName = doc.selectSingleNode("//config/backupdb/dbname").getText();
            String backupUserName = doc.selectSingleNode("//config/backupdb/username").getText();
            String backupPassword = doc.selectSingleNode("//config/backupdb/password").getText();
            String sysDBName = "information_schema";
            int disk = 4;
            Proc proc;
            OSInfo osinfo = OSInfo.getOSInfo();
            if (osinfo.isWin()) {
            }
            if (osinfo.isLinux()) {
                String dataDir = null;
                Connection conn = null;
                Statement stat = null;
                ResultSet rs = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://" + dbIp + ":" + dbPort + "/" + sysDBName + "?useUnicode=true&characterEncoding=utf-8";
                    conn = DriverManager.getConnection(url, userName, password);
                    stat = conn.createStatement();
                    rs = stat.executeQuery("select VARIABLE_VALUE from `GLOBAL_VARIABLES` where VARIABLE_NAME = 'datadir';");
                    if (rs != null && rs.next()) {
                        dataDir = rs.getString(1);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(),e);
                } finally {
                    try {
                        if (null != rs) {
                            rs.close();
                            rs = null;
                        }
                        if (null != stat) {
                            stat.close();
                            stat = null;
                        }
                        if (null != conn) {
                            conn.close();
                            conn = null;
                        }
                    } catch (SQLException e) {
                        logger.error(e.getMessage(),e);
                    }
                }
                proc = new Proc();
                proc.exec("df " + dataDir);
                String _result = proc.getOutput();
                String[] lines = _result.split("\n");
                String[] str = lines[1].split("\\s+");
                int MB = 1024 * 1024;
                disk = Integer.parseInt(str[1]) / MB;
            }

            json = "[{dbip:'" + dbIp + "',dbport:'" + dbPort + "',dbname:'" + dbName +
                    "',username:'" + userName + "',dbUsed:" + dbUsed + ",_disk:" + disk + ",disk:'" + disk +
                    " GB',backup_dbip:'" + backupDbIp + "',backup_dbport:'" + backupDbPort +
                    "',backup_dbname:'" + backupDbName + "',backup_username:'" + backupUserName + "'}]";
//            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "审计库管理", "用户查找审计库信息成功");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "审计库管理", "用户查找审计库信息失败");
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    /**
     * 更新/pages/data/db-config.xml
     *
     * @return
     * @throws Exception
     */
    public String update() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        SAXReader reader = new SAXReader();
        String fileName = SiteContext.getInstance().contextRealPath + AppConstant.XML_DB_CONFIG_PATH;
        Document doc = reader.read(new File(fileName));
        String msg = null;
        XMLWriter writer = null;
        try {
            Node tempNode = doc.selectSingleNode("//config/maindb/dbused");
            tempNode.setText(request.getParameter("dbUsed"));
            writer = new XMLWriter(new FileWriter(fileName));// 构建输出流
            writer.write(doc);// 输出XML
            msg = "保存成功";
            logService.newLog("INFO", SessionUtils.getAccount(request)
                    .getUserName(), "审计库管理", "用户更新审计库信息成功");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
                    .getUserName(), "审计库管理", "用户更新审计库信息失败");
            msg = "保存失败";
        } finally {
            writer.close();
        }

        String json = "{success:true,msg:'" + msg + "'}";
        actionBase.actionEnd(response, json, result);
        return null;
    }

    /**
     * 备份策略
     *
     * @return
     * @throws Exception
     */
    public String selectBackup() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = null;
        try {
            SAXReader reader = new SAXReader();
            String fileName = SiteContext.getInstance().contextRealPath + AppConstant.BACKUP_CONFIG_PATH;
            Document doc = reader.read(new File(fileName));
            String conf_type = doc.selectSingleNode("//backup/conf_type").getText();
            String conf_time = doc.selectSingleNode("//backup/conf_time").getText();
            String conf_day = doc.selectSingleNode("//backup/conf_day").getText();
            String conf_time2 = doc.selectSingleNode("//backup/conf_time2").getText();
            String conf_month_day = doc.selectSingleNode("//backup/conf_month_day").getText();
            String conf_time3 = doc.selectSingleNode("//backup/conf_time3").getText();
            String conf_file_path = doc.selectSingleNode("//backup/conf_file_path").getText();
            String conf_enabled = doc.selectSingleNode("//backup/conf_enabled").getText();
            String conf_maxfiles = doc.selectSingleNode("//backup/conf_maxfiles").getText();
            String log_type = doc.selectSingleNode("//backup/log_type").getText();
            String log_time = doc.selectSingleNode("//backup/log_time").getText();
            String log_day = doc.selectSingleNode("//backup/log_day").getText();
            String log_time2 = doc.selectSingleNode("//backup/log_time2").getText();
            String log_month_day = doc.selectSingleNode("//backup/log_month_day").getText();
            String log_time3 = doc.selectSingleNode("//backup/log_time3").getText();

            json = "[{conf_type:'" + conf_type + "',conf_time:'" + conf_time + "',conf_day:'" + conf_day +
                    "',conf_time2:'" + conf_time2 + "',conf_month_day:'" + conf_month_day +
                    "',conf_time3:'" + conf_time3 + "',conf_file_path:'" + conf_file_path +
                    "',conf_enabled:'" + conf_enabled + "',conf_maxfiles:'" + conf_maxfiles +
                    "',log_type:'" + log_type + "',log_time:'" + log_time + "',log_day:'" + log_day +
                    "',log_time2:'" + log_time2 + "',log_month_day:'" + log_month_day +
                    "',log_time3:'" + log_time3 + "'}]";
            logService.newLog("INFO", SessionUtils.getAccount(request)
                    .getUserName(), "审计备份策略", "用户显示审计备份策略成功");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
                    .getUserName(), "审计备份策略", "用户显示审计备份策略失败");
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }


    /**
     * 更新/pages/data/backup-config.xml
     *
     * @return
     * @throws Exception
     */
    public String updateBackup() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        SAXReader reader = new SAXReader();
        String fileName = SiteContext.getInstance().contextRealPath + AppConstant.BACKUP_CONFIG_PATH;
        Document doc = reader.read(new File(fileName));
        String msg = null;
        XMLWriter writer = null;
        try {
            Node tempNode = doc.selectSingleNode("//backup/conf_type");
            tempNode.setText(request.getParameter("conf_type"));
            tempNode = doc.selectSingleNode("//backup/conf_time");
            tempNode.setText(request.getParameter("conf_time"));
            tempNode = doc.selectSingleNode("//backup/conf_day");
            tempNode.setText(request.getParameter("conf_day"));
            tempNode = doc.selectSingleNode("//backup/conf_time2");
            tempNode.setText(request.getParameter("conf_time2"));
            tempNode = doc.selectSingleNode("//backup/conf_month_day");
            tempNode.setText(request.getParameter("conf_month_day"));
            tempNode = doc.selectSingleNode("//backup/conf_time3");
            tempNode.setText(request.getParameter("conf_time3"));
            tempNode = doc.selectSingleNode("//backup/conf_file_path");
            tempNode.setText(request.getParameter("conf_file_path"));
            tempNode = doc.selectSingleNode("//backup/conf_enabled");
            tempNode.setText(ServletRequestUtils.getStringParameter(request, "conf_enabled", "0"));
            tempNode = doc.selectSingleNode("//backup/log_type");
            tempNode.setText(request.getParameter("log_type"));
            tempNode = doc.selectSingleNode("//backup/log_time");
            tempNode.setText(request.getParameter("log_time"));
            tempNode = doc.selectSingleNode("//backup/log_day");
            tempNode.setText(request.getParameter("log_day"));
            tempNode = doc.selectSingleNode("//backup/log_time2");
            tempNode.setText(request.getParameter("log_time2"));
            tempNode = doc.selectSingleNode("//backup/log_month_day");
            tempNode.setText(request.getParameter("log_month_day"));
            tempNode = doc.selectSingleNode("//backup/log_time3");
            tempNode.setText(request.getParameter("log_time3"));
            writer = new XMLWriter(new FileWriter(fileName));// 构建输出流
            writer.write(doc);// 输出XML
            msg = "保存成功";
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "审计备份策略", "用户更新审计备份策略信息成功");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "审计备份策略", "用户更新审计备份策略信息失败");
            msg = "保存失败";
        } finally {
            writer.close();
        }
        String json = "{success:true,msg:'" + msg + "'}";
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
