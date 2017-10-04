package rsmee;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import model.TipoMensaje;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TipoMensajeTest {


	@Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "tipoMensajeTest.war")
            .addClass(TipoMensaje.class)
            .addAsResource("persistence-test.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsWebInfResource("rsmtst2-ds.xml");
    }

	
	 @Test
	    public void cantidadTipoMensaje() throws Exception {
	        // given
	        String obtenerTipoMensajeQ = "select tm from TipoMensaje tm";

	        // when
	        System.out.println("Obteniendo Tipos de mensaje...");
	        List<TipoMensaje> tipoMensaje = em.createQuery(obtenerTipoMensajeQ, TipoMensaje.class).getResultList();
	        System.out.println("Se encontraron " + tipoMensaje.size() + " tipos de mensaje");
	        Assert.assertEquals(tipoMensaje.size(), 2);
	    }
	 
	    @PersistenceContext
	    EntityManager em;
	    @Inject
	    UserTransaction utx;

	    @Before
	    public void preparePersistenceTest() throws Exception {
	        startTransaction();
	    }
	    
	    private void startTransaction() throws Exception {
	        utx.begin();
	        em.joinTransaction();
	    }
	    
	    @After
	    public void commitTransaction() throws Exception {
	        utx.commit();
	    }

}
