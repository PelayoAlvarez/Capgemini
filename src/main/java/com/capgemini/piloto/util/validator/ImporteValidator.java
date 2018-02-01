package com.capgemini.piloto.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.piloto.errors.impl.ImporteFormatException;

public class ImporteValidator {
	
	private ImporteValidator() { }
	
	public static void validateImporte(String importe) {
		Pattern importeRegex = 
			    Pattern.compile("^([0-9]{1,15})(\\.[0-9]{1,2})?$", 
			    		Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = importeRegex.matcher(importe);
		
		if (!matcher.find())
			throw new ImporteFormatException();
	}
}
