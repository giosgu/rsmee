package dao;

import model.Especialidad;

public class EspecialidadDao extends BaseDao<Especialidad, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6096657882326654458L;

	public EspecialidadDao(){
    	this.entityType = Especialidad.class;
    	this.idType = Long.class;

	}
	
}
