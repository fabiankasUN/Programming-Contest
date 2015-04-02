package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Swaps {

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

		int k[] = readInts(in.readLine());
		Arrays.sort(k);
		int cont = 0;
		int s = k.length - 1;
		for (int i = 0; i < k.length; i++) {
			int pivot = s;
			boolean f = false;
			while (k[i] > 0 && pivot > i) {
				k[i] -= 1;
				if (!f && k[pivot] == 1) {
					s--;
				}
				f = true;
				k[pivot]--;
				out.append((i + 1) + " " + (pivot + 1) + "\n");
				cont++;
				pivot--;
			}
		}
		boolean flag = true;
		for (int i = 0; i < k.length; i++) {
			if (k[i] != 0) {
				out.delete(0, out.length());
				out.append("No\n");
				flag = false;
				break;
			}
		}

		if (flag) {
			System.out.println(cont);
		}

		System.out.print(out);

		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}

}
