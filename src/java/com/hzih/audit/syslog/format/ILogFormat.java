package com.hzih.audit.syslog.format;

public interface ILogFormat {

    public ILogFormat process(String log);

    public boolean validate(String log);
}
