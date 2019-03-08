package com.egdfrm.unit.model.stock; 

import java.io.Serializable;   
/**
 * QA检验报告 模型类
 * @author hgb
 * @date 2017-6-27
 */
@SuppressWarnings("serial")
public class QACheckReportExcel implements Serializable{
	
		private String check_date_start;
		private String check_date_end;
	
	  private String check_date;   // --检验日期
      private String inspect_number;   // --送检单
      private String line_code;   // --线别MES_PACKING_CHECK_REPORT_V
      private String user_name;   // --检验员
      private String model;   // --机型
      private String segment1;   // --成品编码
      private String mo;    // --MO
      private Integer pack_qty;   // --送检数量
      private Integer aql;     // --抽样数量
      private Integer maj_qty;   // --AQL允收MAJ(0.4);   //
      private Integer min_qty;   // ----AQL允收MIN(1.0);   //
      private Integer spe_qty;    //--不良严重
      private Integer major_qty;   //--不良主要
      private Integer sec_qty;     //--不良次要
      private String check_status; //-- 检查结果
      private String check_msg;   //--不良现象
      private String check_remark; // --备注
	
      public String getCheck_date_start() {
		return check_date_start;
	}
	public void setCheck_date_start(String check_date_start) {
		this.check_date_start = check_date_start;
	}
	public String getCheck_date_end() {
		return check_date_end;
	}
	public void setCheck_date_end(String check_date_end) {
		this.check_date_end = check_date_end;
	}
	public String getCheck_date() {
		return check_date;
	}
	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}
	public String getInspect_number() {
		return inspect_number;
	}
	public void setInspect_number(String inspect_number) {
		this.inspect_number = inspect_number;
	}
	public String getLine_code() {
		return line_code;
	}
	public void setLine_code(String line_code) {
		this.line_code = line_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getMo() {
		return mo;
	}
	public void setMo(String mo) {
		this.mo = mo;
	}
	public Integer getPack_qty() {
		return pack_qty;
	}
	public void setPack_qty(Integer pack_qty) {
		this.pack_qty = pack_qty;
	}
	public Integer getAql() {
		return aql;
	}
	public void setAql(Integer aql) {
		this.aql = aql;
	}
	public Integer getMaj_qty() {
		return maj_qty;
	}
	public void setMaj_qty(Integer maj_qty) {
		this.maj_qty = maj_qty;
	}
	public Integer getMin_qty() {
		return min_qty;
	}
	public void setMin_qty(Integer min_qty) {
		this.min_qty = min_qty;
	}
	public Integer getSpe_qty() {
		return spe_qty;
	}
	public void setSpe_qty(Integer spe_qty) {
		this.spe_qty = spe_qty;
	}
	public Integer getMajor_qty() {
		return major_qty;
	}
	public void setMajor_qty(Integer major_qty) {
		this.major_qty = major_qty;
	}
	public Integer getSec_qty() {
		return sec_qty;
	}
	public void setSec_qty(Integer sec_qty) {
		this.sec_qty = sec_qty;
	}
	public String getCheck_status() {
		return check_status;
	}
	public void setCheck_status(String check_status) {
		this.check_status = check_status;
	}
	public String getCheck_msg() {
		return check_msg;
	}
	public void setCheck_msg(String check_msg) {
		this.check_msg = check_msg;
	}
	public String getCheck_remark() {
		return check_remark;
	}
	public void setCheck_remark(String check_remark) {
		this.check_remark = check_remark;
	}
	
	 

}
