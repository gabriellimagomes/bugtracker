package br.com.gabriel.mvc.service;

import br.com.gabriel.mvc.modelo.Bug;
import br.com.gabriel.mvc.modelo.NovoBugDTO;
import br.com.gabriel.mvc.modelo.Usuario;

public interface BugService {

	Bug criaBug(NovoBugDTO novoBugDTO, Usuario usuario);
}
