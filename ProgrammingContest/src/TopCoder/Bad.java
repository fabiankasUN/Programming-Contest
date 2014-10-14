package TopCoder;
import java.util.Arrays;

public class Bad {

	public static int dp[];

	public static int maxDonations(int[] donations) {
		dp = new int[50];
		dp[0] = donations[0];
		for (int i = 2; i < donations.length - 1; i++) {
			dp[i] = Math.max(donations[i] + dp[i - 2], dp[i - 1]);
		}
		int a = dp[donations.length - 2];
		Arrays.fill(dp, 0);
		dp[1] = donations[1];
		for (int i = 2; i < donations.length; i++) {
			dp[i] = Math.max(donations[i] + dp[i - 2], dp[i - 1]);
		}

		return Math.max(dp[donations.length - 1], a);
	}

	public static void main(String[] args) {
		int a[] = { 10, 3, 2, 5, 7, 8 };
		System.out.println(maxDonations(a));
	}
}
