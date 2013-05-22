_menusZIndex=2000
_menusItems=new Array
_globMenuCaptured=null
_isColor=0
_isLastUsedColor=1
_isNotColor=2
_currentFocus=null
_mitemH=22
function newMenuWidget(id,hideCB,beforeShowCB)
{
var o=newWidget(id)
o.items=new Array
o.par=null
o.currentSub=-1
o.nextSub=-1
o.zIndex=_menusZIndex
o.hideCB=hideCB
o.beforeShowCB=beforeShowCB
o.init=MenuWidget_init
o.justInTimeInit=MenuWidget_justInTimeInit
o.getHTML=MenuWidget_getHTML
o.getShadowHTML=MenuWidget_getShadowHTML
o.show=MenuWidget_show
o.internalAdd=o.add=MenuWidget_add
o.addCheck=MenuWidget_addCheck
o.addSeparator=MenuWidget_addSeparator
o.getItem=MenuWidget_getItem
o.isShown=MenuWidget_isShown
o.showSub=MenuWidget_showSub
o.captureClicks=MenuWidget_captureClicks
o.releaseClicks=MenuWidget_releaseClicks
o.focus=MenuWidget_focus
o.restoreFocus=MenuWidget_restoreFocus
o.hasVisibleItem=MenuWidget_hasVisibleItem
o.clickCB=new Array
o.clickCBDocs=new Array
o.write=MenuWidget_write
o.alignLeft=false
o.sepCount=0
return o
}
function MenuWidget_captureClicks(w)
{
var o=this
if (o.par==null)
{
if (w==null)
{
_globMenuCaptured=o
o.clickCB.length=0
o.clickCBDocs.length=0
w=_curWin
}
if (canScanFrames(w))
{
if (_moz)
{
_oldErrHandler=window.onerror
window.onerror=localErrHandler
}
try
{
d=w.document
o.clickCB[o.clickCB.length]=d.onmousedown
o.clickCBDocs[o.clickCBDocs.length]=d
d.onmousedown=MenuWidget_globalClick
var fr=w.frames,len=fr.length
for (var i=0;i<len;i++)
o.captureClicks(fr[i])
}
catch(expt)
{
}
if (_moz)
window.onerror=_oldErrHandler
}
}
}
function MenuWidget_releaseClicks()
{
var o=this
if (o.par==null)
{
var len=o.clickCB.length
for (var i=0;i<len;i++)
{
try
{
o.clickCBDocs[i].onmousedown=o.clickCB[i]
}
catch(expt)
{
}
o.clickCB[i]=null
o.clickCBDocs[i]=null
}
o.clickCB.length=0
o.clickCBDocs.length=0
}
}
_menuItem=null;
function MenuWidget_focus()
{
var o=this, items=o.items, len=items.length
for(var i=0; i<len;i++)
{
if(items[i].isShown && !items[i].isSeparator)
{
_menuItem=items[i];
setTimeout("_menuItem.focus()",1);
if(o.endLink) o.endLink.show(true)
if(o.startLink) o.startLink.show(true)
break;
}
}
}
function MenuWidget_keepFocus(id)
{
var o=getWidget(getLayer(id))
if (o) o.focus();
}
function MenuWidget_restoreFocus()
{
var o=this
if(o.endLink) o.endLink.show(false)
if(o.startLink) o.startLink.show(false)
if(o.parIcon) o.parIcon.focus()
else 
if (o.par)o.par.focus()
else if(o.parCalendar) o.parCalendar.focus()
}
function MenuWidget_keyDown(id,e)
{
var o=getWidget(getLayer(id))
var key=eventGetKey(e)
if(key==27 && o)
{
o.restoreFocus()
o.show(false)
if (o.par && o.par.par)
{
o.par.par.currentSub=-1
}
o.currentSub=-1
}
else if(o && (key==109 || key==37))
{
if (o.par && o.par.par)  
{
o.restoreFocus()
o.show(false)
o.par.par.currentSub=-1
o.currentSub=-1
}
}
}
function MenuWidget_globalClick()
{
var o=_globMenuCaptured
if (o!=null)
{
_globMenuCaptured=null
o.releaseClicks()
o.show(false)
}
}
function MenuWidget_add(id,text,cb,icon,dx,dy,disabled,disDx,disDy,alt)
{
var o=this,i=o.items.length
var ret=o.items[i]=newMenuItem(o,id,text,cb,icon,dx,dy,disabled,disDx,disDy,false,alt)
ret.menuIndex=i
ret.dynHTML()
return ret
}
function MenuWidget_addCheck(id,text,cb,icon,dx,dy,disabled,disDx,disDy,alt)
{
var o=this,i=o.items.length
var ret=o.items[i]=newMenuItem(o,id,text,cb,icon,dx,dy,disabled,disDx,disDy,true,alt)
ret.menuIndex=i
ret.dynHTML()
return ret
}
function MenuWidget_addSeparator()
{
var s=this.internalAdd("_menusep_"+(this.sepCount++))
s.isSeparator=true
return s
}
function MenuWidget_init()
{
}
function MenuWidget_getItem(index)
{
var o=this,items=o.items
if ((index>=0)&&(index<items.length))
return items[index]
return null
}
function MenuWidget_showSub()
{
var o=this
if (o.nextSub!=-1)
{
if (o.nextSub!=o.currentSub)
{
var currentItem=o.items[o.currentSub]
if (currentItem&&currentItem.sub)
{
currentItem.sub.show(false)
o.currentSub=-1
}
var nextItem=o.items[o.nextSub]
if (nextItem&&nextItem.sub)
{
var lyr=nextItem.layer
var x=parseInt(o.css.left)
var y=parseInt(o.css.top)
for (var i=0;i<o.nextSub;i++)
{
var item=o.items[i]
if (item.isShown)
{
if ((item.icon!=null)||(item.text!=null))
y+=_mitemH
else
y+=3
}
}
var w=o.getWidth()
x=x+w-4
nextItem.attachSubMenu(nextItem.sub)
nextItem.sub.show(true,x,y,false,w)
o.currentSub=o.nextSub
}
}
}
else if (o.currentSub!=-1)
{
var currentItem=o.items[o.currentSub]
if (currentItem&&currentItem.sub)
{
currentItem.sub.show(false)
o.currentSub=-1
}
}
}
function MenuWidget_write()
{
}
function MenuWidget_justInTimeInit()
{
var o=this
o.layer=getLayer(o.id)
if (o.layer==null)
{
targetApp(o.getHTML())
o.layer=getLayer(o.id)
}
o.layer._widget=o.widx
o.css=o.layer.style
o.endLink=newWidget("endLink_"+o.id)
o.endLink.init()
o.startLink=newWidget("startLink_"+o.id)
o.startLink.init()
o.iframeLyr=getLayer("menuIframe_"+o.id)
o.iframeCss=o.iframeLyr.style
var items=o.items
for (var i in items)
items[i].init()
}
function MenuWidget_getShadowHTML()
{
return getBGIframe('menuIframe_'+this.id)
}
function MenuWidget_getHTML()
{
var o=this,items=o.items
var keysCbs=' onkeydown="'+_codeWinName+'.MenuWidget_keyDown(\''+o.id+'\',event);return true" '
var s=o.getShadowHTML()+'<a style="position:absolute;left:-30px;top:-30px; visibility:hidden" id="startLink_'+o.id+'" href="javascript:void(0)" onfocus="'+_codeWinName+'.MenuWidget_keepFocus(\''+o.id+'\');return false;" ></a><table style="display:none;" class="menuFrame" id="'+o.id+'" cellspacing="0" cellpadding="0" border="0" '+keysCbs+'><tbody>'
for (var i in items)
s+=items[i].getHTML()
s+='</tbody></table><a style="position:absolute;left:-30px;top:-30px; visibility:hidden" id="endLink_'+o.id+'" href="javascript:void(0)" onfocus="'+_codeWinName+'.MenuWidget_keepFocus(\''+o.id+'\');return false;" ></a>'
return s
}
function MenuWidget_show(show,x,y,parentPropagate,parentMenuW)
{
var o=this
if (o.layer==null)
o.justInTimeInit()
var css=o.css,iCss=o.iframeCss
if (show)
{
if (o.beforeShowCB)
o.beforeShowCB()
if(!o.hasVisibleItem()) return;
o.captureClicks()
css.display='block'
css.zIndex=(o.zIndex+1)
css.visibility="hidden"
css.left="-1000px"
css.top="-1000px"
var w=o.getWidth()
var h=o.getHeight()
if (o.alignLeft)
x-=w
var x2=x+w+4,y2=y+h+4
if (x2>winWidth())
x=Math.max(0,x-4-(w+((parentMenuW!=null)?parentMenuW-12:0)))
if (y2>winHeight())
y=Math.max(0,y-4-h+(parentMenuW!=null?30:0))
css.left=""+x+"px"
css.top=""+y+"px"
css.visibility="visible"
iCss.left=""+x+"px"
iCss.top=""+y+"px"
iCss.width=""+w+"px"
iCss.height=""+h+"px"
iCss.zIndex=o.zIndex-1
iCss.display='block'
if (_ie)
{
y-=2
x-=2
}
o.nextSub=-1
o.showSub()
o.focus()
}
else
{
if (parentPropagate&&o.par)
{
if (o.par.par)
o.par.par.show(show,x,y,parentPropagate)
}
css.display='none'
iCss.display='none'
o.nextSub=-1
o.showSub()
if (o.hideCB)
o.hideCB()
o.releaseClicks()
}
}
function MenuWidget_isShown()
{
var o=this
if (o.layer==null)
return false
else
return (o.css.display=='block')
}
function MenuWidget_hasVisibleItem()
{
var o=this
if(o.isMenuColor || o.isCalendar) return true;
var items=o.items
for (var i in items)
{
var item=items[i]
if (item && !(item.isSeparator==true) && item.isShown)
return true;
}
return false
}
function newMenuItem(par,id,text,cb,icon,dx,dy,disabled,disDx,disDy,isCheck,alt)
{
var o=new Object
o.par=par
o.id=id
o.text=text
o.cb=cb
o.icon=icon
o.dx=(dx==null)?0:dx
o.dy=(dy==null)?0:dy
o.disDx=(disDx==null)?o.dx:disDx
o.disDy=(disDy==null)?o.dy:disDy
o.sub=null
o.layer=null
o.iconTDLayer=null
o.iconLayer=null
o.textLayer=null
o.textOnlyLayer=null
o.hasNoLayer=false
o.isSeparator=false
o.disabled=(disabled!=null)?disabled:false
o.isShown=true
o.alt=alt 
o.index=_menusItems.length
_menusItems[o.index]=o
o.menuIndex=-1
o.isCheck=isCheck
o.checked=false
o.menuItemType=_isNotColor
o.init=MenuItem_init
o.leftZoneClass="menuLeftPart"
o.leftZoneSelClass="menuLeftPartSel"
o.attachSubMenu=MenuItem_attachSubMenu
o.getHTML=MenuItem_getHTML
o.getHTMLPart=MenuItem_getHTMLPart
o.dynHTML=MenuItem_dynHTML
o.setDisabled=MenuItem_setDisabled
o.check=MenuItem_check
o.isChecked=MenuItem_isChecked
o.show=MenuItem_show
o.setText=MenuItem_setText
o.setIcon=MenuItem_setIcon
o.focus=MenuItem_focus
return o
}
function MenuItem_init()
{
if (!this.hasNoLayer)
{
var o=this,id=o.par.id
o.layer=getLayer(id+'_item_'+o.id)
o.layer._boIndex=o.index
if (!o.isSeparator)
{
if ((o.icon!=null)||(o.isCheck))
{
o.iconLayer=getLayer(id+'_item_icon_'+o.id)
o.iconTDLayer=getLayer(id+'_item_td_'+o.id)
}
o.textLayer=getLayer(id+'_text_'+o.id)
o.textOnlyLayer=getLayer(id+'_span_text_'+o.id)
if(o.textOnlyLayer) 
{
o.textOnlyLayer.title=o.checked?o.textOnlyLayer.innerText+" "+_menuCheckLab:""
if(o.disabled) o.textOnlyLayer.title +=" "+_menuDisableLab
}
}
if (o.isCheck)
{
o.check(o.checked,true)
}
}
}
function MenuItem_attachSubMenu(menu)
{
var o=this
o.sub=menu
menu.par=o
menu.zIndex=o.par.zIndex+2
if (o.layer)
{
if (o.arrowLayer==null)
o.arrowLayer=getLayer(o.par.id+'_item_arrow_'+o.id)
var dis=o.disabled
changeSimpleOffset(o.arrowLayer,dis?7:0,dis?81:64)
}
return menu
}
function MenuItem_check(check,force)
{
var o=this
if ((o.checked!=check)||force)
{
o.checked=check
if (o.par.layer)
{
var lyr=o.layer
if (lyr)
{
if (o.icon==null)
changeSimpleOffset(o.iconLayer,0,(o.checked?48:0),null,(o.checked?_menuCheckLab:""))
changeOffset(o.iconTDLayer,0,(o.checked?96:0))
if (o.checkFrame==null)
o.checkFrame=getLayer(o.par.id+'_item_check_'+o.id)
o.checkFrame.className='menuIcon'+(o.checked?"Check":"")
if(o.textOnlyLayer) 
o.textOnlyLayer.title=o.checked?o.textOnlyLayer.innerText+" "+_menuCheckLab:""
}
}
}
}
function MenuItem_isChecked()
{
return this.checked
}
function MenuItem_setDisabled(dis)
{
var o=this
if (o.disabled!=dis)
{
o.disabled=dis
if (o.par.layer)
{
var lyr=o.layer
if (lyr)
{
lyr.style.cursor=dis?'default':_hand
if (o.icon)
changeSimpleOffset(o.iconLayer,dis?o.disDx:o.dx,dis?o.disDy:o.dy)
var cn='menuTextPart'+(o.disabled?'Disabled':'')
if (cn!=o.textLayer.className)
o.textLayer.className=cn
if (o.sub)
{
if (o.arrowLayer==null)
o.arrowLayer=getLayer(o.par.id+'_item_arrow_'+o.id)
changeSimpleOffset(o.arrowLayer,dis?7:0,dis?81:64)
}
if(o.textOnlyLayer) 
o.textOnlyLayer.title=o.disabled?o.textOnlyLayer.innerText+" "+_menuDisableLab:""
}
}
}
}
function MenuItem_setText(s)
{
var o=this,id=o.par.id
o.text=s
o.textLayer.innerHTML=o.getHTMLPart(1)
o.textOnlyLayer=getLayer(id+'_span_text_'+o.id)
}
function MenuItem_setIcon(dx,dy,disDx,disDy,url)
{
var o=this
o.url = url ? url : o.url
o.dx = (dx != null) ? dx : o.dx
o.dy = (dy != null) ? dy : o.dy
o.disDx = (disDx != null) ? disDx : o.disDx
o.disDy = (disDy != null) ? disDy : o.disDy
if (o.icon)
changeSimpleOffset(o.iconLayer,o.disabled?o.disDx:o.dx, o.disabled?o.disDy:o.dy,o.url)
}
function MenuItem_show(sh)
{
var o=this
o.isShown=sh
if (o.layer!=null)
o.layer.style.display=sh?'':'none'
}
function MenuItem_invert(lyr,inv)
{
var c=lyr.childNodes,y=0,len=c.length,idx=lyr._boIndex
var o=_menusItems[idx]
if (o.disabled)
inv=0
else
{
if (inv)
{
o.par.nextSub=o.menuIndex
MenuItem_callShowSub(idx,true)
if (o.par.par)
{
if (o.par.par.par)
{
o.par.par.par.nextSub=o.par.par.menuIndex
}
}
}
}
 var realPart=0
 for (var i=0;i<len;i++)
 {
 var ce=c[i]
 if (ce.tagName!=null)
 {
 if (realPart==0)
 ce.className=inv?o.leftZoneSelClass:o.leftZoneClass
 else if (realPart==1)
 ce.className="menuTextPart"+(inv?"Sel":"")+(o.disabled?"Disabled":"")
 else
 ce.className="menuRightPart"+(inv?"Sel":"")
  realPart++
 }
 }
}
function MenuItem_click(lyr,e)
{
eventCancelBubble(e)
var idx=lyr._boIndex,o=_menusItems[idx]
o.layer=lyr
if (!o.disabled)
{
if (o.sub)
{
o.par.nextSub=o.menuIndex
MenuItem_callShowSub(idx)
}
else
{
o.par.show(false,0,0,true)
if (o.isCheck)
{
if (o.par.uncheckAll)
o.par.uncheckAll()
o.check(!o.checked)
}
MenuItem_invert(lyr,0,idx)
o.par.nextSub=-1
if (o.cb)
setTimeout("MenuItem_delayedClick("+idx+")",1)
}
}
}
function MenuItem_keyDown(lyr,e)
{
var idx=lyr._boIndex,o=_menusItems[idx]
o.layer=lyr
var k=eventGetKey(e)
switch(k)
{
case 13:
MenuItem_click(lyr,e)
break;
case 107:
case 39:
if (!o.disabled && o.sub )
{
MenuItem_click(lyr,e)
}
break;
case 109:
case 37:
break;
case 40:
var items=o.par.items, len = items.length
for(var i=o.menuIndex+1;i<len;i++)
{
if(items[i].isShown && !items[i].isSeparator)
{
items[i].focus()
break;
}
}
break;
case 38:
var items=o.par.items, len = items.length
for(var i=o.menuIndex-1;i>=0;i--)
{
if(items[i].isShown && !items[i].isSeparator)
{
items[i].focus()
break;
}
}
break;
}
}
function MenuItem_callShowSub(idx,delayed)
{
var o=_menusItems[idx]
if (delayed)
setTimeout('MenuItem_delayedShowSub('+idx+')',500)
else
MenuItem_delayedShowSub(idx)
}
function MenuItem_delayedShowSub(idx)
{
var o=_menusItems[idx]
o.par.showSub()
}
function MenuItem_noBubble(e)
{
eventCancelBubble(e)
}
function MenuItem_delayedClick(idx)
{
var item=_menusItems[idx]
if (item.cb)
item.cb()
}
function MenuItem_getHTMLPart(part)
{
var o=this
switch(part)
{
case 0:
var im=null,className=' class="menuIcon' + (o.checked?"Check":"")+'"'
if (o.isCheck&&(o.icon==null))
im=simpleImgOffset(_skin+"menus.gif",16,16,0,o.checked?48:0,(o.par.id+'_item_icon_'+o.id),null,(o.checked?_menuCheckLab:""))
else
im=o.icon?simpleImgOffset(o.icon,16,16,o.disabled?o.disDx:o.dx,o.disabled?o.disDy:o.dy,(o.par.id+'_item_icon_'+o.id),null,o.alt?o.alt:''):(getSpace(16,16))
if (o.isCheck)
{
var size=_ie?18:16
im='<div id="'+o.par.id+'_item_check_'+o.id+'" class="menuIcon'+(o.checked?"Check":"")+'" style="width:'+size+'px;height:'+size+'px;padding:1px">'+im+'</div>'
}
return im
case 1:
var div1=_saf?'<div style="height:19px;padding-top:5px">':'',div2=_saf?'</div>':''
return div1+'<span id="'+(o.par.id+'_span_text_'+o.id)+'" tabIndex="0">'+convStr(o.text)+'</span>'+div2
case 2:
return simpleImgOffset(_skin+"menus.gif",16,16,o.sub?(o.disabled?7:0):0,o.sub?(o.disabled?81:64):0,o.par.id+'_item_arrow_'+o.id)
case 3:
return '<table width="100%" height="3" cellpadding="0" cellspacing="0" border="0" style="'+backImgOffset(_skin+"menus.gif",0,80)+';"><tbody><tr><td></td></tr></tbody></table>'
}
}
function MenuItem_getHTML()
{
var o=this
if ((o.icon!=null)||(o.text!=null))
{
var invertCbs=' onclick="'+_codeWinName+'.MenuItem_click(this,event);return true" oncontextmenu="'+_codeWinName+'.MenuItem_click(this,event);return false" onmouseover="'+_codeWinName+'.MenuItem_invert(this,1)" onmouseout="'+_codeWinName+'.MenuItem_invert(this,0);" '
var keysCbs=' onkeydown="'+_codeWinName+'.MenuItem_keyDown(this,event);return true" '
return '<tr onmousedown="'+_codeWinName+'.MenuItem_noBubble(event)" onmouseup="'+_codeWinName+'.MenuItem_noBubble(event)" id="'+(o.par.id+'_item_'+o.id)+'" style="'+(!o.isShown?'display:none;':'')+'height:'+_mitemH+'px;width:24px;cursor:'+(o.disabled?'default':_hand)+'" '+invertCbs+keysCbs+' valign="middle">'+
'<td id="'+(o.par.id+'_item_td_'+o.id)+'" style="width:23px;height:'+_mitemH+'px;" align="center" class="'+o.leftZoneClass+'">'+
o.getHTMLPart(0)+
'</td>'+
'<td '+(o.centered?' align="center" ':'')+' style="height:'+_mitemH+'px" id="'+(o.par.id+'_text_'+o.id)+'" class="menuTextPart'+(o.disabled?'Disabled':'')+'">'+
o.getHTMLPart(1)+
'</td>'+
'<td align="center" style="width:24px;height:'+_mitemH+'px;" class="menuRightPart">'+
o.getHTMLPart(2)+
'</td>'+
'</tr>'
}
else
{
return '<tr onmousedown="'+_codeWinName+'.MenuItem_noBubble(event)" onclick="'+_codeWinName+'.MenuItem_noBubble(event)" id="'+(o.par.id+'_item_'+o.id)+'" onmouseup="'+_codeWinName+'.MenuItem_noBubble(event)" style="height:3px">'+
'<td class="'+o.leftZoneClass+'" style="width:24px;height:3px;border:0px"></td>'+
'<td colspan="2" style="padding-left:5px;padding-right:5px;border:0px">'+
o.getHTMLPart(3)+
'</td></tr>'
}
}
function MenuItem_dynHTML()
{
var o=this
if (o.par.layer==null)
return
var tbody=o.par.layer.childNodes[0],tr=tbody.insertRow(tbody.rows.length),st=tr.style
tr.onmousedown=MenuItem_noBubble
tr.onmouseup=MenuItem_noBubble
tr.id=(o.par.id+'_item_'+o.id)
if ((o.icon!=null)||(o.text!=null))
{
var td1=tr.insertCell(0),td2=tr.insertCell(1),td3=tr.insertCell(2),st1=td1.style,st2=td2.style,st3=td3.style
tr.onclick=MenuItem_clickCallTrue
tr.oncontextmenu=MenuItem_clickCallFalse
tr.onmouseover=MenuItem_invertCall1
tr.onmouseout=MenuItem_invertCall0
st.height=""+_mitemH+"px"
st.width="24px"
st.cursor=(o.disabled?'default':_hand)
td1.id=(o.par.id+'_item_td_'+o.id)
st1.width="23px"
st1.height=""+_mitemH+"px"
td1.innerHTML=o.getHTMLPart(0)
td1.align="center"
td1.className=o.leftZoneClass
if (o.centered)
td2.align="center"
st2.height=""+_mitemH+"px"
td2.id=(o.par.id+'_text_'+o.id)
td2.className="menuTextPart"+(o.disabled?'Disabled':'')
td2.innerHTML=o.getHTMLPart(1)
td3.className="menuRightPart"
td3.align="center"
st3.width="24px"
st3.height=""+_mitemH+"px"
changeOffset(td3,0,0,_skin+"menus.gif")
td3.innerHTML=o.getHTMLPart(2)
o.init()
}
else
{
tr.onclick=MenuItem_noBubble
tr.style.height="3px"
var td1=tr.insertCell(0),td2=tr.insertCell(1),st1=td1.style,st2=td2.style
td1.className=o.leftZoneClass
st1.width="24px"
st1.height="3px"
td2.colSpan="2"
st2.paddingLeft="5px"
st2.paddingRight="5px"
td2.innerHTML=o.getHTMLPart(3)
}
}
function MenuItem_clickCallTrue(event)
{
MenuItem_click(this,event)
return true
}
function MenuItem_clickCallFalse(event)
{
MenuItem_click(this,event)
return false
}
function MenuItem_invertCall0(event)
{
MenuItem_invert(this,0)
}
function MenuItem_invertCall1(event)
{
MenuItem_invert(this,1)
}
function MenuItem_focus()
{
var o=this
if(isLayerDisplayed(o.layer) && o.textOnlyLayer && o.textOnlyLayer.focus)
{
o.textOnlyLayer.focus();
}
}
function newMenuColorWidget(id,hideCB)
{
var o=newMenuWidget(id,hideCB)
o.addSeparator=null
o.lastUsedTxt=""
o.lastUsedColorsAr=null
o.addColor=MenuColorWidget_addColor
o.addLastUsed=MenuColorWidget_addLastUsed
o.getHTML=MenuColorWidget_getHTML
o.uncheckAll=MenuColorWidget_uncheckAll
o.isMenuColor=true
return o
}
function MenuColorWidget_addColor(tooltip,color,cb)
{
var o=this,i=o.items.length
var ret=o.items[i]=newColorMenuItem(o,color,tooltip,cb)
ret.menuIndex=i
return ret
}
function MenuColorWidget_addLastUsed(text,lastUsedColorsAr,cb, beforeShowCB)
{
var o=this
o.lastUsedTxt = text
o.lastUsedColorsAr = lastUsedColorsAr
o.beforeShowCB = MenuColorWidget_beforeShowCB
colorsMax = 8
len = o.items.length
var it = null
for (var c = 0; c < colorsMax; c++)
{
it = newLastUsedColorMenuItem(o,c,"",cb)
it.isLast = (c == colorsMax-1) ? true : false 
o.items[len + c] = it
}
}
function MenuColorWidget_getHTML()
{
var o=this,items=o.items
var j = 0
var keysCbs=' onkeydown="'+_codeWinName+'.MenuWidget_keyDown(\''+o.id+'\',event);return true" '
var s = new Array
s[j++] = getBGIframe('menuIframe_'+this.id)
s[j++] = '<a style="position:absolute;left:-30px;top:-30px; visibility:hidden" id="startLink_'+o.id+'" href="javascript:void(0)" onfocus="'+_codeWinName+'.MenuWidget_keepFocus(\''+o.id+'\');return false;" ></a>'
s[j++] = '<table style="display:none;" class="menuFrame" id="'+o.id+'" cellspacing="0" cellpadding="0" border="0"'+keysCbs+'><tbody>'
var sep = '<tr style="height:3px"><td colspan="8" style="padding-left:5px;padding-right:5px;"><table width="100%" height="3" cellpadding="0" cellspacing="0" border="0" style="'+backImgOffset(_skin+"menus.gif",0,80)+';"><tbody><tr><td></td></tr></tbody></table></td></tr>'
var len = items.length
lastUsedCol=""
lastUsedColIconsNb = 0
lastUsedColIconsMaxLine = 3
for (var i in items)
{
var item=items[i]
switch (item.menuItemType)
{
case _isColor:
s[j++] = item.getHTML()
break;
case _isLastUsedColor:
lastUsedCol += item.getHTML()
lastUsedCol += (lastUsedColIconsNb++ == lastUsedColIconsMaxLine)? "</tr><tr>":""
if (item.isLast)
{
s[j++] = sep
s[j++] = '<tr><td colspan="8">'
s[j++] ='<table border="0" cellspacing="0" cellpadding="0" width="100%"><tbody><tr>'
s[j++] ='<td width="50%" class="menuTextPart">' + convStr(o.lastUsedTxt) + '</td>'
s[j++] ='<td><table border="0" cellspacing="0" cellpadding="0"><tbody><tr>'
s[j++] =lastUsedCol
s[j++] ='</tr></tbody></table></td>'
s[j++] ='</tr></tbody></table>'
s[j++] = '</td></tr>'
s[j++] = sep
}
break;
case _isNotColor:
item.leftZoneClass="menuLeftPartColor"
item.leftZoneSelClass="menuLeftPartSel"
item.centered=true
s[j++] ='<tr><td colspan="8"><table border="0" cellspacing="0" cellpadding="0" width="100%"><tbody><tr>'+item.getHTML()+'</tr></tbody></table></td></tr>'
s[j++] = (i == 0 )? sep:""
}
}
s[j++] ='</tbody></table><a style="position:absolute;left:-30px;top:-30px; visibility:hidden" id="endLink_'+o.id+'" href="javascript:void(0)" onfocus="'+_codeWinName+'.MenuWidget_keepFocus(\''+o.id+'\');return false;" ></a>'
return s.join("")
}
function MenuColorWidget_beforeShowCB()
{
var o=this, j=0
lenLastUsed = o.lastUsedColorsAr.length
for (var i in o.items)
{
var item=o.items[i]
if (item.menuItemType == _isLastUsedColor) 
{
if (j < lenLastUsed)
{
item.color = o.lastUsedColorsAr[j++]
item.layer.childNodes[0].childNodes[0].style.backgroundColor = 'rgb(' + item.color + ')'
var t = _colorsArr[item.color]
item.text = (t)? t:(_RGBTxtBegin + item.color + _RGBTxtEnd)
item.layer.childNodes[0].childNodes[0].childNodes[0].alt = item.text
item.show(true)
} else {
item.show(false)
}
} 
}
}
function MenuColor_invert(lyr,inv)
{
var o=_menusItems[lyr._boIndex]
if (o && o.checked)
inv=1
lyr.childNodes[0].className="menuColor"+(inv?"sel":"")
}
function MenuColorWidget_uncheckAll()
{
var o=this,items=o.items
for (var i in items)
{
var item=items[i]
if (item.checked)
item.check(false)
}
}
function MenuColor_click(lyr,e)
{
eventCancelBubble(e)
var idx=lyr._boIndex,o=_menusItems[idx]
o.par.uncheckAll()
MenuColor_invert(lyr,1,idx)
o.checked=true
o.par.show(false,0,0,true)
if (o.cb)
setTimeout("MenuItem_delayedClick("+idx+")",1)
}
function newColorMenuItem(par,color,text,cb)
{
var o=newMenuItem(par,"color_"+color,text,cb)
o.color=color
o.attachSubMenu=null
o.getHTML=ColorMenuItem_getHTML
o.check=ColorMenuItem_check
o.menuItemType=_isColor
return o
}
function ColorMenuItem_check(check)
{
var o=this
if (o.checked!=check)
{
o.checked=check
if (o.layer)
MenuColor_invert(o.layer,o.checked?1:0)
}
}
function ColorMenuItem_getHTML()
{
var o=this,s="",d=_moz?10:12,lenTotal=o.par.items.length,index=o.menuIndex - 1;col=index%8
var len=0
for (var i = 0; i <lenTotal; i++)
{
if (o.par.items[i].menuItemType == _isColor) len++
}
var first=(col==0)
var last=(col==7)
var firstL=(index<8)
var lastL=(index>=(Math.floor((len-1)/8)*8))
var cbs=' onclick="'+_codeWinName+'.MenuColor_click(this,event);return true" oncontextmenu="'+_codeWinName+'.MenuColor_click(this,event);return false" onmousedown="'+_codeWinName+'.MenuItem_noBubble(event)" onmouseup="'+_codeWinName+'.MenuItem_noBubble(event)" onmouseover="'+_codeWinName+'.MenuColor_invert(this,1)" onmouseout="'+_codeWinName+'.MenuColor_invert(this,0);" '
if (first)
s+='<tr valign="middle" align="center">'
s+='<td id="'+(o.par.id+'_item_'+o.id)+'" '+cbs+' style="padding-top:'+(firstL?2:0)+'px;padding-bottom:'+(lastL?2:0)+'px;padding-left:'+(first?3:1)+'px;padding-right:'+(last?3:1)+'px"><div class="menuColor'+(o.checked?'Sel':'')+'"><div style="cursor:'+_hand+';border:1px solid #4A657B;width:'+d+'px;height:'+d+'px;background-color:rgb('+o.color+');">'+img(_skin+'../transp.gif',10,10,null,null,o.text)+'</div></div></td>'
if (last)
s+='</tr>'
return s
}
function newLastUsedColorMenuItem(par,color,text,cb)
{
var o=newMenuItem(par,"lastUsed_color_"+color,text,cb)
o.color=color
o.menuItemType = _isLastUsedColor
o.attachSubMenu=null
o.check=ColorMenuItem_check
o.getHTML=LastUsedColorMenuItem_getHTML
return o
}
function LastUsedColorMenuItem_getHTML()
{
var o=this,s="",d=_moz?10:12
var cbs=' onclick="'+_codeWinName+'.MenuColor_click(this,event);return true" oncontextmenu="'+_codeWinName+'.MenuColor_click(this,event);return false" onmousedown="'+_codeWinName+'.MenuItem_noBubble(event)" onmouseup="'+_codeWinName+'.MenuItem_noBubble(event)" onmouseover="'+_codeWinName+'.MenuColor_invert(this,1)" onmouseout="'+_codeWinName+'.MenuColor_invert(this,0);" '
s+='<td id="'+(o.par.id+'_item_'+o.id)+'" width="18" '+cbs+' style="padding-top:0px;padding-bottom:0px;padding-left:1px;padding-right:1px"><div class="menuColor'+(o.checked?'sel':'')+'"><div style="border:1px solid #4A657B;width:'+d+'px;height:'+d+'px;background-color:rgb('+o.color+');">'+img(_skin+'../transp.gif',10,10,null,null,o.text)+'</div></div></td>'
return s
}
function newScrollMenuWidget(id,changeCB,multi,width,lines,tooltip,dblClickCB,keyUpCB,
showLabel,label,convBlanks)
{
var o=newWidget(id)
o.list=newListWidget("list_"+id,ScrollMenuWidget_changeCB,multi,width,lines,tooltip,ScrollMenuWidget_dblClickCB,ScrollMenuWidget_keyUpCB)
o.list.par=o
o.label=NewLabelWidget("label_"+id,label,convBlanks)
o.showLabel=showLabel
o.changeCB=changeCB
o.dblClickCB=dblClickCB
o.keyUpCB=keyUpCB
o.zIndex=_menusZIndex
o.init=ScrollMenuWidget_init
o.justInTimeInit=ScrollMenuWidget_justInTimeInit
o.setDisabled=ScrollMenuWidget_setDisabled
o.write=ScrollMenuWidget_write
o.getShadowHTML=ScrollMenuWidget_getShadowHTML
o.getHTML=ScrollMenuWidget_getHTML
o.show=ScrollMenuWidget_show
o.add=ScrollMenuWidget_add
o.del=ScrollMenuWidget_del
o.getSelection=ScrollMenuWidget_getSelection
o.select=ScrollMenuWidget_select
o.valueSelect=ScrollMenuWidget_valueSelect
o.getCount=ScrollMenuWidget_getCount
o.isShown=MenuWidget_isShown
return o
}
function ScrollMenuWidget_init()
{
}
function ScrollMenuWidget_justInTimeInit()
{
var o=this
o.layer=getLayer(o.id)
if (o.layer==null)
{
targetApp(o.getHTML())
o.layer=getLayer(o.id)
}
o.layer._widget=o.widx
o.css=o.layer.style
o.css.visibility="hidden"
o.iframeLyr=getLayer("menuIframe_"+o.id)
o.iframeCss=o.iframeLyr.style
o.list.init()
o.label.init()
}
function ScrollMenuWidget_setDisabled()
{
}
function ScrollMenuWidget_write()
{
}
function ScrollMenuWidget_getShadowHTML()
{
return getBGIframe('menuIframe_'+this.id)
}
function ScrollMenuWidget_getHTML()
{
var o=this
var s=''
s+=o.getShadowHTML()
s+='<table id="'+o.id+'" style="display:none;" class="menuFrame" cellspacing="0" cellpadding="0" border="0"><tbody>'
s+='<tr><td align="center">'+o.list.getHTML()+'</td></tr>'
s+='<tr><td align="center">'+o.label.getHTML()+'</td></tr>'
s+='</tbody></table>'
return s
}
function ScrollMenuWidget_show(show,x,y)
{
var o=this
if (o.layer==null)
o.justInTimeInit()
var css=o.css,iCss=o.iframeCss
if (show)
{
css.display='block'
css.zIndex=(o.zIndex+1)
css.visibility="hidden"
css.left="-1000px"
css.top="-1000px"
var w=o.getWidth()
var h=o.getHeight()
if (o.alignLeft)
x-=w
var x2=x+w+4,y2=y+h+4
if (x2>winWidth())
x=Math.max(0,x-4-w)
if (y2>winHeight())
y=Math.max(0,y-4-h)
css.left=""+x+"px"
css.top=""+y+"px"
css.visibility="visible"
iCss.left=""+x+"px"
iCss.top=""+y+"px"
iCss.width=""+w+"px"
iCss.height=""+h+"px"
iCss.zIndex=o.zIndex-1
iCss.display='block'
if (_ie)
{
y-=2
x-=2
}
}
else
{
css.display='none'
iCss.display='none'
}
}
function ScrollMenuWidget_add(s,val,sel,id)
{
var o=this
if (o.layer==null)
o.justInTimeInit()
o.list.add(s,val,sel,id)
}
function ScrollMenuWidget_del(i)
{
var o=this
if (o.layer==null)
o.justInTimeInit()
o.list.del(i)
}
function ScrollMenuWidget_getSelection()
{
var o=this
if (o.layer==null)
o.justInTimeInit()
return o.list.getSelection()
}
function ScrollMenuWidget_select(i)
{
var o=this
if (o.layer==null)
o.justInTimeInit()
o.list.select(i)
}
function ScrollMenuWidget_valueSelect(v)
{
var o=this
if (o.layer==null)
o.justInTimeInit()
o.list.valueSelect(v)
}
function ScrollMenuWidget_getCount()
{
var o=this
if (o.layer==null)
o.justInTimeInit()
return o.list.getCount()
}
function ScrollMenuWidget_changeCB()
{
var o=this
o.par.show(false)
if (o.par.changeCB)
o.par.changeCB()
}
function ScrollMenuWidget_dblClickCB()
{
var o=this
o.par.show(false)
if (o.par.dblClickCB)
o.par.dblClickCB()
}
function ScrollMenuWidget_keyUpCB()
{
var o=this
o.par.show(false)
if (o.par.keyUpCB)
o.par.keyUpCB()
}
