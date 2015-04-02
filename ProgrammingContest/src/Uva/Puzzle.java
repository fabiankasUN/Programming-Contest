package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Puzzle {

	// split de array
	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static int n;
	public static int f[][];

	public static boolean ok(int mask[]) {
		return false;
	}

	public static void dfs(int mask[], int index) {

		if (index == n) {
			ok(mask);
		} else {

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

		while ((line = in.readLine()) != null) {

		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
