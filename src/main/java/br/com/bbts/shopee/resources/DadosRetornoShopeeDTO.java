package br.com.bbts.shopee.resources;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
public class DadosRetornoShopeeDTO {

	private String indicadorDeContinuidade;

	private BigDecimal proximoNumeroSolicitacao;

	private List<ClienteShopeeDTO> listaClientesShopee = new ArrayList<ClienteShopeeDTO>();

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

	public List<ClienteShopeeDTO> getListaClientesShopee() {
		return listaClientesShopee;
	}

	public void setListaClientesShopee(List<ClienteShopeeDTO> listaClientesShopee) {
		this.listaClientesShopee = listaClientesShopee;
	}

}
