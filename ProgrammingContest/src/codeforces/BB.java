package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BB {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static class Node implements Comparable<Node> {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			if (x < o.x)
				return -1;
			if (x > o.x)
				return 1;
			if (y < o.y)
				return -1;
			return 1;
		}

	}

	public static void main(String[] args) throws IOException {
		long inicio = System.currentTimeMillis();
		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		int dates[] = atoi(in.readLine());
		int p = dates[0];
		int q = dates[1];
		int l = dates[2];
		int r = dates[3];
		Node Z[] = new Node[p];
		Node X[] = new Node[q];

		for (int i = 0; i < Z.length; i++) {
			int d[] = atoi(in.readLine());
			Z[i] = new Node(d[0], d[1]);
		}

		for (int i = 0; i < X.length; i++) {
			int d[] = atoi(in.readLine());
			X[i] = new Node(d[0], d[1]);
		}
		int cont = 0;
		for (int k = l; k <= r; k++) {
			w: for (int i = 0; i < X.length; i++) {
				for (int j = 0; j < Z.length; j++) {
					if ((X[i].x + k >= Z[j].x && X[i].x + k <= Z[j].y)
							|| (X[i].y + k >= Z[j].x && X[i].y + k <= Z[j].y)
							|| (X[i].x + k <= Z[j].x && X[i].y + k >= Z[j].y)) {
						cont++;
						break w;
					}
				}
			}
		}

		System.out.println(cont);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
