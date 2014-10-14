package Live;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class InternetBandwidth {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static int INF = 200000000;
	public static int g[][];
	public static int mf, f, s, t;// max flow, actually flow, source node,
									// finish node
	public static int V;// number of vertex
	public static ArrayList<Integer> p = new ArrayList<Integer>();

	private static void augment(int v, int minEdge) {
		if (v == s) {
			f = minEdge;
			return;
		} else if (p.get(v) != -1) {
			augment(p.get(v), Math.min(minEdge, g[p.get(v)][v]));
			g[p.get(v)][v] -= f;
			g[v][p.get(v)] += f;
		}
	}

	public static int edmons_karp(int s, int t) {
		int u;
		mf = 0;

		while (true) {
			f = 0;
			Queue<Integer> q = new LinkedList<Integer>();
			ArrayList<Integer> dist = new ArrayList<Integer>();
			dist.addAll(Collections.nCopies(V, INF));
			dist.set(s, 0);
			q.offer(s);

			p.clear();
			p.addAll(Collections.nCopies(V, -1));

			while (!q.isEmpty()) {
				u = q.poll();
				if (u == t)
					break;
				for (int i = 0; i < V; i++) {
					if (g[u][i] > 0 && dist.get(i) == INF) {
						dist.set(i, dist.get(u) + 1);
						q.offer(i);
						p.set(i, u);// parent of vertex i is vertex u
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
			V = Integer.parseInt(line);
			if (V == 0)
				break;

			int dates[] = atoi(in.readLine());
			s = dates[0] - 1;
			t = dates[1] - 1;
			int c = dates[2];
			g = new int[V][V];
			for (int i = 0; i < c; i++) {
				dates = atoi(in.readLine());
				g[dates[0] - 1][dates[1] - 1] += dates[2];
				g[dates[1] - 1][dates[0] - 1] += dates[2];
			}

			out.append("Network " + times++ + "\n" + "The bandwidth is "
					+ edmons_karp(s, t) + ".\n");
			out.append("\n");
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
