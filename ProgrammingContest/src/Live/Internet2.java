package Live;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Internet2 {

	public static class MaxFlowPreflowN3 {

		int[][] cap;

		public void init(int nodes) {
			cap = new int[nodes][nodes];
		}

		public void addEdge(int s, int t, int capacity) {
			cap[s][t] += capacity;
		}

		public int maxFlow(int s, int t) {
			int n = cap.length;

			int[] h = new int[n];
			h[s] = n - 1;

			int[] maxh = new int[n];

			int[][] f = new int[n][n];
			int[] e = new int[n];

			for (int i = 0; i < n; ++i) {
				f[s][i] = cap[s][i];
				f[i][s] = -f[s][i];
				e[i] = cap[s][i];
			}

			for (int sz = 0;;) {
				if (sz == 0) {
					for (int i = 0; i < n; ++i)
						if (i != s && i != t && e[i] > 0) {
							if (sz != 0 && h[i] > h[maxh[0]])
								sz = 0;
							maxh[sz++] = i;
						}
				}
				if (sz == 0)
					break;
				while (sz != 0) {
					int i = maxh[sz - 1];
					boolean pushed = false;
					for (int j = 0; j < n && e[i] != 0; ++j) {
						if (h[i] == h[j] + 1 && cap[i][j] - f[i][j] > 0) {
							int df = Math.min(cap[i][j] - f[i][j], e[i]);
							f[i][j] += df;
							f[j][i] -= df;
							e[i] -= df;
							e[j] += df;
							if (e[i] == 0)
								--sz;
							pushed = true;
						}
					}
					if (!pushed) {
						h[i] = Integer.MAX_VALUE;
						for (int j = 0; j < n; ++j)
							if (h[i] > h[j] + 1 && cap[i][j] - f[i][j] > 0)
								h[i] = h[j] + 1;
						if (h[i] > h[maxh[0]]) {
							sz = 0;
							break;
						}
					}
				}
			}

			int flow = 0;
			for (int i = 0; i < n; i++)
				flow += f[s][i];

			return flow;
		}
	}

	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
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
		MaxFlowPreflowN3 maxFlow;
		while ((line = in.readLine()) != null) {
			int V = Integer.parseInt(line);
			maxFlow = new MaxFlowPreflowN3();
			maxFlow.init(V);
			if (V == 0)
				break;

			int dates[] = atoi(in.readLine());
			int s = dates[0] - 1;
			int t = dates[1] - 1;
			int c = dates[2];
			for (int i = 0; i < c; i++) {
				dates = atoi(in.readLine());
				maxFlow.addEdge(dates[0] - 1, dates[1] - 1, dates[2]);
				maxFlow.addEdge(dates[1] - 1, dates[0] - 1, dates[2]);
			}

			out.append("Network " + times++ + "\n" + "The bandwidth is "
					+ maxFlow.maxFlow(s, t) + ".\n");
			out.append("\n");
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
