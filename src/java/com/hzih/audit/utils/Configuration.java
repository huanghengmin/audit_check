package com.hzih.audit.utils;

import com.inetec.common.exception.E;
import com.inetec.common.exception.Ex;
import com.inetec.common.i18n.Message;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Configuration {
    static Logger logger = Logger.getLogger(Configuration.class);

    private Document document;

    public String confPath;

    public Configuration(Document doc) {
        this.document = doc;
    }

    /**
     *
     * @param path
     * @throws Ex
     */
    public Configuration(String path) throws Ex {
        this.confPath = path;
        SAXReader saxReader = new SAXReader();
        try {
            document = saxReader.read(path);
        } catch (DocumentException e) {
            e.printStackTrace();
            logger.info(e.getMessage(),e);
        }
    }

    /**
     *
     * @param is
     * @param path
     * @throws Ex
     */
    public Configuration(InputStream is, String path) throws Ex {
        this.confPath = path;
        SAXReader saxReader = new SAXReader();
        try {
            document = saxReader.read(is);
        } catch (DocumentException e) {
            logger.info(e.getMessage(),e);
        }
    }

    /**
     *
     * @param ip
     * @param port
     * @return
     */
    public String editConnectorIp(String ip, String port) {
        try{
            Element connector = (Element) document.selectSingleNode("/Server/Service/Connector[@port=" + port + "]");
            if(connector != null){
                connector.attribute("address").setText(ip);
                String result = save();
                if(result.equals("保存成功")){
                    if(port.equals(""+8443)){
                        return "更新管理服务接口设定IP地址成功";
                    }else if(port.equals(""+8000)){
                        return "更新集控采集数据接口设定IP地址成功";
                    }else{
                        return "更新成功,端口是"+port;
                    }
                }else{
                    return result;
                }
            }
        } catch (Exception e){
            logger.info(e.getMessage(),e);
        }
        return "更新出错";
    }

    /**
     *
     * @param port
     * @return
     */
    public String getConnectorIp(String port) {
        String ip = "";
        try{
           Element connector = (Element) document.selectSingleNode("/Server/Service/Connector[@port=" + port + "]");
            if(connector != null){
                ip = connector.attribute("address").getText();
            }
        } catch (Exception e){
            logger.info(e.getMessage(),e);
        }
        return ip;
    }

    /**
     *
     * @return
     */
    public List<String> getAllowIPs(){
        List<String> allowIps = new ArrayList<String>();
        try{
            Element valve = (Element) document.selectSingleNode("/Server/Service/Engine/Valve");
            if(valve!=null){
                String ip = valve.attribute("allow").getText();
                String[] ips = ip.split("\\|");
                if(ips.length>1){
                    for (int i = 0; i < ips.length; i ++){
                        allowIps.add(ips[i]);
                    }
                }else{
                    allowIps.add(ip);
                }
            }
        } catch (Exception e){
            logger.info(e.getMessage(),e);
        }
        return allowIps;
    }

    /**
     *
     * @param ip
     * @return
     */
    public String editAllowIp(String ip) {
        try{
            Element value = (Element) document.selectSingleNode("/Server/Service/Engine/Valve");
            if(value!=null){
                ip = value.attribute("allow").getText() + ip;
                value.attribute("allow").setText(ip);
                String result = save();
                if(result.equals("保存成功")){
                        return "更新管理客户机地址成功";
                }else{
                    return result;
                }
            }
        } catch (Exception e){
            logger.info(e.getMessage(),e);
        }
        return "更新出错";
    }

    /**
     *
     * @param ip
     * @return
     */
    public String deleteAllowIp(String ip) {
        try{
            Element value = (Element) document.selectSingleNode("/Server/Service/Engine/Valve");
            if(value!=null){
                value.attribute("allow").setText(ip);
                String result = save();
                if(result.equals("保存成功")){
                        return "删除管理客户机地址成功";
                }else{
                    return result;
                }
            }
        } catch (Exception e){
            logger.info(e.getMessage(),e);
        }
        return "删除出错";
    }

    /**
     *
     * @return
     */
    public String save() {
        String result = null;
        XMLWriter output = null;
        try {
            File file = new File(confPath);
            FileInputStream fin = new FileInputStream(file);
            byte[] bytes = new byte[fin.available()];
            while (fin.read(bytes) < 0) fin.close();
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            output = new XMLWriter(new FileOutputStream(file),format);
            if(document != null){
                output.write(document);
                return result = "保存成功";
            }else{
                result = "dom4j处理出错";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            result = e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage();
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (IOException e) {
                e.printStackTrace();
                result = e.getMessage();
            }
        }
        return "保存失败,"+result;
    }

    /**
     *
     * @throws Ex
     */
    public void saveAuditReset() throws Ex {
        XMLWriter output = null;
        try {
            File file = new File(confPath);
            FileInputStream fin = new FileInputStream(file);
            byte[] bytes = new byte[fin.available()];
            while (fin.read(bytes) < 0) fin.close();
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            output = new XMLWriter(new FileOutputStream(file),format);
            if(document != null){
                output.write(document);
            }else{

            }
        } catch (IOException e) {
            throw new Ex().set(E.E_IOException, e, new Message("ccured exception when move Internal configuration To History"));
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (IOException e) {
                throw new Ex().set(E.E_IOException, e, new Message("Occured exception when close XMLWrite"));
            }
        }
    }

    /**
     *
     * @param list
     * @param type
     * @throws Ex
     */
    public void editBackupList(List<String> list, String type) throws Ex {
    }

    /**
     *
     * @param list
     * @param type
     * @throws Ex
     */
    public void addBackupList(List<String> list, String type) throws Ex {
        if("pings".equals(type)) {
            Element pings = (Element) document.selectSingleNode("/backup/pings");
            for(String p : list) {
                pings.addElement("ping").setText(p);
            }
        } else if("telnets".equals(type)) {
            Element telnets = (Element) document.selectSingleNode("/backup/telnets");
            for(String t : list) {
                telnets.addElement("telnet").setText(t);
            }
        } else if("others".equals(type)) {
            Element others = (Element) document.selectSingleNode("/backup/others");
            for(String o : list) {
                others.addElement("other").setText(o);
            }
        }
        saveAuditReset();
    }


    /**
     *
     * @param list
     * @param type
     * @throws Ex
     */
    public void deleteBackupList(List<String> list, String type) throws Ex {
        if("pings".equals(type)) {
            List pings = document.selectNodes("/backup/pings/ping");
            Element _pings = (Element) document.selectSingleNode("/backup/pings");
            if(pings!=null) {
                for(Iterator it = pings.iterator();it.hasNext();){
                    Element e = (Element) it.next();
                    String ping = e.getText();
                    for(String p : list) {
                        if(p.equals(ping)) {
                            _pings.remove(e);
                        }
                    }
                }
            }
        } else if("telnets".equals(type)) {
            List telnets = document.selectNodes("/backup/telnets/telnet");
            Element _telnets = (Element) document.selectSingleNode("/backup/telnets");
            if(telnets!=null) {
                for(Iterator it = telnets.iterator();it.hasNext();){
                    Element e = (Element) it.next();
                    String telnet = e.getText();
                    for(String t : list) {
                        if(t.equals(telnet)) {
                            _telnets.remove(e);
                        }
                    }
                }
            }
        } else if("others".equals(type)) {
            List others = document.selectNodes("/backup/others/other");
            Element _others = (Element) document.selectSingleNode("/backup/others");
            if(others!=null) {
                for(Iterator it = others.iterator();it.hasNext();){
                    Element e = (Element) it.next();
                    String other = e.getText();
                    for(String o : list) {
                        if(o.equals(other)) {
                            _others.remove(e);
                        }
                    }
                }
            }
        }
        saveAuditReset();
    }
}