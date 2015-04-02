package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AlmostUnionFind {

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
		final int MAX = 100001;
		public int padre[];
		public int rango[];
		public int elements[];
		public int sum[];

		public void MakeSet(int n) {
			padre = new int[n * 2];
			rango = new int[n * 2];
			elements = new int[n * 2];
			sum = new int[n * 2];
			for (int i = 0; i < n * 2; ++i) {
				padre[i] = i; // Inicialmente el padre de cada vertice es el mismo
									// vertice
				rango[i] = 0; // Altura o rango de cada vertice es 0
				if (i >= n) {
					elements[i] = 0;
					sum[i] = 0;
				} else {
					elements[i] = 1;
					sum[i] = (i + 1);
				}
			}
			for (int i = 0; i < n; i++) {
				UnionbyRank(i + n, i);
			}
		}

		public void separate(int x, int y) {
			int xRoot = Find(x);
			int yRoot = Find(y);
			padre[x] = yRoot;
			elements[xRoot]--;
			elements[yRoot]++;
			sum[xRoot] -= (x - (sum.length / 2) + 1);
			sum[yRoot] += (x - (sum.length / 2) + 1);
		}

		public int Find(int x) {
			if (x == padre[x]) { // Si estoy en la raiz
				return x; // Retorno la raiz
			} else
				return padre[x] = Find(padre[x]);
		}

		public void UnionbyRank(int x, int y) {
			int xRoot = Find(x);
			int yRoot = Find(y);
			if (rango[xRoot] > rango[yRoot]) {
				elements[xRoot] += elements[yRoot];
				sum[xRoot] += sum[yRoot];
				padre[yRoot] = xRoot;

			} else {
				elements[yRoot] += elements[xRoot];
				sum[yRoot] += sum[xRoot];
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

		String line = "";
		int k[];
		int q = 0, a, b;

		while ((line = in.readLine()) != null) {
			int n = Integer.parseInt(line.split(" ")[0]);
			int m = Integer.parseInt(line.split(" ")[1]);
			UF uf = new UF();
			uf.MakeSet(n);
			for (int i = 0; i < m; i++) {
				k = readInts(in.readLine());
				q = k[0];
				if (q == 1) {
					a = k[1] - 1 + n;
					b = k[2] - 1 + n;
					if (!uf.sameComponent(a, b)) {
						uf.UnionbyRank(a, b);
					}
				} else if (q == 2) {
					a = k[1] - 1 + n;
					b = k[2] - 1 + n;
					if (!uf.sameComponent(a, b)) {
						uf.separate(a, b);
					}
				} else {
					a = k[1] - 1 + n;
					out.append(uf.elements[uf.Find(a)] + " " + (uf.sum[uf.Find(a)])
							+ "\n");
				}
			}
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
