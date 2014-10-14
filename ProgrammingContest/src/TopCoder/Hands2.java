package TopCoder;
public class Hands2 {

	public static long dp[] = new long[52];

	public static long countPerfect(int n) {
		System.out.println(dp(n));
		printArrayInt(dp);
		return dp(n);
	}
	static void printArrayInt(long[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				System.out.print(" ");
			System.out.print(array[i]);
		}
		System.out.println();
	}

	public static long dp(int n) {
		if (dp[n] > 0)
			return dp[n];
		if (n == 0)
			dp[n] = 1;
		for (int i = 0; i <= n - 2; i += 2) {
			dp[n] += dp(i) * dp(n - i - 2);
		}
		return dp[n];
	}

	public static void main(String[] args) {
		countPerfect(50);
		
	}

}
