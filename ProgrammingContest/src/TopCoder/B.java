package TopCoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		int n = Integer.parseInt(line);
		int arr[] = atoi(in.readLine());
		int i = 0;
		int res = 0;

		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (int j = 0; j < arr.length; j++) {
			if (arr[j] == 1)
				for (int k = j + 1; k < arr.length; k++) {
					if (arr[k] == 1) {
						lista.add(k - j);
						break;
					}
				}
		}

		if (lista.size() > 0) {
			res++;
		}
		for (int j = 0; j < lista.size(); j++) {
			if (lista.get(j) == 1)
				res++;
			else if (lista.get(j) == 2) {
				res += 2;
			} else {
				res += 2;
			}

		}

		System.out.println(res);
	}
}
