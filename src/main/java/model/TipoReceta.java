package model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;


@Entity
@Table(name="tipo_receta")
@Named
public class TipoReceta implements Serializable{

	private String codigo;
	private String descripcion;
	
	public TipoReceta(){
		super();
	}	
	public TipoReceta(String codigo){
		super();
		this.codigo = codigo;
	}
	
	public static final TipoReceta AUTOMATICA = new TipoReceta("AUT");
	public static final TipoReceta MANUAL = new TipoReceta("MAN");
	
    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 3)
    @SequenceGenerator(name="TipoRecetaSeq",sequenceName="TIPO_RECETA_SEQ", allocationSize=1)
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "descripcion", length = 64)
	@Size(max = 64)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Transient
	public TipoReceta getAUTOMATICA() {
		return AUTOMATICA;
	}
	@Transient
	public TipoReceta getMANUAL() {
		return MANUAL;
	}
	
}
