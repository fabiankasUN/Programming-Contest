package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class twoEnds {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static int arr[];
	public static int dp[][];
	public static int n;

	public static int dfs(int turn, int izq, int der, int puntaje) {
		if (izq > der) {
			return puntaje;
		}

		/*if (dp[izq][der] != Integer.MAX_VALUE && turn == 0) {
			return dp[izq][der];
		}*/

		int ans = Integer.MAX_VALUE;
		if (turn == 0) {
			if (arr[der] > arr[izq]) {
				ans = dfs(1, izq, der - 1, puntaje + arr[der]);
			} else {
				ans = dfs(1, izq + 1, der, puntaje + arr[izq]);
			}
		} else {
			ans = Math.min(dfs(0, izq, der - 1, puntaje - arr[der]),
					dfs(0, izq + 1, der, puntaje - arr[izq]));
		}
		if (turn == 0)
			return dp[izq][der] = ans;

		return ans;

	}

	public static int dfs2(int turn, int izq, int der, int puntaje) {
		if (izq > der) {
			return puntaje;
		}

		/*if (dp[izq][der] != Integer.MAX_VALUE && turn == 1) {
			return dp[izq][der];
		}*/

		int ans = Integer.MAX_VALUE;
		if (turn == 0) {
			if (arr[der] > arr[izq]) {
				ans = dfs2(1, izq, der - 1, puntaje + arr[der]);
			} else {
				ans = dfs2(1, izq + 1, der, puntaje + arr[izq]);
			}
		} else {
			ans = Math.min(dfs2(0, izq, der - 1, puntaje - arr[der]),
					dfs2(0, izq + 1, der, puntaje - arr[izq]));
		}

		if (turn == 1)
			return dp[izq][der] = ans;
		return ans;

	}

	public static void init() {
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		long inicio = System.currentTimeMillis();
		StringBuilder out = new StringBuilder();
		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		String line = "";
		int times = 1;
		while ((line = in.readLine()) != null) {
			if (line.equals("0"))
				break;

			int dates[] = atoi(line);
			n = dates[0];
			arr = new int[n];
			dp = new int[n + 1][n + 1];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = dates[i + 1];
			}

			init();
			int res = dfs2(1, 0, arr.length - 1, 0);
			//init();
			//int res2 = dfs(0, 0, arr.length - 1, 0);
			int res2 = Integer.MAX_VALUE;
			
			System.out.println("In game " + times++
					+ ", the greedy strategy might lose by as many as "
					+ -Math.min(res, res2) + " points.");
			// System.out.println(res2);
		}
		// System.out.print(out);
		
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
