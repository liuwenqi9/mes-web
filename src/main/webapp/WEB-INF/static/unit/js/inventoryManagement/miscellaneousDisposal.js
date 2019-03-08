/*****************************************************************************
 * 
 * 杂项事务处理JS
 * 
 *****************************************************************************/
//全局变量 事务处理类型
var transactionTypeList;
//全局变量 序号
//行下标从0开始
var rowIndex=0;
//初始化获取事务处理类型数据
function initGetTransactionType() {
	var url = basePath + 'miscellaneousDisposalController/getTransactionTypeList';
	var suc = function(data) {
		transactionTypeList = strToJson(data);  
		
	}
	eGdUtil.requestJsonAjax(url, null, suc);
};
//设置事务处理类型下拉框
function initSetTransactionType(j) {
	var id='#transactionType_id'+j;
	$(id).empty();
	$(id).append("<option value='0'>--请选择事务处理类型--</option>");
	for(var i=0;i<transactionTypeList.length;i++){ 
		if(transactionTypeList[i].LOOKUP_CODE){
			$(id).append("<option value='"+transactionTypeList[i].LOOKUP_CODE+"'>"+transactionTypeList[i].DESCRIPTION+"</option>");
		}
	} 
}
//选择子库
function searchSubinventoryCode(lab){
	//子库ID
	var subId=lab.children[0].id;
	//弹出框
	subinventorySearchBtnClick(subId);
}
//选择货位
function searchCargospace(lab){
	//货位ID
	var cargospaceId=lab.children[0].id;
	//序号
	//var index=cargospaceId.charAt(cargospaceId.length -1);
	var index=lab.children[0].attributes.row.value;  
	//子库值
	var sublibraryName=$('#subinventoryCode'+index).val();
	if(sublibraryName){
		//弹出框
		cargospaceSearchBtnClick(cargospaceId,sublibraryName);
	}else{
		showErrorMsg("请先选择子库！");
	}
}
//选择来源
function searchSource(lab){
	//来源ID
	var sourceId=lab.children[0].id;
	//弹出框
	sourceSearchBtnClick(sourceId);
}
//根据产品条码获取物料信息
function searchWipByBarCode(lab){
	//序号
	//var index=lab.id.charAt(lab.id.length -1);
	var index=lab.attributes.row.value;  
	//数量ID
	var quantityId='quantity'+index;
	//编码ID
	var lotNoId='lotNo'+index;
	//描述ID
	var desrId='desr'+index;
	//条码值
	var barCode=lab.value;
	
	//查询
	var params = { 
			barCode : barCode
	};
	var url = basePath + 'miscellaneousDisposalController/searchWipInfoByBarCode';
	var success = function(datass) { 
//		resultLength=datass.length;
		var flg=datass.flag;
		if(flg=='E'){
			showErrorMsg(datass.errMsg); 
		}else{
			//获取成功
			$('#'+quantityId).val(datass.startQuantity);
			$('#'+lotNoId).val(datass.lotNo);
			$('#'+desrId).val(datass.description);
		}
		
	};
	eGdUtil.requestJsonAjax(url, params, success);
}
//增加一行
function addLine(index){
	var newRow=
		'<div id="row_div" is_row_div="Y" row="'+index+'">'
//事务处理类型
	+'<label class="my-col-sm-1-5 control-label no_padding"  >'
	+'<select type="text" class="form-control no_padding" id="transactionType_id'+index+'"></select> '
	+'</label> '
// 来源 -->
	+'<label class="col-sm-2 control-label no_padding" onclick="searchSource(this);"  >'
	+'<input type="text" class="form-control no_padding" id="sourceCode'+index+'" name="mesTransactionsInterface.sourceId" readonly />'
	+'</label>'
// 子库 -->
	+'<label class="my-col-sm-0-5 control-label no_padding" onclick="searchSubinventoryCode(this);"  >'
	+'<input type="text" class="form-control no_padding" id="subinventoryCode'+index+'" name="mesTransactionsInterface.subinventoryCode" />'
	+'</label> '
// 货位 -->
	+'<label class="my-col-sm-0-5 control-label no_padding" onclick="searchCargospace(this);"  >'
	+'<input type="text" class="form-control no_padding" id="locattionCode'+index+'" row="'+index+'" name="mesTransactionsInterface.locattionCode" readonly/>'
	+'</label>'
// 产品条码 -->
	+'<label class="my-col-sm-1-5 control-label no_padding"  >'
	//输入产品条码后，鼠标在别的空白的地方点一下，产品条码输入框失去焦点时，就会去查询其信息，填入数量，编码，描述输入框
	+'<input value="" type="text" class="form-control no_padding" id="barcodeText'+index+'" row="'+index+'" name="mesTransactionsInterface.barcodeText" onblur="searchWipByBarCode(this);"/>'
	+'</label>'
// 数量 -->
	+'<label class="my-col-sm-0-5 control-label no_padding"  >'
	+'<input type="text" class="form-control no_padding" id="quantity'+index+'" readonly/>'
	+'</label>'
// 编码 -->
	+'<label class="my-col-sm-1-5 control-label no_padding" >'
	+'<input type="text" class="form-control no_padding" id="lotNo'+index+'"  readonly/>'
	+'</label>'
// 描述 -->
	+'<label class="col-sm-2 control-label no_padding" >'
	+'<input type="text" class="form-control no_padding" id="desr'+index+'" readonly/>'
	+'</label>'
// 备注 -->
	+'<label  class="col-sm-1 control-label no_padding"  >'
	+'<input type="text" class="form-control no_padding" id="transactionReference'+index+'" name="mesTransactionsInterface.transactionReference" />'
	+'</label>'
// 删除按钮 -->
	+'<label  class="col-sm-1 control-label no_padding"  >'
	+'<button type="button" class="btn btn-danger btn-sm" onclick="del_row('+index+');"'
	+'data-backdrop="static" data-target="#editCargospaceModal"'
	+'id="cargospace_edit_btn'+index+'">'
	+'<i class="glyphicon glyphicon-trash"></i>删除行'
	+'</button>'
	+'</label>'
	+'</div>';
	//增加行
	$('#miscellaneousDisposal_table').append(newRow);
}
//删除一行
function del_row(index){
	$('#row_div[row="'+index+'"]').remove();
}
//初始化控件
function init(){ 
	//提交按钮事件
	$('#miscellaneousDisposal_commit_btn').click(function(){ 

//		var datas = $('#stockInPrinterTable').bootstrapTable("getData");
		//装所有行数据的list
		var paramss = new Array();
		//is_row_div="Y"
		//遍历获得数据List
		//选择miscellaneousDisposal_table下的div子元素，并且其is_row_div="Y"
		var row_div_list=$('#miscellaneousDisposal_table>div[is_row_div=Y]');
		//var row_div_list=document.getElementById("row_div");
		//var row_div_list=$('#miscellaneousDisposal_table').children("div[is_row_div=Y]");
		if(row_div_list.length<=0){
			//提示请选择子库
			showErrorMsg("请添加一行数据！");
			return;
		}
		//遍历每一个行div，取得其数据
		//for (i in row_div_list)//此方案不可行，有时i=length;
		for (var i=0;i<row_div_list.length;i++)
		{
			var one_row_data={  
					//事务处理类型
					transactionType:"",
					//来源
					sourceCode : "",
					//子库
					subinventoryCode: "",
					//货位
					locattionCode: "",
					//产品条码
					barcodeText: "",
//					//数量
//					quantity: 0,
					//备注
					transactionReference:""
			};
		var row_div=row_div_list[i];
		//得到行号
		//var row_no=row_div.attributes.row.value;
		
		//取得每个行DIV的子元素列表
		//用jquery方案找元素此语句执行错误,此方案不可行
		//var children_list=row_div.children();
		//此方案OK
		var children_list=row_div.children;
		
		//遍历行DIV的子元素列表，得到每一列的数据//label
			//for (j in children_list)//此方案不可行，有时j=length;
		for(var j=0;j<10;j++)
			{
				
				
				//还得找下一级的子元素，这一级的元素才是最终的数据存放地
				//grandson_children是各个输入框，下拉框
				var grandson_children=children_list[j].children[0];
				//每一行有0-9个列，第9列是按钮，所以跳出循环
				if(j==5||j==6||j==7||j==9){
					continue;
				}
				switch (j)
				{
				case 0:
					//事务处理类型
					one_row_data.transactionType=grandson_children.value;
					if(one_row_data.transactionType=='0'){
						//提示请选择事务类型
						showErrorMsg("请选择事务类型");
						return;
					}else{
						break;
					}
				case 1:
					//来源
					one_row_data.sourceCode=grandson_children.value;
					if(one_row_data.sourceCode==''){
						//提示请选择来源
						showErrorMsg("请选择来源");
						return;
					}else{
						break;
					}
				case 2:
					//子库
					one_row_data.subinventoryCode=grandson_children.value;
					if(one_row_data.subinventoryCode==''){
						//提示请选择子库
						showErrorMsg("请选择子库");
						return;
					}else{
						break;
					}
				case 3:
					//货位
					one_row_data.locattionCode=grandson_children.value;
					if(one_row_data.locattionCode==''){
						//提示请选择货位
						showErrorMsg("请选择货位");
						return;
					}else{
						break;
					}
				case 4:
					//产品条码
					one_row_data.barcodeText=grandson_children.value;
					if(one_row_data.barcodeText==''){
						//提示请输入产品条码
						showErrorMsg("请输入产品条码");
						return;
					}else{
						break;
					}
//				case 5:
//					//数量
//					one_row_data.quantity=parseInt(grandson_children.value);
//					if(isNaN(one_row_data.quantity)||one_row_data.quantity==0){
//						//提示请选择数量
//						showErrorMsg("请选择数量");
//						return;
//					}else{
//						break;
//					}
//				case 6:
//					//编码
//					one_row_data.//=grandson_children.value;
//					break;
//				case 7:
//					//描述
//					one_row_data.//=grandson_children.value;
//					break;
				case 8:
					//备注
					one_row_data.transactionReference=grandson_children.value;
					break;
				}
			}
			paramss.push(one_row_data);
		}
		//提交
		
		var params={
				params:JSON.stringify(paramss)
		}
		var url = basePath + 'miscellaneousDisposalController/miscellaneousDisposalSave';
		var successFn = function(datass) { 
			// 关闭遮罩
			$.messager.progress('close');
//			resultLength=datass.length;
			var data=strToJson(datass)
			var flg=data.flag;
			if(flg=='E'){
				showErrorMsg(data.errorMsg); 
			}else{
				//获取成功
				
				//选择miscellaneousDisposal_table下的div子元素，并且其is_row_div="Y"
				var row_div_list=$('#miscellaneousDisposal_table>div[is_row_div=Y]').remove();
				
				rowIndex=0;
				$("#addRow_btn").click();
				showInfoMsg("提交成功");

			}
			
		};
		var failFn = function(datass) { 
			// 关闭遮罩
			$.messager.progress('close');
		};
		var errorFn = function(datass) { 
			// 关闭遮罩
			$.messager.progress('close');
		};
		// 验证通过,弹出遮罩
		$.messager.progress({
			text : '正在提交...',
			interval : 200
		});
		//eGdUtil.requestJsonAjax(url, params, success,fail,error);
		$.ajax({
			type : 'POST',
			data : params,
			url : url,
			timeout:600000,
			success:function(response){
//				alert(response);
				var result = strToJson(response);
				successFn(response);

			},
			failure:function(response){
//				alert(response);
				var result = strToJson(response);
				failFn(result);
			},
			error: function(response) { 
				//alert(response); 
				var result = strToJson(response);
				errorFn(result);
				showErrorMsg(response.responseText);
				} 
		});
	});
}

$(function() {
	//初始化获取事务处理类型数据
	initGetTransactionType();
	 
	//"增加行"按钮事件监听
	$("#addRow_btn").click(function(){
		addLine(rowIndex);
		//初始化设置事务处理类型数据
		initSetTransactionType(rowIndex);
		//行数量+1
		rowIndex++;
	} );
	//初始化就添加一行
	$("#addRow_btn").click();
	// 初始化控件
	init(); 

})
