package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BBBB {

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

	public static class Floyd {

		public static int grafo[][];
		public static int distancias[][];
		public static int padres[][];
		public static final int INF = (Integer.MAX_VALUE - 1) / 2;

		public void inint(int n) {
			grafo = new int[n][n];
			distancias = new int[n][n];
			padres = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int k = 0; k < n; k++) {
					if (i != k)
						grafo[i][k] = INF;
				}
			}
		}

		void floyd(int rows) {

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < rows; j++) {
					distancias[i][j] = grafo[i][j];
					padres[i][j] = i;
					if (grafo[i][j] == INF || i == j) {
						padres[i][j] = -1;
					}
				}
			}

			for (int k = 0; k < rows; k++) {
				// print(distancias);
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < rows; j++) {

						if (distancias[i][j] > distancias[i][k]
								+ distancias[k][j]) {
							distancias[i][j] = distancias[i][k]
									+ distancias[k][j];
							padres[i][j] = padres[k][j];
						}
					}
				}
			}

			// print(distancias);
			// System.out.println();
			// print(padres);
		}

		public static void print(int grafo[][]) {
			for (int i = 0; i < grafo.length; i++) {
				for (int k = 0; k < grafo.length; k++) {
					System.out.print(grafo[i][k] + " ");
				}
				System.out.println();
			}
		}
	}

	public static BufferedReader in;
	public static int dx[] = { 0, 0, 1, -1 };
	public static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {

		File file = new File("entrada");
		if (file.exists()) {
			in = new BufferedReader(new FileReader(file));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int dates[] = readInts(in.readLine());
		int n = dates[0];
		int m = dates[1];
		Floyd f = new Floyd();

		char mapX[] = in.readLine().toCharArray();
		char mapY[] = in.readLine().toCharArray();
		f.inint(n * m);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int index = i * m + j;
				for (int k = 0; k < dx.length; k++) {
					int px = i + dx[k];
					int py = j + dy[k];
					if (px >= 0 && px < n && py >= 0 && py < m) {
						int d = px * m + py;
						if (k == 0) {
							if (mapX[i] == '>') {
								f.grafo[index][d] = 1;
							}
						}
						if (k == 1) {
							if (mapX[i] == '<')
								f.grafo[index][d] = 1;
						}
						if (k == 2) {
							if (mapY[j] == 'v')
								f.grafo[index][d] = 1;
						}
						if (k == 3) {
							if (mapY[j] == '^')
								f.grafo[index][d] = 1;
						}
					}
				}
			}
		}

		f.floyd(n * m);

		boolean salir = false;
		p: for (int i = 0; i < n * m; i++) {
			for (int j = 0; j < m * n; j++) {
				if (i != j && f.distancias[i][j] == f.INF) {
					salir = true;
					break p;
				}
			}
		}
		printMatrixInt(f.distancias);
		if (salir) {
			System.out.println("NO");
		} else
			System.out.println("YES");

	}
}
