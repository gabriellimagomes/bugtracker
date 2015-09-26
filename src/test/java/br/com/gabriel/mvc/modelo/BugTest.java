package br.com.gabriel.mvc.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import br.com.gabriel.mvc.modelo.status.TipoStatus;

public class BugTest {

	@Test
	public void deveCriarAssociandoAUmaPessoa() {
		
		Bug bug = new Bug("nome", "descricao", new Usuario("Jonas"));
		TipoStatus statusAtual = bug.getEventoAtual().getTipoStatus();
		assertTrue("Deve haver uma pessoa associada ao Bug", bug.getEventoAtual().getPessoa() != null);
		assertEquals("Um bug deve comecar com o status Aguardando Triagem", TipoStatus.getTipoStatusInicial(), statusAtual);
	}
	
	@Test
	public void devePoderPassarBugParaProximaFaseEManterHistorico() throws Exception {
		
		Usuario pessoaQueIniciouBug = new Usuario("Fernando");
		Usuario pessoaQueMudouStatusBug = new Usuario("Julia");
		
		Bug bug = new Bug("nome", "descricao", pessoaQueIniciouBug);
		bug.passaProximaFase("descricao", pessoaQueMudouStatusBug);
		TipoStatus statusAtual = bug.getEventoAtual().getTipoStatus();
		
		assertEquals("Depois de aguardar triagem o bug deve caguardar correcao", TipoStatus.AGUARDANDO_CORRECAO, statusAtual);
		assertEquals("a pessoa que contem no objeto bug deve ser a pessoa que alterou o ultimo status do bug", pessoaQueMudouStatusBug, bug.getEventoAtual().getPessoa());
	}
	
	@Test
	public void devePoderPassarBugParaFaseAnteriorEManterHistorico() throws Exception {
		Assert.fail("Implementar");
	}

}