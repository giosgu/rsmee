package rsmee.dao;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import rsmee.BaseJPATest;
import dao.MensajeDao;

@RunWith(Arquillian.class)
public class MensajeTest extends BaseJPATest {
	
	@Inject MensajeDao mensajeDao;
	
	@Deployment
    public static WebArchive createDeployment() {
		return createDeployment("mensajeTest.war");
	}

	@Before
	public void setup(){
	}

	@Test
	public void getMensajesTest(){
		// el usuario 27 tiene 270 mensajes
		Assert.assertEquals(mensajeDao.getMensajes(new Long(27)).size(), 270);

	}

}
