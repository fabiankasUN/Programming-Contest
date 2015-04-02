package Uri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PopulationIncrease {
	public static double[] readInts(String cad) {
		String read[] = cad.split(" ");
		double res[] = new double[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Double.parseDouble(read[i]);
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
			double d[] = readInts(in.readLine());
			double x = d[2];
			double y = d[3];
			long a = (int)d[0];
			long b = (int)d[1];
			int an = 0;

			boolean flag = false;
			while (a <= b) {
				if (an > 100) {
					flag = true;
					break;
				}
				a *= (x / 100) + 1;
				b *= (y / 100) + 1;
				an++;
			}
			if (flag || an> 100) {
				System.out.println("Mais de 1 seculo.");
			} else {
					System.out.println(an + " anos.");
			}
		}

	}
}
