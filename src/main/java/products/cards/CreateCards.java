package products.cards;

import customExceptions.SetCurrencyException;

public class CreateCards {
    private static final float percentCredit = 15;


    public static CreditCard createCreditCard(String name){
        CreditCard creditCard = new CreditCard(0.0, name, percentCredit);
        return creditCard;
    }

    public static DepositCard createDepositCard(String name){
        DepositCard depositCard = new DepositCard(0.0, name);
        return depositCard;
    }

    public static CurrencyCard createCurrencyCard(String name, String currencyType) throws SetCurrencyException {
        if ((currencyType.toLowerCase().equals("usd") | currencyType.toLowerCase().equals("euro"))) {
            CurrencyCard currencyCard = new CurrencyCard(0.0, name, currencyType);
            return currencyCard;
        }
        else throw new SetCurrencyException("Introduced wrong currency type: ", currencyType);

    }
}
