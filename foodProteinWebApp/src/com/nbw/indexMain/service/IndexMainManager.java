package com.nbw.indexMain.service;

import javax.servlet.http.HttpServletRequest;

import com.nbw.indexMain.dao.IndexMainDAO;

public class IndexMainManager {
	
	
	private IndexMainDAO indexMaindao;
	
	
    public String getIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  
    
    public String getRemortIP(HttpServletRequest request) {  
    	  if (request.getHeader("x-forwarded-for") == null) {  
    	   return request.getRemoteAddr();  
    	  }  
    	  return request.getHeader("x-forwarded-for");  
    	 }
	
	
	
	

	public IndexMainDAO getIndexMaindao() {
		return indexMaindao;
	}

	public void setIndexMaindao(IndexMainDAO indexMaindao) {
		this.indexMaindao = indexMaindao;
	}
	
	
	
	
	

}
