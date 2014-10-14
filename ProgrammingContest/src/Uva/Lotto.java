package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Lotto {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}
	static void printArrayInt(int[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				System.out.print(" ");
			System.out.print(array[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		int time=0;
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (line.equals("0"))
				break;
			else{
				if(time++>0){
					out.append("\n");
				}
			}
			int arr2[] = atoi(line);
			int arr[] = new int[arr2.length-1];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = arr2[i + 1];
			}
			
			Arrays.sort(arr);
			int n = arr2[0];
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					for (int k = j + 1; k < n; k++) {
						for (int l = k + 1; l < n; l++) {
							for (int m = l + 1; m < n; m++) {
								for (int s = m + 1; s < n; s++) {
									if (i != j && i != k && i != l && i != m
											&& i != s && j != k && j != l
											&& j != m && j != s && k != l
											&& k != m && k != s && l != m
											&& l != s && m != s) {
										if (i < j && j < k && k < l && l < m
												&& m < s) {
											out.append(arr[i] + " " + arr[j]
													+ " " + arr[k] + " "
													+ arr[l] + " " + arr[m]
													+ " " + arr[s] + "\n");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.print(out);
	}
}
