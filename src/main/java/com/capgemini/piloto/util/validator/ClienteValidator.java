package com.capgemini.piloto.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.piloto.errors.impl.DniFormatException;
import com.capgemini.piloto.errors.impl.EmailFormatException;
import com.capgemini.piloto.errors.impl.TelefonoFormatException;

public class ClienteValidator {
	
	public static void main(String[] args) {
		validateDni("71736256S");
	}
	
	private static ClienteValidator instance;
	
	 private ClienteValidator() {}
	
	 public static ClienteValidator getInstance() {
	 if(instance == null) {
	 instance = new ClienteValidator();
	 }
	 return instance;
	 }
	
	public static void validateDni(String dni) {		
		String validChars = "TRWAGMYFPDXBNJZSQVHLCKET";
		String nifRexp = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]$";
		String nieRexp = "^[XYZ]{1}[0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]{1}$";
		String str = dni.toUpperCase();
		
		if (!str.matches(nifRexp) && !str.matches(nieRexp))
			throw new DniFormatException();
		
		String nie = str
		      .replace("/^[X]/", "0")
		      .replace("/^[Y]/", "1")
		      .replace("/^[Z]/", "2");
		
		char letter = str.charAt(str.length() - 1);
		int charIndex = Integer.parseInt(nie.substring(0, 8)) % 23;
		
		if(validChars.charAt(charIndex) != letter) {
			throw new DniFormatException();
		}
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
