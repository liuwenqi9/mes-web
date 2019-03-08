//发运包装条码JS

$(function(){
    //获取当前条码
    get_current_barcode();

    //刷新条码
    $("#refreshBarcode").on("click",function(){
        get_current_barcode();
    });

    //确认
    $("#barcodeOK").on("click",function(){
        //当前条码
        var barcode = $("#productBarcode").val();
        //打印张数
        var printNum = $("#printNum").val();
        if(printNum < 1){
            alert("打印张数必须大于0");
            return;
        }
        $.ajax({
            type : 'post',
            url : basePath + 'shoppackBarcodeController/getSEcode',
            data : {
                printNum : printNum
            },
            success : function(msg){
                var $msg = $.parseJSON(msg);
                if($msg.success){
                    var codes = $msg.message;
                    codes = codes.split(",");
                    //当前条码
                    $("#productBarcode").val(codes[1]);
                    //起始条码
                    $("#startCode").val(codes[0]);
                    //终止条码
                    $("#endCode").val(codes[1]);
                }
            }
        });
    });

    /**
     * 打印二维码
     */
    $("#printBarcode").on("click",function(){
        //起始条码
        var startCode = $("#startCode").val();
        if(startCode == null || startCode == ''){
            alert("起始条码不能为空!");
            return;
        }
        //终止条码
        var endCode = $("#endCode").val();
        if(endCode == null || endCode == ''){
            alert("终止条码不能为空!");
            return;
        }
        $.ajax({
            type : 'post',
            url : basePath + 'shoppackBarcodeController/toBarCodeByQRCode',
            data : {
                startCode : startCode,
                endCode : endCode
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

    /**
     * 条码补打
     */
    $("#print_patch").on("click",function(){
        var code = $("#fycode").val();
        if(code == null || code == ''){
            alert("请选择需要打印的条码!");
            return;
        }
        $.ajax({
            type : 'post',
            url : basePath + 'shoppackBarcodeController/printPatch',
            data : {
                code : code
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
    })
});

/**
 * 获取当前条码
 */
function get_current_barcode(){
    $.ajax({
        type : 'post',
        url : basePath + 'shoppackBarcodeController/getCurrentBarcode',
        success : function(msg){
            var $msg = $.parseJSON(msg);
            if(!$msg.success){
                alert($msg.message);
                return;
            }
            $("#productBarcode").val($msg.message);
            //起始条码
            $("#startCode").val("");
            //终止条码
            $("#endCode").val("");
        }
    });
}
