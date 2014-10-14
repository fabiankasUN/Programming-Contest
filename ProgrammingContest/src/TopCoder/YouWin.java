package TopCoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YouWin {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static int dp[][] = new int[1 << 20][20];
	public static int r[][] = new int[20][20];
	public static char arr[];
	public static int n;

	public static void init(char arr[]) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				r[i][j] = r[j][i] = Math.min(Math.abs(arr[i] - arr[j]),
						26 - Math.abs(arr[i] - arr[j]));
			}
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = 0;
			}
		}

	}

	public static int dp(int mask, int cursor, int ant) {

		if (mask == (1 << n) - 1)
			return 0;
		/*if (dp[mask][cursor] > 0)
			return dp[mask][cursor];*/

		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if ((mask & (1 << (n - i - 1))) == 0) {
				int cambio;
				if (cursor < i) {
					int last = 0;
					for (int k = cursor; k < i; k++) {
						if ((mask & 1 << k) > 0)
							last++;
					}

					cambio = last;
				} else {
					int last = 0;
					for (int k = cursor - 1; k >= i; k--) {
						if ((mask & 1 << k) > 0)
							last++;
					}

					cambio = last;
				}

				ans = Math.min(ans, dp(mask | (1 << (n - i - 1)), i + 1, i)
						+ cambio + (cursor - 1 >= 0 ? r[ant][i] : r[0][i]) + 1);
			}
		}

		return dp[mask][cursor] = ans;

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

		while ((line = in.readLine()) != null) {
			if (line.equals("0"))
				break;
			arr = line.toCharArray();
			init(arr);
			n = arr.length;

			System.out.println(dp(0, 0, 0));

		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
