/*==================================================================*\
	功能：获取字符串长度，中文字符按2位长度处理
	输入：
		value：要获取长度的字符串值
	版本: 殷建清  2009.10.20  v1.0
\*==================================================================*/
function stringLen(value) {
	var iCount = 0 ;
	if (value != ""){
		var sStr = value.split("");
		for (i = 0 ; i < sStr.length ; i ++){
			if (escape(sStr[i]).indexOf("%u",0) == -1)
				iCount = iCount + 1;
			else
				iCount = iCount + 2;
		}
	}
	return iCount;
}
/*==================================================================*\
	功能：正则表达式匹配空格 tab符 回车等
	输入：
		value：返回去掉空格的字符串
	版本: 石磊  2010.01.25  v1.0
\*==================================================================*/
function stringTrim(value) {
	//正则表达式匹配空格 tab符 回车等
	var reg = /\s+/g ;
	value = value.replace(reg,"");
	return value;
}
/*==================================================================*\
	功能：DhtmlGrid中单元格校验，判断是否为整数类型
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
		integerLen：整数部分最大长度
		decimalLen：小数部分最大长度
	版本: 殷建清  2009.10.20  v1.0
\*==================================================================*/
function isInteger(value, rowNum, colNum) {
	if (value == '') {
		return true;
	}
    var flag = !!value.match(/(^-?\d+$)/); 
    if (!flag){
    	ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列只能输入整数！"});
    	//alert("第" + rowNum + "行,第" + colNum + "列只能输入整数！");
    }
    return flag;
}
/*==================================================================*\
	功能：DhtmlGrid中单元格校验，判断是否为数字类型
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
		integerLen：整数部分最大长度
		decimalLen：小数部分最大长度
	版本: 殷建清  2009.10.20  v1.0
\*==================================================================*/
function isNumeric(value, rowNum, colNum) {
	var flag = true;
	if (value!=''&&value!=' ') {
		var flag = !!value.match(/(^-?\d\d*[\.|,]\d*$)|(^-?\d\d*$)|(^-?[\.|,]\d\d*$)/); 
	    if (!flag){
	    	ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列只能输入数字！"});
	    	//alert("第" + rowNum + "行,第" + colNum + "列只能输入数字！");
	    }
	}
    
    return flag;
}
/*==================================================================*\
	功能：DhtmlGrid中单元格校验，判断数字类型长度是否符合要求
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
		integerLen：整数部分最大长度
		decimalLen：小数部分最大长度
	版本: 殷建清  2009.10.20  v1.0
\*==================================================================*/
function isNotNumericOverlength(value, rowNum, colNum, integerLen, decimalLen) {
    if (decimalLen < 1 ){
    	if (isInteger(value, rowNum, colNum)){
    		if (value.length > integerLen){
    			ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列最最大长度为 " + integerLen + " ！"});
	    		//alert("第" + rowNum + "行,第" + colNum + "列最最大长度为 " + integerLen + " ！");
	    		return false;
    		}
    	}else{
    		return false;
    	}
    }else{
    	if (isNumeric(value, rowNum, colNum)){
    		var values = value.split(".");
    		if (values.length == 1){
    			if(value.length > integerLen){
    				ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列整数位数不能超过" + integerLen + "！"});
    				//alert("第" + rowNum + "行,第" + colNum + "列整数位数不能超过 " + integerLen + "！");
    				return false;
    			}
    		}else if (values.length == 2){
    			if(values[0].length > integerLen){
    				ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列整数位数不能超过" + integerLen + ",<br>小数位数不能超过" + decimalLen + "！"});
    				//alert("第" + rowNum + "行,第" + colNum + "列整数位数不能超过 " + integerLen + ",<br>小数位数不能超过" + decimalLen + "！");
    				return false;
    			}
    			if(values[1].length > decimalLen){
    				ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列整数位数不能超过" + integerLen + ",<br>小数位数不能超过" + decimalLen + "！"});
    				//alert("第" + rowNum + "行,第" + colNum + "列整数位数不能超过 " + integerLen + ",<br>小数位数不能超过" + decimalLen + "！");
    				return false;
    			}
    		}
    	}else{
    		return false;
    	}
    }
    return true;
}
/*==================================================================*\
	功能：DhtmlGrid中单元格校验，判断是否为空
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
	版本: 殷建清  2009.10.20  v1.0
\*==================================================================*/
function isNotEmpty(value, rowNum, colNum) {
    if (value == ""){
    	ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列值不能为空！"});
    	//alert("第" + rowNum + "行,第" + colNum + "列值不能为空！");
    	return false;
    }
    return true;
}
/*==================================================================*\
	功能：DhtmlGrid中单元格校验，判断字符串长度是否符合需要
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
		minLen：最小长度
		minLen：最大长度
	版本: 殷建清  2009.10.20  v1.0
	修改:修改了isNotOverlength,此校验不对特殊字符进行校验 lishuai 2010.01.20  v1.1
\*==================================================================*/
function isNotOverlengthArea(value, rowNum, colNum, minLen, maxLen) {
    var valueLen = stringLen(value);
    if (minLen > 0 ){
    	if (valueLen < minLen){
    		ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列最小长度为 " + minLen + " ！"});
    		//alert("第" + rowNum + "行,第" + colNum + "列最小长度为 " + minLen + " ！");
    		return false;
    	}
    }
    if (minLen > maxLen ){
		if (valueLen > minLen){
			ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列长度为 " + minLen + " ！"});
    		//alert("第" + rowNum + "行,第" + colNum + "列长度为 " + minLen + " ！");
    		return false;
    	}
    }else{
		if (valueLen > maxLen){
			ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列最大长度为 " + maxLen + " ！"});
    		//alert("第" + rowNum + "行,第" + colNum + "列最大长度为 " + maxLen + " ！");
    		return false;
    	}
    }
    return true;
}
/*==================================================================*\
	功能：DhtmlGrid中单元格校验，判断字符串长度是否符合需要
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
		minLen：最小长度
		minLen：最大长度
	版本: 殷建清  2009.10.20  v1.0
\*==================================================================*/
function isNotOverlength(value, rowNum, colNum, minLen, maxLen) {
    var valueLen = stringLen(value);
    if (minLen > 0 ){
    	if (valueLen < minLen){
    		ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列最小长度为 " + minLen + " ！"});
    		//alert("第" + rowNum + "行,第" + colNum + "列最小长度为 " + minLen + " ！");
    		return false;
    	}
    }
    if (minLen > maxLen ){
		if (valueLen > minLen){
			ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列长度为 " + minLen + " ！"});
    		//alert("第" + rowNum + "行,第" + colNum + "列长度为 " + minLen + " ！");
    		return false;
    	}
    }else{
		if (valueLen > maxLen){
			ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列最大长度为 " + maxLen + " ！"});
    		//alert("第" + rowNum + "行,第" + colNum + "列最大长度为 " + maxLen + " ！");
    		return false;
    	}
    }
    return true;
}
/*==================================================================*\
	功能：DhtmlGrid中单元格校验，判断字符串长度是否符合需要
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
		minLen：最小长度
		minLen：最大长度
	版本: 石磊  2010.03.02  v1.0
\*==================================================================*/
function isNotOverlengthContainspaces(value, rowNum, colNum, minLen, maxLen) {
    var valueLen = stringLen(value);
    if (minLen > 0 ){
    	if (valueLen < minLen){
    		ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列最小长度为 " + minLen + " ！"});
    		//alert("第" + rowNum + "行,第" + colNum + "列最小长度为 " + minLen + " ！");
    		return false;
    	}
    }
    if (minLen > maxLen ){
		if (valueLen > minLen){
			ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列长度为 " + minLen + " ！"});
    		//alert("第" + rowNum + "行,第" + colNum + "列长度为 " + minLen + " ！");
    		return false;
    	}
    }else{
		if (valueLen > maxLen){
			ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列最大长度为 " + maxLen + " ！"});
    		//alert("第" + rowNum + "行,第" + colNum + "列最大长度为 " + maxLen + " ！");
    		return false;
    	}
    }
    if (/^[^~!！@#%&()<>《》？￥……（）"':;\.\[\]\{\}\+\*\-\\\|\^\$\?]*$/.test(value)) {
		return true;
	} else {
		ymPrompt.errorInfo({title:"提示",message:"禁止输入^~!！@#%&()<>“':;\.\[\]\{\}\+\*\-\\\|\^\$\?及空格符号"});
		//alert("禁止输入^~!！@#%&()<>“':;\.\[\]\{\}\+\*\-\\\|\^\$\?及空格符号");
		return false;
	}
    return true;
}
/*==================================================================*\
	功能：DhtmlGrid中单元格校验，在表格中判断数据是不是重复
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
		type：1代表判断列重复，2代表判断行重复
	版本: 殷建清  2009.10.20  v1.0
\*==================================================================*/
function isNotRepeatInGrid(mygrid,value, rowNum, colNum, type) {
	if (type == 1){
		var rowCount = mygrid.getRowsNum();
		if (rowCount < 2) return true;
		for (i = 0; i < rowCount; i++){
			var rowValue = mygrid.cells(mygrid.getRowId(i),colNum -1).getValue();
			if (i != (rowNum -1) && rowValue == value) {
				ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列 与 第" + (i + 1) + "行,第" + colNum + "列重复！"});
				//alert("第" + rowNum + "行,第" + colNum + "列 与 第" + (i + 1) + "行,第" + colNum + "列重复！");
				return false;
			}
		}
	}else{
		var colCount = mygrid.getColumnsNum();
		if (rowCount < 2) return true;
		for (i = 0; i < colCount; i++){
			var colValue = mygrid.cells(mygrid.getRowId(rowNum -1),i).getValue();
			if (i != (colNum -1) && colValue == value) {
				ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列 与 第" + rowNum + "行,第" + (i + 1) + "列重复！"});
				//alert("第" + rowNum + "行,第" + colNum + "列 与 第" + rowNum + "行,第" + (i + 1) + "列重复！");
				return false;
			}
		}
	}
	return true;
}
/*==================================================================*\
	功能：DhtmlGrid中单元格校验，从数据库中判断数据是不是重复
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
		validateUrl：校验链接
	版本: 殷建清  2009.10.20  v1.0
\*==================================================================*/
function isNotRepeat(value, rowNum, colNum, validateUrl) {
   	var flag = true;
   	$.ajax({
		type: "POST",
		url: validateUrl,
		data: "name="+value,
		async: false,
		success: function(data){
  					if (data!="OK") {
  					ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列值不能重复，请重新输入！"});
              	    //alert("第" + rowNum + "行,第" + colNum + "列值不能重复，请重新输入！");
              	    flag =  false;
              	}
		}
	});
	return flag;
}
/*==================================================================*\
	功能：添加、修改页面使用 判断输入的字符串长度是否合法
	输入：
		value：输入框值
		minLen：最小长度  minLen等于0时可以为空
		maxLen：最大长度
		name：输入框名称
	返回：boolean
	版本: 石磊  2009.12.09  v1.0
\*==================================================================*/
function isStrThanLength(object, minLen, maxLen, name){
	var value = ""
	if (object!=null) {
		value = object.value;
	} 
	var valueLen = stringLen(value);
	
    if (minLen > 0 ){
    	if (valueLen < minLen){
    		ymPrompt.errorInfo({title:"提示",message:"["+name+"]字符长度不能小于"+minLen+"！"});
    		//alert("["+name+"]字符长度不能小于"+minLen+"！");
    		//object.focus();
    		return false;
    	}
    }
    if (minLen > maxLen ){
		if (valueLen > minLen){
			ymPrompt.errorInfo({title:"提示",message:"["+name+"]字符长度不能大于"+minLen+"！"});
    		//alert("["+name+"]字符长度不能大于"+minLen+"！");
    		//object.focus();
    		return false;
    	}
    }else{
		if (valueLen > maxLen){
			ymPrompt.errorInfo({title:"提示",message:"["+name+"]字符长度不能大于"+maxLen+"！"});
    		//alert("["+name+"]字符长度不能大于"+maxLen+"！");
    		//object.focus();
    		return false;
    	}
    }
    return true;
}
/*==================================================================*\
	功能：添加、修改页面使用 判断输入的URL是否合法
	输入：
		value：输入框值
		minLen：最小长度
		maxLen：最大长度
		name：输入框名称
	返回：boolean
	版本: 石磊  2009.12.09  v1.0
	校验范围
	支持http://www.sohu.com/1.html
	   http://192.123.23.23:8088/ceeems/emsLogin.action?m=toMain&a=1
	   basworkingprocedure.action?m=toIndex
\*==================================================================*/
function isLegalURL(object, minLen, maxLen, name){
	//长度是否合法 true 合法; false 不合法
	var flag = isStrThanLength(object, minLen, maxLen, name);
	var reg=/^(http|https):\/\/[A-Za-z0-9_]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^\"\"])*$/; 
	var reg1=/^(http|https):\/\/(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\:\d{4}\/[A-Za-z0-9_]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^\"\"])*$/;
	var reg2=/^[\/A-Za-z0-9_]+\.[A-Za-z0-9]+[\/=\?%\-&~`@[\]\':+!]*([^\"\"])*$/; 
	if (flag) {
		if (reg.test(object.value) || reg1.test(object.value) || reg2.test(object.value)||object.value=='#') {
			//ymPrompt.succeedInfo({title:"提示",message:"["+name+"]保存成功！"});
			return true;
		} else {
			ymPrompt.errorInfo({title:"提示",message:"["+name+"]输入有误！"});
			object.focus();
			return false;
		}
	} else {
		return false;
	}
	
	return true;
}
/*==================================================================*\
	功能：text添加、修改页面使用 判断输入的字符串内容是否合法,不可以包含^~!！@#%&()<>"':;\.\[\]\{\}\+\*\-\\\|\^\$\?符号及空格
	输入：
		value：输入框值
		minLen：最小长度  minLen小于0不能为空 等于0可以为空
		maxLen：最大长度
		name：输入框名称
	返回：boolean
	版本: 石磊  2009.12.09  v1.0
\*==================================================================*/
function isLegalText(object, minLen, maxLen, name){
	//最小长度等于0即不能为空
	if (minLen<0) {
	   if(isNotEmptyText(object.value,name)==false)
	   return false;
	}
	//长度是否合法 true 合法; false 不合法
	var flag = isStrThanLength(object, minLen, maxLen, name);
	if (flag) {
		if (/^[^~!！@#%&()<>"':;\.\[\]\{\}\+\*\-\\\|\^\$\?\？\s]*$/.test(object.value)) {
			return true;
		} else {
			ymPrompt.errorInfo({title:"提示",message:"["+name+"]禁止输入^~!！@#%&()<>“':;\.\[\]\{\}\+\*\-\\\|\^\$\?？及空格符号"});
			//alert("["+name+"]禁止输入^~!！@#%&()<>“':;\.\[\]\{\}\+\*\-\\\|\^\$\?及空格符号");
			return false;
		}
	} else {
		return false;
	}
	
	return true;
}
/*==================================================================*\
	功能：text添加、修改页面使用 判断输入的字符串内容是否合法,不可以包含^~!！@#%&()<>"':;\.\[\]\{\}\+\*\-\\\|\^\$\?符号
	输入：
		value：输入框值
		minLen：最小长度  minLen小于0不能为空 等于0可以为空
		maxLen：最大长度
		name：输入框名称
	返回：boolean
	版本: 石磊  2010.03.02  v1.0
\*==================================================================*/
function isLegalTextContainspaces(object, minLen, maxLen, name){
	//最小长度等于0即不能为空
	if (minLen<0) {
	   if(isNotEmptyText(object.value,name)==false)
	   return false;
	}
	//长度是否合法 true 合法; false 不合法
	var flag = isStrThanLength(object, minLen, maxLen, name);
	if (flag) {
		if (/^[^~!！@#%&()<>"':;\.\[\]\{\}\+\*\-\\\|\^\$\?\？]*$/.test(object.value)) {
			return true;
		} else {
			ymPrompt.errorInfo({title:"提示",message:"["+name+"]禁止输入^~!！@#%&()<>“':;\.\[\]\{\}\+\*\-\\\|\^\$\?？符号"});
			//alert("["+name+"]禁止输入^~!！@#%&()<>“':;\.\[\]\{\}\+\*\-\\\|\^\$\?及空格符号");
			return false;
		}
	} else {
		return false;
	}
	
	return true;
}
/*==================================================================*\
	功能：text添加、修改页面使用 判断输入的字符串内容是否合法,只能由字母、数字及下划线组成
	输入：
		value：输入框值
		minLen：最小长度  minLen小于0不能为空 等于0可以为空
		maxLen：最大长度
		name：输入框名称
	返回：boolean
	版本: 李帅  2009.12.28  v1.0
\*==================================================================*/
function isLegalTextCode(object, minLen, maxLen, name){
	//最小长度等于0即不能为空
	if (minLen<0) {
	   if(isNotEmptyText(object.value,name)==false)
	   return false;
	}
	//长度是否合法 true 合法; false 不合法
	var flag = isStrThanLength(object, minLen, maxLen, name);
	if (flag) {
		if (/^\w+$/.test(object.value)) {
			return true;
		} else {
			ymPrompt.errorInfo({title:"提示",message:"["+name+"]只能输入字母、<br>数字及下划线"});
			//alert("["+name+"]只能输入字母、数字及下划线");
			return false;
		}
	} else {
		return false;
	}
	
	return true;
}
/*==================================================================*\
	功能：textarea添加、修改页面使用 判断输入的字符串内容是否合法,不可以包含^~@#&\\\|\^符号
	输入：
		value：输入框值
		minLen：最小长度  minLen小于0时不能为空
		maxLen：最大长度
		name：输入框名称
	返回：boolean
	版本: 石磊  2009.12.09  v1.0
\*==================================================================*/
function isLegalTextArea(object, minLen, maxLen, name){
	//最小长度等于0即不能为空
	if (minLen<0) {
		if(isNotEmptyText(object.value,name)==false)
		return false;
	}
	//长度是否合法 true 合法; false 不合法
	var flag = isStrThanLength(object, minLen, maxLen, name);
	if (flag) {
		if (/^[^~@#&\\\|\^]*$/.test(object.value)) {
			return true;
		} else {
			ymPrompt.errorInfo({title:"提示",message:"["+name+"]禁止输入^~@#&\\\|\^符号"});
			//alert("["+name+"]禁止输入^~@#&\\\|\^符号"); 
			return false;
		}
	} else {
		return false;
	}
	
	return true;
}

/*==================================================================*\
	功能：添加 修改页面中单元格校验，判断是否为空
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
	版本: 石磊  2009.12.09  v1.0
\*==================================================================*/
function isNotEmptyText(value,name) {
	//正则表达式匹配空格 tab符 回车等
	var reg = /\s+/g ;
	value = value.replace(reg,"");
    if (value == ""){
    	ymPrompt.errorInfo({title:"提示",message:"["+name+"]不能为空！"});
    	//alert("["+name+"]不能为空！");
    	return false;
    }
    return true;
}

/*==================================================================*\
	功能：DhtmlGrid中单元格校验，判断是否为空
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
	版本: 李帅  2009.10.21  v1.0
\*==================================================================*/
function isEmpty(value, rowNum, colNum) {
    if (value == ""){
    	return false;
    }
    return true;
}

/*
==================================================================   
	2009.9.27 modified
	功能：长度校验
	输入：valueLen：正整数;maxNum: 最大位数;minNum:最小位数;
	说明：如果minNum为-1则不要求非空校验，只限制最大长度；如果minNm为0则要求非空校验，且限制最大长度，但不限制最小长度；如果minNum大于0，则限制最大长度，且限制最小长度
	author: 李帅
==================================================================   
*/
function f_check_length(valueLen, maxNum, minNum, name) {
	if (minNum == -1) {
		if (valueLen > maxNum) {
			ymPrompt.errorInfo(name+":长度不能超过" + maxNum, null, null, "提示", null);
			return false;
		}
	} else {
		if (minNum == 0) {
			if (valueLen == 0) {
				ymPrompt.errorInfo(name+":不能为空", null, null, "提示", null);
				return false;
			}
			if (valueLen > maxNum) {
				ymPrompt.errorInfo(name+":长度不能超过" + maxNum, null, null, "提示", null);
				return false;
			}
		} else {
			if (valueLen < minNum) {
				ymPrompt.errorInfo(name+":长度不能少于" + minNum, null, null, "提示", null);
				return false;
			}
			if (valueLen > maxNum) {
				ymPrompt.errorInfo(name+":长度不能超过" + maxNum, null, null, "提示", null);
				return false;
			}
		}
	}
}



/*
==================================================================   
	2009.9.27 modified
	功能: 判断非空及大小不超过指定要求，判断输入值为数字，且整数部分与小数部分均不超过指定位数
	输入：obj：对象; maxNum:最大位数,为double类型; minNum:最小位数,int类型
	author: 李帅
==================================================================   
*/
function f_check_input_num(obj, maxNum, minNum, name) {
		//首先判断是否为数字
			var re = /^-?[1-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	     	if (!re.test(obj.value))
	    	{
				if(isNaN(obj.value)){
				ymPrompt.errorInfo(name+":请输入数字" , null, null, "提示", null);
		        return false;
		        }
	        }
	   	//如果是数字     	
	   		var inte=parseInt(maxNum);
	   		function includeNot(obj)
			    {
			      var re = /\./;
			      return(new RegExp(re).test(obj));
			    }
			if(inte==maxNum){//maxNum为正整数情况
					if(f_check_num(obj, maxNum, minNum, name)==false)
						return false;
					if(includeNot(obj.value)==true){
						ymPrompt.errorInfo(name+":请输入整数" , null, null, "提示", null);
						return false;
					}
					if(obj.value.length>maxNum){
  						ymPrompt.errorInfo(name+":整数位数不能超过" + maxNum, null, null, "提示", null);
        				return false;
					}
					//maxNum为double类型情况
				}else{
					if(f_check_num(obj, maxNum, minNum, name)==false)
						return false;
		   			}
			return true;
}

/*
==================================================================   
	2009.9.27 modified
	功能：判断obj的整数部分和小数部分(若有小数部分)是否符合要求
	输入：obj：对象,maxNum:double类型,minNum:int类型
	author: 李帅
==================================================================   
*/
function f_check_num(obj, maxNum, minNum, name) {
  	var maxNumNot = parseInt(maxNum.toString().indexOf("."));
  	var maxNumLeft = parseInt((maxNum.toString()).substring(0,maxNumNot));
  	var maxNumRight = parseInt((maxNum.toString()).substring(maxNumNot+1));
  	if (minNum == 0) {
  		if (obj.value.length == 0) {
			ymPrompt.errorInfo(name+":不能为空", null, null, "提示", null);
			return false;
		}
  	}
  	if (minNum > 0) {
  		if (obj.value.length < minNum) {
			ymPrompt.errorInfo(name+":长度不能少于" + minNum, null, null, "提示", null);
			return false;
		}
  	}
  	if(parseInt(obj.value)==obj.value){
  		var objLeft = (obj.value.toString()).substring(0,objNot).length;
  		if(maxNumLeft<objLeft){
	  		ymPrompt.errorInfo(name+":整数位数不能超过" + maxNumLeft, null, null, "提示", null);
	        return false;
	  	}
  	}else{
	  	var objNot = parseInt(obj.value.toString().indexOf("."));
	  	var objLeft = (obj.value.toString()).substring(0,objNot).length;
	  	var objRight = (obj.value.toString()).substring(objNot+1).length;
	  	if(maxNumLeft<objLeft){
	  		ymPrompt.errorInfo(name+":整数位数不能超过" + maxNumLeft, null, null, "提示", null);
	        return false;
	  	}
	  	if(maxNumRight<objRight){
	  		ymPrompt.errorInfo(name+":小数部不能超过" + maxNumRight, null, null, "提示", null);
			return false;
	  	}
	}
}
/*
==================================================================   
	2009.9.27 modified
	功能：判断true或false
	输入：obj：对象
	author: 李帅
==================================================================   
*/
function f_check_true_false(obj,name) {
  if(obj.value == true){
  	return true;
  }else if(obj.value == false){
    return true;
  }else{
  	ymPrompt.errorInfo(name+":请输入true或false" + fltint, null, null, "提示", null);
  	return false;
  }
}

/*
==================================================================   
	2009.12.29 modified
	功能：电话号码验证
	输入：value：被校验值 ；flag 小于0不能为空；name 校验输入框名称
	author: 石磊
	\d 代表一个数字
	{7,8} 代表7－8位数字（表示电话号码）
	{3,} 代表分机号码
	d{2,3} 代表区号
	\+]\d{2,3} 代表国际区号
==================================================================   
*/
function checkPhone(value,flag,name) {
	var reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{2,4}))?$/;
	if (value=="" && flag < 0 ) {
		ymPrompt.errorInfo({title:"提示",message:"["+name+"]不能为空！"});
		//alert("["+name+"]不能为空！");
		return false;
	} else if (value!="" && flag>=0) {
		if (reg.test(value)){
	    	return true;
	    } else {
	    	ymPrompt.errorInfo({title:"提示",message:"["+name+"]填写有误！"});
	    	//alert("["+name+"]填写有误！");
	    	return false;
	    }
	} else {
		return true;
	}
	return true;
}
/*
==================================================================   
	2009.12.29 modified
	功能：手机号码验证 包括13*、15(0|3|6|7|8|9]|18[8|9)号段的校验
	输入：value：被校验值 ；flag 小于0不能为空；name 校验输入框名称
	author: 石磊
==================================================================   
*/ 
function checkMobile (value,flag,name) {
	var reg = /^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$/;
	if (value=="" && flag < 0 ) {
		ymPrompt.errorInfo({title:"提示",message:"["+name+"]不能为空！"});
		//alert("["+name+"]不能为空！");
		return false;
	} else if (value!="" && flag>=0) {
		if (reg.test(value)){
	    	return true;
	    } else {
	    	ymPrompt.errorInfo({title:"提示",message:"["+name+"]填写有误！"});
	    	//alert("["+name+"]填写有误！");
	    	return false;
	    }
	} else {
		return true;
	}
	return true;
}
/*
==================================================================   
	2009.12.29 modified
	功能：联系电话号码验证 可能是座机或手机号 包括13*、15(0|3|6|7|8|9]|18[8|9)号段的校验
	输入：value：被校验值 ；flag 小于0不能为空；name 校验输入框名称
	author: 石磊
==================================================================   
*/ 
function checkTel(value,flag,name) {
	var regPhone = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{2,4}))?$/;
	var regMobile = /^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$/;
	var result = false;
	if (value=="" && flag < 0 ) {
		ymPrompt.errorInfo({title:"提示",message:"["+name+"]不能为空！"});
		//alert("["+name+"]不能为空！");
		return result;
	} else if (value!="" && flag>=0) {
		//只要符合座机、手机其中一个则填写有效
		result = regPhone.test(value) || regMobile.test(value);
		if (result){
	    	return true;
	    } else {
	    	ymPrompt.errorInfo({title:"提示",message:"["+name+"]填写有误！"});
	    	//alert("["+name+"]填写有误！");
	    	return result;
	    }
	} else {
		return true;
	}
	return true;
}
/*
==================================================================   
	2009.12.29 modified
	功能：电子邮件校验
	输入：value：被校验值 ；flag 小于0不能为空；name 校验输入框名称
	author: 石磊
==================================================================   
*/ 
function checkMail(value,flag,name) {
	var reg = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	var result = false;
	if (value=="" && flag < 0 ) {
		ymPrompt.errorInfo({title:"提示",message:"["+name+"]不能为空！"});
		//alert("["+name+"]不能为空！");
		return result;
	} else if (value!="" && flag>=0) {
		result = reg.test(value);
		if (result){
	    	return true;
	    } else {
	    	ymPrompt.errorInfo({title:"提示",message:"["+name+"]填写有误！"});
	    	//alert("["+name+"]填写有误！");
	    	return result;
	    }
	} else {
		return true;
	}
	return true;
}
/*
==================================================================   
	2009.12.29 modified
	功能：邮编校验 六位 第一个数字不能为零
	输入：value：被校验值 ；flag 小于0不能为空；name 校验输入框名称
	author: 石磊
==================================================================   
*/ 
function checkPostalcode(value,flag,name) {
	var reg = /^[1-9]\d{5}$/;
	var result = false;
	if (value=="" && flag < 0 ) {
		ymPrompt.errorInfo({title:"提示",message:"["+name+"]不能为空！"});
		//alert("["+name+"]不能为空！");
		return result;
	} else if (value!="" && flag>=0) {
		result = reg.test(value);
		if (result){
	    	return true;
	    } else {
	    	ymPrompt.errorInfo({title:"提示",message:"["+name+"]填写有误！"});
	    	//alert("["+name+"]填写有误！");
	    	return result;
	    }
	} else {
		return true;
	}
	return true;
}
/*
==================================================================   
	2009.12.29 modified
	功能：身份证校验 15位可以校验生日 18位可以校验地区 生日 性别
	输入：value：被校验值 ；flag 小于0不能为空；name 校验输入框名称
	author: 石磊
==================================================================   
*/ 
var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "}
function identityCard(value,flag,name){
	var len = 15;
	if (value!="") {
		len = value.length;
		//if 15位 else 18位
		if (len == 15){ 
			var reg15 = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/); 
			var arrSplit = value.match(reg15); 
			//检查生日日期是否正确 
			var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]); 
			var bGoodDay; 
			bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
			if (!bGoodDay){ 
				ymPrompt.errorInfo({title:"提示",message:"["+name+"]非法生日"});
		        //alert("["+name+"]非法生日");
		        return false; 
			}
			return true;
		} else if (len==18) {
			var iSum=0;
			var info="";
			if(/^d{17}(d|x)$/i.test(value)) {
				ymPrompt.errorInfo({title:"提示",message:"["+name+"]输入错误"});
				//alert("["+name+"]输入错误");	
				return false;
			}
			value=value.replace(/x$/i,"a");
			if(city[parseInt(value.substr(0,2))]==null){
				ymPrompt.errorInfo({title:"提示",message:"["+name+"]非法地区"});
				//alert("["+name+"]非法地区");
				return false;
			}
			sBirthday=value.substr(6,4)+"-"+Number(value.substr(10,2))+"-"+Number(value.substr(12,2));
			var d=new Date(sBirthday.replace(/-/g,"/"));
			if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
				ymPrompt.errorInfo({title:"提示",message:"["+name+"]非法生日"});
				//alert("["+name+"]非法生日");
				return false;
			}
			for(var i = 17; i >= 0; i -- ) {
				iSum += (Math.pow(2,i) % 11) * parseInt(value.charAt(17 - i),11)
			}
			if(iSum%11!=1){
				ymPrompt.errorInfo({title:"提示",message:"["+name+"]非法证号"});
				//alert("["+name+"]非法证号");	
				return false;
			}
		} else {
			ymPrompt.errorInfo({title:"提示",message:"["+name+"]非法证号"});
			//alert("["+name+"]非法证号");	
		}
	}
	return true;
}
/*
==================================================================   
	2009.12.29 modified
	功能：IP校验
	输入：value：被校验值 ；flag 小于0不能为空；name 校验输入框名称
	author: 石磊
==================================================================   
*/ 
function checkIp(value,flag,name) {
	var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	var result = false;
	if (value=="" && flag < 0 ) {
		return result;
	} else if (value!="" && flag>=0) {
		result = reg.test(value);
		if (result){
	    	return true;
	    } else {
	    	return result;
	    }
	} else {
		return true;
	}
	return true;
}
/*
==================================================================   
	2009.12.29 modified
	功能：IP校验 dhtmlxgrid调用
	输入：value：被校验值 ；flag 小于0不能为空；name 校验输入框名称
	author: 石磊
==================================================================   
*/ 
function checkGridIp(value, rowNum, colNum, minLen, maxLen) {
	var flag = checkIp(value,minLen,'');
   	if (!flag) {
   		ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列填写错误！"});
   		//alert("第" + rowNum + "行,第" + colNum + "列填写错误！");
   	}
	return flag;
}


/*==================================================================*\
	功能：DhtmlGrid中单元格校验，判断输入的字符串内容是否合法,只能由字母、数字及下划线组成
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
		minLen：最小长度
		maxLen：最大长度
	版本: 狄巨礼  2010.04.21  v1.0
\*==================================================================*/
function isLegalGridValueCode(value, rowNum, colNum,minLen,maxLen) {
	//最小长度等于0即不能为空
	if (minLen<1) {
	   if(isNotEmptyText(value,"第" + rowNum + "行,第" + colNum + "列")==false){
	   		return false;
	   	}else{
			minLen = 1;
		}
	}
	var reg = new  RegExp("^[a-zA-Z][a-zA-Z0-9_]{"+(minLen-1)+","+(maxLen-1)+"}$");
    if (reg.exec(value)) {
		return true;
	} else {
		ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列只能输入由字符开头<br>"+minLen+"-"+maxLen+"位由字母、<br>数字及下划线组成的字符！"});
		//alert("["+name+"]只能输入字母、数字及下划线");
		return false;
	}
}


/*==================================================================*\
	功能：DhtmlGrid中单元格校验，判断输入的URL是否合法
	输入：
		value：单元格的值
		rowNum：DhtmlGrid表格的行号
		colNum：DhtmlGrid表格的列号
		minLen：最小长度
		maxLen：最大长度
	返回：boolean
	版本: 狄巨礼  2010.05.13  v1.0
\*==================================================================*/
function isLegalGridURL(value, rowNum, colNum,minLen,maxLen){
	//长度是否合法 true 合法; false 不合法
	var flag = isNotOverlength(value, rowNum, colNum,minLen,maxLen);
	var reg=/^(http|https):\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^\"\"])*$/; 
	var reg1=/^(http|https):\/\/(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\:\d{4}\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^\"\"])*$/;
	var reg2=/^[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&~`@[\]\':+!]*([^\"\"])*$/; 
	if (flag) {
		if (reg.test(value) || reg1.test(value) || reg2.test(value)||value=='#') {
			//ymPrompt.succeedInfo({title:"提示",message:"["+name+"]保存成功！"});
			return true;
		} else {
			ymPrompt.errorInfo({title:"提示",message:"第" + rowNum + "行,第" + colNum + "列输入有误！"});
			object.focus();
			return false;
		}
	} else {
		return false;
	}
	
	return true;
}

