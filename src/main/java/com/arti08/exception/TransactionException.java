package com.arti08.exception;

public class TransactionException extends Exception {

	String msg;

	public TransactionException(String msg) {
		super();
		this.msg = msg;
	}
}
