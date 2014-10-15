package TopCoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FlowerGarden {

	public static class Node implements Comparable<Node> {
		int h, b, w;

		public Node(int h, int b, int w) {
			this.h = h;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return o.h - h;
		}

		@Override
		public String toString() {
			return h + "[" + b + ", " + w + "]";
		}

	}

	public static boolean ok(Node a, Node b) {

		if (a.w < b.b || b.w < a.b || a.h < b.h) {
			return true;
		}

		return false;
	}

	public static int[] getOrdering(int[] height, int[] bloom, int[] wilt) {

		Node nodos[] = new Node[height.length];
		for (int i = 0; i < nodos.length; i++)
			nodos[i] = new Node(height[i], bloom[i], wilt[i]);

		int piv = 0;
		Arrays.sort(nodos);
		int k = 1;
		while (piv != bloom.length) {
			boolean change = false;
			for (int i = piv + 1; i < nodos.length; i++) {
				if (!ok(nodos[piv], nodos[i])) {

					change = true;
					break;
				}
			}
			if (!change) {
				piv++;
				k = piv + 1;
				Arrays.sort(nodos, piv, nodos.length);
			} else {
				Node c1 = nodos[piv];
				nodos[piv] = nodos[k];
				nodos[k] = c1;
				k++;
			}
		}
		int res[] = new int[nodos.length];
		for (int i = 0; i < nodos.length; i++) {
			res[i] = nodos[i].h;
		}

		return res;
	}

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 5, 6 };
		int b[] = { 1, 3, 1, 3, 1, 3 };
		int c[] = { 2, 4, 2, 4, 2, 4 };

		System.out.println(Arrays.toString(getOrdering(a, b, c)));
	}
}
