package com.egdfrm.extend.interceptors;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * web service 拦截 IP
 *  该拦截只放在 CRMService、OverseasDistributorService 下。 防止数据同步出现问题
 * Created by hgb on 2018/9/10
 * Email xhy18650@sina.com
 **/
@Component
public class IpAddressInInterceptor extends AbstractPhaseInterceptor<Message>{

    private final static Logger logger = LoggerFactory.getLogger(IpAddressInInterceptor.class);

    //这个属性是注入进来的,你也可以从properties,xml文件中去读取,也可以从数据库中去获取;
    private List<String> ipList;


    public void setIpList(List<String> ipList) {
        this.ipList = ipList;
    }


    public IpAddressInInterceptor() {
        super(Phase.RECEIVE);
    }

    public void handleMessage(Message message) throws Fault {
        //指定CXF获取客户端的HttpServletRequest : http-request；
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        String ipAddress="";
        boolean flag = false;
        if (null != request) {
            ipAddress = getUserIpAddr(request);

            // unit 内网请求时
            if(ipAddress.startsWith("192.168.") || ipAddress.equals("127.0.0.1")){
                logger.info("请求客户端的IP地址:" + ipAddress+"（内网）");
                flag = true;
            }else {
                logger.info("请求客户端的IP地址:" + ipAddress+"（外网）");
            }
            for (String s : ipList) {
                if (s.equals(ipAddress)) {
                    flag = true;
                    break;
                }
            }
        }
        if(!flag) {
            throw new Fault(new IllegalAccessException("IP address " + ipAddress + " is stint"));
        }
    }

    /**
     * 获取IP地址的方法
     * @param request
     * @return
     */
    private String getUserIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;


        //获取经过代理的客户端的IP地址; 排除了request.getRemoteAddr() 方法
        // 在通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址了
//        String ip = CodesUtil.getIpAddr(request);
//        if (ip != null && ip.indexOf(",") > 0) {
//            logger.info("取到客户多个ip1====================" + ip);
//            String[] arr = ip.split(",");
//            ip = arr[arr.length - 1].trim();//有多个ip时取最后一个ip
//            logger.info("取到客户多个ip2====================" + ip);
//        }
//        return ip;
    }
}
