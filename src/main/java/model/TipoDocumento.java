package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "tipo_documento")
public class TipoDocumento extends CreateAuditoryDataEntity implements ComboEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private String descripcion;
	
	public TipoDocumento(){
		super();
	}
	
	public TipoDocumento(Long codigo){
		this.codigo = codigo;
	}
	
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TipoDocumentoSeqOra")
    @SequenceGenerator(name="TipoDocumentoSeqOra",sequenceName="TIPO_DOCUMENTO_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	//Para Cargar Combos
	
	public String getLabel() {
		return descripcion;
	}
	
	public String getValue() {
		return codigo.toString();
	}
	
	public void setLabel(String label) {
	}
	
	
	public void setValue(String value) {
	}
	
	
    
}
