package util;

import java.net.URL;

public class Resource {

    public static String get(String name) {
        URL resource = new Resource().getClass().getResource(name);
        return resource.getFile();
    }
}
