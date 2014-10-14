package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Antiarithmetic {

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
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (line.equals("0"))
				break;
			String l[] = line.split(":");
			l[1] = l[1].substring(1);
			int arr[] = atoi(l[1]);
			int dp[] = new int[arr.length];
			for (int i = 0; i < dp.length; i++) {
				dp[arr[i]]=i;
			}
			boolean k = true;
			
			for (int i = 0; i < arr.length && k; i++) {
				for (int j = 1; j <= (arr.length-(i+1))/2; j++) {
					if(dp[i+j]>dp[i] && dp[i+2*j]>dp[i] && dp[i+j]<dp[i+2*j]){
						k=false;
						break;
					}
					if(dp[i+j]<dp[i] && dp[i+2*j]<dp[i] && dp[i+j]>dp[i+2*j]){
						k=false;
						break;
					}
				}
				
			}
			/*int cont=0;
			for (int i = 0; i < 10000; i++) {
				cont+=(10000-(i+1))/2;
				System.out.println(cont);
			}
			System.out.println(cont);
			*/
			
			out.append((k?"yes":"no") + "\n");
		}
		System.out.print(out);
	}
}
