package api;

import helpers.Print;
import helpers.Request;
import helpers.vault.DataFromVault;
import io.restassured.response.Response;
import model.dto.ContractType;
import model.repository.ContractTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static helpers.Property.getProperty;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JUnit5Test2 {
    private static Response response;

    private static ContractTypeRepository ctr;
    private static DataFromVault vault;

    @BeforeAll
    static void init() {
        ctr = new ContractTypeRepository();
        vault = DataFromVault.INSTANCE;
    }

    @BeforeAll
    static void request() throws IOException {
        response = Request.call(getProperty("baseUrlAPI"), null);
        System.out.println(vault.baseHost);
        System.out.println(vault.baseBalancer);
    }

    @Test
    void connect_db() {
        List<ContractType> a = ctr.findAll();

        Print.d(String.valueOf(a.get(0).id));
        Print.d(String.valueOf(a.get(0).name));

        Print.d(String.valueOf(a.get(1).id));
        Print.d(String.valueOf(a.get(1).name));
    }

    @Test
    void connect_db2() {
        Print.d(String.valueOf(ctr.findAll()));
    }

    @Test
    @DisplayName("get and assert status code")
    void test1() {
        Assertions.assertNotEquals(System.getProperty("key"), "key");
    }

    @Test
    void test2() {
        Assertions.assertNull(System.getProperty("key"));
    }

    @Test
    @DisplayName("Check response code")
    void test3() {
        Assertions.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    @DisplayName("Check schema")
    void test4() {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/example.json"));
    }

}