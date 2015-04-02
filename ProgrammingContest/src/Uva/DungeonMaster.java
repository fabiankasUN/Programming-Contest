package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class DungeonMaster {

	// split de array
	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static class Node {
		int x;
		int y;
		int w;
		int p;

		public Node( int x, int y, int w, int p ) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.p = p;
		}

	}

	public static int dx[] = { 0, 0, 1, -1 };
	public static int dy[] = { -1, 1, 0, 0 };

	public static boolean isValid(int x, int y, int n, int m) {
		if (x < 0 || x >= n || y < 0 || y >= m)
			return false;
		return true;
	}

	public static int bfs(int x, int y, int r, int fx, int fy, char map[][][],
			int k, int n, int m) {
		int res = -1;

		boolean visited[][][] = new boolean[k][n][m];
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(x, y, 0, r));
		visited[r][x][y] = true;

		while (!q.isEmpty()) {
			Node a = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int px = a.x + dx[i];
				int py = a.y + dy[i];
				if (isValid(px, py, n, m) == true && visited[a.p][px][py] == false
						&& map[a.p][px][py] == '.') {
					q.add(new Node(px, py, a.w + 1, a.p));
					visited[a.p][px][py] = true;
				} else if (isValid(px, py, n, m) == true
						&& visited[a.p][px][py] == false && map[a.p][px][py] == 'E') {
					return a.w + 1;
				}

			}
			if (a.p < k - 1) {
				if ((map[a.p + 1][a.x][a.y] == '.' || map[a.p + 1][a.x][a.y] == 'E')
						&& visited[a.p + 1][a.x][a.y] == false) {
					if (map[a.p + 1][a.x][a.y] == 'E') {
						return a.w + 1;
					}
					visited[a.p + 1][a.x][a.y] = true;
					q.add(new Node(a.x, a.y, a.w + 1, a.p + 1));
				}
			}

			if (a.p > 0) {
				if ((map[a.p - 1][a.x][a.y] == '.' || map[a.p - 1][a.x][a.y] == 'E')
						&& visited[a.p - 1][a.x][a.y] == false) {
					if (map[a.p - 1][a.x][a.y] == 'E') {
						return a.w + 1;
					}
					visited[a.p - 1][a.x][a.y] = true;
					q.add(new Node(a.x, a.y, a.w + 1, a.p - 1));
				}
			}

		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		StringBuilder out = new StringBuilder();
		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		String line = "";

		char map[][][];
		int times = 0;
		while ((line = in.readLine()) != null) {
			if (times++ > 0)
				line = in.readLine();
			String lines[] = line.split(" ");
			int numberMap = Integer.parseInt(lines[0]);
			int n = Integer.parseInt(lines[1]);
			int m = Integer.parseInt(lines[2]);

			if (numberMap == 0 && n == 0 && m == 0)
				break;
			map = new char[numberMap][n][];
			int x = 0, y = 0, r = 0;
			int fx = 0, fy = 0;
			for (int i = 0; i < numberMap; i++) {
				if (i > 0)
					in.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = in.readLine().toCharArray();

					for (int k = 0; k < m; k++) {
						if (map[i][j][k] == 'S') {
							x = j;
							y = k;
							r = i;
						}

						if (map[i][j][k] == 'E') {
							fx = j;
							fy = k;
						}
					}
				}

			}

			int res = bfs(x, y, r, fx, fy, map, numberMap, n, m);
			if (res == -1) {
				out.append("Trapped!\n");
			} else {
				out.append("Escaped in " + res + " minute(s).\n");
			}
		}

		System.out.print(out);
	}
}
