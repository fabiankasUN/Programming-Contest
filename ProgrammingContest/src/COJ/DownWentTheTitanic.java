package COJ;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class DownWentTheTitanic {

	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
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

	public static int nextInt() throws IOException {
		return Integer.parseInt(in.readLine());
	}

	public static double nextDouble() throws IOException {
		return Double.parseDouble(in.readLine());
	}

	public static String nextLine() throws IOException {
		return in.readLine();
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

	public static BufferedReader in;
	public static int dx[] = { 1, -1, 0, 0 };
	public static int dy[] = { 0, 0, -1, 1 };

	public static int p(char a, char b) {
		if (a == '.') {
			if (b == '*')
				return 0;
			return 1;
		}
		if (a == '*') {
			if (b == '*')
				return 0;
			if (b == '#' || b == '@')
				return 100000;
			return 1;
		}
		if (a == '@') {
			if (b == '*')
				return 0;
			return 100000;
		}
		if (a == '#') {
			if (b == '@' || b == '#')
				return 10000;
			if (b == '.')
				return 1;
			return 0;
		}

		return 0;

	}

	public static void main(String[] args) throws IOException {

		File file = new File("entrada");
		if (file.exists()) {
			in = new BufferedReader(new FileReader(file));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		String line;
		int cont = 0;

		while ((line = nextLine()) != null) {
			if (cont > 0) {
				line = nextLine();
			}
			cont++;
			int dates[] = readInts(line);

			HashMap<Point, Integer> tabla = new HashMap<Point, Integer>();
			HashMap<Point, Integer> sink = new HashMap<Point, Integer>();
			int n = dates[0];
			int m = dates[1];
			int p = dates[2];
			char map[][] = new char[n][m];
			int persons = 0;
			int wood = 0;
			int mov = 0;
			int ice = 0;
			for (int i = 0; i < n; i++) {
				char c[] = nextLine().toCharArray();
				for (int j = 0; j < c.length; j++) {
					if (c[j] == '*')
						persons++;
					if (c[j] == '#')
						wood++;
					if (c[j] == '.')
						mov++;
					if (c[j] == '@')
						ice++;
					map[i][j] = c[j];

				}
			}
			int total = persons + wood + mov * 2 + ice + 2;
			Edmons_karp f = new Edmons_karp(total);
			int s = 0;

			int t = persons + wood + mov * 2 + ice + 1;
			int indexP = 1;
			int indexMov = persons + 1;
			int indexWood = persons + mov * 2 + 1;
			int indexIce = persons + wood + mov * 2 + 1;
			int actually = 0;

			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					Point a = new Point(i, j);
					if (map[i][j] == '*') {
						tabla.put(a, indexP++);
					}
					if (map[i][j] == '#') {
						tabla.put(a, indexWood++);
					}
					if (map[i][j] == '.') {
						f.ady[indexMov].add(indexMov + 1);
						f.ady[indexMov + 1].add(indexMov);
						f.g[indexMov][indexMov + 1] = 1;
						tabla.put(a, indexMov++);
						sink.put(a, indexMov++);

					}
					if (map[i][j] == '@') {
						tabla.put(a, indexIce++);
					}
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] != '~') {
						Point a = new Point(i, j);
						if (map[i][j] == '.') {
							actually = sink.get(a);
						} else {
							actually = tabla.get(a);
						}

						for (int k = 0; k < dx.length; k++) {

							int px = i + dx[k];
							int py = j + dy[k];

							if (px >= 0 && px < n && py >= 0 && py < m
									&& map[px][py] != '~') {

								Point temp = new Point(px, py);
								int w = p(map[i][j], map[px][py]);
								int index = tabla.get(temp);
								f.g[actually][index] = w;
								f.ady[actually].add(index);
								if (!f.ady[index].contains(actually)) {
									f.ady[index].add(actually);
								}

							}
						}
					}
				}

			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == '*') {
						int r = tabla.get(new Point(i, j));
						f.g[0][r] = 1;
						f.ady[0].add(r);
						f.ady[r].add(0);
					}
					if (map[i][j] == '#') {
						int r = tabla.get(new Point(i, j));
						f.g[r][t] = p;
						f.ady[r].add(t);
						f.ady[t].add(r);
					}

				}
			}

			long res = f.edmons_karp(s, t);
			System.out.println(res);

		}
	}
}
