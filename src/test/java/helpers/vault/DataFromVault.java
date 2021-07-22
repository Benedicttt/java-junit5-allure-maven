package helpers.vault;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public enum DataFromVault {
    INSTANCE;

    public Response approle;
    public DbAndHost vault;
    Gson gson = new Gson();

    DataFromVault() {
        ApproleProperties approleProperties = gson.fromJson(approle().getBody().asString(), ApproleProperties.class);
        String token = approleProperties.auth.client_token;

        VaultProperties vaultProperties = gson.fromJson(vault(token).getBody().asString(), VaultProperties.class);
        vault = vaultProperties.data.data;
    }

    private Response approle() {
        Map<String, String> headers = Map.of(
                "role_id", System.getProperty("role_id"),
                "secret_id", System.getProperty("secret_id")
        );

        RequestSpecification spec = new RequestSpecBuilder()
                .setContentType("application/x-www-form-urlencoded")
                .setBaseUri(System.getProperty("hostVault"))
//                .addFilter(new ResponseLoggingFilter())
//                .addFilter(new RequestLoggingFilter())
                .addParams(headers)
                .build();

        RequestSpecification request = given().spec(spec);

        return request
                .spec(spec)
                .with()
                .post(System.getProperty("login")).thenReturn();

    }

    private Response vault(String token) {
        Map<String, String> headers = Map.of("X-Vault-Token", token);

        RequestSpecification spec = new RequestSpecBuilder()
                .setContentType("application/x-www-form-urlencoded")
                .setBaseUri(System.getProperty("hostVault"))
//                .addFilter(new ResponseLoggingFilter())
//                .addFilter(new RequestLoggingFilter())
                .addHeaders(headers)
                .build();

        RequestSpecification request = given().spec(spec);

        return request
                .spec(spec)
                .with()
                .get(System.getProperty("env")).thenReturn();

    }
}
