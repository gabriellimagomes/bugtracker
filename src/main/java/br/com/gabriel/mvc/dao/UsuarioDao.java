package br.com.gabriel.mvc.dao;

import br.com.gabriel.mvc.modelo.Usuario;

public interface UsuarioDao {
	void adiciona(Usuario usuario);
	Usuario buscarPorId(Long id);
	Usuario buscarPorUsername(String username);
}
