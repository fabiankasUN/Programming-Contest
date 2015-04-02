package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Expensive {

	static final int MAX = 1005; // maximo numero de vértices

	static int padre[]; // Este arreglo contiene el padre del
	// i-esimo nodo
	static int rango[];

	public static void MakeSet(int n, int e) {
		padre = new int[n];
		rango = new int[n];
		MST = new Edge[e];
		edges = new Edge[e];
		for (int i = 1; i < n; i++) {
			padre[i] = i;
			rango[i] = 0;
		}

	}

	static int Find(int x) {
		return (x == padre[x]) ? x : (padre[x] = Find(padre[x]));
	}

	static void unionByRank(int x, int y) {
		int xRoot = Find(x);
		int yRoot = Find(y);
		if (rango[xRoot] > rango[yRoot]) {
			padre[yRoot] = xRoot;
		} else {
			padre[xRoot] = yRoot;
			if (rango[xRoot] == rango[yRoot]) {
				rango[yRoot]++;
			}
		}

	}

	// Método que me determina si 2 vértices estan o no en la misma componente
	// conexa
	static boolean sameComponent(int x, int y) {
		if (Find(x) == Find(y))
			return true;
		return false;
	}

	public static Edge edges[];
	public static Edge MST[];

	public static class Edge implements Comparable<Edge> {
		int a, b;
		int cost;

		public Edge( int a, int b, int cost ) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}

	}

	public static int kruskall(int V, int E) {
		Arrays.sort(edges);
		int total = 0;
		int index = 0;
		for (int i = 0; i < E; i++) {
			if (!sameComponent(edges[i].a, edges[i].b)) {
				total += edges[i].cost;
				MST[index++] = edges[i];
				unionByRank(edges[i].a, edges[i].b);
			}
		}
		if (index != V - 1) {
			return -1;
		}

		return total;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else {
			in = new BufferedReader(new InputStreamReader(System.in));
		}
		StringBuilder out = new StringBuilder();
		String line;

		while ((line = in.readLine()) != null) {
			if (line.equals("0 0"))
				break;

			int n = Integer.parseInt(line.split(" ")[0]);
			int e = Integer.parseInt(line.split(" ")[1]);
			int a, b;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			int cont = 0;
			MakeSet(n, e);

			for (int i = 0; i < n; i++) {
				line = in.readLine();
				map.put(line, cont++);
			}
			for (int i = 0; i < e; i++) {
				String lines[] = in.readLine().split(" ");
				a = map.get(lines[0]);
				b = map.get(lines[1]);
				edges[i] = new Edge(a, b, Integer.parseInt(lines[2]));
			}
			String init = in.readLine();
			int cost = kruskall(n, e);
			if (cost == -1)
				out.append("Impossible\n");
			else
				out.append(cost + "\n");

		}
		System.out.print(out);
	}

}
