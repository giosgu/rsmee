package rsmee.dao;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import model.Relacion;
import model.Usuario;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import rsmee.BaseJPATest;
import dao.RelacionDao;

@RunWith(Arquillian.class)
public class RelacionDaoTest extends BaseJPATest {

	@Inject
	RelacionDao relacionDao;
	
	@Deployment
    public static WebArchive createDeployment() {
		File[] libs = Maven.resolver()  
			    .loadPomFromFile(POM).resolve(COMMONS_LANG_3)  
			    .withTransitivity().asFile();   
		return createDeployment("relacionDaoTest.war")
				.addAsLibraries(libs);
	}

	
	@Test
	public void obtenerRelacionTest() {
		Relacion relacion = relacionDao.obtenerRelacion(new Long(4), new Long(14));
		Assert.assertNotNull(relacion);
	}

	@Test
	public void envieRelacionPendienteTest() {
		Assert.assertTrue(relacionDao.envieSolicitudPendiente(new Long(714), new Long(1580)));
		Assert.assertFalse(relacionDao.envieSolicitudPendiente(new Long(1), new Long(2)));
	}

	@Test //consulta por relaciones en estado ACP entre dos usuarios
	public void tieneRelacionAceptadaTest() {
		Assert.assertTrue(relacionDao.tienenRelacionAceptada(new Long(2), new Long(4)));
		Assert.assertTrue(relacionDao.tienenRelacionAceptada(new Long(4), new Long(2)));
		Assert.assertFalse(relacionDao.envieSolicitudPendiente(new Long(4), new Long(5)));
		Assert.assertFalse(relacionDao.envieSolicitudPendiente(new Long(5), new Long(4)));
		Assert.assertFalse(relacionDao.envieSolicitudPendiente(new Long(1), new Long(2)));
	}

	@Test //el usuario 714 tiene 26 relaciones en estado SOL
	public void obtenerRelacionesDestinoTest() {
		List<Relacion> relaciones = relacionDao.obtenerRelacionesDestino(new Long(714));
		Relacion relacion = new Relacion();
		relacion.setCodigo(new Long(448));
		Assert.assertEquals(relaciones.size(), 26);
		Assert.assertEquals(relacionDao.getListaSolicitudesPendientes().size(), 26);
		Assert.assertTrue(relaciones.contains(relacion));
	}
	
	@Test 
	public void obtenerRelacionesPorNotificacionTest() {
		// la relacion uno existe
		Relacion relacion = relacionDao.obtenerRelacionPorNotificacion(new Long(1));
		Assert.assertEquals(relacion.getCodigo(), new Long(1));
		//la relacion cuatro no existe
		Relacion relacionNula = relacionDao.obtenerRelacionPorNotificacion(new Long(4));
		Assert.assertNull(relacionNula);

	}
	
	@Test
	public void obtenerRelacionUnMetodoTest(){
		Relacion relacion = relacionDao.obtenerRelacion(new Long(1));
		Assert.assertEquals(relacion, relacionDao.getObjectByID(new Long(1)));
	}
	
//	@Test
	public void tieneSolicitudParaMedicoConEspecialidadTest(){
		Assert.assertTrue(relacionDao.tieneSolicitudParaMedicoConEspecialidad(new Long(21), new Long(4)));
		Assert.assertFalse(relacionDao.tieneSolicitudParaMedicoConEspecialidad(new Long(21), new Long(1)));
	}
	
	@Test
	public void getRelacionesActivasTest(){
		Usuario usuario = new Usuario();
		usuario.setCodigo(new Long(2));
		Assert.assertTrue(relacionDao.getRelacionesActivas(usuario).size()== 22);
		usuario.setCodigo(new Long(39));
		Assert.assertTrue(relacionDao.getRelacionesActivas(usuario).size()== 0);

	}
}
