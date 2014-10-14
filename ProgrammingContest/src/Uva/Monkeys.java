package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Monkeys {

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
		StringBuilder out = new StringBuilder();
		String line = in.readLine();
		int n = Integer.parseInt(line);
		int times = 1;
		for (int i = 0; i < n; i++) {
			in.readLine();
			out.append("Case " + times++ + ": ");
			int arr[] = atoi(in.readLine());
			int a;
			int prev = arr[0];
			for (int j = 1; j < arr.length; j++) {
				a = arr[j] - arr[j - 1];
				if (prev == a)
					prev++;
				if (prev < a)
					prev = a;
			}
			out.append(prev + "\n");
		}
		System.out.print(out);
	}
}
