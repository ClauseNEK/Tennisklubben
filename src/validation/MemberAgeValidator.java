package validation;

import exceptions.InvalidAgeException;

public class MemberAgeValidator implements AgeValidator {

    /**
     * Den overskrevet metode fra interfacen "AgeValidator" og definere hvad alderen for et medlem må være.
     * @param age Alderen, der bliver givet, som input.
     * @throws InvalidAgeException Exceptionen, der bliver kastet, sammen med en besked, der forklarer fejlen.
     */
    @Override
    public void validate(int age) throws InvalidAgeException {
        if(age < 0 || age > 120) {
            throw new InvalidAgeException("Der accepteres kun medlemmer mellem 0 og 120 år.");
        }
    }

}
