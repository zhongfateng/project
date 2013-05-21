// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExtremeTablePage.java

package org.extremecomponents.table.limit;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.hibernate.Query;
import org.hibernate.Session;

// Referenced classes of package org.extremecomponents.table.limit:
//			TableLimitFactory, TableLimit, Limit, DBLimit

public class ExtremeTablePage
{

	private int maxResult;
	private Limit limit;
	private int pageResultSize;
	private String tableId;
	private HttpServletRequest request;

	public ExtremeTablePage(HttpServletRequest request, int defaultPageResultSize, String tableId)
	{
		maxResult = 2000;
		limit = null;
		pageResultSize = 0;
		this.tableId = null;
		org.extremecomponents.table.context.Context context = new HttpServletRequestContext(request);
		if (tableId == null || "".equals(tableId))
			tableId = "ec";
		this.tableId = tableId;
		LimitFactory limitFactory = new TableLimitFactory(context, tableId);
		pageResultSize = defaultPageResultSize;
		this.request = request;
		TableLimit limit12 = new TableLimit(limitFactory);
		limit = limit12;
	}

	public Limit getLimit()
	{
		return limit;
	}

	private int getTotalRows(Connection cn, String sql)
	{
		int total;
		total = 0;
		Statement stat = null;
		ResultSet rs = null;
		try
		{
			stat = cn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next())
			{
				BigDecimal count = rs.getBigDecimal(1);
				if (count != null)
					total = count.intValue();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (rs != null)
				try
				{
					rs.close();
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			if (stat != null)
				try
				{
					stat.close();
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();
				}
		}
		return total;
	}

	public List getPageCollection(Connection con, String sql[], DBLimit db)
	{
		List list1;
		ResultSet rs = null;
		PreparedStatement stat = null;
		ResultSetMetaData rsda = null;
		int totalRows = getTotalRows(con, sql[0]);
		int currentRows = limit.getCurrentRowsDisplayed();
		if (currentRows == 0)
			currentRows = pageResultSize;
		if (request.getAttribute("totalRows") == null)
			request.setAttribute("totalRows", new Integer(totalRows));
		limit.setRowAttributes(totalRows, currentRows);
		int start = limit.getRowStart();
		int end = limit.getRowEnd();
		if (limit.isExported() && limit.getTotalRows() > maxResult)
		{
			String tnm = request.getParameter(tableId + "_p");
			String tRows = request.getParameter(tableId + "_crd");
			if ("".equals(tRows))
				tRows = "30";
			if ("".equals(tnm))
				tnm = "0";
			currentRows = Integer.parseInt(tRows);
			int tpage = Integer.parseInt(tnm);
			if (tpage == 0)
				tpage = 1;
			start = (tpage - 1) * currentRows;
			end = tpage * currentRows;
			request.setAttribute("totalRows", new Integer(currentRows));
		}
		if (start == 0 && end == 0)
		{
			start = 0;
			end = currentRows;
		}
		String tmpSql = "";
		String database = db.getDatabase();
		list1 = new ArrayList();
		try
		{
			if (totalRows <= maxResult)
			{
				tmpSql = sql[1];
				stat = con.prepareStatement(tmpSql);
			} else
			if ("mysql".equalsIgnoreCase(database))
			{
				tmpSql = db.getMYSQLLimitString(sql[1], true);
				stat = con.prepareStatement(tmpSql);
				stat.setInt(1, start);
				stat.setInt(2, currentRows);
			} else
			if ("db2".equalsIgnoreCase(database))
			{
				tmpSql = db.getDB2LimitString(sql[1], true);
				stat = con.prepareStatement(tmpSql);
				stat.setInt(1, start);
				stat.setInt(2, end);
			} else
			if ("h2".equalsIgnoreCase(database))
			{
				tmpSql = db.getH2LimitString(sql[1], true);
				stat = con.prepareStatement(tmpSql);
				stat.setInt(1, start);
				stat.setInt(2, currentRows);
			} else
			if ("firebird".equalsIgnoreCase(database))
			{
				tmpSql = db.getFirebirdLimitString(sql[1], true);
				stat = con.prepareStatement(tmpSql);
				stat.setInt(1, currentRows);
				stat.setInt(2, start);
			} else
			if ("hsq".equalsIgnoreCase(database))
			{
				tmpSql = db.getHSQLLimitString(sql[1], true);
				stat = con.prepareStatement(tmpSql);
				stat.setInt(1, start);
				stat.setInt(2, currentRows);
			} else
			if ("oracle".equalsIgnoreCase(database))
			{
				tmpSql = db.getOracleLimitString(sql[1], true);
				stat = con.prepareStatement(tmpSql);
				stat.setInt(1, end);
				stat.setInt(2, start);
			}
			rs = stat.executeQuery();
			Map map = null;
			int index = 0;
			for (; rs.next(); list1.add(map))
			{
				if (rsda == null)
					rsda = rs.getMetaData();
				index = rsda.getColumnCount();
				map = new HashMap();
				for (int i = 1; i <= index; i++)
				{
					Object obj = null;
					if (rsda.getColumnTypeName(i).equalsIgnoreCase("ROWID"))
					{
						byte by[] = rs.getBytes(i);
						obj = new String(by, 0, by.length);
					} else
					if ("DATE".equalsIgnoreCase(rsda.getColumnTypeName(i)))
						obj = rs.getTimestamp(i);
					else
						obj = rs.getObject(i);
					map.put(rsda.getColumnName(i).toLowerCase(), obj);
				}

			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (rs != null)
				try
				{
					rs.close();
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			if (stat != null)
				try
				{
					stat.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return list1;
	}

	private int getRows(Session session, String sql, Object objs[])
	{
		int totalRows = 0;
		try
		{
			Query q = session.createQuery(sql);
			if (objs != null)
			{
				int ind = 0;
				for (int i = 0; i < objs.length; i++)
					if (objs[i] != null)
					{
						q.setParameter(ind, objs[i]);
						ind++;
					}

			}
			Object obj = q.uniqueResult();
			if (obj != null)
			{
				Integer total = new Integer(obj.toString());
				totalRows = total.intValue();
			}
		}
		catch (Exception exception) { }
		return totalRows;
	}

	public List getPageCollection(Session session, String sql[], Object obj[])
	{
		List vehicleList = null;
		int totalRows = getRows(session, sql[0], obj);
		int currentRows = limit.getCurrentRowsDisplayed();
		if (currentRows == 0)
			currentRows = pageResultSize;
		if (request.getAttribute("totalRows") == null)
			request.setAttribute("totalRows", new Integer(totalRows));
		limit.setRowAttributes(totalRows, currentRows);
		int start = limit.getRowStart();
		int end = limit.getRowEnd();
		if (limit.isExported() && limit.getTotalRows() > maxResult)
		{
			String tnm = request.getParameter(tableId + "_p");
			String tRows = request.getParameter(tableId + "_crd");
			if ("".equals(tRows))
				tRows = "30";
			if ("".equals(tnm))
				tnm = "0";
			currentRows = Integer.parseInt(tRows);
			int tpage = Integer.parseInt(tnm);
			if (tpage == 0)
				tpage = 1;
			start = (tpage - 1) * currentRows;
			end = tpage * currentRows;
			request.setAttribute("totalRows", new Integer(currentRows));
		}
		if (start == 0 && end == 0)
		{
			start = 0;
			end = currentRows;
		}
		try
		{
			Query q = session.createQuery(sql[1]);
			if (obj != null)
			{
				int ind = 0;
				for (int i = 0; i < obj.length; i++)
					if (obj[i] != null)
					{
						q.setParameter(ind, obj[i]);
						ind++;
					}

			}
			if (totalRows > maxResult)
			{
				q.setFirstResult(start);
				q.setMaxResults(currentRows);
			}
			vehicleList = q.list();
			session.flush();
		}
		catch (Exception exception) { }
		return vehicleList;
	}

	public int getMaxResult()
	{
		return maxResult;
	}

	public void setMaxResult(int maxResult)
	{
		this.maxResult = maxResult;
	}
}
