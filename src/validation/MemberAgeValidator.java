package validation;

import exceptions.InvalidAgeException;

public class MemberAgeValidator implements AgeValidator {

    @Override
    public void validate(int age) throws InvalidAgeException {
        if(age < 0 || age > 120) {
            throw new InvalidAgeException("Der accepteres kun medlemmer mellem 0 og 120 år.");
        }
    }

}
