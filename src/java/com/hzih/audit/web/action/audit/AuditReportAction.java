package com.hzih.audit.web.action.audit;

import com.hzih.audit.domain.AccountLog;
import com.hzih.audit.domain.ClientLog;
import com.hzih.audit.service.LogService;
import com.hzih.audit.dao.AccountLogDao;
import com.hzih.audit.dao.ClientLogDao;
import com.hzih.audit.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 15-4-23.
 */
public class AuditReportAction extends ActionSupport {
    private LogService logService;
    private AccountLogDao accountLogDao;
    private ClientLogDao clientLogDao;

    public AccountLogDao getAccountLogDao() {
        return accountLogDao;
    }

    public void setAccountLogDao(AccountLogDao accountLogDao) {
        this.accountLogDao = accountLogDao;
    }

    public ClientLogDao getClientLogDao() {
        return clientLogDao;
    }

    public void setClientLogDao(ClientLogDao clientLogDao) {
        this.clientLogDao = clientLogDao;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public String getReportCount() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        StringBuilder str = new StringBuilder("{success:true,total:10,rows:[");
        List<AccountLog> account001Logs = accountLogDao.findByCode("001");
        int account001Logs_warn = 0;
        int account001Logs_police = 0;
        String audit_warn_number = ReportXMLUtils.getValue(ReportXMLUtils.audit_warn_number);
        String audit_police_number = ReportXMLUtils.getValue(ReportXMLUtils.audit_police_number);
        if(audit_warn_number!=null)
            account001Logs_warn = Integer.parseInt(audit_warn_number);
        if(audit_police_number!=null)
            account001Logs_police = Integer.parseInt(audit_police_number);

        List<AccountLog> account004Logs = accountLogDao.findByCode("004");
        int account004Logs_warn = 0;
        int account004Logs_police = 0;
        String admin_warn_number = ReportXMLUtils.getValue(ReportXMLUtils.admin_warn_number);
        String admin_police_number = ReportXMLUtils.getValue(ReportXMLUtils.admin_police_number);
        if(admin_warn_number!=null)
            account004Logs_warn = Integer.parseInt(admin_warn_number);
        if(admin_police_number!=null)
            account004Logs_police = Integer.parseInt(admin_police_number);

        List<AccountLog> account010Logs = accountLogDao.findByCode("010");
        int account010Logs_warn = 0;
        int account010Logs_police = 0;
        String storage_warn_number = ReportXMLUtils.getValue(ReportXMLUtils.storage_warn_number);
        String storage_police_number = ReportXMLUtils.getValue(ReportXMLUtils.storage_police_number);
        if(storage_warn_number!=null)
            account010Logs_warn = Integer.parseInt(storage_warn_number);
        if(storage_police_number!=null)
            account010Logs_police = Integer.parseInt(storage_police_number);

        List<ClientLog> client002Logs = clientLogDao.findByCode("002");
        int account002Logs_warn = 0;
        int account002Logs_police = 0;
        String verity_warn_number = ReportXMLUtils.getValue(ReportXMLUtils.verity_warn_number);
        String verity_police_number = ReportXMLUtils.getValue(ReportXMLUtils.verity_police_number);
        if(verity_warn_number!=null)
            account002Logs_warn = Integer.parseInt(verity_warn_number);
        if(verity_police_number!=null)
            account002Logs_police = Integer.parseInt(verity_police_number);

        List<ClientLog> client003Logs = clientLogDao.findByCode("003");
        int account003Logs_warn = 0;
        int account003Logs_police = 0;
        String client_warn_number = ReportXMLUtils.getValue(ReportXMLUtils.client_warn_number);
        String client_police_number = ReportXMLUtils.getValue(ReportXMLUtils.client_police_number);
        if(client_warn_number!=null)
            account003Logs_warn = Integer.parseInt(client_warn_number);
        if(client_police_number!=null)
            account003Logs_police = Integer.parseInt(client_police_number);

        List<ClientLog> client005Logs = clientLogDao.findByCode("005");
        int account005Logs_warn = 0;
        int account005Logs_police = 0;
        String build_warn_number = ReportXMLUtils.getValue(ReportXMLUtils.build_warn_number);
        String build_police_number = ReportXMLUtils.getValue(ReportXMLUtils.build_police_number);
        if(build_warn_number!=null)
            account005Logs_warn = Integer.parseInt(build_warn_number);
        if(build_police_number!=null)
            account005Logs_police = Integer.parseInt(build_police_number);

        /*List<ClientLog> client006Logs = clientLogDao.findByCode("006");
        int account006Logs_warn = 2;
        int account006Logs_police = 8;*/
        List<ClientLog> client007Logs = clientLogDao.findByCode("007");
        int account007Logs_warn = 0;
        int account007Logs_police = 0;
        String full_warn_number = ReportXMLUtils.getValue(ReportXMLUtils.full_warn_number);
        String full_police_number = ReportXMLUtils.getValue(ReportXMLUtils.full_police_number);
        if(full_warn_number!=null)
            account007Logs_warn = Integer.parseInt(full_warn_number);
        if(full_police_number!=null)
            account007Logs_police = Integer.parseInt(full_police_number);

        List<ClientLog> client008Logs = clientLogDao.findByCode("008");
        int account008Logs_warn = 0;
        int account008Logs_police = 0;
        String decode_warn_number = ReportXMLUtils.getValue(ReportXMLUtils.decode_warn_number);
        String decode_police_number = ReportXMLUtils.getValue(ReportXMLUtils.decode_police_number);
        if(decode_warn_number!=null)
            account008Logs_warn = Integer.parseInt(decode_warn_number);
        if(decode_police_number!=null)
            account008Logs_police = Integer.parseInt(decode_police_number);

        List<ClientLog> client009Logs = clientLogDao.findByCode("009");
        int account009Logs_warn = 0;
        int account009Logs_police = 0;
        String discard_warn_number = ReportXMLUtils.getValue(ReportXMLUtils.discard_warn_number);
        String discard_police_number = ReportXMLUtils.getValue(ReportXMLUtils.discard_police_number);
        if(discard_warn_number!=null)
            account009Logs_warn = Integer.parseInt(discard_warn_number);
        if(discard_police_number!=null)
            account009Logs_police = Integer.parseInt(discard_police_number);

        List<ClientLog> client011Logs = clientLogDao.findByCode("011");
        int account011Logs_warn = 0;
        int account011Logs_police = 0;
        String replay_warn_number = ReportXMLUtils.getValue(ReportXMLUtils.replay_warn_number);
        String replay_police_number = ReportXMLUtils.getValue(ReportXMLUtils.replay_police_number);
        if(replay_warn_number!=null)
            account011Logs_warn = Integer.parseInt(replay_warn_number);
        if(replay_police_number!=null)
            account011Logs_police = Integer.parseInt(replay_police_number);


        if (account001Logs != null && account001Logs.size() > 0) {
            str.append("{");
            str.append("'name':'审计开启与关闭','warn_count':'"+account001Logs_warn+"','police_count':'"+account001Logs_police+"','count':" + account001Logs.size() + ",'code':'001'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'审计开启与关闭','warn_count':'"+account001Logs_warn+"','police_count':'"+account001Logs_police+"','count':0,'code':'001'");
            str.append("},");
        }

        if (client002Logs != null && client002Logs.size() > 0) {
            str.append("{");
            str.append("'name':'鉴别失败','warn_count':'"+account002Logs_warn+"','police_count':'"+account002Logs_police+"','count':" + client002Logs.size() + ",'code':'002'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'鉴别失败','warn_count':'"+account002Logs_warn+"','police_count':'"+account002Logs_police+"','count':0,'code':'002'");
            str.append("},");
        }

        if (client003Logs != null && client003Logs.size() > 0) {
            str.append("{");
            str.append("'name':'终端一般操作','warn_count':'"+account003Logs_warn+"','police_count':'"+account003Logs_police+"','count':" + client003Logs.size() + ",'code':'003'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'终端一般操作','warn_count':'"+account003Logs_warn+"','police_count':'"+account003Logs_police+"','count':0,'code':'003'");
            str.append("},");
        }

        if (account004Logs != null && account004Logs.size() > 0) {
            str.append("{");
            str.append("'name':'管理员操作','warn_count':'"+account004Logs_warn+"','police_count':'"+account004Logs_police+"','count':" + account004Logs.size() + ",'code':'004'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'管理员操作','warn_count':'"+account004Logs_warn+"','police_count':'"+account004Logs_police+"','count':0,'code':'004'");
            str.append("},");
        }


        if (client005Logs != null && client005Logs.size() > 0) {
            str.append("{");
            str.append("'name':'隧道建立删除','warn_count':'"+account005Logs_warn+"','police_count':'"+account005Logs_police+"','count':" + client005Logs.size() + ",'code':'005'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'隧道建立删除','warn_count':'"+account005Logs_warn+"','police_count':'"+account005Logs_police+"','count':0,'code':'005'");
            str.append("},");
        }

      /*  if (client006Logs != null && client006Logs.size() > 0) {
            str.append("{");
            str.append("'name':'同一隧道建立删除','warn_count':'"+account006Logs_warn+"','police_count':'"+account006Logs_police+"','count':" + client006Logs.size() + ",'code':'006'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'同一隧道建立删除','warn_count':'"+account006Logs_warn+"','police_count':'"+account006Logs_police+"','count':0,'code':'006'");
            str.append("},");
        }*/

        if (client007Logs != null && client007Logs.size() > 0) {
            str.append("{");
            str.append("'name':'完整性校验失败','warn_count':'"+account007Logs_warn+"','police_count':'"+account007Logs_police+"','count':" + client007Logs.size() + ",'code':'007'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'完整性校验失败','warn_count':'"+account007Logs_warn+"','police_count':'"+account007Logs_police+"','count':0,'code':'007'");
            str.append("},");
        }

        if (client008Logs != null && client008Logs.size() > 0) {
            str.append("{");
            str.append("'name':'数据解密失败','warn_count':'"+account008Logs_warn+"','police_count':'"+account008Logs_police+"','count':" + client008Logs.size() + ",'code':'008'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'数据解密失败','warn_count':'"+account008Logs_warn+"','police_count':'"+account008Logs_police+"','count':0,'code':'008'");
            str.append("},");
        }

        if (client009Logs != null && client009Logs.size() > 0) {
            str.append("{");
            str.append("'name':'包被丢弃事件','warn_count':'"+account009Logs_warn+"','police_count':'"+account009Logs_police+"','count':" + client009Logs.size() + ",'code':'009'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'包被丢弃事件','warn_count':'"+account009Logs_warn+"','police_count':'"+account009Logs_police+"','count':0,'code':'009'");
            str.append("},");
        }

        if (account010Logs != null && account010Logs.size() > 0) {
            str.append("{");
            str.append("'name':'日志存储失败','warn_count':'"+account010Logs_warn+"','police_count':'"+account010Logs_police+"','count':" + account010Logs.size() + ",'code':'010'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'日志存储失败','warn_count':'"+account010Logs_warn+"','police_count':'"+account010Logs_police+"','count':0,'code':'010'");
            str.append("},");
        }

        if (client011Logs != null && client011Logs.size() > 0) {
            str.append("{");
            str.append("'name':'重放数据攻击','warn_count':'"+account011Logs_warn+"','police_count':'"+account011Logs_police+"','count':" + client011Logs.size() + ",'code':'011'");
            str.append("}");
        } else {
            str.append("{");
            str.append("'name':'重放数据攻击','warn_count':'"+account011Logs_warn+"','police_count':'"+account011Logs_police+"','count':0,'code':'011'");
            str.append("}");
        }
        str.append("]}");
        actionBase.actionEnd(response, str.toString(), result);
        return null;
    }
}
