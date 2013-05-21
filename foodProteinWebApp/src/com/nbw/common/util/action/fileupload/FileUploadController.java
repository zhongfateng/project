package com.nbw.common.util.action.fileupload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.Constants;
import com.nbw.common.NbwLittleController;
import com.nbw.common.SysParameter;
import com.nbw.hygl.domain.FmptJf;
import com.nbw.hygl.service.FmptJfManager;
import com.nbw.lucene.util.UpdateUtil;
import com.nbw.record.service.RecordManager;
import com.nbw.sys.domain.SysUsers;
import com.nbw.sys.service.SysUsersManager;
import com.nbw.zhipu.util.ConvertFromListToXmlUtil;
import com.nbw.zhipu.util.ExtractPoint;
import com.nbw.zhipu.util.Point;

/**
 * 
 * 文件上传下载
 * 
 * @author 张为锋
 * May 11, 2009  3:04:02 PM
 *
 */
public class FileUploadController extends NbwLittleController {

	/**
	 * constructor
	 * 
	 * @throws ClassNotFoundException
	 */
	private SysUsersManager sysUsersManager;
	private FmptJfManager fmptJfmanager;
	private RecordManager recordManager;
	
	public FileUploadController() throws ClassNotFoundException {
		super();
	}

	/**
	 * 加载tree
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException,
	 *             IOException
	 */
	public ModelAndView load(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		return new ModelAndView("fileupload/fileupload");
	}

	/**
	 * 
	 * 方法描述：文件上传界面
	 * 创建日期：Jan 14, 2009
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showFileUpload(HttpServletRequest request,
			HttpServletResponse response) {
		String fileDirectory = SysParameter.getParameter("uploadFile.path");// 设定上存档案目录 
		// 取得服务器中已有文件的下載列表
		List fileListInServer = new ArrayList();
		File dir = new File(fileDirectory);
		String[] children = dir.list();
		if (children != null) {
			for (int i = 0; i < children.length; i++) {
				fileListInServer.add(children[i]);
			}
		}
		request.setAttribute("downloadList", fileListInServer);
		return new ModelAndView("demo/fileupload/fileupload");
	}
	/**
	 * 
	 * 方法描述：文件下载
	 * 创建日期：Jan 14, 2009
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView downloadFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		HttpSession session=request.getSession();
//		if(session.getAttribute("userName")==null||session.getAttribute("userName").equals(""))
//		{
//			response.setContentType("text/html; charset=utf-8");
//			response.setCharacterEncoding("utf-8");
//			String html = "";
//			html = "<script >alert('你还没有登陆！请登陆');top.location.href = '"
//					+ request.getContextPath()
//					+ "/loginSys.jsp';</script>";
//			response.getWriter().write(html);
//			return null;
//		}
//		else
//		{
		try {
			String title=request.getParameter("title");
			String filename = request.getParameter("filename");
			String 	filenameall=filename.concat(".pdf");
			filenameall = new   String(filenameall.getBytes("iso-8859-1"),"utf-8");  
			String fileDirectory = SysParameter.getParameter("uploadFile.path"); // 设定上存档案目录 
			File fileout = new File(fileDirectory+filenameall);
			InputStream in = new FileInputStream(fileout);
			downFile(in,filenameall,request,response,filename);
			String userID=(String) request.getSession().getAttribute(Constants.SESSION_USER_ID);
			
			SysUsers  sysUser=this.sysUsersManager.findByID(userID);
			
			
			
			Date date = new Date();
			Timestamp time = new Timestamp(date.getTime());
			FmptJf fj=new FmptJf();
			
			//fj.setId(new Random().nextInt());
			fj.setFtype("download");
			fj.setNote(title);
			fj.setUsername(sysUser.getLoginCode());
			fj.setUserid(userID);
			fj.setTimestamp(time);
			fj.setJfcount(-2);
			this.fmptJfmanager.saveFmptJf(fj);
			int sum=this.fmptJfmanager.findSumSorceByUserId(userID);
			UpdateUtil.updateJF(userID,sum);
			
			
			
			
			
			//	UpdateUtil.updateRecord(userName);
			//	int m=	UpdateUtil.getScoreByUserName(userName);
			//UpdateUtil.updateRecord(userName, filename);
			//UpdateUtil.updateNum(Integer.parseInt(filename));
		} catch (Exception e) {
		//	e.printStackTrace();
			// TODO: handle exception
			return  new ModelAndView("lucene/show");
		}
		return null;
		}
	//}

	/**
	 * 
	 * 方法描述：读文件并输出
	 * 创建日期：Jan 14, 2009
	 * @param in 文件流
	 * @param filename 文件名称
	 * @param request
	 * @param response
	 */
	private void downFile(InputStream in, String filename,
			HttpServletRequest request, HttpServletResponse response,String filenameone) {
		try {
		    BufferedOutputStream out=new java.io.BufferedOutputStream(response.getOutputStream());   
			byte[] buff = new byte[1024*4];
			int n = 0;
			response.reset(); //
			response.setContentType("application/x-msdownload; charset=utf-8");
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0)
				response.setHeader("Content-Disposition", "attachment; filename="
						+  new String(filename.getBytes("UTF-8"), "ISO8859-1"));//firefox浏览器
			else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
				response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(filename, "UTF-8"));//IE浏览器
			while ((n = in.read(buff))  != -1)
			out.write(buff, 0, n);
			out.flush();
			out.close();
			in.close();
			
			UpdateUtil.updateNum(filenameone);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 方法描述：上传文件夹
	 * 创建日期：Jan 14, 2009
	 * @param request
	 * @param response
	 * @param command
	 * @return
	 */
	public ModelAndView doUpload(HttpServletRequest request,
			HttpServletResponse response) {

	//	String fileDirectory = SysParameter.getParameter("uploadFile.path"); // 设定上存档案目录 
//		String fileDirectory = request.getContextPath();
//		fileDirectory += File.separator + "UploadFile" + File.separator + "temp"
//		+ File.separator;
		
		String fileDirectory =request.getRealPath("/");
		
		java.io.File di = new java.io.File(fileDirectory);
		if (!di.exists()) {
			di.mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);// 设置缓冲

		// 用以上工厂实例化上传组件
		ServletFileUpload sfu = new ServletFileUpload(factory);
		List fileList = null;
		try {
			request.setCharacterEncoding("utf-8");
			// 得到所有的表单域
			fileList = sfu.parseRequest(request);
		} catch (FileUploadException e) {// 处理文件尺寸过大异常
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 得到所有上传的文件
		for (Iterator iterator = fileList.iterator(); iterator.hasNext();) {
			FileItem fileItem = (FileItem) iterator.next();
			String path = null;
			long size = 0;
			// 忽略普通属性不是上传域的文件域
			if (fileItem.isFormField()) {
				continue;
			}
			// 得到文件的完整路径
			path = fileItem.getName();
			// 得到文件的大小
			size = fileItem.getSize();
			// 得到去除路径的文件名
			String filename=path;
			
//			String filename = path.substring(fileItem.getName().lastIndexOf(
//					"\\"), path.length());
			// 得到文件的扩展名(无扩展名时将得到全名)
			String filename_ext = path.substring(fileItem.getName()
					.lastIndexOf("."), path.length());

			File file = new File(fileDirectory + filename);
			try {
				fileItem.write(file);
				// 保存文件
				
				//提示流没有关闭
				List<Point> list=ExtractPoint.getList(file);
				
				ConvertFromListToXmlUtil.convert(list, fileDirectory+"/aaa.xml");
				request.setAttribute("url",fileDirectory+"/aaa.xml");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 取得服务器中已有文件的下載列表
//		List fileListInServer = new ArrayList();
//		File dir = new File(fileDirectory);
//		String[] children = dir.list();
//		if (children != null) {
//			for (int i = 0; i < children.length; i++) {
//				fileListInServer.add(children[i]);
//			}
//		}
//		request.setAttribute("downloadList", fileListInServer);
//		return new ModelAndView("demo/fileupload/fileupload");
		
		return  new ModelAndView("zhipu/zhipu") ;
	}

	public RecordManager getRecordManager() {
		return recordManager;
	}

	public void setRecordManage(RecordManager recordManage) {
		this.recordManager = recordManager;
	}

	public FmptJfManager getFmptJfmanager() {
		return fmptJfmanager;
	}

	public void setFmptJfmanager(FmptJfManager fmptJfmanager) {
		this.fmptJfmanager = fmptJfmanager;
	}

	public SysUsersManager getSysUsersManager() {
		return sysUsersManager;
	}

	public void setSysUsersManager(SysUsersManager sysUsersManager) {
		this.sysUsersManager = sysUsersManager;
	}

}
