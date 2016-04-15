package com.hzih.audit.syslog.factory;

import com.hzih.audit.syslog.format.ILogFormat;
import com.hzih.audit.syslog.format.impls.AccountLogFormat;
import com.hzih.audit.syslog.format.impls.ClientLogFormat;

public class LogFormatFactory {
    private static AccountLogFormat accountLog = new AccountLogFormat();
    private static ClientLogFormat clientLog = new ClientLogFormat();

    public static ILogFormat getLogFormat(String log) {
        ILogFormat result = null;
        if(accountLog.validate(log)){
            result = accountLog.process(log);
        }else if(clientLog.validate(log)){
           result =  clientLog.process(log);
        }
        return result;
    }
}
