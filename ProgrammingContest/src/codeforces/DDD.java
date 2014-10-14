package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class DDD {

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

	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);

	}

	public static int n, k;

	public static boolean ok(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (gcd(arr[i], arr[j]) != k)
					return false;
			}
		}
		return true;
	}

	public static boolean cond = false;

	public static void dfs(int cont, int ant, int actual, int arr[], int r,
			int s) {
		if (cont == 4) {
			if (ok(arr) == true) {
				System.out.println(s + " " + Arrays.toString(arr));
				if (s == n) {
					cond = true;
					return;
				}
				dfs(1, actual - 1 + k * 2, actual + k * 2, new int[] {
						actual - 1 + k * 2, 0, 0, 0 }, 1000000, s + 1);
			}
		} else {
			for (int i = actual; i < r && !cond; i++) {
				if (gcd(ant, i) == k) {

					arr[cont] = i;
					dfs(cont + 1, i, i + 1, arr, r, s);
				}

			}

		}
	}

	public static void main(String[] args) throws IOException {
		StringBuilder out = new StringBuilder();
		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));
		int d[] = readInts(in.readLine());
		n = d[0];
		k = d[1];
		int r=k;
		int max = -1;
		for (int i = 0; i < n; i++) {
			out.append(r + " " + (r+(k)) + " " + (r+(k*2)) +" " + (r+(k*4)) +"\n");
			max=Math.max(max, (r+(k*4)));
			r+=6*k;
		}
		System.out.println(max);
		System.out.print(out);
		//dfs(1, k, k + 1, new int[] { k, 0, 0, 0 }, 1000000, 1);

	}
}
