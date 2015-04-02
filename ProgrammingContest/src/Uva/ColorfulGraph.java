package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ColorfulGraph {

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

	public static void main(String[] args) throws IOException {
		long inicio = System.currentTimeMillis();
		StringBuilder out = new StringBuilder();
		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		int k[] = readInts(in.readLine());
		int n = k[0];
		int m = k[1];
		UF colors[] = new UF[m+1];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = new UF();
			colors[i].MakeSet(n);
		}
		for (int i = 0; i < m; i++) {
			k = readInts(in.readLine());
			int c = k[2];
			colors[c].UnionbyRank(k[0] - 1, k[1] - 1);
		}
		int q = Integer.parseInt(in.readLine());
		for (int i = 0; i < q; i++) {
			k = readInts(in.readLine());
			int cont = 0;
			for (int j = 0; j < colors.length; j++) {
				if (colors[j].sameComponent(k[0] - 1, k[1] - 1)) {
					cont++;
				}
			}
			out.append(cont + "\n");
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
