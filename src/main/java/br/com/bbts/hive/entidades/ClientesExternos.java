package br.com.bbts.hive.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Immutable;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTES_EXTERNOS")
@Immutable

// FIXME: Falta configurar o lombok.
//@EqualsAndHashCode(callSuper = false)
//@Getter
//@Setter
//@ToString
//@Builder(toBuilder = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class ClientesExternos extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NR_SEQUENCIAL_EXTERNO")
	private BigDecimal numeroSequencialExterno;

	@Column(name = "CD_EMPRESA_EXTERNO")
	private Integer codigoEmpresaExterno;

	@Column(name = "NM_EMPRESA_EXTERNO")
	private String nomeEmpresaExterno;

	@Column(name = "DS_NOME")
	private String nomeCliente;

	@Column(name = "CD_TIPO_DOCUMENTO")
	private Integer codigoTipoDocumento;

	@Column(name = "NR_DOCUMENTO")
	private String numeroDocumento;

	@Column(name = "DT_NASCIMENTO")
	private LocalDate dataNascimento;

	@Column(name = "NM_PAI")
	private String nomePai;

	@Column(name = "NM_MAE")
	private String nomeMae;

	@Column(name = "TP_SEXO")
	private String tipoSexo;

	@Column(name = "TS_ULT_ATUALIZACAO")
	private LocalDateTime timestampUltimaAtualizacao;

	public static Uni<Void> incluir(List<ClientesExternos> clientesExternos) {
		return Uni.createFrom().voidItem().invoke(() -> ClientesExternos.persist(clientesExternos.stream().toList()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getNumeroSequencialExterno() {
		return numeroSequencialExterno;
	}

	public void setNumeroSequencialExterno(BigDecimal numeroSequencialExterno) {
		this.numeroSequencialExterno = numeroSequencialExterno;
	}

	public Integer getCodigoEmpresaExterno() {
		return codigoEmpresaExterno;
	}

	public void setCodigoEmpresaExterno(Integer codigoEmpresaExterno) {
		this.codigoEmpresaExterno = codigoEmpresaExterno;
	}

	public String getNomeEmpresaExterno() {
		return nomeEmpresaExterno;
	}

	public void setNomeEmpresaExterno(String nomeEmpresaExterno) {
		this.nomeEmpresaExterno = nomeEmpresaExterno;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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

	public String getTipoSexo() {
		return tipoSexo;
	}

	public void setTipoSexo(String tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	public LocalDateTime getTimestampUltimaAtualizacao() {
		return timestampUltimaAtualizacao;
	}

	public void setTimestampUltimaAtualizacao(LocalDateTime timestampUltimaAtualizacao) {
		this.timestampUltimaAtualizacao = timestampUltimaAtualizacao;
	}

}
