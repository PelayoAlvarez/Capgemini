package com.capgemini.piloto.util.validator;

import com.capgemini.piloto.errors.impl.TextoFormatException;

public class ComunValidator {
	
	private ComunValidator() { }
	
	public static void validateTexto(String texto, String tipo, int longitud) {
		if (texto.length() > longitud)
			throw new TextoFormatException(tipo); 
	}
}
