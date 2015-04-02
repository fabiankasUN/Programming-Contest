package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Reconnecting {

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

	public static void kruskall(int V, int E) {
		MakeSet(V);
		Arrays.sort(edges);
		int total = 0;
		int index = 0;
		for (int i = 0; i < edges.length; i++) {
			if (!sameComponent(edges[i].a, edges[i].b)) {
				total += edges[i].cost;
				MST[index++] = edges[i];
				unionByRank(edges[i].a, edges[i].b);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		BufferedReader in;
		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else {
			in = new BufferedReader(new InputStreamReader(System.in));
		}
		String line;
		int lines[];
		while ((in.readLine()) != null) {
			int n = Integer.parseInt(in.readLine());
			edges = new Edge[n-1];
			for (int i = 0; i < n-1; i++) {
				lines = readInts(in.readLine());
				edges[i] = new Edge(lines[0]-1, lines[1]-1, lines[2]);
				
				
			}

		}

	}

	public static int[] readInts(String cad) {
		String lines[] = cad.split(" ");
		int arr[] = new int[lines.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(lines[i]);
		}
		return arr;
	}

}
