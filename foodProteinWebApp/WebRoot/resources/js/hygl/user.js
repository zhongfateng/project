function loginBzpt(frm){
	var user=frm.username;
	var pass=frm.password;
	var code=frm.code;
	if(user.value==""){
		alert("请输入用户名饿！");
		user.focus();
		return (false);
	}
	if(user.value.length>10){
		alert("用户名过长,请重新填写！");
		user.focus();
		return (false);
	}
	if(pass.value==""){
		alert("请输入密码！");
		pass.focus();
		return (false);
	}
	if(code.value==""){
		alert("请输入验证码！");
		code.focus();
		return (false);
	}
	return true;
}
function checkEmail(email){
    var emailReg = /([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)\.([0-9a-zA-Z_-]+)/;
    if(!emailReg.test(email.value)){
        return false;
    }
    return true;
}
function isPostalCode(object)
{
 var s =object.value; 
 var pattern =/^[0-9]{6}$/;
     if(s!=""){
         if(!pattern.exec(s)){
		     return false;
         }
     }
	return true;
}
function others(){
	var s=document.getElementById("s1");
	var t=document.getElementById("t1");
	if(s.value=='1045'||s.value=='1046'){
		s.style.display='none';
		t.style.display='';
	}
}
function others2(){
	var s=document.getElementById("s1");
	var t=document.getElementById("t1");
	s.value='1001';
	s.style.display='';
	t.style.display='none';
}
function funcChina(obj){
	if(/.*[\u4e00-\u9fa5]+.*$/.test(obj)){
		return false;
	}
	return true;
}
function checkUser(frm){
	var msgs=document.getElementsByTagName("div");
	var username=frm.loginCode;
	var password=frm.password;
	var qpassword=frm.password2;
	var truename=frm.name;
	var question=frm.attchar08;
	var answer=frm.attchar09;
	var dwmc=frm.attchar11;
	var entshort=frm.attchar12;
	var address=frm.attchar13;
	var postno=frm.attchar14;
	var telephone=frm.attchar15;
	var email=frm.attchar16;
	
	var hm=new Array();
	var j=0;
	for(var i=0;i<msgs.length;i++){
		if(msgs[i].id=='divHtml'){
		    hm[j]=msgs[i];
			j++;
		}		
	}
	for(var i=0;i<hm.length;i++){
		hm[i].innerHTML='';
	}
	if(username.value==''){
		hm[0].innerHTML='请输入用户名！';
		username.focus();
		return false;
	}
	if(username.value.length>10){
		hm[0].innerHTML='用户名过长,请重新填写';
		username.focus();
		return (false);
	}
    if(!funcChina(username.value)){
		hm[0].innerHTML='不能含有汉字！';
		username.focus();
		return false;
	}
	if(password.value==''){
		hm[1].innerHTML='请输入密码！';
		password.focus();
		return false;
	}
	if(qpassword.value==''){
		hm[2].innerHTML='请输入确认密码！';
		qpassword.focus();
		return false;
	}
	if(qpassword.value!=password.value){
		hm[2].innerHTML='确认密码与密码不一致！';
		qpassword.focus();
		return false;
	}
	if(truename.value==''){
		hm[3].innerHTML='请输入真实姓名！';
		truename.focus();
		return false;
	}
	if(question.value==''){
		hm[4].innerHTML='请选择密码提示问题！';
		question.focus();
		return false;
	}
	if(answer.value==''){
		hm[5].innerHTML='请输入密码提示问题！';
		answer.focus();
		return false;
	}
	if(dwmc.value==''){
		hm[6].innerHTML='请输入单位名称！';
		dwmc.focus();
		return false;
	}
	if(entshort.value==''){
		hm[7].innerHTML='请输入企业简称，用于水印！';
		entshort.focus();
		return false;
	}
	if(address.value==''){
		hm[8].innerHTML='请输入通讯地址！';
		address.focus();
		return false;
	}
	if(postno.value==''){
		hm[9].innerHTML='请输入邮政编码！';
		postno.focus();
		return false;
	}
	if(!isPostalCode(postno)){
		hm[9].innerHTML='邮编格式不正确！';
		postno.focus();
		return false;
	}
	if(telephone.value==''){
		hm[10].innerHTML='请输入联系电话！';
		telephone.focus();
		return false;
	}
	if(email.value==''){
		hm[11].innerHTML='请输入Email，以便找回密码使用！';
		email.focus();
		return false;
	}
	if(!checkEmail(email)){
		hm[11].innerHTML='Email地址输入格式错误！';
		email.focus();
		return false;
	}
	return true;
}

function checkUPUser(){
    var frm=document.getElementById('myform');
	var msgs=document.getElementsByTagName("div");
	var question=frm.attchar08;
	var answer=frm.attchar09;
	var dwmc=frm.attchar11;
	var entshort=frm.attchar12;
	var address=frm.attchar13;
	var postno=frm.attchar14;
	var telephone=frm.attchar15;
	var email=frm.attchar16;
	
	var hm=new Array();
	var j=0;
	for(var i=0;i<msgs.length;i++){
		if(msgs[i].id=='divHtml'){
		    hm[j]=msgs[i];
			j++;
		}		
	}
	for(var i=0;i<hm.length;i++){
		hm[i].innerHTML='';
	}
	if(question.value==''){
		hm[0].innerHTML='请选择密码提示问题！';
		question.focus();
		return false;
	}
	if(answer.value==''){
		hm[1].innerHTML='请输入密码提示问题！';
		answer.focus();
		return false;
	}
	if(dwmc.value==''){
		hm[2].innerHTML='请输入单位名称！';
		dwmc.focus();
		return false;
	}
	if(entshort.value==''){
		hm[3].innerHTML='请输入企业简称，用于水印！';
		entshort.focus();
		return false;
	}
	if(address.value==''){
		hm[4].innerHTML='请输入通讯地址！';
		address.focus();
		return false;
	}
	if(postno.value==''){
		hm[5].innerHTML='请输入邮政编码！';
		postno.focus();
		return false;
	}
	if(!isPostalCode(postno)){
		hm[5].innerHTML='邮编格式不正确！';
		postno.focus();
		return false;
	}
	if(telephone.value==''){
		hm[6].innerHTML='请输入联系电话！';
		telephone.focus();
		return false;
	}
	if(email.value==''){
		hm[7].innerHTML='请输入Email，以便找回密码使用！';
		email.focus();
		return false;
	}
	if(!checkEmail(email)){
		hm[8].innerHTML='Email地址输入格式错误！';
		email.focus();
		return false;
	}
	updateUser();
}
function resetBtn(fm){
	 var title=fm.bztitle;
	 fm.reset();
	 title.focus();
	 return false;
}

function resetBtns(fm){
	 var title=fm.title;
	 fm.reset();
	 title.focus();
	 return false;
}
function updateUser(){
	var options = {
		type: 'POST',
		async: false,
		dataType: 'content-type',
		success: function(responseText){
			if(responseText=="<%=Constants.ERROR_LOGIN %>"){
				alert("资料更新失败！");
			}else{
				alert("资料更新成功！")
			}
		}
	}
	//提交form
	$('#myform').ajaxSubmit(options);
} 