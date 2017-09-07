package model;


import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "patologia")
@Named
public class Patologia implements Serializable, ComboEntity {
	
	private Long codigo;
	private String descripcion;
	private String descripcionCorta;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PatologiaSeqOra")
    @SequenceGenerator(name="PatologiaSeqOra",sequenceName="PATOLOGIA_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
	@Column(name = "descripcion", length=120)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	 
	public String getLabel() {
		return descripcion;
	}
	
	public String getValue() {
		return codigo.toString();
	}
	
	public void setLabel(String label) {
		// TODO Auto-generated method stub
		
	}
	
	public void setValue(String label) {
		// TODO Auto-generated method stub
		
	}

	@Column(name = "descripcion_corta", length=10)
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	
	
	
    
}
