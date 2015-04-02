package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PashaandPixels {

	// split de array
	public static int[] readInts(String cad) {
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

		int arr[] = readInts(in.readLine());
		int n = arr[0];
		int m = arr[1];
		int k = arr[2];
		boolean map[][] = new boolean[n][m];
		for (int i = 0; i < k; i++) {
			arr = readInts(in.readLine());
			int x = arr[0] - 1;
			int y = arr[1] - 1;
			map[x][y] = true;

			if (x < n - 1 && y < m - 1) {
				if (map[x][y] && map[x][y + 1] && map[x + 1][y]
						&& map[x + 1][y + 1]) {
					System.out.println(i + 1);
					return;
				}
			}

			if (x - 1 >= 0 && y < m - 1) {
				if (map[x][y] && map[x][y + 1] && map[x - 1][y]
						&& map[x - 1][y + 1]) {
					System.out.println(i + 1);
					return;
				}
			}

			if (x - 1 >= 0 && y - 1 >= 0) {
				if (map[x][y] && map[x][y - 1] && map[x - 1][y]
						&& map[x - 1][y - 1]) {
					System.out.println(i + 1);
					return;
				}
			}

			if (x < n - 1 && y - 1 >= 0) {
				if (map[x][y] && map[x][y - 1] && map[x + 1][y]
						&& map[x + 1][y - 1]) {
					System.out.println(i + 1);
					return;
				}
			}

		}

		System.out.println(0);

		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
