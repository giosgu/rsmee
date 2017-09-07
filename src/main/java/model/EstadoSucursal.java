package model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Size;


@Entity
@Table(name = "estado_sucursal")
@Named
public class EstadoSucursal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final EstadoSucursal ESTADO_ACTIVO = new EstadoSucursal("ACT");
	public static final EstadoSucursal ESTADO_BAJA = new EstadoSucursal("BAJ");

	private String codigo;
    private String descripcion;
    
    public EstadoSucursal(String codigo){
    	this.codigo = codigo;
    }    
    public EstadoSucursal(){
    	super();
    }
        
    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 3)
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "descripcion", length = 30)
	@Size(max = 30)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
}
