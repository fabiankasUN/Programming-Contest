package COJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Dots {

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

	public static int nextInt(String cad) throws IOException {
		return Integer.parseInt(cad);
	}

	public static double nextDouble(String cad) throws IOException {
		return Double.parseDouble(cad);
	}

	public static BufferedReader in;

	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		long dp[] = new long[25001];
		dp[0] = 1;
		dp[1]=3;
		dp[2] = 6;
		long cont = 4;
		for (int i = 3; i < 25001; i++) {
			dp[i] = dp[i - 1] + cont;
			cont ++;
		}
		for (int i = 0; i < n; i++) {
			long k = scan.nextLong();
			boolean s = false;
			for (int j = 0; j < dp.length; j++) {
				if (dp[j] == k) {
					System.out.println("Yes");
					s = true;
					break;
				}
			}
			if (!s) {
				System.out.println("No");
			}

		}

	}
}
