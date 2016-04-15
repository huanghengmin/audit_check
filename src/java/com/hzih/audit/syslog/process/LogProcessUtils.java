package com.hzih.audit.syslog.process;

import com.hzih.audit.domain.AccountLog;
import com.hzih.audit.domain.ClientLog;
import com.hzih.audit.syslog.format.ILogFormat;
import com.hzih.audit.syslog.format.impls.AccountLogFormat;
import com.hzih.audit.syslog.format.impls.ClientLogFormat;
import com.hzih.audit.syslog.hibernate.AccountLogDao;
import com.hzih.audit.syslog.hibernate.ClientLogDao;
import com.hzih.audit.web.action.audit.ProcessXMLUtils;
import com.hzih.audit.web.action.config.AlertMessage;
import com.hzih.audit.web.action.config.AlertThread;
import com.hzih.audit.web.action.config.AlertUtils;
import com.hzih.audit.web.action.config.CheckDataBaseUse;
import org.dom4j.Node;

import java.util.List;

/**
 * Created by Administrator on 15-6-25.
 */
public class LogProcessUtils {
    CheckDataBaseUse checkDataBaseUse = new CheckDataBaseUse();
    private AccountLogDao accountLogDao = new AccountLogDao();
    private ClientLogDao clientLogDao = new ClientLogDao();

    public void processILogFormat(ILogFormat log) {
        if (log instanceof AccountLogFormat) {
            int i = checkDataBaseUse.alertDatabaseUse();
            if (i == 0) {//忽然审计日志
                AccountLog accountLog = ((AccountLogFormat) log).convert();
                if (accountLog.getAudittype().equals("001")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.audit_flag).equals("0")) {
//                       accountLogDao.saveLog(accountLog);
                    } else {
//                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 审计功能的开启与关闭事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (accountLog.getAudittype().equals("004")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.admin_flag).equals("0")) {
//                       accountLogDao.saveLog(accountLog);
                    } else {
//                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 管理员一般操作事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (accountLog.getAudittype().equals("010")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.storage_flag).equals("0")) {
//                       accountLogDao.saveLog(accountLog);
                    } else {
//                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 存储失败事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (accountLog.getAudittype().equals("012")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.admin_verity_flag).equals("0")) {
//                       accountLogDao.saveLog(accountLog);
                    } else {
//                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 管理端鉴别失败事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }else if (accountLog.getAudittype().equals("013")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.tamper_flag).equals("0")) {
//                       accountLogDao.saveLog(accountLog);
                    } else {
//                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 软硬件篡改事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }else if (accountLog.getAudittype().equals("014")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.fault_flag).equals("0")) {
//                       accountLogDao.saveLog(accountLog);
                    } else {
//                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 故障容错事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }
            } else if (i == 1) {//替换最老审计日志
                AccountLog accountLog = ((AccountLogFormat) log).convert();
                AccountLog find_old = accountLogDao.findOldLog();
                if(find_old!=null)
                    accountLog.setId(find_old.getId());
                if (accountLog.getAudittype().equals("001")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.audit_flag).equals("0")) {
                        accountLogDao.saveLog(accountLog);
                    } else {
                        accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 审计功能的开启与关闭事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (accountLog.getAudittype().equals("004")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.admin_flag).equals("0")) {
                        accountLogDao.saveLog(accountLog);
                    } else {
                        accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 管理员一般操作事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (accountLog.getAudittype().equals("010")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.storage_flag).equals("0")) {
                        accountLogDao.saveLog(accountLog);
                    } else {
                        accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 存储失败事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }else if (accountLog.getAudittype().equals("012")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.admin_verity_flag).equals("0")) {
                       accountLogDao.saveLog(accountLog);
                    } else {
                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 管理端鉴别失败事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }else if (accountLog.getAudittype().equals("013")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.tamper_flag).equals("0")) {
                       accountLogDao.saveLog(accountLog);
                    } else {
                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 软硬件篡改事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }else if (accountLog.getAudittype().equals("014")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.fault_flag).equals("0")) {
                       accountLogDao.saveLog(accountLog);
                    } else {
                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 故障容错事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }
            } else {
                AccountLog accountLog = ((AccountLogFormat) log).convert();
                if (accountLog.getAudittype().equals("001")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.audit_flag).equals("0")) {
                        accountLogDao.saveLog(accountLog);
                    } else {
                        accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 审计功能的开启与关闭事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (accountLog.getAudittype().equals("004")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.admin_flag).equals("0")) {
                        accountLogDao.saveLog(accountLog);
                    } else {
                        accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 管理员一般操作事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (accountLog.getAudittype().equals("010")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.storage_flag).equals("0")) {
                        accountLogDao.saveLog(accountLog);
                    } else {
                        accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 存储失败事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }else if (accountLog.getAudittype().equals("012")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.admin_verity_flag).equals("0")) {
                       accountLogDao.saveLog(accountLog);
                    } else {
                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 管理端鉴别失败事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }else if (accountLog.getAudittype().equals("013")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.tamper_flag).equals("0")) {
                       accountLogDao.saveLog(accountLog);
                    } else {
                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 软硬件篡改事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }else if (accountLog.getAudittype().equals("014")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.fault_flag).equals("0")) {
                       accountLogDao.saveLog(accountLog);
                    } else {
                       accountLogDao.saveLog(accountLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 故障容错事件发生", accountLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }
            }
        } else if (log instanceof ClientLogFormat) {
            int i = checkDataBaseUse.alertDatabaseUse();
            if (i == 0) {
                ClientLog clientLog = ((ClientLogFormat) log).convert();
                if (clientLog.getAudittype().equals("002")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.verity_flag).equals("0")) {
//                        clientLogDao.saveLog(clientLog);
                    } else {
//                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 用户校验失败事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("003")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.client_flag).equals("0")) {
//                        clientLogDao.saveLog(clientLog);
                    } else {
//                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 授权用户一般操作事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("005")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.build_flag).equals("0")) {
//                        clientLogDao.saveLog(clientLog);
                    } else {
//                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 隧道的建立与删除事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }/* else if (clientLog.getAudittype().equals("006")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.build_num_flag).equals("0")) {
//                        clientLogDao.saveLog(clientLog);
                    } else {
//                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 连续同一隧道的建立与删除事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } */else if (clientLog.getAudittype().equals("007")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.full_flag).equals("0")) {
//                        clientLogDao.saveLog(clientLog);
                    } else {
//                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 用户数据完整性校验失败事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("008")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.decode_flag).equals("0")) {
//                        clientLogDao.saveLog(clientLog);
                    } else {
//                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 用户数据加解密失败事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("009")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.discard_flag).equals("0")) {
//                        clientLogDao.saveLog(clientLog);
                    } else {
//                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 根据策略,数据包丢弃事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("011")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.replay_flag).equals("0")) {
//                        clientLogDao.saveLog(clientLog);
                    } else {
//                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 数据包重放事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }
            } else if (i == 1) {
                ClientLog clientLog = ((ClientLogFormat) log).convert();
                ClientLog find_old = clientLogDao.findOldLog();
                if(find_old!=null)
                    clientLog.setId(find_old.getId());
                if (clientLog.getAudittype().equals("002")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.verity_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 用户校验失败事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("003")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.client_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 授权用户一般操作事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("005")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.build_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 隧道的建立与删除事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }/* else if (clientLog.getAudittype().equals("006")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.build_num_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 连续同一隧道的建立与删除事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }*/ else if (clientLog.getAudittype().equals("007")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.full_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 用户数据完整性校验失败事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("008")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.decode_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 用户数据加解密失败事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("009")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.discard_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 根据策略,数据包丢弃事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("011")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.replay_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 数据包重放事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }
            } else {
                ClientLog clientLog = ((ClientLogFormat) log).convert();
                if (clientLog.getAudittype().equals("002")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.verity_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 用户校验失败事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("003")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.client_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 授权用户一般操作事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("005")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.build_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 隧道的建立与删除事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } /*else if (clientLog.getAudittype().equals("006")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.build_num_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 连续同一隧道的建立与删除事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } */else if (clientLog.getAudittype().equals("007")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.full_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 用户数据完整性校验失败事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("008")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.decode_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 用户数据加解密失败事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("009")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.discard_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 根据策略,数据包丢弃事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                } else if (clientLog.getAudittype().equals("011")) {
                    if (ProcessXMLUtils.getValue(ProcessXMLUtils.replay_flag).equals("0")) {
                        clientLogDao.saveLog(clientLog);
                    } else {
                        clientLogDao.saveLog(clientLog);
                        List<Node> nodeList = AlertUtils.queryEmailAll();
                        AlertMessage message = new AlertMessage("SSLVPN系统 数据包重放事件发生", clientLog.toString());
                        AlertThread thread = new AlertThread(nodeList, message);
                        thread.start();
                    }
                }
            }
        }
    }
}
