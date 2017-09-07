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


@Entity
@Table(name = "paciente_patologia")
@Named
public class PacientePatologia implements Serializable, ComboEntity{
	
	private Long codigo;
	private Paciente codigoPaciente;
	private Patologia patologia;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PacientePatologiaSeqOra")
    @SequenceGenerator(name="PacientePatologiaSeqOra",sequenceName="PACIENTE_PATOLOGIA_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_paciente", referencedColumnName="codigo")
	public Paciente getCodigoPaciente() {
		return codigoPaciente;
	}
	public void setCodigoPaciente(Paciente codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_patologia", referencedColumnName="codigo")
	public Patologia getPatologia() {
		return patologia;
	}
	public void setPatologia(Patologia patologia) {
		this.patologia = patologia;
	}
	
	public String getLabel() {
		return patologia.getDescripcion();
	}
	
	public String getValue() {
		return patologia.getCodigo().toString();
	}
	
	public void setLabel(String label) {
		// TODO Auto-generated method stub
		
	}
	
	public void setValue(String label) {
		// TODO Auto-generated method stub
		
	}
	
}
