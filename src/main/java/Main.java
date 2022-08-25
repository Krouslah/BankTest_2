import customExceptions.RemoveFromBalanceException;
import customExceptions.SetCurrencyException;
import operations.cardManagment.CardManager;
import operations.depositManagment.DepositManager;
import products.cards.*;
import products.deposit.CreateDeposit;
import products.deposit.Deposit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
   private static List<Card> cardList = new ArrayList<>();
   private static List<Deposit> depositList = new ArrayList<>();
   private static final float percentCredit = 15;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int i = 1;
            while (i < 20 ){
                System.out.println("""
                Chose one:
                 1)Create card
                 2)Create deposit
                 3)Manage cards
                 4)Manage deposit
                 5)Exit
                """);
                i = Integer.parseInt(reader.readLine());
                while (i > 5 | i <= 0 ) {
                    System.out.println("\nIntroduced wrong number\n");
                    i = Integer.parseInt(reader.readLine());
                }
                switch (i){
                    case 1:
                        try {
                            createCardOfChosenType();
                        }
                        catch (SetCurrencyException sce){
                            System.out.println(sce.getMessage());
                        }
                         break;
                    case 2:
                        try {
                            System.out.println("\nSet product name: ");
                            String name = reader.readLine();
                            System.out.println("\nSet currency type: ");
                            String currencyType = reader.readLine();
                            createDeposit(name, currencyType);
                            break;
                        } catch (SetCurrencyException sce) {
                            System.out.println(sce.getMessage());
                            break;
                        }
                    case 3:
                        CardManager cardManager = new CardManager();
                        List<Card> cardList2 = cardManager.choseACard(cardList, percentCredit);
                        if (cardList2.size() == 0){
                            break;
                        }
                        else cardList = cardList2;
                        break;
                    case 4:
                        DepositManager depositManager = new DepositManager();
                         List<List> listOfLists = depositManager.choseDeposit(depositList, cardList);
                         if (listOfLists == null){
                             break;
                         }
                         depositList = listOfLists.get(0);
                         if (listOfLists.size() >= 2){
                             cardList = listOfLists.get(1);
                         }
                         break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Woops!");
                        System.exit(1);

                }
            }
        }
        catch (IOException io){
            io.printStackTrace();
        }
        catch (RemoveFromBalanceException rfbe){
            System.out.println(rfbe.getMessage());
        }
    }

    public static void createCardOfChosenType() throws IOException, SetCurrencyException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nSet product name: ");
        String name = reader.readLine();
        System.out.println("""
                                Chose type: 
                                1)Credit
                                2)Debit
                                3)Currency
                                4)Back
                                """);
        int i = Integer.parseInt(reader.readLine());
        if (i < 0 | i > 4) {
            System.out.println("Introduced wrong number");
            return;
        }
        switch (i){
            case 1,2:
                if (i == 1) {
                    CreditCard creditCard =  CreateCards.createCreditCard(name);
                    cardList.add(creditCard);
                }
                if (i == 2) {
                    DepositCard depositCard = CreateCards.createDepositCard(name);
                    cardList.add(depositCard);
                }
                System.out.println(" \nCard was successfully added\n");
                break;
            case 3:
                System.out.println("\nSet currency type: ");
                String currencyType = reader.readLine();
                CurrencyCard currencyCard = CreateCards.createCurrencyCard(name, currencyType);
                break;
            case 4: break;
            default:
                System.out.println("Wrong input");
                break;
        }


    }

    public static void createDeposit(String name, String currencyType) throws IOException, SetCurrencyException {
        if ((currencyType.toLowerCase().equals("usd") | currencyType.toLowerCase().equals("euro"))) {
            Deposit deposit = CreateDeposit.createDeposit(name, currencyType);
            depositList.add(deposit);
            System.out.println("Deposit was successfully added");
        }
        else throw new SetCurrencyException("Introduced wrong currency type: ", currencyType);
    }
}
