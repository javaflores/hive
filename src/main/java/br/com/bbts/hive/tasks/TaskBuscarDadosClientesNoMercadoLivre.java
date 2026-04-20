package br.com.bbts.hive.tasks;

import java.math.BigDecimal;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import br.com.bbts.hive.services.HiveService;
import br.com.bbts.hive.utils.HiveUtils;
import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@Path("/task/mercadolivre")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "TASKS HIVE - MOTOR DE BUSCA DADOS DOS CLIENTES")
public class TaskBuscarDadosClientesNoMercadoLivre {
	
	@Inject
	HiveService hiveService;

	@Inject
	@RestClient
	ExecutarTaskRestClient executarTaskRestClient;

	@Inject
	Logger logger;

	@GET
	@Path("/buscar/dados/clientes")
	@Operation(summary = "Busca os dados dos clientes no mercado livre de forma automatica a cada 10 segundos.", description = "Busca com os dados dos clientes cadastrados no mercado livre.")
	@Scheduled(every = "10s", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
	public void executeTask() throws Exception {

		logger.info("Task para buscar os dados dos clientes no mercado livre.");
		
		// Busca o ultimo numero de solicitacao cadastrado no hive para utilizar na rechamada do API Externo.
		BigDecimal proximoNumeroSolicitacao = hiveService.recuperarUltimaSolicitacaoDadosClienteMercadoLivre();
		
		logger.info("Executando o API com o numero de solicitação: " + proximoNumeroSolicitacao);

		// Executando a API da do Mercado Livre para retornar os dados dos clientes.
		var dadosRetorno = executarTaskRestClient.executarTaskMercadoLivre(String.valueOf(proximoNumeroSolicitacao));

		// Criando json para exibir no log para visualização dos dados.
		// ObjectMapper mapper = new ObjectMapper();
		// String objetoJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dadosRetorno);
		// logger.info("Lista de dados retornados do mercado livre: \n" + objetoJson);
		
		// Senao estiver vazio, não faz nada.
		if (!dadosRetorno.getListaClientesMercadoLivre().isEmpty()) {
			// Salva os dados retornados na shopee para dentro do hive.
			hiveService.salvarDadosClientesExternosDoMercadoLivre(dadosRetorno);
		}
		
		// Printa os dados somente para acompanhamento no log.
		printarDadosTabela();
		
		logger.infof("Quantidade de itens gravados no hive: " + dadosRetorno.getListaClientesMercadoLivre().size());
	}

	private void printarDadosTabela() throws Exception {
		// Busca os dados no hive e printa os dados no console.
		HiveUtils.printarDadosClientesComoTabela(hiveService.listarDadosClientesNoHive());
	}
}
