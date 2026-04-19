package br.com.bbts.hive.tasks.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
public class DadosRetornoShopeeDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String indicadorDeContinuidade;

	private BigDecimal proximoNumeroSolicitacao;

	private List<DadosClienteShopeeDTO> listaClientesShopee;

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

	public List<DadosClienteShopeeDTO> getListaClientesShopee() {
		if (listaClientesShopee == null) {
			listaClientesShopee = new ArrayList<DadosClienteShopeeDTO>();
		}
		return listaClientesShopee;
	}

	public void setListaClientesShopee(List<DadosClienteShopeeDTO> listaClientesShopee) {
		this.listaClientesShopee = listaClientesShopee;
	}

}
