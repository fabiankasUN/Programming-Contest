package Uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ASCIIArea {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static int dX[] = { 0, 0, 1, -1, -1, -1, 1, 1 };
	public static int dY[] = { -1, 1, 0, 0, -1, 1, 1, -1 };
	public static int h1[][] = { {}, {}, {}, {}, { -1, 0 }, { -1, 0 },
			{ 1, 0 }, { 1, 0 } };
	public static int h2[][] = { {}, {}, {}, {}, { 0, -1 }, { 0, 1 }, { 0, 1 },
			{ 0, -1 } };
	public static int cond[] = { 0, 0, 0, 0, 1, 2, 1, 2 };

	public static void dfs(int map[][], int x, int y) {

		Stack<Point> pila = new Stack<Point>();
		pila.add(new Point(x, y));
		while (!pila.isEmpty()) {
			Point actual = pila.pop();
			x = (int) actual.getX();
			y = (int) actual.getY();
			for (int i = 0; i < dX.length; i++) {
				int a = (int) (dX[i] + actual.getX());
				int b = (int) (dY[i] + actual.getY());
				if (i < 4) {
					if (a >= 0 && a < map.length && b >= 0 && b < map[0].length
							&& map[a][b] == 0) {
						map[a][b] = 3;
						dfs(map, a, b);
					}
				} else {
					int c = x + h1[i][0];
					int d = y + h2[i][0];
					int e = x + h1[i][1];
					int f = y + h2[i][1];
					if (a >= 0 && a < map.length && b >= 0 && b < map[0].length
							&& c >= 0 && c < map.length && d >= 0
							&& d < map[0].length && e >= 0 && e < map.length
							&& f >= 0 && f < map[0].length && map[a][b] == 0
							&& map[c][d] == cond[i] && map[e][f] == cond[i]) {
						map[a][b] = 3;
						dfs(map, a, b);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		char dates[] ;
		while ((line = in.readLine().trim()) != null) {
			int arr[] = atoi(line);
			int n = arr[0];
			int m = arr[1];
			int map[][] = new int[n + 2][m + 2];
			
			for (int i = 1; i < (map.length - 1); i++) {

				dates = in.readLine().trim().toCharArray();
				for (int j = 1; j < (map[i].length - 1); j++) {
					if (dates[j - 1] == '.') {
						map[i][j] = 0;
					}
					else if (dates[j - 1] == '/') {
						map[i][j] = 2;
					}else {
						map[i][j] = 1;
					}
				}
			}

			dfs(map, 0, 0);
			int a = 0;
			int b = 0;

			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if (map[i][j] == 0) {
						b++;
					}
					if (map[i][j] != 3)
						a++;
				}
			}

			a -= b;
			System.out.println((a / 2) + b);
		}
	}
}
