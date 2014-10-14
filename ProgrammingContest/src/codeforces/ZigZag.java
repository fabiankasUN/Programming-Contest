package codeforces;
import java.io.IOException;

public class ZigZag {

	public static int longestZigZag(int arr[]) {

		int dp[][] = new int[2][arr.length];
		dp[0][0] = 1;
		dp[1][0] = 1;

		int mx = 0;
		if (arr.length >= 1)
			mx++;
		for (int i = 1; i < dp[0].length; i++) {
			int maxA = 0;
			int maxB = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[i] > arr[j] && dp[1][j] > maxA) {
					maxA = dp[1][j];
				}
				if (arr[i] < arr[j] && dp[0][j] > maxB) {
					maxB = dp[0][j];
				}
			}
			dp[0][i] = maxA + 1;
			dp[1][i] = maxB + 1;
			mx = Math.max(mx, Math.max(dp[0][i], dp[1][i]));
		}

		return mx;

	}

	public static void main(String[] args) throws IOException {

		int n[] = { 1, 7, 4, 9, 2, 5 };// readInts(in.readLine());
		System.out.println(longestZigZag(n));

	}

}
