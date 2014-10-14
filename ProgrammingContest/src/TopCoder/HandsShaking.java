package TopCoder;
public class HandsShaking {

	public static long dp[];
	public static int n;

	public static long dp(int x) {
		if (dp[x] > 0) {
			return dp[x];
		}
		if (x == 0) {
			return dp[x] = 1;
		}
		long ans = 0;
		for (int i = 0; i <= x - 2; i += 2) {
			ans += dp(i) * dp(x - i - 2);
		}

		return dp[x] = ans;
	}

	public static long countPerfect(int m) {
		long res = 0;
		n = m;
		dp = new long[n + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = 0;
		}
		dp[0] = 1;

		return dp(m);
	}

	public static void main(String[] args) {
		System.out.println(countPerfect(4));
	}
}
