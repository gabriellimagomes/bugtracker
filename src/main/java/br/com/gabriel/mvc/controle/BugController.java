package br.com.gabriel.mvc.controle;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.subject.Subject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.gabriel.mvc.dao.BugDao;
import br.com.gabriel.mvc.modelo.Bug;
import br.com.gabriel.mvc.modelo.NovoBugDTO;
import br.com.gabriel.mvc.modelo.Usuario;
import br.com.gabriel.mvc.service.BugService;

@Controller
public class BugController {
	
	private Result result;
	private BugDao dao;
	private BugService bugService;
	private Subject usuarioLogado;

	@Inject
	public BugController(Result result, BugDao dao, Subject usuarioLogado, BugService bugService) {
		this.result = result;
		this.bugService = bugService;
		this.dao = dao;
		this.usuarioLogado = usuarioLogado;
	}
	
	@Deprecated
	public BugController() {}
	
	/*
	GET /bugtracker/bugs (listar bugs)
	GET /bugtracker/bugs/{idBug}
	POST /bugtracker/bugs
	PUT /bugtracker/bugs/{idBug}/status/proximo
	PUT /bugtracker/bugs/{idBug}/status/anterior
	 */
	
	@Get(value={"/", "/bugs"})
	public void buscarTodos() {
		List<Bug> buscarTodos = dao.todos();
		
		if(buscarTodos == null || buscarTodos.isEmpty()) {
			result.use(Results.status()).noContent();
		} else {
			result.use(Results.json()).withoutRoot()
				.from(buscarTodos)
				.recursive()
				.serialize();
		}
	}
	
	@Get(value="/bugs/{id}")
	public void buscarPorId(Long id) {
		Bug bug = dao.buscaPorId(id);
		//TODO: implementar com a classe
	}
	
	@Post(value="/bugs")
	@Consumes(value="application/json")
	public void adicionar(NovoBugDTO novoBugDTO) {
		Usuario usuario = (Usuario) usuarioLogado.getSession().getAttribute("usuario");
		Bug bug = bugService.criaBug(novoBugDTO, usuario);
		//status .created();
	}
	
	@Put(value="/bugs/status/proximo")
	@Consumes(value="application/json")
	public void proximoStatus(Long id, String descricao){
		
		//status .ok();
	}
	
	@Put(value="/bugs/status/anterior")
	@Consumes(value="application/json")
	public void statusAnterior(Long id, String descricao){
		Bug bug = dao.buscaPorId(id);
		
		Usuario usuario = (Usuario) usuarioLogado.getSession().getAttribute("usuario");
		bug.passaFaseAnterior(descricao, usuario);
		
		dao.atualiza(bug);
		
		result.use(Results.status()).ok();
	}
}