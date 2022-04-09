package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetRecipesLesson3Test {

    private final String apiKey = "7cd6183314c94478924f5298a2e54b8f";


    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void CuisinePositiveTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("cuisine", "British")
                .queryParam("addRecipeInformation", "true")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        for (int i = 0; i < 10; i++) {
            assertThat(response.get("results[" + i + "].cuisines"), hasItem("British"));
        }
    }

    @Test
    void NumberPositiveTest() {
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("number", "1")
                .expect()
                .body("number", equalTo(1))
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void SortMaxTimePositiveTest() {
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("sort", "time")
                .queryParam("addRecipeInformation", "true")
                .expect()
                .body("results[0].readyInMinutes", equalTo(4320))
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void DietPositiveTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("addRecipeInformation", "true")
                .queryParam("diet","primal")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        for (int i = 0; i < 10; i++) {
            assertThat(response.get("results[" + i + "].diets"), hasItem("primal"));
        }
    }

    @Test
    void WrongTypeNegativeTest() {
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("maxReadyTime", "vyjuj")
                .queryParam("addRecipeInformation", "true")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(404);
    }
    }



