package br.com.gabriel.mvc.service;

import br.com.gabriel.mvc.modelo.Bug;
import br.com.gabriel.mvc.modelo.NovoBugDTO;
import br.com.gabriel.mvc.modelo.Usuario;

public class BugServiceImpl implements BugService {

	@Override
	public Bug criaBug(NovoBugDTO novoBugDTO, Usuario usuario) {
		return new Bug(novoBugDTO.getNome(), novoBugDTO.getDescricao(), usuario);
	}

}
