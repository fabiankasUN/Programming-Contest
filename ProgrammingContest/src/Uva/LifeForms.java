package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;


public class LifeForms {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static class SuffixArrayClass {

		public static char cad[];// max 100010
		public Node sA[];
		public int n;
		public static int lcp[];

		public static class Node implements Comparable<Node> {
			int index;
			int rank;
			int nextRank;
			public Node(int index, int rank) {
				this.index = index;
				this.rank = rank;
				this.nextRank = ((index == cad.length - 1) ? -300
						: cad[index + 1]);

			}
			@Override
			public int compareTo(Node o) {
				return (rank == o.rank) ? nextRank - o.nextRank : rank - o.rank;
			}
			@Override
			public String toString() {
				String c = "";
				for (int i = index; i < cad.length; i++) {
					c += cad[i];
				}
				return index + " " + c + "\n";
			}
		}
		public void init(char cad[]) {
			this.cad = cad;
			this.n = cad.length;
			sA = new Node[n];
		}

		public void construct() {
			for (int i = 0; i < n; i++) {
				sA[i] = new Node(i, cad[i]);
			}
			int prev;
			int rank = 0;

			Arrays.sort(sA);

			int ind[] = new int[n];
			for (int i = 2; i <= 2 * n; i <<= 1) {

				rank = 0;
				prev = sA[0].rank;
				sA[0].rank = 0;

				for (int j = 1; j < n; j++) {

					if (sA[j].rank == prev
							&& sA[j].nextRank == sA[j - 1].nextRank) {
						prev = sA[j].rank;
						sA[j].rank = rank;
					} else {
						prev = sA[j].rank;
						sA[j].rank = ++rank;
					}
					ind[sA[j].index] = j;
				}

				for (int j = 0; j < n; j++) {

					if (sA[j].index + i  < n) {
						sA[j].nextRank = sA[ind[sA[j].index + i ]].rank;
					} else {
						sA[j].nextRank = -1;// cuando al doblar no hay rank
					}
				}
				
				Arrays.sort(sA);

			}

		}

		public int[] lpc() {
			int phi[] = new int[n];
			int plcp[] = new int[n];
			lcp = new int[n];
			phi[sA[0].index] = -1;
			
			for (int i = 1; i < n; i++) {
				phi[sA[i].index] = sA[i - 1].index;

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
				lcp[i] = plcp[sA[i].index];
			}

			return lcp;

		}
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
		SuffixArrayClass s = new SuffixArrayClass();
		while ((line = in.readLine()) != null) {
			if (line.equals("0")) {
				break;
			}
			int n = Integer.parseInt(line);
			String r = "";
			for (int i = 0; i < n; i++) {
				r += in.readLine() + (char) ((124 + i));
			}
			s.init(r.toCharArray());
			s.construct();
			s.lpc();
			print(s.sA);
			printArrayInt(s.lcp);
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
	static void print(Object... obj) {
		for (int i = 0; i < obj.length; i++) {
			if (i != 0)
				System.out.print(" ");
			System.out.print(obj[i]);
		}
		System.out.println();
	}
}
