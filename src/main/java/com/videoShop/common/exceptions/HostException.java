package com.videoShop.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HostException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2211937283481988057L;
	
	public HostException(String msg){
		super(msg);
	}
}
