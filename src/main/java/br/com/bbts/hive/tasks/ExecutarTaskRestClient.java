package br.com.bbts.hive.tasks;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@RegisterRestClient(configKey = "executar-task-host")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ExecutarTaskRestClient {

	@GET
	@Path("/mercadolivre/buscar/novos/cadastros")
	List<DadosClienteMercadoLivreDTO> executarTaskMercadoLivre();

	@GET
	@Path("/shopee/dados/clientes/buscar/{numeroSolicitacaoSequencial}")
	List<TaskRespostaDto> executarTaskShopee(@PathParam("numeroSolicitacaoSequencial") String numeroSolicitacaoSequencial);
}
