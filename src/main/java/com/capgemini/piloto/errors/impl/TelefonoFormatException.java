package com.capgemini.piloto.errors.impl;

import com.capgemini.piloto.errors.Error;

public class TelefonoFormatException extends Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getJSONError() {
		return "{\"reason\": \"Formato de teléfono incorrecto\"}";
	}

	@Override
	public String getMessageError() {
		return "Formato de teléfono incorrecto";
	}
}
