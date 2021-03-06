package decorator;

import model.Usuario;

public class UsuarioDecorator {
	
	private Usuario usuario;
	
	public UsuarioDecorator(Usuario usuario){
		this.usuario = usuario;
	}
	
	public Boolean isMedico(){
		return  usuario.getProfesional() != null; 
	}
	
	public Boolean isPaciente(){
		return usuario.getPaciente() != null;
	}
	
}
