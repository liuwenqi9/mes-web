package com.egdfrm.unit.common;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * xml操作
 * Created by tyq on 17/01/16.
 */
public class DocumentUtils {


    /**
     * 根据InputStream获取xml文档
     * @param is Xml文档相关字节流
     * @return xml文档
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static Document getDocumentByInput(InputStream is) throws ParserConfigurationException, IOException, SAXException {
        //获取DocumentBuilderFactory实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //从工厂获取DocumentBuilder实例
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        //将is流中的内容解析为一个xml文档
        Document document = documentBuilder.parse(is);
        return document;
    }

    /**
     * 根据节点名称获取此节点的值
     * 注:该节点必须无子节点,且该节点在此xml中单独存在
     * @param document xml文档
     * @param nodeName 节点名称
     * @return
     */
    public static String getNodeValue(Document document,String nodeName){
        //获取节点
        NodeList nodeList = document.getElementsByTagName(nodeName);
        //获取节点的值
        String value = nodeList.item(0).getChildNodes().item(0).getNodeValue();
        return value;
    }

    /**
     * 获取所有相同节点名称的值
     * @param document xml文档
     * @param nodeName 节点名称
     * @return
     */
    public static String[] getNodeValues(Document document,String nodeName){
        //获取节点
        NodeList nodeList = document.getElementsByTagName(nodeName);
        String[] array = new String[nodeList.getLength()];
        for (int i = 0;i < nodeList.getLength();i++){
            array[i] = nodeList.item(i).getChildNodes().item(0).getNodeValue();
        }
        return array;
    }

}
