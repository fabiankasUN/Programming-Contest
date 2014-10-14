package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Memory {

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
		int cont=0;
		int index=0;
		int res=0;
		int times=1;
		for (int i = 0; i < n; i++) {
			String l[] = in.readLine().split(" ");
			int num = Integer.parseInt(l[0]);
			int  k =Integer.parseInt(l[1]);
			char cad[]=l[2].toCharArray();
			boolean visited[]=new boolean[27];
			res=0;
			out.append("Case "+times+++ ": ");
			for (int j = 0; j < cad.length; j++) {
				cont=k;
				index=j-1;
				while(cont>0 && index>=0){
					if(cad[index]==cad[j] ){
						res++;
						break;	
					}
					cont--;
					index--;
				}
			}
			out.append(res+"\n");
		}
		System.out.print(out);
	}
}
