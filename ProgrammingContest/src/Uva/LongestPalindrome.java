package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LongestPalindrome {

	public static class Manacher {
		private int[] p; // p[i] = length of longest palindromic substring of t,
							// centered at i
		private String s; // original string
		private char[] t; // transformed string

		public Manacher(String s) {
			this.s = s;
			t = preprocess(s);
			p = new int[t.length];

			int center = 0, right = 0;
			for (int i = 1; i < t.length - 1; i++) {
				int mirror = 2 * center - i;

				if (right > i)
					p[i] = Math.min(right - i, p[mirror]);

				// attempt to expand palindrome centered at i
				while (t[i + (1 + p[i])] == t[i - (1 + p[i])])
					p[i]++;

				// if palindrome centered at i expands past right,
				// adjust center based on expanded palindrome.
				if (i + p[i] > right) {
					center = i;
					right = i + p[i];
				}
			}

		}

		// Transform s into t.
		// For example, if s = "abba", then t = "$#a#b#b#a#@"
		// the # are interleaved to avoid even/odd-length palindromes uniformly
		// $ and @ are prepended and appended to each end to avoid bounds
		// checking
		public char[] preprocess(String s) {
			char[] t = new char[s.length() * 2 + 3];
			t[0] = '$';
			t[s.length() * 2 + 2] = '@';
			for (int i = 0; i < s.length(); i++) {
				t[2 * i + 1] = '#';
				t[2 * i + 2] = s.charAt(i);
			}
			t[s.length() * 2 + 1] = '#';
			return t;
		}

		// longest palindromic substring
		public String longestPalindromicSubstring() {
			int length = 0; // length of longest palindromic substring
			int center = 0; // center of longest palindromic substring
			for (int i = 1; i < p.length - 1; i++) {
				if (p[i] > length) {
					length = p[i];
					center = i;
				}
			}
			return s.substring((center - 1 - length) / 2,
					(center - 1 + length) / 2);
		}

		// longest palindromic substring centered at index i/2
		public String longestPalindromicSubstring(int i) {
			i = i + 2;
			int length = p[i];
			int center = i;
			return s.substring((center - 1 - length) / 2,
					(center - 1 + length) / 2);
		}

		public void print() {
			for (int i = 0; i < p.length; i++) {
				System.out.print(p[i] + " ");
			}
			System.out.println();

			for (int i = 0; i < p.length; i++) {
				System.out.print(t[i] + " ");
			}
			System.out.println();
		}

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
		String cad = in.readLine();
		Manacher m = new Manacher(cad);
		if (n % 2 == 0) {
			long cont = 0;
			for (int i = 1; i < m.p.length; i += 2) {
				if (m.p[i] >= n)
					cont++;
			}
			out.append(cont+"\n");

		} else {
			long cont = 0;
			for (int i = 2; i < m.p.length; i+=2) {
				if (m.p[i] >= n)
					cont++;
			}
			out.append(cont + "\n");
		}

		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
