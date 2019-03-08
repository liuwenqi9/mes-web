package com.egdfrm.unit.model.stock; 

import java.io.Serializable;   
/**
 * 售后服务机品质分析报表模型类
 * @author hgb
 * @date 2017-6-27
 */
@SuppressWarnings("serial")
public class AfterSaleStatisticsExcel implements Serializable{ 
	
	private String start_time;//开始时间
	private String end_time; //结束时间
	private String ship_date_start;//出货开始日期
	private String ship_date_end;//出货结束日期
    private String rep_type; //退回类型 
    
    private String rep_work_order;//维修单
    private String outer_contact;//外箱是否有联系方式
    private String inter_note;//内箱是否有送货单
    private String return_operation_time;// 退回时间
	private String custom_name; //客户
	private String contact_name;//联系人
	private String phone; //联系电话
	private String address;//客户地址 
    private String model;  //型号 
    private String segment1; //料号
    private String product_barcode; //机身码
    private Integer return_qty; //退货件数 
    private String maintain_date; //维修时间
    private String rep_people;//维修人员
    private String status; // 出货状态
    private String delivery_operation_time; //出货日期
    private String sh_logi_com; // 出货物流公司
    private String sh_exp_no; // 出货物流单号
    private String re_exp_no;//退货物流单号
    private String customer_feedback;//客户反馈
    private String issue_description;//故障描述
    private String rep_reason; //故障原因
    private String remakr; //备注
    private String wip_name;//生产工单
    private String prod_date;//生产日期
    private String line_code;//生产线别
    private String ship_date;//发运日期
    private String cus_name ;//发运客户
    private String supplier_delivery_info;//供应商快递信息
    private String hand_over_date;//交仓库日期
    
    
    
	 
	public String getShip_date_start() {
		return ship_date_start;
	}
	public void setShip_date_start(String ship_date_start) {
		this.ship_date_start = ship_date_start;
	}
	public String getShip_date_end() {
		return ship_date_end;
	}
	public void setShip_date_end(String ship_date_end) {
		this.ship_date_end = ship_date_end;
	}
	public String getHand_over_date() {
		return hand_over_date;
	}
	public void setHand_over_date(String hand_over_date) {
		this.hand_over_date = hand_over_date;
	}
	public String getSupplier_delivery_info() {
		return supplier_delivery_info;
	}
	public void setSupplier_delivery_info(String supplier_delivery_info) {
		this.supplier_delivery_info = supplier_delivery_info;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getRep_type() {
		return rep_type;
	}
	public void setRep_type(String rep_type) {
		this.rep_type = rep_type;
	}
	public String getRep_work_order() {
		return rep_work_order;
	}
	public void setRep_work_order(String rep_work_order) {
		this.rep_work_order = rep_work_order;
	}
	public String getOuter_contact() {
		return outer_contact;
	}
	public void setOuter_contact(String outer_contact) {
		this.outer_contact = outer_contact;
	}
	public String getInter_note() {
		return inter_note;
	}
	public void setInter_note(String inter_note) {
		this.inter_note = inter_note;
	}
	public String getReturn_operation_time() {
		return return_operation_time;
	}
	public void setReturn_operation_time(String return_operation_time) {
		this.return_operation_time = return_operation_time;
	}
	public String getCustom_name() {
		return custom_name;
	}
	public void setCustom_name(String custom_name) {
		this.custom_name = custom_name;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getProduct_barcode() {
		return product_barcode;
	}
	public void setProduct_barcode(String product_barcode) {
		this.product_barcode = product_barcode;
	}
	public Integer getReturn_qty() {
		return return_qty;
	}
	public void setReturn_qty(Integer return_qty) {
		this.return_qty = return_qty;
	}
	public String getMaintain_date() {
		return maintain_date;
	}
	public void setMaintain_date(String maintain_date) {
		this.maintain_date = maintain_date;
	}
	public String getRep_people() {
		return rep_people;
	}
	public void setRep_people(String rep_people) {
		this.rep_people = rep_people;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDelivery_operation_time() {
		return delivery_operation_time;
	}
	public void setDelivery_operation_time(String delivery_operation_time) {
		this.delivery_operation_time = delivery_operation_time;
	}
	public String getSh_logi_com() {
		return sh_logi_com;
	}
	public void setSh_logi_com(String sh_logi_com) {
		this.sh_logi_com = sh_logi_com;
	}
	public String getSh_exp_no() {
		return sh_exp_no;
	}
	public void setSh_exp_no(String sh_exp_no) {
		this.sh_exp_no = sh_exp_no;
	}
	public String getRe_exp_no() {
		return re_exp_no;
	}
	public void setRe_exp_no(String re_exp_no) {
		this.re_exp_no = re_exp_no;
	}
	public String getIssue_description() {
		return issue_description;
	}
	public void setIssue_description(String issue_description) {
		this.issue_description = issue_description;
	}
	
	public String getCustomer_feedback() {
		return customer_feedback;
	}
	public void setCustomer_feedback(String customer_feedback) {
		this.customer_feedback = customer_feedback;
	}
	public String getRep_reason() {
		return rep_reason;
	}
	public void setRep_reason(String rep_reason) {
		this.rep_reason = rep_reason;
	}
	public String getRemakr() {
		return remakr;
	}
	public void setRemakr(String remakr) {
		this.remakr = remakr;
	}
	public String getWip_name() {
		return wip_name;
	}
	public void setWip_name(String wip_name) {
		this.wip_name = wip_name;
	}
	public String getProd_date() {
		return prod_date;
	}
	public void setProd_date(String prod_date) {
		this.prod_date = prod_date;
	}
	public String getLine_code() {
		return line_code;
	}
	public void setLine_code(String line_code) {
		this.line_code = line_code;
	}
	public String getShip_date() {
		return ship_date;
	}
	public void setShip_date(String ship_date) {
		this.ship_date = ship_date;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
    
    
	 
    
	 
    
   
    

}
