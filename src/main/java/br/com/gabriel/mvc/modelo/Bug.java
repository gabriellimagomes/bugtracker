package br.com.gabriel.mvc.modelo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.SortComparator;

import br.com.gabriel.mvc.modelo.status.TipoStatus;

@Entity
public class Bug {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@Transient
	private TipoStatus tipoStatusProximo;
	@Transient
	private TipoStatus tipoStatusAnterior;
	@Transient
	private Evento eventoAtual;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@SortComparator(value=Evento.class)
	@OrderBy("id DESC")
	private List<Evento> historicoEventos;
	
	
	/**
	 * Hibernate eyes only
	 * @deprecated
	 */
	public Bug() {}
	
	public Bug(Bug bug) {
		this.id = bug.id;
		this.nome = bug.nome;
		this.historicoEventos = bug.historicoEventos;
		this.eventoAtual = this.historicoEventos.get(0);
		this.tipoStatusProximo = getStatusAtual().getProximo();
		this.tipoStatusAnterior = getStatusAtual().getAnterior();
	}
	
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
		tipoStatusAnterior = getStatusAtual().getAnterior();
		Evento evento = new Evento(tipoStatusAnterior, descricao, usuario);
		this.eventoAtual = evento;
		historicoEventos.add(evento);
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