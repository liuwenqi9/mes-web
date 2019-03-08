//借机无工单产品条码JS
$(function(){
	/**
	 * 初始化子库
	 */
	$.ajax({
        type : 'get',
        url : basePath + "jJNoProductBarcodeController/getSecondaryInventoryName",
        success : function (msg) {
            var $msg = $.parseJSON(msg);
              if($msg.success==true){
            	 var $table = $("#JJNoProductBarcode");
            	 var $secondaryInventoryName =$table.find("select[name='sourceSubLibrary']");
            	 var datas = $msg.result;
            	 $secondaryInventoryName.empty();
            	 $secondaryInventoryName.append("<option value=''>---请选择---</option>");
            	 for (var i = 0; i < datas.length; i++) { 
                   $secondaryInventoryName.append("<option value='" + datas[i].sourceSubLibrary + "'>" 
                		   + datas[i].sourceSubLibrary + "</option>"); 
                 }
              } 
        }
    });	
	var $table = $("#JJNoProductBarcode");
	var $type =$table.find("select[name='type_type']");
	//$type.empty();
	//$type.append("<option value=''>---请选择---</option>");
	$type.append("<option value='S_INV'>导入库存</option>");
	//$type.append("<option value='SHIP'>售后</option>");  
    
	/**
	 * 打印二维码
	 */
	$('#printBarcode').on('click',function(){
		var barcode = $('#printCode').val();
		if(barcode==''){
			 $.messager.alert("提示","产品码不能为空","info");
			 return;
		}
		$.ajax({
            type : 'post',
            url : basePath + "jJNoProductBarcodeController/printProductBarcode",
            data : { barcode:barcode
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                  if($msg.success==false){
	                $.messager.alert("提示",$msg.message,"info",function () {  
	                });
                  }else{
                	  window.open($msg.message);
                  }
            }
        });	
		
	});
	
	 /**
     * 借机无工单产品条码更新
     */
    $("#update").on("click",function () { 
        var $table = $("#JJNoProductBarcode");
        //产品编码，物料id，来源子库，来源货位
        var types=[],barcodeTexts=[],inventoryItemIds=[],sourceSubLibrarys=[],sourceLocations=[];
        var dom_types = $table.find("select[name='type_type']");
        var dom_barcodeTexts = $table.find("input[name='barcodeText']");
        var dom_inventoryItemIds = $table.find("input[name='inventoryItemId']");
        var dom_sourceSubLibrarys = $table.find("select[name='sourceSubLibrary']");
        var dom_sourceLocations = $table.find("select[name='sourceLocation']");
        for(var i = 0;i<dom_types.length;i++){ 
        	var dom_type = dom_types[i].value;
        	if(dom_type == null || dom_type == ''){
                $.messager.alert("提示","第"+(i+1)+"条，请选择操作类型","info");
                return;
            }
        	types.push(dom_type);
        	
        	var barcodeText = dom_barcodeTexts[i].value;
        	if(barcodeText == null || barcodeText == ''){
                $.messager.alert("提示","第"+(i+1)+"条产品编码为空","info");
                return;
            }
        	if(barcodeText.length!=10){
        		$.messager.alert("提示","第"+(i+1)+"条产品条码长度不为10位！","info");
                return;
        	}
        	barcodeTexts.push(barcodeText);
        	
            var inventoryItemId = dom_inventoryItemIds[i].value;
            if(inventoryItemId == null || inventoryItemId == ''){
                $.messager.alert("提示","第"+(i+1)+"条数据物料编码不存在，请重新输入","info");
                return;
            }
            inventoryItemIds.push(inventoryItemId);
            
            var sourceSubLibrary = dom_sourceSubLibrarys[i].value;
            if(sourceSubLibrary == null || sourceSubLibrary == ''){
                $.messager.alert("提示","第"+(i+1)+"条数据未选择子库","info");
                return;
            }
            sourceSubLibrarys.push(sourceSubLibrary);
            
            var sourceLocation = dom_sourceLocations[i].value; 
            if(sourceLocation == null || sourceLocation == ''){
                $.messager.alert("提示","第"+(i+1)+"条数据未选择货位","info");
                return;
            }
            sourceLocations.push(sourceLocation);
        }
        $.ajax({
            type : 'post',
            url : basePath + "jJNoProductBarcodeController/updateProductBarcode",
            data : {
            	type:types.join(","),
            	barcodeText:barcodeTexts.join(","),
            	inventoryItemId : inventoryItemIds.join(","), 
                sourceSubLibrary : sourceSubLibrarys.join(","),
                sourceLocation :sourceLocations.join(",")
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                  if($msg.success){
	                $.messager.alert("提示",$msg.message,"info",function () { 
	                	$table.empty();
	                	location.reload();
	                });
                  }else{
                	  $.messager.alert("提示",$msg.message,"info",function () {  
  	                });
                  }
            }
        });
    });  
	
});

/**
 * 编码失去焦点事件
 * @param th
 */
function codeBlur(th) {
    var $this = $(th);
    var $tr = $this.parent().parent();
    var segment1 =$this.val().trim();
    var $prodType = $tr.find("input[name='prodType']");
    var $inventoryItemId = $tr.find("input[name='inventoryItemId']");
    $.ajax({
        type : 'get',
        url : basePath + "jJNoProductBarcodeController/verifyCodeId?segment1="+segment1,
        success:function (msg) {
        	var $msg = $.parseJSON(msg);
            if($msg.success==false){
            	$.messager.alert("提示",$msg.message,"info");
            	$this.val('');
            	$prodType.val('');
            	$inventoryItemId.val('');
            }else{ 
            	$prodType.val($msg.result.prodType); 
            	$inventoryItemId.val($msg.result.inventoryItemId);
            }
        	
        }
    });

}
/**
 * 子库 值变化时
 */
function sourceSubLibraryChange(th) {
    var $this = $(th);
    var $tr = $this.parent().parent();
    var secondaryInventoryName =$this.val().trim(); 
    //货位 
	 var $sourceLocation = $tr.find("select[name='sourceLocation']");
    $.ajax({
        type : 'get',
        url : basePath + "jJNoProductBarcodeController/getLocattionCode?locattionCode="+secondaryInventoryName,
        success : function (msg) {
            var $msg = $.parseJSON(msg);
              if($msg.success==true){  
            	 var datas = $msg.result;   
            	 $sourceLocation.empty();
            	 $sourceLocation.append("<option value=''>--请选择--</option>");
            	 for (var i = 0; i < datas.length; i++) { 
            		 $sourceLocation.append("<option value='" + datas[i].sourceLocation + "'>" 
                		   + datas[i].sourceLocation + "</option>"); 
                 }
              }else{
            	  $sourceLocation.empty();
            	  $sourceLocation.append("<option value=''>--请选择--</option>");
              } 
        }
    });	 
}

/**
 * 操作类型改变时 触发
 */
function typeChange(th) {
    var $this = $(th);
    var $tr = $this.parent().parent();
    var type =$this.val().trim();  //类型值S_INV ,SHIP
 	var $sourceSubLibrary = $tr.find("select[name='sourceSubLibrary']");//子库
	var $sourceLocation = $tr.find("select[name='sourceLocation']");//货位
    if(type=='SHIP'){
    	$sourceSubLibrary.empty();
    	$sourceLocation.empty();
    	$sourceSubLibrary.append("<option value='L001'>L001</option>");
    	$sourceLocation.append("<option value='L001'>L001</option>");  
    	//$sourceLocation.
    } else  if(type=='S_INV'){ 
    	$sourceLocation.empty();
    	$.ajax({
            type : 'get',
            url : basePath + "jJNoProductBarcodeController/getSecondaryInventoryName",
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                  if($msg.success==true){  
                	 var datas = $msg.result;
                	 $sourceSubLibrary.empty();
                	 $sourceSubLibrary.append("<option value=''>---请选择---</option>");
                	 for (var i = 0; i < datas.length; i++) { 
                		 $sourceSubLibrary.append("<option value='" + datas[i].sourceSubLibrary + "'>" 
                    		   + datas[i].sourceSubLibrary + "</option>"); 
                     }
                  } 
            }
        });	
    }
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
     
}

