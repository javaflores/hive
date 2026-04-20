package br.com.bbts;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.anything;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@QuarkusTest
class ShopeeResourceTest {
	
	@Test
	void testBuscarNovosClientesComSucesso() {
		
        given()
	        .contentType(ContentType.JSON)
	        .when()
	        .get("/shopee/buscar/novos/cadastros")
	        .then()
	        .log().body()
	        .statusCode(200)
	        .body("name", anything())
	        .body("genderType", hasItems(1, 2));
	}
	
}