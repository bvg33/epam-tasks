package lib;

import static org.apache.commons.lang3.StringUtils.compare;

public class StringUtils {
    public boolean isPositiveNumber(final String str) {
        return compare(str, "0") > 0;
    }
}
