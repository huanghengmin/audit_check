package com.hzih.audit.syslog.code;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class SyslogCodecFactory implements ProtocolCodecFactory {

    public SyslogCodecFactory() {
    }

    ProtocolDecoder decoder = new SyslogProtocolDecoder();

    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return null;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}