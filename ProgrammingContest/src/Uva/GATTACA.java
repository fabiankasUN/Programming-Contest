package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class GATTACA {

	public static class SuffixArrayClass {

		public static char cad[];// max 100010
		public static int sA[];
		public static int n;
		public static int lcp[];

		// sort suffixes of S in O(n*log(n))
		public static char[] S;
		
		public static Comparator<Integer> comparador = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return S[o1] - S[o2];
			}
		};
		public static int[] suffixArray() {
			n = S.length;
			cad = S;
			Integer[] order = new Integer[n];
			for (int i = 0; i < n; i++)
				order[i] = n - 1 - i;

			// stable sort of characters
			Arrays.sort(order,
					comparador);

			int[] sa = new int[n];
			int[] classes = new int[n];
			for (int i = 0; i < n; i++) {
				sa[i] = order[i];
				classes[i] = S[i];
			}
			// sa[i] - suffix on i'th position after sorting by first len characters
			// classes[i] - equivalence class of the i'th suffix after sorting by
			// first len characters

			for (int len = 1; len < n; len *= 2) {
				int[] c = classes.clone();
				for (int i = 0; i < n; i++) {
					// condition sa[i - 1] + len < n simulates 0-symbol at the end
					// of the string
					// a separate class is created for each suffix followed by
					// simulated 0-symbol
					classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]]
							&& sa[i - 1] + len < n
							&& c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]]
							: i;
				}
				// Suffixes are already sorted by first len characters
				// Now sort suffixes by first len * 2 characters
				int[] cnt = new int[n];
				for (int i = 0; i < n; i++)
					cnt[i] = i;
				int[] s = sa.clone();
				for (int i = 0; i < n; i++) {
					// s[i] - order of suffixes sorted by first len characters
					// (s[i] - len) - order of suffixes sorted only by second len
					// characters
					int s1 = s[i] - len;
					// sort only suffixes of length > len, others are already sorted
					if (s1 >= 0)
						sa[cnt[classes[s1]]++] = s1;
				}
			}
			return sa;
		}

		// sort rotations of S in O(n*log(n))
		public static int[] rotationArray(CharSequence S) {
			int n = S.length();
			Integer[] order = new Integer[n];
			for (int i = 0; i < n; i++)
				order[i] = i;
			Arrays.sort(order,
					comparador);
			
			int[] sa = new int[n];
			int[] classes = new int[n];
			for (int i = 0; i < n; i++) {
				sa[i] = order[i];
				classes[i] = S.charAt(i);
			}
			for (int len = 1; len < n; len *= 2) {
				int[] c = classes.clone();
				for (int i = 0; i < n; i++)
					classes[sa[i]] = i > 0
							&& c[sa[i - 1]] == c[sa[i]]
							&& c[(sa[i - 1] + len / 2) % n] == c[(sa[i] + len / 2)
									% n] ? classes[sa[i - 1]] : i;
				int[] cnt = new int[n];
				for (int i = 0; i < n; i++)
					cnt[i] = i;
				int[] s = sa.clone();
				for (int i = 0; i < n; i++) {
					int s1 = (s[i] - len + n) % n;
					sa[cnt[classes[s1]]++] = s1;
				}
			}
			return sa;
		}

		public int[] lpc(StringBuilder out) {
			int phi[] = new int[n];
			int plcp[] = new int[n];
			lcp = new int[n];
			
			phi[sA[0]] = -1;

			for (int i = 1; i < n; i++) {
				phi[sA[i]] = sA[i - 1];

			}
			int L = 0;
			for (int i = 0; i < n; i++) {
				if (phi[i] == -1)
					plcp[i] = 0;
				else {
					while (i + L < n && phi[i] + L < n
							&& cad[i + L] == cad[phi[i] + L])
						L++;
					plcp[i] = L;
					L = Math.max(L - 1, 0);
				}
			}
			for (int i = 0; i < n; i++) {
				lcp[i] = plcp[sA[i]];
			}

			int max = Integer.MIN_VALUE;
			for (int i = 0; i < plcp.length; i++) {
				if (lcp[i] > max)
					max = lcp[i];
			}

			if (max == 0) {
				out.append("No repetitions found!\n");
			} else {
				int cont = 0;
				int index = -1;
				int maxCont = Integer.MIN_VALUE;
				boolean cond = false;
				for (int i = 0; i < plcp.length; i++) {
					if (lcp[i] == max) {
						cont++;
							index = i - cont;
							maxCont = cont;
							cond = true;

					} else {
						if (cond)
							break;
						cont = 0;
					}
				}
				String res = "";

				for (int i = 0; i < max; i++) {
					res += cad[sA[index] + i];
				}

				out.append(res + " " + ++maxCont + "\n");

			}

			return lcp;

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
		long inicio = System.currentTimeMillis();
		StringBuilder out = new StringBuilder();
		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		String line = "";
		//Scanner in=new Scanner(System.in);
		int n = Integer.parseInt(in.readLine());
		for (int i = 0; i < n; i++) {
			line = in.readLine().trim();
			SuffixArrayClass suffix = new SuffixArrayClass();
			//suffix.init(line.toCharArray());
			//suffix.construct();
			suffix.S=line.toCharArray();
			suffix.sA= suffix.suffixArray();
			suffix.lpc(out);

		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
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

}
