package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Kamal {

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

		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int dates[] = readInts(in.readLine());
		int n = dates[0];
		int m = dates[1];
		char map[][] = new char[n][m];

		for (int i = 0; i < map.length; i++) {
			map[i] = in.readLine().toCharArray();
		}
		int l = 0, r = 0;
		int lTemp = 0, rTemp = 0;
		boolean pos = true;
		int max = 1;
		p: for (int i = 0; i < n; i++) {
			int cont = 0;
			lTemp = rTemp = 0;

			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'X' && j < l) {
					pos = false;
					break p;
				}
				if (map[i][j] == 'X' && cont == 0) {
					lTemp = j;
					rTemp = j;
					cont++;
				} else if (map[i][j] == 'X') {
					rTemp = j;
				} else if (cont > 0) {
					if (rTemp < r) {
						pos = false;
						break p;
					}

					if (cont == 0 && r == 0) {
						l = 0;
						r = m - 1;
					}
					break;
				}
			}
			if (rTemp < r || lTemp < l) {
				pos = false;
				break p;
			}
			if (i > 0) {

			}
			r = rTemp;
			l = lTemp;
		}

		System.out.println(pos);

	}
}
