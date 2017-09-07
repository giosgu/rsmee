package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.validation.constraints.Size;


@Entity
@Table(name="diagnostico")
public class Diagnostico implements Serializable{

	private Long codigo;
	private String codigoDiagnostico;
	private String descripcion;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DiagnosticoSeqOra")
	@SequenceGenerator(name="DiagnosticoSeqOra", sequenceName="DIAGNOSTICO_SEQ", allocationSize=1)
	@Column(name="codigo", nullable=false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@Column(name="codigo_diagnostico", nullable=false, length=16)
	@Size(max=16)
	public String getCodigoDiagnostico() {
		return codigoDiagnostico;
	}
	public void setCodigoDiagnostico(String codigoDiagnostico) {
		this.codigoDiagnostico = codigoDiagnostico;
	}
	
	@Column(name="descripcion", nullable=false, length=128)
	@Size(max=128)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
	
	
}
