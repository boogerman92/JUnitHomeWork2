package ru.netology.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

class MobileBankApiTestV1 {
    @Test
    void shouldReturnDemoAccounts() {
      // Given - When - Then
      // Предусловия
      given()
          .baseUri("http://localhost:9999/api/v1")
      // Выполняемые действия
      .when()
          .get("/demo/accounts")
      // Проверки
      .then()
          .statusCode(200)
              .contentType(ContentType.JSON)
              .header("Content-Type", "application/json; charset=UTF-8")
              .body("",hasSize(3))
              .body("[1].currency",equalTo("USD"))
              .body(" every { it.balance >=0 }",is(true))
              .body(matchesJsonSchemaInClasspath("accounts.schema.json"));

    }
}
