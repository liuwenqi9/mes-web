package com.egdfrm.core.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sjf
 * @date 2016年12月14日 读取资源文件内容
 */
public class PropertiesLoad extends Properties{

	/**
	 * @author sjf
	 * @date 2017年1月9日 
	 * 
	 */
	private static final long serialVersionUID = 743421671516647415L;
	/**
	 * @author sjf
	 * @date 2016年12月14日
	 * @param fileName
	 *            文件名
	 * @param code
	 * @return
	 * @throws IOException
	 */

    public Logger log = LoggerFactory.getLogger(this.getClass()); 
	
	public String load(String fileName, String code) {
		String retVal;
		try {
			Properties properties = new Properties();
			InputStream inputStream = getClass()
					.getResourceAsStream("/" + fileName + ".properties");
			BufferedReader bf = null;
			bf = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

			properties.load(bf);

			retVal = properties.getProperty(code);
		} catch (IOException e) {
			log.error(new Date()+e.getMessage());
			return null;
		}
		return retVal;
	}
}
