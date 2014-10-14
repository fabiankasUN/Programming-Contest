package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dice {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		}
		String line = in.readLine();
		int n = Integer.parseInt(line);
		int k = 24 / n;
		if (n <= 3) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
			if (n == 4) {
				System.out.println("1 * 2 = 2");
				System.out.println("2 * 3 = 6");
				System.out.println("6 * 4 = 24");
			}
			if (n == 5) {
				System.out.println("5 * 4 = 20");
				System.out.println("2 + 3 = 5");
				System.out.println("5 - 1 = 4");
				System.out.println("20 + 4 = 24");
			}
			if (n > 5) {
				if (n % 2 == 0) {
					if (n % 4 != 0) {
						System.out.println("4 * 3 = 12");
						System.out.println("12 * 2 = 24");
						long sum = 24;
						long sig = 2;
						System.out.println(sum + " + " + n + " = " + (sum + n));
						sum += n;
						for (int i = n - 1; i > 4; i--) {
							if (sig <= 1) {

								System.out.println(sum + " + " + i + " = "
										+ (sum + i));
								sum = sum + i;
								sig++;
							} else if (sig <= 3) {
								System.out.println(sum + " - " + i + " = "
										+ (sum - i));
								sum -= i;
								sig++;
								if (sig == 4)
									sig = 0;
							}
						}
						System.out.println(sum + " - " + 1 + " = " + (sum - 1));
					} else {

						System.out.println("4 * 3 = 12");
						System.out.println("12 * 2 = 24");
						System.out.println("24 * 1 = 24");
						long sum = 24;
						long sig = 2;
						System.out.println(sum + " + " + n + " = " + (sum + n));
						sum += n;
						for (int i = n - 1; i > 4; i--) {
							if (sig <= 1) {

								System.out.println(sum + " + " + i + " = "
										+ (sum + i));
								sum = sum + i;
								sig++;
							} else if (sig <= 3) {
								System.out.println(sum + " - " + i + " = "
										+ (sum - i));
								sum -= i;
								sig++;
								if (sig == 4)
									sig = 0;
							}
						}
					}
				} else {
					System.out.println("5 * 4 = 20");
					System.out.println("2 + 3 = 5");
					System.out.println("5 - 1 = 4");
					System.out.println("20 + 4 = 24");
					for (int i = n; i>=7; i-=2) {
						System.out.println( i +" - " + (i-1) +" = 1");
						System.out.println("24 * 1 = 24");
					}
					

				}

			}
		}

	}
}
