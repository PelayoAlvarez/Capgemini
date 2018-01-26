package com.capgemini.piloto.model;

public class Association {
	
	private Association() {
	}

	public static class TitularCliente{
		
		private TitularCliente() {
		}
		
		public static void link(Cliente cliente, ClienteCuenta titulo, Cuenta cuenta) {
			titulo.setCliente(cliente);
			titulo.setCuenta(cuenta);
			cliente._getClienteCuentas().add(titulo);
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
		
		private TitularCuenta() {
		}
		public static void link(Cliente cliente, ClienteCuenta titulo, Cuenta cuenta) {
			titulo.setCliente(cliente);
			titulo.setCuenta(cuenta);
			cliente._getClienteCuentas().add(titulo);
			cuenta._getClienteCuenta().add(titulo);
		}
		
		public static void unlink(ClienteCuenta cc) {
			cc.setMcaHabilitado(false);
		}
	}
}
