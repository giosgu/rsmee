package model;

import java.io.Serializable;

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
@Table(name="profesional_licencia_contacto")
@SuppressWarnings("serial")
public class ProfesionalLicenciaContacto implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ProfesionailLicenciaContactoSeqOra")
	@SequenceGenerator(name="ProfesionailLicenciaContactoSeqOra", sequenceName="PROFESIONAL_LICENCIA_CONTACTO_SEQ", allocationSize=1)
	@Column(name="codigo")
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="codigo", name="codigo_profesional_licencia")
	private ProfesionalLicencia profesionalLicencia;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="codigo", name="codigo_contacto")
	private Contacto contacto;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public ProfesionalLicencia getProfesionalLicencia() {
		return profesionalLicencia;
	}
	public void setProfesionalLicencia(ProfesionalLicencia profesionalLicencia) {
		this.profesionalLicencia = profesionalLicencia;
	}
	public Contacto getContacto() {
		return contacto;
	}
	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}


}
