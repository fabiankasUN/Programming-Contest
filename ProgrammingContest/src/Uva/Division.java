package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Division {

	
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
			if(line.equals("0"))
				break;
			int  n = Integer.parseInt(line);
			for (int i = 1234; i < 98765/n; i++) {
				int num = i*n;
				int k = i;
				int mask=0;
				
				if(num<10000) mask=1;
				
				while(num>=1){
					mask|=1<<(num%10);
					num/=10;
				}
				while(k>=1){
					mask|=1<<(k%10);
					k/=10;
				}
				if(mask == ((1<<10)-1)){
					out.append(num+" / "+ i + " = " + n);
				}
			}
		}
		System.out.print(out);
	}
}
