package Uva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Risk {

	// split de array
	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static int grafo[][];
	public static final int INF = (Integer.MAX_VALUE - 1) / 2;

	public static void inint(int n) {
		grafo = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				if (i != k)
					grafo[i][k] = INF;
			}
		}
	}

	public static void floyd(int rows) {

		for (int k = 0; k < rows; k++) {
			// print(distancias);
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < rows; j++) {
					if (grafo[i][j] > grafo[i][k] + grafo[k][j]) {
						grafo[i][j] = grafo[i][k] + grafo[k][j];
					}
				}
			}
		}

	}

	public static void floyd(int n, int g[][]) {

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
		int arr[];
		int times = 1;
		while ((line = in.readLine()) != null) {
			
			inint(21);
			for (int i = 0; i < 19; i++) {
				if (i == 0)
					arr = readInts(line);
				else
					arr = readInts(in.readLine());
				for (int j = 1; j < arr.length; j++) {
					grafo[i + 1][arr[j]] = 1;
					grafo[arr[j]][i + 1] = 1;
				}
			}

			int q = Integer.parseInt(in.readLine());
			floyd(21);
			out.append("Test Set #" + times+++"\n");
			
			for (int i = 0; i < q; i++) {
				arr = readInts(in.readLine());
				if(arr[0]<10)
					out.append(" ");
				out.append(arr[0] + " to ");
				if(arr[1]<10)
					out.append(" ");
				out.append(arr[1] + ": " + grafo[arr[0]][arr[1]]+"\n");
			}
			out.append("\n");
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
