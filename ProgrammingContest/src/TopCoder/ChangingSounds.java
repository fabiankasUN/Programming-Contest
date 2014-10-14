package TopCoder;
public class ChangingSounds {

	public static int intervals[];
	public static int max, min;
	public static int dp[][];
	int k = 0;
	public static boolean r = false;
	public static int sum = 0;

	public static int dp(int index, int value) {

		if (value < 0 || value > max)
			return -2;
		
		if (index >= intervals.length) {
			return value;
		}
		if (dp[index][value] != -1) {
			return dp[index][value];
		}
		
		return dp[index][value] = Math.max(dp(index + 1, value - intervals[index]), dp(index + 1, value + intervals[index]));
		
	}

	public static int maxFinal(int[] changeIntervals, int beginLevel,
			int maxLevel) {

		intervals = changeIntervals;
		min = beginLevel;
		max = maxLevel;
		dp = new int[intervals.length][1010];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
		int res = dp(0, min);

		if (res < 0)
			return -1;

		return res;

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

	public static void main(String[] args) {
		int arr[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1000 };
		int min = 500;
		int max = 1000;
		System.out.println(maxFinal(arr, min, max));
	}
}
