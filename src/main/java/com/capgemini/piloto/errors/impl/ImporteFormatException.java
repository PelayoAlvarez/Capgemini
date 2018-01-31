package com.capgemini.piloto.errors.impl;

import com.capgemini.piloto.errors.Error;

public class ImporteFormatException extends Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getJSONError() {
		return "{\"reason\": \"Formato de importe incorrecto\"}";
	}

	@Override
	public String getMessageError() {
		return "Formato de importe incorrecto";
	}

}
