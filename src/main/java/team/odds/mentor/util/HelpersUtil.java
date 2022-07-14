package team.odds.mentor.util;

public class HelpersUtil {

    public static String skillFormat(String value) {
        return value.replaceAll("(\\s)+", "").toLowerCase();
    }

}
