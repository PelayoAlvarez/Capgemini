package com.capgemini.piloto.model;

public class Association {

	public static class Titular{
		
		public static void link(Cliente cliente, ClienteCuenta titulo, Cuenta cuenta) {
			titulo.setCliente(cliente);
			titulo.setCuenta(cuenta);
			cliente._getClienteCuenta().add(titulo);
			cuenta._getClienteCuenta().add(titulo);
		}
		
		public static void unlink(ClienteCuenta titulo) {
			titulo.setMcaHabilitado(false);
		}
	}
}
