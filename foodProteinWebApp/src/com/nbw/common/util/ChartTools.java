package com.nbw.common.util;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;




public class ChartTools {
	
	/**
	 * 将整数的RGB值转换为十六进制的字符串
	 * @param rgb
	 * @return
	 */
	public static String getshopcorHex(int[] rgb)
    {
        String strR = "#";
        String str;
        int j;
        for(j=0;j<rgb.length;j++)
        {
            str = Integer.toHexString(rgb[j]);
            if(str.length()==1) str = '0'+str;
            strR = strR+str;
        }
        return strR;
    }
	/**
	 * 将十六进制的字符串转换为整数的RGB�?
	 * @param 
	 * @return rgb数组
	 */
	public static int[] getcolor(String rgb)
    {
		int[] result = {0,0,0};
		if(!youXiao(rgb)) return result;
		String thergb = ChartTools.replace(rgb,"#","");
		if(thergb.length()!=6) return result;
		try
		{
			result[0]=Integer.parseInt(thergb.substring(0,2),16);
			result[1]=Integer.parseInt(thergb.substring(2,4),16);
			result[2]=Integer.parseInt(thergb.substring(4),16);
		}
        catch(Exception e)
        {
        	//Log.log("MeTool","getcolor",e.getMessage());
        }
        return result;
    }
	
	public static Color getColor(String rgb)
	{
		if(!youXiao(rgb)) return null;
		int[] intRGB = getcolor(rgb);
		return new Color(intRGB[0],intRGB[1],intRGB[2]);
	}
	
	 /************************************************************************
	   *<p>功能：将字符串指定安符串替换为另�?字符串�??</p>
	   *<p>调用参数:String strMain--待处理的字符�?,String strFind--�?查找的字符串,String strReplace--用来替换的字符串�?
	   *替换字符串strFind 不可被包含于strReplace �?</p>
	   *<p>返回�?:String 处理过的字符�?</p>
	   *<p>作�??:石中�?</p>
	   *<p>创建日期:2002/02/25</p>
	   *<p>修订�?</p>
	   */

    public static String replace (String strMain,String strFind,String strReplace)
    {
      if(strMain==null) return strMain;
      int intIndex=0;
      String strResult=strMain;
      intIndex=strResult.indexOf(strFind);
      while (intIndex!=-1)
      {
        strResult=strResult.substring(0,intIndex)+strReplace+strResult.substring(intIndex+strFind.length());
        intIndex=strResult.indexOf(strFind);
      }
      return strResult;

    }
	
	
	public static boolean youXiao(String val)
    {
    	if(val==null) return false;
    	if(val.trim().length()<1) return false;
    	return true;
    }
	
	 public static String formatDouble(double f,int dotLength){

       if (dotLength<0) return f+"";
       if (dotLength==0) return Math.round(f)+"";
       StringBuffer sb=new StringBuffer();
       for (int i=0;i<dotLength;i++){
           sb.append("0");
       }
       DecimalFormat df=new DecimalFormat("0."+sb.toString()+"");
       return df.format(f);
    }
	
	 public static Number strToNum(String str,int dotLength)
    {
    	NumberFormat numv = NumberFormat.getInstance();
    	Number result = new Integer(0);
    	try
		{
    		if(str==null) return null;
    		str = str.trim();
    		if(str.length()<1) return result;
    		result = numv.parse(str);
    		result = numv.parse(formatDouble(result.doubleValue(),dotLength));
		}
    	catch(Exception e)
		{
    		System.out.println("Class= Nbwtools, Method =strToNum");
    	}
    	return result;
    }
	
	 public static boolean canToNum(String val)
	    {
	    	
	    	try
	    	{
	    		Double.parseDouble(val);
	    		return true;
	    	}
			catch(Exception e)
			{
				
				return false;
			}
	    }
	 
	 
	 public static void repareLine(String[][] valsrc,int colstart,int dataform)
    {
        int i,j,rowcon,colcon,st=0,k;
        if(valsrc==null) return;
        rowcon=valsrc.length;
        if(rowcon<3) return;
        if(valsrc[0]==null) return;
        colcon = valsrc[0].length;
        if(colcon<colstart+1) return;
        Number nval = new Integer(0);
        Number nvalp = new Integer(0);
        double unitval=0,valnvalp=0;
        for(k=colstart;k<colcon;k++)
        {
        		st=0;
            for(i=1;i<rowcon;i++)
            {
                nvalp = ChartTools.strToNum(valsrc[i-1][k],dataform);
                nval = ChartTools.strToNum(valsrc[i][k],dataform);
                if(nval.doubleValue()==0&&nvalp.doubleValue()!=0)
                {
                    st=i;
                    valnvalp = nvalp.doubleValue();
                }
                if(nval.doubleValue()!=0&&nvalp.doubleValue()==0&&st!=0)
                {
                    unitval = (nval.doubleValue()-valnvalp)/(i-st+1);
                    for(j=st;j<i;j++) valsrc[j][k] = ChartTools.formatDouble(valnvalp+unitval*(j-st+1),dataform)+"";
                }
            }
        }
    }
}
