package co.com.hfbanilat.tallerrest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

public class BookApiRestTest {

    private static final String BASE_URL = "https://demoqa.com";

    @Test
    public void testCreateAuthenticateAndDeleteUser() {
        String username = "banilat";
        String password = "Password123!";

        String userId = createUser(username, password);

        String token = authenticateUser(username, password);
        Assertions.assertNotNull(token, "No se pudo autenticar el usuario");

        deleteUser(userId, token);

        String failedToken = authenticateUser(username, password);
        Assertions.assertNull(failedToken, "El usuario aún se puede autenticar después de ser eliminado");
    }

    private String createUser(String username, String password) {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"userName\": \"" + username + "\", \"password\": \"" + password + "\"}")
                .post(BASE_URL + "/Account/v1/User");
        Assertions.assertEquals(201, response.getStatusCode(), "Usuario no creado correctamente");
        return response.jsonPath().getString("userID");
    }

    private String authenticateUser(String username, String password) {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"userName\": \"" + username + "\", \"password\": \"" + password + "\"}")
                .post(BASE_URL + "/Account/v1/GenerateToken");
        if (response.getStatusCode() == 200 && "Success".equals(response.jsonPath().getString("status"))) {
            return response.jsonPath().getString("token");
        } else {
            return null;
        }
    }

    private void deleteUser(String userId, String token) {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .delete(BASE_URL + "/Account/v1/User/" + userId);
        Assertions.assertEquals(204, response.getStatusCode(), "Usuario no eliminado correctamente");
    }
}
