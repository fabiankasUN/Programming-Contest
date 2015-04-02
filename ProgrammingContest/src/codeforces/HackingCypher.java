package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HackingCypher {

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

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		StringBuilder out = new StringBuilder();
		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int a, b;
		char arr[] = in.readLine().toCharArray();
		int d[];
		int v[] = new int[arr.length];
		for (int i = 0; i < v.length; i++) {
			v[i] = arr[i] - '0';
		}

		d = readInts(in.readLine());
		a = d[0];
		b = d[1];

		int r[] = new int[arr.length];
		int r2[] = new int[arr.length];
		r[0] = v[0] % a;
		
		r2[r.length-1] = v[r.length-1] % b;
		
		for (int i = 1; i < r.length; i++) {
			r[i] = (r[i - 1] * 10 + v[i]) % a;
		}
		int tam= 10%b;
		
		for (int i = r.length-2; i >=0; i--) {
			r2[i] = (r2[i + 1] + v[i] * tam) % b;
			tam = (tam*10)%b; 
		}
		for (int i = 0; i < r2.length-1; i++) {
			if(r[i]==0 && r2[i+1]==0 && v[i+1]!=0){
				out.append("YES\n");
				for (int j = 0; j <=i; j++) {
					out.append(v[j]);
				}
				out.append("\n");
				for (int j = i+1; j < r2.length; j++) {
					out.append(v[j]);
				}
				System.out.println(out);
				return;
			}
		}
		System.out.println("NO");

	}
}
