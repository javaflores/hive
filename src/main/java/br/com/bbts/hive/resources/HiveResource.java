package br.com.bbts.hive.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bbts.hive.services.HiveService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@Path("/hive")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "SERVICOS HIVE")
public class HiveResource {

	@Inject
	Logger logger;

	@Inject
	HiveService hiveService;

	@GET
	@Path("/listar/dados/cliente")
	@Operation(summary = "Lista os dados dos clientes do Mercado Livre e Shopee", description = "Lista todos os dados dos clientes buscados nos serviços externos do Mercado Livre e Shopee")
	public Response listarDadosClientes() throws Exception {

		logger.info("Início da listagem dos dados dos clientes da shopee.");

		var listaDadosClientes = hiveService.listarDadosClientesNoHive();

		// Criando json para exibir no log para visualização dos dados.
		ObjectMapper mapper = new ObjectMapper();
		String objetoJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listaDadosClientes);

		logger.info("Lista de dados retornadas: \n" + objetoJson);
		
		return Response.status(Status.OK).entity(listaDadosClientes).build();
	}
}
