package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ayuda_soporte")
public class Ayuda implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4285031116594309418L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="AyudaSoporteSeqOra")
	@SequenceGenerator(name="AyudaSoporteSeqOra",sequenceName="AYUDA_SOPORTE_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario", referencedColumnName="codigo")
    private Usuario usuario;
    	
	@Column(name = "nombre")
	@Size(max = 30)
    private String nombre;
    
	@Column(name = "apellido")
	@Size(max = 30)
    private String apellido;
    
	@Column(name = "texto_consulta", nullable=false, columnDefinition="TEXT")
    private String textoConsulta;
    
	@Column(name = "mail_contacto")
	@Size(max = 50)
    private String mailContacto;       
	
	@Column(name="fecha")
	@Temporal(TemporalType.DATE) 
	private Date fecha;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }    
    public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTextoConsulta() {
		return textoConsulta;
	}
	public void setTextoConsulta(String textoConsulta) {
		this.textoConsulta = textoConsulta;
	}
	public String getMailContacto() {
		return mailContacto;
	}
	public void setMailContacto(String mailContacto) {
		this.mailContacto = mailContacto;
	}    
    public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}	

}
