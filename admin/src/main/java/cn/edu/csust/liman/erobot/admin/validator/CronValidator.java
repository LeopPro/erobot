package cn.edu.csust.liman.erobot.admin.validator;

import org.quartz.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CronValidator implements ConstraintValidator<Cron, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return CronExpression.isValidExpression(value);
    }
}
