package com.nbw.common;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Used to be the base of a multiaction validator.  The 'validate' function here implements the 
 * Validator interface but is never used.  If you want a generic validator function to run for an 
 * entire multiaction form controller then you can IF you define your form.
 * 		public validate(MyForm form, Errors errors);
 * This class can validate individual methods.  For example if the method in the 
 * MultiActionFormController looks like ...
 * 
 * public ModelAndView viewApplication(HttpServletRequest request, HttpServletResponse response, 
 * 		ApplicationDetailForm form, BindException errors)
 * 
 * then the associated validation method would be
 * 
 * public void viewApplicationValidate(ApplicationForm form, Errors errors) 
 * 
 * If the validation method is not found, then it uses 'validate(MyForm form, Errors errors)'
 * @author David Reepmeyer
 */
public abstract class AbstractMultiActionValidator  implements Validator {

	/**
	 * Used to define the form class
	 * @author David Reepmeyer
	 * @param clazz the class type of the form object
	 */
    abstract public boolean supports(Class clazz);
	
    /**
     * Used to implement the validator interface to allow for a generic validator
     * @author David Reepmeyer
     * @param obj The form object passed to the function
     * @param errors used to store the errors when validating the form
     */
    public void validate(Object obj, Errors errors) {
    }

}
