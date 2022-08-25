package TestClasses;

import cucumber.runtime.junit.Assertions;
import customExceptions.SetCurrencyException;
import jdk.jfr.Name;
import org.testng.Assert;
import org.testng.annotations.Test;
import products.cards.*;
import products.deposit.CreateDeposit;
import products.deposit.Deposit;

import java.util.ArrayList;
import java.util.List;

public class Steps {

    public static List<Card> cardList = new ArrayList<>();
    public static List<Deposit> depositList = new ArrayList<>();
    public void createCard(String type, String name){
        switch (type){
            case "Credit": createCreditCard(name);
            break;
            case "Debit": createDepositCard(name);
            break;
        }
    }

    public void createCreditCard(String name){
        CreditCard card = CreateCards.createCreditCard(name);
        Assert.assertEquals(card.getName(), name, "Created cards has different name. Created - " + card.getName() + ". Expected - " + name);
        cardList.add(card);
    }

    public void createDepositCard(String name){
        DepositCard card = CreateCards.createDepositCard(name);
        Assert.assertEquals(card.getName(), name, "Created cards has different name. Created - " + card.getName() + ". Expected - " + name);
        cardList.add(card);
    }

    public void createCurrencyCard(String name, String currencyType){
        try {
            CurrencyCard card = CreateCards.createCurrencyCard(name, currencyType);
            Assert.assertEquals(card.getName(), name, "Created cards has different name. Created - " + card.getName() + ". Expected - " + name);
            cardList.add(card);
        }
        catch (SetCurrencyException sce) {
            sce.printStackTrace();
            Assert.assertNotEquals(currencyType, "USD", "Currency type don't meet possible value. " + "Current: " + currencyType);
            Assert.assertNotEquals(currencyType, "EURO", "Currency type don't meet possible value. " + "Current: " + currencyType);
        }
    }

    public void checkCard(String classType, String name, String balance){
        Assert.assertEquals(cardList.get(cardList.size()-1).getName(), name, "Created cards has different name. Created - " + cardList.get(cardList.size()-1).getName() + ". Expected - " + name);
        Assert.assertEquals(cardList.get(cardList.size()-1).getClass().toString(), classType, "Created card has different class type. Expected:" + classType + ". Created:" + cardList.get(cardList.size()-1).getClass().toString());
        Assert.assertEquals(cardList.get(cardList.size()-1).getCurrentBalance(), Double.parseDouble(balance), "Created card has wrong balance: " + cardList.get(cardList.size()-1).getCurrentBalance());
    }

    public void createDepositStep(String name, String currencyType){
        Deposit deposit = CreateDeposit.createDeposit(name, currencyType);
        Assert.assertEquals(deposit.getName(), name, "Created deposit has invalid name: " + deposit.getName());
        depositList.add(deposit);
    }
    public void checkDepositStep(String classType, String currencyType, String balance, String name){
        Assert.assertEquals(depositList.get(depositList.size()-1).getName(), name, "Created deposit has different name. Created - " + depositList.get(depositList.size()-1).getName() + ". Expected - " + name);
        Assert.assertEquals(depositList.get(depositList.size()-1).getClass().toString(), classType, "Created deposit has different class type. Expected:" + classType + ". Created:" + depositList.get(depositList.size()-1).getClass().toString());
        Assert.assertEquals(depositList.get(depositList.size()-1).getCurrentBalance(), Double.parseDouble(balance), "Created deposit has wrong balance: " + depositList.get(depositList.size()-1).getCurrentBalance());
        Assert.assertEquals(depositList.get(depositList.size()-1).getCurrency(), currencyType, "Created deposit has wrong currency type: " + depositList.get(depositList.size()-1).getCurrency());
    }

    public void addToCurrentBalanceStep(String balance, String name){
        int i = cardList.indexOf(cardList.stream().filter(x -> x.getName().equals(name)).findFirst().get());
        cardList.get(i).addToBalance(Double.parseDouble(balance));
    }

    public void checkBalanceStep(String balance, String name){
        Assert.assertEquals(cardList.stream().filter(x -> x.getName().equals(name)).findFirst().get().getCurrentBalance(), Double.parseDouble(balance), "Current balance doesn't match. Get: " + cardList.stream().filter(x -> x.getName().equals(name)).findFirst().get().getCurrentBalance());
    }
}
