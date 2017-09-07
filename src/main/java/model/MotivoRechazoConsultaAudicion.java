package model;

import java.io.Serializable;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="motivo_rechazo_consulta_audicion")
public class MotivoRechazoConsultaAudicion implements Serializable, ComboEntity{
	
	private static final long serialVersionUID = 1L;
	
	public MotivoRechazoConsultaAudicion(Long cod){
		super();
		setCodigo(cod);
	}
	public MotivoRechazoConsultaAudicion(){
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MotivoRechazoConsultaAudicionSeqOra")
	@SequenceGenerator(name="MotivoRechazoConsultaAudicionSeqOra", sequenceName="MOTIVORECHAZOCONSULTAAUDICION_SEQ", allocationSize=1)
	@Column(name="codigo", nullable=false)
	private Long codigo;
	
	@Column(name="descripcion", nullable=false, length=128)
	@Size(max=256)
	private String descripcion;
	
	
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
	
	@Override @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String getLabel() {
		return descripcion;
	}
	@Override @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String getValue() {
		return codigo.toString();
	}
	@Override
	public void setLabel(String label) {}
	@Override
	public void setValue(String label) {}

	
}
