package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Baverages {

	public static ArrayList<Integer> adj[];
	public static int inD[];

	public static void init(int v) {
		adj = new ArrayList[v];
		inD = new int[v];

		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}

	public static void topologicalSort(StringBuilder out,
			HashMap<Integer, String> inverse) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int i = 0; i < adj.length; i++) {
			if (inD[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			int v = q.poll();
			out.append(inverse.get(v) + " ");
			for (int i = 0; i < adj[v].size(); i++) {
				int neigh = adj[v].get(i);
				inD[neigh]--;
				if (inD[neigh] == 0)
					q.add(neigh);
			}
		}

	}

	public static int[] readInts(String cad) {
		String line[] = cad.split(" ");
		int arr[] = new int[line.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(line[i]);
		}

		return arr;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		StringBuilder out = new StringBuilder();
		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else {
			in = new BufferedReader(new InputStreamReader(System.in));
		}
		String line;
		int a;
		String names[];
		int times = 1;
		HashMap<Integer, String> table = new HashMap<Integer, String>();
		HashMap<String, Integer> inverse = new HashMap<String, Integer>();

		while ((line = in.readLine()) != null) {
			if (times > 1)
				line = in.readLine();
			if (line == null)
				break;
			int n = Integer.parseInt(line);
			table.clear();
			inverse.clear();
			init(n);
			for (int i = 0; i < n; i++) {
				line = in.readLine().trim();
				table.put(i, line);
				inverse.put(line, i);
			}
			int m = Integer.parseInt(in.readLine().trim());
			try {

				for (int i = 0; i < m; i++) {
					names = in.readLine().split(" ");
					a = inverse.get(names[1]);
					adj[inverse.get(names[0])].add(a);
					inD[a]++;
				}
			} catch (Exception e) {
				while (true)
					;
			}

			out.append("Case #" + times++
					+ ": Dilbert should drink beverages in this order: ");
			topologicalSort(out, table);
			out.deleteCharAt(out.length() - 1);
			out.append(".\n\n");
		}
		System.out.print(out);

	}
}
