package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class AntiBruteForceLock {

	// split de array
	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

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

		public String toString() {
			return a + " " + b + " " + cost;
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

	public static int[] ok(String cad) {
		int arr[] = new int[4];
		arr[0] = Integer.parseInt(cad.charAt(0) + "");
		arr[1] = Integer.parseInt(cad.charAt(1) + "");
		;
		arr[2] = Integer.parseInt(cad.charAt(2) + "");
		arr[3] = Integer.parseInt(cad.charAt(3) + "");
		;
		return arr;
	}

	public static int width(int a[], int b[]) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > b[i]) {
				sum += Math.min(a[i] - b[i], b[i] + (10 - a[i]));
			} else {
				sum += Math.min(b[i] - a[i], a[i] + (10 - b[i]));
			}
		}
		return sum;
	}

	public static boolean flag = false;

	public static void main(String[] args) throws IOException {
		long inicio = System.currentTimeMillis();
		StringBuilder out = new StringBuilder();
		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < n; i++) {
			String cad[] = in.readLine().trim().split(" ");
			int v[][] = new int[cad.length - 1][4];
			for (int j = 0; j < v.length; j++) {
				v[j] = ok(cad[j + 1]);
			}

			int g[][] = new int[v.length][v.length];
			edges = new Edge[v.length * v.length];
			int cont = 0;
			for (int j = 0; j < g.length; j++) {
				for (int k = 0; k < g.length; k++) {
					g[j][k] = width(v[j], v[k]);
					edges[cont++] = new Edge(j, k, g[j][k]);
				}
			}
			int res = kruskall(v.length, edges.length);

			int min = Integer.MAX_VALUE;
			int s[] = new int[] { 0, 0, 0, 0 };
			for (int j = 0; j < v.length; j++) {
				min = Math.min(min, width(s, v[j]));
			}
			out.append((res + min) + "\n");
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
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
}
