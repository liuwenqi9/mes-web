/**
 * 
 */
var HEADER_ID_EDIT = '';
$(function() { 
	
	// 分页条数
	var userTablePageSize = getServerParam(basePath, 'PAGE_SIZE');
	//alert(23);
	// 构造表格
	$('#capacityPacking_table').datagrid({
		title : '包装箱列表',
		url : basePath + 'packagecontentController/getMesCapacityPackingList',
		//url:'bb.json',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		pageSize : userTablePageSize / 2,
		pageList : [ userTablePageSize / 2, userTablePageSize ],
		toolbar : '#capacityPacking_table_param',
		queryParams : {}, 
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
		}, {
			title : '小包装朴准容量',
			field : 'S_QUANTITY',
			align : 'left',
			width : 100
		}, {
			title : '大包装朴准容量',
			field : 'B_QUANTITY',
			align : 'left',
			width : 100
		},{
			title : '最后更新时间',
			field : 'LAST_UPDATE_DATE',
			align : 'left',
			width : 100,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleDateString();
				}
			}
		}, {
			title : '最后更新用户',
			field : 'LOGIN_NAME',
			align : 'left',
			width : 100
		}] ],
		onClickRow : function(rowIndex, rowData) {// ORGANIZATION_ID INVENTORY_ITEM_ID
			HEADER_ID_EDIT = rowData.HEADER_ID+'--'+rowData.SEGMENT1+'--'+rowData.DESCRIPTION+'--'
			+rowData.PROD_TYPE+'--'+rowData.S_QUANTITY+'--'+rowData.B_QUANTITY+'--'
			+rowData.ORGANIZATION_ID+'--'+rowData.INVENTORY_ITEM_ID;
			
		},
		
		onLoadSuccess : function(data) {
			 
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	}); 
	// 绑定查询按钮事件
	$('#capacityPacking_table_search_btn').click(function() {
		$('#capacityPacking_table').datagrid('load', {
			segment1 : $('#SEGMENT1').val() 
		});
	});

	// 绑定查询按钮
	$('#SEGMENT1').keydown(function(e) {
		if (e.keyCode == 13) {
			$('#capacityPacking_table_search_btn').click();
		}
	});

	// 新增按钮
	$('#capacityPacking_table_add_btn').click(function() {
		// 弹出新增窗口
		$('#capacityPacking_window_add').window({
			title : '新增',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 500,
			height : 200,
			href : basePath + 'packagecontentController/toMesCapacityPackingAdd'
			//href : ''
		});
	});
	
	// 修改按钮
	$('#capacityPacking_table_edit_btn').click(function() {
		var row = $('#capacityPacking_table').datagrid('getSelected'); 
		if (row) {
			// 弹出新增窗口
			$('#capacityPacking_window_edit').window({
				title : '修改',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 500,
				height : 200,
				href : basePath + 'packagecontentController/toMesCapacityPackingEdit?HEADER_ID_EDIT='+HEADER_ID_EDIT
				
			});
		} else {
			// 登录失败,弹出提示
			showErrorMsg('请选择要修改的数据');
		}
	});

	   
});