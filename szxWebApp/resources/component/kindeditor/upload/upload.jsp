<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import = "java.util.*" %>   
<%@ page import = "org.apache.commons.fileupload.*" %>  
<%
  	String contextPath = request.getContextPath();
  	String savePath = request.getSession().getServletContext().getRealPath("/")+"kindeditor-3.2\\attach\\image\\";
  	String saveUrl = contextPath + "/kindeditor-3.2/attach/image/";
  	String[] extArr = new String[] {".gif", ".jpg", ".png", ".bmp"};
  	int maxSize = 100*1024*1024;
  	String msg1 = "上传文件大小超过限制。";
  	String msg2 = "上传文件的扩展名不被允许。";
  	String msg3 = "文件上传失败。";
  	String msg = msg3;
  	String fileWidth = null;	//图片宽度
  	String fileHeight = null;	//图片高度
  	String fileBorder = null;	//图片边框
  	String fileTitle = null;	//图片描述
  	Date date = new Date();	
  	String fileName = String.valueOf(date.getTime());	//图片名称，取系统时间的毫秒数
  	String filePath = null;	// 图片路径
  	String fileUrl = null;	// 图片URL
  	
  	DiskFileUpload dfu = new DiskFileUpload();
  	dfu.setSizeMax(maxSize);		//设置文件大小
  	dfu.setSizeThreshold(4096);	//设置缓冲区
  	dfu.setRepositoryPath("d:/temp");	//设置临时目录
  	
  	List fileItems = dfu.parseRequest(request);
  	Iterator iter = fileItems.iterator();
  	while (iter.hasNext()) {
  		FileItem item = (FileItem) iter.next();
  		//String fieldName = item.getFieldName();
  		if (!item.isFormField()) {
  			String name = item.getName();
  			long size = item.getSize();
  			if ((name == null || name.equals("")) && size == 0)
  				continue;
  			if (size > maxSize) {
  				msg = msg1;
  				break;
  			}
  			int pos = name.lastIndexOf(".");
  			String ext = name.substring(pos);
  			boolean b = false;
  			for (int m = 0; m < extArr.length; m++) {
  				if (extArr[m].equalsIgnoreCase(ext)) {
  					b = true;
  					break;
  				}
  			}
  			if (b == false) {
  				msg = msg2;
  				break;
  			}
  			filePath = savePath + fileName + ext;
  			
  			fileUrl = saveUrl + fileName + ext;
  			java.io.File f = new java.io.File(filePath);
  			item.write(f);
  		}
  	}
  	if (fileUrl != null) {
  		out.println("<html>");
  		out.println("<head>");
  		out.println("<title>error</title>");
  		out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">");
  		out.println("</head>");
  		out.println("<body>");
  		out.println("<script type=\"text/javascript\">parent.KE.plugin[\"image\"].insert(\"content\",\""
  						+ fileUrl
  						+ "\",\""
  						+ fileTitle
  						+ "\",\""
  						+ fileWidth
  						+ "\",\""
  						+ fileHeight
  						+ "\",\""
  						+ fileBorder + "\");</script>");
  		out.println("</body>");
  		out.println("</html>");
  	} else {
  		out.println("<html>");
  		out.println("<head>");
  		out.println("<title>error</title>");
  		out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">");
  		out.println("</head>");
  		out.println("<body>");
  		out.println("<script type=\"text/javascript\">alert(\""
  						+ msg
  						+ "\");parent.KindDisableMenu();parent.KindReloadIframe();</script>");
  		out.println("</body>");
  		out.println("</html>");
  	}
  %>  
 

