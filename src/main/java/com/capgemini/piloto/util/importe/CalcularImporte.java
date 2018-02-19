package com.capgemini.piloto.util.importe;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Movimiento;
import com.capgemini.piloto.model.dto.MovimientoDTO;
import com.capgemini.piloto.model.historico.CuentaH;
import com.capgemini.piloto.model.types.TipoMovimiento;
import com.capgemini.piloto.repository.CuentaRepository;
import com.capgemini.piloto.repository.historico.CuentaHRepository;

public class CalcularImporte {
	
	private CalcularImporte() {}

	@Autowired
	private static CuentaHRepository cuentaRepositoryH;

	@Autowired
	private static CuentaRepository cuentaRepository;

	public static boolean calcular(Cuenta cuenta, Movimiento movimiento, MovimientoDTO movimientoDetails) {
		boolean estado = true;
		double saldo = cuenta.getImporte();
		if (movimiento.getTipo().equals(movimientoDetails.getTipo())) {
			if (movimiento.getImporte() != movimientoDetails.getImporte()) {
				double valor = Math.abs(movimiento.getImporte() - movimientoDetails.getImporte());
				if (movimientoDetails.getTipo() == TipoMovimiento.CARGO) {
					if (cuenta.getImporte() > 0) {
						cuenta.setImporte(cuenta.getImporte() - valor);
					} else {
						estado = false;
					}
				} else {
					cuenta.setImporte(cuenta.getImporte() + valor);
				}
			}
		} else {
			estado = calculoImpAlt(saldo, cuenta, movimiento, movimientoDetails);
		}
		return estado;
	}

	private static boolean calculoImpAlt(double saldo, Cuenta cuenta, Movimiento movimiento,
			MovimientoDTO movimientoDetails) {
		boolean estado = true;
		double valor = 0;
		if (movimiento.getImporte() != movimientoDetails.getImporte()) {
			valor = movimiento.getImporte() + movimientoDetails.getImporte();
			if (movimientoDetails.getTipo() == TipoMovimiento.ABONO) {
				cuenta.setImporte(cuenta.getImporte() + valor);
			} else {
				if (cuenta.getImporte() > 0) {
					cuenta.setImporte(cuenta.getImporte() - valor);
				} else {
					estado = false;
				}
			}
		} else {
			estado = calculoImpAltB(movimientoDetails, cuenta);
		}
		if (estado) {
			actualizarCuenta(saldo, cuenta);
		}
		return estado;
	}
	
	private static boolean calculoImpAltB(MovimientoDTO movimientoDetails, Cuenta cuenta) {
		if (movimientoDetails.getTipo() == TipoMovimiento.ABONO) {
			cuenta.setImporte(cuenta.getImporte() + 2 * movimientoDetails.getImporte());
		} else {
			if (cuenta.getImporte() > 0) {
				cuenta.setImporte(cuenta.getImporte() - 2 * movimientoDetails.getImporte());
			} else {
				return false;
			}
		}
		return true;
	}
	
	private static void actualizarCuenta(double saldo, Cuenta cuenta) {
		if (saldo != cuenta.getImporte()) {
			CuentaH ch = new CuentaH(cuenta, cuenta.getUsuario());
			ch.setImporte(saldo);
			cuentaRepositoryH.save(ch);
			cuentaRepository.save(cuenta);
		}
	}
}
