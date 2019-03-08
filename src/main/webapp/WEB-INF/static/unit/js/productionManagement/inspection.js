//报检JS
$(function () {
    getInspections();
    //查询
    $("#search_btn").on("click", function () {
        getInspections('refresh');
    });

    //checkbox数量更改
    $('#inspectionTable').on('check-all.bs.table uncheck-all.bs.table check.bs.table uncheck.bs.table', function (rows) {
        var rows = $('#inspectionTable').bootstrapTable('getSelections');
        //sjf 已选中数量
        $('#selectedCount').val(rows.length);

    });

    //生成报检单
    $('#inspection_generate_btn').click(function () {
        var rows = $('#inspectionTable').bootstrapTable('getSelections');
        if (rows.length > 0) {
            var id = [];
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (row.CHECK_STATUS_F == '已通过' || row.CHECK_STATUS_F == '已提交') {
                    alert("选择的数据中包含已通过或已提交数据!");
                    return;
                }
                if ((i + 1) < rows.length) {
                    if (row.WIP_ENTITY_NAME != rows[i + 1].WIP_ENTITY_NAME) {
                        alert("只能选择相同工单数据!");
                        return;
                    }
                }
                id.push(row.PACKING_BARCODE_ID);
            }
            var SEGMENT2 = rows[0].SEGMENT2;
            var WIP_ENTITY_NAME = rows[0].WIP_ENTITY_NAME;
            var PLAN_LINE = rows[0].PLAN_LINE;
            var PACK_QUANTITY_S = [];
            for (var i = 0; i < rows.length; i++) {
                PACK_QUANTITY_S.push(rows[i].PACK_QUANTITY);
            }
            //这里需要验证数据(过滤页面无效的数据)
            if (isValid(id)) {
                /*window.open(basePath + 'inspectionController/toInspectionPrintPage?id=' + id + '&SEGMENT2=' + SEGMENT2
                 + '&WIP_ENTITY_NAME=' + WIP_ENTITY_NAME + '&PLAN_LINE=' + PLAN_LINE + '&PACK_QUANTITY_S=' + PACK_QUANTITY_S.join(",")
                 );*/
                $.ajax({
                    type: 'post',
                    /*  url : basePath+'inspectionController/generateInspectionButton?id='+id+'&SEGMENT2='+SEGMENT2
                     +'&WIP_ENTITY_NAME='+WIP_ENTITY_NAME+'&PLAN_LINE='+PLAN_LINE+'&PACK_QUANTITY_S='+PACK_QUANTITY_S,*/
                    url: basePath + 'inspectionController/generateInspectionButton?id=' + id + '&PACK_QUANTITY_S=' + PACK_QUANTITY_S,
                    data: {
                        SEGMENT2: SEGMENT2,
                        WIP_ENTITY_NAME: WIP_ENTITY_NAME,
                        PLAN_LINE: PLAN_LINE
                    },
                    success: function (msg) {
                        $msg = $.parseJSON(msg);
                        if ($msg.success) {
                            //alert($msg.message);
                            getInspections("refresh");
                            window.open($msg.message);
                        }
                    }
                });
                /*var url = basePath+'inspectionController/generateInspectionButton?id='+id+'&PACK_QUANTITY_S='+PACK_QUANTITY_S;
                 var params = {
                 SEGMENT2:SEGMENT2,
                 WIP_ENTITY_NAME:WIP_ENTITY_NAME,
                 PLAN_LINE:PLAN_LINE
                 // ,PACK_QUANTITY_S:PACK_QUANTITY_S
                 };
                 var success =function (msg) {
                 $msg = $.parseJSON(msg);
                 if($msg.success){
                 //alert($msg.message);
                 getInspections("refresh");
                 window.open($msg.message);
                 }
                 };
                 eGdUtil.requestJsonAjax(url,params,success);*/


            } else {
                alert("数据异常，请重新选择");
                getInspections('load');
            }
        } else {
            alert("请选择需要的数据！");
        }
    });

});


//验证选中的数据是否合法
function isValid(id) {
    var f = false;
    $.ajax({
        async: false,//同步
        type: 'POST',
        url: basePath + "inspectionController/isValid?id=" + id,
        timeout: 5000,
        success: function (data) {
            var datas = strToJson(data);
            f = datas;
        }
    });
    return f;
}

//初始化列表
function getInspections(refresh) {
    var $table = $('#inspectionTable');
    if (typeof refresh == 'undefined') {
        $table.bootstrapTable("destroy");
        $table.bootstrapTable({
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    productionLine: $("#search_productionLine").val(),
                    workOrderNumber: $("#search_workOrderNumber").val(),
                    fullPackIdentify: $("#search_fullPackIdentify").val(),
                    inspectionIdentify: $("#search_inspectionIdentify").val()
                };
            },
            //分页方式：client客户端分页，server服务端分页（*）
            sidePagination: "server",
            //是否分页
            pagination : true,
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
            columns: [{
                checkbox: true,
                align: 'center',
                valign: 'middle'
            }, {
                title: '生产线',
                field: 'PLAN_LINE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '大包装',
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
                title: '装备件',
                field: 'SEGMENT1',
                align: 'center',
                valign: 'middle'
            }, {
                title: '产品型号',
                field: 'SEGMENT2',
                align: 'center',
                valign: 'middle'
            }, {
                title: '盛放数量',
                field: 'PACK_QUANTITY',
                align: 'center',
                valign: 'middle'
            }, {
                title: '满包标识',
                field: 'PACK_FLAG',
                align: 'center',
                valign: 'middle'
            }, {
                title: '报检标识',
                field: 'CHECK_STATUS_F',
                align: 'center',
                valign: 'middle'
            }, {
                title: '报检单号',
                field: 'INSPECT_NUMBER',
                align: 'center',
                valign: 'middle'
            }, {
                title: '报检日期',
                field: 'INSPECT_DATE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '检验日期',
                field: 'CHECK_DATE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '描述',
                field: 'DESCRIPTION',
                align: 'left',
                valign: 'middle'
            }]
        });
    } else {
        $table.bootstrapTable('refresh', {
            url: basePath + 'inspectionController/getInspectionListNew',
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    productionLine: $("#search_productionLine").val(),
                    workOrderNumber: $("#search_workOrderNumber").val(),
                    fullPackIdentify: $("#search_fullPackIdentify").val(),
                    inspectionIdentify: $("#search_inspectionIdentify").val()
                };
            }
        });
        //sjf 已选中数量
        $('#selectedCount').val(0);
    }
}