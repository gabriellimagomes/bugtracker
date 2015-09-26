package br.com.gabriel.mvc.dao.jpa;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gabriel.mvc.dao.BugDao;
import br.com.gabriel.mvc.modelo.Bug;

public class BugDaoJPA implements BugDao {
	private EntityManager entityManager;
	
	@Inject
	public BugDaoJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * CDI eyes only
	 * @deprecated
	 */
	public BugDaoJPA() {
	}
	
	public void adiciona(Bug bug) {
		entityManager.persist(bug);
	}

	public Bug buscaPorId(Long id) {
		return null;
	}

	public void atualiza(Bug bug) {
	}

	public List<Bug> todos() {
		TypedQuery<Bug> query = entityManager.createQuery("SELECT b FROM Bug b", Bug.class);
		List<Bug> resultList = query.getResultList();
		
		List<Bug> bugs = new LinkedList<Bug>();
		for (Bug bug : resultList) {
			bugs.add(new Bug(bug));
		}
		
		return bugs;
	}
}