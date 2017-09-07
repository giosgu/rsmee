package dao;

import javax.faces.bean.ViewScoped;

import model.OlvidoClave;

@ViewScoped
public class OlvidoClaveDao extends BaseDao<OlvidoClave, Long>  {
	
	public OlvidoClaveDao(){
    	this.entityType = OlvidoClave.class;
    	this.idType = Long.class;
    	
    }

}
