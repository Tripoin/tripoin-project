package com.tripoin.web.view.exception;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TripoinViewException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8954055698221379603L;
	private final Logger LOGGER = LoggerFactory.getLogger(TripoinViewException.class);
	
	public TripoinViewException(String msg) {
		super(msg);
		LOGGER.error(msg);
	}

}
