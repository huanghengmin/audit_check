package com.hzih.audit.utils;

/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-6-7
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
public class StringContext {
    public final static String systemPath = System.getProperty("sslvpn.home");
    public final static String INTERFACE = "/etc/network/interfaces";//linux下IP信息存储文件
    public final static String IFSTATE = "/etc/network/run/ifstate"; //linux下DNS信息
    //    public final static String INTERFACE = systemPath + "/etc/network/interfaces";//linux下IP信息存储文件
//    public final static String IFSTATE = systemPath + "/etc/network/run/ifstate"; //linux下DNS信息
    public final static String localLogPath = systemPath + "/logs";   //日志文件目录
    public final static String webPath = systemPath + "/tomcat/webapps"; //war服务文件存储目录
    public final static String tempPath = systemPath + "/tomcat/temp"; //缓存目录
    public final static String sqlPath = systemPath + "/sql"; //缓存目录
    public final static String mysql_bak_sql = sqlPath + "/mysqlbak.sql";
    public static final String SECURITY_CONFIG = StringContext.systemPath + "/tomcat/conf/server.xml";
    public final static String syslog_xml = StringContext.systemPath + "/config/syslog.xml";
    public final static String storage_xml = StringContext.systemPath + "/config/storage.xml";
    public final static String process_xml = StringContext.systemPath + "/config/process.xml";
    public final static String syslogserver_xml = StringContext.systemPath + "/config/syslogserver.xml";
    public final static String report_xml = StringContext.systemPath + "/config/report.xml";
}
