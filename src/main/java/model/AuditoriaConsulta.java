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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="auditoria_consulta")
public class AuditoriaConsulta implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="AuditoriaConsultaSeqOra")
    @SequenceGenerator(name="AuditoriaConsultaSeqOra",sequenceName="AUDITORIA_CONSULTA_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="auditor", referencedColumnName="codigo", nullable=false)
	private Usuario auditor;
	
	@Column(name="fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_estado", referencedColumnName="codigo", nullable=false)
	private EstadoAuditoriaConsulta estadoAuditoriaConsulta;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="motivo_rechazo", referencedColumnName="codigo", nullable=true)
	private MotivoRechazoConsultaAudicion motivoRechazoConsultaAudicion;
	
	@Column(name="comentario_auditor", nullable=true)
	@Lob
	private String comentarioAuditor;

	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Usuario getAuditor() {
		return auditor;
	}
	public void setAuditor(Usuario auditor) {
		this.auditor = auditor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public EstadoAuditoriaConsulta getEstadoAuditoriaConsulta() {
		return estadoAuditoriaConsulta;
	}
	public void setEstadoAuditoriaConsulta(EstadoAuditoriaConsulta estadoAuditoriaConsulta) {
		this.estadoAuditoriaConsulta = estadoAuditoriaConsulta;
	}
	public MotivoRechazoConsultaAudicion getMotivoRechazoConsultaAudicion() {
		return motivoRechazoConsultaAudicion;
	}
	public void setMotivoRechazoConsultaAudicion(MotivoRechazoConsultaAudicion motivoRechazoConsultaAudicion) {
		this.motivoRechazoConsultaAudicion = motivoRechazoConsultaAudicion;
	}
	public String getComentarioAuditor() {
		return comentarioAuditor;
	}
	public void setComentarioAuditor(String comentarioAuditor) {
		this.comentarioAuditor = comentarioAuditor;
	}

}