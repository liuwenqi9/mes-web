
//初始化



function commit(){
	 
}



$(function() {
	getList(); 
	
	$('#search_btn').on('click',function(){ 
		var barcodeId =$("#barcodeId").val().trim();
		if(barcodeId==''){alert('输入箱号'); return;}
		getList('refresh');
	});
	
	$('#confirm_btn').on('click',function(){
		var rows = $('#outsourcingTable').bootstrapTable('getSelections');
		if(rows.length<1){alert('请选择数据'); return;}
		var barcode_id = rows[0].PACKING_BARCODE_ID;
		var prod_ids = [];
        for (var i = 0; i < rows.length; i++) { 
        	var row =rows[i];
        	prod_ids.push(row.WIP_BARCODE_ID);
        }
		if(confirm('确认是否拆箱?')){
				$.ajax({
		             type : 'post',
		             url : basePath+'outsourcingSplitController/confirm?prodIds='+prod_ids, 
		             data:{barcodeId:barcode_id },
		             success : function (msg) {
		                 var $msg = $.parseJSON(msg);
		                 if($msg.success){
			                	showInfoMsg($msg.message);
			                	$('#search_btn').click();
			                }else{
			                	showErrorMsg($msg.message);
			                }
		             }
		         });
			
			
		}else{
			
		}
	});

});



//查询——btn
//outsourcingSplitController/getOutsourcingByBarcode
function getList(refresh) {
  var $table = $('#outsourcingTable');
  if(typeof refresh == 'undefined'){
	  $table.bootstrapTable("destroy");
      $table.bootstrapTable({ 
          columns : [{
              checkbox: true,
              align: 'center',
              valign: 'middle'
          },{
              title : '箱号',
              field : 'BARCODE_TEXT',
              align : 'center',
              valign : 'middle'
          }, {
              title : '工单号',
              field : 'WIP_ENTITY_NAME',
              align : 'center',
              valign : 'middle'
          }, {
              title : '产品条码',
              field : 'PROD_TEXT',
              align : 'center',
              valign : 'middle'
          }, {
              title : '产品编码',
              field : 'ITEM',
              align : 'center',
              valign : 'middle'
          }, {
              title : '型号',
              field : 'SEGMENT2',
              align : 'center',
              valign : 'middle'
          }, {
              title : '产品描述',
              field : 'DESCRIPTION',
              align : 'center',
              valign : 'middle'
          }]
      });
	  } else {
		$table.bootstrapTable('refreshOptions', {
			method : 'get',
			dataType : "json",
			cache : false,
			//是否启用点击选中行
			clickToSelect : true,
			//是否显示行间隔色
			striped : true,
			url : basePath
					+ 'outsourcingSplitController/getOutsourcingByBarcode',
			queryParams : function(params) {
				return {
					barcodeText : $("#barcodeId").val().trim()
				};
			}
		});
	}
}