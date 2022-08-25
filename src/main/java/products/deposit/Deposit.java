package products.deposit;

import products.cards.Card;

import java.util.Calendar;

public class Deposit {
    private String currency;
    private double currentBalance;
    private String name;

    private float percent;

    private Calendar calendar;

    public Deposit(String currency, double currentBalance, String name, float percent) {
        this.currency = currency;
        this.currentBalance = currentBalance;
        this.name = name;
        this.percent = percent;
        this.calendar = Calendar.getInstance();
    }

    public void addToBalance(Double addValue){
        this.currentBalance += addValue;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void closeDeposit(Card card){
        card.addToBalance(this.currentBalance);
    }

    public void addPercentToBalancePerDay(){
        Calendar currentDate = Calendar.getInstance();
        int i = currentDate.get(Calendar.DAY_OF_YEAR) - this.calendar.get(Calendar.DAY_OF_YEAR);
        this.currentBalance = this.currentBalance + (this.currentBalance * ((this.percent / 365) * i));
        this.calendar = currentDate;
    }

    public String getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

}
