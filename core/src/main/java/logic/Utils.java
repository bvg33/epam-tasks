package logic;

import lib.StringUtils;

public class Utils {
    public boolean isAllPositiveNumbers(String... strings) {
        StringUtils utils = new StringUtils();
        for (String string : strings) {
            boolean result = utils.isPositiveNumber(string);
            if (result == false) {
                return result;
            }
        }
        return true;
    }
}
