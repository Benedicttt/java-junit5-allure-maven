package helpers.vault;

import helpers.Print;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static helpers.JsonHelper.getPath;
import static io.restassured.RestAssured.given;

public enum DataFromVault {
    INSTANCE;
    Response approle;
    Response dataVault;
    public String baseHost;
    public String baseBalancer;

    DataFromVault() {
        Print.d("Singletone");
        approle      = approle();
        String token = getPath(approle.getBody().asString(), "$.auth.client_token");

        dataVault    = vault(token);
        baseHost     = getPath(dataVault.getBody().asString(), "$.data.data.host.base");
        baseBalancer = getPath(dataVault.getBody().asString(), "$.data.data.host.balance");
    }

    private Response approle() {
        Map<String, String> headers = Map.of(
                "role_id", System.getProperty("role_id"),
                "secret_id", System.getProperty("secret_id")
        );

        RequestSpecification spec = new RequestSpecBuilder()
                .setContentType("application/x-www-form-urlencoded")
                .setBaseUri(System.getProperty("hostVault"))
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
                .addHeaders(headers)
                .build();

        RequestSpecification request = given().spec(spec);

        return request
                .spec(spec)
                .with()
                .get(System.getProperty("env")).thenReturn();

    }
}
