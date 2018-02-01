package com.capgemini.piloto.model;

public class Validation {
	
	private Validation() {
		
	}
	
	public static boolean dniValido(String dni) {
		String validChars = "TRWAGMYFPDXBNJZSQVHLCKET";
		String nifRexp = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]$";
		String nieRexp = "^[XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]$";
		String str = dni.toUpperCase();
		
		if (!str.matches(nifRexp) && !str.matches(nieRexp)) return false;
		
		String nie = str
		      .replaceAll("^[X]", "0")
		      .replaceAll("^[Y]", "1")
		      .replaceAll("^[Z]", "2");
		
		char letter = str.charAt(str.length() - 1);
		int charIndex = Integer.parseInt(nie.substring(0, 8)) % 23;
		
		return validChars.charAt(charIndex) == letter;
	}
	
}
