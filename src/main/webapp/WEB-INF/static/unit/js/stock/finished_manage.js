//成品库存查询 JS
$(function () {
    //初始化DataTable
    dataTable();

    //查询
    $("#finished_search_btn").on("click", function (e) {
        dataTable("refresh");
    });

    /******************子库选择 || 货位选择 相关代码 start******************/
    //子库选择 || 货位选择
    $("#choose_zk,#choose_hw").on("click", function (e) {
        var headName = "子库";
        var id = e.target.id;
        var url = basePath;
        var zk_code = "";
        switch (id) {
            case 'choose_zk':
                headName = "子库";
                url += "stock/finished/getZKByPage";
                break;
            case 'choose_hw':
                headName = "货位";
                zk_code = $("#subinventory_code").val();
                url += "stock/finished/getHWByPage";
                break;
        }
        $("#myModalLabel").html(headName);
        $("#workOrderNumSearch").attr("placeholder", headName);
        //打开模态框
        $("#myModal").modal();
        getModalChoose(url, headName, zk_code);
    });

    //查询
    $("#search").on("click", function (e) {
        $('#barcodeTable').bootstrapTable('refreshOptions', {
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    code: $("#subinventory_code").val().trim(),
                    id: $("#workOrderNumSearch").val().trim()
                };
            }
        });
    });

    //确认选择
    $("#choose_ok").on("click", function (e) {
        var headName = $("#myModalLabel").html();
        var selectRows = $('#barcodeTable').bootstrapTable('getSelections');
        if (selectRows.length == 0) {
            alert("请选择数据!");
            return;
        }
        var value = selectRows[0].ID;
        if (headName == '子库') {
            $("#subinventory_code").val(value);
        }
        if (headName == "货位") {
            $("#locattion_code").val(value);
        }
        //关闭模态框
        $("#myModal").modal("hide");
    });
    /******************子库选择 || 货位选择 相关代码 end******************/

    /*
     * Execl导出
     */
    $("#exportExcel").on("click",function () {
        //子库
        var subinventory_code = $("#subinventory_code").val().trim();
        //货位
        var locattion_code = $("#locattion_code").val().trim();
        //物料编码
        var item_no = $("#item_no").val().trim();
        var mo_order = $("#mo_order").val().trim();
        window.open(basePath+"/stock/finished/exportExcel?subinventory_code="+subinventory_code+"&locattion_code="+locattion_code+"&item_no="+item_no+"&mo_order="+mo_order);
    });
});

//创建DataTable
function dataTable(refresh) {
    var url = basePath + 'stock/finished/findPage';
    var $finishedTable = $('#finishedTable');
    if (typeof refresh == 'undefined') {
        $finishedTable.bootstrapTable({
            queryParams: function (params) {
                return {
                    offset: params.offset,
                    limit: params.limit,
                    subinventory_code: $("#subinventory_code").val().trim(),
                    locattion_code: $("#locattion_code").val().trim(),
                    item_no: $("#item_no").val().trim(),
                    mo_order: $("#mo_order").val().trim()
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
            //头信息
            //toolbar: '#productBarcode_toolbar',
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
                    title: '子库',
                    field: 'subinventory_code',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '货位',
                    field: 'locattion_code',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: 'MO单',
                    field: 'mo_order',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '物料编码',
                    field: 'item_no',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '产品型号',
                    field: 'prod_tpye',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '现有量',
                    field: 'barcode_quantity',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '包装箱数',
                    field: 'pack_count',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: 'ERP子库库存',
                    field: 'erp_onhand_quantity',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '描述',
                    field: 'item_desc',
                    align: 'left',
                    valign: 'middle'
                }
            ]
        });
    } else {
        $finishedTable.bootstrapTable('refreshOptions', {
            url: url,
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    subinventory_code: $("#subinventory_code").val().trim(),
                    locattion_code: $("#locattion_code").val().trim(),
                    item_no: $("#item_no").val().trim(),
                    mo_order: $("#mo_order").val().trim()
                };
            }
        });
    }
}

//子库 || 货位选择
function getModalChoose(url, name, code) {
    $("#workOrderNumSearch").val("");
    var $barcodeTable = $('#barcodeTable');
    $barcodeTable.bootstrapTable("destroy");
    $barcodeTable.bootstrapTable({
        url: url,
        queryParams: function (params) {
            return {
                offset: params.offset + 1,
                limit: params.limit,
                code: code,
                id: $("#workOrderNumSearch").val().trim()
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
        showColumns: false,
        // 是否显示刷新按钮
        showRefresh: false,
        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        //height: 30,
        //是否只能选择单行
        singleSelect: true,
        //是否启用点击选中行
        clickToSelect: true,
        //中文支持
        dataLocale: "zh-US",
        // 是否启用排序
        sortable: true,
        // 排序方式
        sortOrder: "desc",
        //头信息
        //toolbar: '#productBarcode_toolbar',
        //默认加载条数
        pageSize: 10,
        //每页显示数据条数
        pageList: [10, 20, 50, 100],
        // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache: false,
        // 是否显示分页（*）
        pagination: true,
        // 是否启用排序
        sortable: false,
        // 排序方式
        sortOrder: "desc",
        // 初始化加载第一页，默认第一页
        // 我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
        //pageNumber:1,
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            }, {
                title: name,
                field: 'ID',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}