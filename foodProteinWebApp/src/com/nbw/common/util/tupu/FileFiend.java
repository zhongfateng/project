package com.nbw.common.util.tupu;

/**
 * Program  : OperateModulesOrientedI.java
 * Author   : ices.x
 * Create   : 2006-4-28 10:15:07
 *
 * Copyright 2006 by Embedded Internet Solutions Inc.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Embedded Internet Solutions Inc.("Confidential Information").  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with Embedded Internet Solutions Inc.
 *
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;

import com.nbw.common.util.FTPTool;


/**
 * �����ļ��Ļ����
 * 
 * @author ices.x 2005.10.10
 * @version 2005.12.16_02.
 * @version 2006.2.22 add getAllFiles
 * @version 2006.4.3 add FILE_SEPARATOR;
 * @version 2006.4.22 add getSize();
 */
public class FileFiend {
	public static final String FILE_SEPARATOR = System
			.getProperty("file.separator");
	private static int ARRAY_LENGTH = 8096;

	private final static Logger logger = Logger.getLogger(FileFiend.class);

	/**
	 * ɾ��ָ�����ļ��С�
	 * 
	 * @param directoryPath
	 * @return �Ƿ�ɹ� �ɹ�=true.
	 */
	public static boolean deleteDirectory(String directoryPath) {
		File file;
		File[] files;
		file = new File(directoryPath);
		if (file != null) {
			if (file.exists() && file.isDirectory()) {
				files = file.listFiles();
				if (files != null) {	
					for (int i = 0; i < files.length; i++) {
						if (files[i].exists()) {
							if (files[i].isDirectory()) {
								deleteDirectory(files[i].getPath());
							} else if (files[i].isFile()) {
								boolean isHad = files[i].delete();
								if (!isHad) {
									logger.info("delete file <<<<<<<faile>>>>>>>:"
											+ files[i]);
									return false;
								}
							}
						}
					}
					file.delete();
				}
			} else if (!file.exists() && file.isDirectory()) {
				logger.info("directory do not exists!:" + directoryPath
						+ " at _File.deleteDirectory()");
				return true;
			}
		} else {
			logger.info("file Object is null!:path=" + directoryPath);
			return false;
		}
		return true;
	}

	/**
	 * ɾ��ָ�����ļ�
	 * 
	 * @param filePath
	 * @return int:1=delete sucess;0=delete <<<<<<<faile>>>>>>>;2=no such file;
	 */
	public static int deleteFile(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			if (file != null) {
				if (file.exists()) {
					if (file.delete()) {
						logger.info("delete the file sucess: " + filePath);
						return 1;
					} else {
						logger.info("delete the file <<<<<<<faile>>>>>>>: "
								+ filePath);
						return 0;
					}
				} else {
					logger.info("this file do not existe :" + filePath);
					return 2;
				}
			} else {
				logger.info("file Object is null!:path=" + filePath);
				return 2;
			}
		} catch (Exception e) {
			logger.error(e, e);
		} finally {
			file = null;
		}
		return 2;
	}

	/**
	 * ��String�д����ļ��������ļ����ڸ��ǵ������ڴ������ļ���
	 * 
	 * @param filePath
	 * @param fileData
	 * @return д�ļ��Ƿ�ɹ�.
	 * @throws IOException
	 */
	public static boolean writeFile(String filePath, String fileData,
			String charsetName) {
		if (filePath == null || fileData == null) {
			logger.info("the fileName or fileData is null: fileName="
					+ filePath + " fileData=" + fileData);
			return false;
		} else if (filePath.equals("") || filePath.trim().equals("")) {
			logger.info("the fileName or fileData is   : fileName=" + filePath
					+ " fileData=" + fileData);
			return false;
		}
		FileOutputStream fileOutputStream = null;
		try {
			byte[] data = fileData.getBytes(charsetName);
			File file = new File(filePath);
			if (!file.exists()) {
				logger.info("this file is not exist!:" + filePath);
				file.createNewFile();
				logger.info("creat file!:" + filePath);
			}
			fileOutputStream = new FileOutputStream(filePath);
			fileOutputStream.write(data);
			fileOutputStream.close();
			logger.info("write file:" + filePath);
			return true;
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					logger.error(e.toString());
					logger.error("writeFile(String, String, String)", e); //$NON-NLS-1$
				}
			}
		}
		return false;
	}

	/**
	 * ��byte[]�����д���ļ��������ļ����ڸ��ǵ������ڴ������ļ���
	 * 
	 * @param filePath
	 *            �ļ�ȫ·��.
	 * @param fileData
	 *            �ļ����.
	 * @return д�ļ��Ƿ�ɹ�.
	 */
	public static boolean writeFile(String filePath, byte[] fileData) {
		if (filePath == null || fileData == null) {
			logger.info("filePath or fileData is null");
			return false;
		} else if (filePath.trim().equals("")) {
			logger.info("filePath is \"\"!");
			return false;
		}
		FileOutputStream write = null;
		try {
			write = new FileOutputStream(filePath);
			write.write(fileData);
			logger.info("write file:" + filePath + " success!");
			return true;
		} catch (FileNotFoundException e) {
			logger.error(e, e);
		} catch (IOException e) {
			logger.error(e, e);
		} finally {
			if (write != null)
				try {
					write.close();
				} catch (IOException e) {
					logger.error(e, e);
				}
		}
		return false;
	}

	/**
	 * ��String�д����ļ��������ļ����ڸ��ǵ������ڴ������ļ�,����ļ�·���ϵ�Ŀ¼û���򴴽���Ŀ¼��
	 * 
	 * @param filePath
	 * @param fileData
	 * @return д�ļ��Ƿ�ɹ�.
	 * @throws IOException
	 */
	public static boolean directWriteFile(String filePath, String fileData,
			String charsetName) {
		if (filePath == null || fileData == null) {
			logger.info("the fileName or fileData is null: fileName="
					+ filePath + " fileData=" + fileData);
			return false;
		} else if (filePath.equals("") || filePath.trim().equals("")) {
			logger.info("the fileName or fileData is   : fileName=" + filePath
					+ " fileData=" + fileData);
			return false;
		}
		String fileDir = filePath.substring(0,
				filePath.lastIndexOf(System.getProperty("file.separator")));
		boolean flag = makeDirectory(fileDir);
		if (!flag) {
			return false;
		}
		FileOutputStream fileOutputStream = null;
		try {
			byte[] data = fileData.getBytes(charsetName);
			File file = new File(filePath);
			if (!file.exists()) {
				logger.info("this file is not exist!:" + filePath);
				file.createNewFile();
				logger.info("creat file!:" + filePath);
			}
			fileOutputStream = new FileOutputStream(filePath);
			fileOutputStream.write(data);
			fileOutputStream.close();
			logger.info("write file:" + filePath);
			return true;
		} catch (FileNotFoundException e) {
			logger.error(e, e);
		} catch (IOException e) {
			logger.error(e, e);
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					logger.error(e, e);
					logger.error("directWriteFile(String, String, String)", e); //$NON-NLS-1$
				}
			}
		}
		return false;
	}

	/**
	 * ��byte[]�����д���ļ��������ļ������򸲸ǵ������ڴ������ļ�,Ŀ¼������Ҳֱ�Ӵ�����Ŀ¼��
	 * 
	 * @param filePath
	 *            �ļ�ȫ·��.
	 * @param fileData
	 *            �ļ����.
	 * @return д�ļ��Ƿ�ɹ�.
	 */
	public static boolean directWriteFile(String filePath, byte[] fileData) {
		if (filePath == null || fileData == null) {
			logger.info("filePath or fileData is null");
			return false;
		} else if (filePath.trim().equals("")) {
			logger.info("filePath is \"\"!");
			return false;
		}
		String fileDir = filePath.substring(0,
				filePath.lastIndexOf(System.getProperty("file.separator")));
		boolean flag = makeDirectory(fileDir);
		if (!flag) {
			return false;
		}
		FileOutputStream write = null;
		try {
			write = new FileOutputStream(filePath);
			write.write(fileData);
			logger.info("write file:" + filePath + " success!");
			return true;
		} catch (FileNotFoundException e) {
			logger.error(e, e);
		} catch (IOException e) {
			logger.error(e, e);
		} finally {
			if (write != null)
				try {
					write.close();
				} catch (IOException e) {
					logger.error(e, e);
				}
		}
		return false;
	}

	/**
	 * ��ָ�����ļ���ѹ��ָ�����ļ��У���ѹ����ļ���Ŀ¼�͸��ѹ���ļ�����ͬ.
	 * 
	 * @param zipFilePath
	 *            ȫ·��
	 * @param unZipDirectory
	 *            ȫ·��
	 * @return ��ѹ���ļ��Ƿ�ɹ�.
	 * @throws IOException
	 */
	public static boolean unZipFile(String zipFilePath, String unZipDirectory)
			throws IOException {
		ZipFile zipFile = new ZipFile(zipFilePath);
		Enumeration entries = zipFile.entries();
		if (zipFile == null) {
			return false;
		}
		while (entries.hasMoreElements()) {
			ZipEntry zipEntry = (ZipEntry) entries.nextElement();
			File f = new File(unZipDirectory + FILE_SEPARATOR
					+ zipEntry.getName());
			if (zipEntry.isDirectory()) {
				if (!f.exists() && !f.mkdirs())
					throw new IOException("Couldn't create directory: " + f);
			} else {
				BufferedInputStream is = null;
				BufferedOutputStream os = null;
				try {
					is = new BufferedInputStream(
							zipFile.getInputStream(zipEntry));
					File destDir = f.getParentFile();
					if (!destDir.exists() && !destDir.mkdirs()) {
						throw new IOException("Couldn't create dir " + destDir);
					}
					os = new BufferedOutputStream(new FileOutputStream(f));
					int b = -1;
					while ((b = is.read()) != -1) {
						os.write(b);
					}
					os.flush();
				} finally {
					if (is != null)
						is.close();
					if (os != null)
						os.close();
				}
			}
		}
		return true;
	}

	/**
	 * ������ļ�cut��ָ����ȫ·��������·���������Զ�������·����
	 * 
	 * @param filePath
	 *            ȫ·����
	 * @param toDirectory
	 *            ȫ·����
	 * @return �Ƿ�ɹ� �ɹ�==true.
	 */
	public static boolean cutFile(String filePath, String toDirectory) {
		if (filePath == null || toDirectory == null) {
			logger.info("the filePath or toDirectory is null ! when cut this file: "
					+ filePath + "---- to:" + toDirectory);
			return false;
		} else if (filePath.trim().equals("") || toDirectory.trim().equals("")) {
			logger.info("the filePath or toDirectory is \"\"! when cut this file: "
					+ filePath + "---- to:" + toDirectory);
			return false;
		}
		if (copyFile(filePath, toDirectory)) {
			int delte = deleteFile(filePath);
			if (delte == 1) {
				return true;
			} else {
				logger.info("copy the file sucess form "
						+ filePath
						+ "----to:"
						+ toDirectory
						+ " but delete this file <<<<<<<faile>>>>>>> when cut this file.");
				return false;
			}
		} else {
			logger.info("copy the file <<<<<<<faile>>>>>>> form " + filePath
					+ "----to:" + toDirectory + " when cut this file.");
			return false;
		}
	}

	/**
	 * ��ָ�����ļ���cut��ָ����·���£�����·�������ڣ����ܹؼ��·����
	 * 
	 * @param directory
	 *            ȫ·��
	 * @param toDirectory
	 *            ȫ·��
	 * @return �Ƿ�ɹ� �ɹ�==true.
	 */
	public static boolean cutDirectory(String directory, String toDirectory) {
		if (copyDirectory(directory, toDirectory)) {
			boolean isDelete = deleteDirectory(directory);
			if (isDelete) {
				logger.info("cut directory success: form"
						+ " directory ---- to " + toDirectory);
				return true;
			} else {
				logger.info("copy directory sucess but delete <<<<<<<faile>>>>>>> when cut the directory form"
						+ " directory ---- to " + toDirectory);
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * �ж����ļ�ϵͳ���Ƿ���ڴ��ļ���
	 * 
	 * @param filePath
	 *            ȫ·��
	 * @return �ļ���ϵͳ��=true.
	 */
	public static boolean isHave(String filePath) {
		if (filePath == null) {
			logger.info("not know file isHave .filePath is null! at _File.isHave");
			return true;
		} else if (filePath.trim().equals("")) {
			logger.info("not know file isHave . filePath is \"\"! at _File.isHave");
			return true;
		}
		File file = new File(filePath);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * ��ָ�����ļ�copy��ָ����·���£�����·�������ڽ�������·����
	 * 
	 * @param filePath
	 *            ȫ·��
	 * @param toDirectoryPath
	 *            ȫ·��
	 * @return �Ƿ�ɹ� �ɹ�==true.
	 */
	public static boolean copyFile(String filePath, String toDirectoryPath) {
		if (filePath == null || toDirectoryPath == null) {
			logger.info("the filePath or toDirectory is null at _File.copyFile()");
			return false;
		} else if (filePath.trim().equals("")
				|| toDirectoryPath.trim().equals("")) {
			logger.info("the filePath or toDirectory is \"\" at _File.copuFile()");
			return false;
		}
		File directory = new File(toDirectoryPath);
		if (!directory.exists()) {
			makeDirectory(toDirectoryPath);
		}

		File file = new File(filePath);
		String toFilePath = toDirectoryPath + FILE_SEPARATOR + file.getName();
		File toFile = new File(toFilePath);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			if (!file.isFile()) {
				logger.info("can not get FileInputStream from this file:"
						+ filePath);
				return false;
			}
			fis = new FileInputStream(file);
			fos = new FileOutputStream(toFile);
			byte[] data = new byte[fis.available()];
			fis.read(data);
			fos.write(data);
			fos.flush();
			logger.debug("copy file file:" + file + "-----to:"
					+ toDirectoryPath);
			return true;
		} catch (FileNotFoundException e) {
			logger.error(e, e);
		} catch (IOException e) {
			logger.error(e, e);
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (fis != null)
					fis.close();
				file = null;
				toFile = null;
			} catch (IOException e) {
				logger.error(e, e);
			}

		}
		return false;
	}

	/**
	 * ��ָ�����ļ�copy��ָ����·���£�����·�������ڽ�������·����
	 * 
	 * @param sfilePath
	 *            ȫ·��
	 * @param toDirectoryPath
	 *            ȫ·��
	 * @return �Ƿ�ɹ� �ɹ�==true.
	 */
	public static boolean copyFileByFilePath(String sfilePath, String dfilePath) {
		if (sfilePath == null || dfilePath == null) {
			logger.info("the filePath is null at _File.copyFile()");
			return false;
		} else if (sfilePath.trim().equals("") || dfilePath.trim().equals("")) {
			logger.info("the filePath is \"\" at _File.copuFile()");
			return false;
		}
		String toFileDir = dfilePath.substring(0,
				dfilePath.lastIndexOf(System.getProperty("file.separator")));
		boolean flag = makeDirectory(toFileDir);
		if (!flag) {
			return false;
		}
		File file = new File(sfilePath);
		File toFile = new File(dfilePath);
		try {
			if (!file.isFile()) {
				logger.info("can not get FileInputStream from this file:"
						+ sfilePath);
				return false;
			}
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(toFile);
			byte[] data = new byte[fis.available()];
			int bytesRead;
			while ((bytesRead = fis.read(data)) != -1) {
				fos.write(data, 0, bytesRead);
			}
			fos.flush();
			fos.close();
			fis.close();
			logger.info("copy file file:" + file + "-----to:" + dfilePath);
			return true;
		} catch (FileNotFoundException e) {
			logger.error(e, e);
		} catch (IOException e) {
			logger.error(e, e);
		}
		return false;
	}

	/**
	 * ��ָ�����ļ���copy��ָ����·���£����ָ�����ƶ���·�������ڣ�������·��;ָ����·�����ڽ�����.
	 * 
	 * @param directoryPath
	 *            ȫ·��
	 * @param toDirectoryPath
	 *            ȫ·��
	 * @return �Ƿ�ɹ� �ɹ�==true.
	 */
	public static boolean copyDirectory(String directoryPath,
			String toDirectoryPath) {
		File file;
		File[] files;
		file = new File(directoryPath);
		if (file.exists() && file.isDirectory()) {
			files = file.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].exists()) {
						if (files[i].isDirectory()) {
							copyDirectory(files[i].getPath(), toDirectoryPath
									+ FILE_SEPARATOR + files[i].getName());
						} else if (files[i].isFile()) {
							copyFile(files[i].getPath(), toDirectoryPath);
						}
					}
				}
			}
		} else {
			logger.info("file not exists or is not directory!");
			return false;
		}
		logger.debug("copy directory form:" + directoryPath + "to :"
				+ toDirectoryPath + ".");
		return true;
	}

	/**
	 * �Ӹ���ַ��д����ļ�ϵͳ·����
	 * 
	 * @param directory
	 *            ���·����ʾ�ַ�
	 * @return �Ƿ�ɹ� �ɹ�==true.
	 */
	public static boolean makeDirectory(String directory) {
		File file = new File(directory);
		if (!file.exists()) {
			if (file.mkdirs()) {
				logger.debug("make dirctory success!:" + directory);
				file = null;
				return true;
			} else {
				logger.error("make dirctory <<<<<<<faile>>>>>>>!:" + directory);
				file = null;
				return false;
			}
		} else {
			logger.debug("this directory is existed!:" + directory);
			file = null;
			return true;
		}
	}

	/**
	 * ���ļ��жs���ݷŵ�String��.
	 * 
	 * @param filePath
	 *            �ļ�·��.
	 * @param encoding
	 *            �����ʽ.
	 * @return �ļ���String.
	 */
	public static String readFile(String filePath, String encoding) {
		String contents = null;
		FileInputStream fissrc = null;
		InputStream stream = null;
		try {
			fissrc = new FileInputStream(filePath);
			int len = fissrc.available();
			byte[] data = new byte[len];
			int actual = 0;
			int bytesread = 0;
			while ((bytesread != len) && (actual != -1)) {
				actual = fissrc.read(data, bytesread, len - bytesread);
				bytesread += actual;
			}
			contents = new String(data, encoding);
		} catch (FileNotFoundException e) {
			//logger.error("readFile(String, String)", e); //$NON-NLS-1$
			URL url = FileFiend.class.getClassLoader().getResource(filePath);
			try {
				stream = url.openStream();
				int len = stream.available();
				byte[] data = new byte[len];
				int actual = 0;
				int bytesread = 0;
				while ((bytesread != len) && (actual != -1)) {
					actual = stream.read(data, bytesread, len - bytesread);
					bytesread += actual;
				}
				contents = new String(data, encoding);
			} catch (IOException e1) {
				logger.error("readFile(String, String)", e); //$NON-NLS-1$
			}
		} catch (IOException e) {
			logger.error("readFile(String, String)", e); //$NON-NLS-1$
		} finally {
			if (fissrc != null)
				try {
					fissrc.close();
				} catch (IOException e) {
					logger.error("readFile(String, String)", e); //$NON-NLS-1$
				}
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					logger.error("readFile(String, String)", e); //$NON-NLS-1$
				}
			}
		}

		return contents;
	}

	public static String readFile(InputStream inputStream, String encoding) {
		String contents = null;
		try {
			int len = inputStream.available();
			byte[] data = new byte[len];
			int actual = 0;
			int bytesread = 0;
			while ((bytesread != len) && (actual != -1)) {
				actual = inputStream.read(data, bytesread, len - bytesread);
				bytesread += actual;
			}
			contents = new String(data, encoding);
		} catch (IOException e) {
			logger.error("readFile(String, String)", e); //$NON-NLS-1$
		}
		return contents;
	}

	/**
	 * ������е��ļ���.
	 * 
	 * @param directoryPath
	 * @return
	 */
	public static ArrayList getAllFiles(String directoryPath) {
		ArrayList allFiles = new ArrayList();
		if (!isHave(directoryPath)) {
			return allFiles;
		} else {
			File file = new File(directoryPath);
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory())
						allFiles.addAll(getAllFiles(files[i].getAbsolutePath()));
					else
						allFiles.add(files[i].getAbsoluteFile().toString());
				}

				return allFiles;
			} else {
				return allFiles;
			}
		}
	}

	/**
	 * ������ļ����ļ��еػ�ȡ���С.
	 * 
	 * @param path
	 *            �ļ������ļ��еĴ�С.
	 * @return long ���͵Ĵ�С bytes
	 * @throws FileNotFoundException
	 * @since 2006.4.22
	 */
	public static long getSize(File file) throws FileNotFoundException {
		if (file.isFile()) {
			return getFileSize(file);
		} else {
			return getDirectorySize(file, 0);
		}
	}

	/**
	 * ����һ���ļ��Ĵ�С.
	 * 
	 * @param filePath
	 *            �ļ���ȫ·��.
	 * @return long bytes value �ļ���С.
	 * @throws FileNotFoundException
	 * @since 2006.4.22
	 */
	public static long getFileSize(File file) throws FileNotFoundException {
		if (!file.exists()) {
			throw new FileNotFoundException("this file is not exited: " + file);
		}
		return file.length();
	}

	/**
	 * ����һ���ļ��еĴ�С.
	 * 
	 * @param directoryPath
	 *            �ļ��е�ȫ·��.
	 * @return �ļ��д�С.
	 * @throws FileNotFoundException
	 * @since 2006.4.22
	 */
	public static long getDirectorySize(String directoryPath)
			throws FileNotFoundException {
		return getDirectorySize(new File(directoryPath), 0);
	}

	/**
	 * ����һ���ļ��еĴ�С.
	 * 
	 * @param directoryPath
	 *            �ļ��е�ȫ·��.
	 * @return �ļ��д�С.
	 * @throws FileNotFoundException
	 * @since 2006.4.22
	 */
	public static long getDirectorySize(File directoryPath)
			throws FileNotFoundException {
		return getDirectorySize(directoryPath, 0);
	}

	/**
	 * �ݹ�Ļ�ȡ�ļ��е�ÿһ���ļ��Ĵ�С,�������ܵĴ�С.
	 * 
	 * @param directoryPath
	 *            �ļ��д�С.
	 * @param size
	 *            �ݹ����
	 * @return long bytes value;
	 * @throws FileNotFoundException
	 * @since 2006.4.22
	 */
	private static long getDirectorySize(File directoryPath, long size)
			throws FileNotFoundException {
		File[] files = directoryPath.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				size = getDirectorySize(files[i], size);
			} else {
				size += getFileSize(files[i]);
			}
		}
		return size;
	}

	/**
	 * @author xiaobao
	 * @param filePath
	 * @return long bytes value;
	 * @since 1.0.0 ���ļ��жs���ݷŵ�byte��.
	 * @createTime 2006-11-06 13:50:20
	 */
	public static byte[] readFileByByte(String filePath) {
		byte[] data = null;
		FileInputStream fissrc = null;
		try {
			fissrc = new FileInputStream(filePath);
			int len = fissrc.available();
			data = new byte[len];
			int actual = 0;
			int bytesread = 0;
			while ((bytesread != len) && (actual != -1)) {
				actual = fissrc.read(data, bytesread, len - bytesread);
				bytesread += actual;
			}
		} catch (FileNotFoundException e) {
			logger.error("readFileByByte(String)", e); //$NON-NLS-1$
		} catch (IOException e) {
			logger.error("readFileByByte(String)", e); //$NON-NLS-1$
		} finally {
			try {
				if (fissrc != null)
					fissrc.close();
			} catch (IOException e) {
				logger.error("close FileInputStream", e); //$NON-NLS-1$
			}
		}
		return data;
	}

	public static void copyStreamToFile(InputStream inputStream, String file)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		byte[] data = new byte[inputStream.available()];
		inputStream.read(data);
		fos.write(data);
		fos.flush();
		fos.close();
	}

	public static byte[] readFileInClassLoader(String path) {
		InputStream inputStream = null;
		byte[] bs = null;
		try {
			inputStream = ClassLoader.getSystemResource(path).openStream();
			bs = new byte[inputStream.available()];
			inputStream.read(bs);
		} catch (IOException e1) {
			logger.error("openStream  errer");
			e1.printStackTrace();
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				logger.error("close openStream is errer");
				e.printStackTrace();
			}
		}

		return bs;
	}

	public static byte[] getMergeBytes(byte[] pByteA, byte[] pByteB) {
		int aCount = pByteA.length;
		int bCount = pByteB.length;
		byte[] b = new byte[aCount + bCount];
		for (int i = 0; i < aCount; i++) {
			b[i] = pByteA[i];
		}
		for (int i = 0; i < bCount; i++) {
			b[aCount + i] = pByteB[i];
		}
		return b;
	}

	public static byte[] inputStreamToByte(InputStream inputStream) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try{
			byte[] bs = new byte[ARRAY_LENGTH];
			int k = 0;
			while ((k = inputStream.read(bs)) != -1) {
				outputStream.write(bs, 0, k);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(inputStream!=null)
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return outputStream.toByteArray();
	}

	public static void main (String[] args) throws FileNotFoundException{
		FileFiend ff = new FileFiend();
//		FTPTool ftp = new FTPTool();
//		ftp.connect();
//		System.out.println(FileFiend.readFile(ftp.downloadFileStream("/tupu/1.txt"),"UTF-8"));
		String filename = "1.txt";
	//	filename = new  String(filename.getBytes("iso-8859-1"),"utf-8");  
		String fileDirectory = "d:/ftp/tupu/"; // 设定上存档案目录 
		File fileout = new File(fileDirectory+filename);
		InputStream in = new FileInputStream(fileout);
		System.out.println(ff.readFile(in,"UTF-8"));
	}
}
