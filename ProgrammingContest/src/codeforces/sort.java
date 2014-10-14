package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class sort {

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
		int cont = 0;
		if (arr.length == 1)
			System.out.println("yes\n1 1");
		else {
			int x = -1;
			int y = -1;
			boolean k = false;
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					if (x == -1) {
						x = i;
						y = x + 1;
					} else {
						y = i + 1;
					}
				}
			}

			if (x == -1 && y == -1)
				System.out.println("yes\n1 1");
				 else {
				boolean cond = true;
				for (int i = x; i < y; i++) {
					if (arr[i] < arr[i + 1])
						cond = false;
				}
				if(y<arr.length-1 && arr[x]>arr[y+1]){
					cond=false;
				}
				if (cond) {

					System.out.println("yes\n" + (x + 1) + " " + (y + 1));
				} else {
					System.out.println("no");
				}
			}

		}
	}
}
