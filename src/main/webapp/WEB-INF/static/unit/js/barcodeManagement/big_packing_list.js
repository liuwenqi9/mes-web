
$(function () {
    dataTable();

    //查询
    $("#search_btn").on("click",function () {
        dataTable("refresh");
    });

    //条码打印
    $("#print_btn").on("click",function () {
        var selectRows = $('#bigPackingTable').bootstrapTable('getSelections');
        if(selectRows.length == 0){
            alert("请选择数据!");
            return;
        }
        var big_packing_barcode_ids = [];
        for(var i = 0;i<selectRows.length;i++){
            big_packing_barcode_ids.push(selectRows[i].big_packing_barcode_id);
        }
        $.ajax({
            type : 'post',
            url : basePath + 'bigPackingController/printCode',
            data : {
                ids : big_packing_barcode_ids
            },
            success : function(msg){
                msg = $.parseJSON(msg);
                var message = msg.message;
                if(msg.success){
                    window.open(message);
                }else {
                    alert(message);
                }
            }
        });
    });

    //弹出框  条码打印
    $("#print_btn2").on("click",function () {
        var big_packing_barcode_ids=[];
        var id = $('#big_id').val();
        big_packing_barcode_ids.push(id);
        $.ajax({
            type : 'post',
            url : basePath + 'bigPackingController/printCode',
            data : {
                ids : big_packing_barcode_ids
            },
            success : function(msg){
                msg = $.parseJSON(msg);
                var message = msg.message;
                if(msg.success){
                    window.open(message);
                }else {
                    alert(message);
                }
            }
        });
    });
});

//创建dataTable
function dataTable(refresh) {
    var url = basePath + 'bigPackingController/findPage';
    var $bigPackingTable = $('#bigPackingTable');
    if(typeof refresh == 'undefined'){
        $bigPackingTable.bootstrapTable({
            queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    big_barcode_text : ''
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
            singleSelect:true,
            //是否启用点击选中行
            clickToSelect: false,
            //中文支持
            dataLocale:"zh-US",
            // 是否启用排序
            sortable : true,
            // 排序方式
            sortOrder : "desc",
            //头信息
            //toolbar: '#productBarcode_toolbar',
            //默认加载条数
            pageSize : 10,
            //每页显示数据条数
            pageList : [10,20,50,100],
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
                    title: '大包装箱',
                    field: 'big_barcode_text',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '已包数量',
                    field: 'big_pack_quantity',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '操作',
                    field: 'big_packing_barcode_id',
                    align: 'left',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        //var url = basePath + 'stocktakingController/toIterationSummary?headerId=' + row.HEADER_ID + '&organizationId=' + row.ORGANIZATION_ID;
                        var e = '<a href="javascript:void(0);" onclick="detail('+row.big_packing_barcode_id+');" >明细详情</a>';
                        return e;
                    }
                }
            ]
        });
    }else {
        $bigPackingTable.bootstrapTable('refreshOptions',{
            url : url,
            queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    big_barcode_text : $("#big_barcode_text").val()
                };
            }
        });
    }
}

function  detail(index) {
    $('#big_id').val(index);
    $('#myModal').modal("show");
    var $detailTable = $('#detailTable');
    $detailTable.bootstrapTable("destroy");
    $detailTable.bootstrapTable({
        url : basePath + 'bigPackingController/findDetail?id='+index,
        queryParams : function(params) {
        },
        method : 'get',
        dataType : "json",
        cache : false,
        columns : [{
            title: '大包装箱',
            field: 'big_barcode_text',
            align: 'center',
            valign: 'middle'
        }, {
            title: '大包装数量',
            field: 'big_pack_quantity',
            align: 'center',
            valign: 'middle'
        },{
                title: '小包装箱',
                field: 'small_barcode_text',
                align: 'center',
                valign: 'middle'
        },{
            title: '产品型号',
            field: 'small_prod_type',
            align: 'center',
            valign: 'middle'
        },{
            title: '物料编码',
            field: 'small_segment1',
            align: 'center',
            valign: 'middle',

        },{
            title: '小包装数量',
            field: 'small_pack_quantity',
            align: 'center',
            valign: 'middle'
        }]
    });
}