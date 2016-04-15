package com.hzih.audit.web.servlet;

import com.hzih.audit.domain.SafePolicy;
import com.hzih.audit.myjfree.RunMonitorInfoList;
import com.hzih.audit.myjfree.RunMonitorLiuliangBean2List;
import com.hzih.audit.service.SafePolicyService;
import com.hzih.audit.constant.AppConstant;
import com.hzih.audit.constant.ServiceConstant;
import com.hzih.audit.syslog.revicer.SyslogServer;
import com.hzih.audit.syslog.revicer.SyslogServerXMLUtils;
import com.hzih.audit.utils.ShellUtils;
import com.hzih.audit.web.SiteContext;
import com.hzih.audit.web.thread.SystemStatusService;
import com.inetec.common.util.OSInfo;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.io.IOException;

public class SiteContextLoaderServlet extends DispatcherServlet {
    private Logger logger = Logger.getLogger(SiteContextLoaderServlet.class);

    private static final long serialVersionUID = 1L;


    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        SiteContext.getInstance().contextRealPath = config.getServletContext().getRealPath("/");
        servletContext.setAttribute("appConstant", new AppConstant());
        SafePolicyService service = (SafePolicyService) context.getBean(ServiceConstant.SAFEPOLICY_SERVICE);
        SafePolicy data = service.getData();
        SiteContext.getInstance().safePolicy = data;

        //读取网卡流量
        /*OSInfo osinfo = OSInfo.getOSInfo();
        if (osinfo.isLinux()) {
            new RunMonitorInfoList().start();
            new RunMonitorLiuliangBean2List().start();
        }else{
            new RunMonitorInfoList().start();
            new RunMonitorLiuliangBean2List().start();
        }*/
        OSInfo osinfo = OSInfo.getOSInfo();
        if (osinfo.isLinux()) {
            new SystemStatusService().start();
        }

        // Start SyslogServer
        String host = SyslogServerXMLUtils.getValue(SyslogServerXMLUtils.host);
        String port = SyslogServerXMLUtils.getValue(SyslogServerXMLUtils.port);
        logger.info("syslog server host:" + host + ",port:" + port);
        if (host != null && port != null) {
            SyslogServer syslog = new SyslogServer();
            syslog.config(host, Integer.parseInt(port));
            syslog.start();
            ShellUtils.add_syslog_server("udp", port);
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        // do nothing
        return null;
    }

    @Override
    public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        // do nothing
    }

    @Override
    public String getServletInfo() {
        // do nothing
        return null;
    }

    @Override
    public void destroy() {
        // do nothing
        // Stop SyslogServer
        String host = SyslogServerXMLUtils.getValue(SyslogServerXMLUtils.host);
        String port = SyslogServerXMLUtils.getValue(SyslogServerXMLUtils.port);
        if (host != null && port != null) {
            ShellUtils.del_syslog_server("udp", port);
        }
    }
}
