package COJ;

import java.io.IOException;
import java.util.Scanner;

public class Stockholm {

	public static void main(String[] args) {
		Scanner scan;
		scan = new Scanner(System.in);

		int k = scan.nextInt();
		while (k-- > 0) {
			long n = scan.nextLong();
			long res = 1;
			long c = 0;
			while (n > 0) {
				if (n == 1) {
					if (c % 2 == 0) {
						res += 3;

					} else {
						res += 2;
						c++;
					}
					break;
				}
				long cont = 2;
				long temp = 1;
				while (cont <= n) {
					temp = temp * 2;
					cont *= 2;
				}
				temp *= 2;
				n = n - cont / 2;
				res += temp;
				c++;
			}
			if (c % 2 != 0)
				res++;
			System.out.println(res - 1);
		}
	}
}
