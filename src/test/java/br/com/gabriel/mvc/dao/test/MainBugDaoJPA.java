package br.com.gabriel.mvc.dao.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.gabriel.mvc.dao.BugDao;
import br.com.gabriel.mvc.dao.jpa.BugDaoJPA;
import br.com.gabriel.mvc.modelo.Bug;
import br.com.gabriel.mvc.modelo.Usuario;

public class MainBugDaoJPA {

	private static EntityManager entityManager;

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
//		inserir();
		
		buscarPorId(1L);
		//buscarTodos();
		
		//atualizar(3L);
		
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		factory.close();
	}

	private static void buscarTodos() {
		BugDao dao = new BugDaoJPA(entityManager);
		
		List<Bug> todos = dao.todos();
		for (Bug bug : todos) {
			System.out.println(bug);
		}
	}

	private static void inserir() {
		Bug bug = new Bug("Primeiro", "Erro na tela", new Usuario(1L));
		
		System.out.println(bug);
		System.out.println(bug.getHistoricoEventos());
		
		BugDao dao = new BugDaoJPA(entityManager);
		
		dao.adiciona(bug);
		
	}
	
	private static void buscarPorId(Long id) {
		
		System.out.println("id="+id);
		
		BugDao dao = new BugDaoJPA(entityManager);
		
		Bug bug = dao.buscaPorId(id);
		
		System.out.println(bug);
	}
	
	private static void atualizar(Long id) {
		
		System.out.println("id="+id);
		
		BugDao dao = new BugDaoJPA(entityManager);
		
		Bug bug = dao.buscaPorId(id);
		
		System.out.println("retornado: " + bug);
		
		bug.passaProximaFase("Proxima fase", new Usuario(4L));
		
		dao.atualiza(bug);
		
	}
}
