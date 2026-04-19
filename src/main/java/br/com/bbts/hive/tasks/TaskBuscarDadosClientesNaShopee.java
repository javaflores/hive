package br.com.bbts.hive.tasks;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

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
	@Tag(name = "TASKS HIVE - BUSCAR DADOS DOS CLIENTES NA SHOPEE")
	@Operation(summary = "Busca os dados dos clientes na shopee", description = "Busca com os dados dos clientes cadastrados na shopee.")
	@Scheduled(every = "10s", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
	public void executeTask() throws Exception {

		logger.info("Iniciando Task para buscar os dados dos clientes da shopee.");

		// Executando a API da shopee para retornar os dados dos clientes.
		var listaDadosRetorno = executarTaskRestClient.executarTaskShopee();

		// Criando json para exibir no log para visualização dos dados.
//		ObjectMapper mapper = new ObjectMapper();
//		String objetoJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listaDadosRetorno);
//		logger.info("Lista de dados retornados da shopee: \n" + objetoJson);
		
		hiveService.salvarDadosClientesExternosDaShopee(listaDadosRetorno);
		
		var listaClientesShopee = hiveService.listarClientesExternosShopee();
		listaClientesShopee.forEach(cliente -> {
			logger.info("Cliente da shopee no hive: " + cliente.getNomeCliente());
		});
		
		logger.infof("Quantidade de itens gravados no hive: " + listaClientesShopee.size());
	}
}
