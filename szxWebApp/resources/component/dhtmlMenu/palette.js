_allBOIcons=new Array
_allBOIconsMenus=new Array
function NewLabelWidget(id,text,convBlanks)
{
var o=newWidget(id)
o.text=text
o.convBlanks=convBlanks
o.getHTML=LabelWidget_getHTML
o.setDisabled=LabelWidget_setDisabled
o.dis=false
return o;
}
function LabelWidget_setDisabled(dis)
{
var o=this
if (o.dis!=dis)
{
o.dis=dis
if (o.layer)
{
o.layer.className="iconText"+(dis?"Dis":"")
}
}
}
function LabelWidget_getHTML()
{
var o=this
return '<div id="'+o.id+'" class="icontext'+(o.dis?"Dis":"")+'" style="white-space:nowrap;margin-right:4px;margin-left:4px;cursor:default">'+convStr(o.text,o.convBlanks)+'</div>'
}
function newIconWidget(id,src,clickCB,text,alt,w,h,dx,dy,disDx,disDy)
{
var o=newWidget(id)
o.src=src
o.clickCB=clickCB
o.text=text
o.alt=alt
o.width=null
o.txtAlign="left"
o.border=4
o.txtNoPadding=false
o.allowDblClick=false
if (src)
{
o.w=(w!=null)?w:16
o.h=(h!=null)?h:16
o.dx=(dx!=null)?dx:0
o.dy=(dy!=null)?dy:0
o.disDx=(disDx!=null)?disDx:0
o.disDy=(disDy!=null)?disDy:0
}
else
{
o.w=1
o.h=16
}
o.dis=false
o.margin=1
o.extraHTML=''
o.imgLayer=null
o.txtLayer=null
o.overCB="IconWidget_overCB"
o.outCB="IconWidget_outCB"
o.getHTML=IconWidget_getHTML
o.getTxtWidth=IconWidget_getTxtWidth
o.index=_allBOIcons.length++
o.nocheckClass="iconnocheck"
o.hoverClass="iconhover"
o.checkClass="iconcheck"
o.checkhoverClass="iconcheckhover"
o.currentClass=o.nocheckClass
o.currentHoverClass=o.hoverClass
o.setClasses=IconWidget_setClasses
o.internalUpCB=null
o.internalDownCB=IconWidget_internalDownCB
o.internalUpCB=IconWidget_internalUpCB
o.isHover=false
o.changeTooltip=IconWidget_changeTooltip
o.changeText=IconWidget_changeText
o.changeImg=IconWidget_changeImg
o.setDisabled=IconWidget_setDisabled
o.isDisabled=IconWidget_isDisabled
_allBOIcons[o.index]=o
o.outEnable=true
o.oldRes=o.resize
o.resize=IconWidget_resize
o.iconOldInit=o.init
o.init=IconWidget_init
return o
}
function newIconMenuWidget(id,src,clickCB,text,alt,w,h,dx,dy,disDx,disDy,isColor,beforeShowCB)
{
var o=newWidget(id)
o.menuItemType = isColor? _isColor:_isNotColor
o.icon=newIconWidget("iconMenu_icon_"+id,src,IconMenuWidget_iconClickCB,text,alt,w,h,dx,dy,disDx,disDy)
o.arrow=newIconWidget("iconMenu_arrow_"+id,_skin+"menus.gif",IconMenuWidget_arrowClickCB,null,(_openMenuPart1+(text?text:(alt?alt:""))+_openMenuPart2),7,16,0,81,0,97)
o.menu=isColor? newMenuColorWidget("iconMenu_menu_"+id,IconMenuWidget_hideCB) : newMenuWidget("iconMenu_menu_"+id,IconMenuWidget_hideCB,beforeShowCB)
o.icon.par=o
o.arrow.par=o
o.menu.parIcon=o
o.icon.margin=0
o.arrow.margin=0
o.icon.overCB="IconWidget_none"
o.icon.outCB="IconWidget_none"
o.arrow.overCB="IconWidget_none"
o.arrow.outCB="IconWidget_none"
o.margin=1
o.spc=0
o.getHTML=IconMenuWidget_getHTML
o.clickCB=clickCB
o.getMenu=IconMenuWidget_getMenu
o.menIcnOldInit=o.init
o.init=IconMenuWidget_init
o.index=_allBOIconsMenus.length++
_allBOIconsMenus[o.index]=o
o.setDisabled=IconMenuWidget_setDisabled
o.isDisabled=IconMenuWidget_isDisabled
o.disableMenu=IconMenuWidget_disableMenu
o.changeText=IconMenuWidget_changeText
o.imwpResize=o.resize
o.resize=IconMenuWidget_resize
o.focus=IconMenuWidget_focus
o.changeArrowTooltip=IconMenuWidget_changeArrowTooltip
return o
}
function IconMenuWidget_changeText(s)
{
this.icon.changeText(s)
}
function IconMenuWidget_changeArrowTooltip(tooltip)
{
this.arrow.changeTooltip(tooltip,false);
}
function IconMenuWidget_resize(w,h)
{
var o=this
if (w!=null)
w=Math.max(0,w-2*o.margin)
var d=o.layer.display!="none"
if (d&_moz&&!_saf)
o.setDisplay(false)
o.imwpResize(w,h)
if (w!=null)
o.icon.resize(Math.max(0,w-13-o.spc))
if (d&_moz&&!_saf)
o.setDisplay(true)
}
function IconMenuWidget_setDisabled(dis)
{
var o=this
if (dis) {
if (o.menu.isShown()) {
o.menu.show(false)
}
IconMenuWidgetOutCB(o.index)
}
o.icon.setDisabled(dis)
o.arrow.setDisabled(dis)
}
function IconMenuWidget_isDisabled()
{
return (this.icon.dis == true)
}
function IconMenuWidget_internalCB()
{
var o=this,col=null
if (o.id!=null)
{
col = (o.menuItemType != _isLastUsedColor)? o.id.slice(6) : o.color 
}
var icon=o.par.parIcon
icon.oldColor=icon.curColor
icon.curColor=col
if (icon.curColor!=null)
icon.showSample()
if (icon.clickColor)
icon.clickColor()
}
function IconMenuWidget_focus()
{
var o=this
o.arrow.focus()
}
function IconMenuWidget_disableMenu(b)
{
var o=this
o.arrow.setDisabled(b)
o.menu.setDisabled(b)
}
function IconMenuWidget_getMenu()
{
return this.menu
}
function IconWidget_none()
{
}
function IconMenuWidget_init()
{
var o=this
o.menIcnOldInit()
o.icon.init()
o.arrow.init()
o.menu.init()
var l=o.layer
l.onmouseover=IconMenuWidget_OverCB
l.onmouseout=IconMenuWidget_OutCB
}
function IconMenuWidget_getHTML()
{
var o=this
return '<table id="'+o.id+'" cellspacing="0" cellpadding="0" border="0" style="margin:'+o.margin+'px"><tr><td>'+o.icon.getHTML()+'</td><td style="padding-left:'+o.spc+'px" width="'+(13+o.spc)+'">'+o.arrow.getHTML()+'</td></table>'
}
function IconMenuWidget_OverCB()
{
IconMenuWidgetOverCB(getWidget(this).index)
return true
}
function IconMenuWidget_OutCB()
{
IconMenuWidgetOutCB(getWidget(this).index)
}
function IconMenuWidgetOverCB(i)
{
o=_allBOIconsMenus[i]
IconWidget_overCB(o.icon.index)
IconWidget_overCB(o.arrow.index)
}
function IconMenuWidgetOutCB(i)
{
o=_allBOIconsMenus[i]
if (!o.menu.isShown())
{
IconWidget_outCB(o.icon.index)
IconWidget_outCB(o.arrow.index)
}
else
{
IconWidget_overCB(o.icon.index)
IconWidget_overCB(o.arrow.index)
}
}
function IconMenuWidget_iconClickCB()
{
var o=this.par
if (o.clickCB==null)
{
var l=o.layer
o.menu.show(!o.menu.isShown(),getPos(l).x,getPos(l).y+o.getHeight()+1,null,null,o)
IconMenuWidgetOverCB(o.index)
}
else
o.clickCB()
}
function IconMenuWidget_arrowClickCB()
{
var o=this.par,l=o.layer
o.menu.show(!o.menu.isShown(),getPos(l).x,getPos(l).y+o.getHeight()+1,null,null,o)
IconMenuWidgetOverCB(o.index)
}
function IconMenuWidget_hideCB()
{
var o=this.parIcon
IconMenuWidgetOutCB(o.index)
}
function newRadioIconMenuWidget(id,src,clickCB,text,alt,w,h,dx,dy,disDx,disDy,radioIdx,attachMenuCB)
{
var o=newIconMenuWidget(id,src,clickCB,text,alt,w,h,dx,dy,disDx,disDy,false)
o.defDx=dx
o.defDy=dy
o.attachMenuCB=null
if (attachMenuCB) {
o.attachMenuCB=attachMenuCB
o.arrow.clickCB=RadioIconMenuWidget_arrowClickCB
}
o.attachMenu=RadioIconMenuWidget_attachMenu
o.updateButton=RadioIconMenuWidget_updateButton
o.radioIdx=radioIdx?radioIdx:-1
o.checked=radioIdx?true:false
return o
}
function RadioIconMenuWidget_arrowClickCB()
{
var o=this.par,l=o.layer
o.attachMenuCB()
o.menu.show(!o.menu.isShown(),getPos(l).x,getPos(l).y+o.getHeight()+1,null,null,o)
IconMenuWidgetOverCB(o.index)
}
function RadioIconMenuWidget_updateButton(idx)
{
var o=this
o.icon.dis=false
if ((idx != null) &&  (idx > 0)) {
if (o.radioIdx == idx) return
o.radioIdx=idx
o.checked=true
dx=idx*16
dy=0
cn=o.icon.checkClass
} else {
o.checked=false
dx=16
dy=0
cn=o.icon.nocheckClass
o.radioIdx=0
}
if (o.icon)
{
var lyr=o.icon.layer
if (lyr)
{
o.icon.changeImg(dx,dy)
lyr.className=cn
}
}
if (idx==null) o.setDisabled(true)
}
function RadioIconMenuWidget_attachMenu(menu)
{
var o=this
o.par=null
o.menu=menu
menu.par=null
menu.container=o
return menu
}
function newIconColorMenuWidget(id,src,clickCB,text,alt,w,h,dx,dy,disDx,disDy, lastUsedColorsAr)
{
var o=newIconMenuWidget(id,src,clickCB,text,alt,w,h,dx,dy,disDx,disDy,true)
o.setColor=IconColorMenuWidget_setColor
o.getColor=IconColorMenuWidget_getColor
o.icon.extraHTML='<div id="colSample_'+o.id+'" style="position:relative;top:-6px;left:2px;width:16px;height:4px;overflow:hidden;margin:0px"></div>'
o.curColor=null
o.sampleLayer=null
o.clickColor=clickCB
o.clickCB=null
o.colOldSetDis=o.setDisabled
o.setDisabled=IconColorMenuWidget_setDisabled
o.showSample=IconColorMenuWidget_showSample
o.changeTooltip=IconColorMenuWidget_changeTooltip
var m=o.menu
m.ac=m.addColor
var cb=IconMenuWidget_internalCB
var c1=m.addCheck("color_-1,-1,-1",_default,cb)
c1.leftZoneClass="menuLeftPartColor"
c1.leftZoneSelClass="menuLeftPartSel"
_colorsArr = new Array()
_colorsArr["0,0,0"]=_black
_colorsArr["148,52,0"]=_brown
_colorsArr["49,52,0"]=_oliveGreen
_colorsArr["0,52,0"]=_darkGreen
_colorsArr["0,52,99"]=_darkTeal
_colorsArr["0,0,132"]=_navyBlue
_colorsArr["49,52,148"]=_indigo
_colorsArr["66,65,66"]=_darkGray
_colorsArr["132,4,0"]=_darkRed
_colorsArr["255,101,0"]=_orange
_colorsArr["123,125,0"]=_darkYellow
_colorsArr["0,125,0"]=_green
_colorsArr["0,125,123"]=_teal
_colorsArr["0,0,255"]=_blue
_colorsArr["99,101,148"]=_blueGray
_colorsArr["132,130,132"]=_mediumGray
_colorsArr["255,0,0"]=_red
_colorsArr["255,195,66"]=_lightOrange
_colorsArr["148,199,0"]=_lime
_colorsArr["49,150,99"]=_seaGreen
_colorsArr["49,199,198"]=_aqua
_colorsArr["49,101,255"]=_lightBlue
_colorsArr["132,0,132"]=_violet
_colorsArr["148,150,148"]=_gray
_colorsArr["255,0,255"]=_magenta
_colorsArr["255,199,0"]=_gold
_colorsArr["255,255,0"]=_yellow
_colorsArr["0,255,0"]=_brightGreen
_colorsArr["0,255,255"]=_cyan
_colorsArr["0,199,255"]=_skyBlue
_colorsArr["148,52,99"]=_plum
_colorsArr["198,195,198"]=_lightGray
_colorsArr["255,178,181"]=_pink
_colorsArr["255,199,148"]=_tan
_colorsArr["255,255,206"]=_lightYellow
_colorsArr["206,255,206"]=_lightGreen
_colorsArr["206,255,255"]=_lightTurquoise
_colorsArr["148,199,255"]=_paleBlue
_colorsArr["198,150,255"]=_lavender
_colorsArr["255,255,255"]=_white
with (m)
{
for (var i in _colorsArr) {
ac(_colorsArr[i],i,cb)
}
}
if (lastUsedColorsAr) 
{
m.addLastUsed(_lastUsed,lastUsedColorsAr, cb)
}
c1=m.add(null,_moreColors,cb)
c1.leftZoneClass="menuLeftPartColor"
c1.leftZoneSelClass="menuLeftPartSel"
return o
}
function IconColorMenuWidget_setColor(color)
{
var o=this,menu=o.menu
menu.uncheckAll()
o.curColor=color
if (color!=null)
{
var id="color_"+color
var items=menu.items
for (var i in items)
{
var item=items[i]
if (item.id==id)
{
item.check(true)
break
}
}
}
o.showSample()
}
function IconColorMenuWidget_showSample()
{
var o=this
if (o.layer)
{
if (o.sampleLayer==null)
o.sampleLayer=getLayer('colSample_'+o.id)
o.sampleLayer.style.backgroundColor=((o.curColor!='-1,-1,-1')&&(o.curColor!=null)) ? 'rgb('+o.curColor+')' : ''
}
}
function IconColorMenuWidget_setDisabled(dis)
{
this.colOldSetDis(dis)
if (this.layer)
this.showSample()
}
function IconColorMenuWidget_getColor()
{
return this.curColor
}
function IconColorMenuWidget_changeTooltip(s)
{
var o=this
if (s==null) return;
if (o.icon && o.arrow) {
o.icon.alt=s;
o.icon.changeTooltip(s)
o.arrow.changeTooltip(_openMenuPart1+ s +_openMenuPart2)
}
}
function newIconCheckWidget(id,src,clickCB,text,alt,w,h,dx,dy,disDx,disDy)
{
var o=newIconWidget(id,src,clickCB,text,alt,w,h,dx,dy,disDx,disDy)
o.checked=false
o.internalUpCB=IconCheckWidget_internalUpCB
o.internalDownCB=IconCheckWidget_internalDownCB
o.check=IconCheckWidget_check
o.isChecked=IconCheckWidget_isChecked
o.oldInit=o.init
o.init=IconCheckWidget_init
o.isRadio=false
return o
}
function newIconRadioWidget(id,src,clickCB,text,alt,group,w,h,dx,dy,disDx,disDy)
{
var o=newIconCheckWidget(id,src,clickCB,text,alt,w,h,dx,dy,disDx,disDy)
o.group=group
o.beforeClickCB=IconRadioWidget_uncheckOthers
o.isRadio=true
if (_RadioWidget_groups[group]==null)
_RadioWidget_groups[group]=new Array
o.groupInstance=_RadioWidget_groups[group]
var g=o.groupInstance
o.groupIdx=g.length
g[g.length]=o
return o
}
function newPaletteContainerWidget(id,contextMenu,margin)
{
var o=newWidget(id)
o.beginHTML=PaletteContainerWidget_beginHTML
o.endHTML=PaletteContainerWidget_endHTML
o.add=PaletteContainerWidget_add
o.palettes=new Array
o.contextMenu=contextMenu
o.margin=(margin!=null)?margin:4
return o
}
function newPaletteWidget(id,height)
{
var o=newWidget(id)
o.getHTML=PaletteWidget_getHTML
o.add=PaletteWidget_add
o.disableChildren=PaletteWidget_disableChildren
o.items=new Array
o.oldInit=o.init
o.init=PaletteWidget_init
o.beginRightZone=PaletteWidget_beginRightZone
o.height=height
o.rightZoneIndex=-1
o.sepCount=0
o.vertPadding=4
return o
}
function newPaletteSepWidget(id)
{
var o=newWidget(id)
o.getHTML=PaletteSepWidget_getHTML
o.isSeparator=true
return o
}
function newPaletteVerticalSepWidget(id)
{
var o=newWidget(id)
o.getHTML=PaletteVerticalSepWidget_getHTML
o.isSeparator=true
return o
}
function PaletteVerticalSepWidget_getHTML()
{
return img(_skin+"iconsep.gif",6,22,null,' id="'+this.id+'" ')
}
function getPaletteSep()
{
return img(_skin+"iconsep.gif",6,22)
}
function IconRadioWidget_uncheckOthers()
{
var g=this.groupInstance,idx=this.groupIdx,len=g.length;
for (var i=0;i<len;i++)
{
if (i!=idx)
{
var c=g[i];
if(c)
c.check(false);
}
}
}
function PaletteWidget_beginRightZone()
{
this.rightZoneIndex=this.items.length
}
function PaletteSepWidget_getHTML()
{
return '<div style="background-image:url('+_skin+'sep.gif);height:2px;padding:0px;margin-top:0px;margin-bottom:0px;margin-left:4px;margin-right:4px">'+getSpace(1,2)+'</div>'
}
function PaletteContainerWidget_beginHTML()
{
var o=this
var cm=o.contextMenu?('oncontextmenu="'+_codeWinName+'.PaletteContainerWidget_contextMenu(this,event);return false"'):''
return '<div '+cm+ 'class="palette" style="overflow:hidden;margin:'+o.margin+'px;" id="'+o.id+'">'
}
_delayedMenu=null
function PaletteContainerWidget_contextMenu(o,e)
{
if (_ie)
e=_curWin.event
_delayedMenu=getWidget(o).contextMenu
setTimeout('_delayedMenu.par=null;_delayedMenu.show(true,'+absxpos(e)+','+absypos(e)+')',1)
}
function PaletteContainerWidget_endHTML()
{
return '</div>'
}
function PaletteContainerWidget_add(palette)
{
this.palettes[this.palettes.length]=palette
return palette
}
function PaletteWidget_getHTML()
{
var o=this,items=o.items,len=items.length,fields=new Array;j=0
fields[j++]='<table id="'+o.id+'" '+attr("height",o.height)+' cellspacing="0" cellpadding="0" '+(_ie?"":'width="100%"')+'><tbody><tr valign="middle">'
fields[j++]='<td align="left" style="padding-left:'+o.vertPadding+'px;padding-right:4px"><table cellspacing="0" cellpadding="0"><tbody><tr valign="middle">'
var haveRightZone=false
for (var i=0;i<len;i++)
{
if (i==o.rightZoneIndex)
{
fields[j++]='</tr></tbody></table></td><td width="100%" align="right" style="padding-right:'+o.vertPadding+'px"><table cellspacing="0" cellpadding="0"><tbody><tr valign="middle">'
haveRightZone=true
}
var it=items[i]
fields[j++]='<td>'+it.getHTML()+'</td>'
}
if (!haveRightZone)
fields[j++]='</tr></tbody></table></td><td width="100%" align="right" style="padding-right:4px"><table cellspacing="0" cellpadding="0"><tbody><tr valign="middle"><td></td>'
fields[j++]='</tr></tbody></table></td></tr></tbody></table>'
return fields.join("")
}
function PaletteWidget_add(item)
{
if(item==null)
{
item=newPaletteVerticalSepWidget(this.id+"_palettesep_"+(this.sepCount++))
}
this.items[this.items.length]=item
return item
}
function PaletteWidget_disableChildren(dis)
{
var items=this.items
for (var i in items)
{
var item=items[i]
if (item&&(item.isSep!=true))
item.setDisabled(dis)
}
}
function PaletteWidget_init()
{
this.oldInit()
var items=this.items
for (var i in items)
{
var item=items[i]
if (item)
item.init()
}
}
function IconWidget_getTxtWidth()
{
var o=this,w=o.width
if (w!=null)
{
w=w-(o.margin*2) 
w=w-(o.src?o.w+o.border:1) 
w=w-(o.txtNoPadding?0:((o.src?4:2)+2))
if (_ie)
w-=2
else
w-=2
return Math.max(0,w)
}
else
return -1
}
function IconWidget_init()
{
var o=this,dblClick=false
o.iconOldInit()
var l=o.layer
l.tabIndex=o.dis?-1:0
l.title=(o.alt?o.alt:(o.text?o.text:""))
if (o.clickCB)
{
l.onclick=IconWidget_upCB
l.onmousedown=IconWidget_downCB
if (o.allowDblClick&&(_ie||_saf))
{
dblClick=true
addDblClickCB(l,IconWidget_upCB)
}
l.onkeydown=IconWidget_keydownCB
l.onmouseover=IconWidget_realOverCB
l.onmouseout=IconWidget_realOutCB
}
if (!dblClick)
addDblClickCB(l,IconWidget_retFalse)
l.onselectstart=IconWidget_retFalse
}
function IconWidget_getHTML()
{
var o=this,imgCode=o.src?'<div style="overflow:hidden;height:'+(o.h+o.border)+'px;width:'+(o.w+o.border)+'px;">'+simpleImgOffset(o.src,o.w,o.h,o.dis?o.disDx:o.dx,o.dis?o.disDy:o.dy,'IconImg_'+o.id,null,o.alt,'margin:2px;cursor:'+(o.clickCB ? (o.dis ? 'default' : _hand):'default'))+o.extraHTML+'</div>':'<div class="icontext" style="width:1px;height:'+(o.h+o.border)+'px"></div>'
var txtAtt='style="white-space:nowrap;',txtW=o.getTxtWidth()
if (txtW>=0)
txtAtt+='text-overflow:ellipsis;overflow:hidden;width:'+txtW+'px'
txtAtt+='"'
return '<table style="height:'+(o.h+o.border)+'px;'+(o.width!=null?"width:"+o.width+"px;":"")+'margin:'+o.margin+'px" id="'+o.id+'" class="' + o.nocheckClass + '" cellspacing="0" cellpadding="0" border="0"><tr valign="middle">'+
'<td>'+ ((o.clickCB&&_ie)?lnk(imgCode,null,null,null, ' tabIndex="-1"' ):imgCode)+'</td>'+
(o.text?'<td align="'+o.txtAlign+'" style="padding-left:'+(o.txtNoPadding?0:(o.src?4:2))+'px;padding-right:'+(o.txtNoPadding?0:2)+'px"><div id="IconImg_Txt_'+o.id+'" class="icontext'+(o.dis?"Dis":"")+'" '+txtAtt+'>'+convStr(o.text)+'</div></td>':'')+
'</tr></table>'
}
function IconWidget_realOutCB()
{
var o=getWidget(this)
eval(o.outCB+'('+o.index+')')
}
function IconWidget_realOverCB()
{
var o=getWidget(this)
eval(o.overCB+'('+o.index+')')
return true
}
function IconWidget_retFalse()
{
return false
}
function IconWidget_resize(w,h)
{
var o=this
if (o.layer)
o.oldRes(w,h)
if (o.txtLayer==null)
o.txtLayer=getLayer("IconImg_Txt_"+o.id)
if (w!=null)
{
o.width=w
var txtW=o.getTxtWidth()
if (o.txtLayer&&(txtW>=0))
{
o.txtLayer.style.width=''+txtW+'px'
}
}
if (h!=null)
{
o.h=h?(h-o.border):o.h
if (o.txtLayer&&(o.h>=0))
{
o.txtLayer.style.height=''+o.h+'px'
}
}
}
function IconWidget_changeTooltip(s,isTemporary)
{
var o=this
if (s==null) return;
if(!isTemporary)
o.alt=s;
if(o.layer)
o.layer.title=s
if (o.imgLayer==null)
o.imgLayer = getLayer('IconImg_'+this.id);
if (o.imgLayer)
changeSimpleOffset(o.imgLayer,null,null,null,s)
}
function IconWidget_changeText(s)
{
var o=this
o.text=s
if (o.layer)
{
if (o.txtLayer==null)
o.txtLayer=getLayer("IconImg_Txt_"+o.id)
o.txtLayer.innerHTML=convStr(s)
}
}
function IconWidget_changeImg(dx,dy,src)
{
var o=this
if (src) o.src=src
if (dx!=null) o.dx=dx
if (dy!=null) o.dy=dy
if (o.layer&&(o.imgLayer==null))
o.imgLayer = getLayer('IconImg_'+this.id);
if (o.imgLayer)
changeSimpleOffset(o.imgLayer,dx,dy,o.src)
}
function IconWidget_internalDownCB()
{
if (!this.dis)
this.currentHoverClass=this.checkhoverClass
}
function IconWidget_internalUpCB()
{
if (!this.dis)
this.currentHoverClass=this.hoverClass
}
function IconWidget_downCB()
{
var o=getWidget(this)
if ((o.layer)&&(!o.dis))
{
o.internalDownCB()
o.layer.className=o.currentHoverClass
}
if (_ie||_saf)
return false
}
function IconWidget_upCB()
{
var o=getWidget(this)
if ((o.layer)&&(!o.dis))
{
o.internalUpCB()
o.layer.className=o.isHover?o.currentHoverClass:o.currentClass
setTimeout("delayedClickCB("+o.index+")",1)
}
}
function IconWidget_keydownCB(e)
{
if(eventGetKey(e)==13) 
{
var o=getWidget(this)
setTimeout("delayedClickCB("+o.index+")",1)
eventCancelBubble(e);
}
}
function delayedClickCB(index)
{
var o=_allBOIcons[index]
if (o.beforeClickCB)
o.beforeClickCB()
if (o.clickCB)
o.clickCB()
}
function IconWidget_overCB(index)
{
var o=_allBOIcons[index]
o.css.cursor=(o.clickCB ? (o.dis ? 'default' : _hand) : 'default')
if ((o.layer)&&(!o.dis)&&!(o.par && o.par.checked)) 
{
o.isHover=true
o.layer.className=o.currentHoverClass
}
}
function IconWidget_outCB(index)
{
var o=_allBOIcons[index]
if ((o.layer)&&(o.outEnable)&&!(o.par && o.par.checked)) 
{
o.isHover=false
o.layer.className=o.currentClass
}
}
function IconCheckWidget_init()
{
var o=this
o.oldInit()
o.check(false,true)
}
function IconCheckWidget_internalDownCB()
{
var o=this
if (!o.dis)
o.currentHoverClass=o.checked?o.hoverClass:o.checkhoverClass
}
function IconCheckWidget_internalUpCB()
{
var o=this
if (!o.dis)
{
o.checked=o.isRadio?true:!o.checked
o.currentClass=o.checked?this.checkClass:this.nocheckClass
o.currentHoverClass=o.checked?this.checkhoverClass:this.hoverClass
}
}
function IconCheckWidget_check(checked,force)
{
var o=this
if ((o.checked!=checked)||force)
{
o.checked=checked
o.layer.className=o.currentClass=o.checked?this.checkClass:this.nocheckClass
o.currentHoverClass=o.checked?this.checkhoverClass:this.hoverClass
}
if (o.checked&&o.beforeClickCB)
o.beforeClickCB()
}
function IconCheckWidget_isChecked()
{
return this.checked
}
function IconWidget_setClasses(nocheck, check, hover, checkhover)
{
var o=this
o.nocheckClass=nocheck
o.checkClass=check
o.hoverClass=hover
o.checkhoverClass=checkhover
o.currentClass=o.nocheckClass
o.currentHoverClass=o.hoverClass
}
function IconWidget_setDisabled(dis)
{
 var o=this
if (o.dis!=dis)
{
o.dis=dis
if (o.layer)
{
var crs=o.clickCB ? (o.dis ? 'default' : _hand) : 'default'
o.layer.style.cursor=crs
if (o.src)
{
if (o.imgLayer==null)
o.imgLayer = getLayer('IconImg_'+this.id);
changeSimpleOffset(o.imgLayer,dis?o.disDx:o.dx,dis?o.disDy:o.dy)
o.imgLayer.style.cursor=crs
}
if (o.text)
{
if (o.txtLayer==null)
o.txtLayer=getLayer("IconImg_Txt_"+o.id)
o.txtLayer.className="iconText"+(dis?"Dis":"")
if (dis)
o.layer.className=o.currentClass
}
o.layer.tabIndex=o.dis?-1:0
}
}
}
function IconWidget_isDisabled()
{
return this.dis?this.dis:false
}
function newCustomCombo(id,changeCB,noMargin,width,tooltip,url,w,h,dx,dy,disDx,disDy)
{
var o=newIconMenuWidget(id,url,null," ",tooltip,w,h,dx,dy,disDx,disDy)
o.icon.width=width!=null?Math.max(0,width-13):50-(2*o.margin)
o.icon.setClasses("combonocheck", "combocheck", "combohover", "combocheck")
o.icon.clip
o.arrow.setClasses("iconnocheck", "combobtnhover", "combobtnhover", "combobtnhover")
o.spc=0
o.margin=2
if (url==null)
{
o.icon.h=12
o.arrow.h=12
o.arrow.dy+=2
o.arrow.disDy+=2
}
o.counter=0
o.changeCB=changeCB
o.selectedItem=null
o.setOldDid=o.setDisabled
o.disabled=false
o.ccomboOldInit=o.init
o.init=CustomCombo_init
o.add=CustomCombo_add
o.addSeparator=CustomCombo_addSeparator
o.addMenuItem=CustomCombo_addMenuItem
o.select=CustomCombo_select
o.getSelection=CustomCombo_getSelection
o.valueShow=CustomCombo_valueShow
o.valueSelect=CustomCombo_valueSelect
o.setUndefined=CustomCombo_setUndefined
o.setDisabled=CustomCombo_setDisabled
o.getVisibleItemsCount=CustomCombo_getVisibleItemsCount
o.selectItem=CustomCombo_selectItem
o.getItemByIndex=CustomCombo_getItemByIndex
o.getItemIndex=CustomCombo_getItemIndex
o.setItemDisabled=CustomCombo_setItemDisabled
return o
}
function CustomCombo_init()
{
var o=this
o.ccomboOldInit()
if(o.disabled) o.icon.changeTooltip(o.icon.alt?o.icon.alt:"",true)
o.arrow.changeTooltip(_openMenuPart1+(o.icon.alt?o.icon.alt:"")+_openMenuPart2)
}
function CustomCombo_add(s,val,selected)
{
var o=this
var item=o.menu.addCheck(o.id+"_it_"+(o.counter++),s,CustomCombo_internalCB)
item.val=""+val
item.parCombo=o
item.isComboVal=true
if ((o.selectedItem==null)||selected)
o.selectItem(item)
}
function CustomCombo_addSeparator()
{
this.menu.addSeparator()
}
function CustomCombo_addMenuItem(id,text,cb,icon,dx,dy,disabled,disDx,disDy)
{
this.menu.add(id,text,cb,icon,dx,dy,disabled,disDx,disDy)
}
function CustomCombo_internalCB()
{
var o=this,c=o.parCombo
c.selectItem(o)
if (c.changeCB)
c.changeCB()
}
function CustomCombo_getItemByIndex(idx)
{
var items=this.menu.items
return ((idx>=0)&&(idx<items.length))?items[idx]:null
}
function CustomCombo_getItemIndex(item)
{
var items=this.menu.items,len=items.length,j=0
for (var i=0;i<len;i++)
{
var it=items[i]
if (it.isComboVal)
{
if (it.id==item.id)
return j
j++
}
}
return -1
}
function CustomCombo_selectItem(item)
{
var o=this
if (o.selectedItem)
o.selectedItem.check(false)
if (item)
{
o.val=item.val
o.icon.changeText(o.disabled?"":item.text)
o.selectedItem=item
item.check(true)
if(o.disabled)
o.icon.changeTooltip(o.icon.alt?o.icon.alt:"",true)
else
o.icon.changeTooltip(o.icon.alt?(o.icon.alt+' ('+item.text)+')':(item.text),true)
}
else
{
o.val=null
o.icon.changeText("")
o.icon.changeTooltip(o.icon.alt?o.icon.alt:"",true)
o.selectedItem=null
}
}
function CustomCombo_setDisabled(d)
{
var o=this
if (o.selectedItem)
o.icon.changeText(d?"":o.selectedItem.text)
o.disabled=d
o.setOldDid(d)
if(d) o.icon.changeTooltip(o.icon.alt?o.icon.alt:"",true)
}
function CustomCombo_select(idx)
{
var o=this,item=o.getItemByIndex(idx)
if (item)
o.selectItem(item)
}
function CustomCombo_setItemDisabled(idx,disabled)
{
var o=this,item=o.getItemByIndex(idx)
if (item)
item.setDisabled(disabled)
}
function CustomCombo_getSelection()
{
var o=this,it=o.selectedItem
if (it)
return {index:o.getItemIndex(it),value:it.val}
else
return null
}
function CustomCombo_valueSelect(v)
{
v=""+v
var o=this,items=o.menu.items,len=items.length
for (var i=0;i<len;i++)
{
var it=items[i]
if ((it.isComboVal)&&(it.val==v)&&(it.isShown) )
{
o.selectItem(it)
return true
}
}
return false
}
function CustomCombo_valueShow(v,show)
{
v=""+v
var o=this,items=o.menu.items,len=items.length
for (var i=0;i<len;i++)
{
var it=items[i]
if ((it.isComboVal)&&(it.val==v))
{
it.show(show)
return
}
}
}
function CustomCombo_setUndefined(u)
{
var o=this
if (u)
o.selectItem(null)
}
function CustomCombo_getVisibleItemsCount()
{
var o=this,items=o.menu.items,len=items.length,n=0
for (var i=0;i<len;i++)
{
var it=items[i]
if ((it.isComboVal)&&(it.isShown))
{
n++
}
}
return n;
}
function newTextComboWidget(id,maxChar,tooltip,w,changeCB,checkCB,beforeShowCB,formName)
{
var o=newWidget(id)
o.text=newTextFieldWidget((formName?formName:"text_"+id),TextComboWidget_checkCB,maxChar,null,TextComboWidget_enterCB,true,tooltip,w-13)
o.arrow=newIconWidget("arrow_"+id,_skin+"menus.gif",TextComboWidget_arrowClickCB,null,(_openMenuPart1+(tooltip?tooltip:"")+_openMenuPart2),7,16,0,81,0,97)
o.menu=newMenuWidget("menu_"+id,TextComboWidget_hideCB,beforeShowCB)
o.arrow.setClasses("iconnocheck", "combobtnhover", "combobtnhover", "combobtnhover")
o.text.par=o
o.arrow.par=o
o.menu.parIcon=o
o.arrow.margin=0
o.arrow.overCB="IconWidget_none"
o.arrow.outCB="IconWidget_none"
o.margin=0
o.spc=0
o.counter=0
o.arrow.h=12
o.arrow.dy+=2
o.arrow.disDy+=2
o.index=_allBOIconsMenus.length++
_allBOIconsMenus[o.index]=o
o.menIcnOldInit=o.init
o.init=TextComboWidget_init
o.imwpResize=o.resize
o.resize=TextComboWidget_resize
o.getHTML=TextComboWidget_getHTML
o.setDisabled=TextComboWidget_setDisabled
o.add=TextComboWidget_add
o.addSeparator=TextComboWidget_addSeparator
o.addMenuItem=TextComboWidget_addMenuItem
o.select=TextComboWidget_select
o.getSelection=TextComboWidget_getSelection
o.valueShow=TextComboWidget_valueShow
o.valueSelect=TextComboWidget_valueSelect
o.setUndefined=TextComboWidget_setUndefined
o.changeCB=changeCB
o.checkCB=checkCB
o.selectItem=TextComboWidget_selectItem
o.getItemByIndex=TextComboWidget_getItemByIndex
o.getItemIndex=TextComboWidget_getItemIndex
o.setItemDisabled=TextComboWidget_setItemDisabled
o.text.enterCancelBubble=false
return o
}
function TextComboWidget_init()
{
var o=this
o.menIcnOldInit()
o.text.init()
o.arrow.init()
o.menu.init()
var l=o.layer
l.onmouseover=TextCombo_OverCB
l.onmouseout=TextCombo_OutCB
}
function TextComboWidget_getHTML()
{
var o=this
return '<table id="'+o.id+'" cellspacing="0" cellpadding="0" border="0" style="cursor:default;margin:'+o.margin+'px"><tr><td>'+o.text.getHTML()+'</td><td style="padding-left:'+o.spc+'px" width="'+(13+o.spc)+'">'+o.arrow.getHTML()+'</td></table>'
}
function TextComboWidget_resize(w,h)
{
var o=this
if (w!=null)
w=Math.max(0,w-2*o.margin)
var d=o.layer.display!="none"
if (d&_moz&&!_saf)
o.setDisplay(false)
o.imwpResize(w,h)
if (d&_moz&&!_saf)
o.setDisplay(true)
}
function TextComboWidget_add(s,val,selected)
{
var o=this
var item=o.menu.addCheck(o.id+"_it_"+(o.counter++),s,TextComboWidget_internalCB)
item.val=""+val
item.parCombo=o
item.isComboVal=true
if ((o.selectedItem==null)||selected)
o.selectItem(item)
}
function TextComboWidget_addSeparator()
{
this.menu.addSeparator()
}
function TextComboWidget_addMenuItem(id,text,cb,icon,dx,dy,disabled,disDx,disDy)
{
this.menu.add(id,text,cb,icon,dx,dy,disabled,disDx,disDy)
}
function TextComboWidget_setDisabled(d)
{
var o=this
if (o.selectedItem)
o.text.setValue(d?"":o.selectedItem.text)
o.text.setDisabled(d)
o.arrow.setDisabled(d)
o.menu.setDisabled(d)
o.disabled=d
}
function TextComboWidget_select(idx)
{
var o=this,item=o.getItemByIndex(idx)
if (item)
o.selectItem(item)
}
function TextComboWidget_setItemDisabled(idx,disabled)
{
var o=this,item=o.getItemByIndex(idx)
if (item)
item.setDisabled(disabled)
}
function TextComboWidget_getSelection()
{
var o=this,it=o.selectedItem
var txt=o.text.getValue()
if (it)
return {index:o.getItemIndex(it),value:it.val}
else
return {index:-1,value:txt}
}
function TextComboWidget_valueSelect(v)
{
v=""+v
var o=this,items=o.menu.items,len=items.length
for (var i=0;i<len;i++)
{
var it=items[i]
if ((it.isComboVal)&&(it.val==v))
{
o.selectItem(it)
return
}
}
o.text.setValue(v)
}
function TextComboWidget_valueShow(v,show)
{
v=""+v
var o=this,items=o.menu.items,len=items.length
for (var i=0;i<len;i++)
{
var it=items[i]
if ((it.isComboVal)&&(it.val==v))
{
it.show(show)
return
}
}
o.text.setValue(v)
o.text.show(show)
}
function TextComboWidget_setUndefined(u)
{
var o=this
if (u)
o.selectItem(null)
}
function TextComboWidget_selectItem(item)
{
var o=this
if (o.selectedItem)
o.selectedItem.check(false)
if (item)
{
o.val=item.val
o.text.setValue(o.disabled?"":item.text)
o.selectedItem=item
item.check(true)
}
else
{
o.val=null
o.text.setValue("")
o.selectedItem=null
}
}
function TextComboWidget_getItemByIndex(idx)
{
var items=this.menu.items
return ((idx>=0)&&(idx<items.length))?items[idx]:null
}
function TextComboWidget_getItemIndex(item)
{
var items=this.menu.items,len=items.length,j=0
for (var i=0;i<len;i++)
{
var it=items[i]
if (it.isComboVal)
{
if (it.id==item.id)
return j
j++
}
}
return -1
}
function TextComboWidget_changeCB()
{
var p=this.par
var b=true
if (p.checkCB)
b=p.checkCB()
if (!b)
return
if (p.changeCB)
p.changeCB()
}
function TextComboWidget_enterCB()
{
var p=this.par
if (p.selectedItem)
{
p.selectedItem.check(false)
p.selectedItem=null
}
var b=true
if (p.checkCB)
b=p.checkCB()
if (!b)
return
if (p.changeCB) 
p.changeCB()
}
function TextComboWidget_checkCB()
{
var p=this.par
if (p.checkCB) 
p.checkCB()
}
function TextComboWidget_hideCB()
{
var o=this.parIcon
TextComboOutCB(o.index)
}
function TextComboWidget_arrowClickCB()
{
var o=this.par,l=o.layer
o.menu.show(!o.menu.isShown(),getPos(l).x,getPos(l).y+o.getHeight()+1,null,null,o)
TextComboOverCB(o.index)
}
function TextCombo_OverCB()
{
TextComboOverCB(getWidget(this).index)
return true
}
function TextComboOverCB(i)
{
var o=_allBOIconsMenus[i]
IconWidget_overCB(o.arrow.index)
}
function TextCombo_OutCB(i)
{
TextComboOutCB(getWidget(this).index)
}
function TextComboOutCB(i)
{
var o=_allBOIconsMenus[i]
if (!o.menu.isShown())
IconWidget_outCB(o.arrow.index)
else
IconWidget_overCB(o.arrow.index)
}
function TextComboWidget_internalCB()
{
var o=this,c=o.parCombo
c.selectItem(o)
if (c.changeCB)
c.changeCB()
}
function newIconScrollMenuWidget(id,src,clickCB,text,alt,w,h,dx,dy,disDx,disDy,
changeCB,multi,width,lines,tooltip,dblClickCB,keyUpCB,showLabel,label,convBlanks)
{
var o=newIconMenuWidget(id,src,clickCB,text,alt,w,h,dx,dy,disDx,disDy)
o.menu=newScrollMenuWidget("iconMenu_menu_"+id,changeCB,multi,width,lines,tooltip,dblClickCB,keyUpCB,
showLabel,label,convBlanks)
o.add=IconScrollMenu_add
return o
}
function IconScrollMenu_add(s,val,sel,id)
{
this.menu.add(s,val,sel,id)
}
