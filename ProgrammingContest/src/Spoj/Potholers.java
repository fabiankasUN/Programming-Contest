package Spoj;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Potholers {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static int INF = 2000000000;
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

	public static int edmons_karp(int source, int finish) {
		int u;
		mf = 0;
		s = source;
		t = finish;
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
					if (i < dist.size() && dist != null && g[u][i] > 0
							&& dist.get(i) == INF) {
						dist.set(i, dist.get(u) + 1);
						q.add(i);
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
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		g = new int[205][205];
		for (int i = 0; i < n; i++) {
			V = scan.nextInt();
			for (int j = 0; j < V; j++) {
				for (int k = 0; k < V; k++) {
					g[j][k] = 0;
				}
			}
			for (int j = 0; j < V - 1; j++) {
				int d = scan.nextInt();
				for (int k = 0; k < d; k++) {
					int a = scan.nextInt();
					if (j == 0 || (a == V))
						g[j][a - 1] = 1;
					else
						g[j][a - 1] = 500000000;
				}
			}
			System.out.println(edmons_karp(0, V - 1));
		}
	}
}
