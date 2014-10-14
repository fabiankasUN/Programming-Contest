package Uri;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class I {

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
		for (int i = 0; i < n; i++) {
			int dates[] = atoi(in.readLine());
			int M = dates[1];
			int c = dates[0];
			int arr[] = new int[c + 1];
			int d[] = atoi(in.readLine());
			for (int j = 0; j < M; j++) {
				arr[d[j]]++;
			}
			boolean second = false;
			for (int j = 1; j < arr.length; j++) {
				if (arr[j] > M / 2) {
					out.append(j + "\n");
					second = true;
					break;
				}
			}
			if (second == false) {
				out.append(-1 + "\n");
			}

		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
