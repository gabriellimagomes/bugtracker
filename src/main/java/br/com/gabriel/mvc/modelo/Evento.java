package br.com.gabriel.mvc.modelo;

import java.util.Date;

import br.com.gabriel.mvc.modelo.status.TipoStatus;

public class Evento {
	
	private Long id;
	private TipoStatus tipoStatus;
	private String descricao;
	private Usuario usuario;
	private Date dataDeCriacao;

	public Evento(TipoStatus tipoStatus, String descricao, Usuario usuario) {
		this.tipoStatus = tipoStatus;
		this.descricao = descricao;
		this.usuario = usuario;
		this.dataDeCriacao = new Date();
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuario getPessoa() {
		return usuario;
	}

	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}

	public TipoStatus getTipoStatus() {
		return tipoStatus;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", tipoStatus=" + tipoStatus + ", descricao=" + descricao
				+ ", pessoa=" + usuario + ", dataDeCriacao=" + dataDeCriacao + "]";
	}

}
