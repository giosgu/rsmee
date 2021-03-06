package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "zona")
public class Zona implements Serializable{
	
    private Long codigo;
    private String descripcion;
	
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ZonaSeqOra")
    @SequenceGenerator(name="ZonaSeqOra",sequenceName="ZONA_SEQ", allocationSize=1)
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
