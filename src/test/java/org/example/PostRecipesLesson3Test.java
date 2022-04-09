package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostRecipesLesson3Test {

    private final String apiKey = "7cd6183314c94478924f5298a2e54b8f";


    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void CuisineEuropeanPositiveTest() {
        JsonPath response = given()
                .when()
                .formParam("title", "Strawberries and Cream Scones")
                .post("https://api.spoonacular.com/recipes/cuisine?" + "apiKey=" + apiKey)
                .body()
                .jsonPath();
        assertThat(response.get("cuisines"), hasItems("European", "Scottish", "British", "English"));
        assertThat(response.get("cuisine"), equalTo("European"));}

    @Test
    void CuisineAfricanPositiveTest() {
        JsonPath response = given()
                .when()
                .formParam("title", "African Chicken Peanut Stew")
                .post("https://api.spoonacular.com/recipes/cuisine?" + "apiKey=" + apiKey)
                .body()
                .jsonPath();
        assertThat(response.get("cuisines"), hasItem("African"));
        assertThat(response.get("cuisine"), equalTo("African"));}

    @Test
    void CuisineScandinavianPositiveTest() {
        JsonPath response = given()
                .when()
                .formParam("title", "Baked Swedish Pancake")
                .post("https://api.spoonacular.com/recipes/cuisine?" + "apiKey=" + apiKey)
                .body()
                .jsonPath();
        assertThat(response.get("cuisines"), hasItems("Scandinavian", "European", "Nordic"));
        assertThat(response.get("cuisine"), equalTo("Scandinavian"));}

    @Test
    void CuisineJapanesePositiveTest() {
        JsonPath response = given()
                .when()
                .formParam("title", "Miso Soup With Thin Noodles")
                .post("https://api.spoonacular.com/recipes/cuisine?" + "apiKey=" + apiKey)
                .body()
                .jsonPath();
        assertThat(response.get("cuisines"), hasItems("Japanese", "Asian"));
        assertThat(response.get("cuisine"), equalTo("Japanese"));}

    @Test
    void CuisineCreolePositiveTest() {
        JsonPath response = given()
                .when()
                .formParam("title", "Red Kidney Bean Jambalaya")
                .post("https://api.spoonacular.com/recipes/cuisine?" + "apiKey=" + apiKey)
                .body()
                .jsonPath();
        assertThat(response.get("cuisines"), hasItems("Creole","Cajun"));
        assertThat(response.get("cuisine"), equalTo("Creole"));}


}


