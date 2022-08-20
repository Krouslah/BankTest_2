package operations.depositManagment;

import operations.cardManagment.CardManager;
import products.cards.Card;
import products.deposit.Deposit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DepositManager {
    public void depositList(List<Deposit> depositList){
        if(depositList.size() == 0){
            System.out.println("Созданных вкладов нет");
        }
        else {
            System.out.print("Список депозитов: "); depositList.forEach(System.out::print);
        }
    }

    public List<List> choseDeposit(List<Deposit> depositList, List<Card> cardList) throws IOException {
        List<List> list = new ArrayList<>();
        System.out.println("Выберите депозит из списка: ");
        depositList(depositList);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(reader.readLine());
        if (i < 0 | i > depositList.size()) {
            System.out.println("Выбран несуществующий вклад");
            return null;
        }
        System.out.println("""
                    Выберите операцию:
                    1)Добавить на баланс
                    2)Добавить проценты на баласа (посуточно)
                    3)Запрос баланса
                    4)Закрытие вклада
                    5)Выход
                    """);
        int j = Integer.parseInt(reader.readLine());
        while (j < 0 | j > 5){
            System.out.println("\nВведен неверный пункт\n");
            System.out.println("""
                    Выберите операцию:
                    1)Добавить на баланс
                    2)Добавить проценты на баласа (посуточно)
                    3)Запрос баланса
                    4)Закрытие вклада
                    5)Выход
                    """);
            j = Integer.parseInt(reader.readLine());
        }
        switch (j){
            case 1:
                System.out.println("Введите сумму для пополнения: ");
                double sum = Double.parseDouble(reader.readLine());
                depositList.get(i).addToBalance(sum);
                list.add(depositList);
                return list;
            case 2:
                depositList.get(i).addPercentToBalancePerDay();
                System.out.println("Посуточные проценты успешно добавлены");
                list.add(depositList);
                return list;
            case 3:
                System.out.println("Баланс: " + depositList.get(i).getCurrentBalance());
                list.add(depositList);
                return list;
            case 4:
                System.out.println("Выберите карту для зачисления средств из вклада: ");
                CardManager cardManager = new CardManager();
                List<Card> cardList2 = cardManager.choseACard(cardList);
                int k = Integer.parseInt(reader.readLine());
                depositList.get(i).closeDeposit(cardList2.get(i));
                depositList.remove(depositList.get(i));
                list.add(depositList);
                list.add(cardList2);
                return list;
            case 5:
                return list;
            default:
                System.out.println("Oops! Smth went wrong");
                return list;
        }
    }
}
