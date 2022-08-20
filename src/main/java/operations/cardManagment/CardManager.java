package operations.cardManagment;

import customExceptions.RemoveFromBalanceException;
import products.cards.Card;
import products.cards.CreditCard;
import products.cards.CurrencyCard;
import products.cards.DepositCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CardManager {
    public CardManager() {
    }

    public void cardList(List<Card> cardList){
        if(cardList.size() == 0){
            System.out.println("List has no created cards");
        }
        else {
            System.out.print("Card list: "); cardList.forEach(System.out::print);
            System.out.println();
        }
    }
    public List<Card> choseACard(List<Card> cardList, float percentCredit) throws IOException, RemoveFromBalanceException {
        try {
            System.out.println("Chose a card: ");
            cardList(cardList);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int i = Integer.parseInt(reader.readLine()) - 1;
            if (i < 0 | i > cardList.size()) {
                System.out.println("Incorrect card");
                return null;
            }
            System.out.println("""
                    Chose option:
                    1)Added to balance
                    2)Remove from balance
                    3)Balance request
                    4)Dept request
                    5)Remove interest on debt
                    """);
            int j = Integer.parseInt(reader.readLine());
            while (j < 0 | j > 5){
                System.out.println("\nIncorrect number\n");
                System.out.println("""
                    Chose option:
                    1)Add to balance
                    2)Remove from balance
                    3)Balance request
                    4)Dept request
                    5)Remove interest on debt
                    """);
                j = Integer.parseInt(reader.readLine());
            }
            switch (j) {
                case 1:
                    System.out.println("Sum to added: ");
                    double sum = Double.parseDouble(reader.readLine());
                    cardList.get(i).addToBalance(sum);
                    return cardList;
                case 2:
                    System.out.println("Sum to remove");
                    double summ = Double.parseDouble(reader.readLine());
                    System.out.print("Card List(case2, CardManager_1): "); cardList.forEach(System.out::print);
                    System.out.println();
                    cardList.get(i).removeFromBalance(summ, cardList.get(i));
                    System.out.print("Card List(case2, CardManager_2): "); cardList.forEach(System.out::print);
                    return cardList;
                case 3:
                    System.out.println("Current balance: " + cardList.get(i).getCurrentBalance());
                    return cardList;
                case 4:
                    if(cardList.get(i).getClass().equals(CreditCard.class)){
                        ((CreditCard) cardList.get(i)).getArrear(((CreditCard) cardList.get(i)));
                    }
                    else System.out.println("Current card cannot have dept-option");
                    return cardList;
                case 5:
                    if(cardList.get(i).getClass().equals(CreditCard.class)){
                        ((CreditCard) cardList.get(i)).removePercent((CreditCard) cardList.get(i), percentCredit);
                    }
                    else System.out.println("Current card cannot have dept-option");
                    return cardList;
                default:
                    System.out.println("Oops! Smth went wrong");
                    return cardList;

            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

    public List<Card> choseACard(List<Card> cardList) throws IOException {
        cardList(cardList);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(reader.readLine());
        if (i < 0 | i > cardList.size()) {
            System.out.println("Chosen card does not exists");
            return null;
        }
        return cardList;
    }
}
