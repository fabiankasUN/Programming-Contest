package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Dreamon {

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

	public static boolean ok(String cad) {
		int cont = 0;
		for (int c = 0; c < cad.length(); c++) {
			if (cad.charAt(c) == '+')
				cont++;
			else
				cont--;
		}

		if (cont == res)
			return true;
		return false;
	}

	public static int res;
	public static char a[];
	public static char b[];
	public static int suma, total;

	public static void dfs(int index, String cad) {
		if (index == a.length) {
			if (ok(cad)) {
				suma++;
			}
			total++;
			return;
		}

		if (b[index] == '?') {
			dfs(index + 1, cad + "+");
			dfs(index + 1, cad + "-");
		} else {
			dfs(index + 1, cad + "" + b[index]);
		}

	}

	public static void main(String[] args) throws IOException {

		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		a = in.readLine().toCharArray();
		b = in.readLine().toCharArray();
		suma = 0;
		total = 0;
		res = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == '+') {
				res++;
			} else
				res--;
		}
		dfs(0, "");
		double r = suma / (total * 1.0);
		System.out.println(String.format(Locale.UK, "%.12f", r));

	}
}
