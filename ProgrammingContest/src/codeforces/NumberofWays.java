package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NumberofWays {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
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

	public static int lower_bound(int[] arr, int lower, int upper, int key,
			int ini) {
		int ans = -1;
		while (lower <= upper) {
			int mid = (lower + upper) / 2;
			if (key == (arr[mid]) - arr[ini]) {
				return mid;

			} else if (key < (arr[mid]) - arr[ini]) {
				upper = mid - 1;

			} else {
				lower = mid + 1;

			}
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		int n = Integer.parseInt(line);
		int arr[] = atoi(in.readLine());
		int s1[] = new int[n];
		int s2[] = new int[n];

		int c1[] = new int[n];

		s1[0] = arr[0];
		s2[n - 1] = arr[n - 1];
		c1[0] = arr[0] == 0 ? 1 : 0;
		for (int i = 1; i < arr.length; i++) {
			s1[i] = s1[i - 1] + arr[i];
			s2[n - i - 1] = s2[n - i] + arr[n - i - 1];
			if (arr[i] == 0) {
				c1[i] = c1[i - 1] + 1;
			}
		}

		if (n <= 2) {
			System.out.println(0);
		} else {
			int izq = 1;
			int res = 0;
			while (izq < n - 1) {
				int sumA = s1[izq - 1];
				int index = lower_bound(s1, izq, n - 1, sumA, izq - 1);
				if (index != -1) {
					if (sumA == s2[index + 1]) {
						res++;
						int a = index - 1;
						int b = index + 1;
						while (s1[a] - s1[izq - 1] == sumA && a >= izq) {
							a--;
							res++;
							;
						}
						while (s1[b] - s1[izq - 1] == sumA && b < n - 1) {
							b++;
							res++;
						}
					}
				}
				izq++;

			}
			System.out.println(res);
		}
	}
}
