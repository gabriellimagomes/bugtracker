package br.com.gabriel.mvc.shiro;

import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;

import br.com.caelum.vraptor.security.strategy.ShiroInitConfigStrategy;

public class InitConfigStrategy implements ShiroInitConfigStrategy {
    @Override
    public void init(DefaultWebSecurityManager securityManager, AuthorizingRealm realm) {
        DefaultWebSessionStorageEvaluator evaluator = new DefaultWebSessionStorageEvaluator();
        
        //stateless
        evaluator.setSessionStorageEnabled(false);
		((DefaultSubjectDAO) securityManager.getSubjectDAO()).setSessionStorageEvaluator(evaluator);
	}
}