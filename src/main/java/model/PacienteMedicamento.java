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
@Table(name = "paciente_medicamento")
public class PacienteMedicamento implements Serializable{
	
	private Long codigo;
	private Paciente codigoPaciente;
	private Medicamento codigoMedicamento;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PacienteMedicamentoSeqOra")
    @SequenceGenerator(name="PacienteMedicamentoSeqOra",sequenceName="PACIENTE_MEDICAMENTO_SEQ", allocationSize=1)
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
	@JoinColumn(name="codigo_medicamento", referencedColumnName="codigo")
	public Medicamento getCodigoMedicamento() {
		return codigoMedicamento;
	}
	public void setCodigoMedicamento(Medicamento codigoMedicamento) {
		this.codigoMedicamento = codigoMedicamento;
	}
   
}
