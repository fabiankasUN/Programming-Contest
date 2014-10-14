package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DD {

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
	public static long cont = 0;

	public static ArrayList<Integer> KMP(int[] cadena, int[] patron) {

		int[] fallas = new int[patron.length + 1];

		ArrayList<Integer> coincidencias = new ArrayList<Integer>();
		int pos;
		Arrays.fill(fallas, -1);
		// funcion de fallas
		for (int i = 1; i <= patron.length; i++) {
			pos = fallas[i - 1];

			while (pos != -1 && patron[pos] != patron[i - 1]) {
				pos = fallas[pos];
			}

			fallas[i] = pos + 1;
		}

		// printArrayInt(cadena);
		// printArrayInt(patron);
		// printArrayInt(fallas);
		// cp = pocision cadena, pp = pocision patron
		for (int cp = 0, pp = 0; cp < cadena.length; cp++) {

			while (pp != -1
					&& (pp == patron.length || patron[pp] != cadena[cp])) {
				pp = fallas[pp];

			}

			pp++;

			if (pp == patron.length) {
				coincidencias.add(cp + 1 - patron.length);
				cont++;

			}
		}

		return coincidencias;
	}

	public static void main(String[] args) throws IOException {

		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int dates[] = readInts(nextLine());
		int[] c1 = readInts(nextLine());
		int[] c2 = readInts(nextLine());

		if (c2.length == 1) {
			System.out.println(c1.length);
			return;
		}
		int k = Integer.MAX_VALUE;
		int A[] = new int[c1.length - 1], B[] = new int[c2.length - 1];
		for (int i = 0; i < A.length; i++) {
			A[i] = c1[i + 1] - c1[i];
		}
		for (int i = 0; i < B.length; i++) {
			B[i] = c2[i + 1] - c2[i];
		}
		KMP(A, B);

		System.out.println(cont);

	}

}
