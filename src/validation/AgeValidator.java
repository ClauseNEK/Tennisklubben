package validation;

import exceptions.InvalidAgeException;

public interface AgeValidator {
    /**
     * Interfacens tomme metode der, når implementeret, skal definere kravene og kaste en InvalidAgeException
     * hvis den får et input/alder, der er udenfor den defineret aldersgrænsen.
     * @param age Alderen, der bliver givet, som input.
     * @throws InvalidAgeException Den exception der skal kastes, hvis "age" ikke er lever op til de implementeret krav
     */
    void validate(int age) throws InvalidAgeException;
}
