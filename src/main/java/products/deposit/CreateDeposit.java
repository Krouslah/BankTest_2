package products.deposit;

public class CreateDeposit {
    private static final float percentDeposit = 15;
    public static Deposit createDeposit(String name, String currencyType){
        return new Deposit(currencyType, 0.0, name, percentDeposit);
    }
}
