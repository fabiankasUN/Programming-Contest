package Uva;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;

public class ArcticNetwork {

	static int padre[]; // Este arreglo contiene el padre del
	// i-esimo nodo
	static int rango[];

	// Método de inicialización
	static void MakeSet(int n) {
		padre = new int[n];
		rango = new int[n];
		for (int i = 1; i < n; i++) {
			padre[i] = i;
			rango[i] = 0;
		}

	}

	// Método para encontrar la raiz del vértice actual X
	static int Find(int x) {
		return (x == padre[x]) ? x : (padre[x] = Find(padre[x]));
	}

	// Método para unir 2 componentes conexas
	static void Union(int x, int y) {
		padre[Find(x)] = Find(y);
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

	public static class Edge implements Comparable<Edge> {

		int ini, fin;
		double w;

		public Edge( int ini, int fin, double w ) {
			this.ini = ini;
			this.fin = fin;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			if (w < o.w)
				return -1;
			else if (w > o.w)
				return 1;
			return 0;
		}

	}

	public static Edge edges[];
	public static Edge MST[];

	public static double Kruskall(int n, int w) {

		double total = 0;
		MakeSet(n);
		Arrays.sort(edges);
		int sum = 0;
		for (int i = 0; i < edges.length; i++) {
			if (edges[i].ini != edges[i].fin
					&& !sameComponent(edges[i].ini, edges[i].fin)) {
				unionByRank(edges[i].ini, edges[i].fin);
				MST[sum++] = edges[i];
				total += edges[i].w;
			}
		}
		sum--;
		w--;
		while (w > 0 && sum >= 0) {
			sum--;
			w--;
		}
		if (sum < 0)
			return 0;
		return MST[sum].w;

	}

	public static double dist(Point a, Point b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
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
		int test = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < test; i++) {
			int k[] = readInts(in.readLine().trim());
			int n = k[0];
			int m = k[1];
			Point points[] = new Point[m];
			edges = new Edge[m * m];
			MST = new Edge[m * m];
			for (int j = 0; j < m; j++) {
				k = readInts(in.readLine().trim());
				points[j] = new Point(k[0], k[1]);
			}

			int index = 0;

			for (int j = 0; j < points.length; j++) {
				for (int p = 0; p < points.length; p++) {
					edges[index++] = new Edge(j, p, dist(points[j], points[p]));
				}
			}

			double res = Kruskall(m, n);
			System.out.println(String.format(Locale.US, "%.2f", res));

			/*
			 * Arrays.sort(edges); for (int j = 0; j < edges.length; j++) {
			 * System.out.println(edges[j].w); }
			 */

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
