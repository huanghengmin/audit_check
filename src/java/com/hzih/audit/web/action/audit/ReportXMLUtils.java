package com.hzih.audit.web.action.audit;

import com.hzih.audit.utils.StringContext;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

/**
 * Created by Administrator on 15-6-24.
 */
public class ReportXMLUtils {

    private static Logger logger = Logger.getLogger(ReportXMLUtils.class);
    public static final String config = "config";
    public static final String audit_warn_number = "audit_warn_number";
    public static final String verity_warn_number = "verity_warn_number";
    public static final String client_warn_number = "client_warn_number";
    public static final String admin_warn_number = "admin_warn_number";
    public static final String build_warn_number = "build_warn_number";
    public static final String full_warn_number = "full_warn_number";
    public static final String decode_warn_number = "decode_warn_number";
    public static final String discard_warn_number = "discard_warn_number";
    public static final String storage_warn_number = "storage_warn_number";
    public static final String replay_warn_number = "replay_warn_number";

    public static final String audit_police_number = "audit_police_number";
    public static final String verity_police_number = "verity_police_number";
    public static final String client_police_number = "client_police_number";
    public static final String admin_police_number = "admin_police_number";
    public static final String build_police_number = "build_police_number";
    public static final String full_police_number = "full_police_number";
    public static final String decode_police_number = "decode_police_number";
    public static final String discard_police_number = "discard_police_number";
    public static final String storage_police_number = "storage_police_number";
    public static final String replay_police_number = "replay_police_number";
    public static final String charset = "utf-8";


    public static String find() {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            doc = saxReader.read(new File(StringContext.report_xml));
        } catch (DocumentException e) {
            logger.error(e.getMessage(),e);
        }
        if (doc != null) {
            Element config = doc.getRootElement();
            Element audit_warn_number_el = config.element(audit_warn_number);
            Element verity_warn_number_el = config.element(verity_warn_number);
            Element client_warn_number_el = config.element(client_warn_number);
            Element admin_warn_number_el = config.element(admin_warn_number);
            Element build_warn_number_el = config.element(build_warn_number);
            Element full_warn_number_el = config.element(full_warn_number);
            Element decode_warn_number_el = config.element(decode_warn_number);
            Element discard_warn_number_el = config.element(discard_warn_number);
            Element storage_warn_number_el = config.element(storage_warn_number);
            Element replay_warn_number_el = config.element(replay_warn_number);

            Element audit_police_number_el = config.element(audit_police_number);
            Element verity_police_number_el = config.element(verity_police_number);
            Element client_police_number_el = config.element(client_police_number);
            Element admin_police_number_el = config.element(admin_police_number);
            Element build_police_number_el = config.element(build_police_number);
            Element full_police_number_el = config.element(full_police_number);
            Element decode_police_number_el = config.element(decode_police_number);
            Element discard_police_number_el = config.element(discard_police_number);
            Element storage_police_number_el = config.element(storage_police_number);
            Element replay_police_number_el = config.element(replay_police_number);

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("audit_warn_number:'" + audit_warn_number_el.getText() + "',");
            sb.append("verity_warn_number:'" + verity_warn_number_el.getText() + "',");
            sb.append("client_warn_number:'" + client_warn_number_el.getText() + "',");
            sb.append("admin_warn_number:'" + admin_warn_number_el.getText() + "',");
            sb.append("build_warn_number:'" + build_warn_number_el.getText() + "',");
            sb.append("full_warn_number:'" + full_warn_number_el.getText() + "',");
            sb.append("decode_warn_number:'" + decode_warn_number_el.getText() + "',");
            sb.append("discard_warn_number:'" + discard_warn_number_el.getText() + "',");
            sb.append("storage_warn_number:'" + storage_warn_number_el.getText() + "',");
            sb.append("replay_warn_number:'" + replay_warn_number_el.getText() + "',");

            sb.append("audit_police_number:'" + audit_police_number_el.getText() + "',");
            sb.append("verity_police_number:'" + verity_police_number_el.getText() + "',");
            sb.append("client_police_number:'" + client_police_number_el.getText() + "',");
            sb.append("admin_police_number:'" + admin_police_number_el.getText() + "',");
            sb.append("build_police_number:'" + build_police_number_el.getText() + "',");
            sb.append("full_police_number:'" + full_police_number_el.getText() + "',");
            sb.append("decode_police_number:'" + decode_police_number_el.getText() + "',");
            sb.append("discard_police_number:'" + discard_police_number_el.getText() + "',");
            sb.append("storage_police_number:'" + storage_police_number_el.getText() + "',");
            sb.append("replay_police_number:'" + replay_police_number_el.getText() + "'");
            sb.append("}");
            return sb.toString();
        }
        return null;
    }


    public static String getValue(String name) {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        String result = null;
        try {
            doc = saxReader.read(new File(StringContext.report_xml));
        } catch (DocumentException e) {
            logger.error(e.getMessage(),e);
        }
        if(doc!=null){
            Element ldap = doc.getRootElement();
            Element el = ldap.element(name);
            result = el.getText();
            return result;
        }
        return null;
    }

    public static boolean save(
            String audit_warn_number,
            String verity_warn_number,
            String client_warn_number,
            String admin_warn_number,
            String build_warn_number,
           String full_warn_number,
           String decode_warn_number,
           String discard_warn_number,
           String storage_warn_number,
           String replay_warn_number,

           String audit_police_number,
           String verity_police_number,
           String client_police_number,
           String admin_police_number,
           String build_police_number,
           String full_police_number,
           String decode_police_number,
           String discard_police_number,
           String storage_police_number,
           String replay_police_number) {
        boolean flag = false;
        Document doc = DocumentHelper.createDocument();
        Element config = doc.addElement(ReportXMLUtils.config);

        Element audit_warn_number_el = config.addElement(ReportXMLUtils.audit_warn_number);
        audit_warn_number_el.setText(audit_warn_number);
        Element verity_warn_number_el = config.addElement(ReportXMLUtils.verity_warn_number);
        verity_warn_number_el.setText(verity_warn_number);
        Element client_warn_number_el = config.addElement(ReportXMLUtils.client_warn_number);
        client_warn_number_el.setText(client_warn_number);
        Element admin_warn_number_el = config.addElement(ReportXMLUtils.admin_warn_number);
        admin_warn_number_el.setText(admin_warn_number);
        Element build_warn_number_el = config.addElement(ReportXMLUtils.build_warn_number);
        build_warn_number_el.setText(build_warn_number);
        Element full_warn_number_el = config.addElement(ReportXMLUtils.full_warn_number);
        full_warn_number_el.setText(full_warn_number);
        Element decode_warn_number_el = config.addElement(ReportXMLUtils.decode_warn_number);
        decode_warn_number_el.setText(decode_warn_number);
        Element discard_warn_number_el = config.addElement(ReportXMLUtils.discard_warn_number);
        discard_warn_number_el.setText(discard_warn_number);
        Element storage_warn_number_el = config.addElement(ReportXMLUtils.storage_warn_number);
        storage_warn_number_el.setText(storage_warn_number);
        Element replay_warn_number_el = config.addElement(ReportXMLUtils.replay_warn_number);
        replay_warn_number_el.setText(replay_warn_number);

        Element audit_police_number_el = config.addElement(ReportXMLUtils.audit_police_number);
        audit_police_number_el.setText(audit_police_number);
        Element verity_police_number_el = config.addElement(ReportXMLUtils.verity_police_number);
        verity_police_number_el.setText(verity_police_number);
        Element client_police_number_el = config.addElement(ReportXMLUtils.client_police_number);
        client_police_number_el.setText(client_police_number);
        Element admin_police_number_el = config.addElement(ReportXMLUtils.admin_police_number);
        admin_police_number_el.setText(admin_police_number);
        Element build_police_number_el = config.addElement(ReportXMLUtils.build_police_number);
        build_police_number_el.setText(build_police_number);
        Element full_police_number_el = config.addElement(ReportXMLUtils.full_police_number);
        full_police_number_el.setText(full_police_number);
        Element decode_police_number_el = config.addElement(ReportXMLUtils.decode_police_number);
        decode_police_number_el.setText(decode_police_number);
        Element discard_police_number_el = config.addElement(ReportXMLUtils.discard_police_number);
        discard_police_number_el.setText(discard_police_number);
        Element storage_police_number_el = config.addElement(ReportXMLUtils.storage_police_number);
        storage_police_number_el.setText(storage_police_number);
        Element replay_police_number_el = config.addElement(ReportXMLUtils.replay_police_number);
        replay_police_number_el.setText(replay_police_number);

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(charset);
        format.setIndent(true);
        try {
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(StringContext.report_xml)), format);
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
