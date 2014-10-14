package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Knight {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static int proff = 11;
	public static char map[][];
	public static int max = Integer.MAX_VALUE;
	public static char[][] res = { { '1', '1', '1', '1', '1' },
		{ '0', '1', '1', '1', '1' }, { '0', '0', ' ', '1', '1' },
		{ '0', '0', '0', '0', '1' }, { '0', '0', '0', '0', '0' } };


	public static boolean ok(char map[][], int x, int y) {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {

					if (map[i][j] != res[i][j])
						return false;
			}
		}
		return true;
	}

	public static int dX[] = { -2, -2, -1, 1, 2, 2, 1, -1 };
	public static int dY[] = { -1, 1, 2, 2, 1, -1, -2, -2 };

	public static boolean validate(int x, int y) {
		if (x < 0 || x >= 5 || y < 0 || y >= 5)
			return false;
		return true;
	}

	public static int tempX, tempY;

	public static char[][] copy(char map[][]) {
		char tempp[][] = new char[5][5];
		for (int i = 0; i < tempp.length; i++) {
			for (int j = 0; j < tempp.length; j++) {
				tempp[i][j] = map[i][j];
			}
		}
		return tempp;
	}
	public static class node{
		int x;
		int y;
		char map[][];
		int proff;
		int antX;
		int antY;
		public node(int x, int y, char[][] map, int proff, int antX, int antY) {
			super();
			this.x = x;
			this.y = y;
			this.map = map;
			this.proff = proff;
			this.antX = antX;
			this.antY = antY;
		}
		
	}
	public static int dfs(char map[][], int x, int y, int proff, int antX,
			int antY) {
		
		Queue<node> pila = new LinkedList<node>();
		pila.add(new node(x, y, map, proff, antX, antY));
		
		w:while(!pila.isEmpty()){
			node actual = pila.poll();
			x = actual.x;
			y = actual.y;
			if(actual.proff<=10){
				if (ok(actual.map, x, y)) {
					if (max >actual.proff) {
						max = actual.proff;
						break w;
					}
				}
				for (int i = 0; i < dX.length; i++) {
					tempX = dX[i] + x;
					tempY = dY[i] + y;
					if (validate(tempX, tempY) && (actual.antX != tempX || actual.antY != tempY)) {

						char actually[][] = copy(actual.map);
						actually[x][y] = actual.map[tempX][tempY];
						actually[tempX][tempY] = ' ';
						pila.add(new node(tempX, tempY, actually, actual.proff+1, actual.x, actual.y));
					}
				}
			}
		}
		

		return -1;

	}

	public static void main(String[] args) throws IOException {
		long inicio = System.currentTimeMillis();
		StringBuilder out = new StringBuilder();
		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		String line = "";
		char cad[];
		int n = Integer.parseInt(in.readLine());
		map = new char[5][5];

		int x = 0;
		int y = 0;
		for (int i = 0; i < n; i++) {
			max = Integer.MAX_VALUE;
			for (int j = 0; j < 5; j++) {
				cad = in.readLine().toCharArray();
				for (int k = 0; k < cad.length; k++) {
					if (cad[k] == '0')
						map[j][k] = '0';
					else
						map[j][k] = '1';
					if (cad[k] == ' ') {
						x = j;
						y = k;
						map[j][k] = ' ';
					}
				}
			}

			dfs(map, x, y, 0, -1, -1);
			if (max != Integer.MAX_VALUE) {
				out.append("Solvable in " + max + " move(s).\n");
			} else {
				out.append("Unsolvable in less than 11 move(s).\n");
			}

		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
