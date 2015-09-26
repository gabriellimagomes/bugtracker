package br.com.gabriel.mvc.shiro;

import java.util.Collections;
import java.util.Set;

import javax.inject.Inject;

import br.com.caelum.vraptor.security.Permission;
import br.com.caelum.vraptor.security.User;
import br.com.gabriel.mvc.dao.UsuarioDao;
import br.com.gabriel.mvc.modelo.Usuario;

public class AuthService implements Permission {

    @Inject UsuarioDao usuarioDao;

    @Override
    public User getUserByUsername(String email) {
        Usuario usuario = usuarioDao.buscarPorUsername(email);
        return new User(usuario.getEmail(), usuario.getSenha());
    }

    @Override
    public Set<String> getRolesByUser(String username) {
        return Collections.emptySet();
    	//return usuarioDao.listarPerfilsByUsername(username);
    }

    @Override
    public Set<String> getPermissionsByRole(String role) {
    	return Collections.emptySet();
    	//return usuarioDao.listarPermissoesByPerfil(role);
    }
}