package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Dividingcoins {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static int n;
	public static int tam;
	public static int p[];
	public static int dp[][];
	public static int max;

	public static int mochi(int index, int w) {
		if (w < 0) {
			return 0;
		}
		if (index == n)
			return 0;

		if (dp[index][w] != -1)
			return dp[index][w];

		int ans = -1;

		if (p[index] > w)
			ans = mochi(index + 1, w);
		else
			ans = Math.max(mochi(index + 1, w - p[index]) + p[index],
					mochi(index + 1, w));

		return dp[index][w] = ans;
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

		int k = Integer.parseInt(in.readLine());

		for (int i = 0; i < k; i++) {
			n = Integer.parseInt(in.readLine());
			if (n == 0) {
				in.readLine();
				out.append("0\n");
			} else {
				p = atoi(in.readLine());
				tam = 0;
				for (int j = 0; j < p.length; j++) {
					tam += p[j];
				}

				dp = new int[n + 1][(int) Math.ceil((tam / 2.0)) + 1];

				for (int j = 0; j < dp.length; j++) {
					for (int l = 0; l < dp[0].length; l++) {
						dp[j][l] = -1;
					}
				}
				int res = mochi(0, (int) Math.ceil((tam / 2.0)));
				int r = tam - res;
				out.append(Math.abs(r - res) + "\n");
			}
		}

		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
