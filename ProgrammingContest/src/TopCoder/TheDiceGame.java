package TopCoder;
public class TheDiceGame {

	public static int n;
	public static int prof;
	public static double r = 0;

	public static void sol(String cad, int k, int sum) {
		if (prof == k && sum < n) {
			r++;
			System.out.println(cad);
		} else if (prof != k)
			for (int i = 1; i <= 6; i++) {
				sol(cad + " " + i, k + 1, sum + i);
			}

	}

	public static double expectedThrows(int candies) {
		double res = 0;
		r = 0;
		n = candies;

		for (int i = 1; i <= n - 1; i++) {
			r = 0;
			prof = i;
			sol("", 0, 0);
			System.out.println(r);
			res += (r * 1.0) / (Math.pow(6, i) * 1.0);
		}
		System.out.println(res);
		return res;
	}

	public static void main(String[] args) {
		expectedThrows(9);
	}
}
