package model;


import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * @author alessiof
 *
 */
@Entity
@Table(name = "genero")
@Named
public class Genero implements Serializable, ComboEntity {
		
	//ids
	public static final String STR_MASCULINO = "M";
	public static final String STR_FEMENINO = "F";
	
	private String codigo;
	private String descripcion;
	
	@Id
    @Column(name = "codigo", unique = true, nullable = false, columnDefinition="CHAR", length=1)
	public String getCodigo() {
		return codigo;
	}    
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "descripcion", length = 30)
	@Size(max = 30)
	public String getDescripcion() {
		return descripcion;
	}	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	//Combo
	
	public String getLabel() {
		return getDescripcion();
	}
	
	public String getValue() {
		return getCodigo();
	}
	
	public void setLabel(String label) {}
	
	public void setValue(String label) {}
	
}
