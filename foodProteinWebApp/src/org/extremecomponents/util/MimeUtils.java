// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MimeUtils.java

package org.extremecomponents.util;

import java.io.File;
import java.io.PrintStream;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;

public class MimeUtils
{

	public static final String MIMETYPES_PROPERTIES = "mimeTypes.properties";
	private static Properties properties;

	public MimeUtils()
	{
	}

	public static String getFileMimeType(File file)
	{
		if (file == null)
			return null;
		else
			return getFileMimeType(file.getName());
	}

	public static String getFileMimeType(String fileName)
	{
		if (StringUtils.isBlank(fileName) || fileName.indexOf(".") == -1)
		{
			return null;
		} else
		{
			fileName = fileName.substring(fileName.lastIndexOf("."));
			return getExtensionMimeType(fileName);
		}
	}

	public static String getExtensionMimeType(String extension)
	{
		String result = null;
		if (StringUtils.isBlank(extension))
			return result;
		init();
		extension = extension.toLowerCase();
		if (!extension.startsWith("."))
			extension = "." + extension;
		result = (String)properties.get(extension);
		return result;
	}

	private static void init()
	{
		if (properties != null)
			return;
		try
		{
			properties = new Properties();
			properties.load((new MimeUtils()).getClass().getResourceAsStream("mimeTypes.properties"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String args[])
	{
		System.out.println("MimeUtils.getExtensionMimeType(.gif)=" + getExtensionMimeType(".gif"));
		System.out.println("MimeUtils.getExtensionMimeType(.pdf)=" + getExtensionMimeType(".pdf"));
		System.out.println("MimeUtils.getExtensionMimeType(.xls)=" + getExtensionMimeType(".xls"));
		System.out.println("MimeUtils.getFileMimeType(foo.gif)=" + getFileMimeType("foo.gif"));
		System.out.println("MimeUtils.getFileMimeType(foo.pdf)=" + getFileMimeType("foo.pdf"));
		System.out.println("MimeUtils.getFileMimeType(foo.xls)=" + getFileMimeType("foo.xls"));
		System.out.println("MimeUtils.getFileMimeType(foo.badextension)=" + getFileMimeType("foo.badextension"));
	}
}
