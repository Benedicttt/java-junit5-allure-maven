package helpers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;

import java.io.IOException;

import static helpers.Property.getProperty;
import static io.restassured.RestAssured.given;

public class Request {
    private static RequestSpecification spec;

    public static Response call(String namePath, String nameParams) throws IOException {
        spec = new RequestSpecBuilder()
                .setConfig(setHttpRetry())
                .setContentType(ContentType.JSON)
                .setBaseUri(getProperty("baseUrlAPI"))
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();

        RequestSpecification request = given().spec(spec);
        Response response = request.header("", "")
                .spec(spec)
                .with()
                .get(namePath).thenReturn();

        return response;
    }

    private static RestAssuredConfig setHttpRetry() {
        HttpClientConfig.HttpClientFactory htc = new HttpClientConfig.HttpClientFactory() {

            @Override
            public HttpClient createHttpClient() {
                DefaultHttpClient d = new DefaultHttpClient();
                d.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(100000, true));
                return d;
            }
        };

        RestAssuredConfig rac = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig().httpClientFactory(htc));

        return rac;
    }
}
