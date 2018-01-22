package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class TableItem {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private Integer cod_Entidad;
    
    private String descripcion;

    private String clave;
    
    private Integer valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	//Returns information about TableItem
	@Override
	public String toString() {
		return "TableItem [id=" + id + ", cod_entidad=" + cod_Entidad + ", descripcion=" + descripcion + ", clave="
				+ clave + ", valor=" + valor + "]";
	}
	
	//Returns the value of codEntidad
	public Integer getCodEntidad() {
		return cod_Entidad;
	}
	
	//Modifies the value of codEntidad
	public void setCodEntidad(Integer codEntidad) {
		this.cod_Entidad = codEntidad;
	}
	
	//Returns the value of descripcion
	public String getDescripcion() {
		return descripcion;
	}
	
	//Modifies the value of descripcion
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	//Returns the value of clave
	public String getClave() {
		return clave;
	}
	
	//Modifies the value of clave
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	//Returns the value of valor
	public Integer getValor() {
		return valor;
	}
	
	//Modifies the value of valor
	public void setValor(Integer valor) {
		this.valor = valor;
	}
    
    
}

