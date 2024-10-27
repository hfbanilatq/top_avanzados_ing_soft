package co.com.hfbanilat.tallerrest;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
public class NasaApiTest {

    private static final String BASE_URL = "https://api.nasa.gov";
    private static final String API_KEY;

    static {
        Dotenv dotenv = Dotenv.load();
        API_KEY = dotenv.get("NASA_API_KEY");
    }

    @Test
    public void testGetAstronomyPictureOfTheDay() {
        Response response = RestAssured.given()
                .queryParam("api_key", API_KEY)
                .get(BASE_URL + "/planetary/apod");

        Assertions.assertEquals(200, response.getStatusCode(), "No se pudo obtener la Imagen Astronómica del Día");
        System.out.println("APOD Response: " + response.asString());
    }

    @Test
    public void testGetSpecificDateAstronomyPicture() {
        String date = "2023-10-01";
        Response response = RestAssured.given()
                .queryParam("api_key", API_KEY)
                .queryParam("date", date)
                .get(BASE_URL + "/planetary/apod");

        Assertions.assertEquals(200, response.getStatusCode(), "No se pudo obtener la Imagen Astronómica de la fecha especificada");
        System.out.println("APOD Response for date " + date + ": " + response.asString());
    }

    @Test
    public void testGetMarsRoverPhotos() {
        int sol = 1000;
        Response response = RestAssured.given()
                .queryParam("api_key", API_KEY)
                .queryParam("sol", sol)
                .get(BASE_URL + "/mars-photos/api/v1/rovers/curiosity/photos");

        Assertions.assertEquals(200, response.getStatusCode(), "No se pudieron obtener las fotos del rover de Marte");
        System.out.println("Mars Rover Photos Response: " + response.asString());
    }
}
