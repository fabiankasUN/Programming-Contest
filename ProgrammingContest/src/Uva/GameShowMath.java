package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class GameShowMath {

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

	public static int nextInt() throws IOException {
		return Integer.parseInt(in.readLine());
	}

	public static double nextDouble() throws IOException {
		return Double.parseDouble(in.readLine());
	}

	public static String nextLine() throws IOException {
		return in.readLine();
	}

	public static BufferedReader in;

	public static int arr[];
	public static boolean dp[][];
	public static int p;
	public static int MAX = 32000;
	public static char ops[] = new char[102];

	public static char OP[] = { '+', '-', '*', '/' };

	public static int ok(int j, int a, int b) {
		if (j == 0)
			return Math.abs(a + b) <= MAX ? a + b : 999999;
		if (j == 1)
			return Math.abs(a - b) <= MAX ? a - b : 999999;
		if (j == 2)
			return Math.abs(a * b) <= MAX ? a * b : 999999;
		if (j == 3) {
			if (b == 0 || a % b != 0)
				return 999999;
			return Math.abs(a / b) <= MAX ? a / b : 999999;
		}
		return 10000;
	}

	public static boolean fin = false;

	public static void dfs(int i, int value) {

		if (fin || dp[i][value + MAX] == true)
			return;

		dp[i][value + MAX] = true;

		if (i == p + 1) {

			if (value == arr[p + 1]) {
				for (int j = 1; j < arr.length - 1; j++) {
					if (j != arr.length - 2)
						out.append(arr[j] + "" + ops[j]);
					else
						out.append("" + arr[j]);
				}
				out.append("=" + arr[p + 1] + "\n");
				fin = true;
			}
			return;
		}

		for (int j = 0; j < 4; j++) {
			int res = ok(j, value, arr[i]);
			// 5 + 3
			if (res != 999999) {
				ops[i - 1] = OP[j];
				dfs(i + 1, res);
			}
		}

	}

	public static StringBuilder out = new StringBuilder();

	public static void main(String[] args) throws IOException {

		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int n = nextInt();
		dp = new boolean[102][64001];

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < dp.length; j++) {
				for (int k = 0; k < dp[0].length; k++) {
					dp[j][k] = false;
				}
			}
			fin = false;
			arr = readInts(nextLine());
			p = arr.length - 2;

			dfs(2, arr[1]);
			if (fin == false) {
				out.append("NO EXPRESSION\n");
			}
		}
		System.out.print(out);
	}
}
