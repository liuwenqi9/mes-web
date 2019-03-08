//成品借机 JS

$(function () {

    //列表初始化
    summaryDataTableInit();

    $("#startTime,#endTime").datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format:"yyyy-mm-dd"
    });

    /**
     * 查询
     */
    $("#jj_summary_search_btn").on("click",function () {
        var url = basePath + 'seizeAnOpportunity/findSummaryPage';
        $('#seizeAnOpportunitySummaryTable').bootstrapTable('refreshOptions', {
            url : url,
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    number: $("#jj_summary_number").val().trim(),
                    startTime: $("#startTime").val().trim(),
                    endTime: $("#endTime").val().trim()
                };
            }
        });
    });

    /**
     * 新增
     */
    $("#saopAdd").on("click",function () {
        var $myModalAdd = $("#myModalAdd");
        $myModalAdd.find(".modal-body").load(basePath+"seizeAnOpportunity/addModal",function () {
            $myModalAdd.modal("show");
            datetimepickerInit();
        });
    });

    /**
     * 删除借机头
     */
    $("#delete_header").on("click",function () { 
    	var rows = $('#seizeAnOpportunitySummaryTable').bootstrapTable("getSelections");
        if(rows.length == 0){
            $.messager.alert("提示","请选择数据!","info");
            return;
        }
        var ids = [];
        for(var i=0;i<rows.length;i++){
        	ids.push(rows[i].id);
        }
    	 $.ajax({
             type : 'get',
             url : basePath+'seizeAnOpportunity/deleteHeaders?ids='+ids, 
             success : function (msg) {
                 var $msg = $.parseJSON(msg);
                 if($msg.success){
	                	showInfoMsg($msg.message); 
	                	$("#jj_summary_search_btn").click();
	                }else{
	                	showErrorMsg($msg.message);
	                }
             }
         });
    });

    
    /**
     * 物流管理
     */
    $("#expressBtn").on("click",function () {
        var rows = $('#seizeAnOpportunitySummaryTable').bootstrapTable("getSelections");
        if(rows.length == 0){
            $.messager.alert("提示","请选择借机数据","info");
            return;
        }
        if(rows.length > 1){
            $.messager.alert("提示","只能选择一条借机数据","info");
            return;
        }
        var $expressModal = $("#expressModal");
        $expressModal.find(".modal-body").load(basePath+"seizeAnOpportunity/expressModal",function () {
            $expressModal.modal("show");
        });
    });

    /**
     * 借机保存
     */
    $("#jj_add_save").on("click",function () {
        //借机单号
        var number = $("#jj_add_number").val();
        //借机部门
        var dept = $("#jj_add_dept").val();
        if(dept == null || dept == ''){
            $.messager.alert("提示","请选择借机部门","info");
            return;
        }
        //借机人员
        var person = $("#jj_add_person").val();
        if(person == null || person == ''){
            $.messager.alert("提示","请输入借机人员","info");
            return;
        }
        //借机用途
        var purpose = $("#jj_add_purpose").val();
        if(person == null || person == ''){
            $.messager.alert("提示","请选择借机用途","info");
            return;
        }
        //备注
        var remark = $("#add_remark").val();
        var $table = $("#jj_add_table");
        //物料ID,申请数量,计划归还时间,来源子库,目的子库,目的货位
        var codeIds=[],quantitys=[],returnTimes=[],sourceSubLibrarys=[],goalSubLibrarys=[],goalLocations=[];
        var dom_codeIds = $table.find("input[name='codeId']");
        var dom_quantitys = $table.find("input[name='quantity']");
        var dom_returnTimes = $table.find("input[name='returnTime']");
        var dom_sourceSubLibrarys = $table.find("select[name='sourceSubLibrary']");
        var dom_goalSubLibrarys = $table.find("select[name='goalSubLibrary']");
        var dom_goalLocations = $table.find("select[name='goalLocation']");
        for(var i = 0;i<dom_codeIds.length;i++){
            var codeId = dom_codeIds[i].value;
            if(codeId == null || codeId == ''){
                $.messager.alert("提示","第"+(i+1)+"条数据编码不存在，请重新输入","info");
                return;
            }
            codeIds.push(codeId);
            var quantity = dom_quantitys[i].value;
            if(quantity == null || quantity == ''){
                $.messager.alert("提示","第"+(i+1)+"条数据未录入申请数量","info");
                return;
            }
            quantitys.push(quantity);
            var returnTime = dom_returnTimes[i].value;
            if(returnTime == null || returnTime == ''){
                $.messager.alert("提示","第"+(i+1)+"条数据未录入计划归还时间","info");
                return;
            }
            returnTimes.push(returnTime);
            var sourceSubLibrary = dom_sourceSubLibrarys[i].value;
            if(returnTime == null || returnTime == ''){
                $.messager.alert("提示","第"+(i+1)+"条数据未选择来源子库","info");
                return;
            }
            sourceSubLibrarys.push(sourceSubLibrary);
            var goalSubLibrary = dom_goalSubLibrarys[i].value;
            if(goalSubLibrary == null || goalSubLibrary == ''){
                $.messager.alert("提示","第"+(i+1)+"条数据未选择目的子库","info");
                return;
            }
            goalSubLibrarys.push(goalSubLibrary);
            //目的货位
            var goalLocation = dom_goalLocations[i].value;
            /*if(goalLocation == null || goalLocation == ''){
                $.messager.alert("提示","第"+(i+1)+"条数据未录入目的货位","info");
                return;
            }*/
            goalLocations.push(goalLocation);
        }
        $.ajax({
            type : 'post',
            url : basePath + "seizeAnOpportunity/insert",
            data : {
                number : number,
                dept : dept,
                person : person,
                purpose : purpose,
                remark : remark,
                codeId : codeIds.join(","),
                quantity : quantitys.join(","),
                returnTime : returnTimes.join(","),
                sourceSubLibrary : sourceSubLibrarys.join(","),
                goalSubLibrary : goalSubLibrarys.join(","),
                goalLocation : goalLocations.join(",")
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                $.messager.alert("提示",$msg.message,"info",function () {
                    $("#myModalAdd").modal("hide");
                    $("#jj_summary_search_btn").click();
                });
            }
        });
    });

    /**
     * 物流保存
     */
    $("#express_save").on("click",function () {
        var express = $("#express").val();
        if(express == '' || express == null){
            $.messager.alert("提示","请选择物流公司","info");
            return;
        }
        var expressNumber = $("#expressNumber").val();
        if(expressNumber == '' || expressNumber == null){
            $.messager.alert("提示","请输入物流单号","info");
            return;
        }
        var rows = $('#seizeAnOpportunitySummaryTable').bootstrapTable("getSelections");
        $.ajax({
            type : 'post',
            url : basePath + "seizeAnOpportunity/updateHeader",
            data : {
                number : rows[0].number,
                express : express,
                expressNumber : expressNumber
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                $.messager.alert("提示",$msg.message,"info",function () {
                    if($msg.success){
                        $("#expressModal").modal("hide");
                        $("#jj_summary_search_btn").click();
                    }
                });
            }
        });

    });

    /**
     * 打印
     */
    $("#printer_btn").on("click",function () {
        var rows = $('#seizeAnOpportunitySummaryTable').bootstrapTable("getSelections");
        if(rows.length == 0){
            $.messager.alert("提示","请选择数据","info");
            return;
        }
        var numbers = [];
        for(var i = 0;i < rows.length;i++){
            numbers.push(rows[i].number);
        }
        $.ajax({
            type : 'post',
            url : basePath + "seizeAnOpportunity/printer",
            data : {
                number : numbers.join(",")
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                if($msg.success){
                    window.open($msg.message);
                }else {
                    $.messager.alert("提示",$msg.message,"info");
                }
            }
        });
    });
});

function summaryDataTableInit() {
    var $seizeAnOpportunitySummaryTable = $('#seizeAnOpportunitySummaryTable');
    $seizeAnOpportunitySummaryTable.bootstrapTable({
        //分页方式：client客户端分页，server服务端分页（*）
        sidePagination: "server",
        //是否分页
        pagination: true,
        //请求方法 post方式会自动把参数封装为json格式
        method: 'get',
        //服务器返回的数据类型
        //dataType : "json",
        //是否显示行间隔色
        striped: true,
        //是否显示所有的列
        showColumns: true,
        // 是否显示刷新按钮
        showRefresh: false,
        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        //height: 30,
        //是否只能选择单行
        singleSelect: false,
        //是否启用点击选中行
        clickToSelect: true,
        //中文支持
        dataLocale: "zh-US",
        // 是否启用排序
        sortable: true,
        // 排序方式
        sortOrder: "desc",
        //默认加载条数
        pageSize: 100,
        //每页显示数据条数
        pageList: [100, 200, 500, 1000],
        // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache: false,
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            }, {
                title: '借机单号',
                field: 'number',
                align: 'center',
                valign: 'middle'
            }, {
                title: '创建日期',
                field: 'createTime',
                align: 'center',
                valign: 'middle',
                formatter:function (value,row,index) {
                    if(value != null && value != ''){
                        var da = new Date(value);
                        var year = da.getFullYear();
                        var month = da.getMonth()+1;
                        if(month < 10){
                            month = "0"+month;
                        }
                        var day = da.getDate();
                        if(day < 10){
                            day = "0"+day;
                        }
                        return year+"-"+month+"-"+day;
                    }
                }
            }, {
                title: '借机部门',
                field: 'dept',
                align: 'center',
                valign: 'middle'
            }, {
                title: '借机人',
                field: 'person',
                align: 'center',
                valign: 'middle'
            }, {
                title: '用途',
                field: 'purpose',
                align: 'center',
                valign: 'middle'
            }, {
                title: '物流公司',
                field: 'express',
                align: 'center',
                valign: 'middle'
            }, {
                title: '物流单号',
                field: 'expressNumber',
                align: 'center',
                valign: 'middle'
            }, {
                title: '操作',
                field: '',
                align: 'center',
                valign: 'middle',
                formatter:function (value,row,index) {
                    var number = row.number;
                    var detailed = "<button class='btn btn-primary btn-sm' data-backdrop='static' onclick='getDetailed(\""+number+"\")'><i class='glyphicon glyphicon-search'></i>详情</button>";
                    return detailed;
                }
            }, {
                title: '备注',
                field: 'remark',
                align: 'left',
                valign: 'middle'
            }
        ]
    });
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
    //$tr_clone.find("select[name='goalLocation']").empty();
    $tr_clone.find("input[name='returnTime']").datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format:"yyyy-mm-dd"
    });
}

/**
 * 目的子库改变事件
 * 目的货位联动
 * @param th
 */
function goalSubLibraryChange(th) {
    var $this = $(th);
    var goalSubLibrary = $this.val();
    //目的货位
    var $goalLocation = $this.parent().next().children().eq(0);
    $goalLocation.empty();
    $.ajax({
        type : 'post',
        url : basePath + "seizeAnOpportunity/queryGoalLocations",
        data : {
            goalSubLibrary : goalSubLibrary
        },
        success : function (msg) {
            var $msg = $.parseJSON(msg);
            for (var i = 0;i < $msg.length;i++){
                var map = $msg[i];
                var option = "<option value='"+map.LOCATTION_CODE+"'>"+map.LOCATTION_CODE+"</option>";
                $goalLocation.append(option);
            }
        }
    });
}

/**
 * 编码失去焦点事件
 * @param th
 */
function codeBlur(th) {
    var $this = $(th);
    var $tr = $this.parent().parent();
    $.ajax({
        type : 'post',
        url : basePath + "seizeAnOpportunity/queryDescribe",
        data : {
            code : $this.val().trim()
        },
        success:function (msg) {
            //型号
            var $model = $tr.find("input[name='model']");
            //货位描述
            var $describe = $tr.find("input[name='describe']");
            //编码ID
            var $codeId = $this.next();
            if(msg == 'null'){
                $.messager.alert("提示","此编码不存在，请重新输入","info");
                $codeId.val("");
                $model.val("");
                $describe.val("");
                return;
            }
            var $msg = $.parseJSON(msg);
            $codeId.val($msg.INVENTORY_ITEM_ID);
            $model.val($msg.PROD_TYPE);
            $describe.val($msg.DESCRIPTION);
        }
    });

}

/**
 * 详情
 * @param number
 */
function getDetailed(number) {
   var gg =  $("#myModalDetailedBody").load(basePath+"seizeAnOpportunity/detailedModal",
        {
            number : number
        },
        function () {
            //打印按钮
            var $detailed_printer = $("#detailed_printer");
            //销售按钮
            var $sale = $("#sale");
            $detailed_printer.attr("jjNumber", number);
            /**
             * 打印
             */
            $detailed_printer.on("click", function () {
                var datas = $('#seizeAnOpportunityTable').bootstrapTable("getData");
                if (datas.length == 0) {
                    $.messager.alert("提示", "未查询到数据", "info");
                    return;
                }
                $.ajax({
                    type: 'post',
                    url: basePath + "seizeAnOpportunity/printer",
                    data: {
                        number: datas[0].number
                    },
                    success: function (msg) {
                        var $msg = $.parseJSON(msg);
                        if ($msg.success) {
                            window.open($msg.message);
                        } else {
                            $.messager.alert("提示", $msg.message, "info");
                        }
                    }
                });
            });
            /**
             * 销售
             */
            $sale.on("click",function () {
                $("#saleModal").modal("show");
            });
            
            /**
             * 删除借机行
             */
            $("#delete_line").on("click",function () { 
            	var rows = $('#seizeAnOpportunityTable').bootstrapTable("getSelections");
                if(rows.length == 0){
                    $.messager.alert("提示","请选择数据!","info");
                    return;
                }
                var ids2 = []; 
                for(var i=0;i<rows.length;i++){ 
                	ids2.push(rows[i].line_id);
                }
            	 $.ajax({
                     type : 'get',
                     url : basePath+'seizeAnOpportunity/deleteLines?ids='+ids2, 
                     success : function (msg) {
                         var $msg = $.parseJSON(msg);
                         if($msg.success){
        	                	showInfoMsg($msg.message); 
        	                	var url = basePath + 'seizeAnOpportunity/findDetailedPage';
        	                     $('#seizeAnOpportunityTable').bootstrapTable('refreshOptions', {
        	                         url : url,
        	                         queryParams: function (params) {
        	                             return {
        	                                 offset: params.offset + 1,
        	                                 limit: params.limit,
        	                                 number: number,
        	                             };
        	                         }
        	                     });
        	                }else{
        	                	showErrorMsg($msg.message);
        	                }
                     }
                 });
            });
            
            /**
             * 关闭
             */
            $("#saleClose").on("click",function () {
                $("#saleModal").modal("hide");
            });
            /**
             * 销售保存
             */
            $("#saleSave").on("click",function () {
                var evaluation = $("#evaluation").val();
                if(evaluation == null || evaluation == ''){
                    $.messager.alert("提示", "请输入参考信息", "info");
                    return;
                }
                var selectRows = $('#seizeAnOpportunityTable').bootstrapTable("getSelections");
                if(selectRows.length == 0){
                    $.messager.alert("提示", "请选择数据", "info");
                    return;
                }
                 
                var falg = true;//判断是否满足销售条件
                var ids = [];
                var barcode_ids = [];
                for(var i = 0;i < selectRows.length;i++){
                    ids.push(selectRows[i].id);
                    var a = selectRows[i].wip_barcode_id; 
                    if(typeof(a) == "undefined"){//产品条码id为undefined
                    	falg = false;
                    } 
                    if(typeof(selectRows[i].actualTime) != "undefined"){//实际规划时间不为undefined时，不能销售
                    	falg = false;
                    }
                    barcode_ids.push(a);
                }
                if(!falg){
                    $.messager.alert("提示", "选中的数据不能销售", "info");
                    return;
                }
                $.ajax({
                    type : 'post',
                    url : basePath + 'seizeAnOpportunity/updateSale',
                    data : {
                        evaluation : evaluation,
                        ids : ids,
                        barcodeId:barcode_ids
                    },
                    success: function (msg) {
                        var $msg = $.parseJSON(msg);
                        $.messager.alert("提示", $msg.message, "info",function () {
                            if($msg.success){
                                $("#saleModal").modal("hide");
                                $("#evaluation").val("");
                                $('#seizeAnOpportunityTable').bootstrapTable("refresh");
                            }
                        });
                    }
                });
            });
            //打开模态框
            $("#myModalDetailed").modal("show");
            //初始化列表
            dataTableInit(number);
    });
   var gg1= "";
}

/**
 * 时间控件初始化
 */
function datetimepickerInit() {
    //时间控件初始化
    $("input[name='returnTime']").datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format:"yyyy-mm-dd"
    });
}

function dataTableInit(number) {
    var url = basePath + 'seizeAnOpportunity/findDetailedPage';
    var $seizeAnOpportunityTable = $('#seizeAnOpportunityTable');
    $seizeAnOpportunityTable.bootstrapTable({
        url : url,
        queryParams: function (params) {
            return {
                offset: params.offset + 1,
                limit: params.limit,
                number: number
            };
        },
        //分页方式：client客户端分页，server服务端分页（*）
        sidePagination: "server",
        //是否分页
        pagination: true,
        //请求方法 post方式会自动把参数封装为json格式
        method: 'get',
        //服务器返回的数据类型
        //dataType : "json",
        //是否显示行间隔色
        striped: true,
        //是否显示所有的列
        //showColumns: true,
        // 是否显示刷新按钮
        showRefresh: false,
        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        //height: 30,
        //是否只能选择单行
        singleSelect: false,
        //是否启用点击选中行
        clickToSelect: true,
        //中文支持
        dataLocale: "zh-US",
        // 是否启用排序
        sortable: true,
        // 排序方式
        sortOrder: "desc",
        //默认加载条数
        pageSize: 10,
        //每页显示数据条数
        pageList: [10, 20, 50, 100],
        // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache: false,
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            }, {
                title: '编码',
                field: 'code',
                align: 'center',
                valign: 'middle'
            }, {
                title: '型号',
                field: 'model',
                align: 'center',
                valign: 'middle'
            }, {
                title: '申请数量',
                field: 'quantity',
                align: 'center',
                valign: 'middle'
            }, {
                title: '实际数量',
                field: 'lendQuantity',
                align: 'center',
                valign: 'middle'
            }, {
                title: '产品条码',
                field: 'productCode',
                align: 'center',
                valign: 'middle'
            }, {
                title: '来源子库',
                field: 'sourceSubLibrary',
                align: 'center',
                valign: 'middle'
            }, {
                title: '目的子库',
                field: 'goalSubLibrary',
                align: 'center',
                valign: 'middle'
            }, {
                title: '目的货位',
                field: 'goalLocation',
                align: 'center',
                valign: 'middle'
            }, {
                title: '计划归还日期',
                field: 'returnTime',
                align: 'center',
                valign: 'middle',
                formatter:function (value,row,index) {
                    if(value != null && value != ''){
                        return value.substring(0,10);
                    }
                }
            }, {
                title: '实际归还日期',
                field: 'actualTime',
                align: 'center',
                valign: 'middle',
                formatter:function (value,row,index) {
                    if(value != null && value != ''){
                        return value.substring(0,10);
                    }
                }
            }, {
                title: '物料描述',
                field: 'describe',
                align: 'left',
                valign: 'middle'
            }
        ]
    });
}