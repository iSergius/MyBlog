package name.isergius.learn.myblog.ui.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kondratyev Sergey on 26.11.15.
 */
public class EqualValidator implements ConstraintValidator<Equal,Object> {

    private Map<String,Object> groups = new HashMap<>();
    private String group;
    private boolean newGroup;

    @Override
    public void initialize(Equal constraintAnnotation) {
        newGroup = false;
        group = constraintAnnotation.group();
        if (!groups.containsKey(group)) {
            newGroup = true;
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (newGroup){
            groups.put(group,value);
            return true;
        } else {
            Object expectedValue = groups.get(group);
            return value.equals(expectedValue);
        }
    }

}
