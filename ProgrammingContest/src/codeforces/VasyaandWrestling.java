package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VasyaandWrestling {

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
		long a = 0;
		long b = 0;
		int ult = -1;
		int k;

		int indexA = 0;
		int indexB = 0;
		int r1[] = new int[n], r2[] = new int[n];

		for (int i = 0; i < n; i++) {
			k = Integer.parseInt(in.readLine());
			if (k < 0) {
				b += (-k);
				ult = 1;
				r2[indexB] = (-k);
				indexB++;
			} else {
				a += k;
				ult = 0;
				r1[indexA] = k;
				indexA++;
			}
		}
		if (a > b) {
			System.out.println("first");
		} else if (a < b) {
			System.out.println("second");
		} else {
			for (int i = 0; i < Math.min(indexA, indexB); i++) {
				if (r1[i] > r2[i]) {
					System.out.println("first");
					return;
				} else if (r2[i] > r1[i]) {
					System.out.println("second");
					return;
				}
			}
			if (indexA > indexB) {
				System.out.println("first");
			} else if (indexB > indexA) {
				System.out.println("second");
			} else if (ult == 0)
				System.out.println("first");
			else
				System.out.println("second");
		}

	}
}
