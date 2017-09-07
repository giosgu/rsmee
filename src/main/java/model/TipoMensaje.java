package model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Named
@Entity
@Table(name = "tipo_mensaje")
public class TipoMensaje  implements Serializable {
	
	public static String STR_MENSAJE = "MSJ";
	public static String STR_CAMPANIA = "CAM";
	
	public static void setSTR_MENSAJE(String sTRMENSAJE) {
		STR_MENSAJE = sTRMENSAJE;
	}

	public static void setSTR_CAMPANIA(String sTRCAMPANIA) {
		STR_CAMPANIA = sTRCAMPANIA;
	}

	public TipoMensaje(){
		super();
	}
	
	public TipoMensaje(String codigo){
		super();
		this.codigo = codigo;		
	}
	
	@Id
    @Column(name = "codigo", unique = true, nullable = false)
    @Size(max = 3)
	private String codigo;
	
	@Column(name = "descripcion", length = 60)
	private String descripcion;


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

	public static String getSTR_MENSAJE() {
		return STR_MENSAJE;
	}
	public static String getSTR_CAMPANIA() {
		return STR_CAMPANIA;
	}


}
