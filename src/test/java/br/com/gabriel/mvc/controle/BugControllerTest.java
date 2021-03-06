package br.com.gabriel.mvc.controle;

import java.util.List;

import org.junit.Assert;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.specification.RequestSpecification;

import br.com.gabriel.mvc.dao.helper.DBHelper;
import br.com.gabriel.mvc.modelo.Bug;
import br.com.gabriel.mvc.modelo.NovoBugDTO;

/**
 * Subir o tomcat com: mvn tomcat7:run -Dbr.com.caelum.vraptor.environment=test
 */
public class BugControllerTest {

	private RequestSpecification basicLogin;

	// @Before
	public void setUp() throws Exception {
		new DBHelper().criarMassaDeDados();
		basicLogin = RestAssured.given().auth().preemptive().basic("gabriel@mail.com", "senha");
	}

	// @After
	public void tearDown() throws Exception {
		new DBHelper().limparBancoDeDados();
	}

	// @Test
	public void deveListarTodosOsBugs() throws Exception {
		JsonPath json = basicLogin.get("/bugtracker/bugs").andReturn().jsonPath();

		List<Bug> list = json.get("");
		Assert.assertEquals(3, list.size());
	}

	// @Test
	public void deveBuscarUmBugPeloId() {
		JsonPath json = basicLogin.get("/bugtracker/bugs/1").andReturn().jsonPath();

		Bug bug = json.getObject("", Bug.class);

		Assert.assertNotNull(bug);
	}

	public void deveAdicionarUmBug() throws Exception {
		NovoBugDTO novoBugDTO = new NovoBugDTO();
		novoBugDTO.setNome("Bug test");
		novoBugDTO.setDescricao("Usando o Junit");
	}
}