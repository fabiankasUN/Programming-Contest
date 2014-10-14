package Uva;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Ones {

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
		//File archivo = new File("entrada");
		//if (archivo.exists())
		//	in = new BufferedReader(new FileReader(archivo));
		//else
		in = new BufferedReader(new InputStreamReader(System.in));
		
		String line = "";
		int n, rmod, c, aux, rmodf;
		while ((line = in.readLine()) != null) {
			n = Integer.parseInt(line.trim());
			rmod = 1 % n;
			rmodf = rmod;
			aux = 10 % n;
			c = 1;
			while (rmodf != 0) {
				rmod = (rmod * (aux)) % n;
				rmodf = (rmodf + rmod) % n;
				c++;
			}
			out.append(c + "\n");

		}
		System.out.print(out);
		//if (archivo.exists())
		//	System.out.println("Tiempo transcurrido : "
		//			+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
