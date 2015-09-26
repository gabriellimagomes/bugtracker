package br.com.gabriel.mvc.shiro;

import javax.inject.Inject;

import org.apache.shiro.authz.AuthorizationException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.security.AuthorizationRestrictionListener;
import br.com.caelum.vraptor.view.Results;

@Controller
public class AuthController implements AuthorizationRestrictionListener {
	@Inject
	private Result result;

	@Override
	public void onAuthorizationRestriction(AuthorizationException e) {
		result.include("error", e.toString());
		//result.forwardTo(LoginController.class).formLogin();
		// OR
		result.use(Results.status()).forbidden(e.toString());
	}
}