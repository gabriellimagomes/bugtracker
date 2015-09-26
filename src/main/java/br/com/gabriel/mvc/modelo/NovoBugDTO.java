package br.com.gabriel.mvc.modelo;

public class NovoBugDTO {

	private String nome;
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "NovoBugDTO [nome=" + nome + ", descricao=" + descricao + "]";
	}
}
