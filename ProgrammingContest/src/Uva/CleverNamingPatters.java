package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class CleverNamingPatters {

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

	public static void main(String[] args) throws IOException {
		long inicio = System.currentTimeMillis();
		StringBuilder out = new StringBuilder();
		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		Edmons_karp f;
		int times = 1;
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(in.readLine());
			int t;

			ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
			for (int j = 0; j < k; j++) {
				ArrayList<String> m = new ArrayList<String>();
				String cad[] = in.readLine().split(" ");
				for (int l = 1; l < cad.length; l++) {
					m.add(cad[l].toLowerCase());
				}
				Collections.sort(m);
				lista.add(m);
			}
			f = new Edmons_karp(2 + 26 + 26);
			t = 2 + 26 + 26 - 1;
			char c = 'a';
			for (int j = 0; j < lista.size(); j++) {
				ArrayList<String> m = lista.get(j);

				int indexProblem = (c - 'a') + 1;
				f.g[0][indexProblem] = 1;
				f.ady[0].add(indexProblem);
				f.ady[indexProblem].add(0);
				c++;
				for (int l = 0; l < m.size(); l++) {
					char init = (m.get(l).charAt(0));

					int indexLetter = init - 'a' + 1 + 26;
					f.g[indexProblem][indexLetter] = 1;
					f.ady[indexLetter].add(indexProblem);
					f.ady[indexProblem].add(indexLetter);

					if (f.g[indexLetter][t] == 0) {
						f.g[indexLetter][t] = 1;
						f.ady[indexLetter].add(t);
						f.ady[t].add(indexLetter);
					}
				}
			}

			long res = f.edmons_karp(0, t);
			ArrayList<String> r = new ArrayList<String>();
			for (int j = 27; j < 27 + lista.size(); j++) {
				for (int m = 1; m < 1 + lista.size(); m++) {
					if (f.g[j][m] == 1) {
						ArrayList<String> rr = lista.get(j - 27);
						for (int l = 0; l < rr.size(); l++) {
							if (rr.get(l).charAt(0) - 'a' + 1 == m) {
								r.add((rr.get(l).charAt(0) + "").toUpperCase()
										+ rr.get(l).substring(1).toLowerCase());
								break;
							}
						}
						break;
					}
				}
			}
			out.append("Case #" + times++ + ":\n");
			Collections.sort(r);
			for (int j = 0; j < r.size(); j++) {
				out.append(r.get(j) + "\n");
			}

		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
