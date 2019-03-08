var pageNumber = 1;

$(function () {
    //baseInfo
    getBarcodeTracebackBaseInfo();

    $('#myTab a[href="#1"]').click(function (e) {
        pageNumber = 1;
        getBarcodeTracebackBaseInfo();
    });

    $('#myTab a[href="#2"]').click(function (e) {
        pageNumber = 2;
        getBarcodeTracebackShipmentInfo();
    });
    $('#myTab a[href="#3"]').click(function (e) {
        pageNumber = 3;
        getBarcodeTracebackRefundInfo();
    });
    $('#myTab a[href="#4"]').click(function (e) {
        pageNumber = 4;
        getBarcodeTracebackRepairInfo();
    });
    $('#myTab a[href="#5"]').click(function (e) {
        pageNumber = 5;
        getBarcodeTracebackSundryTransaction();
    });


    //信息查询btn
    $('#search_btn').on('click', function () {
        switch (pageNumber) {
            case 1:
                getBarcodeTracebackBaseInfo();
                break;
            case 2:
                getBarcodeTracebackShipmentInfo();
                break;
            case 3:
                getBarcodeTracebackRefundInfo();
                break;
            case 4:
                getBarcodeTracebackRepairInfo();
                break;
            case 5:
                getBarcodeTracebackSundryTransaction();
                break;
        }

    });

});


//基本信息列表
function getBarcodeTracebackBaseInfo() {
    var $table = $('#baseInfoTable');
    $table.bootstrapTable("destroy");
    $table.bootstrapTable({
        url: basePath + 'stocktakingController/getSubpoolList',
        queryParams: function (params) {
            return {
                offset: params.offset + 1,
                limit: params.limit,
                productBarcode: $('#productBarcode').val(),
                workOrderNumber: $('#workOrderNumber').val(),
                mo: $('#mo').val()
            };
        },
        method: 'get',
        //dataType : "json",
        cache: false,
        //是否只能选择单行
        //singleSelect: false,
        //是否启用点击选中行
        //clickToSelect: true,
        sidePagination: "server",
        pagination: true,
        //中文支持
        dataLocale: "zh-US",
        pageSize: 10,
        //每页显示数据条数
        pageList: [5, 10, 20],
        columns: [{
            title: '工单号',
            field: 'DATA2',
            align: 'center',
            valign: 'middle'
        }, {
            title: 'MO单号',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '生产线',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '装备件',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '产品型号',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '状态',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '生产时间',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '入库时间',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '入库数量',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '库存现有量',
            field: 'data',
            align: 'center',
            valign: 'middle'
            /*formatter:function(value,row,index){
             var e = row.BARCODE_QUANTITY-row.PD_QUANTITY;
             return e;
             }*/
        }, {
            title: '子库',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '库存货位',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '描述',
            field: 'data',
            align: 'left',
            valign: 'middle'
        }]
    });
}

//出货信息列表
function getBarcodeTracebackShipmentInfo() {
    var $table = $('#shipmentInfoTable');
    $table.bootstrapTable("destroy");
    $table.bootstrapTable({
        url: basePath + 'stocktakingController/getSubpoolList',
        queryParams: function (params) {
            return {
                offset: params.offset + 1,
                limit: params.limit,
                productBarcode: $('#productBarcode').val(),
                workOrderNumber: $('#workOrderNumber').val(),
                mo: $('#mo').val()
            };
        },
        method: 'get',
        //dataType : "json",
        cache: false,
        //是否只能选择单行
        //singleSelect: false,
        //是否启用点击选中行
        //clickToSelect: true,
        sidePagination: "server",
        pagination: true,
        //中文支持
        dataLocale: "zh-US",
        pageSize: 10,
        //每页显示数据条数
        pageList: [5, 10, 20],
        columns: [{
            title: '条码',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '物料编码',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '状态',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '销售订单',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '出货时间',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '出货数量',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '客户',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '区域',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '搬运单号',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }]
    });
}

//退货信息列表
function getBarcodeTracebackRefundInfo() {
    var $table = $('#refundInfoTable');
    $table.bootstrapTable("destroy");
    $table.bootstrapTable({
        url: basePath + 'stocktakingController/getSubpoolList',
        //barcodeTracebackController/getBarcodeTracebackSundryTransaction
        queryParams: function (params) {
            return {
                offset: params.offset + 1,
                limit: params.limit,
                productBarcode: $('#productBarcode').val(),
                workOrderNumber: $('#workOrderNumber').val(),
                mo: $('#mo').val()
            };
        },
        method: 'get',
        //dataType : "json",
        cache: false,
        //是否只能选择单行
        //singleSelect: false,
        //是否启用点击选中行
        //clickToSelect: true,
        sidePagination: "server",
        pagination: true,
        //中文支持
        dataLocale: "zh-US",
        pageSize: 10,
        //每页显示数据条数
        pageList: [5, 10, 20],
        columns: [{
            title: '条码',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '物料编码',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '状态',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '退货时间',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '退货数量',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }]
    });
}

//返修信息
function getBarcodeTracebackRepairInfo() {
    var $table = $('#repairInfoTable');
    $table.bootstrapTable("destroy");
    $table.bootstrapTable({
        url: basePath + 'stocktakingController/getSubpoolList',
        //barcodeTracebackController/getBarcodeTracebackSundryTransaction
        queryParams: function (params) {
            return {
                offset: params.offset + 1,
                limit: params.limit,
                productBarcode: $('#productBarcode').val(),
                workOrderNumber: $('#workOrderNumber').val(),
                mo: $('#mo').val()
            };
        },
        method: 'get',
        //dataType : "json",
        cache: false,
        //是否只能选择单行
        //singleSelect: false,
        //是否启用点击选中行
        //clickToSelect: true,
        sidePagination: "server",
        pagination: true,
        //中文支持
        dataLocale: "zh-US",
        pageSize: 10,
        //每页显示数据条数
        pageList: [5, 10, 20],
        columns: [{
            title: '条码',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '生产工单',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '生产时间',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '返修时间',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }]
    });

}

//杂项交易
function getBarcodeTracebackSundryTransaction() {
    var $table = $('#sundryTransactionTable');
    $table.bootstrapTable("destroy");
    $table.bootstrapTable({
        url: basePath + 'stocktakingController/getSubpoolList',
        //barcodeTracebackController/getBarcodeTracebackSundryTransaction
        queryParams: function (params) {
            return {
                offset: params.offset + 1,
                limit: params.limit,
                productBarcode: $('#productBarcode').val(),
                workOrderNumber: $('#workOrderNumber').val(),
                mo: $('#mo').val()
            };
        },
        method: 'get',
        //dataType : "json",
        cache: false,
        //是否只能选择单行
        //singleSelect: false,
        //是否启用点击选中行
        //clickToSelect: true,
        sidePagination: "server",
        pagination: true,
        //中文支持
        dataLocale: "zh-US",
        pageSize: 10,
        //每页显示数据条数
        pageList: [5, 10, 20],
        columns: [{
            title: '条码',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '物料编码',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '领用时间',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '数量',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '账户别名类型',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }, {
            title: '来源',
            field: 'data',
            align: 'center',
            valign: 'middle'
        }]
    });
} 


 
