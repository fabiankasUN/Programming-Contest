package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoggleBlitz {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static char arr[][] = new char[21][21];
	public static int k;

	public static void main(String[] args) throws IOException {
		long inicio = System.currentTimeMillis();
		StringBuilder out = new StringBuilder();
		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		String line = "";
		int n = Integer.parseInt(in.readLine());
		in.readLine();
		char c[];
		for (int i = 0; i < n; i++) {
			k = Integer.parseInt(in.readLine());
			c = in.readLine().toCharArray();
			for (int j = 0; j < c.length; j++) {
				arr[i][j] = c[j];
			}

			if (i != n - 1)
				in.readLine();
		}

		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
