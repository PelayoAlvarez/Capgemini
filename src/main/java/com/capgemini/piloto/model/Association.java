package com.capgemini.piloto.model;

public class Association {

	private Association() {
	}

	public static class TitularCliente {

		private TitularCliente() {
		}

		public static void link(Cliente cliente, ClienteCuenta titulo, Cuenta cuenta) {
			titulo.setCliente(cliente);
			titulo.setCuenta(cuenta);

			cliente.pgetClienteCuentas().add(titulo);
			cuenta.pgetClienteCuenta().add(titulo);

		}

		public static void unlink(ClienteCuenta titulo, Cuenta cuenta) {
			titulo.setMcaHabilitado(false);

			// miramos si la cuenta linqueada al cliente que borramos es solo suya, si no lo
			// es entonces habra que borrar la cuenta
			for (ClienteCuenta titular : cuenta.pgetClienteCuenta()) {
				if (titular.getMcaHabilitado())
					return;
			}
			cuenta.setmCAHabilitado(false);
			titulo.getTarjetas().forEach(tarjeta -> tarjeta.setMcaHabilitado(false));
		}
	}

	public static class TitularCuenta {

		private TitularCuenta() {
		}

		public static void link(Cliente cliente, ClienteCuenta titulo, Cuenta cuenta) {
			titulo.setCliente(cliente);
			titulo.setCuenta(cuenta);

			cliente.pgetClienteCuentas().add(titulo);
			cuenta.pgetClienteCuenta().add(titulo);
		}

		public static void unlink(ClienteCuenta cc) {
			cc.setMcaHabilitado(false);
			cc.getTarjetas().forEach(tarjeta -> tarjeta.setMcaHabilitado(false));
		}
	}
}
