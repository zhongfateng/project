
//set iframe height
function setIframe(){
if (window.contentDocument && window.contentDocument.body.offsetHeight) 
//if((parent.document.getElementById('commoniframeid')!=null)){
  parent.document.getElementById('commoniframeid').style.height=  window.document.body.offsetHeight; 
//}
 else if(window.document &&  window.document.body.scrollHeight)
 // if(!(parent.document.getElementById('commoniframeid')==null)){
parent.document.getElementById('commoniframeid').style.height=  window.document.body.scrollHeight+30;
//}
   //alert(window.document.body.scrollHeight);
  //alert(parent.document.getElementById('commoniframeid').style.height);
//parent.document.all("commoniframeid").style.width=document.body.scrollWidth;
}


function setIframetwo(ifname){
//alert(11111);
if (window.contentDocument && window.contentDocument.body.offsetHeight) 
  parent.document.getElementById(ifname).style.height=  window.document.body.offsetHeight; 
else if(window.document &&  window.document.body.scrollHeight)
  parent.document.getElementById(ifname).style.height=  window.document.body.scrollHeight+30;
   //alert(window.document.body.scrollHeight);
  //alert(parent.document.getElementById('commoniframeid').style.height);
//parent.document.all("commoniframeid").style.width=document.body.scrollWidth;
}


function setIframeheight(ifname,height){
//alert(11111);
if (window.contentDocument && window.contentDocument.body.offsetHeight) 
  parent.document.getElementById(ifname).style.height=  window.document.body.offsetHeight+height; 
else if(window.document &&  window.document.body.scrollHeight)
  parent.document.getElementById(ifname).style.height=  window.document.body.scrollHeight+height;
   //alert(window.document.body.scrollHeight);
  //alert(parent.document.getElementById('commoniframeid').style.height);
//parent.document.all("commoniframeid").style.width=document.body.scrollWidth;
}