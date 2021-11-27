package com.dscomm.iecs.base.utils;

import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;


/**
 * 描述:获取请求IP工具类
 *
 * @author YangFuxi
 * Date Time 2019/10/10 11:08
 */
public class ObtainIPUtils {
    private static final Logger logger = LoggerFactory.getLogger(ObtainIPUtils.class);

    private static String hostIp = null ;
    private static List<String>  ips = new ArrayList<>( ) ;
    private static String  port = null ;
    private static List<String> localTomcatIpAndPorts = new ArrayList<>( ) ;


    public final static String getIpAddress(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");
//        if (logger.isInfoEnabled()) {
//            logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
//        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
//                if (logger.isInfoEnabled()) {
//                    logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
//                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
//                if (logger.isInfoEnabled()) {
//                    logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
//                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
//                if (logger.isInfoEnabled()) {
//                    logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
//                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//                if (logger.isInfoEnabled()) {
//                    logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
//                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
//                if (logger.isInfoEnabled()) {
//                    logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
//                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }

        // 解决请求和响应的IP一致且通过浏览器请求时，request.getRemoteAddr()为"0:0:0:0:0:0:0:1"
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip = hostIp ;
        }

        return ip;
    }





    /**
     * 获取当前tomcat服务的ip和端口
     *
     * @return ip和端口
     */
    public static void localTomcatIpAndPort() {
        buildLocalIp();
        buildLocalIpList() ;
        buildLocalTomcatPort() ;
        buildLocalTomcatIpAndPortList() ;
    }


    /**
     * 获取当前tomcat服务的ip和端口
     *
     * @return ip和端口
     */
    public static List<String> getLocalTomcatIpAndPortList() {
        return  localTomcatIpAndPorts ;
    }





    /**
     * 获取当前tomcat服务的ip和端口
     *
     * @return ip和端口
     */
    public static List<String> buildLocalTomcatIpAndPortList() {
        try {
            List<String> temps = new ArrayList<>() ;
            for (int i = 0; i < ips.size(); i++) {
                if (!StringUtils.isBlank(ips.get(i))) {
                    if (!StringUtils.isBlank(port)) {
                        String  localTomcatIpAndPort = String.format("%s:%s", ips.get(i), port) ;
                        if( !temps.contains( localTomcatIpAndPort)  ){
                            temps.add ( localTomcatIpAndPort ) ;
                        }
                    }
                }
            }
            localTomcatIpAndPorts =  temps ;

            return  localTomcatIpAndPorts ;
        } catch (Exception ex) {
            logger.error("get local tomcat ip and port fail", ex);
            return null  ;
        }
    }


    /**
     * 获取本机ip
     *
     * @return 本机ip
     */
    public static List<String> getLocalIpList() {
       return  ips ;
    }


    /**
     * 获取本机ip
     *
     * @return 本机ip
     */
    public static List<String> buildLocalIpList() {
        try {
//            InetAddress address = InetAddress.getLocalHost();
//            String hostName = address.getHostName();
//            InetAddress[] items = InetAddress.getAllByName(hostName);
//            if (items != null && items.length > 0) {
//                Arrays.asList(items).forEach(item -> list.add(item.getHostAddress()));
//            }
            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) interfaces.nextElement();
                Enumeration ipAddrEnum = ni.getInetAddresses();
                while (ipAddrEnum.hasMoreElements()) {
                    InetAddress addr = (InetAddress) ipAddrEnum.nextElement();
                    if (addr.isLoopbackAddress() == true) {
                        continue;
                    }

                    String ip = addr.getHostAddress();
                    if (ip.indexOf(":") != -1) {
                        //skip the IPv6 addr
                        continue;
                    }

                    logger.info("Interface: " + ni.getName()
                            + ", IP: " + ip);
                    if (!ips.contains(ip)){
                        ips.add(ip);
                    }
                }
            }
            return ips ;
        } catch (Exception ex) {
            logger.error("get local ip fail", ex);
            return null;
        }
    }


    /**
     * 获取本机ip
     *
     * @return 本机ip
     */
    public static String getLocalIp() {
        try {

            return hostIp ;
        } catch (Exception ex) {
            logger.error("get local ip fail", ex);
            return null;
        }
    }

    /**
     * 获取本机ip
     *
     * @return 本机ip
     */
    public static String buildLocalIp() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            hostIp  = address.getHostAddress();
            return hostIp;
        } catch (Exception ex) {
            logger.error("get local ip fail", ex);
            return null;
        }
    }



    public static String getLocalTomcatPort() {
        return  port ;
    }

    /**
     * 获取当前tomcat端口
     *
     * @return tomcat端口
     */
    public  static String buildLocalTomcatPort() {
        try {
            MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
            port = objectNames.iterator().next().getKeyProperty("port");
            return port;
        } catch (Exception ex) {
            logger.error("get local tomcat port fail", ex);
            return null;
        }
    }
}
