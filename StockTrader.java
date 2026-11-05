import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("rising.txt"));
        String symbol = in.next();
        int n = in.nextInt();

        // TODO: create the three arrays, each with n elements
        int[] year = new int[n];
        int[] month = new int[n];
        double[] price = new double[n];

        loadData(in, year, month, price);
		int low = indexOfLowest(price);
		int hi  = indexOfHighest(price);

        System.out.println(symbol + " lowest: $" + price[low] + " in " + monthName(month[low]) + " " + year[low]);
		System.out.println(symbol + " highest: $" + price[hi] + " in " + monthName(month[hi]) + " " + year[hi]);
        

        // TODO: call loadData, then printData
    }
    public static void loadData(Scanner in, int[] years, int[] months, double[] prices) {
        for (int i = 0; i < years.length; i++) {
            years[i] = in.nextInt();
            months[i] = in.nextInt();
            prices[i] = in.nextDouble();
        }
        printData(years, months, prices);
    }

    public static String monthName(int month) {
        String[] name = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return name[month - 1];
    }

    public static void printData(int[] years, int[] months, double[] prices) {
        for (int i = 0; i < months.length; i++) {
            System.out.print(monthName(months[i]) + " " + years[i] + ": $");
            System.out.printf("%.2f%n", prices[i]);
        }
    }
    public static int indexOfLowest(double[] prices) {
        int lowestIndex = 0;

        for (int i = 1; i < prices.length; i++) {
   
            if (prices[i] < prices[lowestIndex]) {
                lowestIndex = i;
     
            }
        }
        return lowestIndex;
    }

    public static int indexOfHighest(double[] prices) {
        int highestprice = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > highestprice) {
                highestprice = i;
            }
        }
        return highestprice;
    }
}
