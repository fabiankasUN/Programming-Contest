package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTreeTraversal {

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

	public static int v[];

	public static class Node {
		int date;
		int lv;
		Node left;
		Node right;

		public Node(int date, int lv, Node left, Node right) {
			this.date = date;
			this.lv = lv;
			this.left = left;
			this.right = right;
		}

	}

	public static class Tree {
		Node raiz;
		int cont = 0;

		public Tree(int date) {
			this.raiz = new Node(date, 0, null, null);
			cont++;
		}

		void add(int date) {
			Node temp = raiz;
			while (temp != null) {
				if (temp.date >= date) {
					if (temp.left != null)
						temp = temp.left;
					else {
						temp.left = new Node(date, cont++, null, null);
						break;
					}
				} else {
					if (temp.right != null)
						temp = temp.right;
					else {
						temp.right = new Node(date, cont++, null, null);
						break;
					}
				}
			}
		}

		public void dfs(StringBuilder out) {
			Queue<Node> cola = new LinkedList<Node>();
			cola.add(raiz);
			while (!cola.isEmpty()) {
				Node s = cola.poll();
				if (s.left != null)
					cola.add(s.left);
				if (s.right != null)
					cola.add(s.right);
				out.append(s.date + " ");
			}
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
		for (int i = 0; i < n; i++) {
			out.append("Case " + (i + 1) + ":\n");
			int k = Integer.parseInt(in.readLine());
			v = readInts(in.readLine());
			Tree arbol = new Tree(v[0]);
			for (int j = 1; j < v.length; j++) {
				arbol.add(v[j]);
			}
			arbol.dfs(out);
			out.deleteCharAt(out.length() - 1);
			out.append("\n");
			out.append("\n");
		}
		System.out.print(out);

	}
}
