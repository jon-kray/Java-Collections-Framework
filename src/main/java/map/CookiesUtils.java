package map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookiesUtils {

    private final static String SPEC_SYMBOL = ";";
    private final static String SPEC_SYMBOL_KEY_VALUE = ":";

    public static Map<String, Pair> getCookies(List<String> cookiesTestList) {
        Map<String, Pair> result = new HashMap<>();
        for (String string : cookiesTestList) {
            String[] arrayString = string.split(SPEC_SYMBOL_KEY_VALUE);
            String[] valueAndFlag = arrayString[1].split(SPEC_SYMBOL);
            if (valueAndFlag.length == 2) {
                result.put(arrayString[0], Pair.of(arrayString[0], valueAndFlag[0], Flag.valueOf(valueAndFlag[1]), Flag.HTTP_ONLY ));
            }else {
                result.put(arrayString[0], Pair.of(arrayString[0], valueAndFlag[0], Flag.values()));
            }
        }
        return result;
    }
}
