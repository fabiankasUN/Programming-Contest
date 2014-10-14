package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ralyt {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	static void printArrayInt(int[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				System.out.print(" ");
			System.out.print(array[i]);
		}
		System.out.println();
	}

	public static void print(int dp[][]) {
		for (int i = 0; i < dp.length; i++) {
			for (int k = 0; k < dp.length; k++) {
				System.out.print(dp[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void print(boolean dp[][]) {
		for (int i = 0; i < dp.length; i++) {
			for (int k = 0; k < dp.length; k++) {
				System.out.print((dp[i][k]?"0":"1") + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		int n = Integer.parseInt(line);
		int dp[][] = new int[n][n];
		int m[][] = new int[n][n];
		boolean v[][] = new boolean[n][n];
		long res = 0;
		for (int i = 0; i < n; i++) {
			m[i] = atoi(in.readLine());
		}
		dp[0][0] = m[0][0];
		for (int i = 1; i < m.length; i++) {
			dp[0][i] = dp[0][i - 1] + m[0][i];
			dp[i][0] = dp[i - 1][0] + m[i][0];
		}
		for (int i = 1; i < v.length; i++) {
			for (int j = 1; j < v.length; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
			}
		}
		print(dp);
		

		res += dp[dp.length - 1][dp.length - 1];
		int i = dp.length - 1;
		int j = dp.length - 1;
		int a;
		int b;
		while (i > 0 || j > 0) {
			v[i][j] = true;
			if (i > 0)
				a = dp[i - 1][j];
			else
				a = -1;

			if (j > 0)
				b = dp[i][j - 1];
			else
				b = -1;

			if (a > b)
				i--;
			else
				j--;
		}
		print(v);
		dp[0][0] = 0;
		for (i = 1; i < m.length; i++) {
			dp[0][i] = dp[0][i - 1] + (v[0][i] ? 0 : m[0][i]);
			dp[i][0] = dp[i - 1][0] + (v[i][0] ? 0 : m[i][0]);
		}
		print(dp);
		for (i = 1; i < v.length; i++) {
			for (j = 1; j < v.length; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
						+ (v[i][j] ? 0 : m[i][j]);
			}
		}
		print(dp);
		res += dp[dp.length - 1][dp.length - 1];
		System.out.println(res);
	}
}
