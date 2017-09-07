package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="profesional_licencia")
@SuppressWarnings("serial")
public class ProfesionalLicencia implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ProfesionalLicenciaSeqOra")
	@SequenceGenerator(name="ProfesionalLicenciaSeqOra", sequenceName="PROFESIONAL_LICENCIA_SEQ", allocationSize=1)
	@Column(name="codigo")
    private Long codigo;
	
	@ManyToOne
	@JoinColumn(name="titular", referencedColumnName="codigo")
	private Usuario titular;
	
	@ManyToOne
	@JoinColumn(name="suplente", referencedColumnName="codigo")
	private Usuario suplente;
	
	@Column(name="fecha_desde")
	private Date fechaDesde;
	
	@Column(name="fecha_hasta")
	private Date fechaHasta;

	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Usuario getTitular() {
		return titular;
	}
	public void setTitular(Usuario titular) {
		this.titular = titular;
	}
	public Usuario getSuplente() {
		return suplente;
	}
	public void setSuplente(Usuario suplente) {
		this.suplente = suplente;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}
