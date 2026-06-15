import java.util.*;

public class StockBuyAndSell {

    // ===================================================
    // Approach 1 - Brute Force
    // Try every pair (buy day i, sell day j where j > i)
    // Track maximum profit
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static int maxProfitBrute(int[] prices) {
        int maxProfit = 0;
        int n = prices.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }
        return maxProfit;
    }

    // ===================================================
    // Approach 2 - Optimal (Single Pass)
    // Track minimum price seen so far (best buy day)
    // At each day, compute profit if sold today
    // Update maxProfit accordingly
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;          // found a cheaper buy day
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice); // sell today
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
        // Output: 5  →  Buy at 1, Sell at 6
    }
}
