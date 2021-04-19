package util;

import java.net.URL;

public class Resource {

    public static String get(String name) {
        URL resource = Resource.class.getClassLoader().getResource(name);
        return resource.getFile();
    }
}
