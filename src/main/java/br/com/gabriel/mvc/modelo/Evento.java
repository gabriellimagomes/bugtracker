package br.com.gabriel.mvc.modelo;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.gabriel.mvc.modelo.status.TipoStatus;

@Entity
public class Evento implements Comparator<Evento>{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoStatus tipoStatus;
	
	private String descricao;
	
	@ManyToOne
	private Usuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDeCriacao;

	/**
	 * Hibernate eyes only
	 * @deprecated
	 */
	public Evento() {}
	
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

	@Override
	public int compare(Evento o1, Evento o2) {
		return o1.dataDeCriacao.compareTo(o2.getDataDeCriacao());
	}
	
}
