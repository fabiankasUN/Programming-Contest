package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class TrainPeter {

	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static long[] readLongs(String cad) {
		String read[] = cad.split(" ");
		long res[] = new long[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Long.parseLong(read[i]);
		}
		return res;
	}

	static void printArrayInt(int[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				System.out.print(" ");
			System.out.print(array[i]);
		}
		System.out.println();
	}

	static void printMatrixInt(int[][] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				if (j > 0)
					System.out.print(" ");
				System.out.print(array[i][j]);
			}
			System.out.println();
		}

	}

	public static int max(int arr[]) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		return max;
	}

	public static int min(int arr[]) {
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
		}
		return min;
	}

	public static ArrayList<Integer> KMP(char cadena[], char patron[]) {

		int[] fallas = new int[patron.length + 1];
		ArrayList<Integer> coincidencias = new ArrayList<Integer>();
		int pos;
		Arrays.fill(fallas, -1);

		// funcion de fallas
		for (int i = 1; i <= patron.length; i++) {
			pos = fallas[i - 1];

			while (pos != -1 && patron[pos] != patron[i - 1]) {

				pos = fallas[pos];
			}

			fallas[i] = pos + 1;
		}

		// cp = pocision cadena, pp = pocision patron
		for (int cp = 0, pp = 0; cp < cadena.length; cp++) {
			while (pp != -1
					&& (pp == patron.length || patron[pp] != cadena[cp])) {
				pp = fallas[pp];
			}
			pp++;
			if (pp == patron.length) {
				coincidencias.add(cp + 1 - patron.length);
			}
		}

		return coincidencias;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		StringBuilder out = new StringBuilder();
		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		char s[] = in.readLine().toCharArray();
		char a[] = in.readLine().toCharArray();
		char b[] = in.readLine().toCharArray();
		char c[] = new char[a.length + b.length];
		char d[] = new char[s.length];
		for (int i = 0; i < a.length; i++) {
			c[i] = a[i];
		}
		for (int i = 0; i < b.length; i++) {
			c[i + a.length] = b[i];
		}
		for (int i = 0; i < s.length; i++) {
			d[d.length - (i + 1)] = s[i];
		}
		ArrayList<Integer> r1 = KMP(s, a);
		ArrayList<Integer> r2 = KMP(s, b);
		ArrayList<Integer> r3 = KMP(d, a);
		ArrayList<Integer> r4 = KMP(d, b);
		boolean h1 = false, h2 = false;

		if (r1.size() > 0 && r2.size() > 0) {
			int min = r1.get(0);

			for (int i = 0; i < r2.size(); i++) {
				if (min+a.length <= r2.get(i))
					h1 = true;
			}
		}
			
		if (r3.size() > 0 && r4.size() > 0) {
			int min = r3.get(0);

			for (int i = 0; i < r4.size(); i++) {
				if (min+a.length <= r4.get(i))
					h2 = true;
			}
		}
		if(h1 && h2)
			System.out.println("both");
		else if(h1){
			System.out.println("forward");
		}else if(h2){
			System.out.println("backward");
		}else{
			System.out.println("fantasy");
		}

	}
}
