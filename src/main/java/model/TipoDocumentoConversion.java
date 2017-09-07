package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_documento_conversion")
public class TipoDocumentoConversion implements Serializable{
	

	public TipoDocumentoConversion(){
		super();
	}
	
	public TipoDocumentoConversion(String codigo){
		this.codigo = codigo;
	}
	
	@Id
	@Column(name="codigo", nullable = false, unique=true)
	private String codigo;
	
	@Column(name="descripcion", nullable=false)
	private String descripcion;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


}
