package br.com.bbts.shopee.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.bbts.shopee.dao.ClienteDao;
import br.com.bbts.shopee.dto.DadosRequisicaoDTO;
import br.com.bbts.shopee.entidades.ClienteShopee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.datafaker.Faker;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@ApplicationScoped
public class DadosClientesService {

	// Feito assim só para exemplificar.
	private final static int TIPO_CPF = 1;
	private final static int TIPO_CNPJ = 2;
	private final static int TIPO_PASSAPORTE = 3;
	public static final String YYYY_DDD_HH_MM_SS_SSSS = "yyyyDDDHHmmssSSSS";

	@Inject
	ClienteDao clienteDao;

	public void salvarDadosClienteShopee(DadosRequisicaoDTO dadosDTO) throws Exception {

		// Preenche o objeto para persistencia.
		ClienteShopee clienteShopee = new ClienteShopee();
		clienteShopee.setNome(dadosDTO.getNome());
		clienteShopee.setNumeroDocumento(dadosDTO.getNumeroDocumento());
		clienteShopee.setCodigoTipoDocumento(recuperarTipoDocumento(dadosDTO.getNumeroDocumento()));
		clienteShopee.setTimestampAtualizacao(LocalDateTime.now());
		
		// Gera o numero de solicitação a ser utilizado na busca dos dados.
		clienteShopee.setNumeroSolicitacao(gerarNumeroSolicitacao());

		// Dados fake.
		Faker faker = new Faker();
		clienteShopee.setDataNascimento(faker.date().birthdayLocalDate());
		clienteShopee.setNomeMae(faker.name().femaleFirstName() + " " + faker.name().lastName());
		clienteShopee.setNomePai(faker.name().fullName());
		clienteShopee.setSexo(faker.gender().shortBinaryTypes().toUpperCase());

		// Salva os dados do cliente.
		clienteDao.incluirDadosClienteShopee(clienteShopee);
	}

	// Feito assim somente para testes do tipo de documento.
	private Integer recuperarTipoDocumento(String numeroDocumento) {

		// Busca pelo tamanho do numero do documento o seu tipo.
		if (StringUtils.length(numeroDocumento) < 5) {
			return TIPO_PASSAPORTE;
		} else if (StringUtils.length(numeroDocumento) < 12) {
			return TIPO_CPF;
		} else {
			return TIPO_CNPJ;
		}
	}

	public List<ClienteShopee> filtrarDadosClienteShopee(BigDecimal numeroSolicitacaoSequencial) throws Exception {
		return clienteDao.filtrarDadosClienteShopee(numeroSolicitacaoSequencial);
	}
	
	public BigDecimal gerarNumeroSolicitacao() {
		
		// Gera um numero de solicitacao com base no timestamp.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_DDD_HH_MM_SS_SSSS);
		return new BigDecimal(LocalDateTime.now().format(formatter));
	}

}
