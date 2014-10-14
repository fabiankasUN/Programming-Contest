package TopCoder;
public class Egalitarianism3Easy {

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
			// print(padres);

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
		}
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

	public static int total;

	public static int max(int arr[], int distancias[][], int nodo) {
		int m = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0 && arr[i] > total) {
				boolean cond = true;
				ciclow: for (int j = 0; j < distancias.length; j++) {
					if (distancias[nodo][j] == i && j != nodo) {

						for (int vecinos = 0; vecinos < distancias.length; vecinos++) {//
							if (distancias[nodo][vecinos] == i
									&& vecinos != nodo && j != vecinos) {
								if (distancias[nodo][vecinos] != distancias[j][vecinos]) {
									cond = false;
									break ciclow;
								}
							}
						}
					}
				}
				if (cond == true) {
					if (total < arr[i])
						total = arr[i];
				}

			}
		}

		return m;
	}

	public static int maxCities(int n, int[] a, int[] b, int[] len) {

		Floyd floyd = new Floyd();
		floyd.inint(n);

		for (int i = 0; i < a.length; i++) {
			floyd.grafo[a[i] - 1][b[i] - 1] = len[i];
			floyd.grafo[b[i] - 1][a[i] - 1] = len[i];
		}
		floyd.floyd(n);
		total = 0;
		int dp[] = new int[11000];
		for (int i = 0; i < len.length; i++) {
			for (int u = 0; u < dp.length; u++) {
				dp[u] = 0;
			}
			for (int j = 0; j < floyd.distancias.length; j++) {
				if (i != j)
					dp[floyd.distancias[i][j]]++;
			}

			int p = max(dp, floyd.distancias, i);
			if (total < p)
				total = p;
		}

		return total + 1;
	}

	public static void main(String[] args) {
		int a[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		int b[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int len[] = { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000 };
		System.out.println(maxCities(10, a, b, len));
	}

}
