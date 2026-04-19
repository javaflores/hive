package br.com.bbts.mercadolivre.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;

@NamedNativeQuery(name = ClienteMercadoLivre.INCLUIR_DADOS_USUARIOS, 
		query = "INSERT INTO ClienteMercadoLivre (" +
			"numeroSolicitacao, nome, codigoTipoDocumento, numeroDocumento, timestampAtualizacao," +
			"dataNascimento, nomePai, nomeMae, sexo) " +	
			"VALUES (:numeroSolicitacao, :nome, :codigoTipoDocumento, :numeroDocumento, :timestampAtualizacao, " + 
			":dataNascimento, :nomePai, :nomeMae, :sexo)"
)

@NamedNativeQuery(name = ClienteMercadoLivre.FILTRAR_DADOS_USUARIOS, 
		query = "SELECT id, numeroSolicitacao, nome, codigoTipoDocumento, numeroDocumento, timestampAtualizacao, " +
			"dataNascimento, nomePai, nomeMae, sexo " +
			"FROM ClienteMercadoLivre " +
			"WHERE numeroSolicitacao > :numeroSolicitacao " +
			"ORDER BY numeroSolicitacao ASC " + 
			"FETCH FIRST 6 ROWS ONLY", 
		resultClass = ClienteMercadoLivre.class
)

@Entity
@Table(name = "ClienteMercadoLivre")
@Immutable
public class ClienteMercadoLivre extends PanacheEntityBase {

	public static final String INCLUIR_DADOS_USUARIOS = "ClienteMercadoLivre.INCLUIR_DADOS_USUARIOS";
	public static final String FILTRAR_DADOS_USUARIOS = "ClienteMercadoLivre.FILTRAR_DADOS_USUARIOS";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "numeroSolicitacao")
	private BigDecimal numeroSolicitacao;

	@Column(name = "nome")
	private String nome;

	@Column(name = "codigoTipoDocumento")
	private Integer codigoTipoDocumento;

	@Column(name = "numeroDocumento")
	private String numeroDocumento;

	@Column(name = "timestampAtualizacao")
	private LocalDateTime timestampAtualizacao;

	@Column(name = "dataNascimento")
	private LocalDate dataNascimento;

	@Column(name = "nomePai")
	private String nomePai;

	@Column(name = "nomeMae")
	private String nomeMae;

	@Column(name = "sexo")
	private String sexo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getNumeroSolicitacao() {
		return numeroSolicitacao;
	}

	public void setNumeroSolicitacao(BigDecimal numeroSolicitacao) {
		this.numeroSolicitacao = numeroSolicitacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
