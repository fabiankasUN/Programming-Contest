package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MishaandForest {
	// split de array
	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static class Node {
		int d;
		int xor;

		public Node( int d, int xor ) {
			this.d = d;
			this.xor = xor;
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
		Node arr[] = new Node[n];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			int c[] = readInts(in.readLine());
			arr[i] = new Node(c[0], c[1]);
			if (arr[i].d == 1) {
				list.add(i);
			}
		}
		int cont = 0;
		for (int i = 0; i < list.size(); i++) {

			int temp = list.get(i);
			while (arr[temp].d == 1) {
				cont++;
				out.append(temp + " " + arr[temp].xor + "\n");
				int k = arr[temp].xor;
				arr[k].xor ^= temp;
				arr[k].d--;
				temp = k;
			}
		}

		System.out.println(cont);
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
