package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class GopherStrategy {

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

	public static double resultado;

	public static class MinCostFlowSimple {

		public static double[] minCostFlow(ArrayList<Integer> ady[],
				double[][] cap, double[][] cost, int s, int t) {
			int n = cap.length;
			double[] d = new double[n];
			int[] p = new int[n];
			double flowCost = 0;
			int index;
			for (int flow = 0;; ++flow) {
				Arrays.fill(d, Integer.MAX_VALUE);
				d[s] = 0;
				for (int i = 0; i < n; i++)
					for (int j = 0; j < n; j++)
						for (int k = 0; k < ady[j].size(); k++) {
							index = ady[j].get(k);
							if (cap[j][index] > 0 && d[j] < Integer.MAX_VALUE
									&& d[index] > d[j] + cost[j][index]) {
								d[index] = d[j] + cost[j][index];
								p[index] = j;
							}
						}
				if (d[t] == Integer.MAX_VALUE)
					return new double[] { flowCost, flow };
				flowCost += d[t];
				resultado = Math.max(resultado, d[t]);
				for (int v = t; v != s; v = p[v]) {
					--cap[p[v]][v];
					++cap[v][p[v]];
				}
			}

		}

		public static void addEdge(double[][] cap, double[][] cost, int u,
				int v, int edgeCapacity, double edgeCost) {
			cap[u][v] = edgeCapacity;
			cost[u][v] = edgeCost;
			cost[v][u] = -edgeCost;
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
			resultado = Integer.MIN_VALUE;
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

			double cost[][] = new double[h + g + 3][h + g + 3];
			double cap[][] = new double[h + g + 3][h + g + 3];
			ArrayList<Integer> ady[] = new ArrayList[h + g + 3];
			for (int j = 0; j < ady.length; j++) {
				ady[j] = new ArrayList<Integer>();
			}
			MinCostFlowSimple f = new MinCostFlowSimple();
			for (int j = 0; j < g; j++) {
				f.addEdge(cap, cost, 0, j + 1, 1, 0);
				ady[0].add(j + 1);
				ady[j + 1].add(0);
			}

			for (int j = 0; j < g; j++) {
				for (int m = 0; m < h; m++) {
					f.addEdge(cap, cost, j + 1, m + 1 + g, 1, dist[j][m]);
					ady[j + 1].add(m + g + 1);
					ady[m + g + 1].add(j + 1);
				}
			}
			for (int j = 0; j < h; j++) {
				f.addEdge(cap, cost, j + g + 1, h + g + 1, 1, 0);
				ady[j + g + 1].add(h + g + 1);
				ady[h + g + 1].add(j + g + 1);
			}
			f.addEdge(cap, cost, h + g + 2, 0, g - k, 0);
			ady[h + g + 2].add(0);
			ady[0].add(h + g + 2);
			// printMatrixInt(cost);

			double res[] = f.minCostFlow(ady, cap, cost, h + g + 2, h + g + 1);
			// System.out.println();
			// printMatrixInt(cap);

			out.append("Case #" + (i + 1) + ":\n");
			if (res[1] != g - k)
				out.append("Too bad.\n");
			else
				out.append(String.format(Locale.US, "%.3f", resultado) + "\n");

			out.append("\n");
			// System.out.println(min);
			// System.out.println(res[0] + " " + res[1]);

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
