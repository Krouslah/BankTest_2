package products.cards;

import customExceptions.RemoveFromBalanceException;

public class DepositCard extends Card {
    private String currency = "RUB";

    public DepositCard(double currentBalance, String name) {
        super(currentBalance, name);
    }

    public void removeFromBalanceDepositCard(Double removeFromBalance, DepositCard card) throws RemoveFromBalanceException {
        try {
            if (card.getCurrentBalance() >= removeFromBalance) {
                card.setCurrentBalance(card.getCurrentBalance() - removeFromBalance);
                System.out.println("Sum successfully removed");
            }
            else
                throw new RemoveFromBalanceException("Denied. Have no that much money ", removeFromBalance);
        } catch (RemoveFromBalanceException rfbe) {
            System.out.println(rfbe.getMessage());
        }
    }

    @Override
    public String toString() {
        return "DepositCard{" +
                "currency='" + currency + ", current balance=" + getCurrentBalance() + ", name=" + getName() +
                "} ";
    }
}
