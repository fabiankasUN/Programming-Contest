package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Councilling {

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

	public static class Node {
		String cad;
		int ant;

	}

	public static void main(String[] args) throws IOException {

		File file = new File("entrada");
		StringBuilder out = new StringBuilder();
		if (file.exists()) {
			in = new BufferedReader(new FileReader(file));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int n = nextInt();
		String line;
		Edmons_karp f;
		HashMap<Integer, String> tabla = new HashMap<Integer, String>();
		HashMap<String, Integer> inverseTable = new HashMap<String, Integer>();
		HashSet<String> rep = new HashSet<String>();
		HashMap<Integer, String> names = new HashMap<Integer, String>();
		HashMap<String, Integer> party = new HashMap<String, Integer>();
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		HashSet<String> partySet = new HashSet<String>();

		for (int i = 0; i < n; i++) {
			if (i == 0)
				nextLine();
			tabla.clear();
			inverseTable.clear();
			rep.clear();
			names.clear();
			party.clear();
			list.clear();
			partySet.clear();
			int k = 0;
			while ((line = nextLine()) != null && !line.equals("")) {
				String values[] = line.split(" ");
				ArrayList<String> l = new ArrayList<String>();
				partySet.add(values[1]);
				k++;
				for (int j = 0; j < values.length; j++) {
					l.add(values[j]);
					if (j > 1 && !rep.contains(values[j])) {
						k++;
						rep.add(values[j]);
					}
				}
				list.add(l);
			}

			k += partySet.size() + 2;
			f = new Edmons_karp(k);
			int mid = ((rep.size() - 1) / 2);

			int cont = 1;
			int sig = partySet.size() + 1;
			int aug = list.size() + partySet.size() + 1;
			int index;
			int t = k - 1;
			for (int j = 0; j < list.size(); j++) {
				ArrayList<String> w = list.get(j);

				if (!party.containsKey(w.get(1))) {
					party.put(w.get(1), cont);
					index = cont;
					cont++;
				} else {
					index = party.get(w.get(1));
				}
				if (f.g[0][index] == 0) {
					f.ady[0].add(index);
					f.ady[index].add(0);
				}
				if (f.g[0][index] < mid) {
					f.g[0][index]++;
				}

				f.g[index][sig] = 1;
				f.ady[index].add(sig);
				f.ady[sig].add(index);
				tabla.put(sig, w.get(0));

				for (int l = 2; l < w.size(); l++) {
					if (!inverseTable.containsKey(w.get(l))) {
						f.g[sig][aug] = 1;
						f.ady[sig].add(aug);
						f.ady[aug].add(sig);
						f.g[aug][t] = 1;

						f.ady[aug].add(t);
						f.ady[t].add(aug);
						inverseTable.put(w.get(l), aug);
						names.put(aug, w.get(l));
						aug++;
					} else {
						index = inverseTable.get(w.get(l));
						f.g[sig][index] = 1;
						f.ady[sig].add(index);
						f.ady[index].add(sig);
					}
				}
				sig++;
			}

			long res = f.edmons_karp(0, k - 1);

			if (res != inverseTable.size()) {
				out.append("Impossible.\n");
			} else {
				for (int j = list.size() + partySet.size() + 1; j < k - 1; j++) {
					for (int p = 0; p < f.ady[j].size(); p++) {
						index = f.ady[j].get(p);
						if (f.g[j][index] == 1 && index != k - 1) {

							out.append(tabla.get(index) + " " + names.get(j)
									+ "\n");
							break;
						}
					}
				}
			}
			if (i != n - 1)
				out.append("\n");

		}
		System.out.print(out);

	}
}
