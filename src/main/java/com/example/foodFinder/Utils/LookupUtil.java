package com.example.foodFinder.Utils;

public class LookupUtil {
    public static <E extends Enum<E>> E lookup(Class<E> e, String id) {
        E result;
        try {
            result = Enum.valueOf(e, id);
        } catch (IllegalArgumentException exception) {
            throw new RuntimeException("Invalid argument " + id +" for Enum "+e.getSimpleName());
        }

        return result;
    }
}
