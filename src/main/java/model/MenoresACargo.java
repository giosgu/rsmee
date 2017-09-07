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
@Table(name = "menores_a_cargo")
public class MenoresACargo implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MenoresACargoSeqOra")
	@SequenceGenerator(name="MenoresACargoSeqOra", sequenceName="MENORES_A_CARGO_SEQ", allocationSize=1)
	@Column(name="codigo", unique=true, nullable=true)
    private Long codigo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="adulto_responsable", referencedColumnName="codigo")
	private Usuario adultoResponsable;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="menor", referencedColumnName="codigo")
	private Usuario menor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vinculoFamiliar", referencedColumnName="codigo")
	private VinculoFamiliar vinculoFamiliar;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="estado", referencedColumnName="codigo")
	private EstadoMenoresACargo estado;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Usuario getAdultoResponsable() {
		return adultoResponsable;
	}
	public void setAdultoResponsable(Usuario adultoResponsable) {
		this.adultoResponsable = adultoResponsable;
	}

	public Usuario getMenor() {
		return menor;
	}
	public void setMenor(Usuario menor) {
		this.menor = menor;
	}

	public VinculoFamiliar getVinculoFamiliar() {
		return vinculoFamiliar;
	}
	public void setVinculoFamiliar(VinculoFamiliar vinculoFamiliar) {
		this.vinculoFamiliar = vinculoFamiliar;
	}
	
	public EstadoMenoresACargo getEstado() {
		return estado;
	}
	public void setEstado(EstadoMenoresACargo estado) {
		this.estado = estado;
	}	
	

}
