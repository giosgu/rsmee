package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.validation.constraints.Size;

@Entity
@Table(name = "profesional")
public class Profesional implements Serializable{
	
	private Long codigo;
	private String matriculaNacional;
	private String matriculaProvincial;
	private Provincia codigoProvinciaMatriculal;
	private String institucion;
	private String titulo;
	private List<Especialidad> especialidades;
	private Long anioTitulo;
	private String lugarAtencion;
	private String diasAtencion;
	private String horarioAtencion;
	private String sloganPresentacion;
	private String facebook;
	private String tweeter;	
	private String pathCV;
	private String pathFirmaPerfil;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ProfesionalSeqOra")
	@SequenceGenerator(name="ProfesionalSeqOra",sequenceName="PROFESIONAL_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "matricula_nacional")
	public String getMatriculaNacional() {
		return matriculaNacional;
	}
	public void setMatriculaNacional(String matriculaNacional) {
		this.matriculaNacional = matriculaNacional;
	}
	
	@Column(name = "matricula_provincial")
	public String getMatriculaProvincial() {
		return matriculaProvincial;
	}
	public void setMatriculaProvincial(String matriculaProvincial) {
		this.matriculaProvincial = matriculaProvincial;
	}
		
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_provincia_matricula", referencedColumnName="codigo")
	public Provincia getCodigoProvinciaMatriculal() {
		return codigoProvinciaMatriculal;
	}
	public void setCodigoProvinciaMatriculal(Provincia codigoProvinciaMatriculal) {
		this.codigoProvinciaMatriculal = codigoProvinciaMatriculal;
	}
		
	@Column(name = "institucion")
	public String getInstitucion() {
		return institucion;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
		
	@Column(name = "titulo")
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "profesional_especialidad", 
				joinColumns = { @JoinColumn(name = "codigo_profesional") }, 
				inverseJoinColumns = { @JoinColumn(name = "codigo_especialidad") }
	)
	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
		
	@Column(name = "anio_titulo")
	public Long getAnioTitulo() {
		return anioTitulo;
	}
	public void setAnioTitulo(Long anioTitulo) {
		this.anioTitulo = anioTitulo;
	}
		
	@Column(name = "lugar_atencion")
	public String getLugarAtencion() {
		return lugarAtencion;
	}
	public void setLugarAtencion(String lugarAtencion) {
		this.lugarAtencion = lugarAtencion;
	}
		
	@Column(name = "dias_atencion")
	public String getDiasAtencion() {
		return diasAtencion;
	}
	public void setDiasAtencion(String diasAtencion) {
		this.diasAtencion = diasAtencion;
	}
		
	@Column(name = "horario_atencion")
	public String getHorarioAtencion() {
		return horarioAtencion;
	}
	public void setHorarioAtencion(String horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}
	
	@Column(name = "slogan_presentacion")
	public String getSloganPresentacion() {
		return sloganPresentacion;
	}
	public void setSloganPresentacion(String sloganPresentacion) {
		this.sloganPresentacion = sloganPresentacion;
	}
	
	@Column(name = "facebook")
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	
	@Column(name = "tweeter")
	public String getTweeter() {
		return tweeter;
	}
	public void setTweeter(String tweeter) {
		this.tweeter = tweeter;
	}
	
	@Column(name = "path_cv")
	@Size(max = 256)
	public String getPathCV() {
		return pathCV;
	}
	public void setPathCV(String pathCV) {
		this.pathCV = pathCV;
	}
	
	@Column(name = "path_firma_perfil")
	@Size(max = 256)
	public String getPathFirmaPerfil() {
		return pathFirmaPerfil;
	}
	public void setPathFirmaPerfil(String pathFirmaPerfil) {
		this.pathFirmaPerfil = pathFirmaPerfil;
	}
	
	
}
