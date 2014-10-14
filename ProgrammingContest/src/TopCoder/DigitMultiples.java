package TopCoder;
public class DigitMultiples {

	public static int dp[][];

	public static int dp(int digit, int index) {
		if (index > a.length)
			return 0;
		if (dp[digit][index] > 0)
			return dp[digit][index];

		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j < b.length; j++) {
				if ((a[index] - '0') * i == (b[j] - '0')) {
					dp[i][index] = 1 + dp(i, index + 1);
				}
			}

		}

		return 0;

	}

	public static char a[];
	public static char b[];

	public static int getLongest(String single, String multiple) {
		a = single.toCharArray();
		b = multiple.toCharArray();

		dp = new int[a.length + 1][b.length + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = 0;
			}
		}
		int res = dp(0, 0);

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = 0;
			}
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(getLongest("3211113321571", "5555266420"));
	}
}
