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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "relacion")
public class Relacion implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 4123296005062205150L;
	private Long codigo;
	private Usuario origenUsuario;
	private Usuario destinoUsuario;
	private EstadoRelacion estadoRelacion;
	private String motivoDesasignacion;
	private Notificacion notificacion;
	private Date fechaCreacion;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="RelacionSeqOra")
    @SequenceGenerator(name="RelacionSeqOra",sequenceName="RELACION_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="usuario_origen", referencedColumnName="codigo")
	public Usuario getOrigenUsuario() {
		return origenUsuario;
	}
	public void setOrigenUsuario(Usuario origenUsuario) {
		this.origenUsuario = origenUsuario;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_destino", referencedColumnName="codigo")
	public Usuario getDestinoUsuario() {
		return destinoUsuario;
	}
	public void setDestinoUsuario(Usuario destinoUsuario) {
		this.destinoUsuario = destinoUsuario;
	}
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="estado_relacion", referencedColumnName="codigo")
	public EstadoRelacion getEstadoRelacion() {
		return estadoRelacion;
	}
	public void setEstadoRelacion(EstadoRelacion estadoRelacion) {
		this.estadoRelacion = estadoRelacion;
	}
	
	@Column(name="motivo_desasignacion", length=256)
	public String getMotivoDesasignacion() {
		return motivoDesasignacion;
	}
	public void setMotivoDesasignacion(String motivoDesasignacion) {
		this.motivoDesasignacion = motivoDesasignacion;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_notificacion", referencedColumnName="codigo")
	public Notificacion getNotificacion() {
		return notificacion;
	}
	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}
	
	@Column(name="fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	  @Override
	    public int hashCode() {
	        HashCodeBuilder hcb = new HashCodeBuilder();
	        hcb.append(codigo);
	        return hcb.toHashCode();
	    }
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Relacion)) {
            return false;
        }
        Relacion that = (Relacion) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(codigo, that.codigo);
        return eb.isEquals();
    }
}
