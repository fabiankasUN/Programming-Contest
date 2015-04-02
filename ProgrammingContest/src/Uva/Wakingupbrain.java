package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Wakingupbrain {

	// split de array
	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static class UF {
		final int MAX = 10000;
		public int padre[];
		public int rango[];

		public void MakeSet(int n) {
			padre = new int[n];
			rango = new int[n];
			for (int i = 0; i < n; ++i) {
				padre[i] = i; // Inicialmente el padre de cada vertice es el mismo
									// vertice
				rango[i] = 0; // Altura o rango de cada vertice es 0
			}
		}

		public int Find(int x) {
			if (x == padre[x]) { // Si estoy en la raiz
				return x; // Retorno la raiz
			}
			// else return Find( padre[ x ] ); //De otro modo busco el padre del
			// vertice actual, hasta llegar a la raiz.
			else
				return padre[x] = Find(padre[x]);
		}

		public void UnionbyRank(int x, int y) {
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

		public boolean sameComponent(int x, int y) {
			if (Find(x) == Find(y))
				return true;
			return false;
		}
	}

	public static boolean ok(boolean array[]) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == false)
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		long inicio = System.currentTimeMillis();
		StringBuilder out = new StringBuilder();
		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		String line = "";
		int times = 0;
		int a, b;
		int index = 0;
		HashMap<Character, Integer> table = new HashMap<Character, Integer>();
		while ((line = in.readLine()) != null) {
			if (times++ > 0)
				line = in.readLine();
			int n = Integer.parseInt(line);
			int k = Integer.parseInt(in.readLine());

			table.clear();
			index = 0;

			boolean wake[] = new boolean[n];
			int degree[] = new int[n];
			int next[] = new int[n];
			int g[][] = new int[n][n];

			char c[] = in.readLine().toCharArray();

			table.put(c[0], index++);
			table.put(c[1], index++);
			table.put(c[2], index++);
			wake[0] = true;
			wake[1] = true;
			wake[2] = true;

			for (int i = 0; i < k; i++) {
				c = in.readLine().toCharArray();
				if (table.containsKey(c[0]))
					a = table.get(c[0]);
				else {
					a = index;
					table.put(c[0], index++);
				}

				if (table.containsKey(c[1]))
					b = table.get(c[1]);
				else {
					b = index;
					table.put(c[1], index++);
				}

				g[a][b] = 1;
				g[b][a] = 1;

				if (wake[a]) {
					degree[b]++;
				}
				if (wake[b])
					degree[a]++;

			}
			int change = 1;
			boolean flag = false;
			int res = 0;
			while (!ok(wake) && change > 0) {
				Arrays.fill(next, 0);
				res++;
				change = 0;
				for (int i = 0; i < wake.length; i++) {
					if (!wake[i] && degree[i] >= 3) {
						wake[i] = true;
						change++;
						for (int j = 0; j < g.length; j++) {
							if (!wake[j] && g[i][j] == 1) {
								next[j]++;
							}
						}
					}
				}
				if (change == 0)
					flag = true;
				for (int i = 0; i < degree.length; i++) {
					degree[i] += next[i];
				}
			}

			if (flag) {
				out.append("THIS BRAIN NEVER WAKES UP\n");
			} else {
				out.append("WAKE UP IN, " + res + ", YEARS\n");
			}
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
