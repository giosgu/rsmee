package model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name = "parametros")
@Named
public class Parametros implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String RAIZ_IMAGEN_PERFIL = "RAIZ_IMAGEN_PERFIL";
	public static final String IMAGEN_PERFIL_DEFAULT_HOMBRE = "IMAGEN_PERFIL_DEFAULT_HOMBRE";
	public static final String IMAGEN_PERFIL_DEFAULT_MUJER = "IMAGEN_PERFIL_DEFAULT_MUJER";	
	public static final String IMAGEN_PERFIL_DEFAULT_NINIO = "IMAGEN_PERFIL_DEFAULT_NINIO";
	public static final String IMAGEN_PERFIL_DEFAULT_NINIA = "IMAGEN_PERFIL_DEFAULT_NINIA";
	public static final String IMAGEN_PERFIL_DEFAULT_BEBE_MASC = "IMAGEN_PERFIL_DEFAULT_BEBE_MASC";
	public static final String IMAGEN_PERFIL_DEFAULT_BEBE_FEM = "IMAGEN_PERFIL_DEFAULT_BEBE_FEM";
	public static final String URL_RSM = "URL_RSM";
	public static final String PREFIJO_DOCTOR = "Dr.";
	public static final String PREFIJO_DOCTORA = "Dra.";
	public static final String RAIZ_PATH_CV = "RAIZ_PATH_CV";
	public static final String RAIZ_RSM_USUARIOS = "RAIZ_RSM_USUARIOS";
	public static final String CARPETA_ADJUNTOS = "CARPETA_ADJUNTOS";
	public static final String LISTA_MAILS_SOPORTE_TECNICO = "LISTA_MAILS_SOPORTE_TECNICO";
	public static final String MAILING_RESULTADO_PROCESAMIENTO_ARCHIVOS = "MAILING_RESULTADO_PROCESAMIENTO_ARCHIVOS";
	public static final String RAIZ_FIRMA_PERFIL = "RAIZ_FIRMA_PERFIL";
	public static final String HABILITACION_MENORES_A_CARGO = "HABILITACION_MENORES_A_CARGO";
	public static final String PIE_RECETA_AUTOMATICA = "PIE_RECETA_AUTOMATICA";
	public static final String IMAGEN_PERFIL_DEFAULT_AUSTRAL_SALUD = "IMAGEN_PERFIL_DEFAULT_AUSTRAL_SALUD";
		
	private String codigo;
	private String descripcion;
	
	@Id
    @Column(name = "codigo", unique = true, nullable = false)
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "descripcion", nullable = false)
	@Size(max = 256)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public static String getUrlRsm() {
		return URL_RSM;
	}  
	
}
