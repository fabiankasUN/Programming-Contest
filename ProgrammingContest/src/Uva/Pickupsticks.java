package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Pickupsticks {

	public static ArrayList<Integer> adj[];
	public static int d_in[];

	public static void init(int v) {
		adj = new ArrayList[v];
		d_in = new int[v];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}

	public static StringBuilder topologicalSort(int n) {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			if (d_in[i] == 0) {
				q.add(i);
			}
		}
		StringBuilder out = new StringBuilder();
		int cont = 0;
		while (!q.isEmpty()) {
			int v = q.poll();
			out.append((v + 1) + "\n");
			cont++;
			for (int i = 0; i < adj[v].size(); i++) {
				int neigh = adj[v].get(i);
				d_in[neigh]--;
				if (d_in[neigh] == 0)
					q.add(neigh);
			}
		}
		if (cont != n)
			return null;
		return out;

	}

	public static int[] readInts(String cad) {
		String line[] = cad.split(" ");
		int arr[] = new int[line.length];
		for (int i = 0; i < arr.length; i++) {
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
		init(1000000);
		while ((line = in.readLine()) != null) {
			if (line.equals("0 0"))
				break;
			int d[] = readInts(line);
			int n = d[0];
			int m = d[1];
			for (int i = 0; i < n; i++) {
				adj[i].clear();
				d_in[i] = 0;
			}
			for (int i = 0; i < m; i++) {
				d = readInts(in.readLine());
				adj[d[0] - 1].add(d[1] - 1);
				d_in[d[1] - 1]++;
			}
			StringBuilder res = topologicalSort(n);
			if (res == null)
				System.out.println("IMPOSSIBLE");
			else
				System.out.print(res);
		}
		// System.out.print(out);

	}
}
