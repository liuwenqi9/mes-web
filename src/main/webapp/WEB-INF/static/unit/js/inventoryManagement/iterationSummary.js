
$(function(){
	getSummaryTable();
	
	
	$('#excel_btn').on('click',function(){
		//confirmAndCancelModal
		$('#confirmAndCancelModal').modal();   
	});
	
	$('#confirm_btn').on('click',function(){  
		$('#confirmAndCancelModal').modal('hide');
		window.open(basePath + 'stocktakingController/exportExcel?headerId='+ $('#headerId').val()
				+'&organizationId='+ $('#organizationId').val());
		
	});
});

//盘点汇总信息table
function getSummaryTable() {
	var $summaryTable = $('#summaryTable');
	$summaryTable.bootstrapTable("destroy");
	$summaryTable.bootstrapTable({
		url : basePath + 'stocktakingController/getIterationSummary?headerId='+ $('#headerId').val()
		+'&organizationId='+ $('#organizationId').val(),
		queryParams : function(params) { 
		}, 
		method : 'get', 
		dataType : "json",  
		cache : false, 
		columns : [ {
			title : '盘点序列号',
			field : 'PD_NUMBER',
			align : 'center',
			valign : 'middle'
		}, {
			title : '盘点时间',
			field : 'PD_DATE',
			align : 'center',
			valign : 'middle'
		}, {
			title : '子库',
			field : 'SUBINVENTORY_CODE',
			align : 'center',
			valign : 'middle'
		}, {
			title : '货位',
			field : 'LOCATTION_CODE',
			align : 'center',
			valign : 'middle'
		}, {
			title : '物料编号',
			field : 'SEGMENT1',
			align : 'center',
			valign : 'middle'
		},{
			title : '规格型号',
			field : 'PROD_TYPE',
			align : 'center',
			valign : 'middle' 
		}, {
                title : 'MO单',
                field : 'MO',
                align : 'center',
                valign : 'middle'
            }, {
			title : 'ERP</br>库存数',
			field : 'ERP_ONHAND_QUANTITY',
			align : 'center',
			valign : 'middle'
		},{
			title : '条码</br>库存数',
			field : 'PD_QUANTITY',
			align : 'center',
			valign : 'middle'
		}, {
			title : '条码</br>盘存数',
			field : 'BARCODE_QUANTITY',
			align : 'center',
			valign : 'middle'
		}, {
			title : '差异数',
			field : 'XX',
			align : 'center',
			valign : 'middle',
			formatter:function(value,row,index){ 
				 		var e = row.BARCODE_QUANTITY-row.PD_QUANTITY;
                        return e;  
                    }
		},{
			title : '物料描述',
			field : 'DESCRIPTION',
			align : 'left',
			valign : 'middle'
		} ]
	});
	 
}


 

 