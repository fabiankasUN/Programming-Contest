package TopCoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {

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
		String cad = in.readLine();
		String reves = "";
		for (int i = 0; i < cad.length(); i++) {
			reves += cad.charAt(cad.length() - i - 1);
		}
		String num = Integer.parseInt(reves, 2) + "";
		int p = Integer.parseInt(num) + 1;
		String w = new Integer(p).toString(p, 2);
		if (w.length() < reves.length()) {
			String temp = "";
			for (int i = 0; i < reves.length() - w.length(); i++) {
				temp += "0";

			}
			w = temp + w;
		}
		int cont = 0;
		int a = reves.length() - 1, b = w.length() - 1;
		while (b >= w.length() - reves.length()) {
			if (w.charAt(b) != reves.charAt(a)) {
				cont++;
			}
			b--;
			a--;
		}

		System.out.println(cont);

	}
}
