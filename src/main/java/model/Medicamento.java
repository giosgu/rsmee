package model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "medicamento")
public class Medicamento implements Serializable{
	
	private Long codigo;
	private String nombre;
	private String presentacion;
	private String dosis;
	private Date fechaDesde;
	private Date fechaHasta;
	private String laboratorio;

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="MedicamentoSeqOra")
    @SequenceGenerator(name="MedicamentoSeqOra",sequenceName="MEDICAMENTO_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Column(name = "presentacion")
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	
	@Column(name = "dosis")
	public String getDosis() {
		return dosis;
	}
	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	
	@Column(name = "fecha_desde")
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	
	
	@Column(name = "fecha_hasta")
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	@Column(name = "laboratorio")
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

    
}
