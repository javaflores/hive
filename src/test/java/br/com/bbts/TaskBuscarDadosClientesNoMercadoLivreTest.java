package br.com.bbts;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import br.com.bbts.hive.tasks.TaskBuscarDadosClientesNoMercadoLivre;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@QuarkusTest
class TaskBuscarDadosClientesNoMercadoLivreTest {

	@Inject
	TaskBuscarDadosClientesNoMercadoLivre taskBuscarDadosClientesNoMercadoLivre;

	@Test
	void executeTask() {
		assertDoesNotThrow(() -> taskBuscarDadosClientesNoMercadoLivre.executeTask());
	}

}