package com.capgemini.piloto.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.piloto.errors.impl.NumeroCuentaFormatException;

public class CuentaValidator {

	private static CuentaValidator instance;

	private CuentaValidator() {
	}

	public static CuentaValidator getInstance() {
		if (instance == null) {
			instance = new CuentaValidator();
		}
		return instance;
	}

	public static void validateCuenta(String numCuenta) {
		Pattern cuentaRegex = Pattern.compile("^[0-9]{25}$", Pattern.CASE_INSENSITIVE);

		Matcher matcher = cuentaRegex.matcher(numCuenta);

		if (!matcher.find())
			throw new NumeroCuentaFormatException();
	}
}
