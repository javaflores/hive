package br.com.bbts;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@QuarkusTest
class MercadoLivreResourceTest {
	
	@Test
	void testGerarDadosUsuarioComSucesso() {
		
        given()
	        .contentType(ContentType.JSON)
	        .when()
	        .get("/mercadolivre/dados/clientes/gerar")
	        .then()
	        .log().body()
	        .statusCode(200)
	        .body("nome", anything())
	        .body("sexo", hasItems("M", "F"));
	}
	
	@Test
	void testListarDadosClientesComRechamadaComSucesso() {
		
        given()
	        .contentType(ContentType.JSON)
	        .when()
	        .get("/mercadolivre/dados/clientes/buscar/1")
	        .then()
	        .log().body()
	        .statusCode(200)
	        .body("indicadorDeContinuidade", is("S"))
	        .body("proximoNumeroSolicitacao", notNullValue())
	        .body("listaClientesMercadoLivre[0].nome", is("João da Silva"))
	        .body("listaClientesMercadoLivre[0].timestampAtualizacao", is("2026-04-20T13:37:47.013348"))
	        .body("listaClientesMercadoLivre[0].sexo", is("M"))
	        .body("listaClientesMercadoLivre[1].nome", is("Ana Souza Pereira"))
	        .body("listaClientesMercadoLivre[1].timestampAtualizacao", is("2026-04-20T13:38:11.153412"))
	        .body("listaClientesMercadoLivre[1].sexo", is("F"));
	}
	
	@Test
	void testListarDadosClientesNaoEncontradoComSucesso() {
		
        given()
	        .contentType(ContentType.JSON)
	        .when()
	        .get("/mercadolivre/dados/clientes/buscar/20291101605186789") // Numero não tem na base
	        .then()
	        .log().body()
	        .statusCode(200)
	        .body("indicadorDeContinuidade", is("N"))
	        .body("proximoNumeroSolicitacao", is(0))
	        .body("listaClientesMercadoLivre", anything());
	}
	
}