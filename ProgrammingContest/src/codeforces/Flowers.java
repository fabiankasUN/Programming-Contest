package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Flowers {

	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static long[] readLongs(String cad) {
		String read[] = cad.split(" ");
		long res[] = new long[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Long.parseLong(read[i]);
		}
		return res;
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

	static void printMatrixInt(int[][] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				if (j > 0)
					System.out.print(" ");
				System.out.print(array[i][j]);
			}
			System.out.println();
		}

	}

	public static int max(int arr[]) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		return max;
	}

	public static int min(int arr[]) {
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
		}
		return min;
	}

	public static BufferedReader in;

	public static int s, n;
	public static int sum = 0;

	public static boolean validate(String res) {
		int cont = 0;
		for (int i = 0; i < res.length(); i++) {
			if (cont >= 0 && res.charAt(i) == 'W') {
				cont++;
			} else if (cont % s != 0 && res.charAt(i) == 'R') {
				return false;
			} else if (res.charAt(i) == 'W') {
				cont = 0;
			}
		}
		if (cont % s == 0)
			return true;
		return false;
	}

	public static void dfs(int i, int count, String res) {
		if (i == n) {
			if (validate(res))
				// sum++;

				System.out.println(res);
			return;
		}
		if (count > 0)
			dfs(i + 1, count - 1, res + "W");
		dfs(i + 1, count, res + "R");
	}

	public static int MOD = 1000000007;

	public static void main(String[] args) throws IOException {

		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int d[] = readInts(in.readLine());
		int t = d[0];
		int k = d[1];

		long dp[] = new long[100001];
		long sum[] = new long[100001];
		dp[0] = 0;
		for (int i = 1; i < k; i++) {
			dp[i] = 1;
		}
		dp[k] = 2;

		for (int i = k + 1; i < 100001; i++) {
			dp[i] = (dp[i - 1] + dp[i - k]) % MOD;
		}

		sum[0] = 0;

		for (int i = 1; i < dp.length; i++) {
			sum[i] = (dp[i] + sum[i - 1]) % MOD;
		}
		for (int i = 0; i < t; i++) {
			int r[] = readInts(in.readLine());
			System.out.println((sum[r[1]] - sum[r[0] - 1] + MOD) % MOD);
		}

	}
}
