package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CheateriusProblem {

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
		int arr[];

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

		String line = in.readLine();

		int n = Integer.parseInt(line);

		int map[][] = new int[n][4];
		for (int i = 0; i < map.length; i++) {
			String s = in.readLine();
			map[i][0] = Integer.parseInt(s.charAt(0) + "");
			map[i][1] = Integer.parseInt(s.charAt(1) + "");
			s = in.readLine();
			map[i][3] = Integer.parseInt(s.charAt(0) + "");
			map[i][2] = Integer.parseInt(s.charAt(1) + "");
			if (i != n - 1)
				in.readLine();
		}

		ArrayList<int[]> listas = new ArrayList<int[]>();

		for (int i = 0; i < map.length; i++) {
			boolean find = false;
			for (int j = 0; j < listas.size(); j++) {
				for (int k = 0; k < 4; k++) {
					int cont = 0;
					for (int l = 0; l < 4; l++) {
						if (map[i][(l + k) % 4] == listas.get(j)[l]) {
							cont++;
						} else
							break;
					}

					if (cont == 4) {
						find = true;
					}
				}
			}
			if (!find) {
				listas.add(map[i]);
			}
		}
		System.out.println(listas.size());

		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
