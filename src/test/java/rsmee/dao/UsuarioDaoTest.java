package rsmee.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;

import model.EstadoUsuario;
import model.Prestador;
import model.Usuario;

import org.apache.commons.lang3.time.DateUtils;
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
public class UsuarioDaoTest extends BaseJPATest {

	private static Long CODIGO_ESPECIALIDAD_TRAUMATOLOGIA = new Long(6);
	private static Long CODIGO_ESPECIALIDAD_MEDICINA_GRAL = new Long(1);
	
	@Inject UsuarioDao usuarioDao;
	@Inject MensajeDestinoDao mensajeDestinoDao;
	Usuario administrador = null;
	
	@Deployment
    public static WebArchive createDeployment() {
		return createDeployment("usuarioTest.war");
	}

	@Before
	public void setup(){
		administrador = usuarioDao.findBy(new Long(1));
	}
	
	@Test
	public void getUsuariosCodigoInTest() {
		List<Long> codigosUsuarios = new ArrayList<Long>();
		codigosUsuarios.add(new Long(1));
		codigosUsuarios.add(new Long(2));
		codigosUsuarios.add(new Long(3));
		codigosUsuarios.add(new Long(4));
		codigosUsuarios.add(new Long(5));
		codigosUsuarios.add(new Long(6));
		
		List<Usuario> usuarios = usuarioDao.getUsuariosCodigoIn(codigosUsuarios); 
		Assert.assertEquals(usuarios.size(), codigosUsuarios.size());
		
		Assert.assertTrue(usuarios.contains(new Usuario(new Long(1))));
		Assert.assertTrue(usuarios.contains(new Usuario(new Long(6))));
	}

	@Test
	public void buscarUsuarioPorCodigoTest(){
		Assert.assertEquals(usuarioDao.buscarUsuarioPorCodigo(new Long(1)), new Usuario(new Long(1)));
		Assert.assertNull(usuarioDao.buscarUsuarioPorCodigo(new Long(2135642)));
	}
	
	@Test
	public void cantidadUsuariosPendientesTest(){
		Assert.assertEquals(usuarioDao.cantidadUsuariosPacientesPendientes(administrador), new Integer(7));
		//creo un usuario administrador fake
		Usuario usuario = new Usuario(new Long(987987));
		Prestador prestador = new Prestador();
		prestador.setCodigo("sazarara");
		usuario.setPrestador(prestador);
		Assert.assertEquals(usuarioDao.cantidadUsuariosPacientesPendientes(usuario), new Integer(0));
	}
	
	@Test
	public void listarUsuariosAdministradorTest(){
		List<Usuario> usuarios = usuarioDao.listarUsuarios(administrador, 2, 2);
		Assert.assertTrue(usuarios.size() == 2);
		Assert.assertEquals(usuarios.get(0).getCodigo(), new Long(3667));
		usuarios = usuarioDao.listarUsuarios(administrador, 2, 6);
		Assert.assertTrue(usuarios.size() == 1);
		Assert.assertEquals(usuarios.get(0).getCodigo(), new Long(3672));

	}
	
	@Test
	public void obtenerCvPorCodUsuarioTest(){
		Assert.assertEquals(usuarioDao.obtenerCvPorCodUsuario(new Long(2)), "/opt/rsm/cv/agustin_podesta@hotmail.com/CV_Podesta Lecuona Agustin.pdf");
		Assert.assertEquals(usuarioDao.obtenerCvPorCodUsuario(new Long(2197)), "/opt/rsm/cv/claradediego@gmail.com/CV Ma. Clara de Diego.doc");
		Assert.assertNull(usuarioDao.obtenerCvPorCodUsuario(new Long(1)));
	}
	
	@Test
	public void getUsuariosPacientesPorEstadoTest(){
		List<String> codigosEstado = new ArrayList<String>();
		codigosEstado.add(EstadoUsuario.ESTADO_BAJA.getCodigo());
		codigosEstado.add(EstadoUsuario.ESTADO_ACTIVO.getCodigo());
		Assert.assertTrue(usuarioDao.getUsuariosPacientesPorEstado(codigosEstado).size() == 962 + 2271);
	}
	
	@Test
	public void existeUsuarioAfiliadoTest(){
		Assert.assertTrue(usuarioDao.existeUsuarioAfiliado("43183819", "00355102"));
		Assert.assertFalse(usuarioDao.existeUsuarioAfiliado("43183819", "SARAZASASA"));
	}
	
	@Test
	public void usuariosPacientesPorClavePrestadorTest(){
		// nombres y apellidos con acento
		List<Usuario> usuarios = usuarioDao.usuariosPacientesPorClavePrestador("1", "Inés");
		Assert.assertTrue(usuarios.size()==7);

		// nombres y apellidos con acento
		usuarios = usuarioDao.usuariosPacientesPorClavePrestador("1", "MarÍA");
		Assert.assertTrue(usuarios.size()==16);
		
		// no hay pacientes con prestador distinto de 1
		usuarios = usuarioDao.usuariosPacientesPorClavePrestador("2", "MarÍA");
		Assert.assertTrue(usuarios.size()==0);

	}
	
	@Test
	public void getCodigosProfesionalesPorPrestadorTest(){
		List<Long> codigosProfesional = usuarioDao.getCodigosProfesionalesPorPrestador("1");
		// hay 36 profesionales
		Assert.assertTrue(codigosProfesional.size()== 36);
	}

	@Test
	public void getUsuariosEstadoPreTest() throws ParseException{
		//desde el 2016-01-01 hay 50 usuarios en estado PRE
		Date fechaDesde = (Date) DateUtils.parseDate("2016-01-01", "yyyy-MM-dd");
		System.out.println("se obtendrán usuarios estadp PRE desde la fecha " + fechaDesde.toString());
		List<Usuario> usuarios = usuarioDao.getUsuariosEstadoPre(fechaDesde);
		Assert.assertTrue(usuarios.size() == 50);
	}
	
	@Test
	public void obtenerUsuariosItemSegunPrestadorTest(){
		List<SelectItem> usuarios = usuarioDao.obtenerUsuariosItemSegunPrestador(administrador);
		Assert.assertTrue(usuarios.size() == 2300);
	}
	
	@Test
	public void usuariosMedicosPorPrestadorConEspecialidadTest(){
		List<Long> codigosEspecialidad = new ArrayList<Long>();
		codigosEspecialidad.add(CODIGO_ESPECIALIDAD_TRAUMATOLOGIA);
		List<Usuario> usuarios = usuarioDao.usuariosMedicosPorPrestadorConEspecialidad(PRESTADOR_AUSTRAL_SALUD, codigosEspecialidad);
		//Hay un traumatólogos en Austral
		Assert.assertTrue(usuarios.size() == 1);
		//por las dudas, verifico que efectivamente sean traumatólogos!
		Assert.assertEquals(usuarios.get(0).getProfesional().getEspecialidades().get(0).getCodigo(), CODIGO_ESPECIALIDAD_TRAUMATOLOGIA);
		//rechequeo, hay 19 medicos con especialidad medicina general
		codigosEspecialidad = new ArrayList<Long>();
		codigosEspecialidad.add(CODIGO_ESPECIALIDAD_MEDICINA_GRAL);
		usuarios = usuarioDao.usuariosMedicosPorPrestadorConEspecialidad(PRESTADOR_AUSTRAL_SALUD, codigosEspecialidad);
		Assert.assertTrue(usuarios.size() == 19);
		
	}
	
	@Test
	public void colegasConMismaEspecialidadTest(){
		Usuario medicoCinco = usuarioDao.getObjectByID(new Long(5));
		List<Long> codigoEspecialidades = new ArrayList<Long>();
		codigoEspecialidades.add(CODIGO_ESPECIALIDAD_MEDICINA_GRAL);
		List<Usuario> usuarios = usuarioDao.colegasConMismaEspecialidad(PRESTADOR_AUSTRAL_SALUD, codigoEspecialidades, medicoCinco);
		Assert.assertTrue(usuarios.size() == 18);
	}
	
	@Test
	public void getCodigosProfesionalesQueTenganEspecilidadTest(){
		List<Long> codigoEspecialidades = new ArrayList<Long>();
		codigoEspecialidades.add(CODIGO_ESPECIALIDAD_MEDICINA_GRAL);
		codigoEspecialidades.add(CODIGO_ESPECIALIDAD_TRAUMATOLOGIA);
		List<Long> codigoUsuarios = usuarioDao.getCodigosProfesionalesQueTenganEspecilidad(codigoEspecialidades);
		// hay 20 médicos con ambas especialidades
		Assert.assertTrue(codigoUsuarios.size() == 20);
		//verifico que esté el traumatólogo (código 2234
		Assert.assertTrue(codigoUsuarios.contains(new Long(2234)));
		//verifico que exista un medicina general
		Assert.assertTrue(codigoUsuarios.contains(new Long(1063)));
		
	}
	@Test
	public void usuariosPacientesContactadosConTest(){
		String[] codigoMedicosDestino = "27,518,675,762,1063,1067,1549,1580,1611,1629".split(",");
		List<Long> codMedicos = arrayStringToListLong(codigoMedicosDestino);
		List<Long> codigosPacientes = mensajeDestinoDao.codPacientesQueConsultaronA(codMedicos);
		List<Usuario> usuariosPacientes = usuarioDao.usuariosPacientesContactadosCon(codMedicos);
		Assert.assertEquals(codigosPacientes.size(), usuariosPacientes.size());
	}
}
