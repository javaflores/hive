package br.com.bbts.shopee.resources;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import br.com.bbts.shopee.dto.ClienteShopeeDTO;
import br.com.bbts.shopee.dto.DadosRequisicaoDTO;
import br.com.bbts.shopee.dto.DadosRetornoDTO;
import br.com.bbts.shopee.entidades.ClienteShopee;
import br.com.bbts.shopee.services.DadosClientesService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@Path("/shopee/dados/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "SERVICOS EXTERNOS")
public class ListarDadosClientesResource {

	private static final int QTD_MAX_LISTA = 5;

	@Inject
	Logger logger;

	@Inject
	DadosClientesService dadosClientesService;

	@POST
	@Path("/gerar")
	@Transactional(rollbackOn = Exception.class)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "[SHOPEE] - Cadastra os dados do cliente informando o nome e documento.", description = "Cadastra os dados do cliente a ser cadastrado na shopee.")
	public Response gerarDadosUsuario(DadosRequisicaoDTO dadosClienteDTO) throws Exception {

		// Salva uma publicacao completa.
		dadosClientesService.salvarDadosClienteShopee(dadosClienteDTO);

		// Retorna os dados do usuario com seu ID.
		return Response.status(Status.OK).entity("OK").build();
	}

	@GET
	@Path("/buscar/{numeroSolicitacaoSequencial}")
	@Operation(summary = "[SHOPEE] - Busca os dados dos clientes a partir do número de solicitação", description = "Busca com os dados dos clientes cadastrados na shopee pelo numero de solicitação informado.")
	public Response listarDadosClientes(
			@PathParam("numeroSolicitacaoSequencial") BigDecimal numeroSolicitacaoSequencial) throws Exception {

		logger.info("Início da listagem dos dados dos clientes da shopee.");

		// Montando o objeto de retorno.
		DadosRetornoDTO retorno = new DadosRetornoDTO();
		retorno.setIndicadorDeContinuidade("N");
		retorno.setProximoNumeroSolicitacao(BigDecimal.ZERO);

		// Busca os dados dos clientes com base no ultimo
		// numero de solicitacao informado.
		var listaDadosFiltrados = dadosClientesService.filtrarDadosClienteShopee(numeroSolicitacaoSequencial);

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
			var cliente = new ClienteShopeeDTO();
			cliente.setNumeroSolicitacao(dadoUsuario.getNumeroSolicitacao());
			cliente.setCodigoTipoDocumento(dadoUsuario.getCodigoTipoDocumento());
			cliente.setNumeroDocumento(dadoUsuario.getNumeroDocumento());
			cliente.setNome(dadoUsuario.getNome());
			cliente.setTimestampAtualizacao(dadoUsuario.getTimestampAtualizacao());
			cliente.setNomeMae(dadoUsuario.getNomeMae());
			cliente.setNomePai(dadoUsuario.getNomePai());
			cliente.setSexo(dadoUsuario.getSexo());
			cliente.setDataNascimento(dadoUsuario.getDataNascimento());
			retorno.getListaClientesShopee().add(cliente);
		});

		logger.info("Lista de dados retornadas: " + retorno.getListaClientesShopee().size());

		return Response.status(Status.OK).entity(retorno).build();
	}

	private boolean verificarSeTemRechamada(List<ClienteShopee> listaDadosFiltrados) {

		// Se a quantidade que veio do banco de dados é maior que 5, então
		// tem proximos para serem chamados.
		if (listaDadosFiltrados.size() > QTD_MAX_LISTA) {
			return true;
		}

		return false;

	}

}
