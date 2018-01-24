package com.capgemini.piloto.model;

public class Association {

	public static class Titular{
		
		public static void link(Cliente cliente, ClienteCuenta titulo, Cuenta cuenta) {
			titulo.setCliente(cliente);
			titulo.setCuenta(cuenta);
			cliente._getCuentas().add(titulo);
			cuenta._getClientes().add(cliente);
		}
		
		public static void unlink(ClienteCuenta titulo) {
			titulo.setMcaHabilitado(false);
		}
	}
}
