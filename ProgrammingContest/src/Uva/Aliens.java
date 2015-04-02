package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Aliens {

	// split de array
	public static int[] atoi(String cad) {
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

		int n = Integer.parseInt(in.readLine());
		int arr[] = new int[100002];
		int k [];
		for (int i = 0; i < n; i++) {
			arr = atoi(in.readLine());
			k = atoi(in.readLine());
			Arrays.sort(k);
			int res = 0;
			int index = 0;
			while (index < k.length && res <= arr[1]) {
				if (res + k[index] <= arr[1])
					res += k[index++];
				else
					break;
			}
			out.append(res + " " + index + "\n");
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}

}
