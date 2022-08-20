package products.cards;

import customExceptions.RemoveFromBalanceException;

public class CurrencyCard extends Card {
    private String currency;

    public CurrencyCard(double currentBalance, String name, String currency) {
        super(currentBalance, name);
        this.currency = currency;
    }

    public void removeFromBalanceCurrencyCard(Double removeFromBalance, CurrencyCard card) throws RemoveFromBalanceException {
        try {
            if(card.getCurrentBalance() >= removeFromBalance) {
                card.setCurrentBalance(card.getCurrentBalance() - removeFromBalance);
                System.out.println("Списание проведено успешно");
            }
            else throw new RemoveFromBalanceException("В списании отказано. Недостаточно средств на карте для списания ", removeFromBalance);
        }
        catch (RemoveFromBalanceException rfbe){
            System.out.println(rfbe.getMessage());
        }
    }

    @Override
    public String toString() {
        return "CurrencyCard{" +
                "currency='" + currency + ", current balance=" + getCurrentBalance() + ", name=" + getName() +
                "} ";
    }
}
