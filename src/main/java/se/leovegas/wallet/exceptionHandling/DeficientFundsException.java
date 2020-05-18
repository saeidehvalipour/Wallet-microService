package se.leovegas.wallet.exceptionHandling;

public class DeficientFundsException extends Exception {
    private String message;

    public DeficientFundsException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
