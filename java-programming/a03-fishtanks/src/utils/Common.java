package utils;

import java.util.UUID;

public class Common {
    public static String generateUid() {
        return UUID.randomUUID().toString();
    }

}
