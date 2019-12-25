public class Util {
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception ignored) {}
    }
}
