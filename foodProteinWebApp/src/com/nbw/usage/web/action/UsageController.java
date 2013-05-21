package com.nbw.usage.web.action;

import java.io.File;
import java.io.IOException;
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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.usage.domain.Usage;
import com.nbw.usage.service.UsageManager;

public class UsageController extends NbwController{

	
	private UsageManager usageManager;
	
	
	
	public UsageManager getUsageManager() {
		return usageManager;
	}

	public void setUsageManager(UsageManager usageManager) {
		this.usageManager = usageManager;
	}


	public ModelAndView frontToUsageEdit(HttpServletRequest request,
			HttpServletResponse response, Usage command, BindException errors)
			throws ServletException, IOException,FileUploadException {
		
	
		return new ModelAndView("backEdit/Usage");
	}
			//图片的上传功能，并保存，以备前台显示所用
	public ModelAndView frontUpLoad(HttpServletRequest request,
			HttpServletResponse response, Usage command, BindException errors)
			throws ServletException, IOException, FileUploadException {
	
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
	
	
	
	
//	public ModelAndView frontInsert(HttpServletRequest request,
//			HttpServletResponse response, Usage command, BindException errors)
//			throws ServletException, IOException,FileUploadException {
//		
//		
//		String str=request.getParameter("content1");
//	
//		return new ModelAndView("backEdit/user_know","body",str);
//	}
	
	
	
	public ModelAndView insertDB(HttpServletRequest request,
			HttpServletResponse response, Usage usage, BindException errors)
			throws ServletException, IOException,FileUploadException {
		String title=request.getParameter("title");
		String body=request.getParameter("body");
		String id=request.getParameter("id");
		Integer did=Integer.parseInt(id);
		Usage us=new Usage();
		us.setTitle(title);
		us.setId(did);
		us.setBody(body);
		this.usageManager.saveUsage(us);
		response.sendRedirect("usage.action?m=loadData");
		return null;
		//	return  new ModelAndView("backEdit/listAll","dataList",this.usageManager.getAll());
			
		
	
		
	}
	public ModelAndView frontGetUsageDetail(HttpServletRequest request,
			HttpServletResponse response, Usage usage, BindException errors)
			throws ServletException, IOException,FileUploadException {
		String id=request.getParameter("id");
		
		
		Integer did=Integer.parseInt(id);
		usage=this.usageManager.getUsageByID(did);
		return new ModelAndView("backEdit/user_know","usage",usage);
	}
	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub
		
	}
	
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, Usage command, BindException errors)
			throws ServletException, IOException {
				
			if(command!=null)
			{
			this.usageManager.editUsage(command);
			}
			
		response.sendRedirect("usage.action?m=loadData");
		return null;
		//return new ModelAndView("backEdit/listAll","dataList",this.usageManager.getAll());
	}
	/**
	 * 获得列表数据
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView loadData(HttpServletRequest request,
			HttpServletResponse response, Usage command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("backEdit/listAll","dataList",this.usageManager.getAll());
	}
//	public ModelAndView toAddPage(HttpServletRequest request,
//			HttpServletResponse response, Usage command, BindException errors)
//			throws ServletException, IOException {
//		
//		
//		return new ModelAndView("backEdit/Insert");
//	}
	public ModelAndView toAddPage(HttpServletRequest request,
			HttpServletResponse response, Usage command, BindException errors)
			throws ServletException, IOException {
		
		
		return new ModelAndView("backEdit/Insert");
	}
	
	public ModelAndView frontToList(HttpServletRequest request,
			HttpServletResponse response, Usage command, BindException errors)
			throws ServletException, IOException {
		
		
		return new ModelAndView("backEdit/usageList","dataList",this.usageManager.getAll());
	}
	
	
	public ModelAndView toEditPage(HttpServletRequest request,
			HttpServletResponse response, Usage command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
	Usage usage = this.usageManager.findById(Integer.parseInt(id));
		return new ModelAndView("backEdit/Usage","usage",usage);
	}
	
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, Usage command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");		
		try {
			this.usageManager.deleteUsage(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("backEdit/listAll","dataList",this.usageManager.getAll());
	}
	
	
}
