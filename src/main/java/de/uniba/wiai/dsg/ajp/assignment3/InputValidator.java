package de.uniba.wiai.dsg.ajp.assignment3;

public class InputValidator {

    public InputValidator() {
    }

    /**
     * Validates objects to be not null.
     *
     * @param inputObject object to be validated.
     * @throws IllegalArgumentException when argument is null.
     */
    static Object validateObject(Object inputObject) {
        if (inputObject == null) {
            StackTraceElement callingMethodElem = Thread.currentThread().getStackTrace()[2];
            String callingMethodString = callingMethodElem.getClassName() + "." + callingMethodElem.getMethodName();
            throw new IllegalArgumentException("Null argument in " + callingMethodString);
        }
        return inputObject;
    }

    /**
     * Validates string provided to be not null or empty.
     *
     * @param inputString the string to validate.
     * @return validated string.
     * @throws IllegalArgumentException when argument is null or empty.
     */
    static String validateString(String inputString) {
        if (inputString == null) {
            throw new IllegalArgumentException("Input String must not be null");
        }
        if (inputString.isBlank()) {
            throw new IllegalArgumentException("Input String must not be empty");
        }
        return inputString;
    }

    /**
     * Validates integer to be a positive integer.
     *
     * @param inputInteger integer to validate.
     * @return validated integer.
     * @throws IllegalArgumentException when argument is equal zero or negative.
     */
    static int validateInteger(int inputInteger) {
        if (inputInteger == 0) {
            throw new IllegalArgumentException("Input Integer must not be 0");
        }
        if (inputInteger < 0) {
            throw new IllegalArgumentException("Input Integer must not be negative");
        }
        return inputInteger;
    }

    /**
     * Validates integer to be in range of 0 - 100.
     *
     * @param inputInteger integer to validate.
     * @return validated discount.
     * @throws IllegalArgumentException when argument is not in range of 1 - 100.
     */
    static int validateDiscount(int inputInteger) {
        if (inputInteger < 0 ) {
            throw new IllegalArgumentException("Input Integer must be between 0 and 100");
        }
        if (inputInteger > 100) {
            throw new IllegalArgumentException("Input Integer must be between 0 and 100");
        }
        return inputInteger;
    }
}
