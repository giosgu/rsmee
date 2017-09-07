/**
 * 
 */
package model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 * @author alessiof
 *
 */
@Entity
@Table(name = "perfil_campania")
@Named
public class PerfilCampania implements Serializable, ComboEntity {


	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;
	
	public static final String STR_MEDICO = "MED";
	public static final String STR_PACIENTE = "PAC";
	public static final String STR_TODOS = "TOD";
	public static final String STR_NINGUNO = "NIN";
	
	public static final PerfilCampania PERFIL_MEDICO = new PerfilCampania(STR_MEDICO);
	public static final PerfilCampania PERFIL_PACIENTE = new PerfilCampania(STR_PACIENTE);	
	
	public PerfilCampania(String codigoPerfil){
		super();
		this.codigo = codigoPerfil;
	}
	public PerfilCampania(){
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
	
	public static String getStrMedico() {
		return STR_MEDICO;
	}
	public static String getStrPaciente() {
		return STR_PACIENTE;
	}
	public static String getStrTodos() {
		return STR_TODOS;
	}
	public static String getStrNinguno() {
		return STR_NINGUNO;
	}
	
	@Override 
	public String getLabel() {
		return descripcion;
	}
	@Override
	public String getValue() {
		return codigo.toString();
	}
	@Override
	public void setLabel(String label) {}
	@Override
	public void setValue(String label) {}
	
	
}
