package COJ;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MAXIMUM {

	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
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

		StringBuilder out = new StringBuilder();
		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));
		int n = nextInt();
		int i, j, k;
		int dp[][] = new int[202][202];
		char map[][] = new char[202][];
		int dates[];
		int ini;
		int a, b, index, max, INF = Integer.MIN_VALUE / 2, sum;
		for (i = 0; i < n; i++) {
			dates = readInts(nextLine());
			a = dates[0];
			b = dates[1];
			index = dates[1] - 1;
			max = Integer.MIN_VALUE;
			sum = 0;

			for (int s = 0; s < a; s++) {
				for (int t = 0; t < b; t++) {
					dp[s][t] = 0;
				}
				map[s] = nextLine().toCharArray();
			}

			dp[0][0] = map[0][0] == 'T' ? 1 : map[0][0] == '#' ? INF : 0;
			max = Math.max(dp[0][0], max);
			if (dp[0][0] == INF)
				out.append("0\n");
			else {
				for (j = 1; j < b; j++) {
					if (map[0][j] == '#') {
						dp[0][j] = INF;
					} else {
						dp[0][j] = dp[0][j - 1] + ((map[0][j] == 'T') ? 1 : 0);
						max = Math.max(dp[0][j], max);
					}

				}

				for (j = 1; j < a; j++) {
					ini = j % 2 == 0 ? 0 : 1;

					if (ini == 0) {
						dp[j][0] = map[j][0] == 'T' ? 1 + dp[j - 1][0]
								: map[j][0] == '#' ? INF : dp[j - 1][0];

						max = Math.max(dp[j][0], max);
						for (k = 1; k < b; k++) {
							sum = map[j][k] == 'T' ? 1 : 0;
							if (map[j][k] == '#')
								dp[j][k] = INF;
							else {
								dp[j][k] = Math.max(dp[j - 1][k], dp[j][k - 1])
										+ sum;
								max = Math.max(dp[j][k], max);
							}

						}
					} else {
						dp[j][index] = map[j][index] == 'T' ? 1 + dp[j - 1][index]
								: map[j][index] == '#' ? INF : dp[j - 1][index];

						max = Math.max(dp[j][index], max);
						for (k = b - 2; k >= 0; k--) {
							sum = map[j][k] == 'T' ? 1 : 0;
							if (map[j][k] == '#')
								dp[j][k] = INF;
							else {
								dp[j][k] = Math.max(dp[j - 1][k], dp[j][k + 1])
										+ sum;
								max = Math.max(dp[j][k], max);
							}

						}
					}
				}
				if (max > 0) {
					out.append(max + "\n");
				} else {
					out.append("0\n");
				}

			}
		}
		System.out.print(out);

	}
}
