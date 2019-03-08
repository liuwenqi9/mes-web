package com.egdfrm.extend.common;

//import java.io.Serializable;
//import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
/**
 * 
 * <p>ClassName: json转换工具类</p>
 * <p>Description: TODO</p>
 * <p>Author: Meng Min</p>
 * <p>Date: 2015年6月2日</p>
 */
public class JsonObjectConverTools {
    /**
     * 
     * <p>Description: 对象转换成json</p>
     * @param <T>
     * @param object
     * @return
     */
    public static <T> String objectToJson(T t) {
        return JSON.toJSONString(t);
    }
    /**
     * 
     * <p>Description: json转换成对象</p>
     * @param <T>
     * @param json
     * @return
     */
    public static <T> T jsonToObject(String json,Class<T> t) {
        
        return JSON.parseObject(json, t);
    }
    /**
     * 
     * <p>Description: 将json转换成list</p>
     * @param json
     * @param t
     * @return
     */
    public static <T> List<T> jsonToList(String json,Class<T> t) {  
        List<T> list = JSON.parseArray(json, t);  
        return list;
    }
    /**
     * 
     * <p>Description: list转换成json</p>
     * @param json
     * @return
     */
    public static <T> String listToJson(List<T> list) {
        return JSON.toJSONString(list);
    }
    
    public static String addJsonpString(HttpServletRequest request,String json)
    {
   	 String callback = request.getParameter("callback");
   	 if (callback != null)
   	 {
   		 return callback + "(" + json + ")";
   	 }
   	 else
   	 {
   		return json;
   	 }
    }
}
