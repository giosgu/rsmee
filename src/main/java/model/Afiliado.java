package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Afiliado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8027098420964424187L;


	public Afiliado(){
		super();
	}

	public Afiliado(String[] object, ArchivoDetalle archivoDetalle){
		super();
		numeroAfiliado = object[0];
		tipoDocumento = new TipoDocumentoConversion(object[1]);
		numeroDocumento = object[2];
		this.archivoDetalle = archivoDetalle;
		tipoPaciente = new TipoPaciente(object[3]);
	}

	
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="AfiliadoSeq")
    @SequenceGenerator(name="AfiliadoSeq",sequenceName="AFILIADO_SEQ", allocationSize=1)
    @Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_archivo_detalle", referencedColumnName="codigo", nullable=false)
    private ArchivoDetalle archivoDetalle;
    
	@Column(name = "numero_afiliado", nullable=false)
	private String numeroAfiliado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_tipo_documento", referencedColumnName="codigo", nullable=false)
    private TipoDocumentoConversion tipoDocumento;
        
	@Column(name = "numero_documento", nullable=false)
    private String numeroDocumento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_paciente", referencedColumnName="codigo", nullable=false)
	private TipoPaciente tipoPaciente;


	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public ArchivoDetalle getArchivoDetalle() {
		return archivoDetalle;
	}


	public void setArchivoDetalle(ArchivoDetalle archivoDetalle) {
		this.archivoDetalle = archivoDetalle;
	}


	public String getNumeroAfiliado() {
		return numeroAfiliado;
	}


	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}


	public TipoDocumentoConversion getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(TipoDocumentoConversion tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public String getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public TipoPaciente getTipoPaciente() {
		return tipoPaciente;
	}

	public void setTipoPaciente(TipoPaciente tipoPaciente) {
		this.tipoPaciente = tipoPaciente;
	}
	
	
}
