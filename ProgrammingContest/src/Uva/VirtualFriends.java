package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class VirtualFriends {

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
		public int elements[];

		public void MakeSet(int n) {
			padre = new int[n];
			rango = new int[n];
			elements = new int[n];
			for (int i = 0; i < n; ++i) {
				padre[i] = i; // Inicialmente el padre de cada vertice es el mismo
									// vertice
				rango[i] = 0; // Altura o rango de cada vertice es 0
				elements[i] = 1;
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
				elements[xRoot] += elements[yRoot];
				padre[yRoot] = xRoot;
			} else {
				elements[yRoot] += elements[xRoot];
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

		int n = Integer.parseInt(in.readLine());
		HashMap<String, Integer> table = new HashMap<String, Integer>();
		int cont;
		int a, b;
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(in.readLine());
			UF uf = new UF();
			uf.MakeSet(100001);
			cont = 0;
			table.clear();
			for (int j = 0; j < k; j++) {
				String lines[] = in.readLine().split(" ");
				if (table.containsKey(lines[0])) {
					a = table.get(lines[0]);
				} else {
					a = cont;
					table.put(lines[0], cont++);
				}
				if (table.containsKey(lines[1])) {
					b = table.get(lines[1]);
				} else {
					b = cont;
					table.put(lines[1], cont++);

				}
				if (!uf.sameComponent(a, b)) {
					uf.UnionbyRank(a, b);
					out.append(uf.elements[uf.Find(a)] + "\n");
				}else{
					out.append(uf.elements[uf.Find(a)] + "\n");
				}
			}
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
