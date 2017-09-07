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
@Table(name = "tipo_notificacion")
public class TipoNotificacion implements Serializable {

	public static String TN_SOLICITUD = "SOL";
	public static String TN_RESPUESTA_SOLICITUD = "RSO";
	public static String TN_MENSAJE = "MSJ";
	public static String TN_RESPUESTA_MSJ = "RMS";
	public static String TN_SOLICITUD_MEDICAMENTO = "MED";
	public static String TN_RESPUESTA_SOLICITUD_MEDICAMENTO = "RME";
	public static String TN_SOLICITUD_MEDICAMENTO_SUCURSAL = "SUC";
	public static String TN_RESPUESTA_PRESCRIPCION_SUCURSAL_ERROR_RECETA = "PRE";
	public static String TN_RESPUESTA_ACEPTADA_SUCURSAL = "ASU";
	public static String TN_CAMPANIA = "CAM";
	public static String TN_CAMPANIA_PENDIENTE = "CPE";
	public static String TN_CAMPANIA_ACEPTADA = "COK";
	public static String TN_CAMPANIA_RECHAZADA = "CRC";
	public static String TN_PACIENTE_PENDIENTE = "PPE";
	public static String TN_PACIENTE_NUEVO_ACEPTADO = "PNA";
	public static String TN_PROFESIONAL_LICENCIA = "LIC";
	public static String TN_PROFESIONAL_FIN_LICENCIA = "FLI";
	public static String TN_PROFESIONAL_SUPLENCIA = "SUP";
	public static String TN_PROFESIONAL_FIN_SUPLENCIA = "FSU";
	
	public static TipoNotificacion SOLICITUD = new TipoNotificacion(TN_SOLICITUD);
	
	public TipoNotificacion(){
		super();
	}

	public TipoNotificacion(String codigo){
		super();
		this.codigo = codigo;
	}

	
	@Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 3)
	private String codigo;
	
	@Column(name = "descripcion", length = 60)
	private String descripcion;
	
	@Column(name = "mail_path_template", length = 256)
	private String mailPathTemplate;

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

	public String getMailPathTemplate() {
		return mailPathTemplate;
	}
	public void setMailPathTemplate(String mailPathTemplate) {
		this.mailPathTemplate = mailPathTemplate;
	}
	
}
