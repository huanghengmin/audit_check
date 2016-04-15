package com.hzih.audit.utils;

import com.inetec.common.util.OSInfo;
import com.inetec.common.util.Proc;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 15-5-5.
 */
public class ShellUtils {
    private static Logger logger = Logger.getLogger(ShellUtils.class);
    /**
     * 添加syslog_server
     * @param protocol
     * @param port
     * @return
     */
    public static boolean add_syslog_server(String protocol,String port) {
        Proc proc = new Proc();
        String command = null;
        logger.info("新增 syslog_server port:"+port+",protocol:"+protocol);
        if (OSInfo.getOSInfo().isWin()) {
            command = StringContext.systemPath + "/script/syslog_server_start.bat " +
                    protocol + " " +
                    port;
        } else {
            command = StringContext.systemPath + "/script/syslog_server_start.sh " +
                    protocol + " " +
                    port;
        }
        proc.exec(command);
        if (proc.getResultCode() != -1) {
            if(!proc.getErrorOutput().contains("error")&&!proc.getErrorOutput().contains("Error")){
                return true;
            } else {
                logger.equals(proc.getErrorOutput());
            }
        }
        return false;
    }


    /**
     * 删除 syslog_server
     * @param protocol
     * @param port
     * @return
     */
    public static boolean del_syslog_server(String protocol,String port) {
        Proc proc = new Proc();
        String command = null;
        logger.info("删除 syslog_server port:"+port+",protocol:"+protocol);
        if (OSInfo.getOSInfo().isWin()) {
            command = StringContext.systemPath + "/script/syslog_server_stop.bat " +
                    protocol + " " +
                    port;
        } else {
            command = StringContext.systemPath + "/script/syslog_server_stop.sh " +
                    protocol + " " +
                    port;
        }
        proc.exec(command);
        if (proc.getResultCode() != -1) {
            if(!proc.getErrorOutput().contains("error")&&!proc.getErrorOutput().contains("Error")){
                return true;
            } else {
                logger.equals(proc.getErrorOutput());
            }
        }
        return false;
    }



}
