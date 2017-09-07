package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estado_relacion")
public class EstadoRelacion implements Serializable{
	
	public static EstadoRelacion ESTADO_INICIAL = new EstadoRelacion("SOL");
	public static EstadoRelacion ESTADO_BAJA_DESTINO = new EstadoRelacion("BAD");
	public static EstadoRelacion ESTADO_BAJA_ORIGEN = new EstadoRelacion("BAO");
	public static EstadoRelacion ESTADO_ACEPTADA= new EstadoRelacion("ACP");
	public static EstadoRelacion ESTADO_IGNORADA= new EstadoRelacion("IGD");
	public static EstadoRelacion ESTADO_BAJA_SISTEMA= new EstadoRelacion("BAS");
	
	public static String INICIAL = "SOL";

	private String codigo;
	private String descripcion;

	public EstadoRelacion(){
		super();
	}
	
	public EstadoRelacion(String codigo){
		super();
		this.codigo = codigo;
	}

	@Id
    @Column(name = "codigo", unique = true, nullable = false, length=3)
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
