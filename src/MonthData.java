public class MonthData {

    public int expenses;
    public int income;
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOn;

    public MonthData(String name, boolean isExpense, int quantity, int sum) {
        this.itemName = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOn = sum;
    }

}