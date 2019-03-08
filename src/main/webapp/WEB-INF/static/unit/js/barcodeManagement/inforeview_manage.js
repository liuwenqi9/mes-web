//条码信息追溯
$(function () {
    //初始化表格
    createBasicTable();

    //基本信息
    var $basic_btn = $("#basic_btn");
    //出货信息
    var $shipment_btn = $("#shipment_btn");
    //退货信息
    var $back_btn = $("#back_btn");
    //返修信息
    var $repair_btn = $("#repair_btn");
    //杂项交易
    var $miscellaneous_btn = $("#miscellaneous_btn");
    var $btns = [$basic_btn,$shipment_btn,$back_btn,$repair_btn,$miscellaneous_btn];

    //查询btn
    $('#inforeview_search_btn').on('click',function(){
        for(var i = 0;i < $btns.length;i++){
            var $btn = $btns[i];
            var btn_id = $btn.attr("id");
            var selected = $btn.attr("selected");
            if(selected == 'selected'){
                var index = btn_id.indexOf("_btn");
                //得到当前选择table的id
                var tableId = btn_id.substring(0,index)+"Table";
                var $table = $("#"+tableId);
                $table.bootstrapTable('refreshOptions',{
                    url : basePath + $table.attr("url"),
                    queryParams : function(params) {
                        return tableParam(params);
                    }
                });
            }
        }
    });

    $("#exportExcel").on("click",function () { 
    	//产品条码
        var barcodeNum = $("#barcodeNum").val().trim();
        //工单号
        var workOrderNumber = $("#workOrderNumber").val().trim();
        //MO单
        var moNumber = $("#moNumber").val().trim(); 
        for(var i = 0;i < $btns.length;i++){
            var $btn = $btns[i]; 
            var selected = $btn.attr("selected");
            if(selected == 'selected'){
            	      if(i==0){
            		window.open(basePath+"/info/review/basicPageExportExcel?barcodeNum=" +barcodeNum+"&workOrderNumber="+workOrderNumber+"&moNumber="+moNumber);
            	}else if(i==1){
            		window.open(basePath+"/info/review/shipmentPageExportExcel?barcodeNum=" +barcodeNum+"&workOrderNumber="+workOrderNumber+"&moNumber="+moNumber);
            	}else if(i==2){
            		window.open(basePath+"/info/review/backPageExportExcel?barcodeNum=" +barcodeNum+"&workOrderNumber="+workOrderNumber+"&moNumber="+moNumber);
            	}else if(i==3){
            		window.open(basePath+"/info/review/repairPageExportExcel?barcodeNum=" +barcodeNum+"&workOrderNumber="+workOrderNumber+"&moNumber="+moNumber);
            	}else if(i==4){
            		window.open(basePath+"/info/review/miscellaneousPageExportExcel?barcodeNum=" +barcodeNum+"&workOrderNumber="+workOrderNumber+"&moNumber="+moNumber);
            	}  
            }
        }
        
        
        
    }); 
    
    //工单选择
    $("#choose").on("click",function(){
        //打开模态框
        $("#myModal").modal();
        getworkOrderNumber();
    });

    //工单查询
    $("#search").on("click",function () {
        getworkOrderNumber("refresh");
    });

    /**
     * 列表选择
     */
    for(var j = 0;j < $btns.length;j++){
        $btns[j].on("click",function () {
            for(var i = 0;i < $btns.length;i++){
                var $btn = $btns[i];
                $btn.removeClass("btn-success");
                $btn.addClass("btn-info");
                if($btn.attr("selected") == 'selected'){
                    var btn_id = $btn.attr("id");
                    var index = btn_id.indexOf("_btn");
                    //得到当前选择table的id
                    var tableId = btn_id.substring(0,index)+"Table";
                    var $table = $("#"+tableId);
                    $table.bootstrapTable("destroy");
                }
                $btn.removeAttr("selected");
            }
            var $this = $(this);
            $this.removeClass("btn-info");
            $this.addClass("btn-success");
            $this.attr("selected","true");
            var btn_id = $this.attr("id");
            var index = btn_id.indexOf("_btn");
            //得到当前选择table的id
            var tableId = btn_id.substring(0,index)+"Table";
            var $table = $("#"+tableId);
            $table.show();
            switch (tableId){
                case 'basicTable':
                    createBasicTable();
                    break;
                case 'shipmentTable':
                    createShipmentTable();
                    break;
                case 'backTable':
                    createBackTable();
                    break;
                case 'repairTable':
                    createRepairTable();
                    break;
                case 'miscellaneousTable':
                    createMiscellaneousTable();
                    break;
            }
            $("#inforeview_search_btn").click();
        })
    }

    /**
     * 确认选择
     */
    $("#choose_ok").on("click",function(){
        var selectRows = $('#barcodeTable').bootstrapTable('getSelections');
        if(selectRows.length == 0){
            alert("请选择数据!");
            return;
        }
        //选中的工单
        var worNum = selectRows[0].WIP_ENTITY_NAME;
        $("#workOrderNumber").val(worNum);
        //关闭模态框
        $("#myModal").modal("hide");
    });
});

//请求参数封装
function tableParam(params) {
    //产品条码
    var barcodeNum = $("#barcodeNum").val().trim();
    //工单号
    var workOrderNumber = $("#workOrderNumber").val().trim();
    //MO单
    var moNumber = $("#moNumber").val().trim();
    return {
        offset : params.offset + 1,
        limit : params.limit,
        barcodeNum : barcodeNum,
        workOrderNumber : workOrderNumber,
        moNumber : moNumber
    };
}

//创建基本情况_Table
function createBasicTable() {
    //基本情况_Table
    var $basicTable = $('#basicTable');
    $basicTable.bootstrapTable({
        queryParams : function(params) {
            return tableParam(params);
        },
        //请求方法 post方式会自动把参数封装为json格式
        method : 'get',
        //是否显示分页（*）
        pagination : true,
        //分页方式：client客户端分页，server服务端分页（*）
        sidePagination : "server",
        //是否显示行间隔色
        striped : true,
        //是否显示所有的列
        showColumns : false,
        //是否显示刷新按钮
        showRefresh : false,
        //是否只能选择单行
        singleSelect:false,
        //是否启用点击选中行
        clickToSelect: true,
        //中文支持
        dataLocale:"zh-US",
        //默认加载条数
        pageSize : 10,
        //每页显示数据条数
        pageList : [10,200,500,1000],
        //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache : false,
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            },{
                title: '产品条码',
                field: 'BARCODE_TEXT',
                align: 'center',
                valign: 'middle'
            }, {
                title: '工单号',
                field: 'WIP_ENTITY_NAME',
                align: 'center',
                valign: 'middle'
            }, {
                title: 'MO单号',
                field: 'MO_ORDER',
                align: 'center',
                valign: 'middle'
            }, {
                title: '生产线',
                field: 'PLAN_LINE_CODE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '装配件',
                field: 'SEGMENT1',
                align: 'center',
                valign: 'middle'
            }, {
                title: '描述',
                field: 'DESCRIPTION',
                align: 'left',
                valign: 'middle'
            }, {
                title: '产品型号',
                field: 'PROD_TPYE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '状态',
                field: 'BARCODE_STATUS',
                align: 'center',
                valign: 'middle'
            }, {
                title: '生产时间',
                field: 'WIP_DATE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '入库时间',
                field: 'INV_DATE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '入库数量',
                field: 'INV_QUANTITY',
                align: 'center',
                valign: 'middle'
            }, {
                title: '库存现有量',
                field: 'START_QUANTITY',
                align: 'center',
                valign: 'middle'
            }, {
                title: '子库',
                field: 'SUBINVENTORY_CODE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '库存货位',
                field: 'LOCATTION_CODE',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}

/**
 * 创建出货信息查询_Table
 */
function createShipmentTable() {
    //出货信息查询_Table
    var $shipmentTable = $("#shipmentTable");
    //出货信息列表
    $shipmentTable.bootstrapTable({
        queryParams : function(params) {
            return tableParam(params);
        },
        //请求方法 post方式会自动把参数封装为json格式
        method : 'get',
        //是否显示分页（*）
        pagination : true,
        //分页方式：client客户端分页，server服务端分页（*）
        sidePagination : "server",
        //是否显示行间隔色
        striped : true,
        //是否显示所有的列
        showColumns : false,
        //是否显示刷新按钮
        showRefresh : false,
        //是否只能选择单行
        singleSelect:false,
        //是否启用点击选中行
        clickToSelect: true,
        //中文支持
        dataLocale:"zh-US",
        //默认加载条数
        pageSize : 10,
        //每页显示数据条数
        pageList : [10,200,500,1000],
        //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache : false,
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            },{
                title: '条码',
                field: 'BARCODE_TEXT',
                align: 'center',
                valign: 'middle'
            }, {
                title: '物料编码',
                field: 'SEGMENT1',
                align: 'center',
                valign: 'middle'
            }, {
                title: '销售订单',
                field: 'SOURCE_HEADER_NUMBER',
                align: 'center',
                valign: 'middle'
            }, {
                title: '出售时间',
                field: 'TRANSACTION_DATE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '出售数量',
                field: 'QUANTITY',
                align: 'center',
                valign: 'middle'
            }, {
                title: '客户',
                field: 'CUSTOMER_NAME',
                align: 'center',
                valign: 'middle'
            }, {
                title: '区域',
                field: 'AREA',
                align: 'center',
                valign: 'middle'
            }, {
                title: '搬运单号',
                field: 'REQUEST_NUMBER',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}

/**
 * 创建退货信息_Table
 */
function createBackTable() {
    //退货信息查询_Table
    var $backTable = $("#backTable");
    //退货信息列表
    $backTable.bootstrapTable({
        queryParams : function(params) {
            return tableParam(params);
        },
        //请求方法 post方式会自动把参数封装为json格式
        method : 'get',
        //是否显示分页（*）
        pagination : true,
        //分页方式：client客户端分页，server服务端分页（*）
        sidePagination : "server",
        //是否显示行间隔色
        striped : true,
        //是否显示所有的列
        showColumns : false,
        //是否显示刷新按钮
        showRefresh : false,
        //是否只能选择单行
        singleSelect:false,
        //是否启用点击选中行
        clickToSelect: true,
        //中文支持
        dataLocale:"zh-US",
        //默认加载条数
        pageSize : 10,
        //每页显示数据条数
        pageList : [10,200,500,1000],
        //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache : false,
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            },{
                title: '条码',
                field: 'BARCODE_TEXT',
                align: 'center',
                valign: 'middle'
            }, {
                title: '物料编码',
                field: 'WIP_ENTITY_NAME',
                align: 'center',
                valign: 'middle'
            }, {
                title: '退货时间',
                field: 'PLAN_LINE_CODE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '退货数量',
                field: 'SEGMENT1',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}

/**
 * 创建返修信息_Table
 */
function createRepairTable() {
    //返修信息查询_Table
    var $repairTable = $("#repairTable");
    //出货信息列表
    $repairTable.bootstrapTable({
        queryParams : function(params) {
            return tableParam(params);
        },
        //请求方法 post方式会自动把参数封装为json格式
        method : 'get',
        //是否显示分页（*）
        pagination : true,
        //分页方式：client客户端分页，server服务端分页（*）
        sidePagination : "server",
        //是否显示行间隔色
        striped : true,
        //是否显示所有的列
        showColumns : false,
        //是否显示刷新按钮
        showRefresh : false,
        //是否只能选择单行
        singleSelect:false,
        //是否启用点击选中行
        clickToSelect: true,
        //中文支持
        dataLocale:"zh-US",
        //默认加载条数
        pageSize : 10,
        //每页显示数据条数
        pageList : [10,200,500,1000],
        //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache : false,
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            },{
                title: '条码',
                field: 'BARCODE_TEXT',
                align: 'center',
                valign: 'middle'
            }, {
                title: '生产工单',
                field: 'WIP_ENTITY_NAME',
                align: 'center',
                valign: 'middle'
            }, {
                title: '生产时间',
                field: 'WIP_DATE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '返修时间',
                field: 'TRANSACTION_DATE',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}

/**
 * 创建杂项交易_Table
 */
function createMiscellaneousTable() {
    //杂项交易查询_Table
    var $miscellaneousTable = $("#miscellaneousTable");
    //杂项交易列表
    $miscellaneousTable.bootstrapTable({
        queryParams : function(params) {
            return tableParam(params);
        },
        //请求方法 post方式会自动把参数封装为json格式
        method : 'get',
        //是否显示分页（*）
        pagination : true,
        //分页方式：client客户端分页，server服务端分页（*）
        sidePagination : "server",
        //是否显示行间隔色
        striped : true,
        //是否显示所有的列
        showColumns : false,
        //是否显示刷新按钮
        showRefresh : false,
        //是否只能选择单行
        singleSelect:false,
        //是否启用点击选中行
        clickToSelect: true,
        //中文支持
        dataLocale:"zh-US",
        //默认加载条数
        pageSize : 10,
        //每页显示数据条数
        pageList : [10,200,500,1000],
        //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache : false,
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            },{
                title: '条码',
                field: 'BARCODE_TEXT',
                align: 'center',
                valign: 'middle'
            }, {
                title: '物料',
                field: 'ITEM_NO',
                align: 'center',
                valign: 'middle'
            }, {
                title: '使用时间',
                field: 'TRANSACTION_DATE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '数量',
                field: 'TRANSACTION_QUANTITY',
                align: 'center',
                valign: 'middle'
            }, {
                title: '账户别名类型',
                field: 'DESCRIPTION',
                align: 'center',
                valign: 'middle'
            }, {
                title: '来源',
                field: 'SEGMENT1',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}

//工单查询
function getworkOrderNumber(refresh) {
    var url = basePath + 'productBarcodeController/getWorkOrderByPage';
    var $barcodeTable = $('#barcodeTable');
    if(typeof refresh == 'undefined'){
        $barcodeTable.bootstrapTable({
            url : url,
            queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    workOrderNumber : $("#workOrderNumSearch").val().trim()
                };
            },
            //分页方式：client客户端分页，server服务端分页（*）
            sidePagination : "server",
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
            // 是否启用排序
            sortable : true,
            // 排序方式
            sortOrder : "desc",
            //默认加载条数
            pageSize : 10,
            //每页显示数据条数
            pageList : [10,100,200,500],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            // 是否显示分页（*）
            pagination : true,
            // 是否启用排序
            sortable : false,
            // 排序方式
            sortOrder : "desc",
            // 初始化加载第一页，默认第一页
            // 我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
            //pageNumber:1,
            columns: [
                {
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '工单',
                    field: 'WIP_ENTITY_NAME',
                    align: 'center',
                    valign: 'middle'
                }
            ]
        });
    }else {
        $barcodeTable.bootstrapTable('refreshOptions',{
            queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    workOrderNumber : $("#workOrderNumSearch").val().trim()
                };
            }
        });
    }
}