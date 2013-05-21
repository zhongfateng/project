package com.nbw.common;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

/** 
 * AbstractMultiActionFormController 
 * 
 * 1) This class extends AbstractFormController to provide Multi Action capabilities. 
 *      i) Subclasses will need to implement action methods. 
 * 
 *      ii) Action methods must have the following signature: 
 * 
 *              ModelAndView anyMeaningfulName(HttpServletRequest request, 
 *                      HttpServletResponse response, CommandClass command, 
 *                      BindException errors); 
 * 
 *          Where anyMeaningfulName can be replaced by your own action method names 
 *          and CommandClass is the class of the command specified. 
 * 
 *      iii) Action Methods are resolved using the MethodNameResolver specified. 
 * 
 *      iv) The First page displayed will always be the form page. Other action methods 
 *          will only be resolved and invoked upon submission. 
 * 
 * 
 *      v) NoSuchRequestHandlingMethodException will be thrown if action method cannot 
 *          be resolved 
 * 
 * 
 * 2) It supports Multiple Validation methods and single universal validation method. 
 * 
 *      i) A Validator method can be defined such that it maps transparently to a controller method.
 *    		This is done by associating the controller method name, 
 *    		myMethod(HttpServletRequest request,HttpServletResponse response,MyForm form,BindException errors)
 *    		with a validator name myMethodValidate(MyForm form, Errors errors)
 *          
 *      ii) The controller methods do not have to have independent validator methods.  Any methods needing
 *      	shared validation can use validate(MyForm form, Errors errors) for validation.
 *      
 *      iii) If neither is specified, then no validation is done
 * 
 * 
 * 3) Binding works as in AbstractFormController. 
 * 
 * 4) This class is declared abstract because it does not have any meaningful usage 
 *      if it is not subclassed with action methods implemented. If there are no 
 *      action methods implemented in its subclass, NoSuchRequestHandlingMethodException 
 *      will always be thrown as there is no default behaviour. 
 * 
 * 
 * Sample configuration for subclasses: 
 * <bean id="someController" class="some.controller.class.Name"> 
 *      <property name="methodNameResolver"><ref bean="paramResolver"/></property> 
 *      <property name="sessionForm"><value>true</value></property> 
 *      <property name="commandName"><value>someCommandName</value></property> 
 *      <property name="commandClass"><value>some.command.class.Name</value></property> 
 *      <property name="validator"><ref bean="validatorForAllMethods"/></property> 
 *      <property name="formView"><value>form.view</value></property> 
 *      <property name="successView"><value>success.view</value></property> 
 * </bean> 
 * 
 * 
 * 
 * @author Choon Whee 
 *         date Jul 19, 2005 
 *         time 9:41:18 AM
 * modified by David Reepmeyer (modified Validation procedure)
 * 			date Aug 12, 2005 
 */ 
public abstract class AbstractMultiActionFormController extends AbstractFormController { 

    private MethodNameResolver methodNameResolver; 
    private String formView; 
    private String successView; 

    protected ModelAndView processFormSubmission(HttpServletRequest request, 
                                                 HttpServletResponse response, Object command, BindException errors) 
            throws Exception 
            { 

                if (errors.hasErrors()) { 
                    if (logger.isDebugEnabled()) { 
                        logger.debug("Data binding errors: " + errors.getErrorCount()); 
                    } 
                    System.err.println(errors.getMessage());
                    return showForm(request, response, errors); 
                } else { 
                    String methodName = methodNameResolver.getHandlerMethodName(request); 
                    Method method = null; 
                    Method[] methods = this.getClass().getMethods();
					for(int i = 0; i <methods.length ; i++){
						if(methods[i].getName().equals(methodName)){
							method = methods[i];
						}
					}
                  //java
					//Class dd=  (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
					//method = getClass().getMethod(methodName,new Class[]{HttpServletRequest.class, HttpServletResponse.class, (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0], BindException.class}); 
                  // method = getClass().getMethod(methodName,new Class[]{HttpServletRequest.class, HttpServletResponse.class, getCommandClass(), BindException.class}); 

					List params = new ArrayList(4); 
					params.add(request); 
					params.add(response); 
					//Java 5.0
					params.add(getCommandClass().cast(command)); 
					//Java 1.4
					//params.add(command);  
					params.add(errors); 
					try{
					  return (ModelAndView) method.invoke(this, params.toArray(new Object[params.size()])); 
					}
					catch (InvocationTargetException e){
				        //鎵惧埌瀹為檯鐨勫紓甯革紝骞舵姏鍑 
						e.printStackTrace();
				        throw (Exception)e.getTargetException();
					} 
					catch(Exception e){
						e.printStackTrace();
						throw e;
					}
                } 
            } 


    /**
     * This method allows for a single MultiActionValidator to validate methods from the multiaction controller
     * separately.
     * @author David Reepmeyer
     * @param request the request object
     * @param command the command object
     * @param errors errors that will be determined during validation
     */
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception { 
    	String methodName = methodNameResolver.getHandlerMethodName(request);
    	Method method; 
        try { 
            method = getValidator().getClass().getMethod(methodName+"Validate",  new Class[]{getCommandClass(), org.springframework.validation.Errors.class}); 
        } catch (NoSuchMethodException e) { 
        	//there should be no problem.  There is no validation neccessary for this action
        	//OR it requires a standard validation for of the several methods to use/share
        	if (this.getValidator() != null) { 
        		try {
        			method = getValidator().getClass().getMethod("validate",  new Class[]{getCommandClass(), org.springframework.validation.Errors.class});
        		} catch (NoSuchMethodException ex) { 
        			this.getValidator().validate(command, errors);
        			return;
        		}
    	        List params = new ArrayList(2); 
    	        //Java 5.0
    	        //params.add(getCommandClass().cast(command)); 
    	        //Java 1.4
    	        params.add(command); 
    	        params.add(errors); 
    	        method.invoke(this.getValidator(), params.toArray(new Object[params.size()]));
        	}
        	return;
        } catch (NullPointerException npe) {
        	//no big deal, just means that no validator was specified.
        	return;
        }
        List params = new ArrayList(2); 
        //Java 5.0
        //params.add(getCommandClass().cast(command)); 
        //Java 1.4
        params.add(command); 
        params.add(errors); 
        method.invoke(this.getValidator(), params.toArray(new Object[params.size()]));
    }  
    /**
     * Method allows for the function to go to the method regaurdless of posting or not
     */
    /* causes an infinite loop with proccessFormSubmission
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception { 

		// Form submission: in session-form mode, we need to find
		// the form object in the HTTP session.
			if (isSessionForm()) {
				HttpSession session = request.getSession(false);
				if (session == null || session.getAttribute(getFormSessionAttributeName(request)) == null) {
					// Cannot submit a session form if no form object is in the session.
					return handleInvalidSubmit(request, response);
				}
			}
	
			// Found form object in HTTP session: fetch form object,
			// bind, validate, process submission.
			Object command = getCommand(request);
			ServletRequestDataBinder binder = bindAndValidate(request, command);
			return processFormSubmission(request, response, command, binder.getErrors());
    	
    	//return showForm(request, errors, getFormView()); 
    } */

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception { 
        return showForm(request, errors, getFormView()); 
    } 
    
    protected ModelAndView showForm(HttpServletRequest request, BindException errors) throws Exception { 
        return showForm(request, errors, getFormView()); 
    } 
    
    public void setMethodNameResolver(MethodNameResolver methodNameResolver) { 
        this.methodNameResolver = methodNameResolver; 
    } 
    public String getFormView() { 
        return formView; 
    } 
    public void setFormView(String formView) { 
        this.formView = formView; 
    } 
    public String getSuccessView() { 
        return successView; 
    } 
    public void setSuccessView(String successView) { 
        this.successView = successView; 
    } 
} 

