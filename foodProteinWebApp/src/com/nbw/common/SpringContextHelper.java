package com.nbw.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ApplicationContext的帮助类
 * 自动装载ApplicationContext
 * 
 * @author 
 * @create 
 * 
 */
public class SpringContextHelper implements ApplicationContextAware {

     private static ApplicationContext context ;


     /*
     * 注入ApplicationContext
     */
  
          //在加载Spring时自动获得context
		public void setApplicationContext(ApplicationContext context)
				throws BeansException {
			 SpringContextHelper.context = context;
			// TODO Auto-generated method stub
		}
		
		public static Object getBean(String beanName){
			return context.getBean(beanName);
		}
		
}