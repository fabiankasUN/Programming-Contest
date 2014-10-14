package TopCoder;
public class JumpingBoard2 {

	public static int b[][];
	public static int dp[][];
	public static boolean cond = false;

	public static int dp(int i, int j, int xAnt, int yAnt) {
		if (i < 0 || i >= b.length || j < 0 || j >= b[0].length) {
			return 0;
		}

		if (b[i][j] == 'H') {
			return 0;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		dp[i][j] = -2;

		int value = Integer.parseInt((char) (b[i][j]) + "");
		int a = dp(i + value, j, i, j);
		int b = dp(i - value, j, i, j);
		int c = dp(i, j + value, i, j);
		int d = dp(i, j - +value, i, j);
		if(a==-2 || b==-2 || c==-2 || d==-2){
			cond=true;
			return -2;
		}
		return dp[i][j] = Math.max(a + 1,
				Math.max(b + 1, Math.max(c + 1, d + 1)));

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
