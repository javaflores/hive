package br.com.bbts.hive.tasks;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.bbts.hive.tasks.dto.DadosClienteShopeeDTO;
import br.com.bbts.hive.tasks.dto.DadosRetornoMercadoLivreDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@ApplicationScoped
@RegisterRestClient(configKey = "executar-task-host")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ExecutarTaskRestClient {
	
	@GET
	@Path("/shopee/buscar/novos/cadastros")
	List<DadosClienteShopeeDTO> executarTaskShopee();

	@GET
	@Path("/mercadolivre/dados/clientes/buscar/{numeroSolicitacaoSequencial}")
	DadosRetornoMercadoLivreDTO executarTaskMercadoLivre(@PathParam("numeroSolicitacaoSequencial") String numeroSolicitacaoSequencial);
}
