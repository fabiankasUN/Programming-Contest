package TopCoder;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class BishopMove {

	public static int howManyMoves(int r1, int c1, int r2, int c2) {
		if (validate(r1, c1, r2, c2))
			if (r1 == r2 && c1 == c2)
				return 0;
			else
				return 1;
		res = -1;
		dfs(r1, c1, r2, c2, 0);
		if(res>2)
			return -1;
		return res;
	}

	public static int res = 0;
	public static int dX[] = { -1, -1, 1, 1 };
	public static int dY[] = { -1, 1, -1, 1 };

	public static boolean validate(int r1, int c1, int r2, int c2) {
		if (r1 + c1 == r2 + c2 || Math.abs(r1 - c1) == Math.abs(r2 - c2))
			return true;
		return false;

	}

	public static void dfs(int r1, int c1, int r2, int c2, int d) {

		Queue<Point> cola = new LinkedList<Point>();
		Queue<Integer> cola2 = new LinkedList<Integer>();
		cola.add(new Point(r1, c1));
		cola2.add(1);
		boolean arr[][] = new boolean[8][8];
		arr[r1][c1] = true;
		res=1;
		p:while (!cola.isEmpty()) {
			Point actual = cola.poll();
			int proff = cola2.poll();
			if(proff>2){
				res=100;
				break p;
			}
			for (int i = 0; i < dX.length; i++) {
				int x = actual.x + dX[i];
				int y = actual.y + dY[i];
				if (validate(x, y, r2, c2)) {
					res = proff + 1;
					break p;
				} else if (x >= 0 && x < 8 && y >= 0 && y < 8 && !arr[x][y]) {
					cola.add(new Point(x, y));
					cola2.add(proff + 1);
					arr[x][y] = true;
				}
			}
		}

	}

	public static void main(String[] args) {
		System.out.println(1<<1);
		System.out.println(howManyMoves(1, 3, 5, 5));
	}
}
