package validation;

import exceptions.InvalidAgeException;

public interface AgeValidator {

    void validate(int age) throws InvalidAgeException;
}
