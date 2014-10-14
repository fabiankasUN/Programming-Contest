package Uva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class TestingCATCHER {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static int arr[];
	public static int dp[];

	public static int dfs(int rank) {
		if (rank == arr.length) {
			return 0;
		}
		if (dp[rank] != -1) {
			return dp[rank];
		}
		int ans = 0;
		for (int i = rank; i < arr.length; i++) {
			if (rank == 0) {
				ans = Math.max(ans, 1 + dfs(i + 1));
			} else {
				if ((arr[rank - 1] > arr[i])) {
					ans = Math.max(ans, dfs(i + 1) + 1);
				}
			}
		}
		return dp[rank] = ans;

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
		int times = 0;
		while ((line = in.readLine()) != null) {
			if (line.equals("-1"))
				break;
			if (times++ > 0)
				out.append("\n");
			ArrayList<Integer> lista = new ArrayList<Integer>();
			lista.add(Integer.parseInt(line));

			while (!(line = in.readLine()).equals("-1")) {
				lista.add(Integer.parseInt(line));
			}

			arr = new int[lista.size()];
			dp = new int[arr.length];
			for (int i = 0; i < dp.length; i++) {
				dp[i] = -1;
			}
			for (int i = 0; i < arr.length; i++) {
				arr[i] = lista.get(i);
			}
			
			
			out.append("Test #" + times + ":\n"
					+ "  maximum possible interceptions: " + dfs(0) + "\n");
			System.out.println();
		}
		System.out.print(out);
		if (archivo.exists())
			System.out.println("Tiempo transcurrido : "
					+ (System.currentTimeMillis() - inicio) + " milisegundos.");
	}
}
