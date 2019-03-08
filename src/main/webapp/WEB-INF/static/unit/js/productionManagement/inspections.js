//检验JS
function inspectIsValid(ids){
    var f = false;
    $.ajax({
        async:false,//同步
        type : 'POST',
        url : basePath + "inspectionController/isValid2?ids="+ids,
        timeout:5000,
        success : function(data) {
            var datas = strToJson(data);
            f=datas;
        }
    });
    return f;
}


$(function() {
	//检验扫描单号
	$('#inspectNumber').focus(); 
	$('#inspectNumber').keydown(function(e){
		var key = e.which;
		if(key==13){
			getInspections('refresh');
			$('#inspectNumber').select();
			$('#inspectNumber').focus(); 
		}
		
	});
	
    getInspections();
    //查询
    $("#search_btn").on("click",function () {
        getInspections('refresh');
    });
    //检验
    $('#inspection_btn').click(function(){
        var rows = $('#inspectionsTable').bootstrapTable('getSelections');
        if (rows.length > 0) {
            var ids =[];
            for(var i=0;i<rows.length;i++){
                var row = rows[i];
                if(row.CHECK_STATUS_F != '已提交'){
                    alert("选择数据中包好已检验的数据!");
                    return;
                }
                ids.push(row.PACKING_BARCODE_ID);
            }
            //打开检验框
            $('#inspectionMsgModal').modal({
                show : true,
                backdrop : false
            });
            $('#packingBarcodeId').val(ids);
        }else{
            alert("请选择需要的数据！");
        }
    });

     
    //comfirm_OK_btn
    $('#comfirm_OK_btn').click(function(){
        $('#checkStatus').val('P');//给表单赋值 （通过）
        var ids = $('#packingBarcodeId').val();
        if ( inspectIsValid(ids)){
            eGdUtil.requestJsonAjax(basePath+'inspectionController/toOK',
                $('#inspectionMsgForm').serializeObject(), function(data){
                    if(strToJson(data)){//true 修改成功
                        $('#inspectionMsgModal').modal('hide');
                        $(':input', '#inspectionMsgForm').not(':button, :submit, :reset, :hidden')
                            .val('').removeAttr('checked').removeAttr('selected');
                        getInspections('refresh');
                    }else{alert('数据处理失败');$('#inspectionMsgModal').modal('hide');}
                }
            );
            getInspections('refresh');
        }else{ alert("数据异常，请重新选择");
            $(':input', '#inspectionMsgForm').not(':button, :submit, :reset, :hidden')
                .val('').removeAttr('checked').removeAttr('selected');
            getInspections('refresh');
            $('#inspectionMsgModal').modal('hide');}
    });
    
  //comfirm_NG_btn
    $('#comfirm_NG_btn').click(function(){
        $('#checkStatus').val('E');//给表单赋值 （不通过）
        var ids = $('#packingBarcodeId').val();
        if(confirm('确认是否NG?')){ 
            if ( inspectIsValid(ids)){
                eGdUtil.requestJsonAjax(basePath+'inspectionController/toBack',
                    $('#inspectionMsgForm').serializeObject(), function(data){
                        if(strToJson(data)){//true 修改成功
                            $('#inspectionMsgModal').modal('hide');
                            $(':input', '#inspectionMsgForm').not(':button, :submit, :reset, :hidden')
                                .val('').removeAttr('checked').removeAttr('selected');
                            getInspections('refresh');
                        }else{alert('数据处理失败'); $('#inspectionMsgModal').modal('hide');}
                    }
                );
                getInspections('refresh');
            }else{ alert("数据异常，请重新选择");
                $(':input', '#inspectionMsgForm').not(':button, :submit, :reset, :hidden')
                    .val('').removeAttr('checked').removeAttr('selected');
                getInspections('refresh');
                $('#inspectionMsgModal').modal('hide');
                } 
        }
        
    });
    
  //comfirm_back_btn
    $('#comfirm_back_btn').click(function(){
        $('#checkStatus').val('B');//给表单赋值 （退回）
        var ids = $('#packingBarcodeId').val();
        if(confirm('确认是否退回?')){  
            if ( inspectIsValid(ids)){
                eGdUtil.requestJsonAjax(basePath+'inspectionController/toNG',
                    $('#inspectionMsgForm').serializeObject(), function(data){
                        if(strToJson(data)){//true 修改成功
                            $('#inspectionMsgModal').modal('hide');
                            $(':input', '#inspectionMsgForm').not(':button, :submit, :reset, :hidden')
                                .val('').removeAttr('checked').removeAttr('selected');
                            getInspections('refresh');
                        }else{alert('数据处理失败'); $('#inspectionMsgModal').modal('hide');}
                    }
                );
                getInspections('refresh');
            }else{ alert("数据异常，请重新选择");
                $(':input', '#inspectionMsgForm').not(':button, :submit, :reset, :hidden')
                    .val('').removeAttr('checked').removeAttr('selected');
                getInspections('refresh');
                $('#inspectionMsgModal').modal('hide');}

        }
        
    });
    
    /**
     * 验证 是能输入数字
     */
   /* $('#spe_qty,#major_qty,#sec_qty').on('keyup',function(){
    	var v = $(this).val();
    });*/
});

//初始化列表
function getInspections(refresh) {
    var $table = $('#inspectionsTable');
    if(typeof refresh == 'undefined'){
        $table.bootstrapTable({
            queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    checkStatus : $("#checkStatus_search").val(),
                    inspectNumber : $("#inspectNumber").val(),
                    planLine : $("#search_productionLine").val(),
                    workOrderNumber : $("#workOrderNumber").val(),
                    modelNum : $("#modelNum").val(),
                    moNum : $("#moNum").val()
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
            showColumns : true,
            // 是否显示刷新按钮
            showRefresh : false,
            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            //height: 30,
            //是否只能选择单行
            singleSelect:false,
            //是否启用点击选中行
            clickToSelect: true,
            //中文支持
            dataLocale:"zh-US",
            // 是否启用排序
            sortable : true,
            // 排序方式
            sortOrder : "desc",
            //默认加载条数
            pageSize : 100,
            //每页显示数据条数
            pageList : [100,200,500,1000],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            columns : [{
                checkbox: true,
                align: 'center',
                valign: 'middle'
            },{
                title : '报检单号',
                field : 'INSPECT_NUMBER',
                align : 'center',
                valign : 'middle'
            }, {
                title : '生产线',
                field : 'PLAN_LINE',
                align : 'center',
                valign : 'middle'
            }, {
                title : '大包装箱号',
                field : 'BARCODE_TEXT',
                align : 'center',
                valign : 'middle'
            }, {
                title : '盛放数量',
                field : 'PACK_QUANTITY',
                align : 'center',
                valign : 'middle'
            }, {
                title : '工单',
                field : 'WIP_ENTITY_NAME',
                align : 'center',
                valign : 'middle'
            },{
                title : 'MO单',
                field : 'MO_ORDER',
                align : 'center',
                valign : 'middle'
            }, {
                title : '装配件',
                field : 'SEGMENT1',
                align : 'center',
                valign : 'middle'
            }, {
                title : '产品型号',
                field : 'SEGMENT2',
                align : 'center',
                valign : 'middle'
            }, {
                title : '满包标识',
                field : 'PACK_FLAG',
                align : 'center',
                valign : 'middle'
            }, {
                title : '报检时间',
                field : 'INSPECT_DATE_F',
                align : 'center',
                valign : 'middle'
            },{
                title : '报检结果',
                field : 'CHECK_STATUS_F',
                align : 'center',
                valign : 'middle'
            },{
                title : '检验日期',
                field : 'CHECK_DATE',
                align : 'center',
                valign : 'middle'
            }, {
                title : '产品描述',
                field : 'DESCRIPTION',
                align : 'left',
                valign : 'middle'
            }]
        });
    }else {
        $table.bootstrapTable('refreshOptions',{
            url :  basePath + 'inspectionController/getInspectionsListNew',
            queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    checkStatus : $("#checkStatus_search").val(),
                    inspectNumber : $("#inspectNumber").val(),
                    planLine : $("#search_productionLine").val(),
                    workOrderNumber : $("#workOrderNumber").val(),
                    modelNum : $("#modelNum").val(),
                    moNum : $("#moNum").val()
                };
            }
        });
    }
}