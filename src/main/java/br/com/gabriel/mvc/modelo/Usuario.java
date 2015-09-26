package br.com.gabriel.mvc.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.caelum.vraptor.serialization.SkipSerialization;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String email;
	@SkipSerialization
	private String senha;
	
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public Usuario(String nome) {
		this.nome = nome;
	}

	public Usuario(Long id) {
		this.id = id;
	}
	
	public Usuario() {
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}

	public boolean senhaValida(String senhaValidada) {
		return senhaValidada.equals(senha);
	}
	
}