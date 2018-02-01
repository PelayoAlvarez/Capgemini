package com.capgemini.piloto.errors.impl;

public class NumeroCuentaFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Formato de número de cuenta incorrecto";
	}

}
