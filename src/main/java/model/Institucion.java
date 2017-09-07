package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "institucion")
public class Institucion extends CreateAuditoryDataEntity implements ComboEntity {
	
	private Long codigo;
	private String descripcion;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="InstitucionSeqOra")
    @SequenceGenerator(name="InstitucionSeqOra",sequenceName="INSTITUCION_SEQ", allocationSize=1)
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
	
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setLabel(String label) {
		// TODO Auto-generated method stub
		
	}
	
	public void setValue(String label) {
		// TODO Auto-generated method stub
		
	}
	
	
}
