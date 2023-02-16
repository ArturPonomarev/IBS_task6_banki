package banki.framework.utils;

public class StringUtils {
    public static int convertStringToInt(String str) {
        String newStr = "";
        for (String number : str.split("[^0-9]"))
            newStr += number;
        return Integer.parseInt(newStr);
    }
}
