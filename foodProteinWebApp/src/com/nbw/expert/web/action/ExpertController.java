package com.nbw.expert.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.common.util.PageBean;
import com.nbw.expert.domain.Expert;
import com.nbw.expert.service.ExpertManager;


public class ExpertController extends NbwController{

	private ExpertManager expertManager;

	public ExpertManager getExpertManager() {
		return expertManager;
	}
	public void setExpertManager(ExpertManager expertManager) {
		this.expertManager = expertManager;
	}
	public ModelAndView frontToSearch(HttpServletRequest request,
			HttpServletResponse response, Expert command, BindException errors)
			throws ServletException, IOException {
		
		 response.setContentType("text/html;charset=utf-8");  
         PrintWriter out=response.getWriter();  
         String account=request.getParameter("account");  
         if("iamcrazy".equals(account)){  
             out.print("Sorry,the user is exist");  
         }  
         else{  
             out.print("Congratulation,this accont you can use!!!!");  
         }  
         out.close();  
		return null;
	}
	public ModelAndView frontGetMoreExpert(HttpServletRequest request,
			HttpServletResponse response, Expert command, BindException errors)
			throws ServletException, IOException {
		int pageSize = 5;
		int page = 1;
		if (request.getParameter("page") != null) {
			String pageString = (String) request.getParameter("page");
			try {
				page = Integer.parseInt(pageString);
			} catch (NumberFormatException e) {
				System.out.println("数字类型错误:" + e.getMessage());
			}
		}
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("page", page);
		PageBean expertList=this.expertManager.getMoreExpert(page, pageSize);
		return new ModelAndView("expert/expertList","expertList",expertList);
	}
	public ModelAndView getLittleExpert(HttpServletRequest request,
			HttpServletResponse response, Expert command, BindException errors)
			throws ServletException, IOException {
	List<Expert> listExpert=	this.expertManager.getFourExpert(); 
		return new ModelAndView("expert/searchExpert","fourExpert",listExpert);
	}
	public ModelAndView frontGetDetailExpert(HttpServletRequest request,
			HttpServletResponse response, Expert command, BindException errors)
			throws ServletException, IOException {
		String eid=	request.getParameter("eid");
		Integer id=	Integer.parseInt(eid);
		Expert e=this.expertManager.getDetailExpertByID(id);
		return new ModelAndView("expert/expertDetail","e",e);
	}
	
	
	
	
	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub
		
	}
	
	
	public ModelAndView frontTo(HttpServletRequest request,
			HttpServletResponse response, Expert command, BindException errors)
			throws ServletException, IOException {
	String str=	request.getParameter("content1");
	
		System.out.println(str);
	
		return new ModelAndView("usage/Usage");
	}
	
	public ModelAndView frontToe(HttpServletRequest request,
			HttpServletResponse response, Expert command, BindException errors)
			throws ServletException,Exception {
			
		//文件保存目录路径
		String savePath = request.getRealPath("/") + "attached/";

		//文件保存目录URL
		String saveUrl  = request.getContextPath() + "/attached/";

		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		//最大文件大小
		long maxSize = 1000000;

		response.setContentType("text/html; charset=UTF-8");

		if(!ServletFileUpload.isMultipartContent(request)){
			System.out.println("请选择文件。");
		
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			System.out.println("上传目录不存在。");
		
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			System.out.println("上传目录没有写权限。");
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			System.out.println("目录名不正确。");
		}
		//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			long fileSize = item.getSize();
			if (!item.isFormField()) {
				//检查文件大小
				if(item.getSize() > maxSize){
					System.out.println("上传文件大小超过限制。");
				}
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
					System.out.println("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				try{
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				}catch(Exception e){
					System.out.println("上传文件失败。");
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				response.getWriter().println(obj.toJSONString());
			}
		}
		return null;
	}
	
	
}
