import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class YearlyReport {
    public int year;
    public HashMap<Integer, YearlyReportMonth> monthsData = new HashMap<>();//записи годового отчета
    public static HashMap<Integer, String> monthName = new HashMap<>();
    static {
        monthName.put(1, "January");
        monthName.put(2, "February");
        monthName.put(3, "March");
        monthName.put(4, "April");
        monthName.put(5, "May");
        monthName.put(6, "June");
        monthName.put(7, "July");
        monthName.put(8, "August");
        monthName.put(9, "September");
        monthName.put(10, "October");
        monthName.put(11, "November");
        monthName.put(12, "December");
    }

    public YearlyReport(int year) {
        this.year = year;

        String content = readFileContentsOrNull("resources/y." + year + ".csv");
        String[] lines = content.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int sum = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            if (!monthsData.containsKey(month)) {
                monthsData.put(month, new YearlyReportMonth(month));
            }

            YearlyReportMonth oneMonthData = monthsData.get(month);
            if (isExpense) {
                oneMonthData.expenses += sum;
            } else {
                oneMonthData.income += sum;
            }
        }
    }

    public int getSumProfitMonth(int month) {
        int profit = 0;
        for (int months : monthsData.keySet()) {
            profit = monthsData.get(month).income - monthsData.get(month).expenses;
        }
        return profit;
    }
    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
    void printAllIncomeAndExpense() {
        for (int month : monthsData.keySet()) {
            System.out.println("В " + monthName.get(month) + " доход составил: " + monthsData.get(month).income + " рублей, расход составил: " + monthsData.get(month).expenses + " рублей.");
        }
    }
    public int getSumIncome() {
        int income = 0;
        for (YearlyReportMonth oneMonthData : monthsData.values()) {
            income += oneMonthData.income;
        }
        return income;
    }
    public int getSumExpenses() {
        int expenses = 0;
        for (YearlyReportMonth oneMonthData : monthsData.values()) {
            expenses += oneMonthData.expenses;
        }
        return expenses;
    }

    void printInfoYear() {
        System.out.println("Средний расход за все месяцы в году составил: " + getSumExpenses() / Main.MONTHS_COUNT + " рублей.");
        System.out.println("Средний доход за все месяцы в году составил: " + getSumIncome() / Main.MONTHS_COUNT + " рублей.");
        for (int month : monthsData.keySet()) {
            System.out.println("Прибыль за " + monthName.get(month) + " составила: " + (monthsData.get(month).income - monthsData.get(month).expenses + " рублей."));
        }
    }
}







