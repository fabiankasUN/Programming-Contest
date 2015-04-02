package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class Jaida2 {

	/*
	 * public static ArrayList<Integer> numerosPrimos(int n) {
	 * 
	 * if (n < 2) return new ArrayList<Integer>(); char[] is_composite = new
	 * char[(n - 2 >> 5) + 1]; final int limit_i = n - 2 >> 1, limit_j = 2 *
	 * limit_i + 3; ArrayList<Integer> results = new ArrayList<>((int)
	 * Math.ceil(1.25506 * n / Math.log(n))); results.add(2); for (int i = 0; i <
	 * limit_i; ++i) if ((is_composite[i >> 4] & 1 << (i & 0xF)) == 0) {
	 * results.add(2 * i + 3); for (long j = 4L * i * i + 12L * i + 9; j <
	 * limit_j; j += 4 * i + 6) is_composite[(int) (j - 3L >> 5)] |= 1 << (j - 3L
	 * >> 1 & 0xF); } return results; }
	 */

	public static ArrayList<Integer> criba(int n) {

		ArrayList<Integer> results = new ArrayList<>(1300000);
		results.add(2);
		boolean v[] = new boolean[n + 1];
		v[0] = true;
		v[1] = true;
		v[2] = false;
		for (int i = 3; i < v.length; i += 2) {
			if (!v[i]) {
				results.add(i);
				if (i > 46348)
					continue;
				for (int j = i * i; j < v.length; j += i) {
					v[j] = true;
				}
			}
		}
		return results;
	}

	public static TreeSet<Integer> hash = new TreeSet<Integer>();
	public static boolean res[] = new boolean[1000003];

	public static boolean[] readInts(String cad) {
		String read[] = cad.split(" ");
		hash.clear();
		for (int i = 0; i < read.length; i++) {
			try {
				hash.add(Integer.parseInt(read[i]));
			} catch (Exception e) {
			}
		}
		for (int i = 0; i < res.length; i++) {
			res[i] = false;
		}

		for (Integer integer : hash) {
			if (integer < 1000003)
				res[integer] = true;
			else
				break;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		StringBuilder out = new StringBuilder();
		BufferedReader in;
		long inicio = System.currentTimeMillis();
		File archivo = new File("salida");
		if (archivo.exists()) {
			in = new BufferedReader(new FileReader(archivo));
			// System.setOut(new PrintStream("salida"));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Integer> primes;
		primes = criba(16000002);
		
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

					for (int j = 2; j < primes.size(); j++) {
						if (primes.get(j) > 1000000 && hash.contains(primes.get(j))) {
							cont += primes.get(j) - primes.get(j - 1);
						} else if (primes.get(j) < 1000000 && v[primes.get(j)]) {
							cont += primes.get(j) - primes.get(j - 1);
						} else {
							cont += primes.get(j) - primes.get(j - 1) - 1;
							break;
						}
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
