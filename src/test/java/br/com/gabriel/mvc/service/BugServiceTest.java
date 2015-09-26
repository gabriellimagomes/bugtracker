package br.com.gabriel.mvc.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.gabriel.mvc.modelo.Bug;
import br.com.gabriel.mvc.modelo.NovoBugDTO;
import br.com.gabriel.mvc.modelo.Usuario;

public class BugServiceTest {

	@Test
	public void deveCriarObjetoBugAtravesDadosDeTela() {
		NovoBugDTO novoBugDTO = new NovoBugDTO();
		novoBugDTO.setNome("exemplo");
		novoBugDTO.setDescricao("descricao");
		
		BugService bugService = new BugServiceImpl();
		Bug bug = bugService.criaBug(novoBugDTO, new Usuario("gabriel"));
		assertEquals("exemplo", bug.getNome());
		assertEquals("descricao", bug.getEventoAtual().getDescricao());
		assertEquals("gabriel", bug.getEventoAtual().getPessoa().getNome());
	}

}
