
function $id(id) {
	return document.getElementById(id);
}
function $p(id) {
	return parent.document.getElementById(id);
}
function $n(name) {
	return document.getElementsByName(name);
}
function $t(tag) {
	return document.getElementsByTagName(tag);
}
function $v(id) {
	return $(id).value;
}
function $pv(id) {
	return $p(id).value;
}

//checked All checbox by item Name
function selAll(name) {
	var all = $n(name);
	for (var i = 0; i < all.length; i++) {
		all[i].checked = true;
	}
}

//reverse All checkbox by item Name
function revSel(name) {
	var all = $n(name);
	for (var i = 0; i < all.length; i++) {
		all[i].checked = !all[i].checked;
	}
}

//reset All checkbox by item Name
function resetAll(name) {
	var all = $n(name);
	for (var i = 0; i < all.length; i++) {
		all[i].checked = false;
	}
}

//judge whether one item is selected
function isSingleSel(name) {
	var all = $n(name);
	var returnValue = true;
	var flag = 0;
	for (var i = 0; i < all.length; i++) {
		if(all[i].checked){
		     flag++;
		}
	}
	if(flag==0 || flag>1){
	   returnValue = false;
	}
	return returnValue ;
}
//shilei  iframe
function isSingleSelParent(name) {
	var all = window.frames['moduletree'].window.document.getElementsByName(name);
	var returnValue = true;
	var flag = 0;
	for (var i = 0; i < all.length; i++) {
		if(all[i].checked){
		     flag++;
		}
	}
	if(flag==0 || flag>1){
	   returnValue = false;
	}
	return returnValue;
}
//judge whether multi items are selected
function isMultiSel(name) {
	var all = $n(name);
	var returnValue = true;
	var flag = 0;
	for (var i = 0; i < all.length; i++) {
		if(all[i].checked){
		     flag++;
		}
	}
	if(flag==0 || flag==1){
	   returnValue = false;
	}
	return returnValue ;
}

 function ajaxValidate(url,data){
	  $.ajax({
			type: "post",
			url: url,
			beforeSend: function(XMLHttpRequest){
				//ShowLoading();
			},
			success: function(data, textStatus){
				if("success"==data){
				   document.all.myform.submit();
				}else{
				   ymPrompt.errorInfo(data,null,null,"错误提示",null);
				}
			},
			data :data,
			complete: function(XMLHttpRequest, textStatus){
				//HideLoading();
			},
			error: function(){
	             ymPrompt.errorInfo("系统出现异常，请稍后再尝试！",null,null,"错误提示",null);	   		
			}
	   });
   }

function clearNoNum(obj){
	obj.value = obj.value.replace(/[^\d.]/g,"");
	obj.value = obj.value.replace(/^\./g,"");
	obj.value = obj.value.replace(/\.{2,}/g,".");
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}

function clearNoNumAndSpot(obj){
	obj.value = obj.value.replace(/[^\d]/g,"");
	//obj.value = obj.value.replace(/^\./g,"");
	//obj.value = obj.value.replace(/\.{2,}/g,".");
	//obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}


