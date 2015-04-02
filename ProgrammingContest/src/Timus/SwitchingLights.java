package Timus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SwitchingLights {
	public static int aux;

	static class SegmentTreeLazyPropagation {
		int init, finish, value, length;
		boolean update;
		SegmentTreeLazyPropagation left, right;

		public SegmentTreeLazyPropagation( int i, int f ) {
			init = i;
			finish = f;
			length = finish - init + 1;
			if (init != finish) {
				left = new SegmentTreeLazyPropagation(i, (i + f) / 2);
				right = new SegmentTreeLazyPropagation(((i + f) / 2) + 1, f);
			}
			update = false;
			value = 0;
		}

		public void upd() {
			value = length - value;
			if (init != finish) {
				left.update = !left.update;
				right.update = !right.update;
			}
			update = false;
		}

		public int set(int a, int b) {
			if (update) {
				upd();
			}
			int result = 0;
			if (a == init && b == finish) {
				result = length - 2 * value;
				upd();
			} else if (left.init <= a && left.finish >= b) {
				aux = left.set(a, b);
				result = aux;
				value += aux;
			} else if (right.init <= a && right.finish >= b) {
				aux = right.set(a, b);
				result = aux;
				value += aux;
			} else {
				aux = left.set(a, left.finish) + right.set(right.init, b);
				result = aux;
				value += aux;
			}
			return result;
		}

		public int query(int a, int b) {
			if (update) {
				upd();
			}
			int result = 0;
			if (a == init && b == finish) {
				result = value;
			} else if (left.init <= a && left.finish >= b) {
				result = left.query(a, b);
			} else if (right.init <= a && right.finish >= b) {
				result = right.query(a, b);
			} else {
				result = left.query(a, left.finish) + right.query(right.init, b);
			}
			return result;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		StringBuilder out = new StringBuilder();
		File file = new File("in");
		if (file.exists())
			in = new BufferedReader(new FileReader(file));
		else
			in = new BufferedReader(new InputStreamReader(System.in));
		String line, lines[];
		int n, m;
		while ((line = in.readLine()) != null) {
			lines = line.split(" ");
			n = Integer.parseInt(lines[0]);
			m = Integer.parseInt(lines[1]);
			SegmentTreeLazyPropagation root = new SegmentTreeLazyPropagation(1, n);
			for (int i = 0; i < m; i++) {
				lines = in.readLine().split(" ");
				if (lines[0].equals("0")) {
					root.value += root.set(Integer.parseInt(lines[1]),
							Integer.parseInt(lines[2]));
				} else {
					out.append(root.query(Integer.parseInt(lines[1]),
							Integer.parseInt(lines[2]))
							+ "\n");
				}
			}
		}
		System.out.print(out);
	}
}