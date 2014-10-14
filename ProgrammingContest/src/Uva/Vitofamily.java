package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Vitofamily {

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
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int n = Integer.parseInt(in.readLine());

		for (int i = 0; i < n; i++) {
			int arr[] = atoi(in.readLine());
			if (arr[0] == 1)
				out.append("0\n");
			else {
				int max = Integer.MAX_VALUE;
				for (int j = 1; j < arr.length; j++) {
					int temp = 0;
					for (int k = 1; k < arr.length; k++) {
						if (j != k) {
							temp += Math.abs(arr[j] - arr[k]);
						}
					}
					if (temp < max) {
						max= temp;
					}
				}
				out.append(max+"\n");
			}

		}

		System.out.print(out);
	}
}
