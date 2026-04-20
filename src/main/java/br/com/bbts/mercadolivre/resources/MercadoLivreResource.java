package br.com.bbts.mercadolivre.resources;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import br.com.bbts.mercadolivre.dto.ClienteMercadoLivreDTO;
import br.com.bbts.mercadolivre.dto.DadosRetornoDTO;
import br.com.bbts.mercadolivre.entidades.ClienteMercadoLivre;
import br.com.bbts.mercadolivre.services.DadosClientesService;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@Path("/mercadolivre/dados/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "SERVICOS EXTERNOS")
public class MercadoLivreResource {

	// Máximo de itens a ser retornado por execução para simular a rechamada.
	private static final int QTD_MAX_LISTA = 5;

	@Inject
	Logger logger;

	@Inject
	DadosClientesService dadosClientesService;
	
	// Implementação para iniciar e executar a geração dos dados.
    void onStart(@Observes StartupEvent ev) throws Exception {               
        System.out.println("Iniciando a criação de dados mocados...");
        
        // Executa o método para gerar 20 dados aleatórios.
        gerarDadosUsuario();
    }

	@GET
	@Path("/gerar")
	@Transactional(rollbackOn = Exception.class)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "[MERCADO LIVRE] - Gera 20 dados de cliente aleatórios.", description = "Cadastra os dados dos clientes de forma aleatória no Mercado Livre.")
	public Response gerarDadosUsuario() throws Exception {

		// Percorre e cadastra os dados de forma aleatória para testes.
		for (int i = 0; i < 20; i++) {
			
			// Salva uma publicacao completa.
			dadosClientesService.salvarDadosClienteMercadoLivre();
		}

		// Retorna os dados do usuario com seu ID.
		return Response.status(Status.OK).entity("OK").build();
	}

	@GET
	@Path("/buscar/{numeroSolicitacaoSequencial}")
	@Operation(summary = "[MERCADO LIVRE] - Busca os dados dos clientes a partir do número de solicitação [Retorna 5 por vez]", description = "Busca com os dados dos clientes cadastrados no Mercado Livre pelo numero de solicitação informado.")
	public Response listarDadosClientes(
			@PathParam("numeroSolicitacaoSequencial") BigDecimal numeroSolicitacaoSequencial) throws Exception {

		logger.info("Início da listagem dos dados dos clientes do Mercado Livre.");
		
		logger.info("Buscando os próximos dados com o numero de solicitação: " + numeroSolicitacaoSequencial);

		// Montando o objeto de retorno.
		DadosRetornoDTO retorno = new DadosRetornoDTO();
		retorno.setIndicadorDeContinuidade("N");
		retorno.setProximoNumeroSolicitacao(BigDecimal.ZERO);

		// Busca os dados dos clientes com base no ultimo
		// numero de solicitacao informado.
		var listaDadosFiltrados = dadosClientesService.filtrarDadosClienteMercadoLivre(numeroSolicitacaoSequencial);

		// Verifica pela quantidade da lista se tem rechamada.
		boolean temRechamada = verificarSeTemRechamada(listaDadosFiltrados);

		// Se existir rechamada, então retornar na resposta.
		if (temRechamada) {
			
			// Remove o ultimo da lista já que veio 1 a mais.
			listaDadosFiltrados.removeLast();
			
			// Coloca o indicador de continuidade.
			retorno.setIndicadorDeContinuidade("S");
			
			// Pega o numero de solicitacao do ultimo para ser utilizado na rechamada.
			retorno.setProximoNumeroSolicitacao(listaDadosFiltrados.getLast().getNumeroSolicitacao());
		}

		// Preenchendo a resposta.
		listaDadosFiltrados.forEach(dadoUsuario -> {
			var cliente = new ClienteMercadoLivreDTO();
			cliente.setNumeroSolicitacao(dadoUsuario.getNumeroSolicitacao());
			cliente.setCodigoTipoDocumento(dadoUsuario.getCodigoTipoDocumento());
			cliente.setNumeroDocumento(dadoUsuario.getNumeroDocumento());
			cliente.setNome(dadoUsuario.getNome());
			cliente.setTimestampAtualizacao(dadoUsuario.getTimestampAtualizacao());
			cliente.setNomeMae(dadoUsuario.getNomeMae());
			cliente.setNomePai(dadoUsuario.getNomePai());
			cliente.setSexo(dadoUsuario.getSexo());
			cliente.setDataNascimento(dadoUsuario.getDataNascimento());
			retorno.getListaClientesMercadoLivre().add(cliente);
		});

		logger.info("Lista de dados retornadas: " + retorno.getListaClientesMercadoLivre().size());

		return Response.status(Status.OK).entity(retorno).build();
	}

	private boolean verificarSeTemRechamada(List<ClienteMercadoLivre> listaDadosFiltrados) {

		// Se a quantidade que veio do banco de dados é maior que 5, então
		// tem proximos para serem chamados.
		if (listaDadosFiltrados.size() > QTD_MAX_LISTA) {
			return true;
		}

		return false;
	}
}
