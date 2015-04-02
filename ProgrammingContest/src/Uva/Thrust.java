package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Thrust {

	public static ArrayList<Integer>[] ady;
	public static boolean[] visited;
	public static int[] dfs_num, dfs_low;
	public static Stack<Integer> s;
	public static int dfsNumberCounter, numSCC;
	public static ArrayList<ArrayList<Integer>> res;

	public static void init(int V) {
		ady = new ArrayList[V];
		visited = new boolean[V];
		dfs_num = new int[V];
		dfs_low = new int[V];
		for (int i = 0; i < V; i++) {
			ady[i] = new ArrayList<Integer>();
			dfs_num[i] = -1;
		}
		s = new Stack<Integer>();
		dfsNumberCounter = numSCC = 0;
	}

	public static void add(int u, int v) {
		ady[u].add(v);
	}

	public static void tarjanSCC(int u) {
		dfs_num[u] = dfs_low[u] = dfsNumberCounter++;
		s.push(u);
		visited[u] = true;
		for (int i = 0; i < ady[u].size(); i++) {
			int v = ady[u].get(i);
			if (dfs_num[v] == -1)
				tarjanSCC(v);
			if (visited[v])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
		}
		if (dfs_low[u] == dfs_num[u]) {
			res.add(new ArrayList<Integer>());
			int v;
			do {
				v = s.pop();
				// res.get(numSCC).add(v);
				visited[v] = false;
			} while (u != v);
			numSCC++;
		}
	}

	public static int SCC() {
		res = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < ady.length; i++)
			if (dfs_num[i] == -1)
				tarjanSCC(i);
		return numSCC;
	}

	public static int[] readInts(String cad) {
		String line[] = cad.split(" ");
		int arr[] = new int[line.length];
		for (int i = 0; i < line.length; i++) {
			arr[i] = Integer.parseInt(line[i]);
		}

		return arr;
	}

	public static void main(String args[]) throws IOException {
		BufferedReader in;
		StringBuilder out = new StringBuilder();
		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else {
			in = new BufferedReader(new InputStreamReader(System.in));
		}
		String line;

		int a, b, n, m, d[];
		HashMap<String, Integer> inverse = new HashMap<String, Integer>();
		String names, names2;
		int cont = 0;
		while ((line = in.readLine()) != null) {
			if (line.equals("0 0"))
				break;
			inverse.clear();
			cont = 0;

			d = readInts(line);
			n = d[0];
			m = d[1];
			init(n);
			for (int i = 0; i < n; i++) {
				names = in.readLine();
				inverse.put(names, cont);
				cont++;
			}

			for (int i = 0; i < m; i++) {
				names = in.readLine();
				names2 = in.readLine();
				a = inverse.get(names);
				b = inverse.get(names2);
				add(a, b);
			}

			int list = SCC();
			out.append(list + "\n");

		}
		System.out.print(out);

	}
}
