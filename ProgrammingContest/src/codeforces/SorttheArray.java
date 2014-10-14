package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SorttheArray {

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
			int x2 = 0;
			int y = 0;
			int x = 0;
			int y2 = 0;
			int index1 = 0;
			int index2 = 0;
			boolean cond = false;
			for (int i = 0; i < arr.length - 1 && !cond; i++) {
				if (arr[i] > arr[i + 1]) {
					if (cont == 0) {
						x = arr[i];
						x2 = arr[i + 1];
						index1 = i + 1;
					} else if (cont == 1) {
						y = arr[i + 1];
						y2 = arr[i];
						index2 = i + 2;
					} else {
						cond = true;
					}
					cont++;

				}
			}
			if (cond) {
				System.out.println("no");
			} else if (cont <= 1) {
				if (cont == 1) {
					int temp = arr[index1-1];
					arr[index1-1] = arr[index1 ];
					arr[index1] = temp;
					cont = 0;
					for (int i = 0; i < arr.length - 1; i++) {
						if (arr[i] > arr[i + 1]) {
							cont++;
							break;
						}
					}
					if (cont == 0)
						System.out.println("yes\n" + index1 + " "
								+ (index1 + 1));
					else{
						System.out.println("no");
					}
				} else
					System.out.println("yes\n" + "1 1");
				
			} else if (x > y2 && y < x2) {
				System.out.println("yes\n" + index1 + " " + index2);
				
			}else{
				System.out.println("no");
			}
		}

	}
}
