package com.example.demoActuator.common;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 8205877130108144548L;


	private String code;

	private String description;

	public ReturnCodes getError() {
		return error;
	}

	private ReturnCodes error;
	private String[] tokens;
	private String originalMessage;
	private Throwable exception;
	protected String key;

	public ApplicationException(ReturnCodes error, Throwable exception, String... tokens) {
		super(exception);
		this.originalMessage = exception.getMessage();
		this.error = error;
		this.code = error.getCode();
		this.exception = exception;
		this.tokens = tokens;
	}

	public ApplicationException(ReturnCodes error, String... tokens) {
		super();
		this.error = error;
		this.code = error.getCode();
		this.tokens = tokens;
	}

	public String getOriginalMessage(){
		return originalMessage;
	}

	public String getKey() {
		return key;
	}

	public Throwable getException() {
		return exception;
	}

	public String getCode() {
		return this.code;
	}

	public String getErrorTitle() {
		if(this.getError()!=null)
			return error.getTitle(tokens);
		else
			return null;
	}

	@Override
	public String toString() {
		return this.getCode() + " - " + this.getErrorTitle();
	}

	public ApplicationException(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public ApplicationException(String code) {
		this.code = code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
