package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartGrid {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static boolean dif(int first[], int second[]) {
		for (int i = 0; i < second.length; i++) {
			if (first[i] != second[i])
				return false;
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line);
			int first[] = atoi(in.readLine());
			int second[] = atoi(in.readLine());
			int third[] = new int[n + 1];
			for (int i = 0; i < second.length; i++) {
				third[first[i]] = i + 1;
			}
			
			int cont = 0;
			while (dif(first, second)) {
					for (int j = 0; j < second.length - 1; j++) {
						if (third[second[j]] > third[second[j + 1]]) {
							cont++;
							int temp = second[j];
							second[j] = second[j + 1];
							second[j + 1] = temp;
							break;
						}
				}
			}
			out.append(cont+"\n");

		}
		System.out.print(out);
	}
}
