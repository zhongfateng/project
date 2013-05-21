package com.nbw.common.util.action.fileupload;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwLittleController;
import com.nbw.common.util.GUID;

public class FileUpload extends NbwLittleController {

	@SuppressWarnings("deprecation")
	public ModelAndView doUploadFile(HttpServletRequest request,
			HttpServletResponse response) {
		String filetype=request.getParameter("filename");
		String fileDirectory = request.getRealPath("");
		fileDirectory += File.separator + "UploadFile" + File.separator + "temp"
				+ File.separator;
		java.io.File di = new java.io.File(fileDirectory);
		if (!di.exists()){
			di.mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);// 璁剧疆缂撳啿

		ServletFileUpload sfu = new ServletFileUpload(factory);
		List fileList = null;
		try {
			request.setCharacterEncoding("utf-8");
			fileList = sfu.parseRequest(request);
		} catch (FileUploadException e) {// 澶勭悊鏂囦欢灏哄杩囧ぇ寮傚父
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Iterator iterator = fileList.iterator(); iterator.hasNext();) {
			FileItem fileItem = (FileItem) iterator.next();
			String path = null;
			long size = 0;

			if (fileItem.isFormField()) {
				continue;
			}

			path = fileItem.getName();

			size = fileItem.getSize();

			String filename_ext = path.substring(fileItem.getName()
					.lastIndexOf("."), path.length());
			String id = new GUID().toString();
			File file = new File(fileDirectory + id
					+ filename_ext);
			try {
				fileItem.write(file);// 
				response.getWriter().write(id + filename_ext);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
