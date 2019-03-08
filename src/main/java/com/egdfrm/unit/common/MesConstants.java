package com.egdfrm.unit.common;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;


public class MesConstants {

    /**
     * <p>
     * Field SUCCESS: 成功
     * </p>
     */
    public static final String SUCCESS = "S";
    /**
     * <p>
     * Field WARING: 警告
     * </p>
     */
    public static final String WARING = "W";
    /**
     * <p>
     * Field ERROR: 错误
     * </p>
     */
    public static final String ERROR = "E";
    /**
     * <p>
     * Field SMALL_PACK: 条码首字母#小包装
     * </p>
     */
    public static final char SMALL_PACK='S';
    /**
     * <p>
     * Field BIG_PACK: 条码首字母#大包装
     * </p>
     */
    public static final char BIG_PACK='B';
    /**
     * <p>
     * Field SHIP_PACK: 条码首字母#发运包装
     * </p>
     */
    public static final char SHIP_PACK='P';
    /**
     * <p>
     * Field NX_PRODUCT: 条码首字母#内销
     * </p>
     */
    public static final char NX_PRODUCT='C';
    /**
     * <p>
     * Field WX_PRODUCT: 条码首字母#外销
     * </p>
     */
    public static final char WX_PRODUCT='H';
    /**
     * <p>
     * Field SECOND_PACKING: 二次包装
     * </p>
     */
    public static final String SECOND_PACKING="SP";
    /**
     * <p>
     * Field SHIP_PACKING: 发运包装
     * </p>
     */
    public static final String SHIP_PACKING="SH";
    /**
     * <p>
     * Field PACK: 包装箱
     * </p>
     */
    public static final int PACK=0;
    /**
     * <p>
     * Field WIP: 产品
     * </p>
     */
    public static final int WIP=1;

    public static final String OBJECT="OBJ";
    public static final String TYPE="type";
    public static final String RETVAL="retVal";
    public static final String RESULT="flag";
    public static final String DBRETURN="dbreturn";
    public static final String ERROR_MESSAGE="errMsg";
    /**
     * CRM的webservice返回参数
     */
    public static final String BATCH_ID="bat_id";
    public static final String STATUS="status";
    public static final String MESSAGE="message";
    
    /**
     * <p>
     * Field YES = "Y"
     * </p>
     */
    public static final String YES = "Y";
    /**
     * <p>
     * Field NO = "N"
     * </p>
     */
    public static final String NO = "N";
    
    public static final String UNKNOWN="unknown";
    

    /**
     * <p>
     * Field P: CRM销售订单首字母#普通订单
     * </p>
     */
    public static final char CRMORDERNUMBER_P='P';
    /**
     * <p>
     * Field X: CRM销售订单首字母#项目订单
     * </p>
     */
    public static final char CRMORDERNUMBER_X='X';
    
    
    public static final BigDecimal Big0=new BigDecimal(0);
    public static final BigDecimal Big101=new BigDecimal(101);
    public static final BigDecimal Big1001=new BigDecimal(1001);
    public static final BigDecimal Big1000=new BigDecimal(1000);
    
	//导出生产统计日报表excel表头
	public static final Map<String,String> productionStatisticsTitle;
	static {
		productionStatisticsTitle = new LinkedHashMap<String,String>();
		productionStatisticsTitle.put("生产线", "planLineCode");
		productionStatisticsTitle.put("工单号", "wipEntityName");
		productionStatisticsTitle.put("物料编号", "segment1");
		productionStatisticsTitle.put("描述", "description");
		productionStatisticsTitle.put("型号", "segment2");
		productionStatisticsTitle.put("MO号", "moOrder");
		productionStatisticsTitle.put("工单数量", "startQuantity");
		productionStatisticsTitle.put("生产数量", "transactionQuantity");
	}

}
