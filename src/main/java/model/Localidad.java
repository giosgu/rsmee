package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.validation.constraints.Size;


@Entity
@Table(name="localidad")
public class Localidad implements ComboEntity, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String descripcion;
	private Provincia codigoProvincia;
	
    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 10)
    @SequenceGenerator(name="LocalidadSeqOra",sequenceName="LOCALIDAD_SEQ", allocationSize=1)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "descripcion", length = 60)
	@Size(max = 60)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_provincia", referencedColumnName="codigo")	
	public Provincia getCodigoProvincia() {
		return codigoProvincia;
	}
	public void setCodigoProvincia(Provincia codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}
	
	//Para Cargar Combos	
	public String getLabel() {
		return descripcion;
	}	
	public String getValue() {
		return codigo.toString();
	}	
	public void setLabel(String label) {
	}	
	public void setValue(String value) {
	}

}
