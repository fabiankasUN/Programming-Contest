package TopCoder;
public class JumpingBoard {

	public static int b[][];
	public static int dp[][];
	public static boolean sum[][][][];
	public static boolean cond = false;

	public static int dp(int i, int j, int xAnt, int yAnt) {
		if (cond)
			return -1;
		if (i < 0 || i >= b.length || j < 0 || j >= b[0].length) {
			return 0;
		}

		if (xAnt != -1 && yAnt != -1 && sum[i][j][xAnt][yAnt]) {
			sum[i][j][xAnt][yAnt] = true;
			cond = true;

		} else if (xAnt != -1) {
			sum[i][j][xAnt][yAnt] = true;
		}

		if (b[i][j] == 'H') {
			return 0;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		if (xAnt != -1 && b[i][j] == b[xAnt][yAnt]) {
			cond = true;
			return -1;
		}

		int ans = Integer.MIN_VALUE;
		int value = Integer.parseInt((char) (b[i][j]) + "");
		ans = Math.max(ans, dp(i + value, j, i, j) + 1);
		ans = Math.max(ans, dp(i - value, j, i, j) + 1);
		ans = Math.max(ans, dp(i, j + value, i, j) + 1);
		ans = Math.max(ans, dp(i, j - +value, i, j) + 1);
		return dp[i][j] = ans;

	}

	public static int maxJumps(String[] board) {
		b = new int[board.length][board[0].length()];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length(); j++) {
				b[i][j] = board[i].charAt(j);
			}
		}

		cond = false;
		dp = new int[b.length][b[0].length];
		sum = new boolean[b.length][b[0].length][b.length][b[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length(); j++) {
				dp[i][j] = -1;
			}
		}
		int res = dp(0, 0, -1, -1);
		if (cond)
			return -1;
		return res;

	}

	public static void main(String[] args) {
		String cad[] = { "12H3" };
		System.out.println(maxJumps(cad));
	}
}
