package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class CaptainMarmot {

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

	public static int nextInt(String cad) throws IOException {
		return Integer.parseInt(cad);
	}

	public static double nextDouble(String cad) throws IOException {
		return Double.parseDouble(cad);
	}

	public static BufferedReader in;

	public static class Node {
		Point p[];
		Point piv;

		public Node(Point t) {
			this.p = new Point[4];
			this.piv = t;
		}

	}

	public static class Point implements Comparable<Point> {
		int x, y;
		int w;

		public Point(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (x == o.x)
				return y - o.y;
			else {
				return x - o.x;
			}

		}

	}

	public static boolean iguales(Point a, Point b) {
		if (a.x == b.x && a.y == b.y)
			return true;
		return false;
	}

	public static Point girar(Point a, Point b, int num) {
		if (num > 1)
			b = girar(a, b, num - 1);

		Point res = null;
		int rX = Math.abs(a.x - b.x);
		int rY = Math.abs(a.y - b.y);

		if (a.x >= b.x && a.y >= b.y) {
			res = new Point(a.x + rY, a.y - rX, 1);
		} else if (a.x <= b.x && a.y >= b.y) {
			res = new Point(a.x + rY, a.y + rX, 1);
		} else if (a.x <= b.x && a.y <= b.y) {
			res = new Point(a.x - rY, a.y + rX, 1);
		} else if (a.x >= b.x && a.y <= b.y) {
			res = new Point(a.x - rY, a.y - rX, 1);
		}
		return res;
	}

	public static double dist(Point a, Point b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}

	public static int cmp(final double x, final double y, final double eps) {
		return (x <= y + eps) ? (x + eps < y) ? -1 : 0 : 1;
	}

	public static boolean ok(Point a, Point b, Point c, Point d) {
		ArrayList<Point> lista = new ArrayList<Point>();
		lista.add(a);
		lista.add(b);
		lista.add(c);
		lista.add(d);
		Collections.sort(lista);

		ArrayList<Double> l = new ArrayList<Double>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < i; j++) {
				l.add(dist(lista.get(i), lista.get(j)));
			}
		}
		Collections.sort(l);
		if (cmp(l.get(0), l.get(3), 00000.1) != 0) {
			return false;
		}
		if (cmp(l.get(0), 0.0, 00000.1) == 0)
			return false;
		if (cmp(l.get(4), l.get(5), 00000.1) != 0)
			return false;

		return true;

	}

	public static void main(String[] args) throws IOException {

		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int n = nextInt(in.readLine());
		for (int i = 0; i < n; i++) {
			Node nodes[] = new Node[4];
			for (int j = 0; j < 4; j++) {
				int d[] = readInts(in.readLine());
				Point r = new Point(d[2], d[3], 0);
				Point s = new Point(d[0], d[1], 0);
				nodes[j] = new Node(r);
				nodes[j].p[0] = s;
				for (int k = 1; k < 4; k++) {
					nodes[j].p[k] = girar(r, s, 4 - k);
					nodes[j].p[k].w = 4 - k;
				}
			}

			int max = Integer.MAX_VALUE;
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					for (int c = 0; c < 4; c++) {
						for (int d = 0; d < 4; d++) {
							if (ok(nodes[0].p[a], nodes[1].p[b], nodes[2].p[c],
									nodes[3].p[d])) {
								max = Math.min(max, nodes[0].p[a].w
										+ nodes[1].p[b].w + nodes[2].p[c].w
										+ nodes[3].p[d].w);
							}
						}
					}
				}
			}
			if (max == Integer.MAX_VALUE) {
				System.out.println("-1");
			} else
				System.out.println(max);
		}

	}
}
