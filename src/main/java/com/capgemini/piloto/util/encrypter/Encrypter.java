package com.capgemini.piloto.util.encrypter;

public class Encrypter {
	
	private static Encrypter instance;

	public static Encrypter getInstance() {
		if (instance == null)
			instance = new Encrypter();

		return instance;
	}

	public String encriptar(String input) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<input.length(); i++) {
			sb.append(input.charAt(i) + 1);
		}
		return sb.toString();
	} 
}
