package model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Size;

@Entity
@Table(name="acceso_usuario")
public class AccesoUsuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8211880287592813713L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="AccesoUsuarioSeqOra")
	@SequenceGenerator(name="AccesoUsuarioSeqOra",sequenceName="ACCESO_USUARIO_SEQ", allocationSize=1)
	@Column(name="codigo", unique=true, nullable=false)
    private Long codigo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario", referencedColumnName="codigo", nullable=false)
	private Usuario usuario;
	
	@Column(name="fecha_ingreso", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;
	
	@Column(name="fecha_egreso", nullable=true)
	@Temporal(TemporalType.TIMESTAMP) 
	private Date fechaEgreso;
	
	@Column(name="sistema_operativo")
	@Size(max=256)
	private String sistemaOperativo;
	
	@Column(name="dispositivo")
	@Size(max=256)
	private String dispositivo;
	
	@Column(name="navegador")
	@Size(max=256)
	private String navegador;
	
	@Column(name="navegador_detalle")
	@Size(max=256)
	private String navegadorDetalle;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaEgreso() {
		return fechaEgreso;
	}
	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}
	public String getSistemaOperativo() {
		return sistemaOperativo;
	}
	public void setSistemaOperativo(String sistemaOperativo) {
		this.sistemaOperativo = sistemaOperativo;
	}
	public String getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}
	public String getNavegador() {
		return navegador;
	}
	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}
	public String getNavegadorDetalle() {
		return navegadorDetalle;
	}
	public void setNavegadorDetalle(String navegadorDetalle) {
		this.navegadorDetalle = navegadorDetalle;
	}
	
}
