

$(function() { 
	//初始化数据  
	/*eGdUtil.requestJsonAjax(basePath+ 'inspectionController/getInspectionByKey', 
							{id:$('#packing_barcode_id').val()}, function(datas){
								var data = strToJson(datas);
									}
	);*/
	$('#print_btn').on('click',function(){printdiv();});
	
});   
//打印页面的背景颜色由在 页面设置，
function printdiv()  
{   
var opo = 	window.open('','oPop');
var headstr = "<html><head>"; 
headstr +='<meta charset="UTF-8">';
headstr +='<title>打印页面</title></head><body>';
var footstr = "</body></html>";
document.getElementById("print_btn").setAttribute('type', 'hidden'); 
var printData = document.getElementById("print_div1").innerHTML; //获得 div 里的所有 html 数据 
document.getElementById("print_btn").setAttribute('type', 'button');
var str= headstr+printData+footstr;
opo.document.write(str); 
opo.print();
opo.close();  
} 