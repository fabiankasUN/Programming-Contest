package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class reloj {
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

		String a = in.readLine();
		String b = in.readLine();
		int r1 = Integer.parseInt(a.substring(0, 2));
		int r2 = Integer.parseInt(b.substring(0, 2));
		int s1 = Integer.parseInt(a.substring(3, 5));
		int s2 = Integer.parseInt(b.substring(3, 5));
		String res = "";

		if (s1 >= s2) {
			if (s1 - s2 < 10)
				res += "0";

			res += (s1 - s2);
		} else {
			if (60 - (s2 - s1) < 10)
				res += "0";
			res += (60 - (s2 - s1)) + "";
		}

		if (r1 >= r2) {
			if (r1 == r2 && s1 < s2) {
				System.out.print(23);
			} else {
				if (s1 < s2)
					r2++;
				if (r1 - r2 < 10)
					System.out.print("0");
				System.out.print(r1 - r2);
			}
		} else {
			if (s1 < s2)
				r2++;
			if (24 - (r2 - r1) < 10)
				System.out.print("0");
			System.out.print(24 - (r2 - r1));
		}
		System.out.print(":");
		System.out.println(res);

	}

}
