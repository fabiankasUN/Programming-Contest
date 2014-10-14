package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Boredom {
	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;

		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else {
			in = new BufferedReader(new InputStreamReader(System.in));
		}
		int n = Integer.parseInt(in.readLine());
		String line[] = in.readLine().split(" ");
		int arr[] = new int[100001];
		for (int i = 0; i < line.length; i++) {
			arr[Integer.parseInt(line[i])]++;
		}
		for (int i = 0; i < line.length; i++) {
			arr[i] = arr[i] * i;
		}
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				if (arr[i + 1] == 0) {
					total += arr[i] * i;
					arr[i] = -1000;
				}
			} else {
				if (i == arr.length - 1) {
					if (arr[i - 1] == 0) {
						total += arr[i] * i;
						arr[i] = -1000;
					}

				} else if (arr[i + 1] == 0 && arr[i - 1] == 0) {
					total += arr[i] * i;
					arr[i] = -1000;
				}
			}
		}
		boolean mark[] = new boolean[100001];
		for (int i = 0; i < arr.length - 2; i++) {
			int a = arr[i];
			int b = arr[i + 1];
			int c = arr[i + 2];
			if (a != -1000 && a > b && !mark[a]) {
				mark[a] = true;
				total += a;
			} else if (b != -1000 && b > c && !mark[b]) {
				mark[i] = true;
				mark[i + 2] = true;
				total += b;
			}
		}
		System.out.println(total);
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
}
