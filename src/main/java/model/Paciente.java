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
@Table(name = "paciente")
public class Paciente implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PacienteSeqOra")
    @SequenceGenerator(name="PacienteSeqOra",sequenceName="PACIENTE_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;

	@Column(name = "altura")
	@Size(max = 3)
	private String altura;
	
	@Column(name = "peso")
	@Size(max = 6)
	private String peso;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario", referencedColumnName="codigo")
	private Usuario codigoUsuario;
	
	@Column(name = "codigo_historia_clinica")
	private Integer codHistoriaClinica;
	
	//NO MODIFICAR EL LAZY
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "paciente_patologia", 
				joinColumns = { @JoinColumn(name = "codigo_paciente") }, 
				inverseJoinColumns = { @JoinColumn(name = "codigo_patologia") }
	)
	private List<Patologia> patologias;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_paciente", referencedColumnName="codigo")
	private TipoPaciente tipoPaciente;
	
	public List<Patologia> getPatologias() {
		return patologias;
	}
	public void setPatologias(List<Patologia> patologias) {
		this.patologias = patologias;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}	

	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	public Usuario getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(Usuario codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	public Integer getCodHistoriaClinica() {
		return codHistoriaClinica;
	}
	public void setCodHistoriaClinica(Integer codHistoriaClinica) {
		this.codHistoriaClinica = codHistoriaClinica;
	}
	
	public TipoPaciente getTipoPaciente() {
		return tipoPaciente;
	}
	public void setTipoPaciente(TipoPaciente tipoPaciente) {
		this.tipoPaciente = tipoPaciente;
	}
	
}
