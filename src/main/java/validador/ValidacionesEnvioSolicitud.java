package validador;

import javax.inject.Inject;

import model.Usuario;
import dao.RelacionDao;
import excepcion.ValidationServiceException;

public class ValidacionesEnvioSolicitud  implements Validador {

	private Usuario usuarioOrigen;
	private Usuario usuarioDestino;
	@Inject RelacionDao relacionList;
	
	public void validate() throws ValidationServiceException {
		//Si es paciente, que no tenga un médico con esa especialidad
/*		if(usuarioDestino.getProfesional()!=null && usuarioOrigen.getPaciente()!=null){			
			List<Especialidad> especialidadesDestino = usuarioDestino.getProfesional().getEspecialidades();
			
			List<Long> codigoEspecialidades = new ArrayList<Long>();
			for (Especialidad especialidad : especialidadesDestino)
				codigoEspecialidades.add(especialidad.getCodigo());
			if(usuarioOrigen.getPaciente()!=null){
				TipoPacienteEspecialidadList tipoPacienteEspecialidadList = (TipoPacienteEspecialidadList) Component.getInstance(TipoPacienteEspecialidadList.class);			
				if(tipoPacienteEspecialidadList.getTipoPacienteEspecialidad(usuarioOrigen.getPaciente().getTipoPaciente().getCodigo(), 
						codigoEspecialidades) == null){
					throw new ValidationServiceException("No es posible agregar a " +usuarioDestino.getNombre()+ " " +usuarioDestino.getApellido()+
							" a sus contactos, dado que no coincide el tipo de paciente con la especialidad del profesional.");					
				}
			}

			ContactoList contactoList = (ContactoList) Component.getInstance(ContactoList.class);
			for (Especialidad especialidad : especialidadesDestino) {
				if(contactoList.tieneUnMedicoConEspecialidad(usuarioOrigen.getCodigo(), especialidad.getCodigo()))
					throw new ValidationServiceException("No es posible agregar a " +usuarioDestino.getNombre()+ " " +usuarioDestino.getApellido()+
							" a sus contactos, dado que sólo puede consultar a un Médico por Especialidad.");
			}
			
			for(Especialidad especialidad: especialidadesDestino){
				if(relacionList.tieneSolicitudParaMedicoConEspecialidad(usuarioOrigen.getCodigo(), especialidad.getCodigo())){
					throw new ValidationServiceException("No es posible agregar a " +usuarioDestino.getNombre()+ " " +usuarioDestino.getApellido()+
							" a sus contactos, dado que tiene una solicitud enviada a un Médico con la misma Especialidad.");
				}
			}

		}
*/	}

	public Usuario getUsuarioOrigen() {
		return usuarioOrigen;
	}
	public void setUsuarioOrigen(Usuario usuarioOrigen) {
		this.usuarioOrigen = usuarioOrigen;
	}
	public Usuario getUsuarioDestino() {
		return usuarioDestino;
	}
	public void setUsuarioDestino(Usuario usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}

}
