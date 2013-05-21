package ${moduleName}.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.common.util.DateUtils;
import ${longDomainClass};
import ${moduleName}.service.${managerName};

/**
 * 
 * ${controllerName}
 * 
 * @author 
 * 
 */
public class ${controllerName} extends NbwController {


    private ${managerName} ${managerName?lower_case};
    
    
    
    /**
	 * constructor
	 *  
	 * @throws ClassNotFoundException
	 */
	public ${controllerName}() throws ClassNotFoundException {
		super();
	}

	/**
	 * 获得列表数据
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView loadData(HttpServletRequest request,
			HttpServletResponse response, ${shortDomainClass} command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("${jspPath}/${shortDomainClass?lower_case}List","dataList",this.${managerName?lower_case}.getAll());
	}

    /**
	 * 转向新增页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 *  
	 * @throws ServletException, IOException
	 */
	public ModelAndView toAddPage(HttpServletRequest request,
			HttpServletResponse response, ${shortDomainClass} command, BindException errors)
			throws ServletException, IOException {
		return new ModelAndView("${jspPath}/${shortDomainClass?lower_case}Form");
	}

    /**
	 * 转向编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException, IOException
	 */
	public ModelAndView toEditPage(HttpServletRequest request,
			HttpServletResponse response, ${shortDomainClass} command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");
		command = this.${managerName?lower_case}.findById(id);
		return new ModelAndView("${jspPath}/${shortDomainClass?lower_case}Form","${shortDomainClass?lower_case}",command);
	}

    /**
	 * 保存数据
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException, IOException
	 */
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, ${shortDomainClass} command, BindException errors)
			throws ServletException, IOException {
		if (command!=null&&command.getId()!=null&&!"".equals(command.getId())){
			this.${managerName?lower_case}.edit${shortDomainClass}(command);
		} else {
			this.${managerName?lower_case}.save${shortDomainClass}(command);
		}
		return new ModelAndView("${jspPath}/${shortDomainClass?lower_case}List","dataList",this.${managerName?lower_case}.getAll());
	}

	
	/**
	 * 删除数据
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException, IOException
	 */	
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, ${shortDomainClass} command, BindException errors)
			throws ServletException, IOException {
		String id = request.getParameter("objectId");		
		try {
			this.${managerName?lower_case}.delete${shortDomainClass}(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("${jspPath}/${shortDomainClass?lower_case}List","dataList",this.${managerName?lower_case}.getAll());
	}
	
	/**
	 * 校验数据
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * 
	 * @throws ServletException, IOException
	 */	
	public ModelAndView validateData(HttpServletRequest request,
			HttpServletResponse response, ${shortDomainClass} command, BindException errors)
			throws ServletException, IOException {
	    response.setContentType("text/html; charset=utf-8");    
	    response.setCharacterEncoding("utf-8"); 
	    PrintWriter out = response.getWriter (); 
		String id = request.getParameter("id");
		${shortDomainClass} ${shortDomainClass?lower_case} = this.${managerName?lower_case}.findById(id);
//		if(${shortDomainClass?lower_case}==null){
		if(!"123".equals(id)){
			out.print("success");
		}else{
			out.print("id="+id+"的对象已经存在！");
		}		
		return null;
	}
	
	/**
	 * 把字符型转化为时间型
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	protected void convertStringToDate(HttpServletRequest request,Object command){
		${shortDomainClass} tempObject = (${shortDomainClass})command;
	  <#list propertyList as being>
		<#if being.type='Date'>
		String ${being.name}_showS = request.getParameter("${being.name}_showS");
		if(${being.name}_showS != null && !${being.name}_showS.equals("")){
			tempObject.set${being.name?cap_first}(DateUtils.stringToDate(${being.name}_showS));
		}
		</#if>
	  </#list>	
	}
	


    //*******************************************************************************set and get	
	public ${managerName} get${managerName?lower_case?cap_first}() {
		return ${managerName?lower_case};
	}

	public void set${managerName?lower_case?cap_first}(${managerName} ${managerName?lower_case}) {
		this.${managerName?lower_case} = ${managerName?lower_case};
	}
}
