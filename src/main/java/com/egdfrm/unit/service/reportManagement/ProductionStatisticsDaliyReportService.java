package com.egdfrm.unit.service.reportManagement;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egdfrm.unit.model.report.DailyStatistics;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.egdfrm.core.common.CreateExcel;
import com.egdfrm.core.common.PropertiesLoad;
import com.egdfrm.core.exception.CommonException;
import com.egdfrm.core.service.BaseService;
import com.egdfrm.extend.common.DateUtil;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.mapper.expand.UserMapper;
import com.egdfrm.unit.mapper.standard.MesWipComVMapper;
import com.egdfrm.unit.model.standard.MesWipComV;
import com.egdfrm.unit.model.standard.MesWipComVCriteria;

/**
 * <p>
 * Description: 生产统计日报表SERVICE
 * </p>
 * <p>
 * Author: sjf
 * </p>
 * <p>
 * Date: 2016年12月30日
 * </p>
 */
@Service
public class ProductionStatisticsDaliyReportService extends BaseService {

	@Autowired
	private MesWipComVMapper mwcvm;
	@Autowired
	private UserMapper userMapper;
	/**
	 * @author sjf
	 * @date 2017年1月6日 
	 * @return
     * 获取生产线列表
	 */
	public List<Map<String, Object>> getPlanLines() {
		return userMapper.getPlanLines();
	}

	/**
	 * @author sjf
	 * @date 2017年1月6日 
	 * @param productionLine
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 * 查询生产统计日报表
	 */
	public List<Map<String, Object>> searchProductionStatistics(
			String productionLine, Date dateFrom, Date dateTo) {
		//日期型转化-只取年月日
		Date dateF=DateUtil.StringToDate(DateUtil.DateToString(dateFrom,"yyyy-MM-dd"));
		Date dateT=DateUtil.StringToDate(DateUtil.DateToString(dateTo,"yyyy-MM-dd"));
		List<Map<String, Object>> mwcvList=new ArrayList<Map<String, Object>>();
		MesWipComVCriteria mwcvc=new MesWipComVCriteria();
		//如果没选生产线则不加入条件
		if("0".equals(productionLine)){
			mwcvc.createCriteria().andTransactionDateBetween(dateF, dateT);
		}else{
			mwcvc.createCriteria().andPlanLineCodeEqualTo(productionLine).andTransactionDateBetween(dateF, dateT);
		}
		mwcvList=mwcvm.selectByExampleListMap(mwcvc);

		return  mwcvList;
	}

	/**
	 * @author sjf
	 * @date 2017年1月9日 
	 * @param productionLine
	 * @param dateFrom
	 * @param dateTo
	 * @return
     * 导出excel-服务器生成文件
	 */
	public Map<String, Object> exportProductionStatistics(
			String productionLine, Date dateFrom, Date dateTo) {
		/**
		 * 设置导出文件文件名和路径
		 */
		String fileName = null;
		String filePath = null;
		try {
			//导出的文件名 规则:年月日时分秒毫秒+.xlsx
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
	        String dateNowStr = sdf.format(new Date());
			fileName = new String(("ProductionStatistics"+dateNowStr+".xlsx").getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(new Date()+e.getMessage());
			//导出失败的提示
			throw new CommonException(MesConstants.UNKNOWN,e.getMessage()); 
		}
		//文件路径
		PropertiesLoad pd=new PropertiesLoad();
		filePath = pd.load("resources","excelExportFilePath");
		/**
		 * 取得数据
		 */
		List<MesWipComV> mwcvList=new ArrayList<MesWipComV>();
		MesWipComVCriteria mwcvc=new MesWipComVCriteria();
		mwcvc.createCriteria().andPlanLineCodeEqualTo(productionLine).andTransactionDateBetween(dateFrom, dateTo);
		mwcvList=mwcvm.selectByExample(mwcvc);
		/**
		 * 导出
		 */
		CreateExcel.createExcel(MesConstants.productionStatisticsTitle, mwcvList, fileName, filePath);

		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put(MesConstants.RESULT, MesConstants.SUCCESS);
		retMap.put("fileName", fileName); 
		return retMap;
	}

	/**
	 * @author sjf
	 * @date 2017年1月9日 
	 * @param fileName
	 * @return
	 * 下载服务器文件到本地
	 */
	public ResponseEntity<byte[]> downloadProductionStatistics(String fileName) {
		ResponseEntity<byte[]> re=null;
		//文件路径
		PropertiesLoad pd=new PropertiesLoad();
		String filePath = pd.load("resources","excelExportFilePath");
		
		String dfileName = null;
		try {
			dfileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//导出失败的提示
			throw new CommonException(MesConstants.UNKNOWN,e.getMessage()); 
		} 
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
		headers.setContentDispositionFormData("attachment", dfileName); 
		File file = new File(filePath, fileName);

		try {
			re=new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		} catch (IOException e) {
			//导出失败的提示
			throw new CommonException(MesConstants.UNKNOWN,e.getMessage()); 
		}
		return re;
	}

	/**
	 * 生产统计日报表_数据查询
	 * @param proLine 生产线
	 * @param startTime 开始日期
	 * @param endTime 结束日期
	 * @return 数据集
	 */
	public List<DailyStatistics> queryDailyStatistics(String proLine, String startTime, String endTime) {
		return mwcvm.queryDailyStatistics(proLine,startTime,endTime); 
	}
}
