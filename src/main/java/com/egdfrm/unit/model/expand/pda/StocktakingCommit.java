package com.egdfrm.unit.model.expand.pda;

import java.util.List;

/**
 * 盘点提交模型类
 * */
public class StocktakingCommit {
	// userid，warehouse，stockTaking
	// "checkNo", "wareLoca", "snNo", "num"
	private String userid;
	private String warehouse;
	private List<StockTaking> stockTaking;
	
	
	
	public StocktakingCommit() {
		
	}
	public StocktakingCommit(String userid, String warehouse,
			List<StockTaking> stockTaking) {
		super();
		this.userid = userid;
		this.warehouse = warehouse;
		this.stockTaking = stockTaking;
	}



	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public List<StockTaking> getStockTaking() {
		return stockTaking;
	}
	public void setStockTaking(List<StockTaking> stockTaking) {
		this.stockTaking = stockTaking;
	}



	@Override
	public String toString() {
		return "StocktakingCommit [userid=" + userid + ", warehouse="
				+ warehouse + ", stockTaking=" + stockTaking + "]";
	}



	public class StockTaking {
		private String checkNo;
		private String wareLoca;
		private String snNo;
		private String num;
		
		
		
		public StockTaking() {
			
		}
		public StockTaking(String checkNo, String wareLoca, String snNo,
				String num) {
			super();
			this.checkNo = checkNo;
			this.wareLoca = wareLoca;
			this.snNo = snNo;
			this.num = num;
		}
		public String getCheckNo() {
			return checkNo;
		}
		public void setCheckNo(String checkNo) {
			this.checkNo = checkNo;
		}
		public String getWareLoca() {
			return wareLoca;
		}
		public void setWareLoca(String wareLoca) {
			this.wareLoca = wareLoca;
		}
		public String getSnNo() {
			return snNo;
		}
		public void setSnNo(String snNo) {
			this.snNo = snNo;
		}
		public String getNum() {
			return num;
		}
		public void setNum(String num) {
			this.num = num;
		}
		@Override
		public String toString() {
			return "StockTaking [checkNo=" + checkNo + ", wareLoca=" + wareLoca
					+ ", snNo=" + snNo + ", num=" + num + "]";
		}
		
	}
}
