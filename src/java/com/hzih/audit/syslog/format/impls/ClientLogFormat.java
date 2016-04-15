package com.hzih.audit.syslog.format.impls;

import com.hzih.audit.domain.ClientLog;
import com.hzih.audit.syslog.format.ILogFormat;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientLogFormat implements ILogFormat {
    private Logger logger = Logger.getLogger(ClientLogFormat.class);
    private static final String Log_Flag = "logflag=sslvpn";
    private static final String Log_Source = "logsource=client";
    private static final String S_Separator_Keys = ",";
    private static final String S_Separator_KeyValue = "=";
    private String CN;
    private String SN;
    private String O;
    private String OU;
    private String L;
    private String ST;
    private String phone;
    private String idcard;
    private String email;
    private String sourceip;
    private String sourceport;
    private String accessurl;
    private String result;
    private String upbytes;
    private String downbytes;
    private String audittype;
    private String datetime;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getCN() {
        return CN;
    }

    public void setCN(String CN) {
        this.CN = CN;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getO() {
        return O;
    }

    public void setO(String o) {
        O = o;
    }

    public String getOU() {
        return OU;
    }

    public void setOU(String OU) {
        this.OU = OU;
    }

    public String getL() {
        return L;
    }

    public void setL(String l) {
        L = l;
    }

    public String getST() {
        return ST;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSourceip() {
        return sourceip;
    }

    public void setSourceip(String sourceip) {
        this.sourceip = sourceip;
    }

    public String getSourceport() {
        return sourceport;
    }

    public void setSourceport(String sourceport) {
        this.sourceport = sourceport;
    }

    public String getAccessurl() {
        return accessurl;
    }

    public void setAccessurl(String accessurl) {
        this.accessurl = accessurl;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUpbytes() {
        return upbytes;
    }

    public void setUpbytes(String upbytes) {
        this.upbytes = upbytes;
    }

    public String getDownbytes() {
        return downbytes;
    }

    public void setDownbytes(String downbytes) {
        this.downbytes = downbytes;
    }

    public String getAudittype() {
        return audittype;
    }

    public void setAudittype(String audittype) {
        this.audittype = audittype;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public ILogFormat log_process(String log) {
        String[] keyvalues = log.split(S_Separator_Keys);
        for (int i = 0; i < keyvalues.length; i++) {
            if (keyvalues[i].contains("CN=")) {
                CN = keyvalues[i].trim().substring("CN=".length());
                if (CN.endsWith("\""))
                    CN = CN.substring(0, CN.length() - 1);
                if (CN.startsWith("\"")) {
                    CN = CN.substring(1);
                }
            }
            if (keyvalues[i].contains("SN=")) {
                SN = keyvalues[i].trim().substring("SN=".length());
                if (SN.endsWith("\""))
                    SN = SN.substring(0, SN.length() - 1);
                if (SN.startsWith("\"")) {
                    SN = SN.substring(1);
                }
            }
            if (keyvalues[i].contains("O=")) {
                O = keyvalues[i].trim().substring("O=".length());
                if (O.endsWith("\""))
                    O = O.substring(0, O.length() - 1);
                if (O.startsWith("\"")) {
                    O = O.substring(1);
                }
            }
            if (keyvalues[i].contains("OU=")) {
                OU = keyvalues[i].trim().substring("OU=".length());
                if (OU.endsWith("\""))
                    OU = OU.substring(0, OU.length() - 1);
                if (OU.startsWith("\"")) {
                    OU = OU.substring(1);
                }
            }
            if (keyvalues[i].contains("L=")) {
                L = keyvalues[i].trim().substring("L=".length());
                if (L.endsWith("\""))
                    L = L.substring(0, L.length() - 1);
                if (L.startsWith("\"")) {
                    L = L.substring(1);
                }
            }
            if (keyvalues[i].contains("ST=")) {
                ST = keyvalues[i].trim().substring("ST=".length());
                if (ST.endsWith("\""))
                    ST = ST.substring(0, ST.length() - 1);
                if (ST.startsWith("\"")) {
                    ST = ST.substring(1);
                }
            }
            if (keyvalues[i].contains("phone=")) {
                phone = keyvalues[i].trim().substring("phone=".length());
                if (phone.endsWith("\""))
                    phone = phone.substring(0, phone.length() - 1);
                if (phone.startsWith("\"")) {
                    phone = phone.substring(1);
                }
            }
            if (keyvalues[i].contains("idcard=")) {
                idcard = keyvalues[i].trim().substring("idcard=".length());
                if (idcard.endsWith("\""))
                    idcard = idcard.substring(0, idcard.length() - 1);
                if (idcard.startsWith("\"")) {
                    idcard = idcard.substring(1);
                }
            }
            if (keyvalues[i].contains("email=")) {
                email = keyvalues[i].trim().substring("email=".length());
                if (email.endsWith("\""))
                    email = email.substring(0, email.length() - 1);
                if (email.startsWith("\"")) {
                    email = email.substring(1);
                }
            }
            if (keyvalues[i].contains("sourceip=")) {
                sourceip = keyvalues[i].trim().substring("sourceip=".length());
                if (sourceip.endsWith("\""))
                    sourceip = sourceip.substring(0, sourceip.length() - 1);
                if (sourceip.startsWith("\"")) {
                    sourceip = sourceip.substring(1);
                }
            }
            if (keyvalues[i].contains("sourceport=")) {
                sourceport = keyvalues[i].trim().substring("sourceport=".length());
                if (sourceport.endsWith("\""))
                    sourceport = sourceport.substring(0, sourceport.length() - 1);
                if (sourceport.startsWith("\"")) {
                    sourceport = sourceport.substring(1);
                }
            }
            if (keyvalues[i].contains("accessurl=")) {
                accessurl = keyvalues[i].trim().substring("accessurl=".length());
                if (accessurl.endsWith("\""))
                    accessurl = accessurl.substring(0, accessurl.length() - 1);
                if (accessurl.startsWith("\"")) {
                    accessurl = accessurl.substring(1);
                }
            }
            if (keyvalues[i].contains("result=")) {
                result = keyvalues[i].trim().substring("result=".length());
                if (result.endsWith("\""))
                    result = result.substring(0, result.length() - 1);
                if (result.startsWith("\"")) {
                    result = result.substring(1);
                }
            }
            if (keyvalues[i].contains("upbytes=")) {
                upbytes = keyvalues[i].trim().substring("upbytes=".length());
                if (upbytes.endsWith("\""))
                    upbytes = upbytes.substring(0, upbytes.length() - 1);
                if (upbytes.startsWith("\"")) {
                    upbytes = upbytes.substring(1);
                }
            }
            if (keyvalues[i].contains("downbytes=")) {
                downbytes = keyvalues[i].trim().substring("downbytes=".length());
                if (downbytes.endsWith("\""))
                    downbytes = downbytes.substring(0, downbytes.length() - 1);
                if (downbytes.startsWith("\"")) {
                    downbytes = downbytes.substring(1);
                }
            }
            if (keyvalues[i].contains("audittype=")) {
                audittype = keyvalues[i].trim().substring("audittype=".length());
                if (audittype.endsWith("\""))
                    audittype = audittype.substring(0, audittype.length() - 1);
                if (audittype.startsWith("\"")) {
                    audittype = audittype.substring(1);
                }
            }
            if (keyvalues[i].contains("datetime=")) {
                datetime = keyvalues[i].trim().substring("datetime=".length());
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
      return   log_process(log);
    }


    public ClientLog convert() {
        ClientLog clientLog = new ClientLog();
        clientLog.setAudittype(this.getAudittype());
        clientLog.setCN(this.getCN());
        clientLog.setSN(this.getSN());
        clientLog.setO(this.getO());
        clientLog.setOU(this.getOU());
        clientLog.setL(this.getL());
        clientLog.setST(this.getST());
        clientLog.setPhone(this.getPhone());
        clientLog.setIdcard(this.getIdcard());
        clientLog.setEmail(this.getEmail());
        clientLog.setSourceip(this.getSourceip());
        clientLog.setSourceport(this.getSourceport());
        clientLog.setAccessurl(this.getAccessurl());
        clientLog.setResult(this.getResult());
        clientLog.setUpbytes(this.getUpbytes());
        clientLog.setDownbytes(this.getDownbytes());
        clientLog.setAudittype(this.getAudittype());
        String datetime = this.getDatetime();
        try {
            Date date= format.parse(datetime);
            clientLog.setDatetime(new Timestamp(date.getTime()));
            return clientLog;
        } catch (ParseException e) {
            logger.error("ClientLog parse datetime error:"+e.getMessage(),e);
        }
        return clientLog;
    }

    public boolean validate(String log) {
        boolean result = false;
        if (log.contains(Log_Flag) && log.contains(Log_Source))
            result = true;
        return result;
    }
}
