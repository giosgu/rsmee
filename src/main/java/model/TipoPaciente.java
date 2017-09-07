package model;


import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="tipo_paciente")
@Named
public class TipoPaciente implements Serializable, ComboEntity {
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;
	
	public static final TipoPaciente PEDIATRIA = new TipoPaciente("P");
	public static final TipoPaciente ADULTO = new TipoPaciente("A");
	
	public TipoPaciente(){
		super();
	}

	public TipoPaciente(String codigo){
		super();
		this.codigo = codigo;
	}
	
	@Id 
	@Column(name="codigo", unique=true, nullable=false)
	@Size(max=1)
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name="descripcion", length=256)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	//Combos
	@Override
	public String getLabel() {		
		return getDescripcion();
	}
	@Override
	public String getValue() {
		return getCodigo();
	}
	@Override
	public void setLabel(String label) {}
	@Override
	public void setValue(String label) {}
	
	
}
