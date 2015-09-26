package br.com.gabriel.mvc.controle;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.gabriel.mvc.dao.UsuarioDao;
import br.com.gabriel.mvc.modelo.Usuario;
import br.com.gabriel.mvc.shiro.helper.HashAuthenticationHelper;

@Controller
public class LoginController {
	private Result result;
	private UsuarioDao usuarioDao;
	
	@Inject
	public LoginController(Result result, UsuarioDao usuarioDao){
		this.result = result;
		this.usuarioDao = usuarioDao;
	}
	
	@Deprecated
	public LoginController() {}
	
	@Post("/login")
	@Consumes(value="application/json")
	public void login(String usuario, String senha) {
		System.out.println("LoginController.login() ================================= auth: " + usuario);
		System.out.println("LoginController.login() ================================= auth: " + senha);
		
		String auth = "";
		
		try {
			Usuario usuarioDobanco = usuarioDao.buscarPorUsername(usuario);
			
			if(usuarioDobanco.senhaValida(senha)){
				
				auth = HashAuthenticationHelper.encode(usuario, senha);
				
				System.out.println("LoginController.login() ================================= auth: " + auth);
				
				result.use(Results.http()).addHeader("Authorization", auth).setStatusCode(200);
			} else {
				this.setErroNoLogin();
			}
			
		} catch (Exception e) {
			this.setErroNoLogin();
		}
	}

	private void setErroNoLogin() {
		result.use(Results.http()).sendError(HttpServletResponse.SC_UNAUTHORIZED, "WWW-Authenticate: Basic realm=\"Protected\"");
	}

	/*
	@Get("/logout")
	public void logout() {
	}
	*/
}