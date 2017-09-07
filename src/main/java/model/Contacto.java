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

@Entity
@Table(name = "contacto")
public class Contacto implements Serializable{
	
	private Long codigo;
	private Usuario origen;
	private Usuario destino;
	private Relacion relacion;
	private Date fechaContacto;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ContactoSeqOra")
    @SequenceGenerator(name="ContactoSeqOra",sequenceName="CONTACTO_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_relacion", referencedColumnName="codigo")
	public Relacion getRelacion() {
		return relacion;
	}
	public void setRelacion(Relacion relacion) {
		this.relacion = relacion;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="destino", referencedColumnName="codigo")
	public Usuario getDestino() {
		return destino;
	}
	public void setDestino(Usuario destino) {
		this.destino = destino;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="origen", referencedColumnName="codigo")
	public Usuario getOrigen() {
		return origen;
	}
	public void setOrigen(Usuario origen) {
		this.origen = origen;
	}
	
	@Column(name="fecha_contacto")
	@Temporal(TemporalType.DATE)
	public Date getFechaContacto() {
		return fechaContacto;
	}
	public void setFechaContacto(Date fechaContacto) {
		this.fechaContacto = fechaContacto;
	}

}
