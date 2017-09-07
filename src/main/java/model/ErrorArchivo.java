package model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Named
@Table(name = "error_archivo")
public class ErrorArchivo implements Serializable {
	
	//Terminales
	public static Long ERROR_FORMATO_NOMBRE_ARCHIVO = new Long(101);
	public static Long ERROR_PRESTADOR_NOMBRE_ARCHIVO = new Long(102);
	public static Long ERROR_GUIONES_NOMBRE_ARCHIVO = new Long(103);
	public static Long ERROR_TIPO_NOMBRE_ARCHIVO = new Long(104);
	public static Long ERROR_FORMATO_FECHA_NOMBRE_ARCHIVO = new Long(105);
	public static Long ERROR_FECHA_SUPERIOR_NOMBRE_ARCHIVO = new Long(106);
	public static Long ERROR_EXTENSION_NOMBRE_ARCHIVO = new Long(107);
	public static Long ERROR_CONTENIDO_VACIO = new Long(108);
	public static Long ERROR_NUMERO_AFILIADO_VACIO = new Long(109);
	public static Long ERROR_NUMERO_AFILIADO_INCORRECTO = new Long(110);
	public static Long ERROR_TIPO_DOCUMENTO_VACIO = new Long(111);
	public static Long ERROR_TIPO_DOCUMENTO_INVALIDO = new Long(112);
	public static Long ERROR_NUMERO_DOCUMENTO_VACIO = new Long(113);
	public static Long ERROR_NUMERO_DOCUMENTO_INVALIDO = new Long(114);
	public static Long ERROR_FORMATO_REGISTRO_INVALIDO = new Long(115);
	public static Long ERROR_TIPO_PACIENTE_VACIO = new Long(116);
	public static Long ERROR_TIPO_PACIENTE_INVALIDO = new Long(117);	
	public ErrorArchivo(){
		super();
	}
	
	public ErrorArchivo(Long codigo){
		super();
		this.codigo = codigo;
	}
	
	@Id
    @Column(name = "codigo")
	private Long codigo;

    private String descripcion;

    @Column(length=1)
    private String severidad;
    
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSeveridad() {
		return severidad;
	}

	public void setSeveridad(String severidad) {
		this.severidad = severidad;
	}

}
