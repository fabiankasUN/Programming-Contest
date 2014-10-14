package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class longest {

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
		// $ and @ are prepended and appended to each end to avoid bounds checking
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
		// longest palindromic substring
		public String longestPalindromicSubstring() {
			int length = 0; // length of longest palindromic substring
			int center = 0; // center of longest palindromic substring
			printArrayInt(p);
			for (int i = 1; i < p.length - 1; i++) {
				if (p[i] > length) {
					length = p[i];
					center = i;
				}
			}
			return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
		}

		// longest palindromic substring centered at index i/2
		public String longestPalindromicSubstring(int i) {
			i = i + 2;
			int length = p[i];
			int center = i;
			return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
		}
	}

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
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			String line = "";
			StringBuilder out = new StringBuilder();
			while ((line = in.readLine()) != null && line.length() != 0) {
				Manacher m = new Manacher(line);
				System.out.println(m.longestPalindromicSubstring());
			}
			System.out.print(out);
		}

}
