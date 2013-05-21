package com.nbw.tupu.web.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwLittleController;
import com.nbw.common.util.DoubleUtil;
import com.nbw.common.util.tupu.CommonJsonUtil;
import com.nbw.tupu.domain.FmptTp;
import com.nbw.tupu.domain.Item;
import com.nbw.tupu.service.FmptTpManager;
import com.nbw.tupu.util.Tiqulist;


/**
 * 
 * FmptBioclassController
 * 
 * @author 
 * 
 */
public class FmptTpController  extends NbwLittleController {


    private FmptTpManager fmpttpmanager;
    /**
	 * constructor
	 *  
	 * @throws ClassNotFoundException
	 */
	public FmptTpController() throws ClassNotFoundException {
		super();
	}


	/**
	 * 图谱首页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontToIndex(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		String key = request.getParameter("key");
		if(key!=""&&!"".equals(key)&&key!=null){
			key = URLDecoder.decode(key,"UTF-8");
			request.setAttribute("key", key);
		}
		return new ModelAndView("tupubidui/fmpttpbidui");
	}
//	public ModelAndView frontToAnotherIndex(HttpServletRequest request,
//			HttpServletResponse response)
//			throws ServletException, IOException {
//		String key = request.getParameter("key");
//		if(key!=""&&!"".equals(key)&&key!=null){
//			key = URLDecoder.decode(key,"UTF-8");
//			request.setAttribute("key", key);
//		}
//		return new ModelAndView("tupubidui/fmpttpbidui");
//	}
	
	/**
	 * 查询结果列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontSearch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		if(key!=""&&!"".equals(key)&&key!=null){
			key = URLDecoder.decode(key,"UTF-8");
			List<FmptTp> fmptList = fmpttpmanager.findByName(key);
			request.setAttribute("fmptList", fmptList);
			response.setCharacterEncoding("utf-8");
			String str = "";
			for(FmptTp f:fmptList){
				str += "<a href='#' onClick='javascript:showBzChart("+f.getId()+");'>"+f.getLatinName()+"</a><br />";	
			}
			response.getWriter().write(str);
		}
		return null;
	}
	/**
	 * 用户检索数据库中的数据
	 * @param reques'
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ModelAndView frontCharData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("items");
		List<Item> list = new ArrayList<Item>();
		if (json != null && !json.equals("")) {
			try {
				Item[] items = (Item[]) CommonJsonUtil
						.toArray(json, Item.class);
				for (int i = 0; i < items.length; i++) {
					list.add(items[i]);
				}
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage());
			}
		}
		String tpid = request.getParameter("id");
		if (tpid != null && !tpid.equals("")) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getName().equals(tpid)) {
					return null;
				}
			}
			Item item = fmpttpmanager.search(tpid);
			list.add(item);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(
				CommonJsonUtil.toStr(list.toArray(new Item[0])));
		return null;
	}
	/**
	 * 用户上传本地的*.xy文件
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView frontUpdateFile(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Item> list = new ArrayList<Item>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			request.setCharacterEncoding("UTF-8");
			List<FileItem> items = upload.parseRequest(request);// 上传文件解析
			Iterator<FileItem> itr = items.iterator();// 枚举方法
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {// 判断是文件还是文本信息
					logger.info("表单参数名:" + item.getFieldName() + "，表单参数值:"
							+ item.getString("UTF-8"));
				} else {
					if (item.getName() != null && !item.getName().equals("")) {// 判断是否选择了文件
						String filename=item.getName();
					
						list.add(fmpttpmanager.saveFile(item));
						// 此时文件暂存在服务器的内存当中
						request.setAttribute("upload.message", "上传文件成功！");// 返回上传结果
					} else {
						request.setAttribute("upload.message", "没有选择上传文件！");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message", "上传文件失败！");
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(
				CommonJsonUtil.toStr(list.toArray(new Item[0])));
		return null;
	}
	
	public ModelAndView frontGetDuiBiDu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,Exception {
		
	String first=	(String)request.getParameter("first");
	String second=(String)request.getParameter("sencond");
		String abspath=request.getRealPath("/");
	String pathone=abspath+"/txt/"+first;
	String pathtwo=abspath+"/txt/"+second;
	Double m=Tiqulist.getXiangShiDu(pathone,pathtwo);	
//	System.out.println(m);
	m=m*100;
	m=DoubleUtil.round(m,2);
	
	
	
	response.getWriter().write(m.toString());
//	response.getWriter().write(m.toString());
		
	return null;
	}
	
	
	public ModelAndView frontUpdateFileBiDui(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Item> list = new ArrayList<Item>();
		List<String> listname=new ArrayList<String>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			request.setCharacterEncoding("UTF-8");
			List<FileItem> items = upload.parseRequest(request);// 上传文件解析
			Iterator<FileItem> itr = items.iterator();// 枚举方法
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {// 判断是文件还是文本信息
					logger.info("表单参数名:" + item.getFieldName() + "，表单参数值:"
							+ item.getString("UTF-8"));
				} else {
					if (item.getName() != null && !item.getName().equals("")) {// 判断是否选择了文件
						String filename=item.getName();
						//解析上传的文
						listname.add(fmpttpmanager.saveTxt(item));
						list.add(fmpttpmanager.saveFile(item));
						// 此时文件暂存在服务器的内存当中
						request.setAttribute("upload.message", "上传文件成功！");// 返回上传结果
					} else {
						request.setAttribute("upload.message", "没有选择上传文件！");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message", "上传文件失败！");
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(
				CommonJsonUtil.toStr(list.toArray(new Item[0])));
		return null;
	}
	//*******************************************************************************set and get	
	
	public FmptTpManager getFmpttpmanager() {
		return fmpttpmanager;
	}

	public void setFmpttpmanager(FmptTpManager fmpttpmanager) {
		this.fmpttpmanager = fmpttpmanager;
	}
	
}

