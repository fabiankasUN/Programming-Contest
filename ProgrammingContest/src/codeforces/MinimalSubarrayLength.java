package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinimalSubarrayLength {

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

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		StringBuilder out = new StringBuilder();
		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		String line;
		while ((line = in.readLine()) != null) {

			int d[] = readInts(line);
			int arr[] = readInts(in.readLine());
			long r = Integer.MIN_VALUE;
			int num = d[1];
			for (int i = 0; i < arr.length; i++) {
				r = Math.max(r, arr[i]);
			}
			if (r > num) {
				System.out.println(1);
			} else {

				int a = 0, b = 1;
				long actually = arr[0];
				int max = Integer.MAX_VALUE;
				while (b < arr.length) {
					while (actually < num && b < arr.length) {
						if (actually == Integer.MIN_VALUE)
							actually = 0;
						actually += arr[b];
						b++;
					}

					if (actually >= num) {
						max = Math.min(max, (b - a));
					}
					while (actually - (arr[a] < 0 ? -arr[a] : arr[a]) > num
							&& a < arr.length) {
						actually -= arr[a];
						a++;
						max = Math.max(max, b - a);
					}
					b++;
					a = b;
				}
				System.out.println(max);
			}

		}

	}
}
