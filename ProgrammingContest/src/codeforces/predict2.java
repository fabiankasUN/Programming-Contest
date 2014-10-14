package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class predict2 {

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
	
	public static long lower_bound2(long key, long b1, long b2) {
		long lower = 0, upper = key, ans = -1;
		while (lower <= upper) {
			long mid = (lower + upper) / 2;
			if (key == (mid + (mid - b1) + (((mid - b1) - b2)))) {
				return mid;
			} else if (key < mid)
				lower = mid + 1;
				
			else {
				upper = mid - 1;
			}
		}
		return -1;
	}
	
	public static boolean cond=false;
	public static boolean ok(long x, long y, long z, long n2) {
		long n = n2;
		if (x > y && x > z) {
			n -= x - y;
			n -= x - z;
		} else if (y > z) {
			n -= y - x;
			n -= y - z;
		} else {
			n -= z - x;
			n -= z - y;
		}
		if (n == 0 || (n>0 && n % 3 == 0) ) {
			return true;
			
		}
		return false;
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
			cond=false;
			long x = lower_bound(temp.k, temp.d1, temp.d2);
			cond|=(ok(x, x - temp.d1, (x - temp.d1) - temp.d2, temp.n
					- temp.k)&&x!=-1 || (temp.k==0 && temp.d1==0 && temp.d2==0));
			x = lower_bound2(temp.k, -temp.d1, temp.d2);
			cond|=(ok(x, x + temp.d1, (x + temp.d1) - temp.d2, temp.n
					- temp.k)&&x!=-1);
			x = lower_bound(temp.k, -temp.d1, -temp.d2);
			cond|=(ok(x, x + temp.d1, (x + temp.d1) + temp.d2, temp.n
					- temp.k)&&x!=-1);
			x = lower_bound(temp.k, temp.d1, -temp.d2);
			cond|=(ok(x, x - temp.d1, (x - temp.d1) + temp.d2, temp.n
					- temp.k)&&x!=-1);
			
			/*x = lower_bound(temp.k, -temp.d1, temp.d2);
			cond|=ok(x, x - temp.d1, (x - temp.d1) - temp.d2, temp.n
					- temp.k);
			x = lower_bound(temp.k, -temp.d1, temp.d2);
			cond|=ok(x, x - temp.d1, (x - temp.d1) - temp.d2, temp.n
					- temp.k);*/
			if(cond){
				System.out.println("yes");
			}else{
				System.out.println("no");
			}
			

		}
		//System.out.print(out);
	}
}
