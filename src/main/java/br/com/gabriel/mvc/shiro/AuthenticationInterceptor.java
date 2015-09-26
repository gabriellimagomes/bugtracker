package br.com.gabriel.mvc.shiro;

import static br.com.caelum.vraptor.view.Results.status;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.gabriel.mvc.dao.UsuarioDao;
import br.com.gabriel.mvc.modelo.Usuario;
import br.com.gabriel.mvc.shiro.helper.HashAuthenticationHelper;

@Intercepts
public class AuthenticationInterceptor {

	private static final String ACESSO_NAO_PERMITIDO = "Acesso nao permitido!";
	@Inject
	private Result result;
	@Inject
	private Subject subject;
	@Inject
	private HttpServletRequest request;
	@Inject
	private UsuarioDao usuarioDao; 
	
	@Accepts
	public boolean accepts(ControllerMethod method) {
		System.out.println("AuthenticationInterceptor.accepts() ==> " + request.getRequestURI());
		if(request.getRequestURI().equals("/bugtracker/login")) {
			return false;
		}
		return true;
				
	}

	@AroundCall
	public void around(SimpleInterceptorStack stack) {
		try {
			
			String authorization = request.getHeader("Authorization");
			if (authorization == null) {
				//throw new AuthorizationException("Access Denied! No authorization header received.");
				throw new Exception(ACESSO_NAO_PERMITIDO);
				//result.use(Results.status()).forbidden(ACESSO_NAO_PERMITIDO);
			}

			String[] decode = HashAuthenticationHelper.decode(authorization);
			
			String username = decode[0];
			String password = decode[1];
			
			Usuario usuario = usuarioDao.buscarPorUsername(username);
			
			if(usuario.getSenha().equals(password)) {
				subject.login(new UsernamePasswordToken(username, password));
				subject.getSession().setAttribute("usuario", usuario);
			} else {
				throw new IncorrectCredentialsException(ACESSO_NAO_PERMITIDO);
			}
			
			stack.next();
		} catch (UnknownAccountException e) {
			result.use(status()).forbidden(e.toString());
		} catch (IncorrectCredentialsException e) {
			result.use(status()).forbidden(e.toString());
		} catch (LockedAccountException e) {
			result.use(status()).forbidden(e.toString());
		} catch (ExcessiveAttemptsException e) {
			result.use(status()).forbidden(e.toString());
		} catch (AuthenticationException e) {
			result.use(status()).forbidden(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
			result.use(status()).forbidden(e.getMessage());
		}
	}
}