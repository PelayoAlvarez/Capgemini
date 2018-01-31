package com.capgemini.piloto.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.piloto.errors.impl.ImporteFormatException;

public class ImporteValidator {
	
	private static ImporteValidator instance;
	
	private ImporteValidator() {}
	
	public static ImporteValidator getInstance() {
		if(instance == null) {
			instance = new ImporteValidator();
		}
		return instance;
	}
	
	public static void validateImporte(String importe) {
		Pattern importeRegex = 
			    Pattern.compile("^([0-9]{1,15})(\\.[0-9]{1,2})?$", 
			    		Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = importeRegex.matcher(importe);
		
		if (!matcher.find())
			throw new ImporteFormatException();
	}
}