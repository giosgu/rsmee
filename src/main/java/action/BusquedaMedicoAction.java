package action;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Especialidad;
import model.Usuario;
import dao.EspecialidadDao;
import dao.UsuarioDao;

@Named
@Stateful
@SessionScoped
public class BusquedaMedicoAction extends AbstractActionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1791916928669712570L;
	private String apellidoMedico;
	private Long especialidadSeleccionada;
	@Inject EspecialidadDao especialidadDao;
	private Usuario medicoSeleccionado;
	@Inject UsuarioDao usuarioDao;
	
	
	public void init(){		
	}
	
	
	public String getApellidoMedico() {
		return apellidoMedico;
	}

	public void setApellidoMedico(String apellidoMedico) {
		this.apellidoMedico = apellidoMedico;
	}

	public Long getEspecialidadSeleccionada() {
		return especialidadSeleccionada;
	}

	public void setEspecialidadSeleccionada(Long especialidadSeleccionada) {
		this.especialidadSeleccionada = especialidadSeleccionada;
	}

	public EspecialidadDao getEspecialidadDao() {
		return especialidadDao;
	}

	public void setEspecialidadDao(EspecialidadDao especialidadDao) {
		this.especialidadDao = especialidadDao;
	}


	public List<Especialidad> getEspecialidades() {
		return especialidadDao.findAll();
	}

	public Usuario getMedicoSeleccionado() {
		return medicoSeleccionado;
	}

	public void setMedicoSeleccionado(Usuario medicoSeleccionado) {
		this.medicoSeleccionado = medicoSeleccionado;
	}

	public List<Usuario> getMedicos() {
		return usuarioDao.obtenerMedicos();
	}

	
}
