package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MyTShirtsSuitsMe {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	private static class Edmons_karp {
		public static int INF = 200000000;
		public int g[][];
		public ArrayList<Integer>[] ady;
		public int mf, f, s;// max flow, actually flow, source node,
		public int V;// number of vertex
		public int[] p;

		public Edmons_karp(int v) {
			V = v;
			g = new int[V][V];
			p = new int[V];
			ady = new ArrayList[V];
			for (int i = 0; i < ady.length; i++)
				ady[i] = new ArrayList<Integer>(V);
		}

		private void augment(int v, int minEdge) {
			if (v == s) {
				f = minEdge;
				return;
			} else if (p[v] != -1) {
				augment(p[v], Math.min(minEdge, g[p[v]][v]));
				g[p[v]][v] -= f;
				g[v][p[v]] += f;
			}
		}

		public int edmons_karp(int s, int t) {
			int u, e;
			mf = 0;
			this.s = s;
			Arrays.fill(p, -1);
			while (true) {
				f = 0;
				Queue<Integer> q = new LinkedList<Integer>();
				int[] dist = new int[V];
				Arrays.fill(dist, INF);
				dist[s] = 0;
				q.offer(s);

				Arrays.fill(p, -1);

				while (!q.isEmpty()) {
					u = q.poll();
					if (u == t)
						break;
					for (int i = 0; i < ady[u].size(); i++) {
						e = ady[u].get(i);
						if (g[u][e] > 0 && dist[e] == INF) {
							dist[e] = dist[u] + 1;
							q.offer(e);
							p[e] = u;// parent of vertex e is vertex u
						}
					}
				}
				augment(t, INF);
				if (f == 0)
					break;
				mf += f;
			}
			return mf;
		}
	}

	public static HashMap<String, Integer> sizes = new HashMap<String, Integer>(
			6);

	public static void main(String[] args) throws IOException {
		sizes.put("XXL", 1);
		sizes.put("XL", 2);
		sizes.put("L", 3);
		sizes.put("M", 4);
		sizes.put("S", 5);
		sizes.put("XS", 6);
		long inicio = System.currentTimeMillis();
		StringBuilder out = new StringBuilder();
		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		String line = "";

		int nTest = Integer.parseInt(in.readLine().trim());
		Edmons_karp f;
		for (int i = 0; i < nTest; i++) {
			f = new Edmons_karp(38);
			int[] nm = atoi(in.readLine().trim());
			for (int j = 0; j < nm[1]; j++) {
				String[] s = in.readLine().trim().split(" ");
				for (int k = 0; k < s.length; k++) {
					int indexSize = sizes.get(s[k]);
					if (f.g[0][indexSize] == 0) {
						f.ady[0].add(indexSize);
						f.ady[indexSize].add(0);
					}
					f.g[0][indexSize] = nm[0] / 6;
					f.g[indexSize][7 + j] = 1;
					f.ady[indexSize].add(7 + j);
					f.ady[7 + j].add(indexSize);

				}
				f.g[7 + j][37] = 1;
				f.ady[7 + j].add(37);
				f.ady[37].add(7 + j);
			}
			int maxFlow = f.edmons_karp(0, 37);
			out.append((nm[1]==maxFlow)?"YES\n":"NO\n");
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
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
}
