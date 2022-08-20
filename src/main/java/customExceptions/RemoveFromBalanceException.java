package customExceptions;

public class RemoveFromBalanceException extends Exception{
    private double currentBalance;

    public double getCurrentBalance() {
        return currentBalance;
    }

    public RemoveFromBalanceException(String message, double currentBalance) {
        super(message);
        this.currentBalance = currentBalance;
    }
}
