// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportResponseWrapper.java

package org.extremecomponents.table.filter;

import java.io.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public final class ExportResponseWrapper extends HttpServletResponseWrapper
{
	private static class FilterServletOutputStream extends ServletOutputStream
	{

		private DataOutputStream stream;

		public void write(int b)
			throws IOException
		{
			stream.write(b);
		}

		public void write(byte b[])
			throws IOException
		{
			stream.write(b);
		}

		public void write(byte b[], int off, int len)
			throws IOException
		{
			stream.write(b, off, len);
		}

		public FilterServletOutputStream(OutputStream output)
		{
			stream = new DataOutputStream(output);
		}
	}


	private ByteArrayOutputStream output;
	private int contentLength;
	private String contentType;

	public ExportResponseWrapper(HttpServletResponse response)
	{
		super(response);
		output = new ByteArrayOutputStream();
	}

	public byte[] getData()
	{
		return output.toByteArray();
	}

	public ServletOutputStream getOutputStream()
	{
		return new FilterServletOutputStream(output);
	}

	public PrintWriter getWriter()
	{
		return new PrintWriter(getOutputStream(), true);
	}

	public void setContentLength(int length)
	{
		contentLength = length;
		super.setContentLength(length);
	}

	public int getContentLength()
	{
		return contentLength;
	}

	public void setContentType(String type)
	{
		contentType = type;
		super.setContentType(type);
	}

	public String getContentType()
	{
		return contentType;
	}
}
