package model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="criterio_tipo")
@Named
public class CriterioTipo implements Serializable{
	
	public static CriterioTipo PERFIL = new CriterioTipo(new Long(1));
	public static CriterioTipo PATOLOGIA = new CriterioTipo(new Long(2));
	public static CriterioTipo ESPECIALIDADES = new CriterioTipo(new Long(3));
	public static CriterioTipo GENERO = new CriterioTipo(new Long(4));
	public static CriterioTipo EDAD_DESDE = new CriterioTipo(new Long(5));
	public static CriterioTipo EDAD_HASTA = new CriterioTipo(new Long(6));
	public static CriterioTipo USUARIO = new CriterioTipo(new Long(7));
	
	private static String STR_PERFIL = "1";
	private static String STR_PATOLOGIA = "2";
	private static String STR_ESPECIALIDADES = "3";
	private static String STR_GENERO = "4";
	private static String STR_EDAD_DESDE = "5";
	private static String STR_EDAD_HASTA = "6";
	private static String STR_USUARIO = "7";
	
    private Long codigo;
    private String descripcion;
    
    public CriterioTipo(){
    	super();
    }

    public CriterioTipo(Long codigo ){
    	super();
    	this.codigo = codigo; 
    }

    @Id
    @SequenceGenerator(name="CriterioTipoSeqOra",sequenceName="CRITERIOTIPO_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return this.codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
		
	@Column(name = "descripcion")
	@Size(max = 256)
	@NotNull
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public static String getSTR_PATOLOGIA() {
		return STR_PATOLOGIA;
	}
	public static String getSTR_ESPECIALIDADES() {
		return STR_ESPECIALIDADES;
	}
	public static String getSTR_GENERO() {
		return STR_GENERO;
	}
	public static String getSTR_EDAD_DESDE() {
		return STR_EDAD_DESDE;
	}
	public static String getSTR_EDAD_HASTA() {
		return STR_EDAD_HASTA;
	}
	public static String getSTR_USUARIO() {
		return STR_USUARIO;
	}
	public static void setSTR_PERFIL(String sTRPERFIL) {
		STR_PERFIL = sTRPERFIL;
	}
	public static String getSTR_PERFIL() {
		return STR_PERFIL;
	}

}
