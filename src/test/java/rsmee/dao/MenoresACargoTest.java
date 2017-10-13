package rsmee.dao;

import java.util.List;

import javax.inject.Inject;

import model.MenoresACargo;
import model.Usuario;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import rsmee.BaseJPATest;
import dao.MenoresACargoDao;
import dao.UsuarioDao;

@RunWith(Arquillian.class)
public class MenoresACargoTest extends BaseJPATest {

	@Inject MenoresACargoDao menoresACargoDao;
	@Inject UsuarioDao usuarioDao;
	Usuario pacienteConFamiliarACargo;
	Usuario pacienteSinFamiliarACargo;
	
	@Deployment
    public static WebArchive createDeployment() {
		return createDeployment("menoresACargoDaoTest.war");
	}

	@Before
	public void setup(){
		pacienteConFamiliarACargo =usuarioDao.getObjectByID(new Long(11));
		pacienteSinFamiliarACargo =usuarioDao.getObjectByID(new Long(100));
	}

	@Test
	public void getMensajeDestinoTest(){
		Assert.assertTrue(menoresACargoDao.tieneAlgunFamiliarACargo(pacienteConFamiliarACargo));
		Assert.assertFalse(menoresACargoDao.tieneAlgunFamiliarACargo(pacienteSinFamiliarACargo));
	}
	
	@Test
	public void getMisFamiliaresACargoTest(){
		//el paciente 11 tiene a cargo al familiar 105
		List<MenoresACargo> menores = menoresACargoDao.misFamiliaresACargo(pacienteConFamiliarACargo);
		Assert.assertTrue(menores.size() == 1);
		Assert.assertEquals(new Long(105), menores.get(0).getCodigo());
		//el paciente 100 no tiene familiares a cargo
		Assert.assertEquals(0, menoresACargoDao.misFamiliaresACargo(pacienteSinFamiliarACargo).size());
	}

	@Test
	public void getCodigoMisFamiliaresACargoTest(){
		//el paciente 11 tiene a cargo al familiar 105
		List<Long> menores = menoresACargoDao.codigosMisFamiliaresACargo(pacienteConFamiliarACargo);
		Assert.assertTrue(menores.size() == 1);
		Assert.assertEquals(new Long(1369), menores.get(0));
		//el paciente 100 no tiene familiares a cargo
		Assert.assertEquals(0, menoresACargoDao.codigosMisFamiliaresACargo(pacienteSinFamiliarACargo).size());
	}

	@Test
	public void getMenoresACargoByMenorTest(){
		//el menor "829" corresponde al código 7
		Assert.assertEquals(7, menoresACargoDao.getMenoresACargoByMenor(new Long(829)).getCodigo().intValue());
		//el menor "9999999999" no existe
		Assert.assertNull(menoresACargoDao.getMenoresACargoByMenor(new Long(999999999)));

	}
	
	@Test
	public void getAdultoResponsableByMenorTest(){
		//el menor "875" tiene adulto responsable 281
		Assert.assertEquals(281, menoresACargoDao.getAdultoResponsableByMenor((new Long(875))).getCodigo().intValue());
		//el menor "9999999999" no existe
		Assert.assertNull(menoresACargoDao.getAdultoResponsableByMenor(new Long(999999999)));
	}
	
	@Test
	public void getMenoresACargoByMayorTest(){
	    //el adulto 857 tiene tres menores a cargo 
		Assert.assertEquals(2, menoresACargoDao.getMenoresACargoByMayor(new Long(857)).size());
	    //el adulto 281 tiene dos
		Assert.assertEquals(2, menoresACargoDao.getMenoresACargoByMayor(new Long(281)).size());
	    //el adulto 1 no tiene
		Assert.assertEquals(0, menoresACargoDao.getMenoresACargoByMayor(new Long(1)).size());
	}
	
	@Test
	public void esAdultoResponsableDeMenorTest(){
//	    adulto 2586 tiene 2587
		Assert.assertTrue(menoresACargoDao.esAdultoResponsableDeMenor(new Long(2586) , new Long(2587)));
//	    adulto '2591' tiene a 2593
		Assert.assertTrue(menoresACargoDao.esAdultoResponsableDeMenor(new Long(2591) , new Long(2593)));
//	    adulto 2582 no tiene menor 4
		Assert.assertFalse(menoresACargoDao.esAdultoResponsableDeMenor(new Long(2582) , new Long(4)));
	}
	
}
