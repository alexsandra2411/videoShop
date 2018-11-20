package com.videoShop.common.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.support.ETagDoesntMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This handler makes correct HTTP Response status and creates well formated JSON errors.
 */
@ControllerAdvice
class GlobalDefaultExceptionHandler {

	@Autowired
	private ApplicationContext context;

	/**
	 * Handles MethodArgumentNotValidException and send 400 BAD_REQUEST as response with well formated json error.
	 * @param req Http Request
	 * @param ex   Some exception
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	ValidationErrorInfo handleValidationException(HttpServletRequest req, MethodArgumentNotValidException ex) {
		return new ValidationErrorInfo(req.getRequestURL().toString(), ex);
	}

	/**
	 * Handles ConstraintViolationException and send 400 BAD_REQUEST as response with well formated json error.
	 * @param req Http Request
	 * @param ex   Some exception
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseBody
	ValidationErrorInfo handleConstraintViolationException(HttpServletRequest req,
			javax.validation.ConstraintViolationException ex) {
		return new ValidationErrorInfo(req.getRequestURL().toString(), ex.getConstraintViolations());
	}

	/**
	 * Handles ETagDoesntMatchException and send 400 BAD_REQUEST as response with well formated json error.
	 * @param req Http Request
	 * @param ex   Some exception
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ETagDoesntMatchException.class)
	@ResponseBody
	ErrorInfo handleEtagException(HttpServletRequest req, ETagDoesntMatchException ex) {
		String message = context.getMessage("validation.ETagDoesntMatch", null, "ETag doesn't match", req.getLocale());
		return new ErrorInfo(req.getRequestURL().toString(), message);
	}

	/**
	 * Handles DataIntegrityViolationException and send 400 BAD_REQUEST as response with well formated json error.
	 * @param req Http Request
	 * @param ex   Some exception
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	ErrorInfo handleConstraintViolationException(HttpServletRequest req, DataIntegrityViolationException ex) {
		String message = context.getMessage("validation.dataIntegrityViolation", null, "Data Integrity Violation",
				req.getLocale());
		if (ex.getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException originalEx = (ConstraintViolationException) ex.getCause();
			if (originalEx.getSQL().toLowerCase().startsWith("delete")) {
				message = context.getMessage("validation.constrainViolation.delete", null,
						"Can not delete. Object is in use.", req.getLocale());
			} else if (originalEx.getSQL().toLowerCase().startsWith("insert")) {
				message = context.getMessage("validation.constrainViolation.insert", null,
						"Can not create. Object already exists.", req.getLocale());
			}
		}
		return new ErrorInfo(req.getRequestURL().toString(), message);
	}

	/**
	 * Handles security exception and send 403 UNAUTHORIZED as response with well formated json error.
	 * @param req Http Request
	 * @param e   Some exception
	 * @return
	 */
//	@ResponseStatus(HttpStatus.UNAUTHORIZED)
//	@ExceptionHandler({ AccessDeniedException.class, AuthenticationCredentialsNotFoundException.class })
//	@ResponseBody
//	ErrorInfo securityErrorHandler(HttpServletRequest req, Exception e) {
//		return new ErrorInfo(req.getRequestURL().toString(), e.getLocalizedMessage());
//	}

	/**
	 * Handles default errors and send 500 Internal Server Error as response with well formated json error.
	 * @param req Http Request
	 * @param e   Some exception
	 * @return
	 * @throws Exception
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	ErrorInfo defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;

		return new ErrorInfo(req.getRequestURL().toString(), e.getLocalizedMessage());
	}
}