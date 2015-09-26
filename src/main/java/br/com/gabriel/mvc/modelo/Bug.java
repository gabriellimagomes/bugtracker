package br.com.gabriel.mvc.modelo;

import java.util.LinkedList;
import java.util.List;

import br.com.gabriel.mvc.modelo.status.TipoStatus;

public class Bug {
	
	private Long id;
	private String nome;
	private TipoStatus tipoStatusProximo;
	private TipoStatus tipoStatusAnterior;
	private Evento eventoAtual;
	private List<Evento> historicoEventos;
	
	public Bug(String nome, String descricao, Usuario usuario) {
		this.nome = nome;
		this.historicoEventos = new LinkedList<Evento>();
		Evento evento = new Evento(TipoStatus.getTipoStatusInicial(), descricao, usuario);
		this.historicoEventos.add(evento);
		this.eventoAtual = evento;
		this.tipoStatusProximo = TipoStatus.getTipoStatusInicial().getProximo();
		this.tipoStatusAnterior = TipoStatus.getTipoStatusInicial().getAnterior();
	}

	public void passaProximaFase(String descricao, Usuario usuario) {
		tipoStatusProximo = getStatusAtual().getProximo();
		Evento evento = new Evento(tipoStatusProximo, descricao, usuario);
		this.eventoAtual = evento;
		historicoEventos.add(evento);
	}

	public void passaFaseAnterior(String descricao, Usuario usuario) {
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	private TipoStatus getStatusAtual() {
		return this.historicoEventos.get(0).getTipoStatus();
	}

	public List<Evento> getHistoricoEventos() {
		return historicoEventos;
	}

	public TipoStatus getProximoStatus(){
		return this.tipoStatusProximo;
	}
	
	public TipoStatus getStatusAnterior(){
		return this.tipoStatusAnterior;
	}
	
	public Evento getEventoAtual(){
		return this.eventoAtual;
	}

	@Override
	public String toString() {
		return "Bug [id=" + id + ", nome=" + nome + ", tipoStatusProximo="
				+ tipoStatusProximo + ", tipoStatusAnterior="
				+ tipoStatusAnterior + ", eventoAtual=" + eventoAtual
				+ ", historicoEventos=" + historicoEventos + "]";
	}
	
}