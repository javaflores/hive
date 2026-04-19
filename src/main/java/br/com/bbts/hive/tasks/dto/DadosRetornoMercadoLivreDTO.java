package br.com.bbts.hive.tasks.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
public class DadosRetornoMercadoLivreDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String indicadorDeContinuidade;

	private BigDecimal proximoNumeroSolicitacao;

	private List<DadosClienteMercadoLivreDTO> listaClientesMercadoLivre;

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
		if (listaClientesMercadoLivre == null) {
			listaClientesMercadoLivre = new ArrayList<DadosClienteMercadoLivreDTO>();
		}
		return listaClientesMercadoLivre;
	}

	public void setListaClientesMercadoLivre(List<DadosClienteMercadoLivreDTO> listaClientesMercadoLivre) {
		this.listaClientesMercadoLivre = listaClientesMercadoLivre;
	}

}
