package evgenulianov.cardtransfer.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.YearMonth;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorCardFromValidTill implements ConstraintValidator<ValidCardFromValidTill, String> {

    @Override
    public boolean isValid(String cardFromValidTill, ConstraintValidatorContext constraintValidatorContext) {
        return isValid(cardFromValidTill);
    }

    public boolean isValid(String cardFromValidTill){
        if (cardFromValidTill.length() != 5)
            return false;

        Pattern pattern = Pattern.compile("^([0-9]{1,2})/([0-9]{1,2})$");
        Matcher matcher = pattern.matcher(cardFromValidTill);

        if (!matcher.matches())
            return false;

        int month = Integer.valueOf(cardFromValidTill.substring(0, 2));
        int year = Integer.valueOf(cardFromValidTill.substring(3, 5)) + 2000;
        if (month > 12 || month < 1)
            return false;

        YearMonth yearMonth = YearMonth.of(year, month);
        YearMonth yearMonthNow = YearMonth.now();
        return !yearMonth.isBefore(yearMonthNow);
    }
}
