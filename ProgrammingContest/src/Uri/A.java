package Uri;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
			int cont = 0;
			String cad = in.readLine();
			int total = 0;
			char a[] = cad.split(" ")[0].toCharArray();
			char b[] = cad.split(" ")[1].toCharArray();
			for (int j = 0; j < b.length; j++) {
				if (a[j] <= b[j]) {
					total += b[j] - a[j];
				} else {
					total += 26 -  (a[j]-b[j]); 
				}
			}
			out.append(total+"\n");
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
