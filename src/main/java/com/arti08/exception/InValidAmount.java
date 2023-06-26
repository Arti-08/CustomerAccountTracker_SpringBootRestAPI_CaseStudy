package com.arti08.exception;

public class InValidAmount extends Exception {

	String msg;

	public InValidAmount(String msg) {
		super();
		this.msg = msg;
	}

}
