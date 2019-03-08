//二次拼箱 JS
$(function () {
    $("#packageBarcode").focus();
    getTableByPage();

    /**
     * 扫描包装条码
     */
    /*$("#packageBarcode").on("blur",function () {
        var $this = $(this);
        checkPackaging($this);
    });*/
    $("#packageBarcode").on("keyup",function (e) {
        if(e.keyCode == 13){
            var $this = $(this);
            checkPackaging($this);
        }
    });
    /**
     * 扫描产品条码
     */
    $("#barcode_text").on("keyup",function (e) {
        if(e.keyCode != 13){
            return;
        }
        checkBarcodeText(null);
    });

    /**
     * 实际数量
     */
    $("#actualNum").on("keyup",function (e) {
        if(e.keyCode != 13){
            return;
        }
        var value = $(this).val();
        checkBarcodeText(value);
    });

    /**
     * 删除
     */
    $("#delete").on("click",function () {
        var $table = $('#secondaryLCLTable');
        var selectRows = $table.bootstrapTable("getSelections");
        if(selectRows.length == 0){
            $.messager.alert("提示","请选择需要删除的数据","info");
            return;
        }
        var scanedCount = $("#scanedCount").val();
        for(var i = 0;i<selectRows.length;i++){
            var rows = selectRows[i];
            $table.bootstrapTable('remove',{
                field: 'barcode_text',
                values: [rows.barcode_text]
            });
            scanedCount -= Number(rows.quantity);
        }
        $("#scanedCount").val(scanedCount);
    });

    /**
     * 清空
     */
    $("#resetAll").on("click",function () {
        $('#secondaryLCLTable').bootstrapTable("removeAll");
        $("#scanedCount").val(0);
        //
        $("#packageBarcode").val("");
        $("#barcode_text").val("");
        $("#actualNum").val("");
        $("#relieveLimit").attr("checked",false);
        $('#secondaryLCLTable').bootstrapTable("removeAll");
        $("#surePackageNum").val(0);
        $("#packageBarcode").focus();
    });

    /**
     * 提交
     */
    $("#commit").on("click",function () {
        //解除限制
        var relieveLimit = $("#relieveLimit");
        if(relieveLimit.prop("checked")){
            relieveLimit = "Y";
        }else {
            relieveLimit = "N";
        }
        var packageBarcodes = [];
        var barcode_texts = [];
        var barcodes = [];
        var quantitys = [];
        var $secondaryLCLTable = $("#secondaryLCLTable");
        var datas = $secondaryLCLTable.bootstrapTable("getData");
        if(datas.length<1){
        	$.messager.alert("提示","没有可提交的数据","info");
        	return;
        }
        for(var j=0;j<datas.length;j++){
            var data = datas[j];
            packageBarcodes.push(data.packageBarcode);
            barcode_texts.push(data.barcode_text);
            barcodes.push(data.barcode);
            quantitys.push(data.quantity);
        }
        $.ajax({
            type : 'post',
            url : basePath + 'secondaryLCL/commit',
            data : {
                packageBarcodes : packageBarcodes,
                barcode_texts : barcode_texts,
                barcodes : barcodes,
                quantitys : quantitys,
                status : relieveLimit
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                if($msg[0] == 'S'){
                    $.messager.alert("提示","保存成功","info",function () {
                        $("#packageBarcode").val("");
                        $("#barcode_text").val("");
                        $("#actualNum").val("");
                        $("#relieveLimit").attr("checked",false);
                        $('#secondaryLCLTable').bootstrapTable("removeAll");
                        $("#scanedCount").val(0);
                        $("#surePackageNum").val(0);
                        $("#packageBarcode").focus();
                    });
                }else {
                    $.messager.alert("提示",$msg[1],"info");
                }
            }
        });
    });
});

/**
 * 检查产品条码
 * @param quantity
 */
function checkBarcodeText(quantity) {
    //包装条码
    var packageBarcode = $("#packageBarcode").val();
    //产品条码
    var barcode_text = $("#barcode_text").val();
    if(packageBarcode == barcode_text){
        $.messager.alert("提示","产品条码错误，请重新输入","info"),function () {
            var $barcode_text = $("#barcode_text");
            $barcode_text.val("");
            $barcode_text.focus();
        };
        return;
    }
    var relieveLimit = $("#relieveLimit");
    if(relieveLimit.prop("checked")){
        relieveLimit = "Y";
    }else {
        relieveLimit = "N";
    }
    if(packageBarcode == null || packageBarcode == ''){
        $.messager.alert("提示","请输入包装条码","info",function () {
            var $packageBarcode = $("#packageBarcode");
            $packageBarcode.val("");
            $packageBarcode.focus();
        });
        return;
    }
    if(barcode_text == null || barcode_text == ''){
        $.messager.alert("提示","请输入产品条码","info",function () {
            var $barcode_text = $("#barcode_text");
            $barcode_text.focus();
        });
        return;
    }
    $.ajax({
        type : 'post',
        url : basePath + 'secondaryLCL/checkBarcode',
        data : {
            packageBarcode : packageBarcode,
            barcode : barcode_text,
            relieveLimit : relieveLimit
        },
        success : function (msg) {
            var arrays = $.parseJSON(msg);
            var flag = (arrays[0] == 'S');
            var barcode = arrays[1];
            var num = arrays[2];
            var mark = arrays[3];
            if(flag){
                if(mark == 'WX_BARCODE'){
                    if(quantity != null && quantity != ''){
                        appendTable(barcode,quantity);
                    }else {
                        var $actualNum = $("#actualNum");
                        $actualNum.removeAttr("disabled");
                        $actualNum.focus();
                    }
                }else {
                    appendTable(barcode,num);
                    var $barcode_text = $("#barcode_text");
                    $barcode_text.val("");
                    $barcode_text.focus();
                }
            }else {
                $.messager.alert("提示",arrays[1],"info",function () {
                    var $barcode_text = $("#barcode_text");
                    $barcode_text.val("");
                    $barcode_text.focus();
                });
            }
        }
    });
}

function appendTable(barcode,quantity) {
    //包装条码
    var packageBarcode = $("#packageBarcode").val();
    //产品条码
    var barcode_text = $("#barcode_text").val();
    //可包装数量
    var surePackageNum = $("#surePackageNum").val();
    //解除限制
    var relieveLimit = $("#relieveLimit");
    if(relieveLimit.prop("checked")){
        relieveLimit = "Y";
    }else {
        relieveLimit = "N";
    }
    //扫描数量
    var $scanedCount = $("#scanedCount");
    var count = $scanedCount.val();
    count = Number(count)+Number(quantity);
    var row = {
        packageBarcode : packageBarcode,
        barcode_text : barcode_text,
        barcode : barcode,
        quantity : quantity
    };
    var $secondaryLCLTable = $("#secondaryLCLTable");
    var datas = $secondaryLCLTable.bootstrapTable("getData");
    if(datas != null && datas.length > 0) {
        for(var j=0;j<datas.length;j++){
            if(datas[j].barcode_text == barcode_text){
                $.messager.alert("提示","列表已存在此产品条码","info",function () {
                    var $barcode_text = $("#barcode_text");
                    $barcode_text.val("");
                    $barcode_text.focus();
                });
                return;
            }
        }
    }
    if((Number(surePackageNum) - count) > -1){
        $secondaryLCLTable.bootstrapTable("prepend",row);
        $scanedCount.val(count);
    }else {
        if(relieveLimit == 'Y'){
            $secondaryLCLTable.bootstrapTable("prepend",row);
            $scanedCount.val(count);
        }else {
            $.messager.alert("提示","实际数量大于可包装数量","info");
            return;
        }
    }
}

/**
 * 检查包装条码
 * @param $this
 */
function checkPackaging($this) {
    var value = $this.val();
    if(value == null || value == ''){
        $.messager.alert("提示","请输入包装条码","info",function () {
            $("#packageBarcode").focus();
        });
        return;
    }
    $.ajax({
        type : 'post',
        url : basePath + 'secondaryLCL/checkPackaging',
        data : {
            packageBarcode : value
        },
        success : function (msg) {
            var $msg = $.parseJSON(msg);
            var message = $msg.message;
            if($msg.success){
                $("#surePackageNum").val(message);
                $("#barcode_text").focus();
            }else {
                $.messager.alert("提示","包装条码错误，请重新输入","info",function () {
                    var $packageBarcode = $("#packageBarcode");
                    $packageBarcode.val("");
                    $packageBarcode.focus();
                });
            }
        }
    });
}

function getTableByPage() {
    var url = basePath + 'stockInController/getStockInPrinters';
    var $table = $('#secondaryLCLTable');
    $table.bootstrapTable({
        //分页方式：client客户端分页，server服务端分页（*）
        //sidePagination: "server",
        //是否分页
        pagination: false,
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
                title: '包装条码',
                field: 'packageBarcode',
                align: 'center',
                valign: 'middle'
            }, {
                title: '产品序列号',
                field: 'barcode_text',
                align: 'center',
                valign: 'middle'
            }, {
                title: '产品编码',
                field: 'barcode',
                align: 'center',
                valign: 'middle'
            }, {
                title: '数量',
                field: 'quantity',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}
