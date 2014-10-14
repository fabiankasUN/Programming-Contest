package TopCoder;
public class Hotel {

	public static int c[], cost[];
	public static int m;
	public static int dp[][];

	public static int dp(int index, int total) {
		if (total >= m) {
			return 0;
		}
		if (dp[index][total] > 0) {
			return dp[index][total];
		}

		if (index >= c.length) {
			return 0;
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < cost.length; i++) {
			ans = Math.min(ans, dp(index, total + c[i]) + cost[i]);
		}

		return dp[index][total] = ans;
	}

	public static int marketCost(int minCustomers, int[] customers, int[] coste) {
		int res = 0;
		c = customers;
		cost = coste;
		m = minCustomers;
		dp = new int[c.length + 1][minCustomers + 101];

		// System.out.println(dp(0, 0));
		return dp(0, 0);
	}

	public static void main(String[] args) {
		int a[] = { 9, 11, 4, 7, 2, 8 };
		int b[] = { 4, 9, 3, 8, 1, 9 };
		marketCost(100, a, b);
	}
}
