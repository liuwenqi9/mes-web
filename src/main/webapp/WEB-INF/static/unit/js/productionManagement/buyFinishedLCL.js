/*****
 * 采购成品拼箱界面JS
 */

//初始化
function init(){
	//光标初始定位到箱号输入框
	$('#packageBarcodeId').focus();
	/**
	 * 箱号输入框回车定位到产品条码输入框
	 */
    $('#packageBarcodeId').keydown(function (e) {
        if (e.keyCode == 13) {
            $.ajax({
            	type : 'post',
				url : basePath + 'buyFinishedLCLController/scanPackageBarcode',
				data : {
                    packageBarcode: $('#packageBarcodeId').val()
				},
				success : function (msg) {
                    var $msg = $.parseJSON(msg);
                    if ($msg.success) {
						$("#packageCount").val($msg.message);
                        //光标定位
                        $('#wipBarcodeId').focus();
                        return;
                    }
                    alert($msg.message);
                }
			});
        }
    });
	/**
	 * 产品条码输入框回车查询返回结果
	 */
	$('#wipBarcodeId').keydown(function(e){
		if (e.keyCode == 13) {
			var packageBarcode=$('#packageBarcodeId').val();
			var wipBarcode=$('#wipBarcodeId').val();
			if(eGdUtil.isEmpty(packageBarcode.trim())){
				alert('箱号不能为空！');
				return;
			}
			if(eGdUtil.isEmpty(wipBarcode.trim())){
				alert('产品条码不能为空！');
				return;
			}
			if(packageBarcode==wipBarcode){
				alert('箱号与产品条码相同，请检查！');
     			//产品条码清空
     			$('#wipBarcodeId').val('');
				return;
			}
			/*
			 * 校验，如果已经录入，则不可重复录入
			 */
			 var rows = $('#buyFinishedLCLTable').bootstrapTable('getOptions').data;
		     if (rows.length > 0) {
		         for (var i = 0; i < rows.length; i++) {
		             var row = rows[i];
		             if(row.PACKING_BARCODE_ID){
		            	 //如果结果grid里已存在，则报错
		            	 if(packageBarcode==row.BARCODE_TEXT&&wipBarcode==row.WIPBARCODE){
		            		 alert('该条码已扫描');
		         			//产品条码清空
		         			$('#wipBarcodeId').val('');
		            		 return;
		            	 }
		             }
		         }
		     }
			/*
			 * 查询包装箱信息
			 */
			var params = { 
				packageBarcode : packageBarcode,
				wipBarcode : wipBarcode
			};
			var url = basePath + 'buyFinishedLCLController/scanWipBarcode';
			var success = function(datas) {
				//可包装数量
                var packageCount = $("#packageCount").val();
                //dataTable数量总条数
                var data = $('#buyFinishedLCLTable').bootstrapTable('getData');
                if(data.length < packageCount){
					//查询成功则插入到结果grid中
                    $('#buyFinishedLCLTable').bootstrapTable('append', datas);
                    //刷新已扫描数量
                    var scanedCount=$('#buyFinishedLCLTable').bootstrapTable('getOptions').data.length;
                    $('#scanedCount').val(scanedCount);
				}else {
					alert("包装数量不能大于可包装数量");
				}

			};
			eGdUtil.requestJsonAjax(url, params, success);
			//产品条码清空
			$('#wipBarcodeId').val('');
         } 
	});
	/**
	 * 确认清空
	 */
	$('#confirmBtn').click(function(e){
		$('#packageBarcodeId').val('');
		$('#wipBarcodeId').val('');
		$('#buyFinishedLCLTable').bootstrapTable('removeAll');
		$('#cleanModal').modal('hide');
		//刷新已扫描数量
		var scanedCount=$('#buyFinishedLCLTable').bootstrapTable('getOptions').data.length;
		$('#scanedCount').val(scanedCount);
	});
	
}
/**
 * 删除按钮
 */
function deleteLCL(){
	var rows = $('#buyFinishedLCLTable').bootstrapTable('getSelections');
	if (rows.length > 0) {
       var selects = $('#buyFinishedLCLTable').bootstrapTable('getSelections');  
       var wipBarcodeArray = $.map(selects, function (row) {  
           return row.WIPBARCODE;  
       });  
 
       $('#buyFinishedLCLTable').bootstrapTable('remove', {  
           field: 'WIPBARCODE',  
           values: wipBarcodeArray  
       });
		//刷新已扫描数量
		var scanedCount=$('#buyFinishedLCLTable').bootstrapTable('getOptions').data.length;
		$('#scanedCount').val(scanedCount);
	} else {
		alert("请选择需要删除的数据");
	}
}
/**
 * 清空
 */
function clean(){
	$('#cleanModal').modal({
		show : true
	});
}
/**
 * 提交
 */
function commit(){
	 var rows = $('#buyFinishedLCLTable').bootstrapTable('getOptions').data;
     if (rows.length > 0) {
    	 //拼接后的包装箱id
         var packageBarcodeIds='';
         //拼接后的产品条码
         var wipBarcodes='';
         //拼接
         for (var i = 0; i < rows.length; i++) {
             var row = rows[i];
             if(row.PACKING_BARCODE_ID){
            	packageBarcodeIds+=row.PACKING_BARCODE_ID+',';
             	wipBarcodes+=row.WIPBARCODE+',';
             }
         }
         //去掉最后的字符,
         packageBarcodeIds=packageBarcodeIds.substring(0,packageBarcodeIds.length-1);
         wipBarcodes=wipBarcodes.substring(0,wipBarcodes.length-1);
         /*
          * 提交
          */
		 var params = { 
			packageBarcodeIds : packageBarcodeIds ,
		 	wipBarcodes : wipBarcodes
		 };
		 var url = basePath + 'buyFinishedLCLController/commit';
		 var success = function(datass) { 
		 	showInfoMsg('提交成功');
		 	//清空
		 	$('#confirmBtn').click();
         };
		 eGdUtil.requestJsonAjax(url, params, success);
     }
}
$(function() {
	//初始化结果Grid
	$('#buyFinishedLCLTable').bootstrapTable();
	// 初始化控件
	init();

})
