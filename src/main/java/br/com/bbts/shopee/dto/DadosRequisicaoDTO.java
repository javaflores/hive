package br.com.bbts.shopee.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
public class DadosRequisicaoDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String nome;

	private String numeroDocumento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
}
