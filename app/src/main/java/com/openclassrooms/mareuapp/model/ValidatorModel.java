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

    private String errorMessage;

    /**
     * Constructor
     * @param isValid
     * @param errorMessage
     */

    public ValidatorModel(boolean isValid, String errorMessage) {
        this.isValid = isValid;
        this.errorMessage = errorMessage; }

    /**
     * Getters and setters
     * @return
     */

    public boolean isValid() { return isValid; }

    public void setValid(boolean valid) { isValid = valid; }

    public String getErrorMessage() { return errorMessage; }

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
