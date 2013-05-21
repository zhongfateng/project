_ie=(document.all!=null)?true:false
_dom=(document.getElementById!=null)?true:false
_moz=_dom&&!_ie
_show='visible'
_hide='hidden'
_hand=_ie?"hand":"pointer"
_appVer=navigator.appVersion.toLowerCase();
_mac=(_appVer.indexOf('macintosh')>=0)||(_appVer.indexOf('macos')>=0);
_userAgent=navigator.userAgent?navigator.userAgent.toLowerCase():null
_saf=_moz&&_mac&&(_userAgent.indexOf("safari")>=0)
_ie6 = _ie&&(_appVer.indexOf("msie 5")<0)
_small=(screen.height<=600)
_curDoc=document
_curWin=self
_tooltipWin=self
_tooltipDx=0
_tooltipDy=0
_codeWinName="_CW"
_leftBtn=(_ie||_saf)?1:0
_preloadArr=new Array
_widgets=new Array
_resizeW=_ie6?"col-resize":"E-resize"
_resizeH=_ie6?"row-resize":"S-resize"
_ddData=new Array
_dontNeedEncoding=null;
_thex=null;
function initDom(skin,lang,curWin,codeUniqueName)
{
_skin=skin;
_lang=lang;
if (curWin)
{
_curWin=curWin
_curDoc=curWin.document
}
_tooltipWin=_curWin
if (codeUniqueName)
_codeWinName="_CW"+codeUniqueName
_curWin[_codeWinName]=self
var urlLibLabels = _skin + "../../language/" + _lang + "/" + "labels.js"
includeScript(urlLibLabels)
}
function styleSheet()
{
switch(_lang)
{
case 'ja':
case 'ko':
case 'zh':
includeCSS('style_fe')
break;
default:
includeCSS('style')
break;
}
}
function isLayerDisplayed(lyr)
{
var css=lyr?lyr.style:null
if(css)
{
if(css.display == "none" || css.visibility=="hidden")
return false
else
{
var par=lyr.parentNode
if(par!=null)
return isLayerDisplayed(par)
else
return true
}
}
else
return true;
}
function safeSetFocus(lyr)
{
if (lyr && lyr.focus && isLayerDisplayed(lyr))
lyr.focus()
}
function newWidget(id)
{
var o=new Object
o.id=id
o.layer=null
o.css=null
o.getHTML=Widget_getHTML
o.beginHTML=Widget_getHTML
o.endHTML=Widget_getHTML
o.write=Widget_write
o.begin=Widget_begin
o.end=Widget_end
o.init=Widget_init
o.move=Widget_move
o.resize=Widget_resize
o.setBgColor=Widget_setBgColor
o.show=Widget_show
o.getWidth=Widget_getWidth
o.getHeight=Widget_getHeight
o.setHTML=Widget_setHTML
o.setDisabled=Widget_setDisabled
o.focus=Widget_focus
o.setDisplay=Widget_setDisplay
o.isDisplayed=Widget_isDisplayed
o.appendHTML=Widget_appendHTML
o.setTooltip=Widget_setTooltip
o.initialized=Widget_initialized
o.widx=_widgets.length
_widgets[o.widx]=o
return o
}
function Widget_appendHTML()
{
append(_curDoc.body,this.getHTML())
}
function Widget_getHTML()
{
return ''
}
function Widget_write(i)
{
_curDoc.write(this.getHTML(i))
}
function Widget_begin()
{
_curDoc.write(this.beginHTML())
}
function Widget_end()
{
_curDoc.write(this.endHTML())
}
function Widget_init()
{
var o=this
o.layer=getLayer(o.id)
o.css=o.layer.style
o.layer._widget=o.widx
if (o.initialHTML)
o.setHTML(o.initialHTML)
}
function Widget_move(x,y)
{
c=this.css;if (x!=null){if (_moz) c.left=""+x+"px";else c.pixelLeft=x}if (y!=null){if (_moz) c.top=""+y+"px";else c.pixelTop=y}
}
function Widget_focus()
{
safeSetFocus(this.layer)
}
function Widget_setBgColor(c)
{
this.css.backgroundColor=c
}
function Widget_show(show)
{
this.css.visibility=show?_show:_hide
}
function Widget_getWidth()
{
return this.layer.offsetWidth
}
function Widget_getHeight()
{
return this.layer.offsetHeight
}
function Widget_setHTML(s)
{
var o=this
if (o.layer)
o.layer.innerHTML=s
else
o.initialHTML=s
}
function Widget_setDisplay(d)
{
this.css.display=d?"":"none"
}
function Widget_isDisplayed()
{
if(this.css.display == "none")
return false
else
return true
}
function Widget_setDisabled(d)
{
if (this.layer)
this.layer.disabled=d
}
function Widget_resize(w,h)
{
if (w!=null) this.css.width=''+(Math.max(0,w))+'px';if (h!=null) this.css.height=''+(Math.max(0,h))+'px';
}
function Widget_setTooltip(tooltip)
{
this.layer.title=tooltip
}
function Widget_initialized()
{
return this.layer!=null
}
function newGrabberWidget(id,resizeCB,x,y,w,h,isHori)
{
o=newWidget(id)
o.resizeCB=resizeCB
o.x=x
o.y=y
o.w=w
o.h=h
o.min=null
o.max=null
o.isHori=isHori
o.preloaded=new Image
o.preloaded.src=_skin+"../resizepattern.gif"
o.getHTML=GrabberWidget_getHTML
o.setMinMax=GrabberWidget_setMinMax
if (window._allGrabbers==null)
window._allGrabbers=new Array
o.index=_allGrabbers.length
_allGrabbers[o.index]=o
return o
}
function GrabberWidget_getHTML()
{
var o=this
var cr=o.isHori?_resizeW:_resizeH
var moveableCb='onselectstart="return false" ondragstart="return false" onmousedown="'+_codeWinName+'.GrabberWidget_down(event,\''+o.index+'\',this);return false;"'
var imgG=_ie?('<img onselectstart="return false" ondragstart="return false" onmousedown="'+_codeWinName+'.eventCancelBubble(event)" border="0" hspace="0" vspace="0" src="'+_skin+'../transp.gif" id="modal_'+o.id+'" style="z-index:10000;display:none;position:absolute;top:0px;left:0px;width:1px;height:1px;cursor:'+cr+'">'):('<div onselectstart="return false" ondragstart="return false" onmousedown="'+_codeWinName+'.eventCancelBubble(event)" border="0" hspace="0" vspace="0" id="modal_'+o.id+'" style="z-index:10000;display:none;position:absolute;top:0px;left:0px;width:1px;height:1px;cursor:'+cr+'"></div>')
return getBGIframe('grabIframe_'+o.id)+imgG+'<table cellpadding="0" cellspacing="0" border="0" '+moveableCb+' id="'+o.id+'" style="overflow:hidden;position:absolute;left:'+o.x+'px;top:'+o.y+'px;width:'+o.w+'px;height:'+o.h+'px;cursor:'+cr+'"><tr><td></td></table></table>'
}
function GrabberWidget_setMinMax(min,max)
{
this.min=min
this.max=max
}
function GrabberWidget_down(e,index,lyr)
{
var o=_allGrabbers[index]
window._theGrabber=o
if (o.mod==null)
{
o.mod=getLayer('modal_'+o.id)
o.iframe=newWidget('grabIframe_'+o.id)
o.iframe.init()
}
var mod=o.mod
var ifr=o.iframe
var stl=lyr.style
stl.backgroundImage='url(\''+_skin+'../resizepattern.gif\')'
o.prevZ=stl.zIndex
stl.zIndex=9999
ifr.css.zIndex=9998
mod.onmousemove=eval('_curWin.'+_codeWinName+'.GrabberWidget_move')
mod.onmouseup=eval('_curWin.'+_codeWinName+'.GrabberWidget_up')
var st=mod.style
st.width='100%'
st.height='100%'
mod.style.display="block"
o.grabStartPosx=parseInt(lyr.style.left)
o.grabStartPosy=parseInt(lyr.style.top)
o.grabStartx=eventGetX(e)
o.grabStarty=eventGetY(e)
var p=getPos(o.layer)
ifr.move(p.x,p.y)
ifr.resize(o.getWidth(),o.getHeight())
ifr.setDisplay(true)
eventCancelBubble(e)
return false
}
function GrabberWidget_move(e)
{
var o=_theGrabber,lyr=o.layer,mod=o.mod
var x=o.isHori?Math.max(0,o.grabStartPosx-o.grabStartx+eventGetX(e)):null
var y=o.isHori?null:Math.max(0,o.grabStartPosy-o.grabStarty+eventGetY(e))
if (o.isHori)
{
if (o.min!=null) x=Math.max(x,o.min)
if (o.max!=null) x=Math.min(x,o.max)
}
else
{
if (o.min!=null) y=Math.max(y,o.min)
if (o.max!=null) y=Math.min(y,o.max)
}
eventCancelBubble(e)
o.move(x,y)
getPos(o.layer)
o.iframe.move(x,y)
}
function GrabberWidget_up(e)
{
var o=_theGrabber,lyr=o.layer,mod=o.mod
var stl=lyr.style
stl.backgroundImage=''
stl.zIndex=o.prevZ
var ifr=o.iframe
ifr.move(-100,-100)
ifr.resize(1,1)
ifr.setDisplay(false)
eventCancelBubble(e)
var st=mod.style
st.display="none"
st.width='0px'
st.height='0px'
if (o.resizeCB)
o.resizeCB(parseInt(lyr.style.left),parseInt(lyr.style.top))
}
function newButtonWidget(id,label,cb,width,hlp,tooltip,tabIndex,margin,url,w,h,dx,dy,imgRight,disDx,disDy)
{
var o=newWidget(id)
o.label=label
o.cb=cb
o.width=width
o.hlp=hlp
o.tooltip=tooltip
o.tabIndex=tabIndex
o.isGray=false
o.txt=null
o.icn=null
o.margin=margin?margin:0
o.extraStyle=""
if (url)
{
o.url=url
o.w=w
o.h=h
o.dx=dx
o.dy=dy
o.disDx=(disDx!=null)?disDx:dx
o.disDy=(disDy!=null)?disDy:dy
o.imgRight=imgRight?true:false
}
o.getHTML=ButtonWidget_getHTML
o.setDisabled=ButtonWidget_setDisabled
o.setText=ButtonWidget_setText
o.changeImg=ButtonWidget_changeImg
o.oldInit=o.init
o.init=ButtonWidget_init
o.isDisabled=ButtonWidget_isDisabled
o.instIndex=ButtonWidget_currInst
ButtonWidget_inst[ButtonWidget_currInst++]=o
return o;
}
ButtonWidget_inst=new Array
ButtonWidget_currInst=0
function ButtonWidget_getHTML()
{
with (this)
{
var clk=_codeWinName+'.ButtonWidget_clickCB('+this.instIndex+');return false;"'
var clcbs= 'onclick="'+clk+'" '
if (_ie)  clcbs+= 'ondblclick="'+clk+'" '
var url1=_skin+"button.gif",addPar=' style="'+extraStyle+'cursor:'+_hand+';margin-left:'+margin+'px; margin-right:'+margin+'px; "'+clcbs+' ',tip=attr('title', tooltip),idText="theBttn"+id,bg=backImgOffset(url1,0,42),idIcon="theBttnIcon"+id
var lnkB='<a '+attr('id',idText)+' '+tip+' '+attr('tabindex',tabIndex)+' href="javascript:void(0)" class="wizbutton">'
var l=(label!=null)
var im=(this.url?('<td align="'+(l?(this.imgRight?'right':'left'):'center')+'" style="'+bg+'" width="'+(!l&&(width!=null)?width+6:w+6)+'">'+(l?'':lnkB)+simpleImgOffset(url,w,h,this.isGray?disDs:dx,this.isGray?disDy:dy,idIcon,null,(l?'':tooltip),'cursor:'+_hand)+(l?'':'</a>')+'</td>'):'')
return '<table '+attr('id',id)+' '+addPar+' border="0" cellspacing="0" cellpadding="0"><tr valign="middle">'+
'<td width="5">'+simpleImgOffset(url1,5,21,0,0)+'</td>'+
(this.imgRight?'':im)+
(l?('<td '+attr("width",width)+' align="center" class="'+(this.isGray?'wizbuttongray':'wizbutton')+'" style="padding-left:3px;padding-right:3px;'+bg+'"><nobr>'+lnkB+label+'</a></nobr></td>'):'')+
(this.imgRight?im:'')+
'<td width="5">'+simpleImgOffset(url1,5,21,0,21)+'</td></tr></table>';
}
}
function ButtonWidget_setDisabled(d)
{
var o=this,newCur=d?'default':_hand
o.isGray=d
if (o.layer)
{
o.txt.className=d?'wizbuttongray':'wizbutton'
o.txt.style.cursor=newCur
o.css.cursor=newCur
if(o.icn)
{
changeSimpleOffset(o.icn,o.isGray?o.disDx:o.dx,o.isGray?o.disDy:o.dy)
o.icn.style.cursor=newCur
}
}
}
function ButtonWidget_isDisabled()
{
return this.isGray
}
function ButtonWidget_setText(str)
{
this.txt.innerHTML=convStr(str)
}
function ButtonWidget_init()
{
var o=this
o.oldInit()
o.txt=getLayer('theBttn'+this.id)
o.icn=getLayer('theBttnIcon'+this.id)
var className=o.isGray?'wizbuttongray':'wizbutton'
if (o.txt.className!=className)
o.txt.className=className
}
function ButtonWidget_changeImg(dx,dy,disDx,disDy,url,tooltip)
{
var o=this
if (url) o.url=url
if (dx!=null) o.dx=dx
if (dy!=null) o.dy=dy
if (disDx!=null) o.disDx=disDx
if (disDy!=null) o.disDy=disDy
if (tooltip!=null) o.tooltip=tooltip
if (o.icn)
changeSimpleOffset(o.icn,o.isGray?o.disDx:o.dx,o.isGray?o.disDy:o.dy, o.url, o.tooltip)
}
function ButtonWidget_clickCB(index)
{
var btn=ButtonWidget_inst[index]
if (btn && !btn.isGray)
setTimeout("ButtonWidget_delayClickCB("+index+")",1)
}
function ButtonWidget_delayClickCB(index)
{
var btn=ButtonWidget_inst[index]
if (btn.cb)
{
if (typeof btn.cb!="string")
btn.cb()
else
eval(btn.cb)
}
}
function newScrolledZoneWidget(id,borderW,padding,w,h,bgClass)
{
var o=newWidget(id)
o.borderW=borderW
o.padding=padding
o.w=w
o.h=h
o.oldResize=o.resize
o.beginHTML=ScrolledZoneWidget_beginHTML
o.endHTML=ScrolledZoneWidget_endHTML
o.resize=ScrolledZoneWidget_resize
o.bgClass = (bgClass)? bgClass : 'insetBorder'
return o;
}
function ScrolledZoneWidget_beginHTML()
{
var w=this.w,h=this.h;
if (_moz)
{
var ofs=2*(this.borderW+this.padding)
if (w!=null) w=Math.max(0,w-ofs)
if (h!=null) h=Math.max(0,h-ofs)
}
return '<div align="left" onselectstart="return false" class="' + this.bgClass + '" id="'+this.id+'" style="border-width:'+this.borderW+'px;padding:'+this.padding+'px;'+sty("width",w?w+'px':w)+sty("height",h?h+'px':h)+'overflow:auto">'
}
function ScrolledZoneWidget_endHTML()
{
return '</div>'
}
function ScrolledZoneWidget_resize(w,h)
{
if (_moz)
{
var ofs=2*(this.borderW+this.padding)
if (w!=null) w=Math.max(0,w-ofs)
if (h!=null) h=Math.max(0,h-ofs)
}
this.oldResize(w,h)
}
function newTooltipWidget()
{
if (window._theGlobalTooltip!=null)
return window._theGlobalTooltip
var o=newWidget('theGobalTooltip')
o.getPrivateHTML=TooltipWidget_getPrivateHTML
o.init=TooltipWidget_init
o.oldShow=o.show
o.show=TooltipWidget_show
o.setPos=TooltipWidget_setPos
o.inputs=null
window._theGlobalTooltip=o
o.eventWin=_curWin
preloadImg(_skin+'../swap.gif')
return o;
}
function TooltipWidget_init()
{
// cancels the default init behaviour
}
function TooltipWidget_getPrivateHTML()
{
var o=this
return getBGIframe('tipIframe_'+o.id)+'<div class="dragTooltip" id="'+o.id+'" style="visibility:hidden;z-index:10000;position:absolute;top:0px;left:0px,visibility:'+_hide+'"></div>'+
'<img width="11" height="11" border="0" hspace="0" vspace="0" src="'+_skin+'../swap.gif" id="swap_'+o.id+'" style="position:absolute;top:0px;left:0px;display:none;z-index:10000;">'
}
function TooltipWidget_show(show,str,url,w,h,dx,dy,isHTML)
{
var o=this
oldWin=_curWin
_curWin=_tooltipWin
_curDoc=_tooltipWin.document
// object not init yet, 2 cases
if (o.layer==null)
{
o.layer=getLayer(o.id)
// another instance hasn't written it's HTML yet
if (o.layer==null)
{
targetApp(o.getPrivateHTML())
o.layer=getLayer(o.id)
}
o.css=o.layer.style
o.swapLayer=getLayer("swap_"+o.id)
o.iframe=newWidget('tipIframe_'+o.id)
o.iframe.init()
}
// hides combo boxes or restore
// if (show) o.inputs=hideAllInputs()
// else restoreAllInputs(o.inputs)
dx=dx!=null?dx:0
dy=dy!=null?dy:0
if (show)
{
var s=null
if (url)
s='<span style="margin-right:2px;width:'+w+'px;height:'+h+'px;overflow:hidden">'+img(url,null,null,'top','style="position:relative;top:-'+dy+'px;left:-'+dx+'px"')+'</span>'
o.setHTML('<table border="0" cellspacing="0" cellpadding="0"><tr valign="middle">'+(s?'<td>'+s+'</td>':'')+'<td class="dragTxt"><nobr>'+(isHTML?str:convStr(str))+'</nobr></td></tr></table>')
o.setPos()
o.oldShow(show)
o.iframe.setDisplay(true)
}
else
{
o.oldShow(false)
o.setHTML('')
o.move(0,0)
o.swapLayer.style.display="none"
o.iframe.setDisplay(false)
}
_curWin=oldWin
_curDoc=_curWin.document
}
function TooltipWidget_setPos(shift)
{
if (this.layer==null)
return
var ew=this.eventWin,x=ew.event.x+_curDoc.body.scrollLeft,y=ew.event.y+_curDoc.body.scrollTop
x+=_tooltipDx
y+=_tooltipDy
this.move(x+27,y+10);
this.iframe.move(x+27,y+10);
this.iframe.resize(this.getWidth(),this.getHeight())
var c=this.swapLayer.style;
c.display=shift?"":"none"
if (shift)
{
y+=18
x+=14
if (_moz)
{
c.left=""+x+"px"
c.top=""+y+"px"
}
else
{
c.pixelLeft=x
c.pixelTop=y
}
}
}
function newComboWidget(id,changeCB,noMargin,width,tooltip)
{
var o=newWidget(id)
o.tooltip=tooltip
o.size=1
o.getHTML=ComboWidget_getHTML
o.beginHTML=ComboWidget_beginHTML
o.endHTML=ComboWidget_endHTML
o.changeCB=changeCB
o.noMargin=noMargin
o.width=width==null?null:''+width+'px'
o.add=ComboWidget_add
o.del=ComboWidget_del
o.getSelection=ComboWidget_getSelection
o.select=ComboWidget_select
o.valueSelect=ComboWidget_valueSelect
o.getCount=ComboWidget_getCount
o.oldSetDisabled=o.setDisabled
o.setDisabled=ComboWidget_setDisabled
o.setUndefined=ComboWidget_setUndefined
o.delByID=ComboWidget_delByID
o.move=ComboWidget_move
o.findByValue=ComboWidget_findByValue
o.getValue=ComboWidget_getValue
o.isDisabled=false
o.multi=false
o.undef=false
o.undefId=o.id+"__undef"
o.disabledId=o.id+"__disabled"
return o;
}
_extrCmbS=(_moz?'font-size:12px;':'')
function ComboWidget_beginHTML()
{
return '<select '+(this.multi?'multiple':'')+' '+(this.noMargin?'style="'+sty("width",this.width)+_extrCmbS+'"':'style="'+sty("width",this.width)+'margin-left:10px;'+_extrCmbS+'"')+' class="listinputs" '+attr('onchange',_codeWinName+'.ComboWidget_changeCB(event,this)')+attr('ondblclick',_codeWinName+'.ComboWidget_dblClickCB(event,this)')+attr('onkeyup',_codeWinName+'.ComboWidget_keyUpCB(event,this)')+attr('id',this.id)+attr('name',this.id)+attr('title',this.tooltip)+'size="'+this.size+'">'
}
function ComboWidget_endHTML()
{
return '</select>'
}
function ComboWidget_getHTML(inner)
{
return this.beginHTML()+(inner?inner:'')+this.endHTML()
}
function ComboWidget_add(s,val,sel,id)
{
var e=this.layer,opt=_curDoc.createElement('option');
// Add item
if (_ie)
e.options.add(opt);
else
e.appendChild(opt);
// Set text content
if (opt.innerText!=null)
opt.innerText=s;
else
opt.innerHTML=convStr(s);
// Set other attributes
opt.value=val;
if(id!=null)
opt.id=id;
if (sel)
opt.selected=true;
return opt;
}
function ComboWidget_move(delta)
{
    var e=this.layer,i=e.selectedIndex,len=e.options.length-1,newI=i+delta
    if ((i==-1)||(newI<0)||(newI>len))
        return false
    var oldOpt = e.options[i],newOpt = e.options[newI]
    var oldVal=oldOpt.value,oldHTML=oldOpt.innerHTML,oldID=oldOpt.id,newID=newOpt.id
    oldOpt.value=newOpt.value
    oldOpt.innerHTML=newOpt.innerHTML
    newOpt.id=null
    oldOpt.id=newOpt.id
    newOpt.value=oldVal
    newOpt.innerHTML=oldHTML
    newOpt.id=oldID
    e.selectedIndex=newI
    return true
}
function ComboWidget_getSelection()
{
var e=this.layer,i=e.selectedIndex;if (i<0) return null;var ret=new Object;ret.index=i;ret.value=e.options[i].value;ret.text=e.options[i].text;return ret
}
function ComboWidget_select(i)
{
var o=this,e=o.layer,len=e.options.length
if (i==null) e.selectedIndex=-1
if ((i<0)||(i>=len))
i=len-1
if (i>=0)
e.selectedIndex=i
o.setUndefined(false)
}
function ComboWidget_valueSelect(v)
{
var o=this,e=o.layer,opts=e.options,len=opts.length
for (var i=0;i<len;i++)
{
if (opts[i].value==v)
{
// e.selectedIndex=i
    opts[i].selected=true
o.setUndefined(false)
break;
}
}
}
function ComboWidget_del(i)
{
var e=this.layer
if (i==null)
e.options.length=0
else
{
if (_ie) e.remove(i)
else e.options[i]=null
this.select(i)
}
}
function ComboWidget_changeCB(e,l)
{
var o=getWidget(l);if(o.changeCB) o.changeCB(e)
}
function ComboWidget_dblClickCB(e,l)
{
var o=getWidget(l);if(o.dblClickCB) o.dblClickCB(e)
}
function ComboWidget_keyUpCB(e,l)
{
var o=getWidget(l);if(o.keyUpCB) o.keyUpCB(e)
}
function ComboWidget_getCount()
{
return this.layer.options.length
}
function ComboWidget_delByID(id)
{
var opt=getLayer(id)
if (opt!=null)
this.del(opt.index)
opt=null
}
function ComboWidget_setDisabled(d,addEmptyElt)
{
var o=this
o.oldSetDisabled(d);
o.isDisabled=d;
if (d==true)
{
var old=getLayer(o.disabledId)
if (old==null)
o.add('','',true,o.disabledId);
else
o.layer.selectedIndex=old.index
}
else
o.delByID(o.disabledId)
}
function ComboWidget_setUndefined(u)
{
var o=this
o.undef=u;
if (u==true)
{
var old=getLayer(o.undefId)
if (old==null)
o.add('','',true,o.undefId);
else
o.layer.selectedIndex=old.index
}
else
o.delByID(o.undefId)
}
function ComboWidget_findByValue(val)
{
var o=this,e=o.layer,opts=e.options,len=opts.length
for (var i=0;i<len;i++)
{
if (opts[i].value==val)
{
var ret=new Object;
ret.index=i;
ret.value=e.options[i].value;
return ret
}
}
return null
}
function ComboWidget_getValue(i)
{
var o=this,e=o.layer,opts=e.options,len=opts.length
if(i==null || i<0 || i>len)  return null;
var ret=new Object;
ret.index=i;
ret.value=e.options[i].value;
return ret
}
function newListWidget(id,changeCB,multi,width,lines,tooltip,dblClickCB,keyUpCB)
{
var o=newComboWidget(id,changeCB,true,width,tooltip)
o.dblClickCB=dblClickCB
o.keyUpCB=keyUpCB
o.size=lines
o.multi=multi
o.getMultiSelection=ListWidget_getMultiSelection
o.setUndefined=ListWidget_setUndefined
o.isUndefined=ListWidget_isUndefined
o.change=ListWidget_change
return o;
}
function ListWidget_setUndefined(u)
{
var o=this
o.undef=u;
if (u==true)
{
o.layer.selectedIndex = -1
}
}
function ListWidget_isUndefined()
{
return (this.layer.selectedIndex == -1)
}
function ListWidget_getMultiSelection()
{
var e=this.layer,rets=new Array,len=e.options.length
for (var i=0;i<len;i++)
{
var opt=e.options[i]
if (opt.selected)
{
var ret=new Object;
ret.index=i;ret.value=opt.value;ret.text=opt.text;rets[rets.length]=ret
}
}
return rets
}
function ListWidget_change(multi,lines)
{
var o=this
if(multi!=null)
{
o.multi=multi
o.layer.multiple=multi
}
if(lines!=null)
{
o.size=lines
o.layer.size=lines
}
}
function newInfoWidget(id,title,boldTitle,text,height)
{
var o=newWidget(id)
o.title=title?title:""
o.boldTitle=boldTitle?boldTitle:""
o.text=text?text:""
o.height=(height!=null)?height:58
o.getHTML=InfoWidget_getHTML
o.setText=InfoWidget_setText
o.setTitle=InfoWidget_setTitle
o.setTitleBold=InfoWidget_setTitleBold
o.textLayer=null
return o
}
function InfoWidget_setText(text)
{
var o=this,l=o.textLayer
if (l==null)
l=o.textLayer=getLayer('infozone_'+o.id)
l.innerHTML=convStr(text,false,true)
}
function InfoWidget_setTitle(text)
{
var o=this,l=o.titleLayer
if (l==null)
l=o.titleLayer=getLayer('infotitle_'+o.id)
l.innerHTML=convStr(text)
}
function InfoWidget_setTitleBold(text)
{
var o=this,l=o.titleLayerBold
if (l==null)
l=o.titleLayerBold=getLayer('infotitlebold_'+o.id)
l.innerHTML=convStr(text)
}
function InfoWidget_getHTML()
{
var o=this
return '<div align="left" style="overflow:hidden;'+sty("width",o.width)+sty("height",o.height)+'" id="'+o.id+'">'+
'<nobr>'+img(_skin+'../help.gif',16,16,'top',null,_helpLab)+
'<span class="dialogzone" style="padding-left:5px" id="infotitle_'+o.id+'">'+convStr(o.title)+'</span><span style="padding-left:5px" class="dialogzonebold" id="infotitlebold_'+o.id+'">'+convStr(o.boldTitle)+'</span></nobr>'+
'<br>'+getSpace(1,2)+
'<div class="infozone" align="left" id="infozone_'+o.id+'" style="height:'+(o.height-21-(_moz?2:0))+'px;overflow'+(_ie?'-y':'')+':auto">'+convStr(o.text,false,true)+'</div>'+
// '<div class="infozone"><div align="left" id="infozone_'+o.id+'"
// style="height:'+(o.height-31-(_moz?2:0))+'px;overflow'+(_ie?'-y':'')+':auto">'+convStr(o.text,false,true)+'</div></div>'+
'</div>'
}
function newCheckWidget(id,text,changeCB,bold,imgUrl,imgW,imgH,bconvtext)
{
var o=newWidget(id)
o.text=text
o.convText=bconvtext
o.changeCB=changeCB
o.idCheckbox='check_'+id
o.checkbox=null
o.kind='checkbox'
o.name=o.idCheckbox
o.bold=bold
o.imgUrl=imgUrl
o.imgW=imgW
o.imgH=imgH
o.getHTML=CheckWidget_getHTML
o.setText=CheckWidget_setText
o.parentInit=Widget_init
o.init=CheckWidget_init
o.check=CheckWidget_check
o.isChecked=CheckWidget_isChecked
o.setDisabled=CheckWidget_setDisabled
o.isDisabled=CheckWidget_isDisabled
o.uncheckOthers=CheckWidget_uncheckOthers
o.isIndeterminate=CheckWidget_isIndeterminate
o.setIndeterminate=CheckWidget_setIndeterminate
o.layerClass=('dialogzone'+(o.bold?'bold':''))
o.nobr=true
return o
}
function CheckWidget_getHTML()
{
var o=this,cls=o.layerClass;
return '<table border="0" onselectstart="return false" cellspacing="0" cellpadding="0" class="'+cls+'"'+attr('id',o.id)+'>' +
'<tr valign="middle">'+
'<td><input style="margin:'+(_moz?3:0)+'px" onclick="'+_codeWinName+'.CheckWidget_changeCB(event,this)" ' +
'type="'+o.kind+'"'+attr('id',o.idCheckbox)+attr('name',o.name)+'>' +
'</td>'+
(o.imgUrl?'<td><label style="padding-left:2px" for="'+o.idCheckbox+'">'+img(o.imgUrl,o.imgW,o.imgH)+'</label></td>':'')+
'<td>'+(o.nobr?'<nobr>':'')+'<label style="padding-left:'+ (o.imgUrl?4:2) +'px" id="label_'+o.id+'" for="'+o.idCheckbox+'">'+(o.convText?convStr(o.text):o.text)+'</label>'+(o.nobr?'</nobr>':'')+'</td>'+
'</tr></table>'
}
function CheckWidget_setText(s)
{
var o=this
o.text=s
if (o.layer)
{
if (o.labelLyr==null)
o.labelLyr=getLayer('label_'+o.id)
o.labelLyr.innerHTML=o.convText?convStr(s):s
}
}
function CheckWidget_init()
{
this.parentInit()
this.checkbox=getLayer(this.idCheckbox)
}
function CheckWidget_check(c) {this.checkbox.checked=c;if(c)this.uncheckOthers()}
function CheckWidget_isChecked() {return this.checkbox.checked}
function CheckWidget_changeCB(e,l) {var o=getWidget(l);o.uncheckOthers();if(o.changeCB) o.changeCB(e)}
function CheckWidget_setDisabled(d) {this.checkbox.disabled=d;if(_moz) this.checkbox.className=(d?'dialogzone':'')}
function CheckWidget_isDisabled(){ return this.checkbox.disabled }
function CheckWidget_uncheckOthers() {}
function CheckWidget_isIndeterminate() {return this.checkbox.indeterminate}
function CheckWidget_setIndeterminate(b) {this.checkbox.indeterminate=b}
function newRadioWidget(id,group,text,changeCB,bold,imgUrl,imgW,imgH,bconvtext)
{
var o=newCheckWidget(id,text,changeCB,bold,imgUrl,imgW,imgH,bconvtext)
o.kind='radio'
o.name=group
if (_RadioWidget_groups[group]==null)
_RadioWidget_groups[group]=new Array
o.groupInstance=_RadioWidget_groups[group]
var g=o.groupInstance
o.groupIdx=g.length
g[g.length]=o
o.uncheckOthers=RadioWidget_uncheckOthers
return o;
}
var _RadioWidget_groups=new Array
function RadioWidget_uncheckOthers()
{
var g=this.groupInstance,idx=this.groupIdx,len=g.length
for (var i=0;i<len;i++)
{
if (i!=idx)
{
var c=g[i].checkbox
if(c)
c.checked=false
}
}
}
function newIconNoTextCheckWidget(id,changeCB,imgUrl,imgW,imgH,tooltip)
{
var o=newWidget(id)
o.changeCB=changeCB
o.idCheckbox='check_'+id
o.checkbox=null
o.kind='checkbox'
o.name=o.idCheckbox
o.imgUrl=imgUrl
o.imgW=imgW
o.imgH=imgH
o.tooltip=(tooltip?tooltip:"")
o.getHTML=IconNoTextCheckWidget_getHTML
o.parentInit=Widget_init
o.init=CheckWidget_init
o.check=CheckWidget_check
o.isChecked=CheckWidget_isChecked
o.setDisabled=CheckWidget_setDisabled
o.uncheckOthers=CheckWidget_uncheckOthers
return o
}
function IconNoTextCheckWidget_getHTML()
{
var o=this
return '<table border="0" onselectstart="return false" cellspacing="0" cellpadding="0" title= "'+ convStr(o.tooltip) + '" ' + attr('id',o.id) +'>' +
'<tr>'+
'<td align="center"><label style="padding-left:2px" for="'+o.idCheckbox+'">'+img(o.imgUrl,o.imgW,o.imgH, null, null, convStr(o.tooltip))+'</label></td>'+
'</tr>'+
'<tr>'+
'<td align="center"><input style="margin:'+(_moz?3:0)+'px" onclick="'+_codeWinName+'.CheckWidget_changeCB(event,this)" ' +
'type="'+o.kind+'"'+attr('id',o.idCheckbox)+attr('name',o.name)+'>' +
'</td>'+
'</tr></table>'
}
function newIconNoTextRadioWidget(id,group,changeCB,imgUrl,imgW,imgH,tooltip)
{
var tip = tooltip?tooltip:""
var o=newIconNoTextCheckWidget(id,changeCB,imgUrl,imgW,imgH,tip)
o.kind='radio'
o.name=group
if (_RadioWidget_groups[group]==null)
_RadioWidget_groups[group]=new Array
o.groupInstance=_RadioWidget_groups[group]
var g=o.groupInstance
o.groupIdx=g.length
g[g.length]=o
o.uncheckOthers=RadioWidget_uncheckOthers
return o;
}
function newTextFieldWidget(id,changeCB,maxChar,keyUpCB,enterCB,noMargin,tooltip,width,focusCB,blurCB)
{
var o=newWidget(id)
o.tooltip=tooltip
o.changeCB=changeCB
o.maxChar=maxChar
o.keyUpCB=keyUpCB
o.enterCB=enterCB
o.noMargin=noMargin
o.width=width==null?null:''+width+'px'
o.focusCB=focusCB
o.blurCB=blurCB
o.getHTML=TextFieldWidget_getHTML
o.getValue=TextFieldWidget_getValue
o.setValue=TextFieldWidget_setValue
o.intValue=TextFieldWidget_intValue
o.intPosValue=TextFieldWidget_intPosValue
o.select=TextFieldWidget_select
o.beforeChange=null
o.wInit=o.init
o.init=TextFieldWidget_init
o.oldValue=""
return o
}
function TextFieldWidget_init()
{
var o=this
o.wInit()
o.layer.value=""+o.oldValue
}
function TextFieldWidget_getHTML()
{
return '<input oncontextmenu="event.cancelBubble=true;return true" style="'+sty("width",this.width)+(_moz?'padding-left:3px;padding-right:3px':'')+'margin-left:'+(this.noMargin?0:10)+'px" onfocus="'+_codeWinName+'.TextFieldWidget_focus(this)" onblur="'+_codeWinName+'.TextFieldWidget_blur(this)" onchange="'+_codeWinName+'.TextFieldWidget_changeCB(event,this)" onkeyup="'+_codeWinName+'.TextFieldWidget_keyUpCB(event,this);return true" type="text" '+attr('maxLength',this.maxChar)+' ondragstart="event.cancelBubble=true;return true" onselectstart="event.cancelBubble=true;return true" class="textinputs" id="'+this.id+'" name="'+this.id+'"'+attr('title',this.tooltip)+' value="">'
}
function TextFieldWidget_getValue()
{
return this.layer.value
}
function TextFieldWidget_setValue(s)
{
if (this.layer)
this.layer.value=''+s
else
this.oldValue=s
}
function TextFieldWidget_changeCB(e,l)
{
var o=getWidget(l)
if(o.beforeChange)
o.beforeChange()
if(o.changeCB)
o.changeCB(e)
}
function TextFieldWidget_keyUpCB(e,l)
{
var o=getWidget(l)
if (eventGetKey(e)==13)
{
if (o.beforeChange)
o.beforeChange()
if (o.enterCB)
o.enterCB(e)
return false
}
else if(o.keyUpCB)
{
o.keyUpCB(e)
return true
}
}
function TextFieldWidget_focus(l)
{
var o=getWidget(l)
if (o.focusCB)
o.focusCB()
}
function TextFieldWidget_blur(l)
{
var o=getWidget(l)
if(o.beforeChange)
o.beforeChange()
if (o.blurCB)
o.blurCB()
}
function TextFieldWidget_intValue(nanValue)
{
var n=parseInt(this.getValue())
return isNaN(n)?nanValue:n
}
function TextFieldWidget_intPosValue(nanValue)
{
var n=this.intValue(nanValue)
return (n<0)?nanValue:n
}
function TextFieldWidget_select()
{
this.layer.select()
}
function newIntFieldWidget(id,changeCB,maxChar,keyUpCB,enterCB,noMargin,tooltip,width,customCheckCB)
{
var o=newTextFieldWidget(id,changeCB,maxChar,keyUpCB,enterCB,noMargin,tooltip,width)
o.min=-Number.MAX_VALUE
o.max=Number.MAX_VALUE
o.customCheckCB=customCheckCB // Returns a boolean
o.setMin=IntFieldWidget_setMin
o.setMax=IntFieldWidget_setMax
o.setValue=IntFieldWidget_setValue
o.beforeChange=IntFieldWidget_checkChangeCB
o.value=''
return o
}
function IntFieldWidget_setMin(min)
{
if (!isNaN(min))
this.min=min
}
function IntFieldWidget_setMax(max)
{
if (!isNaN(max))
this.max=max
}
function IntFieldWidget_setValue(s)
{
var o=this,l=o.layer
s = '' + s
if (s =='')
{
if (l)
l.value= ''
o.oldValue = ''
return
}
var n=parseInt(s)
value = ''
if (!isNaN(n) && (n >= o.min) && (n <= o.max) && ((o.customCheckCB==null) || o.customCheckCB(n))) {
 value = n
 o.oldValue = value
} else {
if (o.oldValue)
value = o.oldValue
}
if (l)
l.value= '' + value
}
function IntFieldWidget_checkChangeCB()
{
var o=this
o.setValue(o.layer.value)
}
function newFloatFieldWidget(id,changeCB,maxChar,keyUpCB,enterCB,noMargin,tooltip,width,customCheckCB)
{
var o=newIntFieldWidget(id,changeCB,maxChar,keyUpCB,enterCB,noMargin,tooltip,width)
o.setValue=FloatFieldWidget_setValue
o.customCheckCB=customCheckCB // Returns a boolean
o.setPrecision=FloatFieldWidget_setPrecision
o.toPrecision=FloatFieldWidget_toPrecision
o.setSeparator=FloatFieldWidget_setSeparator
o.beforeChange=FloatFieldWidget_checkChangeCB
o.precision=10 // default precision !!is it enough?
o.sep='.' // default separator
return o
}
function FloatFieldWidget_setValue(s)
{
var o=this,l=o.layer
s = '' + s
if (s =='')
{
if (l)
l.value= ''
o.oldValue = ''
return
}
var n=parseFloat(s)
value = ''
if (!isNaN(n) && (n >= o.min) && (n <= o.max) && ((o.customCheckCB==null) || o.customCheckCB(n))) {
value = '' + o.toPrecision(n)
o.oldValue = value
} else {
if (o.oldValue)
value = o.oldValue
}
if (l)
l.value = value
}
function FloatFieldWidget_toPrecision(n)
{
var o=this
n = '' + n
var nAr = n.split(o.sep)
if (nAr.length == 1) return nAr[0]
var dec = (nAr[1].length >= o.precision)? nAr[1].substr(0, o.precision) : nAr[1]
return nAr[0] + o.sep + dec
}
function FloatFieldWidget_checkChangeCB()
{
var o=this
o.setValue(o.layer.value)
}
function FloatFieldWidget_setPrecision(p)
{
this.precision=p
}
function FloatFieldWidget_setSeparator(s)
{
this.sep=s
}
function newTextAreaWidget(id,rows,cols,tooltip,changeCB,enterCB,cancelCB)
{
var o=newWidget(id)
o.rows=rows
o.cols=cols
o.tooltip=tooltip
o.changeCB=changeCB
o.enterCB=enterCB
o.cancelCB=cancelCB
o.getHTML=TextAreaWidget_getHTML
o.getValue=TextAreaWidget_getValue
o.setValue=TextAreaWidget_setValue
o.resize=TextAreaWidget_resize
o.wInit=o.init
o.init=TextAreaWidget_init
o.oldValue=""
return o
}
function TextAreaWidget_init()
{
var o=this
o.wInit()
o.layer.value=""+o.oldValue
}
function TextAreaWidget_getHTML()
{
return '<textarea oncontextmenu="event.cancelBubble=true;return true" id="'+this.id+'" '+attr('title',this.tooltip)+ 'rows="'+this.rows+'" cols="'+this.cols+'" class="textinputs" value="" onkeydown="return '+_codeWinName+'.TextAreaWidget_keyDownCB(event,this)" ondragstart="event.cancelBubble=true;return true" onselectstart="event.cancelBubble=true;return true" ></textarea>'
}
function TextAreaWidget_getValue()
{
return this.layer.value
}
function TextAreaWidget_setValue(s)
{
if (this.layer)
this.layer.value=''+s
else
this.oldValue=s
}
function TextAreaWidget_resize(lines,cols)
{
var o=this
if(lines && lines >0) o.layer.rows=lines
if(cols && cols>0) o.layer.cols=cols
}
function TextAreaWidget_keyDownCB(e,l)
{
var key = eventGetKey(e),o=getWidget(l)
if (key==13)// enter
{
if (o.enterCB)
o.enterCB(e)
return false
}
else if(key==27)// escape
{
if(o.cancelCB) 
return o.cancelCB(e)
else 
return true;
}
else 
{
// setTimeout to be sure that the key is writen in the textarea
// we do not use the keyup event because of rapid keyboard pression
setTimeout("TextAreaWidget_delayedChangeCB("+key+","+o.widx+")",1)
return true;
}
}
function TextAreaWidget_delayedChangeCB(key,widx)
{
var o=_widgets[widx]
if (o.changeCB)
o.changeCB(key)
}
function newFrameZoneWidget(id,w,h,reverse)
{
var o=newWidget(id)
o.w=(w!=null)?""+Math.max(0,w-10)+"px":null
o.h=(h!=null)?""+Math.max(0,h-10)+"px":null
o.reverse=(reverse!=null)?reverse:false
o.cont=null
o.beginHTML=FrameZoneWidget_beginHTML
o.endHTML=FrameZoneWidget_endHTML
o.oldResize=o.resize
o.resize=FrameZoneWidget_resize
return o
}
function FrameZoneWidget_resize(w,h)
{
var o=this
var d=o.layer.display!="none"
if (d&_moz&&!_saf)
o.setDisplay(false)
o.oldResize(w,h)
if (d&_moz&&!_saf)
o.setDisplay(true)
}
function FrameZoneWidget_beginHTML()
{
var o=this
return '<table  style="'+sty("width",o.w)+sty("height",o.h)+'" id="'+o.id+'" cellspacing="0" cellpadding="0" border="0"><tbody>'+
'<tr height="5">'+
'<td width="5">'+imgOffset(_skin+'dialogframe.gif',5,5,o.reverse?10:0,0)+'</td>'+
'<td style="'+backImgOffset(_skin+"dialogframetopbottom.gif",0,o.reverse?10:0)+'"></td>'+
'<td width="5">'+imgOffset(_skin+'dialogframe.gif',5,5,o.reverse?15:5,0)+'</td>'+
'</tr>'+
'<tr><td style="'+backImgOffset(_skin+"dialogframeleftright.gif",o.reverse?10:0,0)+'"></td><td valign="top" class="dialogzone" id="frame_cont_'+o.id+'">'
}
function FrameZoneWidget_endHTML()
{
var o=this
return '</td><td style="'+backImgOffset(_skin+"dialogframeleftright.gif",o.reverse?15:5,0)+'"></td></tr>'+
'<tr height="5">'+
'<td>'+imgOffset(_skin+'dialogframe.gif',5,5,o.reverse?10:0,5)+'</td>'+
'<td style="'+backImgOffset(_skin+"dialogframetopbottom.gif",0,o.reverse?15:5)+'"></td>'+
'<td>'+imgOffset(_skin+'dialogframe.gif',5,5,o.reverse?15:5,5)+'</td>'+
'</tr>'+
'</tbody></table>'
}
function newBOColor(r,g,b)
{
var o=new Object;
if (r && (g==null) && (b==null))
{
s = r.split(",")
o.r=parseInt(s[0])
o.g=parseInt(s[1])
o.b=parseInt(s[2])
}
else
{
o.r=r
o.g=g
o.b=b
}
o.set=BOColor_set
o.getCopy=BOColor_getCopy
o.getStringDef=BOColor_getStringDef
o.getStyleColor=BOColor_getStyleColor
return o;
}
function BOColor_getStringDef()
{
var o=this
return ""+o.r+","+o.g+","+o.b
}
function BOColor_getCopy()
{
var f=this
var o=newBOColor(f.r,f.g,f.b)
return o;
}
function BOColor_set(r,g,b)
{
this.r=r
this.g=g
this.b=b
}
function BOColor_getStyleColor()
{
var o=this
// if undefined
if (o.r == -1 || o.r == null) return null;
if (o.g == -1 || o.g == null) return null;
if (o.b == -1 || o.b == null) return null;
return "rgb(" + o.r + "," + o.g + "," + o.b + ")"
}
function newDragDropData(widget,dragStartCB,dragCB,dragEndCB,acceptDropCB,leaveDropCB,dropCB)
{
var o=new Object
o.widget=widget
o.dragStartCB=dragStartCB
o.dragCB=dragCB
o.dragEndCB=dragEndCB
o.acceptDropCB=acceptDropCB
o.leaveDropCB=leaveDropCB
o.dropCB=dropCB
o.attachCallbacks=DragDropData_attachCallbacks
o.id=_ddData.length
_ddData[o.id]=o
return o
}
function DragDropData_attachCallbacks(lyr,onlyDrop)
{
if (_ie)
{
onlyDrop=(onlyDrop==null)?false:onlyDrop
if (!onlyDrop)
{
lyr.ondragstart=DDD_dragStart
lyr.ondragend=DDD_dragEnd
}
lyr.ondrop=DDD_drop
lyr.ondragleave=DDD_dragLeave
lyr.ondragover=DDD_dragOver
lyr.ondrag=DDD_drag
lyr._dragDropData=this.id
}
}
function DDD_dragStart()
{
var e=_curWin.event,dt=e.dataTransfer
dt.effectAllowed='copyMove'
dt.dropEffect=_curWin.event.ctrlKey?'copy':'move'
var o=_ddData[this._dragDropData]
o.dragStartCB(o.widget,this)
window._globalDDD=o.widget
e.cancelBubble=true
}
function DDD_drag()
{
var e=_curWin.event,dt=e.dataTransfer
dt.dropEffect=e.ctrlKey?'copy':'move'
var o=_ddData[this._dragDropData]
o.dragCB(o.widget,this,e.ctrlKey?false:e.shiftKey)
e.cancelBubble=true
}
function DDD_dragEnd()
{
var o=_ddData[this._dragDropData]
o.dragEndCB(o.widget,this)
window._globalDDD=null
}
function DDD_dragEnter()
{
DDD_dragOverEnter(this,true)
}
function DDD_dragOver()
{
DDD_dragOverEnter(this,false)
}
function DDD_dragOverEnter(layer,isEnter)
{
var o=_ddData[layer._dragDropData],e=_curWin.event
e.dataTransfer.dropEffect=e.ctrlKey?'copy':'move'
var o=_ddData[layer._dragDropData];
if (o.acceptDropCB(window._globalDDD,o.widget,e.ctrlKey,e.ctrlKey?false:e.shiftKey,layer,isEnter))
e.returnValue=false
e.cancelBubble=true
}
function DDD_dragLeave()
{
var o=_ddData[this._dragDropData],e=_curWin.event
o.leaveDropCB(window._globalDDD,o.widget,e.ctrlKey,e.ctrlKey?false:e.shiftKey,this)
}
function DDD_drop()
{
var o=_ddData[this._dragDropData],e=_curWin.event
o.dropCB(window._globalDDD,o.widget,e.ctrlKey,e.ctrlKey?false:e.shiftKey,this)
window._globalDDD=null
e.cancelBubble=true
}
function arrayAdd(obj,fieldName,item,idx)
{
var array=obj[fieldName],len=array.length
if ((idx==null)||(typeof idx!="number")) idx=-1
if ((idx<0)||(idx>len)) idx=len
if  (idx!=len)
{
var end=array.slice(idx)
array.length=idx+1
array[idx]=item
array=array.concat(end)
}
else array[idx]=item
obj[fieldName]=array
return idx
}
function arrayRemove(obj,fieldName,idx)
{
var array=obj[fieldName],last=array.length-1
if (idx==null)
{
array.length=0
obj[fieldName]=array
return -1
}
if ((idx<0)||(idx>last)) return -1
if (idx==last) array.length=last
else
{
var end=array.slice(idx+1)
array.length=idx
array=array.concat(end)
}
obj[fieldName]=array
return idx
}
function arrayMove(obj,fieldName,i,j)
{
var array=obj[fieldName],len=array.length
if ((i<0)||(i>=len)||(j<0)||(j>=len)) return false
var old=array[i]
arrayRemove(obj,fieldName,i)
arrayAdd(obj,fieldName,old,j)
return true;
}
function arrayGetCopy(arr)
{
var o=new Array,len=arr.length;
for (var i=0;i<len;i++)
o[i]=arr[i]
return o;
}
function arrayFind(obj,fieldName,v,subfield)
{
var array=obj[fieldName],len=array.length;
for (var i=0;i<len;i++)
{
if(subfield)
{
if (array[i][subfield] == v) return i;
}
else 
if(array[i]==v) return i;
}
return -1;
}
function getFrame(name,par)
{
if (par==null) par=self
var frames=par.frames,w=eval("frames."+name)
if (w==null) return w
var l=frames.length
for (var i=0;i<l;i++)
{
w=frames[i]
try {
if (w.name==name)
return w
} catch (exc) {
// keep on
}
}
return null
}
function frameNav(name,url,fillHistory,par,noRefreshDrillBar)
{
var fr=null
if (noRefreshDrillBar & name=="Report")
{
var topfs=getTopFrameset();
fr=topfs.getReportFrame()
} else {
fr=getFrame(name,par)
}
if (fr) {
var l=fr.location
if (fillHistory)
l.href=url
else
l.replace(url)
} else {
var lay = document.getElementById(name)
if (lay)
lay.src=url;
}
}
function frameGetUrl(win)
{
return win.location.href
}
function frameReload(win)
{
var loc=win.location
loc.replace(loc.href)
}
function setTopFrameset()
{
_curWin._topfs="topfs"
}
function getTopFrameset(f)
{
if(f == null)
f = self
if(f._topfs=="topfs")
{
return f;
}
else
{
if(f!= top)
return getTopFrameset(f.parent)
else
return null;
}
}
function convStr(s,nbsp,br)
{
s=""+s
var ret=s.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/"/g,"&quot;")
if (nbsp)
ret=ret.replace(/ /g,"&nbsp;")
if (br)
ret=ret.replace(/\n/g,"<br>")
return ret
}
function escapeCR(s)
{
s=""+s
var ret=s.replace(/\r/g,"").replace(/\n/g,"\\n");
return ret
}
function addDblClickCB(l,cb)
{
if (l.addEventListener)
  l.addEventListener("dblclick",cb,false)
else
  l.ondblclick=cb
}
function img(src,w,h,align,att,alt)
{
att=(att?att:'')
if (alt==null) alt=''
return '<img'+attr('width',w)+attr('height',h)+attr('src', src)+attr(_ie?'alt':'title',alt)+attr("align", align)+' border="0" hspace="0" vspace="0" '+(att?att:'')+'>'
}
function imgOffset(url,w,h,dx,dy,id,att,alt,st,align)
{
return img(_skin+'../transp.gif',w,h,align,
(att?att:'') +' '+attr('id',id)+' style="'+backImgOffset(url,dx,dy)+(st?st:'')+'"',
alt)
}
function simpleImgOffset(url,w,h,dx,dy,id,att,alt,st,align)
{
if (_ie)
{
if (dx==null) dx=0
if (dy==null) dy=0
return '<span '+(att?att:'')+' '+attr("id",id)+' style="padding:0px;width:'+w+'px;height:'+h+'px;overflow:hidden;'+(st?st:'')+'">'+img(url,null,null,'top','style="margin:0px;position:relative;top:'+(-dy)+'px;left:'+(-dx)+'px"',alt)+'</span>'
}
else
return imgOffset(url,w,h,dx,dy,id,att,alt,st,align)
}
function changeSimpleOffset(lyr,dx,dy,url,alt)
{
if (_ie)
{
lyr=lyr.childNodes[0]
var st=lyr.style
if ((url!=null)&&(url!=lyr.src))
lyr.src=url
if (dx!=null)
st.left=""+(-dx)+"px"
if (dy!=null)
st.top=""+(-dy)+"px"
if (alt!=null)
lyr.alt=alt
}
else
changeOffset(lyr,dx,dy,url,alt)
}
function backImgOffset(url,dx,dy)
{
return 'background-image:url(\''+url+'\');background-position:'+(-dx)+'px '+(-dy)+'px;'
}
function changeOffset(lyr,dx,dy,url,alt)
{
var st=lyr.style
if (st)
{
if ((dx!=null)&&(dy!=null))
st.backgroundPosition=''+(-dx)+'px '+(-dy)+'px'
if (url)
st.backgroundImage='url(\''+url+'\')'
}
if(alt) lyr.alt=alt
}
function includeScript(url)
{
document.write('<scr'+'ipt language="javascript" charset="UTF-8" src="'+url+'"><\/scr'+'ipt>')
}
function includeCSS(css,noskin)
{
var url=""
if( noskin )
url=_skin+'../'+css
else
url=_skin+css
url+='.css'
_curDoc.write('<li'+'nk rel="stylesheet" type="text/css" href="'+url+'">')
}
function setLayerTransp(lyr,percent)
{
if (_ie)
lyr.style.filter=(percent==null) ? "" :  "progid:DXImageTransform.Microsoft.Alpha( style=0,opacity="+percent+")"
else
lyr.style.MozOpacity=(percent==null) ? 1 : percent/100
}
function getLayer(id)
{
return _curDoc.getElementById(id)
}
function getPos(el,relTo)
{
relTo = relTo?relTo:null
for (var lx=0,ly=0;(el!=null)&&(el!=relTo);
lx+=el.offsetLeft,ly+=el.offsetTop,el=el.offsetParent);
return {x:lx,y:ly}
}
function getWidget(layer)
{
if (layer==null)
return null
var w=layer._widget
if (w!=null)
return _widgets[w]
else
return getWidget(layer.parentNode)
}
function getWidgetFromID(id)
{
if (id==null)
return null
var l=getLayer(id)
return getWidget(l)
}
function attr(key,val)
{
return (val!=null?' '+key+'="'+val+'" ':'')
}
function sty(key,val)
{
return (val!=null?key+':'+val+';' :'')
}
function getSep(marg,solid)
{
if (marg==null)marg=0;var spc=marg>0?'<td width="'+marg+'">'+getSpace(marg,1)+'</td>':''; return '<table style="margin-top:5px;margin-bottom:5px;" width="100%" cellspacing="0" cellpadding="0"><tr>'+spc+'<td background="'+_skin+'sep'+(solid?'_solid':'')+'.gif" class="smalltxt"><img alt="" src="'+_skin+'../transp.gif" width="10" height="2"></td>'+spc+'</tr></table>'
}
function writeSep(marg,solid)
{
_curDoc.write(getSep(marg,solid))
}
function getSpace(w,h)
{
return '<table height="'+h+'" border="0" cellspacing="0" cellpadding="0"><tr><td>'+img(_skin+'../transp.gif',w,h)+'</td></tr></table>'
}
function writeSpace(w,h)
{
_curDoc.write(getSpace(w,h))
}
function winWidth(win)
{
win=win?win:_curWin
return _ie?win.document.body.clientWidth:win.innerWidth
}
function winHeight(win)
{
win=win?win:_curWin
return _ie?win.document.body.clientHeight:win.innerHeight
}
function getScrollX(win)
{
win=win?win:_curWin
return _ie?win.document.body.scrollLeft:win.scrollX
}
function getScrollY(win)
{
win=win?win:_curWin
return _ie?win.document.body.scrollTop:win.scrollY
}
function winScrollTo(x, y, win)
{
win=win?win:_curWin
win.scrollTo(x,y)
}
function eventGetKey(e,win)
{
win=win?win:_curWin
return _ie?win.event.keyCode:e.keyCode
}
function eventGetX(e)
{
return _ie?_curWin.event.clientX:e.pageX
}
function eventGetY(e)
{
return _ie?_curWin.event.clientY:e.pageY
}
function xpos(o,e,doc,zoom)
{
if ((zoom==null)||(!_ie))
zoom=1
return ((e.clientX/zoom)-getPos(o).x)+(_ie?doc.body.scrollLeft:0)
}
function ypos(o,e,doc,zoom)
{
if ((zoom==null)||(!_ie))
zoom=1
return ((e.clientY/zoom)-getPos(o).y)+(_ie?doc.body.scrollTop:0)
}
function absxpos(e,zoom)
{
if ((zoom==null)||(!_ie)) {
return e.clientX
} else {
return e.clientX/zoom
}
}
function absypos(e,zoom)
{
if ((zoom==null)||(!_ie)) {
return e.clientY
} else {
return e.clientY/zoom
}
}
function eventCancelBubble(e,win)
{
win=win?win:_curWin
_ie?win.event.cancelBubble=true:e.cancelBubble=true
}
function isHidden(lyr)
{
if ((lyr==null)||(lyr.tagName=="BODY")) return false;var sty=lyr.style;if ((sty==null)||(sty.visibility==_hide)||(sty.display=='none')) return true;return isHidden(lyr.parentNode)
}
function opt(val,txt,sel)
{
return '<option value="'+val+'" '+(sel?'selected':'')+'>'+convStr(''+txt)+'</option>'
}
function lnk(inner,clickCB,cls,id,att,dblClickCB)
{
if (clickCB==null)
clickCB="return false"
att=att?att:'';return '<a'+attr('class',cls)+attr('id',id)+attr('href','javascript:void(0)')+attr('onclick',clickCB)+attr('ondblclick',dblClickCB)+att+'>'+inner +'</a>'
}
_oldErrHandler=null
function localErrHandler()
{
return true
}
function canScanFrames(w)
{
var ex=true,d=null
if (_moz)
{
_oldErrHandler=window.onerror
window.onerror=localErrHandler
}
try
{
d=w.document
ex=false
}
catch(expt)
{
}
if (_moz)
window.onerror=_oldErrHandler
return (!ex&&(d!=null))
}
function restoreAllInputs(win,level)
{
if (_ie&&_curWin._inptStackLevel!=null)
{
win=win?win:_curWin
if (canScanFrames(win))
{
if (level==null)
level=--_curWin._inptStackLevel
var b=win.document.body,arr=b?b.getElementsByTagName("SELECT"):null,len=arr?arr.length:0
for (var i=0;i<len;i++)
{
var inpt=arr[i]
if (inpt._boHideLevel==level)
{
inpt.style.visibility=inpt._boOldVis
inpt._boHideLevel=null
}
}
var frames=win.frames,flen=frames.length
for (var k=0;k<flen;k++)
restoreAllInputs(frames[k],level)
}
}
}
function hideAllInputs(x,y,w,h,win,level)
{
if (_ie)
{
win=win?win:_curWin
if (canScanFrames(win))
{
var b=win.document.body,arr=b?b.getElementsByTagName("SELECT"):null,len=arr?arr.length:0
if (level==null)
{
if (_curWin._inptStackLevel==null)
_curWin._inptStackLevel=0
level=_curWin._inptStackLevel++
}
for (var i=0;i<len;i++)
{
var inpt=arr[i],css=inpt.style;
var inter=(x==null)||isLayerIntersectRect(inpt,x,y,w,h)
if (!isHidden(inpt)&&inter)
{
inpt._boHideLevel=level
inpt._boOldVis=css.visibility
css.visibility=_hide
}
}
var frames=win.frames,flen=frames.length
for (var k=0;k<flen;k++)
hideAllInputs(null,null,null,null,frames[k],level)
}
}
}
function getBGIframe(id)
{
return '<iframe id="'+id+'" style="display:none;left:0px;position:absolute;top:0px" src="' + _skin + '../transp.gif' + '" frameBorder="0" scrolling="no"></iframe>'
}
function append(e,s,c)
{
if (_ie)
e.insertAdjacentHTML("BeforeEnd",s)
else
{
var curDoc = c?c:_curDoc
var r=curDoc.createRange()
r.setStartBefore(e)
var frag=r.createContextualFragment(s)
e.appendChild(frag)
}
}
function insBefore(e,s,c)
{
if (_ie)
e.insertAdjacentHTML("BeforeBegin",s)
else
{
var curDoc = c?c:_curDoc
var r=_curDoc.createRange()
r.setEndBefore(e)
var frag=r.createContextualFragment(s)
e.parentNode.insertBefore(frag,e)
}
}
function targetApp(s)
{
append(_curDoc.body,s)
}
function getBasePath()
{
var url=document.location.href,last1= url.lastIndexOf('?');if (last1>=0) url=url.slice(0,last1);var last = url.lastIndexOf('/');return (last>=0)?url.slice(0,last+1):url
}
function isLayerIntersectRect(l,x1,y1,w,h)
{
var xl1=getPos(l).x,yl1=getPos(l).y,xl2=xl1+l.offsetWidth,yl2=yl1+l.offsetHeight,x2=x1+w,y2=y1+h
return ((x1>xl1)||(x2>xl1))&&((x1<xl2)||(x2<xl2)) && ((y1>yl1)||(y2>yl1))&&((y1<yl2)||(y2<yl2))
}
function preloadImg(url)
{
var img=_preloadArr[_preloadArr.length]=new Image;img.src=url
}
function convURL(str)
{
if (_dontNeedEncoding == null)
{
_dontNeedEncoding = new Array(256);
for (var i = 0 ; i < 256 ; i++) _dontNeedEncoding[i] = false;
for (var i = (new String('a')).charCodeAt(0); i <= (new String('z')).charCodeAt(0); i++) _dontNeedEncoding[i] = true;
for (var i = (new String('A')).charCodeAt(0); i <= (new String('Z')).charCodeAt(0); i++) _dontNeedEncoding[i] = true;
for (var i = (new String('0')).charCodeAt(0); i <= (new String('9')).charCodeAt(0); i++) _dontNeedEncoding[i] = true;
_dontNeedEncoding[(new String(' ')).charCodeAt(0)] = true; 
_dontNeedEncoding[(new String('-')).charCodeAt(0)] = true;
_dontNeedEncoding[(new String('_')).charCodeAt(0)] = true;
_dontNeedEncoding[(new String('.')).charCodeAt(0)] = true;
_dontNeedEncoding[(new String('*')).charCodeAt(0)] = true;
_thex = new Array("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F");
}
var encstr = "";
for (var i = 0 ; i < str.length ; i++) encstr += URLEncodeUTF8Char(str.charAt(i));
return encstr;
}
function URLEncodeUTF8Char(c)
{
var unicodeval = c.charCodeAt(0);
if (unicodeval < 128)
{
if (_dontNeedEncoding[unicodeval]) return (c == ' ' ? '+' : c);
else return ("%" + _thex[unicodeval >> 4] + _thex[unicodeval & 15]);
}
else if (unicodeval < 2048)
{
return ("%" + _thex[(unicodeval >> 10) | 12]
+ _thex[(unicodeval >> 6) & 15]
+ "%"+ _thex[(unicodeval >> 4) & 3 | 8]
+ _thex[unicodeval & 15]);
}
else
{
return ("%"+ _thex[14]
+ _thex[unicodeval >> 12]
+ "%" + _thex[(unicodeval >> 10) & 3 | 8]
+ _thex[(unicodeval >> 6) & 15]
+ "%"+ _thex[(unicodeval >> 4) & 3 | 8]
+ _thex[unicodeval & 15]);
}
}
function encString(s)
{
var res=''
if (s!=null)
{
var len=s.length
for (var i=0;i<len;i++)
{
var c=s.charAt(i)
switch (c)
{
case '$': res+='$3'; break
case ',': res+='$4'; break
case '[': res+='$5'; break
case ']': res+='$6'; break
default: res+=c; break
}
}
}
return res
}
function enc()
{
var args=enc.arguments,len=args.length,s='['
if (len>0) s+=args[0]
for (var i=1;i<len;i++) s+=','+args[i]
return s+']'
}
function remSpaceAround(s)
{
var len = s.length;
if(len<=0) return "";
var start=0,end=len
var c=s.substr(start,1);
while (c==' ' && start<len)
{
start++
c=s.substr(start,1);
}
if(start<len)
{
c=s.substr(end-1,1);
while (c==' ')
{
end--
c=s.substr(end-1,1);
}
}
var sub = s.substring(start,end);
return sub
}
function getArrows(upCb,downCB,hori,newNode,newNodeCB)
{
if (hori==null) hori=false;
var s=''
if (hori) s+='<nobr>'
s+=lnk(img(_skin+(hori?'left.gif':'up.gif'),12,12,'top',null,hori?"_LEFT ARROW":"_UP ARROW"),upCb,null,null,null,(_moz?null:upCb))
s+=(hori?'':img(_skin+'../transp.gif',1,5)+'<br>')
s+=lnk(img(_skin+(hori?'right.gif':'down.gif'),hori?11:12,hori?12:11,'top',null,hori?"_RIGHT ARROW":"_LEFT ARROW"),downCB,null,null,null,(_moz?null:downCB))
if (newNode)
{
s+=img(_skin+'../transp.gif',1,5)+'<br>'
s+=lnk(img(_skin+('node.gif'),12,12,'top',null,"_NEW NODE"),newNodeCB,null,null,null,(_moz?null:newNodeCB))
}
if (hori) s+='</nobr>'
return s
}
function initTooltipWin(tooltipWin)
{
_tooltipWin=tooltipWin
}
function setTooltipOffset(dx,dy)
{
_tooltipDx=dx
_tooltipDy=dy
}
_staticUnicBlockWhileWaitWidgetID = "staticUnicBlockWhileWaitWidgetID"
function hideBlockWhileWaitWidget()
{
var lyr=getLayer(_staticUnicBlockWhileWaitWidgetID)
if (lyr)
lyr.style.display="none"
}
function newBlockWhileWaitWidget(urlImg)
{
if (window._BlockWhileWaitWidget!=null)
return window._BlockWhileWaitWidget
var o=newWidget(_staticUnicBlockWhileWaitWidgetID)
o.getPrivateHTML=BlockWhileWaitWidget_getPrivateHTML
o.init=BlockWhileWaitWidget_init
o.show=BlockWhileWaitWidget_show
window._BlockWhileWaitWidget=o
return o
}
function BlockWhileWaitWidget_init()
{
}
function BlockWhileWaitWidget_getPrivateHTML()
{
return '<div id="'+ this.id+'" onselectstart="return false" ondragstart="return false" onmousedown="'+_codeWinName+'.eventCancelBubble(event)" border="0" hspace="0" vspace="0"  style="background-image:url('+_skin+'../transp.gif);z-index:5000;cursor:wait;position:absolute;top:0px;left:0px;width:100%;height:100%"></div>'
}
function BlockWhileWaitWidget_show(show)
{
var o=this
if (o.layer==null)
{
o.layer=getLayer(o.id)
if (o.layer==null)
{
targetApp(o.getPrivateHTML())
o.layer=getLayer(o.id)
o.css=o.layer.style
}
else
{
o.css=o.layer.style
}
}
o.setDisplay(show)
}
var regLang= /^[a-zA-Z]{2}$|^[a-zA-Z]{2}_[a-zA-Z]{2}$/, regIntPos=/^\d+$/, regYes=/^yes$/, regPath=/^[\w|\/|:|.|-]+$/, regAlphanumDot=/^\w+\.+\w+$/, regAplhaNumOptionalDot = /^\w+\.*\w*/;
var paramRegs = new Array()
paramRegs["ID"]=regAplhaNumOptionalDot
paramRegs["allTableCells"]=regYes
paramRegs["gotoPivot"]=regYes
paramRegs["reportIndex"]=regIntPos
paramRegs["fromLPane"]=regYes
paramRegs["skin"]=regPath
paramRegs["lang"]=regLang
paramRegs["noGrabber"]=regYes
paramRegs["isFormulaEdit"]=regYes
function requestQueryString(win, par){
params = win.location.search.substr(1).split("&")
for(i=0;i<params.length;i++){
var param = params[i].split("="), key = param[0], val = param[1]
if (key == par){
var reg = new RegExp(paramRegs[key])
if ((val == "") || (reg.test(val))) {
return val
} else {
var tpfs = getTopFrameset()
if (tpfs != null) {
tpfs._dontCloseDoc=true
tpfs.document.location.replace(tpfs._root+"html/badparamserror.html");
} else {
tpfs=getTopFrameset(window.opener)
if (tpfs != null)
{
document.location.replace(tpfs._skin+"../../../html/badparamserror.html");
}
}
}
}
}
}
function trim(strString) {
    if (strString != null) {
        var iLength = strString.length;
        var i;
        for (i=0; i<iLength; i++) {
            if (strString.charAt(i) != " ") {
                break;
            }
        }
        strString = strString.substring(i);
        iLength = strString.length;
        for (i=iLength; i>0; i--) {
            if (strString.charAt(i-1) != " ") {
                break;
            }
        }
        strString = strString.substring(0,i);
    }
    return strString;
}
function startsWithIgnoreCase(strString, strToFind) {
    var blnRet = false;
    if (strToFind != null) {
        var strVar = strString.substring(0, strToFind.length);
        if (strVar.toLowerCase() == strToFind.toLowerCase()) {
            blnRet = true;
        }
    }
    return blnRet;
}
function endsWithIgnoreCase(strString, strToFind) {
    var blnRet = false;
    if (strToFind != null) {
        var iRight = strString.length- strToFind.length;
        if (iRight >= 0) {
            var strVar = strString.substring(iRight);
            if (strVar.toLowerCase() == strToFind.toLowerCase()) {
                blnRet = true;
            }
        }
    }
    return blnRet;
}
function isTextInput(ev)
{
var source = _ie?ev.srcElement:ev.target;
var isText=false;
if(source.tagName=="TEXTAREA")
isText=true
if((source.tagName=="INPUT") && (source.type.toLowerCase()=="text"))
isText=true
return isText;
}
function shrinkTooltip(t,n)
{ 
var n = n?n:360
return (t.length < n)? t : (t.substring(0,n) + "...")
}
