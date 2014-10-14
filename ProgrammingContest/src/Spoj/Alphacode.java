package Spoj;
import java.io.IOException;
import java.util.Scanner;

public class Alphacode {

	// split de array
	public static int[] atoi(String cad) {
		int res[] = new int[cad.length()];
		for (int i = 0; i < cad.length(); i++) {
			res[i] = Integer.parseInt(cad.charAt(i) + "");
		}
		return res;
	}

	public static void main(String[] args) throws IOException {

		String line = "";
		Scanner scan = new Scanner(System.in);
		while (true) {
			line = scan.next().trim();
			if (line.equals("0"))
				break;

			if (line.length() == 1) {
				System.out.println(1);

			} else {

				int cad[] = atoi(line);
				long dp[] = new long[cad.length];
				for (int i = 0; i < dp.length; i++) {
					dp[i] = 0;
				}
				dp[dp.length - 1] = 1;
				dp[dp.length - 2] = (cad[cad.length - 1] <= 6
						&& cad[cad.length - 1] != 0 && cad[cad.length - 2] == 2 || cad[cad.length - 2] == 1
						&& cad[cad.length - 1] != 0) ? 2 : 1;

				for (int i = dp.length - 3; i >= 0; i--) {
					if (cad[i + 1] == 0) {
						dp[i] = dp[i + 1];
						if (i - 1 >= 0)
							dp[i - 1] = dp[i + 1];
						i--;
					} else if (cad[i] == 2 && cad[i + 1] <= 6 || cad[i] == 1) {
						dp[i] = dp[i + 1] + dp[i + 2];
					} else {
						dp[i] = dp[i + 1];
					}
				}
				System.out.println(dp[0]);
			}

		}
	}
}
