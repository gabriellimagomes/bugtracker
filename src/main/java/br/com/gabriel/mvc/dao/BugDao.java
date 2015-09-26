package br.com.gabriel.mvc.dao;

import java.util.List;

import br.com.gabriel.mvc.modelo.Bug;

public interface BugDao {
	void adiciona(Bug bug);
	Bug buscarPorId(Long id);
	void atualiza(Bug bug);
	List<Bug> todos();
}