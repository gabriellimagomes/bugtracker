package br.com.gabriel.mvc.dao.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gabriel.mvc.dao.UsuarioDao;
import br.com.gabriel.mvc.modelo.Usuario;

public class UsuarioDaoJPA implements UsuarioDao {

	private EntityManager entityManager;

	@Inject
	public UsuarioDaoJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * CDI eyes only
	 * @deprecated
	 */
	public UsuarioDaoJPA() {
	}

	public void adiciona(Usuario usuario) {
		entityManager.persist(usuario);
	}

	public Usuario buscarPorId(Long id) {
		return entityManager.find(Usuario.class, id);
	}

	public Usuario buscarPorUsername(String username) {
		System.out.println("UsuarioDaoJPA.buscarPorUsername(username): " + username);
		TypedQuery<Usuario> query = entityManager.createQuery(
						"select u from Usuario u where u.email = :email", 
						Usuario.class
					);
		query.setParameter("email", username);
		query.setMaxResults(1);
		List<Usuario> resultList = query.getResultList();
		for (Usuario u : resultList) {
			System.out.println("UsuarioDaoJPA.buscarPorUsername() usuario " + u);
		}
		return query.getSingleResult();
	}
}