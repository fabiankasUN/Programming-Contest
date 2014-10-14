package Uri;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {

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
			int d = dates[0];
			int ing = dates[1];
			int type = dates[2];
			int prizes[] = atoi(in.readLine());
			int max = 0;
			for (int j = 0; j < type; j++) {
				int pankake[] = atoi(in.readLine());
				int p = 0;

				for (int k = 1; k < pankake.length; k += 2) {
					p += prizes[pankake[k]] * pankake[k + 1];
				}
				if (d / p > max)
					max = d / p;
			}
			out.append(max + "\n");
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
