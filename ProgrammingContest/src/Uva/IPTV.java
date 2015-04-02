package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class IPTV {

	static final int MAX = 1005; // maximo numero de vértices

	static int padre[]; // Este arreglo contiene el padre del
	// i-esimo nodo
	static int rango[];

	public static void MakeSet(int n) {
		padre = new int[n];
		rango = new int[n];
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
		MakeSet(V);
		Arrays.sort(edges);
		int total = 0;
		int index = 0;
		MST = new Edge[E];
		for (int i = 0; i < E; i++) {
			if (!sameComponent(edges[i].a, edges[i].b)) {
				total += edges[i].cost;
				MST[index++] = edges[i];
				unionByRank(edges[i].a, edges[i].b);
			}
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

		int n = Integer.parseInt(in.readLine());
		int a, b;
		int cont = 0;
		for (int i = 0; i < n; i++) {
			in.readLine();
			int V = Integer.parseInt(in.readLine());
			int E = Integer.parseInt(in.readLine());
			cont = 0;
			int k = 0;
			edges = new Edge[E];
			HashMap<String, Integer> table = new HashMap<String, Integer>();
			for (int j = 0; j < E; j++) {
				String lines[] = in.readLine().split(" ");
				if (!table.containsKey(lines[0])) {
					table.put(lines[0], cont++);
					a = cont - 1;
				} else {
					a = table.get(lines[0]);
				}
				if (!table.containsKey(lines[1])) {
					table.put(lines[1], cont++);
					b = cont - 1;

				} else {
					b = table.get(lines[1]);
				}
				edges[k++] = new Edge(a, b, Integer.parseInt(lines[2]));
			}
			out.append(kruskall(V, E) + "\n");
			if(i!=n-1)
				out.append("\n");
		}
		System.out.print(out);

	}
}
