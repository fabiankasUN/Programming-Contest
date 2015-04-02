package codeforces;

import java.util.HashMap;

public class probar {

	public static class Point {
		byte x, y;

		public Point(byte x, byte y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Point) {
				Point p = (Point) o;
				return this.x == p.x && this.y == p.y;
			}
			return false;
		}
	}

	public static HashMap<Point, Integer> tabla = new HashMap<Point, Integer>();

	public static void main(String[] args) {
		tabla.put(new Point((byte) 1, (byte) 1), 1);
		System.out.println(tabla.get(new Point((byte) 1, (byte) 1)));
	}
}
