package com.hzih.audit.syslog.format.impls;

import com.hzih.audit.domain.AccountLog;
import com.hzih.audit.syslog.format.ILogFormat;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountLogFormat implements ILogFormat {
    private Logger logger = Logger.getLogger(AccountLogFormat.class);
    private static final String Log_Flag = "logflag=sslvpn";
    private static final String Log_Source = "logsource=account";
    private static final String S_Separator_Keys = " ";
    private static final String S_Separator_KeyValue = "=";
    private String account;//操作的管理员
    private String action;//动作及返回状态
    private String auditmodel;//审计模块名称
    private String auditlevel;//审计级别
    private String audittype;//审计类别
    private String result;
    private String datetime;//日期时间
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAuditmodel() {
        return auditmodel;
    }

    public void setAuditmodel(String auditmodel) {
        this.auditmodel = auditmodel;
    }

    public String getAuditlevel() {
        return auditlevel;
    }

    public void setAuditlevel(String auditlevel) {
        this.auditlevel = auditlevel;
    }

    public String getAudittype() {
        return audittype;
    }

    public void setAudittype(String audittype) {
        this.audittype = audittype;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public ILogFormat log_process(String log) {String[] keyvalues = log.split(S_Separator_Keys);
        for (int i = 0; i < keyvalues.length; i++) {
            if (keyvalues[i].contains("account=")) {
                account = keyvalues[i].substring("account=".length());
                if (account.endsWith("\""))
                    account = account.substring(0, account.length() - 1);
                if (account.startsWith("\"")) {
                    account = account.substring(1);
                }
            }

            if (keyvalues[i].contains("action=")) {
                action = keyvalues[i].substring("action=".length());
                if (action.endsWith("\""))
                    action = action.substring(0, action.length() - 1);
                if (action.startsWith("\"")) {
                    action = action.substring(1);
                }
            }

            if (keyvalues[i].contains("auditmodel=")) {
                auditmodel = keyvalues[i].substring("auditmodel=".length());
                if (auditmodel.endsWith("\""))
                    auditmodel = auditmodel.substring(0, auditmodel.length() - 1);
                if (auditmodel.startsWith("\"")) {
                    auditmodel = auditmodel.substring(1);
                }
            }

            if (keyvalues[i].contains("auditlevel=")) {
                auditlevel = keyvalues[i].substring("auditlevel=".length());
                if (auditlevel.endsWith("\""))
                    auditlevel = auditlevel.substring(0, auditlevel.length() - 1);
                if (auditlevel.startsWith("\"")) {
                    auditlevel = auditlevel.substring(1);
                }
            }

            if (keyvalues[i].contains("audittype=")) {
                audittype = keyvalues[i].substring("audittype=".length());
                if (audittype.endsWith("\""))
                    audittype = audittype.substring(0, audittype.length() - 1);
                if (audittype.startsWith("\"")) {
                    audittype = audittype.substring(1);
                }
            }

            if (keyvalues[i].contains("result=")) {
                result = keyvalues[i].substring("result=".length());
                if (result.endsWith("\""))
                    result = result.substring(0, result.length() - 1);
                if (result.startsWith("\"")) {
                    result = result.substring(1);
                }
            }

            if (keyvalues[i].contains("datetime=")) {
                datetime = keyvalues[i].substring("datetime=".length());
                if (datetime.endsWith("\""))
                    datetime = datetime.substring(0, datetime.length() - 1);
                if (datetime.startsWith("\"")) {
                    datetime = datetime.substring(1);
                }
            }
        }
        return this;
    }

    public ILogFormat process(String log) {
        return log_process(log);
    }

    public AccountLog convert() {
        AccountLog accountLog = new AccountLog();
        accountLog.setAccount(this.getAccount());
        accountLog.setAction(this.getAction());
        accountLog.setAuditlevel(this.getAuditlevel());
        accountLog.setAuditmodel(this.getAuditmodel());
        accountLog.setAudittype(this.getAudittype());
        accountLog.setResult(this.getResult());
        String datetime = this.getDatetime();
        try {
            Date date= format.parse(datetime);
            accountLog.setDatetime(new Timestamp(date.getTime()));
            return accountLog;
        } catch (ParseException e) {
            logger.error("AccountLog parse datetime error:"+e.getMessage(),e);
        }
        return null;
    }

    public boolean validate(String log) {
        boolean result = false;
        if (log.contains(Log_Flag) && log.contains(Log_Source))
            result = true;
        return result;
    }
}
