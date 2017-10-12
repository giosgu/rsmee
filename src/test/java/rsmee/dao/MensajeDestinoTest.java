package rsmee.dao;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import model.EstadoMensaje;
import model.MensajeDestino;
import model.Usuario;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import rsmee.BaseJPATest;
import dao.MensajeDestinoDao;
import dao.UsuarioDao;

@RunWith(Arquillian.class)
public class MensajeDestinoTest extends BaseJPATest {

	@Inject MensajeDestinoDao mensajeDestinoDao;
	@Inject UsuarioDao usuarioDao;
	private Usuario administrador;
	
	@Deployment
    public static WebArchive createDeployment() {
		return createDeployment("mensajeDestinoTest.war");
	}

	@Before
	public void setup(){
		administrador = usuarioDao.getObjectByID(new Long(1));
	}

	@Test
	public void getMensajeDestinoTest(){
		// recupero sólo el mensaje id 573
		List<MensajeDestino> mensajesDestino = mensajeDestinoDao.getMensajeDestino(new Long(224), new Long(23));
		Assert.assertTrue(mensajesDestino.size()==1);
		Assert.assertEquals(mensajesDestino.get(0).getCodigo(), new Long(573));
	}
	
	@Test
	public void getCountMensajesPendientesTest(){
		Assert.assertTrue(mensajeDestinoDao.getCountMensajesPendientes(administrador) == 3);
	}
	
	@Test 
	public void estadoPorMensajeYUsuarioDestinoTest(){
		EstadoMensaje estadoMensaje = mensajeDestinoDao.estadoPorMensajeYUsuarioDestino(new Long(3120), new Long(3));
		Assert.assertEquals(estadoMensaje.getCodigo(), EstadoMensaje.LEIDO);
	}
	
	@Test
	public void getMensajesDestinosByCodigosUsuariosTest(){
		//el mensaje 33 se envía, entre a otros, a los siguientes usuarios
		String[] usuariosDestino = "33, 28, 37, 38, 63, 116, 96, 76, 41, 101, 64".split(",");
		List<Long> codigos = arrayStringToListLong(usuariosDestino);
		List<MensajeDestino> mensajesDestino = mensajeDestinoDao.getMensajesDestinosByCodigosUsuarios(new Long(33), codigos);
		Assert.assertEquals(mensajesDestino.size(), 11);
		//los codigos de mensaje destino de los ids de arriba son estos
		List<String> codigosMensajeDestino = Arrays.asList("74,76,77,81,86,87,89,93,96,99,106".split(","));
		for (MensajeDestino mensajeDestino : mensajesDestino) {
			Assert.assertTrue(codigosMensajeDestino.contains(mensajeDestino.getCodigo().toString()));
		}
	}
	
	@Test
	public void codPacientesQueConsultaronATest(){
		String[] codigoMedicosDestino = "27,518,675,762,1063,1067,1549,1580,1611,1629".split(",");
		List<Long> codMedicos = arrayStringToListLong(codigoMedicosDestino);
		List<Long> codigosPacientes = mensajeDestinoDao.codPacientesQueConsultaronA(codMedicos);
		String[] pacientesEsperadosStr = "9,13,55,58,60,121,157,264,280,290,294,320,334,355,407,416,435,441,463,466,467,501,509,531,536,545,552,570,572,598,610,613,625,645,671,720,725,731,744,761,784,815,824,913,924,936,937,945,954,962,969,982,1001,1008,1027,1044,1070,1081,1082,1095,1096,1111,1124,1157,1161,1163,1167,1193,1209,1215,1232,1234,1247,1248,1252,1257,1260,1262,1267,1294,1312,1359,1370,1390,1408,1426,1428,1430,1446,1460,1472,1504,1520,1529,1552,1583,1592,1615,1630,1685,1698,1703,1715,1735,1736,1747,1771,1775,1793,1799,1849,1851,1853,1865,2034,2039,2046,2056,2066,2082,2090,2107,2124,2151,2157,2175,2200,2244,2248,2258,2271,2350,2375,2391,2446,2463,2500,2526,2541,2544,2672,2718,2724,2734,2778,2807,2842,2898,2911,2921,2958,3004,3050,3057,3079,3102,3121,3142,3154,3184,3207,3215,3258,3274,3365,3385,3394,3536,3538,3585".split(",");
		List<Long> pacientesEsperados =arrayStringToListLong(pacientesEsperadosStr);
		Assert.assertEquals(codigosPacientes.size(), 171);
		Assert.assertTrue(codigosPacientes.containsAll(pacientesEsperados));
	}
	
	@Test
	public void esMensajePendienteTest(){
		// el mensaje 33 no está pendiente
		Assert.assertFalse(mensajeDestinoDao.esMensajePendiente(new Long(33)));
		// el mensaje 3121 está pendiente
		Assert.assertTrue(mensajeDestinoDao.esMensajePendiente(new Long(3121)));

	}

}
