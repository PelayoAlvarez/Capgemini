package com.capgemini.piloto.model;

public class Association {

	public static class TitularCliente{
		
		public static void link(Cliente cliente, ClienteCuenta titulo, Cuenta cuenta) {
			titulo.setCliente(cliente);
			titulo.setCuenta(cuenta);
			cliente._getClienteCuenta().add(titulo);
			cuenta._getClienteCuenta().add(titulo);
		}
		
		public static void unlink(ClienteCuenta titulo,Cuenta cuenta) {
			titulo.setMcaHabilitado(false);
			for (ClienteCuenta titular : cuenta._getClienteCuenta()) {
				if(titular.getMcaHabilitado())
					return;
			}
			
			//esto habra que pulirlo mas adelante
			cuenta.setMCAHabilitado(false);
		}
	}

	public static class TitularCuenta{
		
		public static void link(Cliente cliente, ClienteCuenta titulo, Cuenta cuenta) {
			titulo.setCliente(cliente);
			titulo.setCuenta(cuenta);
			cliente._getClienteCuenta().add(titulo);
			cuenta._getClienteCuenta().add(titulo);
		}
		
		public static void unlink(ClienteCuenta titulo, Cliente cliente) {
			titulo.setMcaHabilitado(false);
			for (ClienteCuenta titular : cliente._getClienteCuenta()) {
				if(titular.getMcaHabilitado())
					return;
			}
			
			//esto habra que pulirlo mas adelante
			cliente.setMCAHabilitado(false);
		}
	}
}
