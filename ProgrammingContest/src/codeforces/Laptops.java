package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Laptops {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static class laptop implements Comparable<laptop> {
		int price;
		int q;

		public laptop(int price, int q) {
			this.price = price;
			this.q = q;
		}

		@Override
		public int compareTo(laptop o) {
			if (price == o.price)
				return q - o.q;
			return price - o.price;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		int n = Integer.parseInt(line);
		int dates[];
		ArrayList<laptop> lista = new ArrayList<laptop>();
		for (int i = 0; i < n; i++) {
			dates = atoi(in.readLine());
			lista.add(new laptop(dates[0], dates[1]));
		}

		Collections.sort(lista);

		int minPrice = lista.get(0).price;
		int minQ = lista.get(0).q;
		int maxQ = lista.get(0).q;
		boolean k = false;
		for (int i = 1; i < lista.size() && !k; i++) {
			if (lista.get(i).price > lista.get(i - 1).price) {
				if (lista.get(i).q < maxQ) {
					k = true;
				}
			} else if (lista.get(i).price == lista.get(i - 1).price) {
				if (lista.get(i).q < minQ && lista.get(i).price > minPrice) {
					k = true;
				}
			}
			minQ = Math.min(minQ, lista.get(i).q);
			maxQ = Math.max(maxQ, lista.get(i).q);

		}

		if (k) {
			System.out.println("Happy Alex");
		} else {
			System.out.println("Poor Alex");
		}

	}
}
