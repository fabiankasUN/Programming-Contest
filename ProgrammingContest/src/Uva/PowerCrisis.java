package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PowerCrisis {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static void reset(boolean a[]) {
		Arrays.fill(a, false);
	}

	public static void main(String[] args) throws IOException {
		long inicio = System.currentTimeMillis();

		BufferedReader in;
		File archivo = new File("entrada");
		if (archivo.exists())
			in = new BufferedReader(new FileReader(archivo));
		else
			in = new BufferedReader(new InputStreamReader(System.in));

		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine().trim()) != null) {
			int n = Integer.parseInt(line);
			boolean v[] = new boolean[n];
			for (int i = 1; i <= n; i++) {
				int cont = n-1;
				int k = 0;
				int index = 0;
				reset(v);
				v[0] = true;
				boolean cond = false;
				while (!v[12]) {
					
					k = 0;
					while (k < i) {
						if (v[index]) {
							index = (index + 1) % n;
						} else {
							k++;
							if(k!=i)
							index = (index + 1) % n;
						}
					}
					
					
					cont--;
					v[index] = true;
					if (index == 12) {
						if (cont > 0)
							cond = true;
						break;
					}
				}
				if (!cond) {
					out.append(i + "\n");
					break;
				}
			}
		}
		System.out.print(out);

		if (archivo.exists()) {
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
		}
	}
}
