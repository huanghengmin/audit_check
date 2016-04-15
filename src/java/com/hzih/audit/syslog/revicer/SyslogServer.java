package com.hzih.audit.syslog.revicer;

import com.hzih.audit.syslog.code.SyslogCodecFactory;
import com.hzih.audit.syslog.factory.LogFormatFactory;
import com.hzih.audit.syslog.format.ILogFormat;
//import com.hzih.audit.syslog.process.LogProcessRunnable;
import com.hzih.audit.syslog.process.LogProcessUtils;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import java.net.InetSocketAddress;
//import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * Created by IntelliJ IDEA.
 * User: bluesky
 * Date: 2009-11-7
 * Time: 23:39:55
 * To change this template use File | Settings | File Templates.
 */
public class SyslogServer extends Thread {
//    public static final Logger logger = Logger.getLogger(SyslogServer.class);
    private int timeout = 1;
    private boolean m_runing = false;
    private NioDatagramAcceptor acceptor;
    private SyslogHandler handler;
//    private String charset;
    private ExecutorService filterExecutor;
    private ExecutorService acceptExecutor;
    private String sysloghost;
    private int syslogport;
//    public BlockingQueue<String> query;
//    public static LogProcessRunnable log_process = new LogProcessRunnable();

    public boolean isRun() {
        return m_runing;
    }

    /**
     * 日志处理
     *
     * @param syslog
     */
    public void processSyslog(SyslogMessage syslog) {
        ILogFormat iLogFormat = LogFormatFactory.getLogFormat(syslog.getMessage());
        LogProcessUtils logProcessUtils = new LogProcessUtils();
        logProcessUtils.processILogFormat(iLogFormat);
//        log_process.offer(iLogFormat);
    }

    public void close() {
        m_runing = false;
        if (acceptor != null) {
            if (sysloghost != null)
                acceptor.unbind(new InetSocketAddress(sysloghost, syslogport));
            else {
                acceptor.unbind(new InetSocketAddress(syslogport));
            }
            acceptor.dispose();
            if (filterExecutor != null)
                filterExecutor.shutdown();
            if (acceptExecutor != null) {
                acceptExecutor.shutdown();
            }
        }
//        logger.info("Syslog service Run stop.port:" + syslogport);
    }

    public void config(String host, int port/*, String charset*/) {
        sysloghost = host;
        syslogport = port;
//        this.charset = charset;
        this.filterExecutor = new OrderedThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1);
        this.acceptExecutor = new OrderedThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1);
    }

    public void run() {
        m_runing = true;
        try {
            startSyslog();
//            log_process.start();
        } catch (Exception e) {
            e.printStackTrace();
//            logger.warn("Syslog  service Run error.port:" + syslogport, e);
        }
    }

    /**
     * @throws Exception
     */
    private void startSyslog() throws Exception {
        // Create UDP/IP acceptor.
        acceptor = new NioDatagramAcceptor();
        DatagramSessionConfig dcfg = acceptor.getSessionConfig();
        dcfg.setReuseAddress(true);
        acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(filterExecutor));
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new SyslogCodecFactory()));
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
//        acceptor.getSessionConfig().setKeepAlive(true);
        handler = new SyslogHandler(this);
        acceptor.setHandler(handler);
        acceptor.getSessionConfig().setBothIdleTime(timeout * 60);
        //acceptor.getSessionConfig().setSoLinger(0);
        //acceptor.setReuseAddress(true);
        if (sysloghost != null)
            acceptor.bind(new InetSocketAddress(sysloghost, syslogport));
        else
            acceptor.bind(new InetSocketAddress(syslogport));
    }

    public static void main(String arg[]) throws Exception {
        SyslogServer syslog = new SyslogServer();
//        syslog.config("127.0.0.1", 1554, "UTF-8");
        syslog.config(null,1554/*, "UTF-8"*/);
        syslog.start();
        while (true) {
            Thread.sleep(60 * 1000);
        }
    }

}
