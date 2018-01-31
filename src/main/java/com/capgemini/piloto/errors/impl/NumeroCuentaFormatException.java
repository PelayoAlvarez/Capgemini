package com.capgemini.piloto.errors.impl;

import com.capgemini.piloto.errors.Error;

public class NumeroCuentaFormatException extends Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getJSONError() {
		return "{\"reason\": \"Formato de número de cuenta incorrecto\"}";
	}

	@Override
	public String getMessageError() {
		return "Formato de número de cuenta incorrecto";
	}

}
