package com.arti08.exception;

public class NoDataFoundException extends Exception {

	String msg;

	public NoDataFoundException(String msg) {
		super();
		this.msg = msg;
	}
}

