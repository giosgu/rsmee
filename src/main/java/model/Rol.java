package model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Rol implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1425849537297318761L;

	public static Rol ROL_PACIENTE = new Rol(new Long(2));
	
	public static Long MEDICO = new Long(1);
	public static Long PACIENTE = new Long(2);
	public static Long ADMINISTRADOR_PRESTADOR = new Long(3);
	
	public static final String DESCRIP_AUDITOR = "AUDITOR";
	
	public Rol(Long codigo){
		super();
		this.codigo = codigo;
	}

	public Rol(){
		super();
	}
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="RolSeqOra")
    @SequenceGenerator(name="RolSeqOra",sequenceName="ROL_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
    @Id
	private Long codigo;
	
	//@Ro
	private String descripcion;
	
	//@RoleConditional
	private boolean condicional;
	
	//@RoleGroups
	  @ManyToMany(targetEntity = Rol.class, fetch=FetchType.EAGER)
	  @JoinTable(name = "Rol_Grupos", 
	    joinColumns = @JoinColumn(name = "codigo_grupo"),
	    inverseJoinColumns = @JoinColumn(name = "codigo_rol"))
	private Set<Rol> roles;
	
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
	public boolean isCondicional() {
		return condicional;
	}
	public void setCondicional(boolean condicional) {
		this.condicional = condicional;
	}
	public Set<Rol> getRoles() {
		return roles;
	}
	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	
}
