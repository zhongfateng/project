/**
==================================================================   
	2009.12.29 modified
	功能：普通日期输入
	输入：cell：mygrid cell
	author: 石磊
	使用方法: 
	mygrid = new dhtmlXGridObject('gridbox');
	..............
	..............
	..............
	mygrid.setColTypes("ro,ra,ed,myCalender,ed,ed");
	..............
	..............
	将列类型设置成myCalender（eXcell_myCalender取下划线之后部分，不包括下划线）
==================================================================   
*/ 
function eXcell_myCalender(cell){                           
	if (cell){                                                   
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}
    this.setValue=function(val){
    	if (!val || val.toString()._dhx_trim() == "") {
			this.setCValue("&nbsp;");
			return this.cell._clearCell = true;
		} else {
			this.setCValue(val);
		}                                     
	}
    this.getValue=function(){
    	return this.cell.innerHTML;
    }
    this.edit=function(){
     	if ("&nbsp;"==this.getValue()) {
     		this.val = "";
     	} else {
			this.val = this.getValue();        		
     	}
        this.cell.innerHTML="<input type=\"text\" id='ddd' style='border:0; width: 100%;' onfocus=\"WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'});\" >";
        this.cell.firstChild.onclick=function(e){ (e||event).cancelBubble=true; }
        this.cell.firstChild.value=this.val;
        this.cell.firstChild.focus();    
        this.cell.firstChild.select();
     }
     this.detach=function(){
         this.setValue(this.cell.firstChild.value);
         return this.val!=this.getValue();
     }
}

function eXcell_myCalenderYear(cell){                           
	if (cell){                                                   
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}
    this.setValue=function(val){
    	if (!val || val.toString()._dhx_trim() == "") {
			this.setCValue("&nbsp;");
			return this.cell._clearCell = true;
		} else {
			this.setCValue(val);
		}                                     
	}
    this.getValue=function(){
    	return this.cell.innerHTML;
    }
    this.edit=function(){
     	if ("&nbsp;"==this.getValue()) {
     		this.val = "";
     	} else {
			this.val = this.getValue();        		
     	}
        this.cell.innerHTML="<input type=\"text\" id='ddd' style='border:0; width: 100%;' onfocus=\"WdatePicker({skin:'whyGreen',isShowClear:false,readOnly:true,dateFmt:'yyyy'});\" >";
        this.cell.firstChild.onclick=function(e){ (e||event).cancelBubble=true; }
        this.cell.firstChild.value=this.val;
        this.cell.firstChild.focus();    
        this.cell.firstChild.select();
     }
     this.detach=function(){
         this.setValue(this.cell.firstChild.value);
         return this.val!=this.getValue();
     }
}
function eXcell_myCalenderMonth(cell){                           
	if (cell){                                                   
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}
    this.setValue=function(val){
    	if (!val || val.toString()._dhx_trim() == "") {
			this.setCValue("&nbsp;");
			return this.cell._clearCell = true;
		} else {
			this.setCValue(val);
		}                                     
	}
    this.getValue=function(){
    	return this.cell.innerHTML;
    }
    this.edit=function(){
     	if ("&nbsp;"==this.getValue()) {
     		this.val = "";
     	} else {
			this.val = this.getValue();        		//WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'});
     	}
        this.cell.innerHTML="<input type=\"text\" id='ddd' style='border:0; width: 100%;' onfocus=\"WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM'});\" >";
        this.cell.firstChild.onclick=function(e){ (e||event).cancelBubble=true; }
        this.cell.firstChild.value=this.val;
        this.cell.firstChild.focus();    
        this.cell.firstChild.select();
     }
     this.detach=function(){
         this.setValue(this.cell.firstChild.value);
         return this.val!=this.getValue();
     }
}
function eXcell_myCalenderMonth2(cell){                           
	if (cell){                                                   
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}
    this.setValue=function(val){
    	if (!val || val.toString()._dhx_trim() == "") {
			this.setCValue("&nbsp;");
			return this.cell._clearCell = true;
		} else {
			this.setCValue(val);
		}                                     
	}
    this.getValue=function(){
    	return this.cell.innerHTML;
    }
    this.edit=function(){
     	if ("&nbsp;"==this.getValue()) {
     		this.val = "";
     	} else {
			this.val = this.getValue();        		//WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'});
     	}
        this.cell.innerHTML="<input type=\"text\" id='ddd' style='border:0; width: 100%;' onfocus=\"WdatePicker({skin:'whyGreen',maxDate:'"+getDate()+"',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM'});\" >";
        this.cell.firstChild.onclick=function(e){ (e||event).cancelBubble=true; }
        this.cell.firstChild.value=this.val;
        this.cell.firstChild.focus();    
        this.cell.firstChild.select();
     }
     this.detach=function(){
         this.setValue(this.cell.firstChild.value);
         return this.val!=this.getValue();
     }
}
//得到当前年月日
function getDate(){
var date = new Date();
var month = parseInt(date.getMonth())+1.0;
var d =date.getYear() +"-"+ doHandleMonth(month)+"-"+ doHandleMonth(date.getDate());
//var d =date.getYear() +"-"+ month +"-"+ date.getDate();
return d;
}
function eXcell_myCalenderDay(cell){                           
	if (cell){                                                   
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}
    this.setValue=function(val){
    	if (!val || val.toString()._dhx_trim() == "") {
			this.setCValue("&nbsp;");
			return this.cell._clearCell = true;
		} else {
			this.setCValue(val);
		}                                     
	}
    this.getValue=function(){
    	return this.cell.innerHTML;
    }
    this.edit=function(){
     	if ("&nbsp;"==this.getValue()) {
     		this.val = "";
     	} else {
			this.val = this.getValue();        		//WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'});
     	}
        this.cell.innerHTML="<input type=\"text\" id='ddd' style='border:0; width: 100%;' onfocus=\"WdatePicker({skin:'whyGreen',isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'});\" >";
        this.cell.firstChild.onclick=function(e){ (e||event).cancelBubble=true; }
        this.cell.firstChild.value=this.val;
        this.cell.firstChild.focus();    
        this.cell.firstChild.select();
     }
     this.detach=function(){
         this.setValue(this.cell.firstChild.value);
         return this.val!=this.getValue();
     }
}
function eXcell_myCalenderDay2(cell){                           
	if (cell){                                                   
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}
    this.setValue=function(val){
    	if (!val || val.toString()._dhx_trim() == "") {
			this.setCValue("&nbsp;");
			return this.cell._clearCell = true;
		} else {
			this.setCValue(val);
		}                                     
	}
    this.getValue=function(){
    	return this.cell.innerHTML;
    }
    this.edit=function(){
     	if ("&nbsp;"==this.getValue()) {
     		this.val = "";
     	} else {
			this.val = this.getValue();        		//WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'});
     	}
        this.cell.innerHTML="<input type=\"text\" id='ddd' style='border:0; width: 100%;' onfocus=\"WdatePicker({skin:'whyGreen',maxDate:'"+getDate()+"',isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'});\" >";
        this.cell.firstChild.onclick=function(e){ (e||event).cancelBubble=true; }
        this.cell.firstChild.value=this.val;
        this.cell.firstChild.focus();    
        this.cell.firstChild.select();
     }
     this.detach=function(){
         this.setValue(this.cell.firstChild.value);
         return this.val!=this.getValue();
     }
}
eXcell_myCalender.prototype = new eXcell; 
eXcell_myCalenderYear.prototype = new eXcell; 
eXcell_myCalenderMonth.prototype = new eXcell;
eXcell_myCalenderMonth2.prototype = new eXcell;
eXcell_myCalenderDay.prototype = new eXcell; 
eXcell_myCalenderDay2.prototype = new eXcell; 

/**
==================================================================   
	2009.12.29 modified
	功能：日期输入 和myMaxCalender匹配使用
	输入：cell：mygrid cell
	author: 石磊
	使用方法: 
	mygrid = new dhtmlXGridObject('gridbox');
	..............
	..............
	..............
	mygrid.setColTypes("ro,ra,ed,myMinCalender,ed,ed");
	..............
	..............
	将列类型设置成myMinCalender（eXcell_myMinCalender取下划线之后部分，不包括下划线）
==================================================================   
*/ 
function eXcell_myMinCalender(cell){                                 
	this.cell = cell;
	this.grid = this.cell.parentNode.grid;
    this.setValue=function(val){
    	if (!val || val.toString()._dhx_trim() == "") {
			this.setCValue("&nbsp;");
			return this.cell._clearCell = true;
		} else {
			this.setCValue(val);
		}                                     
	}
    this.getValue=function(){
    	return this.cell.innerHTML;
    }
    this.edit=function(){
    	var maxValue;
    	var r = this.grid.getColumnsNum();
    	for (var i = 0 ; i < r ; i ++ ) {
    		if(this.grid.getColType(i)=="myMaxCalender") {
    			maxValue=this.grid.cells(this.grid.getSelectedId(),i).getValue();
    		}
    	}
    	var maxDate ;
    	if (maxValue!=""　&&　maxValue!="&nbsp;") {
    		maxDate = ",maxDate:'"+maxValue+"'";
    	} else {
    		maxDate = "";
    	}
     	if ("&nbsp;"==this.getValue()) {
     		this.val = "";
     	} else {
			this.val = this.getValue();        		
     	}
        this.cell.innerHTML="<input type=\"text\" style='border:0; width: 100%;' onFocus=\"WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'"+maxDate+"})\"/>";
        this.cell.firstChild.onclick=function(e){ (e||event).cancelBubble=true; }
        this.cell.firstChild.value=this.val;
        this.cell.firstChild.focus();    
        this.cell.firstChild.select();
     }
     this.detach=function(){
         this.setValue(this.cell.firstChild.value);
         return this.val!=this.getValue();
     }
}
eXcell_myMinCalender.prototype = new eXcell; 

/**
==================================================================   
	2009.12.29 modified
	功能：日期输入 和myMinCalender匹配使用
	输入：cell：mygrid cell
	author: 石磊
	使用方法: 
	mygrid = new dhtmlXGridObject('gridbox');
	..............
	..............
	..............
	mygrid.setColTypes("ro,ra,ed,myMaxCalender,ed,ed");
	..............
	..............
	将列类型设置成myMaxCalender（eXcell_myMaxCalender取下划线之后部分，不包括下划线）
==================================================================   
*/ 
function eXcell_myMaxCalender(cell){     
	this.cell = cell;
	this.grid = this.cell.parentNode.grid;
    this.setValue=function(val){
    	if (!val || val.toString()._dhx_trim() == "") {
			this.setCValue("&nbsp;");
			return this.cell._clearCell = true;
		} else {
			this.setCValue(val);
		}                                     
	}
    this.getValue=function(){
    	return this.cell.innerHTML;
    }
    this.edit=function(){
    	var minValue;
    	var r = this.grid.getColumnsNum();
    	for (var i = 0 ; i < r ; i ++ ) {
    		if(this.grid.getColType(i)=="myMinCalender") {
    			minValue=this.grid.cells(this.grid.getSelectedId(),i).getValue();
    		}
    	}
    	var minDate ;
    	if (minValue!="" &&　minValue!="&nbsp;") {
    		minDate = ",minDate:'"+minValue+"'";
    	} else {
    		minDate = "";
    	}
     	if ("&nbsp;"==this.getValue()) {
     		this.val = "";
     	} else {
			this.val = this.getValue();        		
     	}
        this.cell.innerHTML="<input type=\"text\" style='border:0; width: 100%;' onfocus=\"WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'"+minDate+"});\" >";
        this.cell.firstChild.onclick=function(e){ (e||event).cancelBubble=true; }
        this.cell.firstChild.value=this.val;
        this.cell.firstChild.focus();    
        this.cell.firstChild.select();
     }
     this.detach=function(){
         this.setValue(this.cell.firstChild.value);
         return this.val!=this.getValue();
         alert('detach');
     }
}
eXcell_myMaxCalender.prototype = new eXcell; 
/**
==================================================================   
	2009.12.29 modified
	功能：时间输入 小时：分钟
	输入：cell：mygrid cell
	author: 石磊
	使用方法: 
	mygrid = new dhtmlXGridObject('gridbox');
	..............
	..............
	..............
	mygrid.setColTypes("ro,ra,ed,myCalender,ed,ed");
	..............
	..............
	将列类型设置成myCalender（eXcell_myCalender取下划线之后部分，不包括下划线）
==================================================================   
*/ 
function eXcell_myTime(cell){                                 
	if (cell){                                                   
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}
    this.setValue=function(val){
    	if (!val || val.toString()._dhx_trim() == "") {
			this.setCValue("&nbsp;");
			return this.cell._clearCell = true;
		} else {
			this.setCValue(val);
		}                                     
	}
    this.getValue=function(){
    	return this.cell.innerHTML;
    }
    this.edit=function(){
     	if ("&nbsp;"==this.getValue()) {
     		this.val = "";
     	} else {
			this.val = this.getValue();        		
     	}
        this.cell.innerHTML="<input type=\"text\" id='ddd' style='border:0; width: 100%;' onfocus=\"WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true,dateFmt:'H:mm'});\" >";
        this.cell.firstChild.onclick=function(e){ (e||event).cancelBubble=true; }
        this.cell.firstChild.value=this.val;
        this.cell.firstChild.focus();    
        this.cell.firstChild.select();
     }
     this.detach=function(){
         this.setValue(this.cell.firstChild.value);
         return this.val!=this.getValue();
     }
}
eXcell_myTime.prototype = new eXcell;
/**
==================================================================   
	2009.12.29 modified
	功能：日期输入 和myMaxTime匹配使用
	输入：cell：mygrid cell
	author: 石磊
	使用方法: 
	mygrid = new dhtmlXGridObject('gridbox');
	..............
	..............
	..............
	mygrid.setColTypes("ro,ra,ed,myMinTime,ed,ed");
	..............
	..............
	将列类型设置成myMinTime（eXcell_myMinTime取下划线之后部分，不包括下划线）
==================================================================   
*/ 
function eXcell_myMinTime(cell){                                 
	this.cell = cell;
	this.grid = this.cell.parentNode.grid;
    this.setValue=function(val){
    	if (!val || val.toString()._dhx_trim() == "") {
			this.setCValue("&nbsp;");
			return this.cell._clearCell = true;
		} else {
			this.setCValue(val);
		}                                     
	}
    this.getValue=function(){
    	return this.cell.innerHTML;
    }
    this.edit=function(){
    	var maxValue;
    	var r = this.grid.getColumnsNum();
    	for (var i = 0 ; i < r ; i ++ ) {
    		if(this.grid.getColType(i)=="myMaxTime") {
    			maxValue=this.grid.cells(this.grid.getSelectedId(),i).getValue();
    		}
    	}
    	var maxDate ;
    	if (maxValue!=""　&&　maxValue!="&nbsp;") {
    		maxDate = ",maxDate:'"+maxValue+"'";
    	} else {
    		maxDate = "";
    	}
     	if ("&nbsp;"==this.getValue()) {
     		this.val = "";
     	} else {
			this.val = this.getValue();        		
     	}
        this.cell.innerHTML="<input type=\"text\" style='border:0; width: 100%;' onFocus=\"WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true,dateFmt:'H:mm'"+maxDate+"})\"/>";
        this.cell.firstChild.onclick=function(e){ (e||event).cancelBubble=true; }
        this.cell.firstChild.value=this.val;
        this.cell.firstChild.focus();    
        this.cell.firstChild.select();
     }
     this.detach=function(){
         this.setValue(this.cell.firstChild.value);
         return this.val!=this.getValue();
     }
}
eXcell_myMinTime.prototype = new eXcell; 

/**
==================================================================   
	2009.12.29 modified
	功能：日期输入 和myMinTime匹配使用
	输入：cell：mygrid cell
	author: 石磊
	使用方法: 
	mygrid = new dhtmlXGridObject('gridbox');
	..............
	..............
	..............
	mygrid.setColTypes("ro,ra,ed,myMaxTime,ed,ed");
	..............
	..............
	将列类型设置成myMaxTime（eXcell_myMaxTime取下划线之后部分，不包括下划线）
==================================================================   
*/ 
function eXcell_myMaxTime(cell){     
	this.cell = cell;
	this.grid = this.cell.parentNode.grid;
    this.setValue=function(val){
    	if (!val || val.toString()._dhx_trim() == "") {
			this.setCValue("&nbsp;");
			return this.cell._clearCell = true;
		} else {
			this.setCValue(val);
		}                                     
	}
    this.getValue=function(){
    	return this.cell.innerHTML;
    }
    this.edit=function(){
    	var minValue;
    	var r = this.grid.getColumnsNum();
    	for (var i = 0 ; i < r ; i ++ ) {
    		if(this.grid.getColType(i)=="myMinTime") {
    			minValue=this.grid.cells(this.grid.getSelectedId(),i).getValue();
    		}
    	}
    	var minDate ;
    	if (minValue!="" &&　minValue!="&nbsp;") {
    		minDate = ",minDate:'"+minValue+"'";
    	} else {
    		minDate = "";
    	}
     	if ("&nbsp;"==this.getValue()) {
     		this.val = "";
     	} else {
			this.val = this.getValue();        		
     	}
        this.cell.innerHTML="<input type=\"text\" style='border:0; width: 100%;' onfocus=\"WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true,dateFmt:'H:mm'"+minDate+"});\" >";
        this.cell.firstChild.onclick=function(e){ (e||event).cancelBubble=true; }
        this.cell.firstChild.value=this.val;
        this.cell.firstChild.focus();    
        this.cell.firstChild.select();
     }
     this.detach=function(){
         this.setValue(this.cell.firstChild.value);
         return this.val!=this.getValue();
         alert('detach');
     }
}
eXcell_myMaxTime.prototype = new eXcell; 