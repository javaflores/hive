package br.com.bbts.mercadolivre.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
public class ClienteMercadoLivreDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String nome;

	private BigDecimal numeroSolicitacao;

	private Integer codigoTipoDocumento;

	private String numeroDocumento;

	private LocalDateTime timestampAtualizacao;

	private LocalDate dataNascimento;

	private String nomePai;

	private String nomeMae;

	private String sexo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getNumeroSolicitacao() {
		return numeroSolicitacao;
	}

	public void setNumeroSolicitacao(BigDecimal numeroSolicitacao) {
		this.numeroSolicitacao = numeroSolicitacao;
	}

	public Integer getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	public void setCodigoTipoDocumento(Integer codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public LocalDateTime getTimestampAtualizacao() {
		return timestampAtualizacao;
	}

	public void setTimestampAtualizacao(LocalDateTime timestampAtualizacao) {
		this.timestampAtualizacao = timestampAtualizacao;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
