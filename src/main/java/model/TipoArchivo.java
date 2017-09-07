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
@Table(name = "tipo_archivo")
public class TipoArchivo implements ComboEntity, Serializable {


	@Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 3)
	private String codigo;
	
	private String descripcion;
	
	@Column(name = "validador_formato_general")
	private String validadorFormatoGeneral;
	
	@Column(name = "validador_contenido_archivo")
	private String validadorContenidoArchivo;

	@Column(name = "procesador_acciones")
	private String procesadorAcciones;
	
	@Column(name = "generador_respuesta")
	private String generadorRespuesta;
	
	@Column(name = "identificador")
	private String identificador;
	
	
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

	public String getValidadorContenidoArchivo() {
		return validadorContenidoArchivo;
	}

	public void setValidadorContenidoArchivo(String validadorContenidoArchivo) {
		this.validadorContenidoArchivo = validadorContenidoArchivo;
	}

	public String getValidadorFormatoGeneral() {
		return validadorFormatoGeneral;
	}

	public void setValidadorFormatoGeneral(String validadorFormatoGeneral) {
		this.validadorFormatoGeneral = validadorFormatoGeneral;
	}

	public String getGeneradorRespuesta() {
		return generadorRespuesta;
	}

	public void setGeneradorRespuesta(String generadorRespuesta) {
		this.generadorRespuesta = generadorRespuesta;
	}
	
	//Para Cargar Combos	
	public void setLabel(String label) {}
	public String getLabel() {
		return descripcion;
	}	
	public void setValue(String value) {}
	public String getValue() {
		return codigo.toString();
	}

	public String getProcesadorAcciones() {
		return procesadorAcciones;
	}

	public void setProcesadorAcciones(String procesadorAcciones) {
		this.procesadorAcciones = procesadorAcciones;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	
}
