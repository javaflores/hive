package br.com.bbts.mercadolivre.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.bbts.mercadolivre.dao.ClienteDao;
import br.com.bbts.mercadolivre.dto.DadosRequisicaoDTO;
import br.com.bbts.mercadolivre.entidades.ClienteMercadoLivre;
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

	public void salvarDadosClienteMercadoLivre(DadosRequisicaoDTO dadosDTO) throws Exception {

		// Preenche o objeto para persistencia.
		ClienteMercadoLivre clienteMercadoLivre = new ClienteMercadoLivre();
		clienteMercadoLivre.setNome(dadosDTO.getNome());
		clienteMercadoLivre.setNumeroDocumento(dadosDTO.getNumeroDocumento());
		clienteMercadoLivre.setCodigoTipoDocumento(recuperarTipoDocumento(dadosDTO.getNumeroDocumento()));
		clienteMercadoLivre.setTimestampAtualizacao(LocalDateTime.now());
		
		// Gera o numero de solicitação a ser utilizado na busca dos dados.
		clienteMercadoLivre.setNumeroSolicitacao(gerarNumeroSolicitacao());

		// Dados fake.
		Faker faker = new Faker();
		clienteMercadoLivre.setDataNascimento(faker.date().birthdayLocalDate());
		clienteMercadoLivre.setNomeMae(faker.name().femaleFirstName() + " " + faker.name().lastName());
		clienteMercadoLivre.setNomePai(faker.name().fullName());
		clienteMercadoLivre.setSexo(faker.gender().shortBinaryTypes().toUpperCase());

		// Salva os dados do cliente.
		clienteDao.incluirDadosClienteMercadoLivre(clienteMercadoLivre);
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

	public List<ClienteMercadoLivre> filtrarDadosClienteMercadoLivre(BigDecimal numeroSolicitacaoSequencial) throws Exception {
		return clienteDao.filtrarDadosClienteMercadoLivre(numeroSolicitacaoSequencial);
	}
	
	public BigDecimal gerarNumeroSolicitacao() {
		
		// Gera um numero de solicitacao com base no timestamp.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_DDD_HH_MM_SS_SSSS);
		return new BigDecimal(LocalDateTime.now().format(formatter));
	}

}
