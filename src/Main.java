import java.util.HashMap;
import java.util.Scanner;


public class Main {
    public static final int MONTHS_COUNT = 3;
    public static int year = 2021;
    public static YearlyReport yearlyReport = null;
    public static NewMonth monthlyReport = null;
    public static final String[] TITlE_MONTH = {"January " , "February ", "March ", "April ", "May ", "June ", "July ", "August ", "September ", "October ", "November ", "December "};


    public static HashMap<Integer, NewMonth> monthlyReports = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            int userInput = scanner.nextInt();
            if (userInput == 1) {
                for (int m = 1; m <= MONTHS_COUNT; m++) {
                    if (m < 10) {
                        monthlyReport = new NewMonth(m, "resources/m." + year + "0" + m + ".csv");
                    } else {
                        monthlyReport = new NewMonth(m, "resources/m." + year + "" + m + ".csv");
                    }
                    monthlyReports.put(m, monthlyReport);
                }
                System.out.println("Месячные отчеты считаны! Пожалуйста введите другую команду!");

            } else if (userInput == 2) {
                yearlyReport = new YearlyReport(year);
                System.out.println("Годовой отчет считан! Пожалуйста введите другую команду!");

            } else if (userInput == 3) {
                if (yearlyReport != null && monthlyReport != null) {
                    for (int m = 1; m <= MONTHS_COUNT; m++) {
                        if (yearlyReport.getSumProfitMonth(m) != monthlyReports.get(m).getSumProfit()) {
                            System.out.println("В " + TITlE_MONTH[m - 1] + "месяце допущена ошибка.");
                        } else {
                            System.out.println("Сверка отчетов за " + TITlE_MONTH[m - 1] + ", завершена успешно.");
                        }
                    }
                    yearlyReport.printAllIncomeAndExpense();
                    System.out.println("Информация завершена");
                } else {
                    System.out.println("Считайте месячные и годовые отчеты.");
                }

            } else if (userInput == 4) {
                if (monthlyReports != null) {
                    for (int m = 1; m <= MONTHS_COUNT; m++) {
                        System.out.println(TITlE_MONTH[m - 1]);
                    monthlyReports.get(m).getItemNameBigExpense();
                    monthlyReports.get(m).getItemNameBigIncome();
                    }
                } else {
                    System.out.println("Считайте месячные.");
                }

            } else if (userInput == 5) {
                if (yearlyReport != null) {
                    System.out.println(year);
                    yearlyReport.printInfoYear();
                } else {
                    System.out.println("Считайте годовые отчеты.");
                }

            } else if (userInput == 0) {
                System.out.println("Программа завершена.");
                break;
            } else {

                System.out.println("Извините, такой команды пока нет!");
            }
        }
    }

    public static void printMenu() {
        String menu = "ГЛАВНОЕ МЕНЮ.\n" +
                "Что вы хотите сделать?\n" +
                "1 - Считать все месячные отчёты\n" +
                "2 - Считать годовой отчёт\n" +
                "3 - Сверить отчёты\n" +
                "4 - Вывести информацию о всех месячных отчётах\n" +
                "5 - Вывести информацию о годовом отчёте\n" +
                "0 - Выход";
        System.out.println (menu);
    }
}
