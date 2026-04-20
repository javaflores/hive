package br.com.bbts.hive.tasks;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bbts.hive.services.HiveService;
import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@Path("/task/shopee")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "TASKS HIVE - MOTOR DE BUSCA DADOS DOS CLIENTES")
public class TaskBuscarDadosClientesNaShopee {

	@Inject
	HiveService hiveService;
	
	@Inject
	@RestClient
	ExecutarTaskRestClient executarTaskRestClient;

	@Inject
	Logger logger;

	@GET
	@Path("/buscar/dados/clientes")
	@Operation(summary = "Busca os dados dos clientes na Shopee", description = "Busca com os dados dos clientes cadastrados na Shopee.")
	@Scheduled(every = "30s", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
	public void executeTask() throws Exception {

		logger.info("Iniciando Task para buscar os dados dos clientes da Shopee.");

		// Executando a API da shopee para retornar os dados dos clientes.
		var listaDadosRetorno = executarTaskRestClient.executarTaskShopee();
		
		// Criando json para exibir no log para visualização dos dados.
		// ObjectMapper mapper = new ObjectMapper();
		// String objetoJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listaDadosRetorno);
		// logger.info("Lista de dados retornados da Shopee: \n" + objetoJson);
		
		// Salva os dados retornados na shopee para dentro do hive.
		hiveService.salvarDadosClientesExternosDaShopee(listaDadosRetorno);
		
		logger.infof("Quantidade de itens gravados no hive: " + listaDadosRetorno.size());
	}
}
