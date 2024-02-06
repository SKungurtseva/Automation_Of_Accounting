import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class NewMonth {
    public HashMap<String, MonthData> monthDataMap = new HashMap<>();

    public NewMonth(int m, String monthPath) {

        String content = readFileContentsOrNull(monthPath);
        String[] lines = content.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);


            if (!monthDataMap.containsKey(itemName)) {
                monthDataMap.put(itemName, new MonthData(itemName, isExpense, quantity, sumOfOne));
            }

            MonthData oneMonthData = monthDataMap.get(itemName);
            if (isExpense) {
                oneMonthData.expenses = quantity * sumOfOne;
            } else {
                oneMonthData.income = quantity * sumOfOne;
            }
        }
    }

    public int getSumProfit() {
        int profit = 0;
        for (MonthData oneMonthData : monthDataMap.values()) {
            profit += oneMonthData.income - oneMonthData.expenses;
        }
        return profit;
    }
    // получить название категории + большая трата
   public void getItemNameBigExpense(){
       String itemName = "";
       int spendSum = 0;
       for (MonthData oneMonthData : monthDataMap.values()) {
           if (oneMonthData.isExpense) {
               int currentSpendSum = oneMonthData.sumOfOn * oneMonthData.quantity;

               if(currentSpendSum > spendSum){
                   spendSum = currentSpendSum;
                   itemName = oneMonthData.itemName;
               }
           }
       }
       System.out.println("Самая большая трата: " + itemName + ", на сумму: " + spendSum + " рублей.");
   }
    // получить название категории + большой доход
    public void getItemNameBigIncome() {
        String itemName = "";
        int spendSum = 0;
        for (MonthData oneMonthData : monthDataMap.values()) {
            if (!oneMonthData.isExpense) {
                int currentSpendSum = oneMonthData.sumOfOn * oneMonthData.quantity;

                if(currentSpendSum > spendSum){
                    spendSum = currentSpendSum;
                    itemName = oneMonthData.itemName;
                }
            }
        }
        System.out.println("Самый прибыльный товар: " + itemName + ", на сумму: " + spendSum + " рублей.");
    }

    private static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}