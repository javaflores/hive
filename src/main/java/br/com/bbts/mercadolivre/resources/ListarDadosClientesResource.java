package br.com.bbts.mercadolivre.resources;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import net.datafaker.Faker;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@Path("/mercadolivre/buscar")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "SERVICOS EXTERNOS")
public class ListarDadosClientesResource {

	@Inject
	Logger logger;

	@GET
	@Path("/novos/cadastros")
	@Operation(summary = "[MERCADO LIVRE] - Busca os dados dos novos cadatros [Dados Mockado]", description = "Busca com os dados dos clientes cadastrados no mercado livre.")
	public Response buscarNovosClientes() throws Exception {

		logger.info("Início da listagem dos dados dos clientes do mercado livre.");

		// Montando o objeto de retorno.
		List<ClienteMercadoLivreDTO> listaRetorno = new ArrayList<ClienteMercadoLivreDTO>();

		// Preenchendo a lista mockada.
		preencherRetornoDadosMercadoLivre(listaRetorno);

		// Criando json para exibir no log para visualização dos dados.
		ObjectMapper mapper = new ObjectMapper();
		String objetoJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listaRetorno);

		logger.info("Lista de dados retornadas do Mercado Livre: \n" + objetoJson);

		return Response.status(Status.OK).entity(listaRetorno).build();
	}

	private void preencherRetornoDadosMercadoLivre(List<ClienteMercadoLivreDTO> listaRetorno) {

		// Criando uma resposta com 3 itens.
		for (int i = 0; i < 3; i++) {
			var cliente = new ClienteMercadoLivreDTO();
			cliente.setGenderType(ThreadLocalRandom.current().nextInt(1, 3));
			cliente.setPassportNumber("US" + String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000)));
			cliente.setSocialSecurityNumber(ThreadLocalRandom.current().nextInt(10000, 1000000));

			// Dados fake.
			Faker faker = new Faker();
			cliente.setName(faker.name().malefirstName() + " " + faker.name().name());
			cliente.setLastName(faker.name().lastName());
			cliente.setMotherFullName(faker.name().femaleFirstName() + " " + faker.name().lastName());
			cliente.setFatherFullName(faker.name().fullName());

			// Montando a data de nascimento no formato americano MM/dd/yyyy.
			var dataNascimento = LocalDate.now().minusDays(ThreadLocalRandom.current().nextInt(1000, 10000));
			StringBuilder dataFormatada = new StringBuilder();
			dataFormatada.append(dataNascimento.getMonthValue() + "/");
			dataFormatada.append(dataNascimento.getDayOfMonth() + "/");
			dataFormatada.append(dataNascimento.getYear());
			cliente.setBirthDate(dataFormatada.toString());

			// Idade
			int age = Period.between(dataNascimento, LocalDate.now()).getYears();
			cliente.setAge(age);

			listaRetorno.add(cliente);
		}
	}
}
