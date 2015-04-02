package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class NewYearSnowmen {

	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static long[] readLongs(String cad) {
		String read[] = cad.split(" ");
		long res[] = new long[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Long.parseLong(read[i]);
		}
		return res;
	}

	static void printArrayInt(int[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				System.out.print(" ");
			System.out.print(array[i]);
		}
		System.out.println();
	}

	static void printMatrixInt(int[][] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				if (j > 0)
					System.out.print(" ");
				System.out.print(array[i][j]);
			}
			System.out.println();
		}

	}

	public static int max(int arr[]) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		return max;
	}

	public static int min(int arr[]) {
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
		}
		return min;
	}

	public static class Node implements Comparable<Node> {
		int number;
		int c;

		public Node(int number, int c) {
			this.number = number;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return o.c - c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		StringBuilder out = new StringBuilder();
		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		int v[] = readInts(in.readLine());
		ArrayList<Node> snow = new ArrayList<Node>(v.length);
		HashMap<Integer, Integer> tabla = new HashMap<Integer, Integer>();
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		int k = 0;
		if (n < 3) {
			System.out.println(0);
			return;
		} else {
			for (int i = 0; i < v.length; i++) {
				int date;
				if (tabla.containsKey(v[i])) {
					date = tabla.get(v[i]);
					snow.get(date).c++;
				} else {
					tabla.put(v[i], k++);
					snow.add(new Node(v[i], 1));
				}
			}
			for (int i = 0; i < snow.size(); i++) {
				q.add(snow.get(i));
			}
			Node s;
			Node s2;
			Node s3;
			int cont = 0;
			while (q.size() >= 3) {
				s = q.poll();
				s2 = q.poll();
				s3 = q.poll();
				if (s.number > s2.number && s.number > s3.number) {
					if (s2.number > s3.number)
						out.append(s.number + " " + s2.number + " " + s3.number
								+ "\n");
					else
						out.append(s.number + " " + s3.number + " " + s2.number
								+ "\n");
				} else if (s2.number > s.number && s2.number > s3.number) {
					if (s3.number > s.number)
						out.append(s2.number + " " + s3.number + " " + s.number
								+ "\n");
					else
						out.append(s2.number + " " + s.number + " " + s3.number
								+ "\n");

				} else {
					if (s.number > s2.number)
						out.append(s3.number + " " + s.number + " " + s2.number
								+ "\n");
					else
						out.append(s3.number + " " + s2.number + " " + s.number
								+ "\n");

				}

				if (--s.c > 0)
					q.add(s);
				if (--s2.c > 0)
					q.add(s2);
				if (--s3.c > 0)
					q.add(s3);
				cont++;
			}
			System.out.println(cont);
			System.out.print(out);
		}
	}
}
