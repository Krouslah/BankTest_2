package customExceptions;

public class SetCurrencyException extends Exception{
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public SetCurrencyException(String message, String currency) {
        super(message);
        this.currency = currency;
    }
}
