package HackerRank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheMaximumSubarray {

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
		for (int i = 0; i < n; i++) {
			int s = Integer.parseInt(in.readLine());
			int arr[] = readInts(in.readLine());
			int max = -100000;
			int max2 = -100000;
			int acum = max;
			int acum2 = max;
			for (int j = 0; j < arr.length; j++) {
				if (arr[j] > 0) {
					if (acum < 0)
						acum = 0;
					acum += arr[j];
					max = Math.max(max, acum);
				} else {
					max = Math.max(max, arr[j]);
				}

				if (arr[j] < 0 && acum2 + arr[j] < 0) {
					max2 = Math.max(max2, arr[j]);
					acum2 = 0;
				} else {
					if (acum2 < 0)
						acum2 = 0;
					acum2 += arr[j];
					max2 = Math.max(max2, acum2);

				}
			}
			out.append(max2 + " " + max + "\n");
		}
		System.out.print(out);

	}
}
