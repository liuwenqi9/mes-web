package com.egdfrm.unit.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egdfrm.extend.common.DateUtil;

/**
 * 公共方法库
 * Created by tyq on 17/1/11.
 */
public class Utils {

    /**
     * unicode 转字符串
     */
    public static String unicodeToString(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

    /**
     * 字符串转换unicode
     */
    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }
    
    /**
     * 打印固定格式日志
     * sjf
     */
    public static String printWebServiceLog(){

		StackTraceElement[] stacks = new Throwable().getStackTrace();
		//调用的类名
		String className=stacks[1].getClassName();
		//调用的方法名
		String methodName=stacks[1].getMethodName();
		//拼接
		String log="@"+new Date().toString()+"【"+className+"】："+methodName+"#";
		//打印
		System.out.println(log);
		//返回
		return log;
    }
    public static String printWebServiceLog(String inputParams){
    	// 
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		//调用的类名
		String className=stacks[1].getClassName();
		//调用的方法名
		String methodName=stacks[1].getMethodName();
		//拼接
		String log="@"+new Date().toString()+"【"+className+"】："+methodName+"#\n"+inputParams;
		//打印
		System.out.println(log);
		//打印
		System.out.println(inputParams);
		//返回
		return log;
    }
    /**
     * @author sjf
     * @date 2017年2月4日 
     * @param lastTime
     * @param coordinate
     * 打印花费的时间
     *
     */
    public static void printTakeTime(long lastTime,String  coordinate){
    	//时间差
    	long  differenceTime=System.currentTimeMillis()-lastTime; 
		//更新上一次时间
		lastTime=System.currentTimeMillis();
		//如果花费的时间大于5秒，则记录
		if(differenceTime>5000){
	    	// 
			StackTraceElement[] stacks = new Throwable().getStackTrace();
			//调用的类名
			String className=stacks[1].getClassName();
			//调用的方法名
			String methodName=stacks[1].getMethodName();
			//拼接
			String log="@"+new Date().toString()+"【"+className+"】："+methodName+"#";
			//打印
			System.out.println(log);
			//打印
			System.out.println(coordinate+"花费时间："+differenceTime+"ms");
		}
    }
    /**
     * @author sjf
     * @date 2017年1月19日 
     * @param listMap
     * 将List<Map>中日期型格式化，以便转json
     */
    public static void formatListMapDate(List<Map<String, Object>> listMap){
    	for(Map<String, Object> ma:listMap){
    		for(Map.Entry<String, Object> entry  :ma.entrySet()){
    			if (entry.getValue() instanceof Date) {
    				String formatDate=DateUtil.getDate((Date)entry.getValue());
    				entry.setValue(formatDate);
				} 
    		}
    	}
    }
    /**
     * @author sjf
     * @date 2017年1月19日 
     * @param headListMap
     * @param lineListMap
     * @param head 头行关联的头表字段名
     * @param line 头行关联的行表字段名
     * 将行Map放入头Map
     */
    public static void headPutLine(List<Map<String, Object>> headListMap,List<Map<String, Object>> lineListMap,String head,String line){
    	/**
    	 * 循环头表
    	 */
    	for(Map<String, Object> headMap:headListMap){
    		//头行关联的头表字段值
    		Object headValObj=headMap.get(head);
    		if(headValObj==null){
    			continue;
    		}
    		String headVal=headValObj.toString();
    		//头表新增一个字段，用来存放行表对象
    		List<Map<String, Object>> lineList=new ArrayList<Map<String, Object>>();
    		//循环行表
    		for(int i=lineListMap.size()-1;i>=0;i--){
    			Map<String, Object> lineMap=lineListMap.get(i);
        		//头行关联的行表表字段值
        		Object lineValObj=lineMap.get(line);
        		if(lineValObj==null){
        			continue;
        		}
        		String lineVal=lineValObj.toString();
        		//如果相关联，则将行放入头
    			if (headVal.equals(lineVal)) {
    				lineList.add(lineMap);
    				//把那条行数据去掉，最后剩下的数据，再存入一个头数据中
    				lineListMap.remove(lineMap);
				} 
    		}
    		//循环完行表，将关联了头表的数据存入map
    		if(!lineList.isEmpty()){
        		headMap.put("lineList", lineList);
    		}
    	}
    	/**
    	 * 将最后剩下的数据，再存入一个头数据中
    	 */
    	if(!lineListMap.isEmpty()){
        	Map<String, Object> nullHadMap=new HashMap<String, Object>();
    		nullHadMap.put("lineList", lineListMap);
        	//加入头list
    		headListMap.add(nullHadMap);
    	}
    }
    
}
