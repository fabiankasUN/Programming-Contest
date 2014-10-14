package Live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArbiterLogin {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	// split de String
	public static String[] atos(String cad) {
		return cad.split(" ");
	}

	// parsing de String a int
	public static int parseo(String cad, int index) {
		return Integer.parseInt(cad.split(" ")[index]);
	}

	// memory status
	static String memoryStatus() {
		return (Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory() >> 20)
				+ "/" + (Runtime.getRuntime().totalMemory() >> 20) + " MB";
	}

	// imprimir array
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

	// impresion de datos
	static void print(Object... obj) {
		for (int i = 0; i < obj.length; i++) {
			if (i != 0)
				System.out.print(" ");
			System.out.print(obj[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine().trim());
		String l = "";
		for (int i = 0; i < n; i++) {
			l = "Case " + (i + 1) + ": ";
			String values[] = in.readLine().trim().split(" ");
			String cad = values[0];
			String res = values[1];
			if (cad.length() == res.length()) {
				if (cad.equals(res)) {
					System.out.println(l + "Login successful.");
				} else {
					int sum = 0;
					int sum2 = 0;
					for (int j = 0; j < cad.length(); j++) {
						if (cad.charAt(j) == (char) (res.charAt(j) + 32)) {
							sum++;
						}
						if ((char) (cad.charAt(j) + 32) == res.charAt(j)) {
							sum2++;
						}
						if (cad.charAt(j) == res.charAt(j)
								&& (cad.charAt(j) >= '0' && cad.charAt(j) <= '9')
								&& (res.charAt(j) >= '0' && res.charAt(j) <= '9')) {
							sum++;
						}
					}
					if (sum + sum2 == cad.length()) {
						System.out
								.println(l
										+ "Wrong password. Please, check your caps lock key.");
					} else {
						System.out.println(l + "Wrong password.");
					}
				}
			} else {
				String a = "", b = "";
				for (int j = 0; j < cad.length(); j++) {
					if (cad.charAt(j) < '0' || cad.charAt(j) > '9') {
						a += cad.charAt(j);
					}
				}
				if (a.equals(res)) {
					System.out
							.println(l
									+ "Wrong password. Please, check your num lock key.");
				} else {
					int sum = 0;
					int sum2 = 0;
					for (int j = 0; j < a.length(); j++) {
						if (a.charAt(j) == (char) (res.charAt(j) + 32)) {
							sum++;
						}
						if ((char) (a.charAt(j) + 32) == res.charAt(j)) {
							sum2++;
						}
					}
					if (sum + sum2 == res.length()) {
						System.out
								.println(l
										+ "Wrong password. Please, check your caps lock and num lock keys.");
					} else {
						System.out.println(l + "Wrong password.");
					}
				}
			}

		}
	}
}
