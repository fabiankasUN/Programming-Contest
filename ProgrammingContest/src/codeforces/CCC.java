package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC {

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
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();

		int n = Integer.parseInt(line);
		int k = 24 / n;
		if (n <= 3) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
			if (n == 4) {
				System.out.println("2 * 3 = 6");
				System.out.println("6 * 2 = 12");
				System.out.println("12 * 2 = 24");
			}
			if (n == 5) {
				System.out.println("1 + 1 = 2");
				System.out.println("2 * 3 = 6");
				System.out.println("6 * 2 = 12");
				System.out.println("12 * 2 = 24");
			}
			if (n > 5 && n < 24) {
				if (n % 2 == 0) {
					System.out.println("2 * 3 = 6");
					System.out.println("6 * 2 = 12");
					System.out.println("12 * 2 = 24");
					boolean x = false;
					for (int i = 0; i < n - 4; i++) {
						if (i % 2 == 0)
							System.out.println("24 + 1 = 25");
						else {
							System.out.println("24 - 1 = 24");
						}
					}
				} else {
					System.out.println("1 + 1 = 2");
					System.out.println("2 * 3 = 6");
					System.out.println("6 * 2 = 12");
					System.out.println("12 * 2 = 24");
					for (int i = 0; i < n - 5; i++) {
						if (i % 2 == 0)
							System.out.println("24 + 1 = 25");
						else {
							System.out.println("24 - 1 = 24");
						}
					}

				}

			} else if (n >= 24) {
				if (n % 2 == 0) {
					System.out.println("1 + 1 = 2");
					int cont = 2;
					for (int i = 0; i < 24 - 2; i++) {
						System.out.println(cont + " + 1 = " + (cont + 1));
						cont++;
					}

					for (int i = 24; i < n; i++) {
						if (i % 2 == 0) {
							System.out.println("24 + 1 = 25");
						} else {
							System.out.println("25 - 1 = 24");
						}
					}
				} else {
					System.out.println("1 * 3 = 3");
					int cont = 3;
					for (int i = 0; i < 24 - 3; i++) {
						System.out.println(cont + " + 1 = " + (cont + 1));
						cont++;
					}

					for (int i = 24; i <= n; i++) {
						if (i % 2 == 0) {
							System.out.println("24 + 1 = 25");
						} else {
							System.out.println("25 - 1 = 24");
						}
					}
				}
			}
		}

	}
}
