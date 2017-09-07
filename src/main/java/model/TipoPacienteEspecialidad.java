package model;

import java.io.Serializable;

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
@Table(name="tipo_paciente_especialidad")
public class TipoPacienteEspecialidad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private TipoPaciente tipoPaciente;
	private Especialidad especialidad;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TipoPacienteEspecialidadSeqOra")
    @SequenceGenerator(name="TipoPacienteEspecialidadSeqOra",sequenceName="TIPO_PACIENTE_ESPECIALIDAD_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_paciente", referencedColumnName="codigo", nullable=false)
	public TipoPaciente getTipoPaciente() {
		return tipoPaciente;
	}
	public void setTipoPaciente(TipoPaciente tipoPaciente) {
		this.tipoPaciente = tipoPaciente;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="especialidad", referencedColumnName="codigo", nullable=false)
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
}
