package ua.ithillel.reflect.drink.exception;

public class DrinkClientException extends Exception {

    public DrinkClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public DrinkClientException(String message) {
        super(message);
    }
}
