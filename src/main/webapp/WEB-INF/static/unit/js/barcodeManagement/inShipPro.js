
//
var handlerTr ="";
$(function(){ 
	 /**
     *  保存
     */
    $("#update").on("click",function () { 
        var $table = $("#lineTable");
        //产品编码,物料描述，物料id，来源子库，来源货位
        var barcodeTexts=[],inventoryItemIds=[];
        var dom_barcodeTexts = $table.find("input[name='barcodeText']");
        //var dom_descs= $table.find("select[name='desc']");
        var dom_inventoryItemIds = $table.find("input[name='inventoryItemId']"); 
        for(var i = 0;i<dom_barcodeTexts.length;i++){
        	//产品编码
        	var barcodeText = dom_barcodeTexts[i].value;
        	if(barcodeText == null || barcodeText == ''){
        		$.messager.alert("提示","第"+(i+1)+"条产品条码为空","info");
                return;
        	} 
        	barcodeTexts.push(barcodeText);
        	/*//物料描述
        	var dom_desc = dom_descs[i].value;
        	if(dom_desc == null || dom_desc == ''){
                $.messager.alert("提示","第"+(i+1)+"条，请选择操作类型","info");
                return;
            }
        	descs.push(dom_desc);*/
        	//物料id
            var inventoryItemId = dom_inventoryItemIds[i].value;
            if(inventoryItemId == null || inventoryItemId == ''){
                $.messager.alert("提示","第"+(i+1)+"条数据物料编码不存在，请重新输入","info");
                return;
            }
            inventoryItemIds.push(inventoryItemId); 
             
        }
        $.ajax({
            type : 'post',
            url : basePath + "inShipProController/batchInsertToShip",
            data : {
            	//type:descs.join(","),
            	barcodeTexts:barcodeTexts.join(","),
            	inventoryItemIds : inventoryItemIds.join(",")
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                  if($msg.success){
	                $.messager.alert("提示",$msg.message,"info",function () { 
	                	$table.empty();
	                	location.reload();
	                });
                  }else{
                	  $.messager.alert("提示",$msg.message,"info",function () {  
  	                });
                  }
            }
        });
    });  
	/**
	 * 确认选择
	 */
	$("#choose_ok").on("click",function(){
		var selectRows = $('#modalTable').bootstrapTable('getSelections');
		if(selectRows.length == 0){
			alert("请选择数据!");
			return;
		}  
		var $segment1 = handlerTr.find("input[name='segment1']");
		var $description =handlerTr.find("input[name='description']");
	    var $prodType = handlerTr.find("input[name='prodType']");
	    var $inventoryItemId = handlerTr.find("input[name='inventoryItemId']");
	    $segment1.val(selectRows[0].segment1);
	    $description.val(selectRows[0].description);
	    $prodType.val(selectRows[0].prodType);
	    $inventoryItemId.val(selectRows[0].inventoryItemId);
		//关闭模态框
		$("#myModal").modal("hide");
	}); 
	/**
	 * 搜索
	 */
	$("#search").on("click",function(){
		getModalTable("refresh");
	}); 
  	
});

/**
 * 点击选择按钮
 * @param th
 */
function clicks(th) {
    var $this = $(th);
    var $tr = $this.parent().parent();
    /*var desc =$this.val().trim();
    var $prodType = $tr.find("input[name='prodType']");
    var $inventoryItemId = $tr.find("input[name='inventoryItemId']");
    var $choose = $tr.find("input[name='choose']");*/
    //打开模态框
	$("#myModal").modal();
	handlerTr = $tr ;
	getModalTable();  
} 

//物料查询
function getModalTable(refresh) {
	var url = basePath + 'inShipProController/getModalTableLists';
	var $table = $('#modalTable');
	if(typeof refresh == 'undefined'){
		$table.bootstrapTable({
			url : url,
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					findName : $("#find_name").val().trim()
				};
			},
			//分页方式：client客户端分页，server服务端分页（*）
			sidePagination : "server",
			//是否分页
			pagination : true,
			//请求方法 post方式会自动把参数封装为json格式
			method : 'get',
			//服务器返回的数据类型
			//dataType : "json",
			//是否显示行间隔色
			striped : true,
			//是否显示所有的列
			showColumns : false,
			// 是否显示刷新按钮
			showRefresh : false,
			//行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			//height: 30,
			//是否只能选择单行
			singleSelect:true,
			//是否启用点击选中行
			clickToSelect: true,
			//中文支持
			dataLocale:"zh-US",
			//默认加载条数
			pageSize : 10,
			//每页显示数据条数
			pageList : [10,20,50,100],
			// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			cache : false,
			// 是否启用排序
			sortable : false,
			// 排序方式
			sortOrder : "desc",
			columns: [
				{
					checkbox: true,
					align: 'center',
					valign: 'middle'
				}, {
					title: '产品型号',
					field: 'prodType',
					align: 'center',
					valign: 'middle'
				}, {
					title: '物料编码',
					field: 'segment1',
					align: 'center',
					valign: 'middle'
				}, {
					title: '物料描述',
					field: 'description',
					align: 'center',
					valign: 'middle'
				}
			]
		});
	}else {
		$table.bootstrapTable('refreshOptions',{
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					findName : $("#find_name").val().trim()
				};
			}
		});
	}
}
/**
 * 删除行
 * @param th this
 */
function minus(th) {
    var $this = $(th);
    var $tr = $this.parent().parent();
    var $tbody = $tr.parent();
    if($tbody.children().length == 1) return;
    $tr.remove();
}

/**
 * 添加行
 * @param th this
 */
function plus(th) {
    var $this = $(th);
    var $tr = $this.parent().parent();
    var $tbody = $tr.parent();
    var $tr_clone = $tr.clone();
    $tr_clone.appendTo($tbody).find("input").val(""); 
     
}