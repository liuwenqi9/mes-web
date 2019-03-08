String.prototype.replaceAll = function (AFindText,ARepText){
	raRegExp = new RegExp(AFindText,"g");
	return this.replace(raRegExp,ARepText);
};

eGdUtil = function(){};

//判断是否为空
eGdUtil.isEmpty = function(str){  
	 if((str === undefined) || (str == null) || (str.length == 0)) {
		 return true;  
	 }
	 else {
		 return false;  
	 }
};


//  功能：Ajax请求--json
eGdUtil.requestJsonAjax = function(url,params,successFn,failFn,timeout)
{
	$.ajax({
		type : 'POST',
		data : params,
		url : url,
		timeout:(eGdUtil.isEmpty(timeout))?600000:timeout,
		success:function(response){
//			alert(response);
			var result = strToJson(response);
			successFn(result);

		},
		failure:function(response){
//			alert(response);
			var result = strToJson(response);
			if (!eGdUtil.isEmpty(failFn)){
				failFn(result);
			}
		}
	})
};



//转换long类型为日期
eGdUtil.changeLongToDate = function(value) {
	if (value != null) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
};

//比较两个时间大小
eGdUtil.compareTwoDate = function(startDate,endDate){
  var d, s, t, t2;
  var MinMilli = 1000 * 60;
  var HrMilli = MinMilli * 60;
  var DyMilli = HrMilli * 24;
  d = new Date();
  t = Date.parse(startDate);
  t2= Date.parse(endDate); 
  s = Math.round((t2-t)/ DyMilli);
  return s;
};