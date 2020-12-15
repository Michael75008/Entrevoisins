package com.openclassrooms.mareuapp.model;

/**
 * Model object representing a validator
 */

public class ValidatorModel {

    /**
     * Validation estate
     */

    private boolean isValid;

    /**
     * Message
     */

    private int errorMessageId;

    /**
     * Constructor
     */

    public ValidatorModel(boolean isValid, int errorMessageId) {
        this.isValid = isValid;
        this.errorMessageId = errorMessageId;
    }

    /**
     * Getters and setters
     */

    public boolean isValid() { return isValid; }

    public void setValid(boolean valid) { isValid = valid; }

    public int getErrorMessage() { return errorMessageId; }

    public void setErrorMessage(int errorMessageId) { this.errorMessageId = errorMessageId; }
}
