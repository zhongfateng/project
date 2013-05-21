package com.nbw.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.util.StringUtils;

/**
 * 原来基类包含增删改查方法， 现在改用直接在子类生成这些CRUD方法的方式 所以这个版本的基类不提供CRUD的功能了。
 * 
 */
public abstract class NbwController extends AbstractMultiActionFormController {

	/**
	 * 覆盖基类AbstractFormController的方法，因为默认基类不支持 属性为日期型的转换
	 * 
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @return ModelAndView
	 */

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isFormSubmission(request)) {
			try {
				String action = request.getParameter("m");
				String url = request.getServletPath();
				// 如果是用户登录的链接则直接跳过不进行验证
				if (!Constants.SYS_LOGIN_URL.equals(url)) {
					if (action.indexOf("front") == -1) {// 没有front

						if (request.getSession().getAttribute(
								Constants.SESSION_USER_ID) == null
								&& request.getSession().getAttribute("qt") == null) {// portal的验证
							response.setContentType("text/html; charset=utf-8");
							response.setCharacterEncoding("utf-8");
							String html = "";
							html = "<script>alert('您还没有登录，请登录');top.location.href = '"
									+ SysParameter.getParameter("foodProteinWebApp")
									+ "/indexMain.action?m=frontIndexMain';</script>";
							response.getWriter().write(html);
							return null;
						} else if (request.getSession().getAttribute("ht") == null
								&& request.getSession().getAttribute(
										Constants.SESSION_USER_ID) == null) {
							response.setContentType("text/html; charset=utf-8");
							response.setCharacterEncoding("utf-8");
							String html = "";
							html = "<script>alert('登录过期，请重新登录');top.location.href = '"
									+ SysParameter.getParameter("foodProteinWebApp")
							+ "/login.jsp';</script>";
							response.getWriter().write(html);
							return null;
						}
					}
				}
				/*
				 * //如果是用户登录的链接则直接跳过不进行验证
				 * if(!Constants.SYS_LOGIN_URL.equals(url)){ Map sysModulesMap =
				 * (Map)request.getSession().getAttribute(Constants.SESSION_USER_MACTION);
				 * if(sysModulesMap == null){
				 * System.out.println("+++++++++++++++session失效或没登录+++++++++++++++++++");
				 * return new ModelAndView("../../login"); //session失效 }else{
				 * SysModules m = (SysModules)sysModulesMap.get(url);
				 * if(m==null){
				 * System.out.println("+++++++++++++++"+url+"无权限+++++++++++++++++++");
				 * return new ModelAndView("../../httperror403"); //无权限操作并记录日志
				 * }else{ //判断模块是不是公共的，如果是则不断续验证
				 * if(!SysModules.LINK_TYPE_PUBLIC.equals(m.getLinkType())){
				 * SysModuleActions ma =
				 * (SysModuleActions)m.getActionsMap().get(action);
				 * if(ma==null){ return new ModelAndView("../../httperror403");
				 * //无权限操作并记录日志 } } } } }
				 */

				Object command = getCommand(request);
				ServletRequestDataBinder binder = bindAndValidate(request,
						command);
				BindException errors = new BindException(binder
						.getBindingResult());
				this.convertStringToDate(request, command);
				return processFormSubmission(request, response, command, errors);
			} catch (HttpSessionRequiredException ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("Invalid submit detected: " + ex.getMessage());
				}
				return handleInvalidSubmit(request, response);
			}
		} else {
			return showNewForm(request, response);
		}
	}
	
	/**
	 * 转到支付宝需要设置的参数
	 * @param request  
	 * @param subject         商品名称
	 * @param total_fee       商品总价
	 * @param out_trade_no    订单号
	 * @param body            备注
	 * @param show_url        展示商品的链接
	 * @param extra_common_param  用于区分，是那个模块进行支付，不同模块返回的页面也不一样
	 */
	public void setAlipay(HttpServletRequest request,String subject,String total_fee,String out_trade_no,
			                          String body,String show_url,String extra_common_param){
		 String setbody=StringUtils.stringtoIso(body, "utf-8", "iso-8859-1");
	     String setsubject=StringUtils.stringtoIso(subject, "utf-8", "iso-8859-1"); 
		 request.setAttribute("total_fee", total_fee);//金额
		 request.setAttribute("body", setbody);//备注,商品展示信息
		 request.setAttribute("subject", setsubject);//商品名称
		 request.setAttribute("show_url", show_url);
		 request.setAttribute("out_trade_no", out_trade_no);//订单号
		 request.setAttribute("extra_common_param", extra_common_param);//用于区分，是那个模块进行支付，不同模块返回的页面也不一样
	}

	protected abstract void convertStringToDate(HttpServletRequest request,
			Object command);
}
