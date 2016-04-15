package com.hzih.audit.syslog.revicer;

import com.hzih.audit.utils.StringContext;
import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

public class SyslogServerXMLUtils {
    private static Logger logger = Logger.getLogger(SyslogServerXMLUtils.class);
    public static final String syslogserver = "syslogserver";
    public static final String host = "host";
    public static final String port = "port";
    public static final String charset = "utf-8";


    /**
     * @param name
     * @return
     */
    public static String getValue(String name) {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        String result = null;
        try {
            File f = new File(StringContext.syslogserver_xml);
            if(f.exists()) {
                doc = saxReader.read(f);
            }
        } catch (DocumentException e) {
            logger.error(""+e.getMessage(),e);
        }
        if (doc != null) {
            Element ldap = doc.getRootElement();
            result =  ldap.attributeValue(name);
        }
        return result;
    }

    /**
     * @param host
     * @param port
     */
    public static boolean save(String host, String port) {
        boolean flag = false;
        Document doc = DocumentHelper.createDocument();
        Element syslogserver = doc.addElement(SyslogServerXMLUtils.syslogserver);
        Attribute host_attrs = syslogserver.attribute(SyslogServerXMLUtils.host);
        Attribute port_attrs = syslogserver.attribute(SyslogServerXMLUtils.port);
        if (host_attrs != null) {
            host_attrs.setValue(host);
        } else {
            syslogserver.addAttribute(SyslogServerXMLUtils.host, host);
        }
        if (port_attrs != null) {
            port_attrs.setValue(port);
        } else {
            syslogserver.addAttribute(SyslogServerXMLUtils.port, port);
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(charset);
        format.setIndent(true);
        try {
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(StringContext.syslogserver_xml)), format);
            try {
                xmlWriter.write(doc);
                flag = true;
            } catch (IOException e) {
                logger.info(e.getMessage(),e);
            } finally {
                try {
                    xmlWriter.flush();
                    xmlWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.info(e.getMessage(),e);
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage(),e);
        }
        return flag;
    }
}


