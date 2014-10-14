package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BBB {

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
		
		
	public static void main(String[] args) throws IOException {

		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));
		int dates[] = readInts(nextLine());
		int n = dates[0];
		int k = dates[1];
		int p[] = readInts(nextLine());
		Arrays.sort(p);
		int cont = 0;
		int cc = 0;
		long total = 0;
		Queue<Integer> cola = new LinkedList<Integer>();
		while (cc < p.length) {
			for (int i = cola.size(); i < k && cont < p.length; i++) {
				cola.add(p[cont++]);
			}
			int pivote = cola.peek();
			while (!cola.isEmpty() && cola.peek() == pivote) {
				cola.poll();
				cc++;
			}
			total += (pivote - 1);

			if (cont == p.length && !cola.isEmpty()) {
				int temp = 1;
				while (!cola.isEmpty()) {
					temp = cola.poll();
					cc++;
					total += temp - pivote;
					pivote = temp;

				}
				total += temp - 1;
			} else {

				int last = pivote;
				while (!cola.isEmpty() && (cola.peek() - last)+cola.peek() < p[cont]) {
					total += cola.peek() - last;
					last=cola.poll();
					cc++;
				}

				total += (last - 1);
			}

		}
		System.out.println(total);

	}
}
