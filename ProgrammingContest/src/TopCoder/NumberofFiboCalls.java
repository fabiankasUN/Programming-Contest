package TopCoder;
public class NumberofFiboCalls {

	public static int dp0[];
	public static int dp1[];

	public static int fibo1(int n) {

		if (dp1[n] > 0) {
			return dp1[n];
		}
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

		return dp1[n] = fibo1(n - 1) + fibo1(n - 2);
	}

	public static int fibo0(int n) {
		if (dp0[n] > 0) {
			return dp0[n];
		}
		if (n == 0)
			return 1;
		if (n == 1)
			return 0;

		return dp0[n] = fibo0(n - 1) + fibo0(n - 2);
	}

	public static int[] fiboCallsMade(int n) {
		
		int res[] = new int[2];
		dp0 = new int[n + 1];
		dp1 = new int[n + 1];
		for (int i = 0; i < dp0.length; i++) {
			dp0[i] = 0;
			dp1[i] = 0;
		}
		
		
		res[0] = fibo0(n);
		res[1] = fibo1(n);
		return res;
	}

	public static void main(String[] args) {
		fiboCallsMade(0);
	}
}
