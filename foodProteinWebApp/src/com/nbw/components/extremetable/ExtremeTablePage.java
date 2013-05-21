
package com.nbw.components.extremetable;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.DBLimit;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 数据库分页方法 
 * 
 * @param ExtremeTablePage
 *            extremecomponents提供的工具类
 * 
 * @return list 当前对象的List
 * @author huangrong
 */

public class ExtremeTablePage
{

    public ExtremeTablePage(HttpServletRequest request, int defaultPageResultSize, String tableId)
    {
        maxResult = defaultPageResultSize;
        limit = null;
        pageResultSize = 0;
        this.tableId = null;
        org.extremecomponents.table.context.Context context = new HttpServletRequestContext(request);
        if(tableId == null || "".equals(tableId))
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
            if(rs.next())
            {
                BigDecimal count = rs.getBigDecimal(1);
                if(count != null)
                    total = count.intValue();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(rs != null)
                try
                {
                    rs.close();
                }
                catch(SQLException e1)
                {
                    e1.printStackTrace();
                }
            if(stat != null)
                try
                {
                    stat.close();
                }
                catch(SQLException e2)
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
        if(currentRows == 0)
            currentRows = pageResultSize;
        if(request.getAttribute("totalRows") == null)
            request.setAttribute("totalRows", new Integer(totalRows));
        limit.setRowAttributes(totalRows, currentRows);
        int start = limit.getRowStart();
        int end = limit.getRowEnd();
        if(limit.isExported() && limit.getTotalRows() > maxResult)
        {
            String tnm = request.getParameter(tableId + "_p");
            String tRows = request.getParameter(tableId + "_crd");
            if("".equals(tRows))
                tRows = "30";
            if("".equals(tnm))
                tnm = "0";
            currentRows = Integer.parseInt(tRows);
            int tpage = Integer.parseInt(tnm);
            if(tpage == 0)
                tpage = 1;
            start = (tpage - 1) * currentRows;
            end = tpage * currentRows;
            request.setAttribute("totalRows", new Integer(currentRows));
        }
        if(start == 0 && end == 0)
        {
            start = 0;
            end = currentRows;
        }
        String tmpSql = "";
        String database = db.getDatabase();
        list1 = new ArrayList();
        try
        {
            if(totalRows <= maxResult)
            {
                tmpSql = sql[1];
                stat = con.prepareStatement(tmpSql);
            } else
            if("mysql".equalsIgnoreCase(database))
            {
                tmpSql = db.getMYSQLLimitString(sql[1], true);
                stat = con.prepareStatement(tmpSql);
                stat.setInt(1, start);
                stat.setInt(2, currentRows);
            } else
            if("db2".equalsIgnoreCase(database))
            {
                tmpSql = db.getDB2LimitString(sql[1], true);
                stat = con.prepareStatement(tmpSql);
                stat.setInt(1, start);
                stat.setInt(2, end);
            } else
            if("h2".equalsIgnoreCase(database))
            {
                tmpSql = db.getH2LimitString(sql[1], true);
                stat = con.prepareStatement(tmpSql);
                stat.setInt(1, start);
                stat.setInt(2, currentRows);
            } else
            if("firebird".equalsIgnoreCase(database))
            {
                tmpSql = db.getFirebirdLimitString(sql[1], true);
                stat = con.prepareStatement(tmpSql);
                stat.setInt(1, currentRows);
                stat.setInt(2, start);
            } else
            if("hsq".equalsIgnoreCase(database))
            {
                tmpSql = db.getHSQLLimitString(sql[1], true);
                stat = con.prepareStatement(tmpSql);
                stat.setInt(1, start);
                stat.setInt(2, currentRows);
            } else
            if("oracle".equalsIgnoreCase(database))
            {
                tmpSql = db.getOracleLimitString(sql[1], true);
                stat = con.prepareStatement(tmpSql);
                stat.setInt(1, end);
                stat.setInt(2, start);
            }
            rs = stat.executeQuery();
            Map map = null;
            int index = 0;
            for(; rs.next(); list1.add(map))
            {
                if(rsda == null)
                    rsda = rs.getMetaData();
                index = rsda.getColumnCount();
                map = new HashMap();
                for(int i = 1; i <= index; i++)
                {
                    Object obj = null;
                    if(rsda.getColumnTypeName(i).equalsIgnoreCase("ROWID"))
                    {
                        byte by[] = rs.getBytes(i);
                        obj = new String(by, 0, by.length);
                    } else
                    if("DATE".equalsIgnoreCase(rsda.getColumnTypeName(i)))
                        obj = rs.getTimestamp(i);
                    else
                        obj = rs.getObject(i);
                    map.put(rsda.getColumnName(i).toLowerCase(), obj);
                }

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(rs != null)
                try
                {
                    rs.close();
                }
                catch(SQLException e1)
                {
                    e1.printStackTrace();
                }
            if(stat != null)
                try
                {
                    stat.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
        }
        return list1;
    }

    private int getRows(Session session, String sql, Object objs[])
    {
        int totalRows = 0;
            Query q = session.createQuery(sql);
            if(objs != null)
            {
                int ind = 0;
                for(int i = 0; i < objs.length; i++)
                    if(objs[i] != null)
                    {
                        q.setParameter(ind, objs[i]);
                        ind++;
                    }

            }
            Object obj = q.uniqueResult();
            if(obj != null)
            {
                Integer total = new Integer(obj.toString());
                totalRows = total.intValue();
            }
        return totalRows;
    }
    
    private int getRowsSql(Session session, String sql, Object objs[])
    {
        int totalRows = 0;
            Query q = session.createSQLQuery(sql);
            if(objs != null)
            {
                int ind = 0;
                for(int i = 0; i < objs.length; i++)
                    if(objs[i] != null)
                    {
                        q.setParameter(ind, objs[i]);
                        ind++;
                    }

            }
            Object obj = q.uniqueResult();
            if(obj != null)
            {
                Integer total = new Integer(obj.toString());
                totalRows = total.intValue();
            }
        return totalRows;
    }

    //使用hql查询
    public List getPageCollection(Session session, String sql[], Object obj[])
    {
        List vehicleList = null;
        int totalRows = getRows(session, sql[0], obj);
        int currentRows = limit.getCurrentRowsDisplayed();
        if(currentRows == 0)
            currentRows = pageResultSize;
        if(request.getAttribute("totalRows") == null)
            request.setAttribute("totalRows", new Integer(totalRows));
        limit.setRowAttributes(totalRows, currentRows);
        int start = limit.getRowStart();
        int end = limit.getRowEnd();
        if(limit.isExported() && limit.getTotalRows() > maxResult)
        {
            String tnm = request.getParameter(tableId + "_p");
            String tRows = request.getParameter(tableId + "_crd");
            if("".equals(tRows))
                tRows = "30";
            if("".equals(tnm))
                tnm = "0";
            currentRows = Integer.parseInt(tRows);
            int tpage = Integer.parseInt(tnm);
            if(tpage == 0)
                tpage = 1;
            start = (tpage - 1) * currentRows;
            end = tpage * currentRows;
            request.setAttribute("totalRows", new Integer(currentRows));
        }
        if(start == 0 && end == 0)
        {
            start = 0;
            end = currentRows;
        }
        try
        {
            Query q = session.createQuery(sql[1]);
            if(obj != null)
            {
                int ind = 0;
                for(int i = 0; i < obj.length; i++)
                    if(obj[i] != null)
                    {
                        q.setParameter(ind, obj[i]);
                        ind++;
                    }

            }
            if(totalRows > maxResult)
            {
                q.setFirstResult(start);
                q.setMaxResults(currentRows);
            }
            vehicleList = q.list();
            session.flush();
        }
        catch(Exception exception) { }
        return vehicleList;
    }

    //使用sql查询
    public List getPageCollectionSql(Session session, String sql[], Object obj[])
    {
        List vehicleList = null;
        int totalRows = getRowsSql(session, sql[0], obj);
        int currentRows = limit.getCurrentRowsDisplayed();
        if(currentRows == 0)
            currentRows = pageResultSize;
        if(request.getAttribute("totalRows") == null)
            request.setAttribute("totalRows", new Integer(totalRows));
        limit.setRowAttributes(totalRows, currentRows);
        int start = limit.getRowStart();
        int end = limit.getRowEnd();
        if(limit.isExported() && limit.getTotalRows() > maxResult)
        {
            String tnm = request.getParameter(tableId + "_p");
            String tRows = request.getParameter(tableId + "_crd");
            if("".equals(tRows))
                tRows = "30";
            if("".equals(tnm))
                tnm = "0";
            currentRows = Integer.parseInt(tRows);
            int tpage = Integer.parseInt(tnm);
            if(tpage == 0)
                tpage = 1;
            start = (tpage - 1) * currentRows;
            end = tpage * currentRows;
            request.setAttribute("totalRows", new Integer(currentRows));
        }
        if(start == 0 && end == 0)
        {
            start = 0;
            end = currentRows;
        }
        try
        {
            Query q = session.createSQLQuery(sql[1]).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            if(obj != null)
            {
                int ind = 0;
                for(int i = 0; i < obj.length; i++)
                    if(obj[i] != null)
                    {
                        q.setParameter(ind, obj[i]);
                        ind++;
                    }

            }
            if(totalRows > maxResult)
            {
                q.setFirstResult(start);
                q.setMaxResults(currentRows);
            }
            vehicleList = q.list();
            session.flush();
        }
        catch(Exception exception) { }
        return vehicleList;
    }
    //使用sql语句2
    public int[] getPageCollectionSqlBz(int totalRows,String sql[], Object obj[])
    {
        List vehicleList = null;
        int currentRows = limit.getCurrentRowsDisplayed();
        if(currentRows == 0)
            currentRows = pageResultSize;
        if(request.getAttribute("totalRows") == null)
            request.setAttribute("totalRows", new Integer(totalRows));
        limit.setRowAttributes(totalRows, currentRows);
        int start = limit.getRowStart();
        int end = limit.getRowEnd();
        if(limit.isExported() && limit.getTotalRows() > maxResult)
        {
            String tnm = request.getParameter(tableId + "_p");
            String tRows = request.getParameter(tableId + "_crd");
            if("".equals(tRows))
                tRows = "30";
            if("".equals(tnm))
                tnm = "0";
            currentRows = Integer.parseInt(tRows);//每页记录数pageSize
            int tpage = Integer.parseInt(tnm);//当前页pageNum
            if(tpage == 0)
                tpage = 1;
            start = (tpage - 1) * currentRows;
            end = tpage * currentRows;
            request.setAttribute("totalRows", new Integer(currentRows));
        }
        if(start == 0 && end == 0)
        {
            start = 0;
            end = currentRows;
        }
        int[] parm = new int[]{start,end};
        return parm;
    }
    
    public int getMaxResult()
    {
        return maxResult;
    }

    public void setMaxResult(int maxResult)
    {
        this.maxResult = maxResult;
    }

    private int maxResult;
    private Limit limit;
    private int pageResultSize;
    private String tableId;
    private HttpServletRequest request;
	
	
}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\workspace\gymdms\WebRoot\WEB-INF\lib\extremecomponents-1.0.1.jar
	Total time: 204 ms
	Jad reported messages/errors:
Couldn't resolve all exception handlers in method getTotalRows
Couldn't resolve all exception handlers in method getPageCollection
	Exit status: 0
	Caught exceptions:
*/