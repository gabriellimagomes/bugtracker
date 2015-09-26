package br.com.gabriel.mvc.dao.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.gabriel.mvc.dao.UsuarioDao;
import br.com.gabriel.mvc.dao.jpa.UsuarioDaoJPA;
import br.com.gabriel.mvc.modelo.Usuario;

public class MainUsuarioDaoJPA {
	private static EntityManager entityManager;
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Usuario usuario = new Usuario("Gabriel", "gabriel@mail.com", "senha");
		
		UsuarioDao dao = new UsuarioDaoJPA(entityManager);
		
		dao.adiciona(usuario);
		//usuario = dao.buscarPorUsername("gabriel@mail.com");
	
		System.out.println(usuario);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
}
