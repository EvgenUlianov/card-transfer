package evgenulianov.cardtransfer.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.text.DateFormatter;
import java.text.Format;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorCardFromValidTillTest {

    private ValidatorCardFromValidTill validator = new ValidatorCardFromValidTill();

    @Test
    void isValidNow() {
        YearMonth yearMonthNow = YearMonth.now();
        String cardFromValidTillTest = yearMonthNow.format(DateTimeFormatter.ofPattern("MM/yy"));
        assertTrue(validator.isValid(cardFromValidTillTest));
    }

    @Test
    void notValidPreviousMonth() {
        YearMonth yearMonthNow = YearMonth.now().minusMonths(1);
        String cardFromValidTillTest = yearMonthNow.format(DateTimeFormatter.ofPattern("MM/yy"));
        assertFalse(validator.isValid(cardFromValidTillTest));
    }

    @Test
    void notValidNextMonth() {
        YearMonth yearMonthNow = YearMonth.now().plusMonths(1);
        String cardFromValidTillTest = yearMonthNow.format(DateTimeFormatter.ofPattern("MM/yy"));
        assertTrue(validator.isValid(cardFromValidTillTest));
    }

    @ParameterizedTest
    @ValueSource(strings = {"45/67", "456/67", "", "11/g2", "w1/12", "99/99", "10/00", "00/22", "08/20"})
    void notValidValues(String cardFromValidTillTest) {
        assertFalse(validator.isValid(cardFromValidTillTest));
    }

}