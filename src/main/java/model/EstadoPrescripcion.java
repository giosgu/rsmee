package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Size;


@Entity
@Table(name = "estado_prescripcion")
public class EstadoPrescripcion implements Serializable {

	public static EstadoPrescripcion ESTADO_SOLICITADO = new EstadoPrescripcion("SOL");
	public static EstadoPrescripcion ESTADO_RECHAZADO_MEDICO = new EstadoPrescripcion("RME");
	public static EstadoPrescripcion ESTADO_SUCURSAL = new EstadoPrescripcion("SUC");
	public static EstadoPrescripcion ESTADO_FINALIZADO = new EstadoPrescripcion("FIN");
	public static EstadoPrescripcion ESTADO_PROBLEMA_RECETA = new EstadoPrescripcion("PRE");
	public static EstadoPrescripcion ESTADO_ACEPTADO_SUCURSAL = new EstadoPrescripcion("ASU");
	
	public static String SOLICITADO = "SOL";
	public static String RECHAZADO_MEDICO = "RME";
	
	public EstadoPrescripcion(){
		super();
	}
	
	public EstadoPrescripcion(String codigo){
		this.codigo = codigo;
	}
	
    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 3)
	private String codigo;
    
    @Column(name = "descripcion", length = 30)
	private String descripcion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}

