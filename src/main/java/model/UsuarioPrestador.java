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


@Entity
@Table(name = "usuario_prestador")
public class UsuarioPrestador implements Serializable{
	
	private Long codigo;
	private Usuario codigoUsuario;
	private Prestador codigoPrestador;
	private Date fechaVigenciaDesde;
	private Date fechaVigenciaHasta;
	private Long primario;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="UsuarioPrestadorSeqOra")
	@SequenceGenerator(name="UsuarioPrestadorSeqOra",sequenceName="USUARIO_PRESTADOR_SEQ", allocationSize=1)
	@Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_usuario", referencedColumnName="codigo")
	public Usuario getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(Usuario codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_prestador", referencedColumnName="codigo")
	public Prestador getCodigoPrestador() {
		return codigoPrestador;
	}
	public void setCodigoPrestador(Prestador codigoPrestador) {
		this.codigoPrestador = codigoPrestador;
	}
	
	@Column(name = "fecha_vigencia_desde", nullable = false)
	public Date getFechaVigenciaDesde() {
		return fechaVigenciaDesde;
	}
	public void setFechaVigenciaDesde(Date fechaVigenciaDesde) {
		this.fechaVigenciaDesde = fechaVigenciaDesde;
	}
	
	@Column(name = "fecha_vigencia_hasta", nullable = false)
	public Date getFechaVigenciaHasta() {
		return fechaVigenciaHasta;
	}
	public void setFechaVigenciaHasta(Date fechaVigenciaHasta) {
		this.fechaVigenciaHasta = fechaVigenciaHasta;
	}
	
	
	@Column(name = "primario")
	public Long getPrimario() {
		return primario;
	}
	public void setPrimario(Long primario) {
		this.primario = primario;
	}

}
