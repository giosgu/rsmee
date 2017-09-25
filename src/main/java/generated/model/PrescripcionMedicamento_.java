package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.ArchivoAdjunto;
import model.Diagnostico;
import model.EstadoPrescripcion;
import model.Medicamento;
import model.Notificacion;
import model.PrescripcionMedicamento;
import model.Sucursal;
import model.TipoReceta;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PrescripcionMedicamento.class)
public abstract class PrescripcionMedicamento_ {

	public static volatile SingularAttribute<PrescripcionMedicamento, Sucursal> sucursal;
	public static volatile SingularAttribute<PrescripcionMedicamento, Date> fechaEntregado;
	public static volatile SingularAttribute<PrescripcionMedicamento, Date> fechaAceptadaFarmacia;
	public static volatile SingularAttribute<PrescripcionMedicamento, Notificacion> notificacionSolicitud;
	public static volatile SingularAttribute<PrescripcionMedicamento, Date> fechaRechazadaFarmacia;
	public static volatile SingularAttribute<PrescripcionMedicamento, ArchivoAdjunto> archivoAdjunto;
	public static volatile SingularAttribute<PrescripcionMedicamento, Usuario> usuarioDestino;
	public static volatile SingularAttribute<PrescripcionMedicamento, Boolean> ttProlongado;
	public static volatile SingularAttribute<PrescripcionMedicamento, String> mensaje;
	public static volatile SingularAttribute<PrescripcionMedicamento, Date> fechaAnulada;
	public static volatile SingularAttribute<PrescripcionMedicamento, String> respuestaMensaje;
	public static volatile SingularAttribute<PrescripcionMedicamento, Date> fechaSolicitud;
	public static volatile SingularAttribute<PrescripcionMedicamento, EstadoPrescripcion> estadoPrescripcion;
	public static volatile SingularAttribute<PrescripcionMedicamento, Long> codigo;
	public static volatile SingularAttribute<PrescripcionMedicamento, String> telefonoPaciente;
	public static volatile SingularAttribute<PrescripcionMedicamento, Medicamento> medicamento;
	public static volatile SingularAttribute<PrescripcionMedicamento, Date> fechaPrescripcion;
	public static volatile SingularAttribute<PrescripcionMedicamento, String> respuestaFarmaciaMensaje;
	public static volatile SingularAttribute<PrescripcionMedicamento, TipoReceta> tipoReceta;
	public static volatile SingularAttribute<PrescripcionMedicamento, Usuario> usuarioOrigen;
	public static volatile SingularAttribute<PrescripcionMedicamento, String> cantidad;
	public static volatile SingularAttribute<PrescripcionMedicamento, Date> fechaRechazadaMedico;
	public static volatile SingularAttribute<PrescripcionMedicamento, Diagnostico> diagnostico;
	public static volatile SingularAttribute<PrescripcionMedicamento, String> comentarioAnulada;

}

