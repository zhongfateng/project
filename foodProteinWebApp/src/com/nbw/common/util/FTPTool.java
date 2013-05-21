package com.nbw.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import com.nbw.common.SysParameter;
import com.nbw.common.util.tupu.FileFiend;

/**
 * 对FTP服务器进行相关文件操作的类
 * 
 * @author 庞培
 * @since 2006-04-11
 */
public class FTPTool implements java.io.Serializable {
	private static final long serialVersionUID = -5117590265043510111L;
	private FTPClient ftp;
	Logger logger = Logger.getLogger(FTPTool.class.getName());      //取得logger
	public FTPTool() {
		if (ftp == null)
			ftp = new FTPClient();
	}

	/**
	 * 连接文档服务器FTP
	 * 
	 * @return 连接成功标记
	 */
	public boolean connect() {
		if (ftp == null)
			ftp = new FTPClient();
		if (ftp.isConnected())
			return true;
		try {
			ftp.connect(SysParameter.getParameter("ftpHost"));
			ftp.login(SysParameter.getParameter("ftpUser"), SysParameter
					.getParameter("ftpPassword"));
			ftp.setControlEncoding("GBK");
			
			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);   
	        conf.setServerLanguageCode("zh"); 
	        //ftp.enterRemotePassiveMode();
	       // ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
			ftp.setFileType(FTP.BINARY_FILE_TYPE);	
			//ftp.setDataTimeout(timeout)
			ftp.setDataTimeout(24*2*60*60*1000);
			ftp.setDefaultTimeout(24*2*60*60*1000);
			ftp.setSoTimeout(24*2*60*60*1000);
			logger.info("登录成功！");
			//System.out.println("登录成功！");
		} catch (Exception e) {
			logger.info("登录失败！");
			//System.out.println("登录失败！");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 关闭FTP连接
	 */
	public void close() {
		try {
			ftp.disconnect();
			ftp = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得FTP下分级目录结构(包括文件)
	 * 
	 * @param path
	 *            完整路径
	 * @rerurn 各级目录的字符串数组
	 */
	public String[] getDir(String path) {
		int i = 1, flag = 0;
		while (true) {
			flag = path.indexOf(File.separator, flag) + 1;
			if (flag < 1) {
				break;
			}
			i++;
		}
		String[] folders = new String[i];
		int begin = 0, end = 0;
		for (int j = 0; j < i - 1; j++) {
			end = path.indexOf(File.separator, end);
			folders[j] = path.substring(begin, end);
			begin = end + 1;
			end++;
		}
		folders[i - 1] = path.substring(end);
		return folders;
	}

	/**
	 * 获得文件名，如果是目录则返回""
	 * 
	 * @param path
	 *            完整路径
	 * @return 文件名
	 */
	public String getFileName(String path) {
		String fileName;
		String[] folders = this.getDir(path);
		for (int i = 0; i < folders.length; i++) {
			// System.out.println(folders[i]);
		}
		fileName = folders[folders.length - 1];
		if (fileName.indexOf(".") == -1) {
			return "";
		}
		return fileName;
	}

	/**
	 * 获得FTP下分级目录结构(不包括文件)
	 * 
	 * @param path
	 *            完整路径
	 * @return 不包含文件的目录数组
	 */
	public String[] getFolders(String path) {
		String fileName;
		String[] folders;
		String[] dir = this.getDir(path);
		fileName = dir[dir.length - 1];
		if (fileName.indexOf(".") == -1) {
			return dir;
		} else {
			folders = new String[dir.length - 1];
			for (int i = 0; i < dir.length - 1; i++) {
				folders[i] = dir[i];
			}
		}
		return folders;
	}

	/**
	 * 建立目录
	 * 
	 * @param path
	 *            待建目录地址
	 * @return 是否成功
	 */
	public boolean mkDir(String path) {
		String[] folders = this.getFolders(path);
		try {
			for (int i = 0; i < folders.length; i++) {
				ftp.makeDirectory(folders[i]);
				ftp.changeWorkingDirectory(folders[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 改变目录
	 * 
	 * @param path
	 *            目的目录地址
	 * @return 是否成功
	 */
	public boolean changeDir(String path) {

		String[] folders = this.getFolders(path);

		boolean flag = false;
		try {
			boolean s = ftp.changeWorkingDirectory(folders[0]);
			for (int i = 0; i < folders.length; i++) {
				flag = ftp.changeWorkingDirectory(folders[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}

	/**
	 * 删除目录
	 * 
	 * @param 待删目录
	 */
	public boolean deleteDir(String path) {
		String[] folders = this.getFolders(path);
		if (!this.changeDir(path)) {
			return false;
		}
		try {
			FTPFile[] files = ftp.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					ftp.deleteFile(files[i].getName());
				} else {
					if (files[i].getName().equals(".")
							|| files[i].getName().equals("..")) {
					} else {
						path = path + "/" + files[i].getName();
						this.deleteDir(path);
					}
				}
			}
			ftp.changeToParentDirectory();
			ftp.removeDirectory(folders[folders.length - 1]);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 上传文件
	 * 
	 * @param path
	 *            上传文件的完整路径
	 * @param in
	 *            上传文件的输入流
	 * @return 是否成功
	 */
	public boolean storeFile(String path, InputStream in) {
		boolean flag = false;
		try {
			// ftp.setFileType(FTP.BINARY_FILE_TYPE);
			flag = ftp.storeFile(path, in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 下载文件
	 * 
	 * @param path
	 *            下载文件保存的路径
	 * @param os
	 *            下载到本地的文件接收流
	 * @return 下载是否成功
	 */
	public boolean downloadFile(String path, OutputStream os) {
		boolean flag = false;
		try {
		//	ftp.setFileType(FTP.BINARY_FILE_TYPE);
			path = new String(path.getBytes("GBK"), "iso-8859-1");
			flag = ftp.retrieveFile(path, os);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	/**
	 * 下载文件2
	 * 
	 * @return 下载是否成功
	 */
	public boolean bzDownloadFile(String remotePath,String path, OutputStream os) {
		boolean flag = false;
		try {
		//	ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.changeWorkingDirectory(remotePath);
			path = new String(path.getBytes("GBK"), "iso-8859-1");
			flag = ftp.retrieveFile(path, os);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	/**
	 * 下载文件流
	 * 
	 */
	public InputStream downloadFileStream(String path) {
		InputStream in = null;
		try {
			// ftp.setFileType(FTP.BINARY_FILE_TYPE);
			in = ftp.retrieveFileStream(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 *            要删除文件的路径
	 * @return 删除是否成功
	 */
	public boolean deleteFile(String path) {
		boolean flag = false;
		try {
			flag = ftp.deleteFile(path);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	
	/**
	 * 判断文件名是否存在
	 * 
	 * @param path
	 *            要删除文件的路径
	 * @return 删除是否成功
	 */
	public int fileIsExits(String path) {
		int flag = 0;//0为ftp报错，1为成功，2为路径不存在文件，3为存在文件文件大小为0
		try {
			FTPFile[] ftpFiles = ftp.listFiles(path);
			System.out.println("ftpFiles.length="+ftpFiles.length);
			System.out.println("ftpFiles.size="+ftpFiles[0].getSize());
			if (ftpFiles.length>0){
				flag=1;
				if (ftpFiles[0].getSize()<=0){
					flag=3;
				}
			}else{
				flag=2;
			}
		} catch (SocketException e){
			logger.error("SocketException，链接被重置！");
			e.printStackTrace();
			flag=-1;
		}catch (IOException e) {
			logger.error("IOException网络连接出现问题！");
			e.printStackTrace();
			flag=0;
		}
		return flag;
	}
	
	public  int filesExits(String filepath){
		//FTPTool ftp = new FTPTool();
		//ftp.connect();
		//ftp.changeDir("data\\STDATT\\original"+contents);
		int isexits=this.fileIsExits(filepath);
	    //ftp.close();
		return isexits;
	}
	
	
	
	public static void main (String[] args){
		FTPTool ftp = new FTPTool();
		ftp.connect();
		int isexits=ftp.fileIsExits("/data/STDATT/original/IS/201201131731027517480083d5fe84d.pdf");
		if (isexits==1){
			System.out.println("201201131731027517480083d5fe84d.pdf存在且大小不为0");
		}else if (isexits==2){
			System.out.println("201201131731027517480083d5fe84d.pdf不存在");
		}else if (isexits==3){
			System.out.println("201201131731027517480083d5fe84d.pdf存在但是大小为0");
		}
	//	System.out.println(FileFiend.readFile(ftp.downloadFileStream("/tupu/1.txt"),ENCODE));
		
//		isexits=ftp.fileIsExits("/data/STDATT/original/GB/2011070717350285601071f6d80944e.pdf");
//		if (isexits==1){
//			System.out.println("2011070717350285601071f6d80944e.pdf存在且大小不为0");
//		}else if (isexits==2){
//			System.out.println("2011070717350285601071f6d80944e.pdf不存在");
//		}else if (isexits==3){
//			System.out.println("2011070717350285601071f6d80944e.pdf存在但是大小为0");
//		}
		////data/STDATT/original/IS/201106271714373789c2fe15a185b4c.pdf
		///data/STDATT/original/WTO/2011071915324015972d33ff5164b4b.pdf
//		int isexits1=FTPTool.filesExits("data/STDATT/original/WTO/2011071915324015972d33ff5164b4b.pdf");
//		if (isexits1==1){
//			System.out.println("201106271714373789c2fe15a185b4c.pdf存在且大小不为0");
//		}else if (isexits1==2){
//			System.out.println("201106271714373789c2fe15a185b4c.pdf不存在");
//		}else if (isexits1==3){
//			System.out.println("201106271714373789c2fe15a185b4c.pdf存在但是大小为0");
//		}
//		int isexits=FTPTool.filesExits("\\FULLTEXT\\","2012010416553344629401950eee448.pdf");
//		if (isexits==1){
//			System.out.println("2012010416553344629401950eee448.pdf存在且大小不为0");
//		}else if (isexits==2){
//			System.out.println("2012010416553344629401950eee448.pdf不存在");
//		}else if (isexits==3){
//			System.out.println("2012010416553344629401950eee448.pdf存在但是大小为0");
//		}
	}
}
