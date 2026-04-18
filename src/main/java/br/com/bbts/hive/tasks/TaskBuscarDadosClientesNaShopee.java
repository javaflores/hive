package br.com.bbts.hive.tasks;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

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
	Logger logger;

	@GET
	@Path("/buscar/dados/clientes")
	@Tag(name = "TASKS HIVE - BUSCAR DADOS DOS CLIENTES NA SHOPEE")
	@Operation(summary = "Busca os dados dos clientes na shopee", description = "Busca com os dados dos clientes cadastrados na shopee.")
	// @Scheduled(every = "10s", concurrentExecution =
	// Scheduled.ConcurrentExecution.SKIP)
	public void executeTask() throws Exception {
		logger.info("Task para buscar os dados dos clientes na shopee.");
	}

}
