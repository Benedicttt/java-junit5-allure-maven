package helpers;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

public class JsonHelper {
    public static <T> T getPath(String response, String path) {
        ReadContext ctx = JsonPath.parse(response);

        try {
            T obj = ctx.read(path);
            return obj;
        } catch (com.jayway.jsonpath.PathNotFoundException e) {
            return null;
        }
    }
}
