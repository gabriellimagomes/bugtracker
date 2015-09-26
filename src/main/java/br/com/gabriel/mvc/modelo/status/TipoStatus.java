package br.com.gabriel.mvc.modelo.status;

public enum TipoStatus {
 /**
  * fluxo
  * Cancelado
  * aguardando trieagem
  * aguardando correcao
  * corrigido
  * finalizado
  */
	CANCELADO("Cancelado"),
	AGUARDANDO_TRIAGEM("Aguardando Triagem"),
	AGUARDANDO_CORRECAO("Aguardando Correção"),
	CORRIGIDO("Corrigido"),
	FINALIZADO("Finalizado"),
	NENHUM_STATUS("Nenhum Status");
	
	static {
		CANCELADO.set(AGUARDANDO_TRIAGEM, NENHUM_STATUS);
		AGUARDANDO_TRIAGEM.set(AGUARDANDO_CORRECAO, CANCELADO);
		AGUARDANDO_CORRECAO.set(CORRIGIDO, AGUARDANDO_TRIAGEM);
		CORRIGIDO.set(FINALIZADO, AGUARDANDO_CORRECAO);
		FINALIZADO.set(TipoStatus.NENHUM_STATUS, TipoStatus.NENHUM_STATUS);
	}
	
	private TipoStatus proximo;
	private TipoStatus anterior;
	private final String texto;
	
	private TipoStatus(String texto) {
		this.texto = texto;
	}
	
	public TipoStatus getProximo() {
		return proximo;
	}
	public TipoStatus getAnterior() {
		return anterior;
	}
	
	private void set(TipoStatus proximo, TipoStatus anterior){
		this.proximo = proximo;
		this.anterior = anterior;
	}
	
	public String getTexto() {
		return this.texto;
	}

	public static TipoStatus getTipoStatusInicial() {
		return AGUARDANDO_TRIAGEM;
	}
}