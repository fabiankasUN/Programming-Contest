package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B {

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
		return 	res;
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

	public static class Node implements Comparable<Node> {
		int i;
		int dif;

		public Node(int i, int dif) {
			this.i = i;
			this.dif = dif;
		}

		@Override
		public int compareTo(Node o) {
			if (dif < o.dif)
				return -1;
			else if (dif > o.dif)
				return 1;
			return 0;
		}

	}

	public static void main(String[] args) throws IOException {

		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int n = nextInt();
		int arr[] = readInts(nextLine());
		Node nodos[] = new Node[n];
		int sum[] = new int[2005];
		int k = 0;

		for (int i = 0; i < arr.length; i++) {
			nodos[i] = new Node(i + 1, arr[i]);
			sum[arr[i]]++;
			if (sum[arr[i]] > 1) {
				k++;
			}
		}
		Arrays.sort(nodos);

		if (k <= 1) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
			for (int i = 0; i < arr.length; i++) {
				System.out.print(nodos[i].i);
				if (i != arr.length - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
			int cond = 0;
			int a = 0, b = 0;
			for (int i = 1; i < arr.length; i++) {
				if (nodos[i].dif == nodos[i - 1].dif) {
					Node no = nodos[i];
					nodos[i] = nodos[i - 1];
					nodos[i - 1] = no;
					a = i - 1;
					b = i;
					break;

				}
			}
			for (int i = 0; i < arr.length; i++) {
				System.out.print(nodos[i].i);
				if (i != arr.length - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
			Node c = nodos[a];
			nodos[a] = nodos[b];
			nodos[b] = c;

			for (int i = 1; i < arr.length; i++) {
				if (nodos[i].dif == nodos[i - 1].dif && i - 1 != a && i != b) {
					Node no = nodos[i];
					nodos[i] = nodos[i - 1];
					nodos[i - 1] = no;
					a = i - 1;
					b = i;
					break;

				}
			}

			for (int i = 0; i < arr.length; i++) {
				System.out.print(nodos[i].i);
				if (i != arr.length - 1) {
					System.out.print(" ");
				}
			}

		}

		Arrays.sort(nodos);

	}
}
