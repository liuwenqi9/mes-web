package com.egdfrm.unit.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 请求WebService
 * Created by tyq on 17/1/16.
 */
public class HttpConnectionUtils {

    /**
     * 根据参数和地址请求webService
     * @param url wsdl地址
     * @param data 报文
     * @return 请求结果 200成功
     * @throws Exception 异常
     *
     */
    public static InputStream sendHttpRequest(String url, String data) throws IOException {
        InputStream is = null;
        URL ul = new URL(url);
        //获取HttpURLConnection实例
        HttpURLConnection connection = (HttpURLConnection) ul.openConnection();
        //设置http请求为post
        connection.setRequestMethod("POST");
        //允许使用URL连接进行输入
        connection.setDoInput(true);
        //允许使用URL连接进行输出
        connection.setDoOutput(true);
        //设置请求类型
        connection.setRequestProperty("Content-type", "text/xml;charset=utf-8");
        //获取写入此连接的输出流
        OutputStream out = connection.getOutputStream();
        //把报文数据写入此连接
        out.write(data.getBytes("utf-8"));
        //HTTP消息响应状态码
        int code = connection.getResponseCode();
        if(code == 200){
            is = connection.getInputStream();
        }
        //connection.getErrorStream()
        return is;
    }
}
