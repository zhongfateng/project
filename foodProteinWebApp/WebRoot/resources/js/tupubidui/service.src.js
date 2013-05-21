var serverUrl = 'fmpttp.action?m=frontCharData';
var updateFileUrl = 'fmpttp.action?m=frontUpdateFile';
var itemList = null;

var getJsonCallBack = function(data) {
	var dataseries = new Array();
	itemList = data;
	for ( var i = 0; i < data.length; i++) {
		var item = new Object();
		var dataItem=data[i];
		if(dataItem==null)
		continue;
		item.name =dataItem.name;
		var url = dataItem.jsonUrl;
		$.ajax({
			type : "GET",
			async : false,
			url : url,
			dataType : "json",
			success : function(data) {
				item.data = data;
			}
		});
		dataseries[i] = item;
	}
	;
	chart = new Highcharts.StockChart( {
	chart : {
		renderTo : 'container'
	},
	credits : {
		enabled : true,
		href : 'http://www.baidu.com',
		position : {
			align : 'right',
			x : -50,
			verticalAlign : 'bottom',
			y : 0
		},
		text : ''
	},
	rangeSelector : {
		selected : 12
	},

	title : {
		text : ''
	},
	subtitle : {
		text : ''
	},
	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'top',
		x : -10,
		y : 100,
		borderWidth : 0
	},
	series : dataseries,
	rangeSelector : {
		enabled : false
	},
});

}

var getJsonData = function() {
	
	var parmps = getParmps();
	$.ajax({
		type : "POST",
		data : parmps,
		// url : 'http://www.baidu.com/CharDataServlet',
		url : serverUrl,
		dataType : "json",
		success : function(data) {
			getJsonCallBack(data);
		}
	});
}

var getParmps = function() {
	var parmps = new Object();
	parmps.key = $("#searckKey").val();
	var data = itemList;
	var itemsStr = '[';
	var writeFlag=false;
	for ( var i = 0; data!=null&&i < data.length; i++) {
		var item = data[i];
		// {"jsonUrl":"data/1uSEkaYD7sXTlSYTUzfP.json","name":"beeagle"}
		if(item!=null){
			if(writeFlag)itemsStr=itemsStr+",";
			var str = "{'jsonUrl':'" + item.jsonUrl + "','name':'" + item.name
				+ "'}";
			itemsStr = itemsStr + str;
			writeFlag=true;
			}
	
	}
	itemsStr = itemsStr + ']';
	parmps.items = itemsStr;
	return parmps;
};
$(function() {
	$("#searchButton").click(function() {
		getJsonData();
	});
	$("#searckKey").click(function() {
		$("#searckKey").attr("value", null);
	});
	$("#a_parser_all").click(function() {
		drawAllFunction();
	});
	$("#a_parser_each").click(function() {
		drawSingleFunction();
	});
	$('#file_upload').uploadify({
		'buttonText' : '上传文件',
		'swf' : './resources/component/uploadify/uploadify.swf',
		'uploader' : 'fmpttp.action?m=frontUpdateFile',
		'onUploadSuccess' : function(file, data, response) {
			var array = eval("(" + data + ")");
			if (itemList == null)
				itemList = new Array();
			itemList = itemList.concat(array);
			getJsonData();
		}

	});
});