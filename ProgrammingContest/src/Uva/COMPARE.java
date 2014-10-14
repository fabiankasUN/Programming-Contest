package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class COMPARE {

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
		ArrayList l =new ArrayList();
		while ((line = in.readLine()) != null && !line.equals("0 0")) {
					l.add(line);
		}
		ArrayList l2 =new ArrayList();
		while ((line = in.readLine()) != null) {
			l2.add(line);
		}
		for (int i = 0; i < l.size(); i++) {
			if(!l.get(i).equals(l2.get(i)))
			{
				System.out.println("mal");
				break;
			}else{
				System.out.println("bien");
			}
		}
		
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
