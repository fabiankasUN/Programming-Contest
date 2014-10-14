package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Sales {

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
			in.readLine();
			int arr [] = atoi(in.readLine());
			int total=0;
			for (int j = 1; j < arr.length; j++) {
				for (int k = j-1; k >=0; k--) {
					total+=arr[k]<=arr[j]?1:0;
				}
			}
			out.append(total+"\n");
		}
		System.out.print(out);
	}
}
