package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCCC {

	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]) - 1;
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

	public static void main(String[] args) throws IOException {

		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int n = nextInt();
		String cad[] = new String[n];
		String last[] = new String[n];
		for (int i = 0; i < last.length; i++) {
			String c[] = nextLine().split(" ");
			cad[i] = c[0];
			last[i] = c[1];
		}
		int arr[] = readInts(nextLine());
		String ant = "@";
		for (int i = 0; i < arr.length; i++) {
			if (ant.equals("@")) {
				ant = cad[arr[i]].compareTo(last[arr[i]]) < 0 ? cad[arr[i]]
						: last[arr[i]];
			} else {
				String min = cad[arr[i]].compareTo(last[arr[i]]) < 0 ? cad[arr[i]]
						: last[arr[i]];
				;
				String max = cad[arr[i]].compareTo(last[arr[i]]) >= 0 ? cad[arr[i]]
						: last[arr[i]];
				;
				if (min.compareTo(ant) <= 0 && max.compareTo(ant) <= 0) {
					System.out.println("NO");
					return;
				} else {
					if (min.compareTo(ant) > 0) {
						ant = min;
					} else {
						ant = max;
					}
				}
			}
		}
		System.out.println("YES");
		return;
	}
}
