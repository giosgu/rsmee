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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="criterio")
public class Criterio implements Serializable{	
	    
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
    
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_mensaje", referencedColumnName="codigo")
	@NotNull
    private Mensaje mensaje;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_criterio_tipo", referencedColumnName="codigo")
	@NotNull
	private CriterioTipo criterioTipo;

	@Column(name = "valor_codigo")
	@Size(max = 32)
	private String valorCodigo;
	
	@Column(name = "valor_descripcion")
	@Size(max = 128)
	private String valorDescripcion;

	public Long getCodigo() {
		return this.codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
		
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	
	public CriterioTipo getCriterioTipo() {
		return criterioTipo;
	}
	public void setCriterioTipo(CriterioTipo criterioTipo) {
		this.criterioTipo = criterioTipo;
	}
	
	public String getValorCodigo() {
		return valorCodigo;
	}
	public void setValorCodigo(String valorCodigo) {
		this.valorCodigo = valorCodigo;
	}
	
	public String getValorDescripcion() {
		return valorDescripcion;
	}
	public void setValorDescripcion(String valorDescripcion) {
		this.valorDescripcion = valorDescripcion;
	}

}
