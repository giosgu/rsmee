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
@Table(name = "zona_profesional")
public class ZonaProfesional implements Serializable{
	
	private Long codigo;
	private Zona codigoZona;
	private Profesional codigoProfesional;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ZonaProfesionalSeqOra")
    @SequenceGenerator(name="ZonaProfesionalSeqOra",sequenceName="ZONA_PROFESIONAL_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_zona", referencedColumnName="codigo")
	public Zona getCodigoZona() {
		return codigoZona;
	}
	public void setCodigoZona(Zona codigoZona) {
		this.codigoZona = codigoZona;
	}
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_profesional", referencedColumnName="codigo")
	public Profesional getCodigoProfesional() {
		return codigoProfesional;
	}
	public void setCodigoProfesional(Profesional codigoProfesional) {
		this.codigoProfesional = codigoProfesional;
	}
 
}
