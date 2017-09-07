package model;


import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Named
@Table(name = "calificacion")
public class Calificacion implements Serializable {
	
	public static String RESPUESTA_SATISFACTORIA = "SAT";
	
	public Calificacion(){
		super();
	}
	
	public Calificacion(String codigo){
		super();
		this.codigo=codigo;
	}
	
    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 3)
	private String codigo;
    
    @Column(name = "descripcion", length = 50)
    private String descripcion;
    
    @Column(name = "path_imagen", length = 100)
    private String pathImagen;
    
    @Column(name = "alt", length = 100)
    private String alt;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPathImagen() {
		return pathImagen;
	}

	public void setPathImagen(String pathImagen) {
		this.pathImagen = pathImagen;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

}
