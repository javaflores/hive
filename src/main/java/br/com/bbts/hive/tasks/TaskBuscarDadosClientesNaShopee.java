package br.com.bbts.hive.tasks;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

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
public class TaskBuscarDadosClientesNaShopee {

	@Inject
	@RestClient
	ExecutarTaskRestClient executarTaskRestClient;

	@Inject
	Logger logger;

	@GET
	@Path("/buscar/dados/clientes")
	@Tag(name = "TASKS HIVE - BUSCAR DADOS DOS CLIENTES NA SHOPEE")
	@Operation(summary = "Busca os dados dos clientes na shopee", description = "Busca com os dados dos clientes cadastrados na shopee.")
	//@Scheduled(every = "10s", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
	public void executeTask() throws Exception {
		logger.info("Task para buscar os dados dos clientes na shopee.");

		// Executando a API da Shopee para retornar os dados dos clientes.
		var listaDadosRetorno = executarTaskRestClient.executarTaskShopee("1");

		// Criando json para exibir no log para visualização dos dados.
		ObjectMapper mapper = new ObjectMapper();
		String objetoJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listaDadosRetorno);

		logger.info("Lista de dados retornados da shopee: \n" + objetoJson);
	}

}
