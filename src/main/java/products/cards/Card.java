package products.cards;


import customExceptions.RemoveFromBalanceException;

public  class Card {
    private double currentBalance;
    private String name;

    public Card(double currentBalance, String name) {
        this.currentBalance = currentBalance;
        this.name = name;
    }

    public void addToBalance(Double addNumber){
        this.currentBalance = this.currentBalance + addNumber;
        System.out.println("Sum successfully added");
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public String getName() {
        return name;
    }

    public  void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void removeFromBalance(double valueToRemove, Card card) throws RemoveFromBalanceException{
        if (CurrencyCard.class.equals(card.getClass())) {
            ((CurrencyCard) card).removeFromBalanceCurrencyCard(valueToRemove, (CurrencyCard) card);
        }
        if (DepositCard.class.equals(card.getClass())){
            ((DepositCard) card).removeFromBalanceDepositCard(valueToRemove, (DepositCard) card);
        }
        if (CreditCard.class.equals(card.getClass())){
            ((CreditCard) card).removeFromBalanceCreditCard(valueToRemove, (CreditCard) card);
        }
        this.currentBalance -= valueToRemove;
    }

}
