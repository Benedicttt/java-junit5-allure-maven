package api;

import helpers.Print;
import helpers.Request;
import io.restassured.response.Response;

import java.io.IOException;

import static helpers.Property.getProperty;


public class Something {

    private Something() {
        try {
            Request.call(getProperty("baseUrlAPI"), null);

        } catch (IOException e) {

        }
    }

    private static class LazyHolder {
        static final Something INSTANCE = new Something();
    }

    public static Something getInstance() {
        return LazyHolder.INSTANCE;
    }

}



