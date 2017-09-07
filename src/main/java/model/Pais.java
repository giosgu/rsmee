package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Size;

@Entity
@Table(name = "pais")
public class Pais extends CreateAuditoryDataEntity implements ComboEntity {
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;
	
    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 10)	
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
	
	//Para Cargar Combos
	
	public String getLabel() {
		return descripcion;
	}
	
	public String getValue() {
		return codigo.toString();
	}
	
	public void setLabel(String label) {
	}
	
	
	public void setValue(String value) {
	}

}
