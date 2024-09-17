package coursemanager.util;

public final class Validation {
    private Validation() {}

    public static boolean isNonNegative(int x) {
        return x >= 0;
    }

    public static boolean isNonNegative(double x) {
        return x >= 0;
    }

    public static boolean isBooleanInt(int x) {
        return x == 0 || x == 1;
    }
}
