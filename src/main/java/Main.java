import customExceptions.RemoveFromBalanceException;
import customExceptions.SetCurrencyException;
import operations.cardManagment.CardManager;
import operations.depositManagment.DepositManager;
import products.cards.Card;
import products.cards.CreditCard;
import products.cards.CurrencyCard;
import products.cards.DepositCard;
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
   private static final float percentDeposit = 7;
    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Chose one:
//                 1)Create card
//                 2)Create deposit
//                 3)Manage cards
//                 4)Manage deposit
//                 5)Exit
//                """);
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
                            createDeposit();
                            break;
                        } catch (SetCurrencyException sce) {
                            System.out.println(sce.getMessage());
                            break;
                        }
                    case 3:
                        CardManager cardManager = new CardManager();
                        List<Card> cardList2 = cardManager.choseACard(cardList, percentCredit);
//                        System.out.print("Card List :"); cardList.forEach(System.out::print);
//                        System.out.println();
//                        System.out.print("Card List 2: "); cardList2.forEach(System.out::print);
//                        System.out.println();
                        if (cardList2.size() == 0){
                            break;
                        }
                        else cardList = cardList2;
//                        System.out.print("finished card list : "); cardList.forEach(System.out::print);
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
                System.out.println("\nSet card name: ");
                String name = reader.readLine();
                if (i == 1) {
                    CreditCard creditCard = new CreditCard(0.0, name, percentCredit);
                    cardList.add(creditCard);
                }
                if (i == 2) {
                    DepositCard depositCard = new DepositCard(0.0, name);
                    cardList.add(depositCard);
                }
                System.out.println(" \nCard was successfully added\n");
                break;
            case 3:
                System.out.println("\nSet card name: ");
                String name2 = reader.readLine();
                System.out.println("\nSet currency type: ");
                String currencyType = reader.readLine();
                if ((currencyType.toLowerCase().equals("usd") | currencyType.toLowerCase().equals("euro"))) {
                    CurrencyCard currencyCard = new CurrencyCard(0.0, name2, currencyType);
                }
                else throw new SetCurrencyException("Introduced wrong currency type: ", currencyType);
                break;
            case 4: break;
        }


    }

    public static void createDeposit() throws IOException, SetCurrencyException {
        System.out.println("Set deposit name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        System.out.println("\nSet currency type: ");
        String currencyType = reader.readLine();
        if ((currencyType.toLowerCase().equals("usd") | currencyType.toLowerCase().equals("euro"))) {
            Deposit deposit = new Deposit(currencyType, 0.0, name, percentDeposit);
            System.out.println("Deposit was successfully added");
        }
        else throw new SetCurrencyException("Introduced wrong currency type: ", currencyType);
    }

    public static List<Card> getCardList() {
        return cardList;
    }

    public static List<Deposit> getDepositList() {
        return depositList;
    }

    public static void setCardList(List<Card> cardList) {
        Main.cardList = cardList;
    }

    public static void setDepositList(List<Deposit> depositList) {
        Main.depositList = depositList;
    }
}
