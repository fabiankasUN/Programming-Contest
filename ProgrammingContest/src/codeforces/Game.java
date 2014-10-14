package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Game {
	// split de array
	public static long[] atoi(String cad) {
		String read[] = cad.split(" ");
		long res[] = new long[read.length + 1];
		for (int i = 1; i < read.length + 1; i++) {
			res[i] = Integer.parseInt(read[i - 1]);
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

	public static long dp[][];
	public static long sum[];
	public static long arr[];

	static void printMatrixInt(long[][] array) {
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
		System.out.println();

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

		long dates[] = atoi(in.readLine());
		int n = (int) dates[1];
		int m = (int) dates[2];
		int k = (int) dates[3];
		long res = 0;
		arr = atoi(in.readLine());
		if (m == 1) {
			Arrays.sort(arr);
			for (int i = n; i >= 1 + n - k; i--) {
				res += arr[i];
			}
			System.out.println(res);
		} else {

			sum = new long[n + 1];
			for (int i = 1; i <= n; i++) {
				sum[i] = sum[i - 1] + arr[i];
			}

			dp = new long[n + 1][n + 1];
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= (i / m); j++) {
					dp[i][j] = Math.max(dp[i - m][j - 1] + sum[i] - sum[i - m],
							dp[i - 1][j]);
				}
			}
			System.out.println(dp[n][k]);

		}

		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
