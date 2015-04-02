package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Queue {

	// split de array
	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
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

		int arr[] = readInts(in.readLine());
		int n = arr[0];
		int m = arr[1];
		char s[] = in.readLine().toCharArray();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < s.length - 1; j++) {
				if (s[j] == 'B' && s[j + 1] == 'G') {
					s[j] = 'G';
					s[j + 1] = 'B';
					j++;
				}
			}

		}

		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i]);
		}
		System.out.println();

		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
