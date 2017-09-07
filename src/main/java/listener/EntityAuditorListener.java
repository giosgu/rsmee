/*
 *******************************************************************************
 **  Archivo    : EntityAuditorListener.java
 **  Paquete    : com.octomind.rsm.entity
 **  Proyecto   : rsm
 **  Descripcion: (colocar segun proyecto)
 **  Version    : (colocar segun versi�n)
 **  Autor      : mazzca
 **  Fecha      : 18/01/2012 14:36:55
 *******************************************************************************
 **  Modificaciones/Correcciones
 **               Fecha       Responsable     Comentario
 **
 *******************************************************************************
 */
package listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import model.CreateAuditoryDataEntity;
import model.FullAuditoryDataEntity;
import security.UserDataLogin;

/**
 * @author mazzca
 *
 */
//FIXME rehacer clase
public class EntityAuditorListener {

	@PrePersist
	void onCreate(Object entity) {
		try{
			UserDataLogin identity =  null; //((UserDataLogin) org.jboss.seam.Component.getInstance("org.jboss.seam.security.identity", ScopeType.SESSION));
			
	        if (entity instanceof FullAuditoryDataEntity){
	        	FullAuditoryDataEntity fade = (FullAuditoryDataEntity) entity;
	        	fade.setFechaCreacion(new Date());
	        	if (fade.getUsuarioCreacion()==null && identity!=null && identity.getLogUser()!=null)
	        		fade.setUsuarioCreacion(identity.getLogUser());
	        	fade.setFechaModificacion(null);
	        	fade.setUsuarioModificacion(null);
	        }else if (entity instanceof CreateAuditoryDataEntity){
	         	CreateAuditoryDataEntity cade = (CreateAuditoryDataEntity) entity;
	        	cade.setFechaCreacion(new Date());
	        	if (cade.getUsuarioCreacion()==null && identity!=null && identity.getLogUser()!=null)
	        		cade.setUsuarioCreacion(identity.getLogUser());
	        }
		}catch (Exception e) {
			System.out.println("EntityAuditorListener @PrePersist Fallo: "+ e.getMessage());
		}
	}

	@PreUpdate
	void onPersist(Object entity) {
		try{
			UserDataLogin identity = null; //((UserDataLogin) org.jboss.seam.Component.getInstance("org.jboss.seam.security.identity", ScopeType.SESSION));
			
	        if (entity instanceof FullAuditoryDataEntity){
	        	FullAuditoryDataEntity fade = (FullAuditoryDataEntity) entity;
	        	fade.setFechaModificacion(new Date());
	        	if (fade.getUsuarioModificacion()==null && identity!=null && identity.getLogUser()!=null)
	        		fade.setUsuarioModificacion(identity.getLogUser());
	        }
		}catch (Exception e) {
			System.out.println("EntityAuditorListener @PreUpdate Fallo: "+ e.getMessage());
		}
	}
}
