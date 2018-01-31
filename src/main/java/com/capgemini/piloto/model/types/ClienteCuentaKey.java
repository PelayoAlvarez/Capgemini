package com.capgemini.piloto.model.types;

import java.io.Serializable;

public class ClienteCuentaKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3263142287407776214L;

	String cliente;
	String cuenta;

	public ClienteCuentaKey() {
	}

	public ClienteCuentaKey(String cliente, String cuenta) {
		this.cliente = cliente;
		this.cuenta = cuenta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteCuentaKey other = (ClienteCuentaKey) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (cuenta == null) {
			if (other.cuenta != null)
				return false;
		} else if (!cuenta.equals(other.cuenta))
			return false;
		return true;
	}

}
