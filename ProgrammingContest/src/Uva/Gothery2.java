package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.PriorityQueue;

public class Gothery2 {
	public static double resultado = Integer.MIN_VALUE;

	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static double[] readDouble(String cad) {
		String read[] = cad.split(" ");
		double res[] = new double[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Double.parseDouble(read[i]);
		}
		return res;
	}

	public static long[] readLongs(String cad) {
		String read[] = cad.split(" ");
		long res[] = new long[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Long.parseLong(read[i]);
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

	public static int max(int arr[]) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		return max;
	}

	public static int min(int arr[]) {
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
		}
		return min;
	}

	public static int nextInt(String cad) throws IOException {
		return Integer.parseInt(cad);
	}

	public static double nextDouble(String cad) throws IOException {
		return Double.parseDouble(cad);
	}

	public static class MinCostFlow {

		public static class Edge {
			int to, f, rev;
			double cap, cost;

			Edge(int v, double cap, double cost, int rev) {
				this.to = v;
				this.cap = cap;
				this.cost = cost;
				this.rev = rev;
			}
		}

		public static List<Edge>[] createGraph(int n) {
			List<Edge>[] graph = new List[n];
			for (int i = 0; i < n; i++)
				graph[i] = new ArrayList<Edge>();
			return graph;
		}

		public static void addEdge(List<Edge>[] graph, int s, int t, int cap,
				double cost) {
			graph[s].add(new Edge(t, cap, cost, graph[t].size()));
			graph[t].add(new Edge(s, 0, -cost, graph[s].size() - 1));
		}

		static void bellmanFord(List<Edge>[] graph, int s, double[] dist) {
			int n = graph.length;
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[s] = 0;
			boolean[] inqueue = new boolean[n];
			int[] q = new int[n];
			int qt = 0;
			q[qt++] = s;
			for (int qh = 0; (qh - qt) % n != 0; qh++) {
				int u = q[qh % n];
				inqueue[u] = false;
				for (int i = 0; i < graph[u].size(); i++) {
					Edge e = graph[u].get(i);
					if (e.cap <= e.f)
						continue;
					int v = e.to;
					double ndist = dist[u] + e.cost;
					if (dist[v] > ndist) {
						dist[v] = ndist;
						if (!inqueue[v]) {
							inqueue[v] = true;
							q[qt++ % n] = v;
						}
					}
				}
			}
		}

		public static double[] minCostFlow(List<Edge>[] graph, int s, int t,
				double maxf) {
			int n = graph.length;
			double[] prio = new double[n];
			double[] curflow = new double[n];
			int[] prevedge = new int[n];
			int[] prevnode = new int[n];
			double[] pot = new double[n];

			bellmanFord(graph, s, pot);
			int flow = 0;
			int flowCost = 0;
			while (flow < maxf) {
				PriorityQueue<Long> q = new PriorityQueue<>();
				q.add((long) s);
				Arrays.fill(prio, Integer.MAX_VALUE);
				prio[s] = 0;
				boolean[] finished = new boolean[n];
				curflow[s] = Integer.MAX_VALUE;
				while (!finished[t] && !q.isEmpty()) {
					long cur = q.remove();
					int u = (int) (cur & 0xFFFF_FFFFL);
					int priou = (int) (cur >>> 32);
					if (priou != prio[u])
						continue;
					finished[u] = true;
					for (int i = 0; i < graph[u].size(); i++) {
						Edge e = graph[u].get(i);
						if (e.f >= e.cap)
							continue;
						int v = e.to;
						double nprio = prio[u] + e.cost + pot[u] - pot[v];
						if (prio[v] > nprio) {
							prio[v] = nprio;
							q.add(((long) nprio << 32) + v);
							prevnode[v] = u;
							prevedge[v] = i;
							curflow[v] = Math.min(curflow[u], e.cap - e.f);
						}
					}
				}
				if (prio[t] == Integer.MAX_VALUE)
					break;
				for (int i = 0; i < n; i++)
					if (finished[i])
						pot[i] += prio[i] - prio[t];
				double df = Math.min(curflow[t], maxf - flow);
				flow += df;

				for (int v = t; v != s; v = prevnode[v]) {
					Edge e = graph[prevnode[v]].get(prevedge[v]);
					e.f += df;
					graph[v].get(e.rev).f -= df;
					resultado = Math.max(e.cost, resultado);
					flowCost += df * e.cost;
				}

			}
			return new double[] { flow, flowCost };
		}
	}

	public static BufferedReader in;

	public static class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	public static double dist(Point a, Point b) {
		return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
	}

	public static void main(String[] args) throws IOException {

		File file = new File("entrada");
		StringBuilder out = new StringBuilder();
		if (file.exists()) {
			in = new BufferedReader(new FileReader(file));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int n = nextInt(in.readLine());

		for (int i = 0; i < n; i++) {
			resultado=Integer.MIN_VALUE;
			int dates[] = readInts(in.readLine());
			int g = dates[0];
			int h = dates[1];
			int k = dates[2];
			Point gPoints[] = new Point[g];
			Point hPoints[] = new Point[h];
			for (int j = 0; j < g; j++) {
				double dates2[] = readDouble(in.readLine());
				gPoints[j] = new Point(dates2[0], dates2[1]);
			}
			for (int j = 0; j < h; j++) {
				double dates2[] = readDouble(in.readLine());
				hPoints[j] = new Point(dates2[0], dates2[1]);
			}
			double dist[][] = new double[g][h];
			for (int j = 0; j < g; j++) {
				for (int m = 0; m < h; m++) {
					dist[j][m] = dist(gPoints[j], hPoints[m]);
				}
			}
			if (g == 0 || g == k) {
				out.append("Case #" + (i + 1) + ":\n");
				out.append("0.000\n");
			} else {

				MinCostFlow f = new MinCostFlow();

				List<MinCostFlow.Edge>[] graph = f.createGraph(h + g + 3);

				for (int j = 0; j < g; j++) {
					f.addEdge(graph, 0, j + 1, 1, 0);
				}

				for (int j = 0; j < g; j++) {
					for (int m = 0; m < h; m++) {
						f.addEdge(graph, j + 1, m + 1 + g, 1, dist[j][m]);
					}
				}
				for (int j = 0; j < h; j++) {
					f.addEdge(graph, j + g + 1, h + g + 1, 1, 0);
				}
				f.addEdge(graph, h + g + 2, 0, g - k, 0);
				// printMatrixInt(cost);

				double res[] = f.minCostFlow(graph, h + g + 2, h + g + 1,
						Double.MAX_VALUE);
				// System.out.println();
				// printMatrixInt(cap);

				out.append("Case #" + (i + 1) + ":\n");
				if (res[1] < g - k)
					out.append("Too bad.\n");
				else{
					out.append(String.format(Locale.US, "%.3f", resultado)
							+ "\n");
				
				}
				// System.out.println(min);
				// System.out.println(res[0] + " " + res[1]);
			}
			if (i != n - 1)
				out.append("\n");
		}
		System.out.print(out);

	}

	static void printMatrixInt(double[][] array) {
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
