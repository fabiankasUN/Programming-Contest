package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountingPatterns {

	public static int[] atoi(String cad) {
		String line[] = cad.split(" ");
		int arr[] = new int[line.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(line[i]);
		}

		return arr;
	}

	public static int arr[];
	public static int k;
	public static int res;
	public static Tree arbol;

	public static void print(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static class Node {
		Node nodes[];

		public Node() {
			this.nodes = new Node[26];
		}

	}

	public static class Tree {
		Node tree[];

		public Tree() {
			this.tree = new Node[26];
			for (int i = 0; i < 26; i++) {
				tree[i] = new Node();
			}
		}

		public boolean find(int arr[], int p) {

			int cont = 0;
			// System.out.println(arr.length + " "+ k);
			Node temp = tree[arr[p] + k];
			while (cont < arr.length) {
				if (temp.nodes[arr[p] + k] == null)
					return false;
				temp = temp.nodes[arr[p] + k];
				p = (p + 1) % arr.length;
				cont++;
			}
			return true;
		}

		public boolean find2(int arr[], int p) {

			int cont = 0;

			Node temp = tree[(-arr[p]) + k];
			while (cont < arr.length) {
				if (temp.nodes[(-arr[p]) + k] == null)
					return false;
				temp = temp.nodes[(-arr[p]) + k];
				p = (p + 1) % arr.length;
				cont++;
			}
			return true;
		}

		public boolean find3(int arr[], int p) {

			int cont = 0;

			Node temp = tree[(-arr[p]) + k];
			while (cont < arr.length) {
				if (temp.nodes[(-arr[p]) + k] == null)
					return false;
				temp = temp.nodes[(-arr[p]) + k];
				// p = (p - 1);
				p = (p + 1) % arr.length;
				cont++;
			}
			return true;
		}

		public boolean find4(int arr[], int p) {

			int cont = 0;

			Node temp = tree[(arr[p]) + k];
			while (cont < arr.length) {
				if (temp.nodes[(arr[p]) + k] == null)
					return false;
				temp = temp.nodes[(arr[p]) + k];
				// p = (p - 1);
				p = (p + 1) % arr.length;
				cont++;
			}
			return true;
		}

		public boolean add(int arr[]) {
			for (int i = 0; i < arr.length; i++) {
				if (find(arr, i))
					return false;
			}
			for (int i = 0; i < arr.length; i++) {
				if (find2(arr, i))
					return false;
			}
			int arr2[] = new int[arr.length];
			for (int j = 0; j < arr2.length; j++) {
				arr2[j] = arr[arr.length - 1 - j];
			}
			for (int i = 0; i < arr.length; i++) {

				if (find3(arr2, i))
					return false;
			}
			for (int i = 0; i < arr.length; i++) {
				if (find4(arr2, i))
					return false;
			}

			Node temp = tree[arr[0] + k];

			for (int i = 0; i < arr.length; i++) {
				if (temp.nodes[arr[i] + k] == null) {

					temp.nodes[arr[i] + k] = new Node();
					temp = temp.nodes[arr[i] + k];
				} else {
					temp = temp.nodes[arr[i] + k];
				}
			}

			return true;
		}
	}

	public static void dfs(int arr[], int ant, int i) {
		if (i < arr.length)
			for (int j = (i == 0 ? (0) : Math.max(-k,
					((k - ((ant + (arr.length - i) * (k))))))); j <= k; j++) {
				arr[i] = j;
				if (ant + arr[i] == 0 && i == arr.length - 1) {
					if (arbol.add(arr)) {
						res++;
						r += "(";
						for (int w = 0; w < arr.length; w++) {
							if (w > 0)
								r += "," + arr[w];
							else
								r += arr[w];
						}
						r += ")\n";
						// print(arr);
					}

				} else if (ant + arr[i] <= (arr.length - i - 1) * (k)
						&& ant + arr[i] >= -((arr.length - i - 1) * (k))) {
					dfs(arr, ant + arr[i], i + 1);
				}
				// dfs(arr, ant + arr[i], i + 1);

			}
	}

	public static String r;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// Scanner scan = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		String line;
		int dp[][] = new int[13][13];
		/*
		 * dp[1][11] = 1; dp[2][10] = 11; dp[3][9] = 30; dp[4][8] = 285;
		 * dp[5][7] = 1599; dp[6][6] = 9058; dp[7][5] = 32900; dp[8][4] = 73558;
		 * dp[9][3] = 74002; dp[10][2] = 22159; dp[11][1] = 657; dp[12][0] = 1;
		 */
		/*
		 * dp[11][0] = 1; dp[10][1] = 290; dp[9][2] = 5228; dp[8][3] = 13023;
		 * dp[7][4] = 10011; dp[6][5] = 4029; dp[5][6] = 920; dp[4][7] = 204;
		 * dp[3][8] = 25; dp[2][9] = 10; dp[1][10] = 1;
		 */

		/*
		 * for (int i = 0; i < 12; i++) { for (int j = 0; j < 12; j++) { if (i +
		 * j < 12) { k = j; res = 0; arr = new int[i]; arbol = new Tree(); for
		 * (int l = 0; l < arr.length; l++) { arr[l] = Integer.MIN_VALUE; }
		 * dfs(arr, 0, 0); dp[i][j] = res; } } }
		 */

		/*
		 * for (int i = 0; i < dp.length; i++) { for (int j = 0; j < dp.length;
		 * j++) { System.out.print(dp[i][j] + " "); } System.out.println(); }
		 */

		for (int i = 1; i <= dp.length; i++) {
			for (int j = 0; j <= 12; j++) {
				if (i + j <= 12)
					System.out.println(i + " " + j);
			}
		}

		int t = 0;
		while ((line = in.readLine()) != null && !line.equals("")) {

			// while (scan.hasNext()) {
			if (t++ > 0)
				out.append("\n");
			int c[] = atoi(line);
			int n = c[0];
			k = c[1];

			if (n == 0 && k == 0)
				break;
			// k = j;
			res = 0;
			arr = new int[n];
			arbol = new Tree();
			for (int l = 0; l < arr.length; l++) {
				arr[l] = Integer.MIN_VALUE;
			}
			r = "";
			dfs(arr, 0, 0);
			dp[n][k] = res;
			System.out.println(dp[n][k]);
			// out.append(dp[n][k] + "\n");
			// out.append(r);
			// out.append(n+" "+k+"  "+dp[n][k] + "\n");
			// System.out.println(dp[n][k]);

		}

		System.out.print(out);
		// out.deleteCharAt(out.length() - 1);
		// System.out.print(out);

	}

}
