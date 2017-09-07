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
@Table(name = "plan_profesional")
public class PlanProfesional implements Serializable{
	
	private Long codigo;
	private Profesional profesional;
	private Plan plan;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PlanProfesionalSeqOra")
    @SequenceGenerator(name="PlanProfesionalSeqOra",sequenceName="PLAN_PROFESIONAL_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_profesional", referencedColumnName="codigo")
	public Profesional getProfesional() {
		return profesional;
	}
	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_plan", referencedColumnName="codigo")
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

}
