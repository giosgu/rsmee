package model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="estado_auditoria_consulta")
@Named
public class EstadoAuditoriaConsulta implements Serializable{
	
	public EstadoAuditoriaConsulta(String cod) {
		super();
		setCodigo(cod);
	}
	public EstadoAuditoriaConsulta() {
		super();
	}
	
	public static final EstadoAuditoriaConsulta ACEPTADA = new EstadoAuditoriaConsulta("ACP");
	public static final EstadoAuditoriaConsulta RECHAZADA = new EstadoAuditoriaConsulta("RCH");
	
    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 3)
	private String codigo;
	
	@Column(name = "descripcion", nullable = false)
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
	
	//jsf
	public EstadoAuditoriaConsulta getACEPTADA() { return ACEPTADA; }
	public EstadoAuditoriaConsulta getRECHAZADA() { return RECHAZADA; }

}
