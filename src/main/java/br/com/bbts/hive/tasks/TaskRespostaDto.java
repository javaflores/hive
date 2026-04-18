package br.com.bbts.hive.tasks;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TaskRespostaDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String indicadorDeContinuidade;

	private BigDecimal proximoNumeroSolicitacao;

	private List<DadosClienteMercadoLivreDTO> listaClientesMercadoLivre = new ArrayList<DadosClienteMercadoLivreDTO>();

	public String getIndicadorDeContinuidade() {
		return indicadorDeContinuidade;
	}

	public void setIndicadorDeContinuidade(String indicadorDeContinuidade) {
		this.indicadorDeContinuidade = indicadorDeContinuidade;
	}

	public BigDecimal getProximoNumeroSolicitacao() {
		return proximoNumeroSolicitacao;
	}

	public void setProximoNumeroSolicitacao(BigDecimal proximoNumeroSolicitacao) {
		this.proximoNumeroSolicitacao = proximoNumeroSolicitacao;
	}

	public List<DadosClienteMercadoLivreDTO> getListaClientesMercadoLivre() {
		return listaClientesMercadoLivre;
	}

	public void setListaClientesMercadoLivre(List<DadosClienteMercadoLivreDTO> listaClientesMercadoLivre) {
		this.listaClientesMercadoLivre = listaClientesMercadoLivre;
	}
}
