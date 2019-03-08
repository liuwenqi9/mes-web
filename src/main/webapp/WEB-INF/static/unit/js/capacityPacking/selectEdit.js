/**
 * 
 */
$(function() {
	
	//加载物料编码--数据 
	$('#dg').datagrid({
		title : '物料列表',
		url : basePath + 'packagecontentController/getSegment1', 
		method : 'post',
	//	fit : true,
		rownumbers : false,
		singleSelect : true,
		pagination : true,
		pageSize : 10 / 2,
		pageList : [ 10 / 2, 10 ],
		toolbar : '#select_table_param', 
		columns : [ [ 
		{
			title : '物料编码',
			field : 'SEGMENT1',
			align : 'left',
			width : 100
		}, {
			title : '物料描述',
			field : 'DESCRIPTION',
			align : 'left',
			width : 350
		}, {
			title : '产品类型',
			field : 'PROD_TYPE',
			align : 'left',
			width : 100
		}
		] ],
		onClickRow : function(rowIndex, rowData) { 
			
		},
		onDblClickRow:function(rowIndex, rowData) { //alert(rowData.INVENTORY_ITEM_ID);
			$('#materialCode_edit').val(rowData.SEGMENT1);
			$('#materialDes_edit').val(rowData.DESCRIPTION);
			$('#packingType_edit').val(rowData.PROD_TYPE);
			$('#organization_id_edit').val(rowData.ORGANIZATION_ID);
			$('#inventory_item_id_edit').val(rowData.INVENTORY_ITEM_ID);
			//INVENTORY_ITEM_ID,ORGANIZATION_ID
			$('#table_bb3_edit').window('close');
			
		},
		onLoadSuccess : function(data) {
			 
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	}); 
	
	// 绑定查询按钮事件
	$('#select_table_search_btn').click(function() {
		$('#dg').datagrid('load', {
			segment1 : $('#select_SEGMENT1').val(),
			prodType : $('#select_PROD_TYPE').val() 
		});
	});
	// 绑定查询按钮
	$('#select_SEGMENT1').keydown(function(e) {
		if (e.keyCode == 13) {
			$('#select_table_search_btn').click();
		}
	});
	// 绑定查询按钮
	$('#select_PROD_TYPE').keydown(function(e) {
		if (e.keyCode == 13) {
			$('#select_table_search_btn').click();
		}
	});
	
});
