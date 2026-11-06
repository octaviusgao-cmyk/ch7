import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {
    public static void main(String[] args) throws FileNotFoundException {
       

        Scanner in = new Scanner(new File("sp500_1990_2025.txt"));
        String symbol = in.next();
        int n = in.nextInt();

        // TODO: create the three arrays, each with n elements
        int[] year = new int[n];
        int[] month = new int[n];
        double[] price = new double[n];

        loadData(in, year, month, price);
        int low = indexOfLowest(price);
        int hi = indexOfHighest(price);

        System.out.println(
            symbol + " lowest: $" + price[low] + " in " + monthName(month[low]) + " " + year[low]);
        System.out.println(
            symbol + " highest: $" + price[hi] + " in " + monthName(month[hi]) + " " + year[hi]);

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
        int highestIndex = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[highestIndex]) {
                highestIndex = i;
            }
        }
        return highestIndex;
    }

    public static double simulate(int[] years, int[] months, double[] prices) {
        double cash = START_CASH;
        int shares = 0;
        double boughtAt = 0.0;

        for (int i = 0; i < prices.length; i++) {
            double[] history = Arrays.copyOf(prices, i + 1);
            String action = decide(history, shares > 0, boughtAt);

            if (action.equals("BUY") && shares == 0) {
                int canBuy = (int) ((cash - FEE) / prices[i]);
                if (canBuy > 0) {
                    shares = canBuy;
                    cash = cash - shares * prices[i] - FEE;
                    boughtAt = prices[i];
                    System.out.printf("%s %d: BUY %d shares at $%.2f%n", monthName(months[i]),
                        years[i], shares, prices[i]);
                }
            } else if (action.equals("SELL") && shares > 0) {
                cash = cash + shares * prices[i] - FEE;
                System.out.printf("%s %d: SELL %d shares at $%.2f%n", monthName(months[i]),
                    years[i], shares, prices[i]);
                shares = 0;
                boughtAt = 0.0;
            }
        }
        return cash + shares * prices[prices.length - 1];
    }
    
    public static final double START_CASH = 10000.0;
	public static final double FEE = 10.0;
    public static String decide(double[] history, boolean holding, double boughtAt) {
		return buyAndHold(history, holding, boughtAt);
		}
		
	public static String buyAndHold(double[] history, boolean holding, double boughtAt) {
		return "BUY";
		}
}
