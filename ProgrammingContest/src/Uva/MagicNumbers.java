package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MagicNumbers {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}
	public static long max =9876543210L;
	
	public static boolean validate(long x){
	
		int mask=0;
		
		while(x>=1){
			if((mask&(1<<(x%10)))!=0){
				return false;
			}
			mask|=(1<<(x%10));
			x/=10;
		}
		
		return true;
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		int n = Integer.parseInt(line);
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < n; i++) {
			in.readLine();
			long num = Long.parseLong(in.readLine());
			long m = max/num;
			for (int j = 1; j <=m; j++) {
				if(validate(j) && validate(((long)num*j)))
						out.append((long)num*j + " / " + j + " = " + num+"\n");
			}
			if(i!=n-1)
				out.append("\n");
		}
		
		System.out.print(out);
	}
}
