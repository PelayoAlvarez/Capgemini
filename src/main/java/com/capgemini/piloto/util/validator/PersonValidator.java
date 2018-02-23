package com.capgemini.piloto.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.piloto.errors.impl.DniFormatException;
import com.capgemini.piloto.errors.impl.EmailFormatException;
import com.capgemini.piloto.errors.impl.PasswordFormatException;
import com.capgemini.piloto.errors.impl.TelefonoFormatException;
import com.capgemini.piloto.errors.impl.TextoFormatException;

public class PersonValidator {
	
	static Pattern vacioRegex = Pattern.compile("^[ ]*$");
	
	private PersonValidator() { }
	 
	public static void validateDni(String dni) {		
		String validChars = "TRWAGMYFPDXBNJZSQVHLCKET";
		String nifRexp = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]$";
		String nieRexp = "^[XYZ]{1}[0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]{1}$";
		String str = dni.toUpperCase();
		
		if (!str.matches(nifRexp) && !str.matches(nieRexp))
			throw new DniFormatException();
		
		String nie = str
		      .replaceAll("^[X]", "0")
		      .replaceAll("^[Y]", "1")
		      .replaceAll("^[Z]", "2");

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
	
	public static void validateTelefonoFijo(String fijo) {
		Pattern fijoRegex = 
			    Pattern.compile("^(\\+34|0034)?[8|9][0-9]{8}$", 
			    		Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = fijoRegex.matcher(fijo);
		
		if (!matcher.find())
			throw new TelefonoFormatException("fijo");
	}
	
	public static void validateTelefonoMovil(String movil) {
		Pattern movilRegex = 
			    Pattern.compile("^(\\+34|0034)?[6|7][0-9]{8}$", 
			    		Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = movilRegex.matcher(movil);
		
		if (!matcher.find())
			throw new TelefonoFormatException("móvil");
	}
	
	public static void validateNombre(String nombre) {	
		Matcher matcherVacio = vacioRegex.matcher(nombre);
		
		Pattern nombreRegex = 
			    Pattern.compile("^[a-zñÑA-Z ]{1,15}$", 
			    		Pattern.CASE_INSENSITIVE);

		Matcher matcher = nombreRegex.matcher(nombre);
		
		if (!matcher.find() || matcherVacio.find())
			throw new TextoFormatException("nombre");
	}
	
	public static void validateApellidos(String apellidos) {
		Matcher matcherVacio = vacioRegex.matcher(apellidos);
		
		Pattern apellidosRegex = 
			    Pattern.compile("^[a-zñÑA-Z ]{1,30}$", 
			    		Pattern.CASE_INSENSITIVE);

		Matcher matcher = apellidosRegex.matcher(apellidos);
		
		if (!matcher.find() || matcherVacio.find())
			throw new TextoFormatException("apellidos");
	}
	
	public static void validateDireccion(String direccion) {
		Matcher matcherVacio = vacioRegex.matcher(direccion);
		
		Pattern direccionRegex = 
			    Pattern.compile("^[/º0-9a-zñÑA-Z ]{1,50}$", 
			    		Pattern.CASE_INSENSITIVE);

		Matcher matcher = direccionRegex.matcher(direccion);
		
		if (!matcher.find() || matcherVacio.find())
			throw new TextoFormatException("dirección");
	}
	
	public static void validatePassword(String password) {
		Pattern passwordRegex = 
			    Pattern.compile("^[a-zA-Z0-9]{8,32}$", 
			    		Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = passwordRegex.matcher(password);
		if(!matcher.find()) {
			throw new PasswordFormatException();
		}
	}
}
