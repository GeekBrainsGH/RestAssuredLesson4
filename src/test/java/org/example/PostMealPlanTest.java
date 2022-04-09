package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostMealPlanTest {


    private final String hash = "d2a99c96d86d52173b095fbe8ef15cf7cd675646";
    private final String username = "your-users-name310";
    private final String apiKey = "7cd6183314c94478924f5298a2e54b8f";
    private String id;

    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterEach
    void tearDown() {
        given()
                .queryParam("hash", hash)
                .queryParam("apiKey", apiKey)
                .when()
                .delete("https://api.spoonacular.com/mealplanner/" + username + "/shopping-list/items/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    void postAddToShoppingListTest() {
        id = given()
                .queryParam("hash", hash)
                .queryParam("username", username)
                .queryParam("apiKey", apiKey)
                .body("{\n"
                        + " \"item\": 15 carrots,\n"
                        + " \"aisle\": vegetables,\n"
                        + " \"parse\": true\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/"+ username + "/shopping-list/items")
                .then()
                .statusCode(200)
                .body("aisle", equalTo("vegetables"))
                .body("name", equalTo("carrots"))
                .extract()
                .jsonPath()
                .get("id")
                .toString();
    }
}
