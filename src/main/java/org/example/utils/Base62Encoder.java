package org.example.utils;

public class Base62Encoder {
    private static final String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public  static String encode(long value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0){
            int index = (int)value % 62;
            sb.append(BASE62.charAt(index));
            value /= 62;
        }

        return sb.reverse().toString();

    }
}
