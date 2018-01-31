package com.capgemini.piloto.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.piloto.errors.impl.DniFormatException;
import com.capgemini.piloto.errors.impl.EmailFormatException;
import com.capgemini.piloto.errors.impl.TelefonoFormatException;

public class ClienteValidator {
	
	public static void validateDni(String dni) {
		Pattern dniRegex = 
			    Pattern.compile("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$", 
			    		Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = dniRegex.matcher(dni);
		
		if (!matcher.find())
			throw new DniFormatException();
	}
	
	public static void validateEmail(String email) { 
		Pattern emailRegex = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 
			    		Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = emailRegex.matcher(email);
		
		if (!matcher.find())
			throw new EmailFormatException();
	}
	
	public static void validateTelefono(String fijo) {
		Pattern fijoRegex = 
			    Pattern.compile("^(\\+34|0034|34)?[6|7|9][0-9]{8}$", 
			    		Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = fijoRegex.matcher(fijo);
		
		if (!matcher.find())
			throw new TelefonoFormatException();
	}
}
