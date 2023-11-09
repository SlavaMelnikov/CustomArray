package by.melnikov.customarray.util;

public class IdGenerator {
    private static long currentId;

    public static long generateId() {
        return ++currentId;
    }
}
