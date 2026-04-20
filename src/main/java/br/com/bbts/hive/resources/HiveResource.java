package br.com.bbts.hive.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import br.com.bbts.hive.services.HiveService;
import br.com.bbts.hive.utils.HiveUtils;
import br.com.bbts.hive.utils.TipoEmpresaEnum;
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
	@Operation(summary = "Lista os dados dos clientes do Mercado Livre e Shopee gravados no hive de forma padronizados", description = "Lista todos os dados dos clientes buscados nos serviços externos do Mercado Livre e Shopee")
	public Response listarDadosClientes() throws Exception {

		logger.info("Início da listagem dos dados dos clientes da shopee.");

		// Buscando os dados do hive.
		var listaDadosClientes = hiveService.listarDadosClientesNoHive();

		// Printa os dados contigos no hive.
		HiveUtils.printarDadosClientesComoTabela(listaDadosClientes);

		logger.info("Lista de dados retornadas: \n" + listaDadosClientes);

		return Response.status(Status.OK).entity(listaDadosClientes).build();
	}
	
	@GET
	@Path("/listar/dados/cliente/mercadolivre")
	@Operation(summary = "Lista os dados dos clientes do Mercado Livre gravados no hive de forma padronizados", description = "Lista todos os dados dos clientes buscados no serviço externo do Mercado Livre")
	public Response listarDadosClientesDoMercadoLivre() throws Exception {

		logger.info("Início da listagem dos dados dos clientes do Mercado Livre.");

		// Buscando os dados do mercado livre no hive.
		var listaDadosClientes = hiveService.listarDadosClientesNoHivePorTipoEmpresa(TipoEmpresaEnum.MERCADO_LIVRE);

		// Printa os dados contigos no hive.
		HiveUtils.printarDadosClientesComoTabela(listaDadosClientes);

		logger.info("Lista de dados retornadas: \n" + listaDadosClientes);

		return Response.status(Status.OK).entity(listaDadosClientes).build();
	}
	
	@GET
	@Path("/listar/dados/cliente/shopee")
	@Operation(summary = "Lista os dados dos clientes da Shopee gravados no hive de forma padronizados", description = "Lista todos os dados dos clientes buscados no serviço externo da Shopee")
	public Response listarDadosClientesDaShopee() throws Exception {

		logger.info("Início da listagem dos dados dos clientes da shopee.");

		// Buscando os dados da Shopee no hive.
		var listaDadosClientes = hiveService.listarDadosClientesNoHivePorTipoEmpresa(TipoEmpresaEnum.SHOPEE);

		// Printa os dados contigos no hive.
		HiveUtils.printarDadosClientesComoTabela(listaDadosClientes);

		logger.info("Lista de dados retornadas: \n" + listaDadosClientes);

		return Response.status(Status.OK).entity(listaDadosClientes).build();
	}

}
