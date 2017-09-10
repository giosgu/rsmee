package action;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import model.EstadoInvitacion;
import model.EstadoUsuario;
import model.Invitacion;
import model.PerfilCampania;
import model.TipoPaciente;
import model.Usuario;
import utils.Activador;
import dao.AfiliadoDao;
import dao.InvitacionDao;
import excepcion.ValidationServiceException;

@Named
public class InvitacionPacienteAction extends AbstractActionBean implements Serializable {
	

/**
	 * 
	 */
	private static final long serialVersionUID = -4749281214576996031L;

@Inject
private InvitacionDao invitacionHome;

@Inject
private AfiliadoDao afiliadoList;

    
    private Invitacion generarInvitacion(Usuario usuarioPaciente){
    	Invitacion invitacion = new Invitacion();
    	invitacion.setEmail(usuarioPaciente.getIdUsuario());
    	invitacion.setApellido(usuarioPaciente.getApellido());
    	invitacion.setNombre(usuarioPaciente.getNombre());
    	invitacion.setPrestador(usuarioPaciente.getPrestador());
    	invitacion.setEstado(EstadoInvitacion.INVITADO);
    	invitacion.setFecha((Date) Calendar.getInstance().getTime());
    	invitacion.setPerfil(PerfilCampania.PERFIL_PACIENTE);
    	invitacion.setCodigoActivacion(Activador.generarCodigo(usuarioPaciente.getIdUsuario()));
    	invitacionHome.persist(invitacion);
    	
    	return invitacion;
    }

    public EstadoUsuario crearInvitacion(Usuario usuarioPaciente) throws ValidationServiceException{
    	Invitacion invitacion = generarInvitacion(usuarioPaciente);    	
    	//Existencia en Afiliados
    	TipoPaciente tipoPaciente = null;
    	try{
    		tipoPaciente = afiliadoList.obtenerTipoPaciente(usuarioPaciente.getNumDocumento(), usuarioPaciente.getNumeroAfiliado());
    	}
    	catch(NoResultException e){
    		return EstadoUsuario.ESTADO_PENDIENTE;
    	}
		if(tipoPaciente==null)
			return EstadoUsuario.ESTADO_PENDIENTE;
		else{
			if(tipoPaciente.getCodigo().compareTo(TipoPaciente.ADULTO.getCodigo())==0){
				this.enviarMailActivacionPaciente(usuarioPaciente, invitacion);
				return EstadoUsuario.ESTADO_PRE_ACEPTADO;
			}else
				throw new ValidationServiceException("usuario.amb.error.afiliado.no.mayor");
		}
		
    }
    
    public EstadoUsuario crearInvitacionEmancipacion(Usuario usuarioPaciente){
    	Invitacion invitacion = generarInvitacion(usuarioPaciente);
    	this.enviarMailActivacionPaciente(usuarioPaciente, invitacion);
    	return EstadoUsuario.ESTADO_EMANCIPANDOSE;    	
    }
    
    public void enviarMailActivacionPaciente(Usuario usuarioPaciente, Invitacion invitacion){
    	//TODO
//        Map<String, Object> parametros = new HashMap<String, Object>();
//        parametros.put("usuarioDestino", usuarioPaciente);
//        String contexto = parametroList.descripcionParametroPorId(Parametros.URL_RSM);
//        parametros.put("contexto", contexto);
//        String urlActivacion = contexto+
//        		"/activacionUsuarioM.seam?idUsuario="+usuarioPaciente.getIdUsuario()+"&"+
//        		"ca="+invitacion.getCodigoActivacion();
//        parametros.put("urlActivacion", urlActivacion);
//        EmailService emailService = (EmailService) Component.getInstance(EmailService.class);        
//        try{
//			emailService.sendEmail("/templates/mailactivacion.xhtml", parametros);
//		}catch(Exception e){
//			FacesMessages.instance().add("generic.error.envio.mail");
//			e.printStackTrace();
//		}
    }
    

}
