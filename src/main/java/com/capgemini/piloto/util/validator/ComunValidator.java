package com.capgemini.piloto.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.piloto.errors.impl.TextoFormatException;

public class ComunValidator {
	
	private ComunValidator() { }
	
	public static void validateTexto(String texto, String tipo, int longitud) {
		Pattern textoRegex = 
			    Pattern.compile("^[a-zA-Z]+$", 
			    		Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = textoRegex.matcher(texto);
		
		if (!matcher.find() || texto.length() > longitud)
			throw new TextoFormatException(tipo); 
	}
}
