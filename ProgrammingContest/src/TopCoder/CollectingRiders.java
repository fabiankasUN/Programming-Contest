package TopCoder;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class CollectingRiders {

	public static int arr[][];
	public static int dp[][][][];
	public static int sum[][];
	public static boolean v[][];
	public static int actually;

	public static int dp() {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] != ('.' - '0'))
					calculate(i, j);
				else {
					for (int k = 0; k < arr.length; k++) {
						for (int l = 0; l < arr[0].length; l++) {
							dp[i][j][k][l] = 0;
						}
					}
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				int temp = 0;
				boolean cond = true;
				p: for (int k = 0; k < arr.length; k++) {
					for (int l = 0; l < arr[0].length; l++) {
						if (dp[k][l][i][j] == -1) {
							sum[i][j] = -1;
							;
							cond = false;
							break p;
						} else {
							temp += Math.ceil(dp[k][l][i][j]
									/ (Integer.parseInt(arr[k][l] + "") * 1.0));
						}

					}
				}
				if (cond) {
					sum[i][j] = temp;
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < sum.length; i++) {
			for (int j = 0; j < sum[0].length; j++) {
				if (sum[i][j] != -1)
					min = Math.min(min, sum[i][j]);
			}
		}
		return min;
	}

	public static class Node {
		int i, j, value;

		public Node(int i, int j, int value) {
			super();
			this.i = i;
			this.j = j;
			this.value = value;
		}

	}

	public static int pX[] = { 2, 2, 1, -1, -2, -2, 1, -1 };
	public static int pY[] = { -1, 1, 2, 2, 1, -1, -2, -2 };

	public static void calculate(int x, int y) {

		Queue<Node> cola = new LinkedList<Node>();
		cola.add(new Node(x, y, 0));

		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[0].length; j++) {
				v[i][j] = false;
			}
		}
		v[x][y] = true;
		dp[x][y][x][y] = 0;

		while (!cola.isEmpty()) {
			Node actually = cola.poll();
			for (int i = 0; i < pX.length; i++) {
				if (actually.i + pX[i] >= 0 && actually.i + pX[i] < arr.length
						&& actually.j + pY[i] >= 0
						&& actually.j + pY[i] < arr[0].length
						&& v[actually.i + pX[i]][actually.j + pY[i]] == false) {
					v[actually.i + pX[i]][actually.j + pY[i]] = true;
					dp[x][y][actually.i + pX[i]][actually.j + pY[i]] = actually.value + 1;
					cola.add(new Node(actually.i + pX[i], actually.j + pY[i],
							actually.value + 1));
				}
			}
		}

	}

	public static int minimalMoves(String[] board) {
		arr = new int[board.length][board[0].length()];
		for (int i = 0; i < board.length; i++) {
			char c[] = board[i].toCharArray();
			for (int j = 0; j < board[0].length(); j++) {
				arr[i][j] = c[j] - '0';
			}
		}
		sum = new int[board.length][board[0].length()];
		dp = new int[board.length][board[0].length()][board.length][board[0]
				.length()];
		v = new boolean[board.length][board[0].length()];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length(); j++) {
				for (int k = 0; k < board.length; k++) {
					for (int l = 0; l < board[0].length(); l++) {
						dp[i][j][k][l] = -1;
					}
				}
			}
		}
		int res = dp();
		return res == Integer.MAX_VALUE ? -1 : res;

	}

	public static void main(String[] args) {
		String board[] = { "..", "2.", ".." };
		System.out.println(minimalMoves(board));
	}
}
