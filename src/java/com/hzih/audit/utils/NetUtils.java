package com.hzih.audit.utils;

import com.inetec.common.exception.Ex;
import com.inetec.common.net.Ping;
import com.inetec.common.net.Telnet;
import com.inetec.common.util.OSInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 13-5-13
 * Time: 上午10:57
 * To change this template use File | Settings | File Templates.
 */
public class NetUtils {
    private static final Logger logger = LoggerFactory.getLogger(NetUtils.class);
    public static boolean isPingOk(String ip,int count) {
        try {
            String pingStr = Ping.exec(ip, count);
            return getResult(pingStr);
        } catch (Ex ex) {
            logger.info("ping "+ip +"错误,时间:"+new Date(),ex);
        }
        return false;
    }

    private static boolean getResult(String pingStr) {
		String result = "";
		OSInfo osInfo = OSInfo.getOSInfo();
		if(osInfo.isLinux()){
			String[] pings = pingStr.split("\n");
			for (int i = 0; i < pings.length; i++) {
				if(i<pings.length - 1){
					result += pings[i].trim()+"<br>";
				}else{
					result += pings[i].trim();
				}
			}
		}else if(osInfo.isWin()){
			String[] pings = pingStr.split("\r\n");
			for (int i = 0; i < pings.length; i++) {
				if(i<pings.length - 1){
					result += pings[i].trim()+"<br>";
				}else{
					result += pings[i].trim();
				}
			}
		}
        if(( result.indexOf("ttl")>-1 && result.indexOf("time")>-1 )
                ||(result.indexOf("bytes=")>-1 && result.indexOf("time")>-1
                                                 && result.indexOf("TTL")>-1)){
            return true;
        } else {
            return false;
        }
	}

    public static boolean isTelnetOk(String ip,int port) {
        boolean isTelnet = false;
        try {
            isTelnet = Telnet.exec(ip, port);
        } catch (Ex ex) {
            logger.info("telnet "+ip +"错误,时间:"+new Date(),ex);
        }
        return isTelnet;
    }
}
