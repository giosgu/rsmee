package rsmee.dao;

import java.util.List;

import javax.inject.Inject;

import model.Mensaje;
import model.MensajeRespuesta;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import rsmee.BaseJPATest;
import dao.MensajeDao;
import dao.MensajeRespuestaDao;

@RunWith(Arquillian.class)
public class MensajeRespuestaTest extends BaseJPATest {
	
	@Inject MensajeRespuestaDao mensajeRespuestaDao;
	@Inject MensajeDao mensajeDao;
	
	@Deployment
    public static WebArchive createDeployment() {
		return createDeployment("mensajeRespuestaTest.war");
	}

	@Before
	public void setup(){
	}

	@Test
	public void cantidadRespuestasMensajeTest() {
		// el mensaje 2096 tiene 12 respuestas1, 
		Assert.assertEquals(12, mensajeRespuestaDao.cantidadRespuestasMensaje( new Long(2096)).intValue());
		//el mensaje '3017' tiene 1
		Assert.assertEquals(1, mensajeRespuestaDao.cantidadRespuestasMensaje(new Long(3017)).intValue() );
		// el mensaje 2 no tiene respuestas 
		Assert.assertEquals(0, mensajeRespuestaDao.cantidadRespuestasMensaje( new Long(2)).intValue());
	}
	
	@Test
	public void getMensajesRespuestaTest(){
		Mensaje mensaje = (Mensaje)mensajeDao.getObjectByID(new Long(2096));
		List<MensajeRespuesta> mensajesRespuestas = mensajeRespuestaDao.getMensajesRespuesta(mensaje);
		// el mensaje 2096 tiene 12 respuestas1, 
		Assert.assertEquals(12, mensajesRespuestas.size());
		// la primera es la respuesta codigo 3144
		Assert.assertEquals(3144, mensajesRespuestas.get(0).getCodigo().intValue());
		//la última es la respuesta codigo 3325
		Assert.assertEquals(3325, mensajesRespuestas.get(11).getCodigo().intValue());
				
	}

}
