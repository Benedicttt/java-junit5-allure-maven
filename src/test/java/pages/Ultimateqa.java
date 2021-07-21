package pages;

import java.io.IOException;

import static helpers.Property.getProperty;

public class Ultimateqa {

    public String baseUrl() throws IOException { return getProperty("baseUrl"); }
}
