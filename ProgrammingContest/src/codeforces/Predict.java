package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Predict {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static class Node {
		long n;
		long k;
		long d1;
		long d2;

		public Node(long n, long k, long d1, long d2) {
			super();
			this.n = n;
			this.k = k;
			this.d1 = d1;
			this.d2 = d2;
		}

	}


	public static long lower_bound(long key, long b1, long b2) {
		long lower = 0, upper = key, ans = -1;
		while (lower <= upper) {
			long mid = (lower + upper) / 2;
			if (key == (mid + (mid - b1) + (((mid - b1) - b2)))) {
				return mid;
			} else if (key > mid)
				lower = mid + 1;
				
			else {
				upper = mid - 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		int n = Integer.parseInt(line);
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < n; i++) {
			String cad[] = in.readLine().split(" ");
			Node temp = new Node(Long.parseLong(cad[0]),
					Long.parseLong(cad[1]), Long.parseLong(cad[2]),
					Long.parseLong(cad[3]));

			long x = lower_bound(temp.k, temp.d1, temp.d2);
			if (x == -1) {
				out.append("no\n");
			} else {
				long y = x - temp.d1;
				long z = y - temp.d2;
				long dif = temp.n - temp.k;

				dif -= x - z;
				dif -= x - y;
				if (dif >= 0 && dif % 3 == 0)
					out.append("yes\n");
				else
					out.append("no\n");
			}

		}
		System.out.print(out);
	}
}
