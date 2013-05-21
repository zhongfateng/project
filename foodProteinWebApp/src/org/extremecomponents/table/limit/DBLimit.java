// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DBLimit.java

package org.extremecomponents.table.limit;

import java.io.PrintStream;

public class DBLimit
{

	public static final DBLimit MYSQL = new DBLimit("mysql");
	public static final DBLimit DB2 = new DBLimit("db2");
	public static final DBLimit H2 = new DBLimit("h2");
	public static final DBLimit FIREBIRD = new DBLimit("firebird");
	public static final DBLimit HSQ = new DBLimit("hsq");
	public static final DBLimit ORACLE = new DBLimit("oracle");
	private String database;

	public DBLimit()
	{
		database = "oracle";
	}

	public DBLimit(String database)
	{
		this.database = "oracle";
		this.database = database;
	}

	public String getDB2LimitString(String sql, boolean hasOffset)
	{
		int startOfSelect = sql.toLowerCase().indexOf("select");
		StringBuffer pagingSelect = (new StringBuffer(sql.length() + 100)).append(sql.substring(0, startOfSelect)).append("select * from ( select ").append(getRowNumber(sql));
		if (hasDistinct(sql))
			pagingSelect.append(" row_.* from ( ").append(sql.substring(startOfSelect)).append(" ) as row_");
		else
			pagingSelect.append(sql.substring(startOfSelect + 6));
		pagingSelect.append(" ) as temp_ where rownumber_ ");
		if (hasOffset)
			pagingSelect.append("between ?+1 and ?");
		else
			pagingSelect.append("<= ?");
		return pagingSelect.toString();
	}

	private String getRowNumber(String sql)
	{
		StringBuffer rownumber = (new StringBuffer(50)).append("rownumber() over(");
		int orderByIndex = sql.toLowerCase().indexOf("order by");
		if (orderByIndex > 0 && !hasDistinct(sql))
			rownumber.append(sql.substring(orderByIndex));
		rownumber.append(") as rownumber_,");
		return rownumber.toString();
	}

	private static boolean hasDistinct(String sql)
	{
		return sql.toLowerCase().indexOf("select distinct") >= 0;
	}

	public String getMYSQLLimitString(String sql, boolean hasOffset)
	{
		return (new StringBuffer(sql.length() + 20)).append(sql).append(hasOffset ? " limit ?, ?" : " limit ?").toString();
	}

	public String getH2LimitString(String sql, boolean hasOffset)
	{
		return (new StringBuffer(sql.length() + 20)).append(sql).append(hasOffset ? " limit ? offset ?" : " limit ?").toString();
	}

	public String getFirebirdLimitString(String sql, boolean hasOffset)
	{
		return (new StringBuffer(sql.length() + 20)).append(sql).insert(6, hasOffset ? " first ? skip ?" : " first ?").toString();
	}

	public String getHSQLLimitString(String sql, boolean hasOffset)
	{
		return (new StringBuffer(sql.length() + 10)).append(sql).insert(sql.toLowerCase().indexOf("select") + 6, hasOffset ? " limit ? ?" : " top ?").toString();
	}

	public String getOracleLimitString(String sql, boolean hasOffset)
	{
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update"))
		{
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (hasOffset)
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		else
			pagingSelect.append("select * from ( ");
		pagingSelect.append(sql);
		if (hasOffset)
			pagingSelect.append(" ) row_ ) where rownum_ <= ? and rownum_ > ?");
		else
			pagingSelect.append(" ) where rownum <= ?");
		if (isForUpdate)
			pagingSelect.append(" for update");
		return pagingSelect.toString();
	}

	public static void main(String args[])
	{
		String sql = "select count(*),a.sele select,a.bb from abc a where a.id in (select id from bcd )";
		String sql1 = "bb";
		DBLimit limit = new DBLimit();
		System.out.println(limit.getDB2LimitString(sql, true));
		System.out.println(limit.getMYSQLLimitString(sql, true));
		System.out.println(limit.getHSQLLimitString(sql, true));
		System.out.println(limit.getOracleLimitString(sql, true));
		System.out.println(limit.getOracleLimitString(sql1, true));
		Object obj = new Long("123");
		System.out.println(obj.getClass().getName());
	}

	public String getDatabase()
	{
		return database;
	}

}
