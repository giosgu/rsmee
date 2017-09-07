package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Plan implements Serializable{
	
   private Long codigo;
   private String descripcion;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PlanSeqOra")
    @SequenceGenerator(name="PlanSeqOra",sequenceName="PLAN_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
    public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
