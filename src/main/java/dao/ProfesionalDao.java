package dao;

import model.Profesional;

public class ProfesionalDao extends BaseDao<Profesional, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7679118277551367203L;

	public ProfesionalDao(){
    	this.entityType = Profesional.class;
    	this.idType = Long.class;

	}

}

