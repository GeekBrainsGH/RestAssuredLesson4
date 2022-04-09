package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteMealPlanTest {

    private final String hash = "d2a99c96d86d52173b095fbe8ef15cf7cd675646";
    private final String username = "your-users-name310";
    private final String apiKey = "7cd6183314c94478924f5298a2e54b8f";
    private String id;

    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeEach
    void setUpEach() {
        id = given()
                .queryParam("hash", hash)
                .queryParam("username", username)
                .queryParam("apiKey", apiKey)
                .body("{\n"
                        + " \"item\": 3 apples,\n"
                        + " \"aisle\": fruits,\n"
                        + " \"parse\": true\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/"+ username + "/shopping-list/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
    }

    @Test
    void deleteShoppingListTest() {
        given()
                .queryParam("hash", hash)
                .queryParam("apiKey", apiKey)
                .expect()
                .body("status", equalTo("success"))
                .when()
                .delete("https://api.spoonacular.com/mealplanner/" + username + "/shopping-list/items/" + id)
                .then()
                .statusCode(200);
    }


}
