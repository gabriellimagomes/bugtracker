package br.com.gabriel.mvc.dao.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.gabriel.mvc.dao.UsuarioDao;
import br.com.gabriel.mvc.dao.jpa.BugDaoJPA;
import br.com.gabriel.mvc.dao.jpa.UsuarioDaoJPA;
import br.com.gabriel.mvc.modelo.Bug;
import br.com.gabriel.mvc.modelo.Usuario;

public class DBHelper {
	private EntityManager entityManager;
	private EntityManagerFactory factory;
	private UsuarioDao usuarioDao;
	private BugDaoJPA bugDao;

	public void criarMassaDeDados() {
		this.inicio();
		
		usuarioDao.adiciona(new Usuario("Gabriel", "gabriel@mail.com", "senha"));
		bugDao.adiciona(new Bug("Bug um do Rest Assured", "Um erro qualquer 1", new Usuario(1l)));
		bugDao.adiciona(new Bug("Bug dois do Rest Assured", "Um erro qualquer 2", new Usuario(1l)));
		bugDao.adiciona(new Bug("Bug tres do Rest Assured", "Um erro qualquer 3", new Usuario(1l)));
		
		this.fim();
		
	}

	private void inicio() {
		factory = Persistence.createEntityManagerFactory("test");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		usuarioDao = new UsuarioDaoJPA(entityManager);
		bugDao = new BugDaoJPA(entityManager);
	}
	
	private void fim() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

	public void limparBancoDeDados() {
		inicio();
		
		entityManager.createNativeQuery("drop table Bug_Evento").executeUpdate();
		entityManager.createNativeQuery("drop table Evento").executeUpdate();
		entityManager.createNativeQuery("drop table Bug").executeUpdate();
		entityManager.createNativeQuery("drop table Usuario").executeUpdate();
		
		fim();
	}
}
