package model;

import java.io.Serializable;

import javax.inject.Named;
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
import javax.validation.constraints.Size;



@Entity
@Table(name = "profesional_especialidad")
@Named
public class ProfesionalEspecialidad implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 888628102044759648L;
	private Long codigo;
	private Profesional codigo_profesional;
	private Especialidad codigoEspecialidad;
	private String institucion;
	private Long anioEspecialidad;

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ProfesionalEspecialidadSeqOra")
	@SequenceGenerator(name="ProfesionalEspecialidadSeqOra",sequenceName="PROFESIONAL_ESPECIALIDAD_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_profesional", referencedColumnName="codigo")
	public Profesional getCodigo_profesional() {
		return codigo_profesional;
	}
	public void setCodigo_profesional(Profesional codigoProfesional) {
		codigo_profesional = codigoProfesional;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_especialidad", referencedColumnName="codigo")
	public Especialidad getCodigoEspecialidad() {
		return codigoEspecialidad;
	}
	public void setCodigoEspecialidad(Especialidad codigoEspecialidad) {
		this.codigoEspecialidad = codigoEspecialidad;
	}

	
	@Column(name = "institucion", length = 128)
	@Size(max = 128)
	public String getInstitucion() {
		return institucion;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	
	@Column(name = "anio_especialidad")
	public Long getAnioEspecialidad() {
		return anioEspecialidad;
	}
	public void setAnioEspecialidad(Long anioEspecialidad) {
		this.anioEspecialidad = anioEspecialidad;
	}

}
