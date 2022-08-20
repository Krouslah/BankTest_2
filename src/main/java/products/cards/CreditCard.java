package products.cards;

import customExceptions.RemoveFromBalanceException;

import java.util.Calendar;

public class CreditCard  extends Card{
    private Calendar dateFromStartCredit;
    private final String currency = "RUB";

    private final float percent;

    public CreditCard(double currentBalance, String name, float percent) {
        super(currentBalance, name);
        this.percent = percent;
    }


    public void removeFromBalanceCreditCard(Double valueToRemove, CreditCard card){
        if(card.getCurrentBalance() >= valueToRemove) {
            card.setCurrentBalance(card.getCurrentBalance() - valueToRemove);
            System.out.println("Balance successfully changed");
        }
        else {
            card.setCurrentBalance(card.getCurrentBalance() - valueToRemove);
            System.out.println("Balance successfully changed. Credit started");
            setDate();
            System.out.println(card.getCurrentBalance() + " - balance");
        }
    }

    public void setDate(){
        if(this.dateFromStartCredit == null) {
            this.dateFromStartCredit = Calendar.getInstance();
        }
    }

    public void removePercent(CreditCard card, float percent){
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_YEAR) - this.dateFromStartCredit.get(Calendar.DAY_OF_YEAR);
        card.setCurrentBalance(card.getCurrentBalance() - (card.getCurrentBalance() * (i * percent)));
        this.dateFromStartCredit = calendar;
    }

    public void getArrear(CreditCard card){
        if(0 > card.getCurrentBalance()) System.out.println(card.getCurrentBalance());
        else System.out.println("Dept = 0");
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "currency=" + currency +
                ", percent=" + percent + ", current balance=" + getCurrentBalance() + ", name=" + getName() +
                "} ";
    }
}
