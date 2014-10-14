package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SoftwareAllocation {

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
			ArrayList<String> lines = new ArrayList<String>();
			while (line != null && !line.equals("")) {
				lines.add(line);
				line = in.readLine();
			}

			Edmons_karp edmons = new Edmons_karp(38);
			int total = 0;
			int t = 37;

			for (int i = 0; i < lines.size(); i++) {
				String split[] = lines.get(i).split(" ");
				char letter = split[0].charAt(0);
				int indexLetter = letter - 'A' + 1;
				int num = Integer.parseInt(split[0].substring(1));
				total += num;
				edmons.g[0][indexLetter] = num;
				edmons.ady[0].add(indexLetter);
				edmons.ady[indexLetter].add(0);
				for (int j = 0; j < split[1].length() - 1; j++) {
					int indexPc = split[1].charAt(j) - '0' + 27;
					edmons.g[indexLetter][indexPc] = 1;
					edmons.ady[indexPc].add(indexLetter);
					edmons.ady[indexLetter].add(indexPc);
					if (edmons.g[indexPc][t] == 0) {
						edmons.g[indexPc][t] = 1;
						edmons.ady[indexPc].add(t);
						edmons.ady[t].add(indexPc);
					}
				}
			}
			long res = edmons.edmons_karp(0, t);
			if (res != total) {
				out.append("!\n");
			} else {
				for (int i = 27; i < 37; i++) {
					boolean cond = false;
					for (int j = 1; j <= 26; j++) {
						if (edmons.g[i][j] == 1) {
							out.append((char) (j + 'A' - 1));
							cond = true;
							break;
						}
					}
					if (!cond) {
						out.append("_");
					}
				}
				out.append("\n");
			}

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
