import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("tiny.txt"));
        String symbol = in.next();
        int n = in.nextInt();

        // TODO: create the three arrays, each with n elements
        int[] year = new int[n];
        int[] month = new int[n];
        double[] price = new double[n];

        loadData(in, year, month, price);

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
        double lowestprice = prices[0];

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < prices[0]) {
                lowestprice = prices[i];
            }
        }
        return lowestprice;
    }
    
    public static int indexOfHighest(double[] prices) {
		double highestprice = prices[prices.length];
		
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] > prices[prices.length]) 
				highestprice = prices[i];
			}
		}
		return highestprice;
}
