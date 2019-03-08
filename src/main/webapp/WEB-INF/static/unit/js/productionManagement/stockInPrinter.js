//入库单打印
$(function () {
    //初始化dataTable
    getStockInPrinters();

    //时间控件初始化
    $('#checkBeginDate,#checkEndDate').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        //语言选择中文
        format: "yyyy-mm-dd"
    });
    //模糊查询
    $("#stock_search_btn").on("click", function (e) {
        getStockInPrinters('refresh');
    });

    //生成入库单
    $('#stockInPrinter_generate_btn').on("click", function () {
        //获取选中的行
        var selections = $('#stockInPrinterTable').bootstrapTable('getSelections');
        if (selections.length == 0) {
            alert("请选择数据");
            return;
        }
        //包装箱ID
        var pbIDs = [];
        for (var i = 0; i < selections.length; i++) {
            var row = selections[i];
            if ((i + 1) < selections.length) {
                //比较子库ID是否相同
                if (row.SUBINVENTORY_CODE != selections[i + 1].SUBINVENTORY_CODE) {
                    alert("请选择[完工子库]相同的数据!");
                    return;
                }
            }
            pbIDs.push(row.PACKING_BARCODE_ID);
        }
        //验证是否已生成入库单
        $.ajax({
            type: 'post',
            url: basePath + 'stockInController/isPackingBarCodeByNo',
            data: {
                pbIDs: pbIDs.join(",")
            },
            success: function (msg) {
                var $msg = $.parseJSON(msg);
                if ($msg.success) {
                    alert("存在选中的数据已生成入库单");
                    return;
                }
                //生成入库单
                $.ajax({
                    type: 'post',
                    url: basePath + 'stockInController/generateStorageOrder',
                    data: {
                        pbIDs: pbIDs.join(",")
                    },
                    success: function (msg) {
                        $msg = $.parseJSON(msg);
                        if ($msg.success) {
                            //alert($msg.message);
                            getStockInPrinters("refresh");
                            window.open($msg.message);
                        }
                    }
                });
            }
        });
    });
});

/**
 * 创建dataTable
 */
function getStockInPrinters(refresh) {
    var url = basePath + 'stockInController/getStockInPrinters';
    var $table = $('#stockInPrinterTable');
    if (eGdUtil.isEmpty(refresh)) {
        $table.bootstrapTable({
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    productionLine: $('#search_productionLine').val(),
                    workOrderNumber: $('#workOrderNumber').val(),
                    workOrderNumberSubpool: $('#workOrderNumberSubpool').val(),
                    stockInIdentify: "N",
                    checkBeginDate: $('#checkBeginDate').val(),
                    checkEndDate: $('#checkEndDate').val(),
                    mark: 'printer'
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
            paginationPreText: '上一页',
            paginationNextText: '下一页',
            columns: [
                {
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '生产线',
                    field: 'PLAN_LINE',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '包装箱号',
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
                    title: '产品编码',
                    field: 'SEGMENT1',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '产品型号',
                    field: 'SEGMENT2',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '完工子库',
                    field: 'SUBINVENTORY_CODE',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '建议货位',
                    field: 'LOCATTION_CODE',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '包装数量',
                    field: 'PACK_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '入库单号',
                    field: 'INV_NUMBER',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '检验时间',
                    field: 'CHECK_DATE',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '描述',
                    field: 'DESCRIPTION',
                    align: 'left',
                    valign: 'middle'
                }
            ]
        });
    } else {
        $table.bootstrapTable('refreshOptions', {
            url: url,
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    productionLine: $('#search_productionLine').val(),
                    workOrderNumber: $('#workOrderNumber').val(),
                    workOrderNumberSubpool: $('#workOrderNumberSubpool').val(),
                    stockInIdentify: $('#stockInIdentify').val(),
                    checkBeginDate: $('#checkBeginDate').val(),
                    checkEndDate: $('#checkEndDate').val(),
                    mark: 'printer'
                };
            }
        });
    }
}
