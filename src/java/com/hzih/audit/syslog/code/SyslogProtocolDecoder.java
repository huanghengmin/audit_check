package com.hzih.audit.syslog.code;

import com.hzih.audit.syslog.revicer.SyslogMessage;
import com.inetec.common.config.nodes.IpMac;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;

public class SyslogProtocolDecoder implements ProtocolDecoder {
//    final static Logger logger = Logger.getLogger(SyslogProtocolDecoder.class);
//    private static final AttributeKey BUF_BYTE = new AttributeKey(SyslogProtocolDecoder.class, "syslog");
    private String charset="UTF-8";

    public SyslogProtocolDecoder() {
    }

    public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        try {
            IoBuffer bufTmp = ioBuffer;
            while (bufTmp.remaining() >= 4) {
                String data = bufTmp.getString(Charset.forName(charset).newDecoder());
                int n = data.indexOf(">");
                SyslogMessage syslogMessage = new SyslogMessage();
                if (n > 0) {
                    int facility = SyslogMessage.extractFacility(Integer.parseInt(data.substring(1, n)));
                    int priority = SyslogMessage.extractPriority(Integer.parseInt(data.substring(1, n)));
//                    int serverty = SyslogMessage.serverty(priority, facility);
                    syslogMessage.setServerty(priority);
                    syslogMessage.setFacility(facility);
//                    syslogMessage.setServerty(serverty);
                    int msgn = data.indexOf("]:", n) + 2;
                    if (!data.matches("]:"))
                        msgn = n + 1;
                    syslogMessage.setHostName(IpMac.getMinaRemoteIp(ioSession.getRemoteAddress().toString()));
                    syslogMessage.setMessage(data.substring(msgn));
                } else {
//                    logger.warn("Syslog format is error:" + data);
                }
                /* ProtobufResponse pak = new ProtobufResponse();
               pak.setResByteLen(dataLen);
               pak.readFrom(b, 4);*/
                protocolDecoderOutput.write(syslogMessage);
            }
            bufTmp.free();
        } catch (Exception ex) {
            ex.printStackTrace();
//            logger.warn("Tcp Server decode data Exception:" + ex.getMessage());
        }
    }

    public void dispose(IoSession session) throws Exception {
//        System.out.println("dispose");
//        logger.info("dispose");
    }

    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
//        System.out.println("finishDecode");
//        logger.info("finishDecode");
    }
}