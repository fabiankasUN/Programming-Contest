package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class hometask {

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
		int arr[] = atoi(in.readLine());
		Arrays.sort(arr);
		if (arr[0] == 0) {
			if (arr[arr.length - 1] == 0) {
				out.append(0 + "\n");
			} else {
				for (int i = arr.length - 1; i >= 1; i--) {
					out.append(arr[i]);
				}
				out.append(0 + "\n");
			}
		} else {
			boolean search = false;
			;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == 5) {
					search = true;
				}
			}
			
		}
	}
}
