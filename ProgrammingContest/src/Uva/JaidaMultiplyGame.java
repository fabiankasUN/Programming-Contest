package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class JaidaMultiplyGame {

	// split de array
	public static boolean[] readInts(String cad) {
		String read[] = cad.split(" ");
		TreeSet<Integer> hash = new TreeSet<Integer>();
		for (int i = 0; i < read.length; i++) {
			hash.add(Integer.parseInt(read[i]));
		}
		boolean res[] = new boolean[1000001];

		for (Integer integer : hash) {
			res[integer] = true;
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

		boolean arr[] = new boolean[16000002];
		arr[0] = true;
		arr[1] = true;
		arr[2] = false;
		for (int i = 4; i < arr.length; i += 2) {
			arr[i] = true;
		}
		
		for (int i = 3; i < arr.length; i += 2) {
			for (int j = i + i; j < arr.length; j += i) {
				arr[j] = true;
			}
		}

		int n = Integer.parseInt(in.readLine());
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(in.readLine());
			boolean v[] = readInts(in.readLine());
			int cont = 1;

			if (v.length < 3) {
				if (v.length > 1 && v[1] && v[2]) {
					out.append("2\n");
				} else if (v[1]) {
					out.append("1\n");
				} else {
					out.append("0\n");
				}
			} else {
				if (v[1] && v[2] && !v[3]) {
					out.append("2\n");
				} else if (v[1] && !v[2]) {
					out.append("1\n");
				} else if (!v[1]) {
					out.append("0\n");
				} else {
					cont = 3;
					for (int j = 4; j < 1000001; j++) {
						if (v[j] || (arr[j]) || (!arr[j] && v[j])) {
							cont++;
						} else
							break;
					}
					out.append(cont + "\n");
				}

			}

		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
