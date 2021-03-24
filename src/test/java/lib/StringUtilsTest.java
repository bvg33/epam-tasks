package lib;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {
    private final StringUtils utils = new StringUtils();

    @Test
    public void isPositiveNumberTestShouldReturnTrueWhenNumberIsPositive() {
        boolean actual = utils.isPositiveNumber("6");
        assertTrue(actual);
    }

    @Test
    public void testIsPositiveNumber_NumberNegative() {
        boolean actual = utils.isPositiveNumber("-6");
        assertFalse(actual);
    }

}
