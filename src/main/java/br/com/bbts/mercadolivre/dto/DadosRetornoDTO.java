package br.com.bbts.mercadolivre.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
public class DadosRetornoDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String indicadorDeContinuidade;

	private BigDecimal proximoNumeroSolicitacao;

	private List<ClienteMercadoLivreDTO> listaClientesMercadoLivre = new ArrayList<ClienteMercadoLivreDTO>();

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

	public List<ClienteMercadoLivreDTO> getListaClientesMercadoLivre() {
		return listaClientesMercadoLivre;
	}

	public void setListaClientesMercadoLivre(List<ClienteMercadoLivreDTO> listaClientesMercadoLivre) {
		this.listaClientesMercadoLivre = listaClientesMercadoLivre;
	}

}
