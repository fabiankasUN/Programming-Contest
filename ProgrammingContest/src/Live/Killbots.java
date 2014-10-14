package Live;
import java.awt.Point;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Killbots {

	public static class Ficha {
		int x;
		int y;
		char tipo;
		boolean viva;
		int jug;
		int jug2;

		public Ficha(int x, int y, char tipo) {
			this.x = x;
			this.y = y;
			this.tipo = tipo;
			viva = true;
			jug = 0;
			jug2 = 0;
		}

		public String toString() {
			return x + " " + y + " jug : " + jug;
		}
	}

	public static char[][] map = new char[1010][1010];
	public static Point hero = null;
	public static int filas = 0;
	public static int col = 0;
	public static int numFichas = 0;
	public static int muertas = 0;
	public static int matadas = 0;
	public static Queue<Ficha> cola;
	public static Queue<Ficha> cola2;
	public static Queue<Ficha> pila2;
	public static HashMap<Punto, Ficha> tabla = new HashMap<Punto, Ficha>();
	public static int atack;
	public static int cont;
	public static int t;
	public static int w = 0;
	public static int cont2 = 0;

	public static class Punto implements Comparator<Punto> {
		int x;
		int y;

		public Punto(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compare(Punto o1, Punto o2) {
			if (o1.x == o2.x && o1.x == o2.x)
				return 0;
			return 1;
		}

		@Override
		public boolean equals(Object obj) {

			if (this.hashCode() == obj.hashCode())
				return true;
			return false;
		}

		@Override
		public int hashCode() {
			int hash = 1;
			hash = hash * 17 + this.x;
			hash = hash * 31 + this.y;
			return hash;
		}
	}

	public static void moverFicha(Ficha actual) {
		try {

			if (actual.jug == atack - 1)
				actual.jug++;
			actual.jug2++;
			int x = actual.x;
			int y = actual.y;

			if (actual.x == hero.x) {
				actual.y += actual.y < hero.y ? 1 : -1;
			} else if (actual.y == hero.y) {
				actual.x += actual.x < hero.x ? 1 : -1;
			} else if (actual.y < hero.y && actual.x < hero.x) {
				actual.y += 1;
				actual.x += 1;
			} else if (actual.y > hero.y && actual.x > hero.x) {
				actual.y -= 1;
				actual.x -= 1;
			} else if (actual.y < hero.y && actual.x > hero.x) {
				actual.y += 1;
				actual.x -= 1;
			} else {
				actual.y -= 1;
				actual.x += 1;
			}

			if (map[actual.x][actual.y] == '@') {
				matadas++;
				actual.viva = false;
				muertas--;
				map[x][y] = '.';
				tabla.put(new Punto(x, y), null);
			} else if (map[actual.x][actual.y] == '*') {
				actual.viva = false;
				map[x][y] = '.';
				tabla.put(new Punto(x, y), null);
				muertas--;
			} else if (map[actual.x][actual.y] == '.') {
				map[actual.x][actual.y] = actual.tipo;
				map[x][y] = '.';
				tabla.put(new Punto(x, y), null);
				tabla.put(new Punto(actual.x, actual.y), actual);
			} else {
				Ficha nueva = tabla.get(new Punto(actual.x, actual.y));
				if (nueva != null) {
					if ((actual.jug == nueva.jug && w == 0)
							|| (actual.jug2 == nueva.jug2 && w == 1)
							|| (actual.jug == nueva.jug && w == 1 && nueva.tipo == '+')) {
						actual.viva = false;
						nueva.viva = false;
						map[actual.x][actual.y] = '*';
						map[x][y] = '.';
						muertas -= 2;
						tabla.put(new Punto(actual.x, actual.y), null);
						tabla.put(new Punto(x, y), null);
					} else {
						if (w == 0)
							actual.jug--;

						actual.jug2--;
						actual.x = x;
						actual.y = y;
						if (w == 0) {
							if (t == 0)
								cola.add(actual);
							else
								cola2.add(actual);
							cont++;
						} else {
							pila2.add(actual);
							cont2++;
						}

					}
				}

			}
		} catch (Exception e) {
			return;

		}

	}

	public static void printMap() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(System.in));
		String line = "";
		Scanner scan = new Scanner(System.in);
		// while ((line = in.readLine().trim()) != null) {
		while (scan.hasNext()) {
			// if (line.equals(""))
			// break;3
			line = scan.next();
			filas = 0;
			numFichas = 0;
			muertas = 0;
			matadas = 0;
			atack = 0;
			hero = new Point(0, 0);
			cola = new LinkedList<Ficha>();
			cola2 = new LinkedList<Ficha>();
			pila2 = new LinkedList<Ficha>();
			tabla = new HashMap<Punto, Ficha>();
			t = 0;
			cont = 0;
			do {

				String c = line.trim();
				col = c.length();
				for (int i = 0; i < c.length(); i++) {
					map[filas][i] = line.charAt(i);
					if (map[filas][i] != '.' && map[filas][i] != '*'
							&& map[filas][i] != '@') {
						Ficha temp = new Ficha(filas, i, map[filas][i]);
						cola.add(temp);
						tabla.put(new Punto(filas, i), temp);
						numFichas++;
						if (map[filas][i] == '+' || map[filas][i] == '#') {
							muertas++;
						}
					} else if (map[filas][i] == '@') {
						hero = new Point(filas, i);
					}
				}
				filas++;

			} while (!(line = scan.next().trim()).equals("$"));

			col = map[0].length;
			// printMap();
			t = 0;
			while (muertas > 0) {
				atack++;
				cont = 0;
				w = 0;
				cont2 = 0;
				if (t == 0)
					cont = cola.size();
				else
					cont = cola2.size();

				for (int i = 0; i < cont && ((!cola.isEmpty() && t==0) || (t==1 && !cola2.isEmpty())); i++) {

					Ficha actual = t == 0 ? cola.poll() : cola2.poll();
					if (actual.viva)
						moverFicha(actual);
					if (actual.tipo == '#' && actual.viva
							&& actual.jug == atack) {
						pila2.add(actual);
						cont2++;
					} else if (actual.viva && actual.jug == atack) {
						if (t == 0)
							cola2.add(actual);
						else
							cola.add(actual);
					}
				}
				w++;
				for (int i = 0; i < cont2 && !pila2.isEmpty(); i++) {
					Ficha actual = pila2.poll();
					if (actual.viva)
						moverFicha(actual);
					if (actual.viva && actual.jug2 == atack * 2)
						if (t == 0)
							cola2.add(actual);
						else
							cola.add(actual);
				}
				if (t == 0)
					t = 1;
				else
					t = 0;
			}

			System.out.println(matadas);
		}
	}
}
