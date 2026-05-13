package validation;

import exceptions.InvalidAgeException;

public class MemberAgeValidator implements AgeValidator {

    @Override
    public void validate(int age) throws InvalidAgeException {
        if(age < 8 || age > 88) {
            throw new InvalidAgeException("Der accepteres kun medlemmer mellem 8 og 88 år.");
        }
    }

}
